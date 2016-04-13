# spring-data-crate-example

**Spring Boot Application with [Spring Data Crate](https://github.com/KPTechnologyLab/spring-data-crate)**

## Requirements

This sample app requires the `crate-spring-data` jar file which is not available
via a public repository, but must be built from
[source](https://github.com/crate/spring-data-crate).

```console
git clone git@github.com:crate/spring-data-crate.git
cd spring-data-crate
mvn clean install -DskipTests
```

Then move the resulting `jar` file into the `./libs` folder of this project.

## Setup

```console
./gradlew clean compileJava idea
```

## Run Application

```console
./gradlew clean bootRun
```

## Tutorial

See https://crate.io/a/using-sprint-data-crate-with-your-java-rest-application/
