package com.example.browserstack.PageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    protected void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void scrollToElement(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            System.out.println("Scrolled to the element successfully.");
        } catch (Exception e) {
            System.err.println("Failed to scroll to the element: " + e.getMessage());
        }
    }

    protected void clickElement(WebElement element) {
        try {
            waitForVisibility(element);
            element.click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            System.out.println("Standard click failed, attempting JavaScript click.");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            System.err.println("Failed to click element: " + e.getMessage());
        }
    }
}
