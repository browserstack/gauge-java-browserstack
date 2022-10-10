package com.browserstack.gauge.pages;

public class DriverFactory {
    private static final String USERNAME = System.getenv("BROWSERSTACK_USERNAME");
    private static final String AUTOMATE_KEY = System.getenv("BROWSERSTACK_ACCESS_KEY");
    private static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    @BeforeSpec
    public void setUp() {
        try {
            DesiredCapabilities caps = new DesiredCapabilities();

            // Capabilities from environment
            if(System.getenv("DEVICE") !=  null){
                caps.setCapability("browserName", System.getenv("BROWSERNAME"));
                caps.setCapability("platform", System.getenv("PLATFORM"));
                caps.setCapability("device", System.getenv("DEVICE"));
            }
            else {
                caps.setCapability("browser", System.getenv("BROWSER"));
                caps.setCapability("browser_version", System.getenv("BROWSER_VERSION"));
                
                caps.setCapability("os", System.getenv("OS"));
                caps.setCapability("os_version", System.getenv("OS_VERSION"));
            }

            // Hardcoded capabilities
            caps.setCapability("build", "browserstack-build-1");
            caps.setCapability("browserstack.debug", "true");
            caps.setCapability("browserstack.source", "gauge-java:sample-master:v1.0");
            caps.setCapability("name", "BStack gauge-java");

            URL remoteURL = new URL(URL);

            driver = new RemoteWebDriver(remoteURL, caps);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        } catch (MalformedURLException e) {

            System.out.println(e.toString());

        }
    }

    @AfterSpec
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
