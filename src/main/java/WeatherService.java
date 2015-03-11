import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class WeatherService {
    CloseableHttpClient httpClient;
    CloseableHttpResponse response;
    URIBuilder uriBuilder;

    public WeatherService(String date) {
        RequestConfig globalConfig = RequestConfig.custom()
                .setCookieSpec(CookieSpecs.IGNORE_COOKIES)
                .build();
        httpClient = HttpClients.custom()
                .setDefaultRequestConfig(globalConfig)
                .build();
        uriBuilder = new URIBuilder().setScheme("http")
                .setHost("api.worldweatheronline.com")
                .setPath("/free/v2/past-weather.ashx")
                .setParameter("key", "b868de27c36d1354e818a8447a21a")
                .setParameter("format", "json")
                .setParameter("date", date)
                .setParameter("tp", "24"); // time interval in hours; 24 hourly is day average
    }

    public void requestWeather(City city) {
        try {
            // Send request
            URI uri = uriBuilder.setParameter("q", city.getName() + ",ua")
                    .build();
            HttpGet getRequest = new HttpGet(uri);
            for (int i = 0; i < 3; i++) { // 3 tries to send request because occasionally service responds with 503 error
                response = httpClient.execute(getRequest);
                Thread.sleep(200); // service allows max 5 requests per second
                if (response.getStatusLine().getStatusCode() == 200) {
                    break;
                }
            }
            if (response.getStatusLine().getStatusCode() != 200) {
                System.out.println(response.getStatusLine().getStatusCode() +
                        " error requesting weather data for " + city.getName());
                return;
            }

            // Get weather data from response
            String responseString = EntityUtils.toString(response.getEntity());
            JSONObject weatherData = new JSONObject(responseString)
                        .getJSONObject("data")
                        .getJSONArray("weather")
                        .getJSONObject(0)
                        .getJSONArray("hourly")
                        .getJSONObject(0);

            // Set weather data to city instance
            city.setWeather(new Weather(weatherData.getString("tempC"), weatherData.getString("cloudcover"),
                    weatherData.getString("humidity"), weatherData.getString("pressure")));
        } catch (JSONException e) {
            System.out.println("Weather data not found for '" + city.getName() + "' city");
        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void closeSession() {
        try {
            httpClient.close();
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
