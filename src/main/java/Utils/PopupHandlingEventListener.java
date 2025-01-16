package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PopupHandlingEventListener implements WebDriverListener {

    private final WebDriver driver;

    public PopupHandlingEventListener(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void beforeAnyWebElementCall(WebElement element, java.lang.reflect.Method method, Object[] args) {
        handleAcceptAndContinuePopup();
    }

    @Override
    public void beforeAnyWebDriverCall(WebDriver driver, java.lang.reflect.Method method, Object[] args) {
        handleAcceptAndContinuePopup();
    }

    private void handleAcceptAndContinuePopup() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            By popupButtonLocator = By.xpath("//button[contains(text(), 'ACCEPT AND CONTINUE')]");

            // Check if the popup button is visible
            WebElement popupButton = wait.until(ExpectedConditions.visibilityOfElementLocated(popupButtonLocator));
            popupButton.click();
            System.out.println("Popup handled successfully: 'Accept and Continue' clicked.");
        } catch (Exception e) {
            // Ignore if the popup is not present
        }
    }
}
