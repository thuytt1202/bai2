package PageObject;

import net.bytebuddy.asm.Advice;
import org.checkerframework.common.initializedfields.qual.EnsuresInitializedFields;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.time.Duration;
import java.util.List;

public class HomePage {
    @FindBy(xpath = "//input[@id=\"key\"]")
    public WebElement tbSearch;

    By suggetion = By.xpath("//div[contains(@class, \" suggest \")]");
    By topHit = By.xpath("//li[@class=\"ais-Hits-item\"]");
    By topRealted = By.xpath("//li[@class=\"ais-Hits-item news-hits-item hits-js\"]");

    WebDriver webDriver;
    public HomePage(WebDriver webDriver)
    {
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver , this);
    }
    public void inputKeyword (String keyword)
    {
        this.tbSearch.sendKeys(keyword);
    }

    public WebElement getSuggestionbox ()
    {
        WebDriverWait waitSuggesttion = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        WebElement plnSuggestion = waitSuggesttion.until(
                ExpectedConditions.visibilityOfElementLocated(suggetion)
        );
        return plnSuggestion;
    }
  public List<WebElement> getTopHit()
  {
      WebElement plnSuggesttion  = this.getSuggestionbox();
      List<WebElement> lstTophit = plnSuggesttion.findElements(topHit);
      return lstTophit;
  }
    public List<WebElement> getTopRelated()
    {
        WebElement plnSuggestion  = this.getSuggestionbox();
        List<WebElement> lstTopRelation = plnSuggestion.findElements(topRealted);
        return lstTopRelation;
    }

}
