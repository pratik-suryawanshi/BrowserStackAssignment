package com.example.browserstack;

import Utils.Constants;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VisitWebsiteTest extends BaseTest {

    @Test
    public void validateWebsiteLanguage() {
        // Step 1: Navigate to the El País website

        driver.get(Constants.EL_PAIS_URL);

        // Step 2: Validate that the website's text is displayed in Spanish
        String languageAttribute = driver.findElement(By.tagName("html")).getAttribute("lang");
        System.out.println("Language attribute: " + languageAttribute);

        // Assert that the language is Spanish (lang="es")
        Assert.assertEquals(languageAttribute, "es-ES", "The website's language should be Spanish (lang='es').");
    }
}