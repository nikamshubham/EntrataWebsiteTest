package com.automation;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EntrataTests {
	
	private WebDriver driver;
    private WebDriverWait wait;
    
    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
        
        driver.get("https://www.entrata.com/");
        driver.findElement(By.linkText("Accept Cookies")).click();
    }
    
    @Test
    public void testHomePageTitle() {
        // Verify page title of homepage
        String expectedTitle = "Property Management Software | Entrata";
        String actualTitle = driver.getTitle();
        System.out.println("actualTitle "+actualTitle);
        Assert.assertEquals(actualTitle, expectedTitle, "Home page title does not match.");
    }

    @Test
    public void testMultifamilyChallengesCards() {
    	 // Step 1: Wait for the section to be visible and scroll into view 
        WebElement challengesSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='grid-3 hp-grid']")));
        
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", challengesSection);

        // Step 3: Located all cards in the section
        List<WebElement> cards = driver.findElements(By.xpath("//div[@class='gc-card']"));

        // Step 4: Assert the number of cards 
        int expectedCardCount = 6;
        Assert.assertEquals(cards.size(), expectedCardCount, "The number of cards does not match the expected value!");


        // Step 5: Extract the name of each card and verify them 
        String[] expectedTitles  = {"Combat declining rental growth", "Find new ancillary revenue streams", 
        		"Bring AI into your site-level processes", "Make it easier for your onsite teams to do their jobs", 
        		"Enable your properties to be more competitive","Shorten lead-to-lease time"};
        
        for (int i = 0; i < cards.size(); i++) {
            // Get the title of the card 
            String cardTitle = cards.get(i).findElement(By.tagName("h3")).getText();

            // Assert that the title matches the expected value
            Assert.assertEquals(cardTitle, expectedTitles[i], "The card title does not match the expected value!");
        }

        System.out.println("All card names and counts are verified successfully.");
    }
    
    
    @Test
    public void testNavigateToBlogPage() {
    	Actions actions = new Actions(driver);
    	
       // Wait for the Resources link and hover over it
        WebElement resourcesLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Resources")));
        actions.moveToElement(resourcesLink).perform();  // Hover to show the dropdown

        // Now find the Blog link in the dropdown
        WebElement blogLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Blog")));
        blogLink.click();  // Click the Blog link
        
        // Verify the navigation to Blog page
        // Assertion 1: Verify URL contains "/blog"
        wait.until(ExpectedConditions.urlContains("/blog"));
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL - "+currentUrl);
        Assert.assertTrue(currentUrl.contains("/blog"), "The URL does not contain '/blog'. Navigation failed.");

        // Assertion 2: Verify page title contains "Blog"
        String pageTitle = driver.getTitle();
        System.out.println("Page Title - "+pageTitle);
        Assert.assertTrue(pageTitle.contains("Entrata | Blog"), "The page title does not contain 'Blog'. Navigation failed.");

        // Assertion 3: Verify the presence of an element related to the blog
        WebElement blogHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.website-button")));
        System.out.println("Blog Heading - "+blogHeading);
        Assert.assertTrue(blogHeading.isDisplayed(), "Blog heading is not displayed. Navigation failed.");
        System.out.println("Successfully navigated to Blog page!");
        
        
    }
    
    
    @Test
    public void testSignInResidentLogin() {
    	 // Step 1: Click on the "Sign In" link
        WebElement signInLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Sign In")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", signInLink);
//        signInLink.click();

        // Assertion 1: Verify that we are on the Sign In page by URL and page title
        wait.until(ExpectedConditions.urlContains("/sign-in"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/sign-in"), "URL does not contain '/sign-in'.");
        String signInPageTitle = driver.getTitle();
        Assert.assertTrue(signInPageTitle.contains("Entrata Sign In"), "Sign In page title is incorrect.");
        System.out.println("Assertion 1");
        
        // Step 2: On Sign In page, click on the "Resident Login" link
        WebElement residentLoginLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Resident Login")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", residentLoginLink);


        // Assertion 2: Verify that we are on the Resident Login page
        wait.until(ExpectedConditions.urlContains("https://www.residentportal.com/"));
        Assert.assertTrue(driver.getCurrentUrl().contains("https://www.residentportal.com/"), "URL does not contain '/resident'.");
        String residentLoginPageTitle = driver.getTitle();
        Assert.assertTrue(residentLoginPageTitle.contains("Welcome to the Resident Portal App"), "Resident Login page title is incorrect.");
        
        
        System.out.println("All assertions passed. Successfully navigated through Sign In, Resident Login pages!");
    } 
    
    
    
    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
