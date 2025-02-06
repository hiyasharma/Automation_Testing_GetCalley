package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CallyInitialize {
	
	protected WebDriver web_driver;
	protected ChromeDriver driver;


	@BeforeClass
    public void setUp() {

		Utility.agents.add(new Agent("Agent18", "9924262739", "agent18@example.com", "password123"));
		Utility.agents.add(new Agent("Agent19", "9824262740", "agent19@example.com", "securePass456"));
		
		String user_name = Utility.UserName;
		String password = Utility.Password;
		
		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver();

		driver.get("https://app.getcalley.com/Login.aspx");
		driver.manage().window().maximize();
		driver.findElement(By.id("txtEmailId")).sendKeys(user_name);
		driver.findElement(By.id("txtPassword")).sendKeys(password);
		driver.findElement(By.id("btnLogIn")).click();

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// Validate successful login
		String actual_title = driver.getTitle();
		

		if(actual_title.equals("Getcalley - Dashboard")) {
			Utility.LOGIN_STATUS = true;
			
		} else {
			Utility.LOGIN_STATUS = false;
			
			throw new IllegalStateException("Login failed.");
		}
       
	}
	
	@AfterClass
    public void tearDown() {
        if (web_driver != null) {
        	web_driver.quit();
        }
        if (driver != null) {
        	driver.quit();
        }
    }


}
