package com.example.browserstack;

import com.example.browserstack.PageObjects.OpinionPage;
import Utils.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ScrapeArticlesTest extends BaseTest {

    @Test
    public void scrapeOpinionSectionArticles() {
        // Step 1: Navigate to the El País website
        driver.get(Constants.EL_PAIS_URL);
        Assert.assertEquals(driver.getCurrentUrl(), Constants.EL_PAIS_URL, "Failed to navigate to El País website.");

        // Step 2: Navigate to the Opinion section
        OpinionPage opinionPage = new OpinionPage(driver);
        opinionPage.handleCookiePopup();
        opinionPage.clickPopularArticleLink();

        // Step 3: Fetch and print the first five articles
        int articleCount = 5;
        for (int i = 1; i <= articleCount; i++) {
            // Fetch the article title
            String title = opinionPage.getArticleTitle(i);
            Assert.assertNotNull(title, "Article " + i + " title is null.");
            Assert.assertFalse(title.isEmpty(), "Article " + i + " title is empty.");

            // Fetch the article content
            String content = opinionPage.getArticleContent(i);
            Assert.assertNotNull(content, "Article " + i + " content is null.");
            Assert.assertFalse(content.isEmpty(), "Article " + i + " content is empty.");

            System.out.println("Article " + i + " Title: " + title);
            System.out.println("Article " + i + " Content: " + content);

            // Step 4: Download and save the cover image if available
            boolean isImageSaved = opinionPage.saveCoverImage(Constants.IMAGE_SAVE_PATH, i);
            if (isImageSaved) {
                System.out.println("Cover image for Article " + i + " saved successfully.");
            } else {
                System.out.println("No cover image available for Article " + i + ".");
            }
        }
    }
}
