package com.example.browserstack;

import com.example.browserstack.PageObjects.OpinionPage;
import Utils.Constants;
import Utils.TranslateAPI;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class TranslateHeadersTest extends BaseTest {

    @Test
    public void translateArticleHeaders() {
        // Step 1: Navigate to the El País website
        driver.get(Constants.EL_PAIS_URL);

        // Assert the page title to confirm navigation
        Assert.assertTrue(driver.getTitle().contains("EL PAÍS"), "Navigation to El País failed.");

        // Step 2: Navigate to the Opinion section
        OpinionPage opinionPage = new OpinionPage(driver);
        opinionPage.handleCookiePopup();
        opinionPage.clickPopularArticleLink();

        // Step 3: Fetch the first five article titles
        int articleCount = 5;
        Map<String, String> translatedHeaders = new HashMap<>();

        for (int i = 1; i <= articleCount; i++) {
            // Fetch the title
            String title = opinionPage.getArticleTitle(i);
            Assert.assertNotNull(title, "Article title is null for article " + i);

            // Translate the title to English
            String translatedTitle = TranslateAPI.translateText(title, "es", "en");
            Assert.assertNotNull(translatedTitle, "Translation failed for article title: " + title);

            System.out.println("Original Title: " + title);
            System.out.println("Translated Title: " + translatedTitle);

            translatedHeaders.put(title, translatedTitle);
        }

        // Step 4: Identify repeated words in translated titles
        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String translatedTitle : translatedHeaders.values()) {
            String[] words = translatedTitle.toLowerCase().split("\\W+");
            for (String word : words) {
                wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
            }
        }

        // Step 5: Print words repeated more than twice
        System.out.println("Repeated Words (more than twice):");
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            if (entry.getValue() > 2) {
                System.out.println("Word: " + entry.getKey() + ", Count: " + entry.getValue());
            }
        }
    }
}
