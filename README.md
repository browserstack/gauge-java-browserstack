# gauge-java-browserstack

[Gauge](https://docs.gauge.org/) Integration with BrowserStack

![BrowserStack Logo](https://d98b8t1nnulk5.cloudfront.net/production/images/layout/logo-header.png?1469004780)

## Prerequisites

* [BrowserStack Automate](https://www.browserstack.com/automate) account with at least 4 parallel tests. Signup for a free trial [here](https://www.browserstack.com/users/sign_up).
* [Gauge](https://docs.gauge.org/) should be installed and in $PATH. Latest version of Gauge can be downloaded from [the website](https://docs.gauge.org/getting_started/installing-gauge.html?os=macos&language=java&ide=vscode).
* [Maven](http://maven.apache.org/) should be installed and in $PATH. Latest version of Maven can be downloaded from [the website](http://maven.apache.org/install.html).

## Setup

* Clone the repo
* Install dependencies `mvn compile`
* Update `env/default/default.properties` with your [BrowserStack Username and Access Key](https://www.browserstack.com/accounts/settings)
* Update `gauge_jvm_args` with `-javaagent:<your-jar-path>` if running with gradle

## Running the tests with maven
* To run the sample specs, run `mvn test -P sample-test`
* To run the sample local specs, run `mvn test -P sample-local-test`

## Running the tests with gradle
* To build gradle wrapper run `gradle wrapper`
* To clean build gradle run `gradle clean build`
* To run the sample specs, run `gradle runSingleSpec`
* To run the sample local specs, run `gradle runLocalSpec`
* Also can run through `gradle gauge -PspecsDir=sample-spces`

## Notes
* You can view your test results on the [BrowserStack Automate dashboard](https://www.browserstack.com/automate)
* To test on a different set of browsers, check out our [platform configurator](https://www.browserstack.com/automate/java#setting-os-and-browser)

## Additional Resources
* [Documentation for writing Automate test scripts in Java](https://www.browserstack.com/automate/java)
* [Customizing your tests on BrowserStack](https://www.browserstack.com/automate/capabilities)
* [Browsers & mobile devices for selenium testing on BrowserStack](https://www.browserstack.com/list-of-browsers-and-platforms?product=automate)
* [Using REST API to access information about your tests via the command-line interface](https://www.browserstack.com/automate/rest-api)
