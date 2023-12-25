
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;


public class OpenCart {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		String firstName = "Vishwanath";
		String lastName = "D B";
		String email = "vishdb2@gmail.com";
		String phone = "9876543210";
		String password = "Vish@123";

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		driver.get("https://awesomeqa.com/ui/index.php?route=common/home");

		driver.findElement(By.xpath("//span[text()='My Account']")).click();

//		driver.findElement(By.xpath("//a[text()='Register']")).click();
//
//		driver.findElement(By.id("input-firstname")).sendKeys(firstName);
//		driver.findElement(By.id("input-lastname")).sendKeys(lastName);
//		driver.findElement(By.id("input-email")).sendKeys(email);
//		driver.findElement(By.id("input-telephone")).sendKeys(phone);
//		driver.findElement(By.id("input-password")).sendKeys(password);
//		driver.findElement(By.id("input-confirm")).sendKeys(password);
//		driver.findElement(By.cssSelector("input[name='agree']")).click();
//		driver.findElement(By.cssSelector("input[value='Continue']")).click();
//
//		driver.findElement(By.xpath("//a[@class = 'btn btn-primary']")).click();

		driver.findElement(By.xpath("//a[text()='Login']")).click();

		driver.findElement(By.id("input-email")).sendKeys(email);
		driver.findElement(By.id("input-password")).sendKeys(password);
		driver.findElement(By.cssSelector("input[value='Login']")).click();

		driver.findElement(By.className("fa-home")).click();

		List<WebElement> items = driver.findElements(By.className("product-thumb"));

		WebElement item = items.stream()
				.filter(i -> i.findElement(By.tagName("h4")).getText().equalsIgnoreCase("iPhone"))
				.collect(Collectors.toList()).get(0);

		item.findElement(By.tagName("a")).click();

		driver.findElement(By.id("input-quantity")).sendKeys(Keys.BACK_SPACE);
		driver.findElement(By.id("input-quantity")).sendKeys("2");
		driver.findElement(By.id("button-cart")).click();

		driver.findElement(By.xpath("//span[text()='Shopping Cart']")).click();

		driver.findElement(By.xpath("//h4[@class='panel-title'] //a[text()='Use Coupon Code ']")).click();
		driver.findElement(By.id("input-coupon")).sendKeys("new");
		driver.findElement(By.id("button-coupon")).click();

		String couponMsg = driver.findElement(By.cssSelector(".alert.alert-danger.alert-dismissible")).getText();

		System.out.println(couponMsg);

		Thread.sleep(3000);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");

		driver.findElement(By.xpath("//h4[@class='panel-title'] //a[text()='Estimate Shipping & Taxes ']")).click();

		WebElement dropdown = driver.findElement(By.id("input-country"));
		Select select = new Select(dropdown);
		select.selectByVisibleText("India");

		dropdown = driver.findElement(By.id("input-zone"));
		select = new Select(dropdown);
		select.selectByVisibleText("Karnataka");

		driver.findElement(By.id("input-postcode")).sendKeys("572104");

		driver.findElement(By.id("button-quote")).click();

		driver.findElement(By.cssSelector("input[value='flat.flat']")).click();
		driver.findElement(By.id("button-shipping")).click();
		
		Thread.sleep(3000);

		js.executeScript("window.scrollBy(0,1000)");

		driver.findElement(By.xpath("//a[text()='Checkout']")).click();
		
		driver.findElement(By.xpath("//div[@id = 'collapse-payment-address'] //input[@value='new']")).click();
		
		driver.findElement(By.id("input-payment-firstname")).sendKeys("Vish");
		driver.findElement(By.id("input-payment-lastname")).sendKeys("D B");
		driver.findElement(By.id("input-payment-company")).sendKeys("My Dream");
		driver.findElement(By.id("input-payment-address-1")).sendKeys("1st Cross, HMT Layout");
		driver.findElement(By.id("input-payment-city")).sendKeys("Tumkur");
		driver.findElement(By.id("input-payment-postcode")).sendKeys("572104");
		
		dropdown = driver.findElement(By.id("input-payment-country"));
		select = new Select(dropdown);
		select.selectByVisibleText("India");
		
		dropdown = driver.findElement(By.id("input-payment-zone"));
		select = new Select(dropdown);
		select.selectByValue("1489");
		
		Thread.sleep(2000);
		
		driver.findElement(By.id("button-payment-address")).click();
		
		Thread.sleep(2000);
		
		driver.findElement(By.id("button-shipping-address")).click();
		
//		driver.findElement(By.className("comment")).sendKeys("Please deliver the items as soon as possible.");
		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//div[@id='collapse-shipping-method'] //textarea[@name='comment']")).sendKeys("Please deliver the items as soon as possible.");
		driver.findElement(By.id("button-shipping-method")).click();
		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//div[@id='collapse-payment-method'] //textarea[@name='comment']")).sendKeys(Keys.CONTROL+"A");
		driver.findElement(By.xpath("//div[@id='collapse-payment-method'] //textarea[@name='comment']")).sendKeys("Please expect COD for the items.");	
		driver.findElement(By.id("button-payment-method")).click();
		String getWarning = driver.findElement(By.className("alert-danger")).getText();
		System.out.println(getWarning);
		
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.id("button-payment-method")).click();
		
		Thread.sleep(2000);
		
		driver.findElement(By.id("button-confirm")).click();
		
		Thread.sleep(2000);
		
		String sucMsg = driver.findElement(By.cssSelector("div[id='content'] h1")).getText();
		System.out.println(sucMsg);
		
		driver.findElement(By.linkText("Continue")).click();
		
		
	}


}
