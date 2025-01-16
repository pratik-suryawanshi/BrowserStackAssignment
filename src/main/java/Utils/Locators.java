package Utils;

public class Locators {

    // XPath locators for OpinionPage
    public static final String ARTICLE_LINKS_XPATH = "/html/body/footer/section/header/div/a";
    public static final String ARTICLE_CONTENT_XPATH_TEMPLATE = "/html/body/section/section/div/div[1]/article[%d]/p";
    public static final String ARTICLE_IMAGE_XPATH_TEMPLATE = "/html/body/section/section/div/div[1]/article[%d]/figure/a/img";
    public static final String ARTICLE_TITLE_XPATH_TEMPLATE = "/html/body/section/section/div/div[1]/article[%d]/header/h2/a";
    public static final String POPULAR_ARTICLE_LINK_XPATH = "/html/body/footer/section/header/div/a";
    public static final String COOKIE_POPUP_ACCEPT_BUTTON_ID = "didomi-notice-agree-button";
}
