# Gauge (Java) with BrowserStack SDK

Run Gauge + Selenium specs on BrowserStack using the [BrowserStack Java SDK](https://www.browserstack.com/docs/automate/selenium/sdk-installation).
The SDK attaches as a `-javaagent`, reads `browserstack.yml`, and instruments WebDriver — capabilities and
the hub URL come from `browserstack.yml`, not from the test code or `env/*.properties`.

> This is the SDK sample (on the `sdk` branch). The legacy capability-based sample (`env/*.properties`,
> the manual `RemoteWebDriver` setup in `src/test/java/.../SearchSpec.java`) is preserved for reference.

## Prerequisites

- A [BrowserStack](https://www.browserstack.com/) account (username + access key)
- JDK 8+ and Maven
- Gauge CLI (`gauge`) — see https://docs.gauge.org/getting_started/installing-gauge

## Setup

```bash
git clone -b sdk https://github.com/browserstack/gauge-java-browserstack.git
cd gauge-java-browserstack
mvn -q dependency:resolve
```

Configure credentials — set `userName`/`accessKey` in `browserstack.yml`, or:

```bash
export BROWSERSTACK_USERNAME="YOUR_USERNAME"
export BROWSERSTACK_ACCESS_KEY="YOUR_ACCESS_KEY"
```

## Run Sample Test

The add-to-cart spec (`sample-specs/add-to-cart.spec`) across the platforms in `browserstack.yml`:

```bash
# Resolve the SDK jar and attach it as the agent to Gauge's runner JVM:
export GAUGE_JAVA_OPTS="-javaagent:$(mvn -q help:evaluate -Dexpression=settings.localRepository -DforceStdout)/com/browserstack/browserstack-java-sdk/*/browserstack-java-sdk-*.jar"
mvn gauge:execute -DspecsDir=sample-specs
```

## Run Local Test

Verifies the BrowserStack Local tunnel (`browserstackLocal: true` starts it automatically):

```bash
mvn gauge:execute -DspecsDir=sample-local-specs
```

## Notes

- Results: [BrowserStack Automate dashboard](https://automate.browserstack.com/).
- Product features (Accessibility, Percy, Observability) are `browserstack.yml` keys — `testObservability: true` is on by default.

> ⚠️ **Verify before publishing:** Gauge forks a JVM for the Java runner, so the SDK `-javaagent` must reach
> that JVM (here via `GAUGE_JAVA_OPTS`). Confirm the exact attach mechanism / `framework: gauge` token with
> the Java SDK owner — this combo's wiring is best-effort and was not run live (no credentials available).
