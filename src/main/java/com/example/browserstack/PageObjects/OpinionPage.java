package com.example.browserstack.PageObjects;

import Utils.FileUtils;
import Utils.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OpinionPage extends BasePage {

    public OpinionPage(WebDriver driver) {
        super(driver);
    }

    public void handleCookiePopup() {
        try {
            WebElement cookieAcceptButton = driver.findElement(By.id(Locators.COOKIE_POPUP_ACCEPT_BUTTON_ID));
            clickElement(cookieAcceptButton);
            System.out.println("Cookie popup accepted.");
        } catch (Exception e) {
            System.out.println("No cookie popup displayed or already handled.");
        }
    }

    public String getArticleContent(int index) {
        try {
            String articleContentXPath = String.format(Locators.ARTICLE_CONTENT_XPATH_TEMPLATE, index);
            WebElement articleContent = driver.findElement(By.xpath(articleContentXPath));
            waitForVisibility(articleContent);
            return articleContent.getText().trim();
        } catch (Exception e) {
            System.err.println("Failed to load article content: " + e.getMessage());
            return null;
        }
    }

    public boolean saveCoverImage(String baseImagePath, int index) {
        try {
            String articleImageXPath = String.format(Locators.ARTICLE_IMAGE_XPATH_TEMPLATE, index);
            WebElement articleImage = driver.findElement(By.xpath(articleImageXPath));
            wait.until(ExpectedConditions.visibilityOf(articleImage));
            String imageUrl = articleImage.getAttribute("src");
            if (imageUrl != null && !imageUrl.isEmpty()) {
                String imagePath = baseImagePath + "_article_" + index + ".jpg";
                FileUtils.saveImage(imageUrl,imagePath);
                return true;
            }
        } catch (Exception e) {
            System.err.println("Failed to save cover image for article " + index + ": " + e.getMessage());
        }
        return false;
    }

    public String getArticleTitle(int index) {
        try {
            String articleTitleXPath = String.format(Locators.ARTICLE_TITLE_XPATH_TEMPLATE, index);
            WebElement articleTitle = driver.findElement(By.xpath(articleTitleXPath));
            waitForVisibility(articleTitle);
            return articleTitle.getText().trim();
        } catch (Exception e) {
            System.err.println("Failed to load article title: " + e.getMessage());
            return "Title not found";
        }
    }

    public void clickPopularArticleLink() {
        try {
            WebElement popularArticleLink = driver.findElement(By.xpath(Locators.POPULAR_ARTICLE_LINK_XPATH));
            scrollToElement(popularArticleLink);
            clickElement(popularArticleLink);
            System.out.println("Clicked on the popular article link.");
        } catch (Exception e) {
            System.err.println("Failed to click on popular article link: " + e.getMessage());
        }
    }
}
