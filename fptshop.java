package com.bravo;

import PageObject.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.asm.Advice;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.Key;
import java.time.Duration;
import java.util.List;

public class fptshop {
 WebDriver driver;
 HomePage homePage;
 String keyword = "iphone";
 @Before
    public void Setup ()
 {
     WebDriverManager.chromedriver().setup();
     //System.setProperty("webdriver.chrome.driver", "/Users/thuytt2/Downloads/chromedriver.exe");
     this.driver = new ChromeDriver();
     this.driver.manage().window().maximize();
     this.driver.get("https://fptshop.com.vn/");
     this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
     this.homePage = new HomePage(this.driver);
 }
 @After
    public  void TearDown() throws InterruptedException
 {
    Thread.sleep(2000);
    this.driver.quit();
 }
 @Test
    public void Sugesstionbox_should_show_after_keyword()
 {
     this.homePage.inputKeyword(this.keyword);
     //tbSreach.sendKeys(Keys.ENTER);
     //WebDriverWait waitpopup= new WebDriverWait(this.driver,Duration.ofSeconds(15));
     //WebElement pnlSugesstionbox = waitpopup.until(
     //        ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, \" suggest \")]"))
     //);
     WebElement plnSuggettionbox = this.homePage.getSuggestionbox();
     Assert.assertTrue(plnSuggettionbox.isDisplayed());

 }
 @Test
 public void top3_hit_Item_Should_Show_on_Suggesstion_Box()
 {
    this.homePage.inputKeyword(this.keyword);
     List<WebElement> lsttophit = this.homePage.getTopHit();
     Assert.assertEquals(3,lsttophit.size());

     boolean isbold = true;
     boolean iskeywordInclude = true;
     for ( WebElement Item: lsttophit )
     {
         WebElement Keyword = Item.findElement(By.xpath("//em"));
         String Boldstype = Keyword.getCssValue("font-weight");
         isbold = Integer.parseInt(Boldstype)>=500;
         if (isbold==false)
             break;
         iskeywordInclude = Keyword.getText().equalsIgnoreCase(this.keyword);
         if (iskeywordInclude==false)
             break;
     }
    Assert.assertTrue(isbold);
     Assert.assertTrue(iskeywordInclude );
 }
 @Test
    public void should_show_top3_related_to_search_key_word()
 {
    this.homePage.inputKeyword(keyword);
    List<WebElement> lsttoprelated = this.homePage.getTopRelated();
    Assert.assertEquals(3,lsttoprelated.size());
     boolean isbold = true;
     boolean iskeywordInclude = true;
     for ( WebElement Item: lsttoprelated )
     {
         WebElement Keyword = Item.findElement(By.xpath("//em"));
         String Boldstype = Keyword.getCssValue("font-weight");
         isbold = Integer.parseInt(Boldstype)>=500;
         if (isbold==false)
             break;
         iskeywordInclude = Keyword.getText().equalsIgnoreCase(this.keyword);
         if (iskeywordInclude==false)
             break;
     }
     Assert.assertTrue(isbold);
     Assert.assertTrue(iskeywordInclude );
 }

}
