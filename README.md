# Spring Boot App on Google Cloud Platform

> Simple Spring Boot web application with some GCP specific bindings.

## Building

The application is built with Gradle. At present, it is configured to use the Spring Boot plugin and package as an executable uber-JAR.

Build the application with Gradle:

```bash
./gradlew clean build
```

The JAR is automatically output at `build/libs/app.jar`.

## Google Cloud Platform

See the [`app.yml`](/app.yml) file for configuration of deploying the Spring Boot application to App Engine standard Java 11 runtime.

Some considerations for running in App Engine Standard:

* Do _not_ build and deploy a WAR file, even if it is executable.
  This seems to confuse App Engine and it will not start the application all the way.
* Disable the Spring Boot Actuator disk space check.
  App Engine Standard does not provision disk space by default.
  If local storage is needed for an application for temporary file operations, more investigation is required.
* When working with Cloud SQL, a private connection to the database can be used.
  The private IP _must_ be set up when the SQL instance is created.
  Do not provision a public IP.
  Serverless VPC access must be enabled for the VPC and a connector created for App Engine.
  This connector must be specified in `app.yml`.
  After this, the private IP may be used to connect to the database and keep network traffic off the public internet.

## Simulate Application Load

Use [Taurus](https://gettaurus.org/) to execute the [`load-simulation.yml`](/load-simulation.yml) HTTP traffic test.
Update any URL configurations needed, and then execute Taurus, for instance, with Docker Compose:

```bash
docker-compose run taurus load-simulation.yml
```

When running against a local target, this requires that either the `app` or `tomcat` Docker Compose services are already running.
