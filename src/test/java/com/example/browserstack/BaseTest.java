package com.example.browserstack;

import Utils.Constants;
import Utils.PopupHandlingEventListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class BaseTest {

    protected WebDriver driver;

    @BeforeClass
    @Parameters({"browser", "platform", "device", "runLocal"})
    public void setUp(
            @Optional("chrome") String browser,
            @Optional("Windows 10") String platform,
            @Optional("") String device,
            @Optional("true") String runLocal) throws Exception {

        if (Boolean.parseBoolean(runLocal)) {
            // Local WebDriver Setup
            System.out.println("Running tests locally...");
            ChromeOptions options = new ChromeOptions();
            WebDriverManager.chromedriver().setup();
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--start-maximized");

            WebDriver localDriver = new ChromeDriver(options);
            driver = wrapWithEventListener(localDriver);
            driver.get("https://www.google.co.in/");
        } else {
            // BrowserStack WebDriver Setup
            System.out.println("Running tests on BrowserStack...");
            Properties browserStackProps = new Properties();
            browserStackProps.load(Files.newInputStream(Paths.get(Constants.BROWSERSTACK_PROPERTIES_PATH)));

            // BrowserStack options
            Map<String, Object> browserstackOptions = new HashMap<>();
            browserstackOptions.put("userName", browserStackProps.getProperty("browserstack.user"));
            browserstackOptions.put("accessKey", browserStackProps.getProperty("browserstack.key"));
            browserstackOptions.put("consoleLogs", "verbose");
            browserstackOptions.put("networkLogs", true);
            browserstackOptions.put("debug", true);
            browserstackOptions.put("idleTimeout", 300);

            if (!device.isEmpty()) {
                // Mobile Configuration
                browserstackOptions.put("deviceName", device);
                browserstackOptions.put("osVersion", platform);
                browserstackOptions.put("realMobile", true);
            } else {
                // Desktop Configuration
                browserstackOptions.put("os", platform.split(" ")[0]);
                browserstackOptions.put("osVersion", platform.split(" ")[1]);
            }

            // DesiredCapabilities
            MutableCapabilities capabilities = new MutableCapabilities();
            capabilities.setCapability("browserName", browser);
            capabilities.setCapability("browserVersion", "latest");
            capabilities.setCapability("bstack:options", browserstackOptions);

            WebDriver remoteDriver = new RemoteWebDriver(new URL(browserStackProps.getProperty("browserstack.url")), capabilities);
            driver = wrapWithEventListener(remoteDriver);
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            System.out.println("Closing the browser...");
            driver.quit();
        }
    }

    /**
     * Wraps the WebDriver instance with a popup handling event listener.
     *
     * @param baseDriver The original WebDriver instance.
     * @return The WebDriver instance wrapped with the event listener.
     */
    private WebDriver wrapWithEventListener(WebDriver baseDriver) {
        PopupHandlingEventListener popupListener = new PopupHandlingEventListener(baseDriver);
        return new EventFiringDecorator<>(popupListener).decorate(baseDriver);
    }
}
