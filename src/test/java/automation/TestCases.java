package automation;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCases extends CallyInitialize {

    @Test
    public void completeWorkflow() {

        // **Step 1: Verify Login**
    	// Verifying the login status is true before proceeding with further actions
        boolean login_status = Utility.LOGIN_STATUS;
        Assert.assertEquals(login_status, true);
        System.out.println("Login successful");

        // **Step 2: Click on the first SVG menu icon**
        // Waiting for the first SVG menu icon to be clickable and then clicking it
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement firstSvgIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//svg)[1]")));
            firstSvgIcon.click();
            System.out.println("Clicked on the first SVG menu icon successfully");
        } catch (Exception e) {
            System.out.println("Failed to click the first SVG menu icon");
            e.printStackTrace();
        }

        // **Step 3: Add Feedback**
        // Navigating to the feedback template page and adding a new feedback
        driver.get("https://app.getcalley.com/feedbacktemplate.aspx");
        sleep(10000);

        driver.findElement(By.id("ContentPlaceHolder1_txtHeading")).sendKeys("Feedback_List_20");
        driver.findElement(By.id("ContentPlaceHolder1_txtDescription")).sendKeys("This is the Feedback list 20");

        WebElement dropdown = driver.findElement(By.id("ContentPlaceHolder1_ddl_type"));
        Select select = new Select(dropdown);
        select.selectByVisibleText("Lead");

        driver.findElement(By.id("ContentPlaceHolder1_btnSave")).click();
        Assert.assertEquals(driver.getTitle(), "Getcalley - Feedback List");
        System.out.println("Feedback added successfully");

        // **Step 4: Call List Power Import**
        // Uploading a sample file for power import and confirming the upload
        driver.get("https://app.getcalley.com/BulkUploadCsv.aspx");
        driver.findElement(By.id("ContentPlaceHolder1_txtlistname")).sendKeys("PowerImport13");

        try {
            WebElement fileInput = driver.findElement(By.id("ContentPlaceHolder1_fileUpload"));
            String filePath = "D:\\DevSpace\\Automation\\Sample File.xlsx";
            fileInput.sendKeys(filePath);
            sleep(5000);

            driver.findElement(By.id("btnUp")).click();
            sleep(10000);

            WebElement okButton = driver.findElement(By.cssSelector(".sa-confirm-button-container .confirm"));
            okButton.click();

        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertEquals(driver.getTitle(), "Getcalley - Map Data");
        System.out.println("File uploaded successfully");

        // **Step 5: Click the 'PowerListUpload4' Link with Timer**
        // Waiting for the 'PowerListUpload4' link to be clickable and clicking it
        System.out.println("Waiting for 'PowerListUpload4' link...");
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement link = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, 'Upload.aspx')]")));
            sleep(5000); // Adding additional wait before clicking
            link.click();
            System.out.println("Clicked 'PowerListUpload4' successfully");
        } catch (Exception e) {
            System.out.println("Failed to click 'PowerListUpload4' link");
            e.printStackTrace();
        }

        // **Step 6: Click the Call Dialing Icon**
        // Waiting for the Call Dialing icon to be clickable and then clicking it
        System.out.println("Clicking the Call Dialing icon...");
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement callDialingIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[contains(@src, 'images/icon-mobile-dial.png')]")));
            callDialingIcon.click();
            System.out.println("Clicked the Call Dialing icon successfully");
        } catch (Exception e) {
            System.out.println("Failed to click the Call Dialing icon");
            e.printStackTrace();
        }

        // **Step 7: Call From Browser**
        // Navigating to the Bulkcopy Column Map page and selecting dropdown values for import
        driver.get("https://app.getcalley.com/BulkcopyColumnMap.aspx");

        selectDropdown("ddlbelongto_1", "FirstName");
        selectDropdown("ddlbelongto_2", "Phone");
        selectDropdown("ddlbelongto_3", "Notes");

        WebElement importButton = driver.findElement(By.xpath("//input[@type='submit' and @name='ctl00$ContentPlaceHolder1$btnUpload' and @value='Import Data']"));
        importButton.click();

        WebElement okButtonFinal = driver.findElement(By.cssSelector(".sa-confirm-button-container .confirm"));
        okButtonFinal.click();

        System.out.println("Data imported successfully");
        
        // **Step 8: Navigate to Call Report Page**
        // Accessing the Call Report page
        
        driver.get("https://app.getcalley.com/CallReport.aspx");
        
        sleep(10000);
        
        // **Step 9: Perform Call Actions**
        // Performing actions on the first and last buttons on the Call Report page
        
        WebElement firstButton = driver.findElement(By.xpath("//tbody/tr[1]/td[last()]/a[1]"));
        firstButton.click();

        sleep(20000);
        
        WebElement lastButton = driver.findElement(By.xpath("//html/body/form/div[3]/div/div[16]/div/div/div/div/table/tbody/tr[1]/td[13]/a[5]/img"));
        sleep(2000);
        lastButton.click();
        
        // **Step 10: Call From Browser Page**
        // Navigating to the "CallFromBrowser" page and confirming the action
        
        
        sleep(10000);
        
        driver.get("https://app.getcalley.com/CallFromBrowser.aspx?id=MTAyMzAwNDcw");
        
        sleep(10000);
      
       // WebElement okButton1 = driver.findElement(By.cssSelector(".sa-confirm-button-container .confirm"));
        //okButton1.click();
        
        try {
            // Switch to the iframe
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/form/iframe")));

            driver.switchTo().frame(iframe); // Switch to the iframe using the element

            // Now that we're in the iframe, find the button and click it
            WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[7]/div/button")));

            // Click the button to close it
            okButton.click();

            // After performing actions inside the iframe, switch back to the main page
            driver.switchTo().defaultContent();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
        
    	}

    // **Helper Method for Sleep**
    // A helper method to add wait time between actions in the workflow
    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // **Helper Method for Dropdown Selection**
    // A helper method to select a value from a dropdown list
    private void selectDropdown(String dropdownId, String visibleText) {
        WebElement dropdown = driver.findElement(By.id(dropdownId));
        Select select = new Select(dropdown);
        select.selectByVisibleText(visibleText);
    }
}
