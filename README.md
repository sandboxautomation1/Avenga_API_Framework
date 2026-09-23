![This is an image](https://i.ibb.co/qFjKB6Sy/Photos-uk-TC5-Us92-K.png)

# AvengaAPI Automation Framework

An API automation framework for testing REST APIs with Java, Rest Assured, and TestNG. The project validates the functionality, structure, and content of responses from the [Fake REST API](https://fakerestapi.azurewebsites.net/). Tests can be executed locally with Maven, inside a Docker container, or automatically through GitHub Actions.

## Key Capabilities

The framework supports the following checks:

- retrieving collections of books and authors;
- retrieving a book or author by ID;
- creating, updating, and deleting books and authors;
- validating HTTP status codes;
- verifying that responses are not empty and contain required fields;
- validating ID uniqueness;
- validating ISO 8601 date formats;
- performing basic response-time checks;
- validating JSON schemas using the available schema files;
- recording request and response logs in an HTML report.

## Technology Stack


| Technology           | Purpose                                              |
| -------------------- | ---------------------------------------------------- |
| Java 17              | Main programming language and project target version |
| Maven                | Dependency management and test execution             |
| Rest Assured         | Sending HTTP requests and validating API responses   |
| TestNG               | Test organization, grouping, and execution           |
| Jackson and JsonPath | JSON serialization, deserialization, and processing  |
| Lombok               | Reducing boilerplate code                            |
| JavaFaker            | Generating test data                                 |
| ExtentReports        | Generating HTML test reports                         |
| Docker               | Isolated test execution                              |
| GitHub Actions       | CI/CD execution on pushes to`main`                   |

## Requirements

The following tools are required for local execution:

- JDK 17 or a compatible newer Java version;
- Apache Maven 3.8 or newer;
- internet access for calling the test API and downloading Maven dependencies.

Docker is required only when the tests are executed in a container. CI/CD execution requires Docker and a self-hosted GitHub Actions runner, according to the current workflow configuration.

Verify the installation with:

```bash
java -version
mvn -version
docker --version
```

## Project Structure

```text
.
├── pom.xml
├── Dockerfile
├── README.md
├── .github/
│   └── workflows/
│       └── api-tests.yml
├── src/
│   ├── main/java/org/avenga/
│   │   ├── client/
│   │   │   └── BaseClient.java
│   │   ├── config/
│   │   │   └── ConfigManager.java
│   │   ├── data/
│   │   │   ├── Endpoints.java
│   │   │   └── FrameworkConstants.java
│   │   ├── models/
│   │   │   ├── request/
│   │   │   └── response/
│   │   ├── reporting/
│   │   ├── services/
│   │   │   ├── Authors.java
│   │   │   ├── Books.java
│   │   │   └── Users.java
│   │   └── utils/
│   └── test/java/
│       ├── org/avenga/base/
│       ├── org/avenga/tests/
│       │   ├── authors/
│       │   └── books/
│       └── resources/
│           ├── schemas/
│           └── testng.xml
└── test-output/
```

### Responsibilities of the Main Layers

`client` contains the shared request and response specifications. It defines the base URL, JSON content type, and HTTP traffic logging.

`config` manages execution settings. `data` contains API endpoints and framework constants. `models` describes request and response objects.

`services` is the API interaction layer. It provides methods for calling book, author, and user endpoints. `tests` contains the TestNG test scenarios, while `reporting` generates the ExtentReports output.

## Base URL Configuration

By default, the tests use:

```text
https://fakerestapi.azurewebsites.net
```

The value of `BASE_URL` is resolved using the following priority:

1. Java system property;
2. environment variable;
3. the default value from `Endpoints.BASE_URL`.

Example using a Java system property:

```bash
mvn clean test -DBASE_URL=https://fakerestapi.azurewebsites.net
```

Example using an environment variable on Linux/macOS:

```bash
BASE_URL=https://fakerestapi.azurewebsites.net mvn clean test
```

Example using PowerShell:

```powershell
$env:BASE_URL = "https://fakerestapi.azurewebsites.net"
mvn clean test
```

> Use the name `BASE_URL` in uppercase. This is the key read by `ConfigManager`.

## Running the Tests

Clone the repository and navigate to its directory:

```bash
git clone https://github.com/sandboxautomation1/Avenga_API_Framework.git
cd Avenga_API_Framework
```

Run the complete TestNG suite:

```bash
mvn clean test
```

Maven Surefire uses the configuration from `src/test/java/resources/testng.xml`. The current suite executes the book and author tests with up to two parallel TestNG threads.

To run the tests again without cleaning the build artifacts, use:

```bash
mvn test
```

To run a specific TestNG class, use the standard Surefire configuration, for example:

```bash
mvn -Dtest=TS_001_GetBooks test
```

## Test Coverage

The tests are organized into the following areas:


| Area    | Covered operations and validations                                                                                                          |
| ------- | ------------------------------------------------------------------------------------------------------------------------------------------- |
| Books   | GET all, GET by ID, POST, PUT, DELETE, status code, response body, required fields, unique IDs, page count, publish date, and response time |
| Authors | GET all, GET by ID, POST, PUT, DELETE, status code, response body, required fields, and unique IDs                                          |
| Users   | Service layer and endpoint definitions are available for extending the test coverage                                                        |

TestNG tests use groups such as `smoke`, `acceptance`, and `regression`. These groups can be used to separate fast checks, acceptance checks, and regression scenarios.

## Test Reports

After the tests finish, the reports are stored in:

```text
test-output/
```

Open `test-output/index.html` in a browser. The report contains:

- an overall execution summary;
- a list of tests and their categories;
- the status of each test;
- request and response logs;
- error details for failed tests.

Additional Surefire XML reports are generated in:

```text
target/surefire-reports/
```

## Running with Docker

The Dockerfile uses Maven with Eclipse Temurin 17 and contains two stages. The first stage prepares the dependencies and compiles the project. The second stage executes the tests.

Build the Docker image:

```bash
docker build -t avenga-api-tests .
```

Run the tests in a container:

```bash
docker run --rm -v "$(pwd)/test-output:/app/test-output" avenga-api-tests
```

For PowerShell, use:

```powershell
docker run --rm -v "${PWD}/test-output:/app/test-output" avenga-api-tests
```

To use a different API URL, pass the system property to Maven inside the container:

```bash
docker run --rm \
  -v "$(pwd)/test-output:/app/test-output" \
  avenga-api-tests \
  mvn test -DBASE_URL=https://fakerestapi.azurewebsites.net
```

## CI/CD Pipeline

The `.github/workflows/api-tests.yml` workflow runs on every `push` to the `main` branch. The pipeline:

1. checks out the repository;
2. configures JDK 22 on the self-hosted runner;
3. builds a Docker image named `api-tests`;
4. runs the tests inside the Docker container;
5. uploads `test-output` as a GitHub Actions artifact;
6. publishes the report through GitHub Pages.

The pipeline uses PowerShell syntax when starting the Docker container and preparing the report. Therefore, the runner must support Docker and PowerShell, and GitHub Pages must be enabled for the repository.

## Typical Execution Flow

```text
TestNG suite
    ↓
Test class
    ↓
Service layer
    ↓
BaseClient request specification
    ↓
Rest Assured HTTP request
    ↓
Response specification and assertions
    ↓
ExtentReports request/response logs
```

## Extending the Framework

To add a new endpoint, define its path in `Endpoints.java`, create or extend a service class in `services`, add request and response models when needed, and create TestNG tests in the appropriate package under `src/test/java/org/avenga/tests`.

For every new validation, use a clear `testName`, `description`, and TestNG group. This makes the test easier to find in ExtentReports and to include in a dedicated smoke, acceptance, or regression suite.

## License and Intended Use

This project is intended for API automation demonstration and development. Before using it in a production environment, verify the availability, stability, and terms of use of the target API.

## References

Useful resources: [Fake REST API][1], [REST Assured][2], [TestNG][3], and [GitHub Actions][4].

---

**Author:** Manus AI

[1]: https://fakerestapi.azurewebsites.net/
[2]: https://rest-assured.io/
[3]: https://testng.org/
[4]: https://docs.github.com/en/actions
