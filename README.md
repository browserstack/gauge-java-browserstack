# gauge-browserstack

[Gauge](http://getgauge.io) Integration with BrowserStack

![BrowserStack Logo](https://d98b8t1nnulk5.cloudfront.net/production/images/layout/logo-header.png?1469004780)

## Prerequisites

* [BrowserStack Automate](https://www.browserstack.com/automate) account with at least 4 parallel tests. Signup for a free trial [here](https://www.browserstack.com/users/sign_up).
* [Gauge](http://getgauge.io) should be installed and in $PATH. Latest version of Gauge can be downloaded from [the website](http://getgauge.io/get-started/index.html).
* [Maven](http://maven.apache.org/) should be installed and in $PATH. Latest version of Maven can be downloaded from [the website](http://maven.apache.org/install.html).

## Setup

* Clone the repo
* Install dependencies `mvn compile`
* Update `env/default/default.properties` with your [BrowserStack Username and Access Key](https://www.browserstack.com/accounts/settings)

## Running the tests
* To run the test suite, run `mvn test`

## Notes
* You can view your test results on the [BrowserStack Automate dashboard](https://www.browserstack.com/automate)
* To test on a different set of browsers, check out our [platform configurator](https://www.browserstack.com/automate/java#setting-os-and-browser)

## Additional Resources
* [Documentation for writing Automate test scripts in Java](https://www.browserstack.com/automate/java)
* [Customizing your tests on BrowserStack](https://www.browserstack.com/automate/capabilities)
* [Browsers & mobile devices for selenium testing on BrowserStack](https://www.browserstack.com/list-of-browsers-and-platforms?product=automate)
* [Using REST API to access information about your tests via the command-line interface](https://www.browserstack.com/automate/rest-api)