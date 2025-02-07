package automation;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestCases extends CallyInitialize {

    @Test
    public void completeWorkflow() {

        //Login
        boolean login_status = Utility.LOGIN_STATUS;
        AssertJUnit.assertEquals(login_status, true);
        System.out.println("Login successful");

        //Dashboard
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement firstSvgIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//svg)[1]")));
            firstSvgIcon.click();
            System.out.println("Clicked on the first SVG menu icon successfully");
        } catch (Exception e) {
            System.out.println("Failed to click the first SVG menu icon");
            e.printStackTrace();
        }

        //Feedback Creation
        driver.get("https://app.getcalley.com/feedbacktemplate.aspx");
        sleep(10000);

        driver.findElement(By.id("ContentPlaceHolder1_txtHeading")).sendKeys("Example_Feedback_List_");
        driver.findElement(By.id("ContentPlaceHolder1_txtDescription")).sendKeys("This is the Feedback list");

        WebElement dropdown = driver.findElement(By.id("ContentPlaceHolder1_ddl_type"));
        Select select = new Select(dropdown);
        select.selectByVisibleText("Lead");

        driver.findElement(By.id("ContentPlaceHolder1_btnSave")).click();
        AssertJUnit.assertEquals(driver.getTitle(), "Getcalley - Feedback List");
        System.out.println("Feedback added successfully");

        //List Upload
        driver.get("https://app.getcalley.com/BulkUploadCsv.aspx");
        driver.findElement(By.id("ContentPlaceHolder1_txtlistname")).sendKeys("PowerImportList");

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

        AssertJUnit.assertEquals(driver.getTitle(), "Getcalley - Map Data");
        System.out.println("File uploaded successfully");

        
        System.out.println("Waiting for 'PowerListUpload4' link...");
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement link = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, 'Upload.aspx')]")));
            sleep(5000); 
            link.click();
            System.out.println("Clicked 'PowerListUpload4' successfully");
        } catch (Exception e) {
            System.out.println("Failed to click 'PowerListUpload4' link");
            e.printStackTrace();
        }

        //Clicking on Call Dialing Icon
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

        //Call From Browser
        driver.get("https://app.getcalley.com/BulkcopyColumnMap.aspx");

        selectDropdown("ddlbelongto_1", "FirstName");
        selectDropdown("ddlbelongto_2", "Phone");
        selectDropdown("ddlbelongto_3", "Notes");

        WebElement importButton = driver.findElement(By.xpath("//input[@type='submit' and @name='ctl00$ContentPlaceHolder1$btnUpload' and @value='Import Data']"));
        importButton.click();

        WebElement okButtonFinal = driver.findElement(By.cssSelector(".sa-confirm-button-container .confirm"));
        okButtonFinal.click();

        System.out.println("Data imported successfully");
        
        //Navigating to Call Report Page
        driver.get("https://app.getcalley.com/CallReport.aspx");
        
        sleep(10000);
        
        //Performing Call Actions
        WebElement firstButton = driver.findElement(By.xpath("//tbody/tr[1]/td[last()]/a[1]"));
        firstButton.click();

        sleep(20000);
        
        WebElement lastButton = driver.findElement(By.xpath("//html/body/form/div[4]/div/div[16]/div/div/div/div/table/tbody/tr[1]/td[13]/a[5]/img"));
        sleep(2000);
        lastButton.click();
        
         
        // Navigating to the "CallFromBrowser" page and confirming the action
        sleep(10000);
        Object[] windowHandles=driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles[1]);
        
        sleep(15000);
      
        WebElement okButton1 = driver.findElement(By.cssSelector(".confirm"));
        okButton1.click();
        
        sleep(15000);
        
     
        //Scenario 1          Call->Feedback->Next Call
        //Select Feedback 
        WebElement dropdownElement = driver.findElement(By.id("ContentPlaceHolder1_ddlFeedback"));
        Select dropdown1 = new Select(dropdownElement);
        dropdown1.selectByIndex(2);
        sleep(10000);
        
        //Clicking Next Call Button
        
        WebElement nextCallButton = driver.findElement(By.xpath("//div[@id='ContentPlaceHolder1_divNextCallButton']//a"));
        nextCallButton.click();
        
        sleep(15000);
        
        Object[] windowHandles1=driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles1[1]);
        
        sleep(15000);
      
        WebElement okButton2 = driver.findElement(By.cssSelector(".confirm"));
        okButton2.click();
        
        sleep(15000);
        
        
        
        //Scenario 2  Call->Feedback->Reschedule->Next Call
        //Feedback       
        WebElement dropdownElement1 = driver.findElement(By.id("ContentPlaceHolder1_ddlFeedback"));
        Select dropdown2 = new Select(dropdownElement1);
        dropdown2.selectByIndex(2);
        
        
        //Calendar
        WebElement input = driver.findElement(By.id("ContentPlaceHolder1_txtRescheduleCallDate"));

         
            ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].removeAttribute('readonly','readonly')",input);
            input.sendKeys("2025-03-11");
        
        sleep(10000);
        
        //Time
        WebElement timeInput = driver.findElement(By.id("ContentPlaceHolder1_txtRescheduleCallTime"));

        
        timeInput.clear();
        timeInput.sendKeys("10:45");
        
        sleep(10000);
        
        //Clicking next call
        WebElement nextCallButton1 = driver.findElement(By.xpath("//div[@id='ContentPlaceHolder1_divNextCallButton']//a"));
        nextCallButton1.click();

        sleep(10000);
        
        
        Object[] windowHandles2=driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles2[1]);
        
        sleep(10000);
      
        WebElement okButton3 = driver.findElement(By.cssSelector(".confirm"));
        okButton3.click();
        
        sleep(10000);
        
        // Scenario 3             Call->Feedback->Notes->Next Call
        //Select Feedback
        WebElement dropdownElement2 = driver.findElement(By.id("ContentPlaceHolder1_ddlFeedback"));
        Select dropdown3 = new Select(dropdownElement2);
        dropdown3.selectByIndex(2);
 
        sleep(10000);
        
        
        //Write Call Notes
        WebElement callNotesBox = driver.findElement(By.id("ContentPlaceHolder1_txtCallNotes"));
        callNotesBox.sendKeys("Example Call Notes");
        
        
        sleep(10000);
        
        
        //Clicking Next Call Button
        WebElement nextCallButton2 = driver.findElement(By.xpath("//div[@id='ContentPlaceHolder1_divNextCallButton']//a"));
        nextCallButton2.click();
        
        sleep(15000);
        
        Object[] windowHandles3=driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles3[1]);
        
        sleep(15000);
      
        WebElement okButton4 = driver.findElement(By.cssSelector(".confirm"));
        okButton4.click();
        
        sleep(15000);
        
        
        //Scenario 4  Call->Feedback->Reschedule->Call Notes->Next->Call
        //Select Feedback
        WebElement dropdownElement3 = driver.findElement(By.id("ContentPlaceHolder1_ddlFeedback"));
        Select dropdown4 = new Select(dropdownElement3);
        dropdown4.selectByIndex(2);
 
        //Calendar
        WebElement input1 = driver.findElement(By.id("ContentPlaceHolder1_txtRescheduleCallDate"));

         
            ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].removeAttribute('readonly','readonly')",input1);
            input1.sendKeys("2025-03-11");
        
        sleep(10000);
        
        //Time
        WebElement timeInput1 = driver.findElement(By.id("ContentPlaceHolder1_txtRescheduleCallTime"));

        
        timeInput1.clear();
        timeInput1.sendKeys("10:45");
        
        sleep(10000);
        
        //Write Call Notes
        WebElement callNotesBox1 = driver.findElement(By.id("ContentPlaceHolder1_txtCallNotes"));
        callNotesBox1.sendKeys("Example Call Notes");
        
        sleep(10000);
        
      //Clicking Next Call Button
        WebElement nextCallButton3 = driver.findElement(By.xpath("//div[@id='ContentPlaceHolder1_divNextCallButton']//a"));
        nextCallButton3.click();
        
        sleep(10000);
        
        Object[] windowHandles4=driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles4[1]);
        
        sleep(10000);
      
        WebElement okButton5 = driver.findElement(By.cssSelector(".confirm"));
        okButton5.click();
        
        sleep(10000);
        
        //Scenario 5                Call->Feedback->DND
        //Select Feedback
        WebElement dropdownElement4 = driver.findElement(By.id("ContentPlaceHolder1_ddlFeedback"));
        Select dropdown5 = new Select(dropdownElement4);
        dropdown5.selectByIndex(2);
        
        sleep(10000);
        
        //Click on DND 
        WebElement dndButton = driver.findElement(By.xpath("//span[@class='btn-call rd']/a"));
        dndButton.click();
        
        sleep(10000);
        
        //Click on pop up
        WebElement okButton = driver.findElement(By.xpath("//div[@class='sa-confirm-button-container']/button[@class='confirm']"));
        okButton.click();
        
        sleep(10000);
        

        
        Object[] windowHandles5=driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles5[1]);
        
        sleep(10000);
      
        WebElement okButton6 = driver.findElement(By.cssSelector(".confirm"));
        okButton6.click();
        
        sleep(10000);
        
        
        //Scenario 6   Call->Feedback->Reschedule->DND
        //Select Feedback
        WebElement dropdownElement5 = driver.findElement(By.id("ContentPlaceHolder1_ddlFeedback"));
        Select dropdown6 = new Select(dropdownElement5);
        dropdown6.selectByIndex(2);
        
        
        //Calendar
        WebElement input2 = driver.findElement(By.id("ContentPlaceHolder1_txtRescheduleCallDate"));

         
            ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].removeAttribute('readonly','readonly')",input2);
            input2.sendKeys("2025-03-11");
        
        sleep(10000);
        
        //Time
        WebElement timeInput2 = driver.findElement(By.id("ContentPlaceHolder1_txtRescheduleCallTime"));

        
        timeInput2.clear();
        timeInput2.sendKeys("10:45");
        
        sleep(10000);
        
        //Click on DND 
        WebElement dndButton1 = driver.findElement(By.xpath("//span[@class='btn-call rd']/a"));
        dndButton1.click();
        
        sleep(10000);
        
        //Click on pop up
        WebElement okButton7 = driver.findElement(By.xpath("//div[@class='sa-confirm-button-container']/button[@class='confirm']"));
        okButton7.click();
        
        sleep(15000);
        

        
        Object[] windowHandles6=driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles6[1]);
        
        sleep(10000);
      
        WebElement okButton8 = driver.findElement(By.cssSelector(".confirm"));
        okButton8.click();
        
        sleep(10000);
        
        //Scenario 7     Call->Feedback->Notes->DND
        
        //Select Feedback
        WebElement dropdownElement6 = driver.findElement(By.id("ContentPlaceHolder1_ddlFeedback"));
        Select dropdown7 = new Select(dropdownElement6);
        dropdown7.selectByIndex(2);
        
        sleep(10000);
        
      //Write Call Notes
        WebElement callNotesBox2 = driver.findElement(By.id("ContentPlaceHolder1_txtCallNotes"));
        callNotesBox2.sendKeys("Example Call Notes");
        
        sleep(10000);
        
        
      //Click on DND 
        WebElement dndButton2 = driver.findElement(By.xpath("//span[@class='btn-call rd']/a"));
        dndButton2.click();
        
        sleep(10000);
        
        WebElement okButtonn = driver.findElement(By.xpath("//div[@class='sa-confirm-button-container']/button[@class='confirm']"));
        okButtonn.click();
        
        Object[] windowHandles7=driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles7[1]);
        
        sleep(10000);
      
        WebElement okButton9 = driver.findElement(By.cssSelector(".confirm"));
        okButton9.click();
        
        sleep(10000);
        
        
        //Scenario 8  Call->Feedback->Reschedule->Notes->DND
        //Select Feedback
        WebElement dropdownElement7 = driver.findElement(By.id("ContentPlaceHolder1_ddlFeedback"));
        Select dropdown8 = new Select(dropdownElement7);
        dropdown8.selectByIndex(2);
        
        
        //Calendar
        WebElement input3 = driver.findElement(By.id("ContentPlaceHolder1_txtRescheduleCallDate"));

         
            ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].removeAttribute('readonly','readonly')",input3);
            input3.sendKeys("2025-03-11");
        
        sleep(10000);
        
        //Time
        WebElement timeInput3 = driver.findElement(By.id("ContentPlaceHolder1_txtRescheduleCallTime"));

        
        timeInput3.clear();
        timeInput3.sendKeys("10:45");
        
        sleep(10000);
        
        //Write Call Notes
        WebElement callNotesBox3 = driver.findElement(By.id("ContentPlaceHolder1_txtCallNotes"));
        callNotesBox3.sendKeys("Example Call Notes");
        
        sleep(10000);
        
        
      //Click on DND 
        WebElement dndButton3 = driver.findElement(By.xpath("//span[@class='btn-call rd']/a"));
        dndButton3.click();
        
        sleep(10000);
        
        WebElement okButto = driver.findElement(By.xpath("//div[@class='sa-confirm-button-container']/button[@class='confirm']"));
        okButto.click();
        
        Object[] windowHandles8=driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles8[1]);
        
        sleep(10000);
      
        WebElement okButton10 = driver.findElement(By.cssSelector(".confirm"));
        okButton10.click();
        
        sleep(10000);
        
        
        //Scenario 9            Call->Feedback->Save&Pause  
        //Select Feedback
        WebElement dropdownElement8 = driver.findElement(By.id("ContentPlaceHolder1_ddlFeedback"));
        Select dropdown9 = new Select(dropdownElement8);
        dropdown9.selectByIndex(2);
          
        sleep(10000);
      
          
        
         
        //Save and Pause Button
        WebElement savePauseButton = driver.findElement(By.xpath("//span[@class='btn-call ylw']/a"));
        savePauseButton.click();
        sleep(10000);
        
        
        WebElement okButton11 = driver.findElement(By.cssSelector(".confirm"));
        okButton11.click();
          
        sleep(10000);
        
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
