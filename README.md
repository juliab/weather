# Summary

This application creates csv file with weather information for cities provided by user.

It uses [World Weather Online REST API](http://www.worldweatheronline.com/api/) to get weather data.

# Libraries

Following libraries are used in this application:

* [Spring RESTFul Client][library:spring] - as a REST client.

* [Apache Commons CSV][library:csv-commons] and [Jackson CSV Mapper][library:jackson-csv] - to read/write CSV files.

* [Apache Commons CLI][library:cli-commons] - for parsing command line options.

* [Google Guice][library:guice] - for dependency injection.

* [JUnit][library:junit] - as a test framework.

* [SLF4J][library:slf4f] - for logging.

[library:csv-commons]: https://commons.apache.org/proper/commons-csv/
[library:spring]: https://spring.io/
[library:jackson-csv]: https://github.com/FasterXML/jackson-dataformat-csv
[library:cli-commons]: https://commons.apache.org/proper/commons-cli/
[library:slf4f]: http://www.slf4j.org/
[library:junit]: http://junit.org/
[library:guice]: https://github.com/google/guice

# Tools

To work on this project you can use: [Git][tool:git], [Maven][tool:maven]

[tool:git]: http://git-scm.com/
[tool:maven]: http://maven.apache.org/

# Usage

This application  uses [Apache Maven Compiler Plugin](https://maven.apache.org/plugins/maven-compiler-plugin/) 
and [Apache Maven Shade Plugin] (https://maven.apache.org/plugins/maven-shade-plugin/) to compile and build jar executable file.

To run application:

1. Run `mvn clean package` to compile and build jar executable file.

2. Run `java -jar target/weather-app-1.0-SNAPSHOT-shaded.jar [-d <arg>] -i <arg> -o <arg>` to run application.

```
 -d,--date <arg>     The date to return the weather for. Date format: yyyy-MM-dd. Default day is today
 -i,--input <arg>    Input CSV file path. File format: City Name,Area
 -o,--output <arg>   Output CSV file path. 
 ```
 
 Input CSV file sample:
 
```
Dnipropetrovsk,Dnipropetrovsk Oblast
Odessa,Odessa Oblast
Kiev,Kievskaya Oblast
```
