
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class OpenCartModularize {

	WebDriver driver;
	String firstName, lastName, email, phone, password, url;
	static WebDriverWait wait;

	public OpenCartModularize(String firstName, String lastName, String email, String phone, String password,
			String url) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.url = url;
	}

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		String firstName = "Vishwanath";
		String lastName = "D B";
		String email = "vishdb2@gmail.com";
		String phone = "9876543210";
		String password = "Vish@123";
		String url = "https://awesomeqa.com/ui/index.php?route=common/home";

		OpenCartModularize openCart = new OpenCartModularize(firstName, lastName, email, phone, password, url);

		openCart.createWebDriverObject();

		openCart.launchApp();

//		openCart.registartion();

		openCart.Login();

		openCart.selectItem("iPhone");

		openCart.addToCart();

		openCart.moveToCheckOut("new", "India", "Karnataka", "572104");

		openCart.addPaymentAddress("Vish", "D B", "My Dream", "1st Cross, HMT Layout", "Tumkur", "572104", "India",
				"1489");

		openCart.selectDeliveryAddress();

		openCart.deliveryAndPaymentMethod();

		openCart.confirmOrder();

		openCart.moveToHomePage();

//		openCart.closeBrowser();		

	}

	void createWebDriverObject() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}

	void launchApp() {
		driver.get(url);
	}

	void registartion() {

		driver.findElement(By.xpath("//span[text()='My Account']")).click();

		driver.findElement(By.xpath("//a[text()='Register']")).click();

		driver.findElement(By.id("input-firstname")).sendKeys(firstName);
		driver.findElement(By.id("input-lastname")).sendKeys(lastName);
		driver.findElement(By.id("input-email")).sendKeys(email);
		driver.findElement(By.id("input-telephone")).sendKeys(phone);
		driver.findElement(By.id("input-password")).sendKeys(password);
		driver.findElement(By.id("input-confirm")).sendKeys(password);
		driver.findElement(By.cssSelector("input[name='agree']")).click();
		driver.findElement(By.cssSelector("input[value='Continue']")).click();

		driver.findElement(By.xpath("//a[@class = 'btn btn-primary']")).click();

		System.out.println("Registration Successful");

		driver.findElement(By.className("fa-home")).click();

	}

	void Login() {

		driver.findElement(By.xpath("//span[text()='My Account']")).click();

		driver.findElement(By.xpath("//a[text()='Login']")).click();

		driver.findElement(By.id("input-email")).sendKeys(email);
		driver.findElement(By.id("input-password")).sendKeys(password);
		driver.findElement(By.cssSelector("input[value='Login']")).click();

		System.out.println("Login Successful");

		driver.findElement(By.className("fa-home")).click();
	}

	void selectItem(String itemToSelect) {

		List<WebElement> items = driver.findElements(By.className("product-thumb"));

		WebElement item = items.stream()
				.filter(i -> i.findElement(By.tagName("h4")).getText().equalsIgnoreCase(itemToSelect))
				.collect(Collectors.toList()).get(0);

		item.findElement(By.tagName("a")).click();
	}

	void addToCart() {

		driver.findElement(By.id("input-quantity")).sendKeys(Keys.BACK_SPACE);
		driver.findElement(By.id("input-quantity")).sendKeys("2");
		driver.findElement(By.id("button-cart")).click();

		System.out.println(driver.findElement(By.cssSelector(".alert")).getText());
	}

	void moveToCheckOut(String coupon, String country, String state, String pincode) throws InterruptedException {

		driver.findElement(By.xpath("//span[text()='Shopping Cart']")).click();

		driver.findElement(By.xpath("//h4[@class='panel-title'] //a[text()='Use Coupon Code ']")).click();
		driver.findElement(By.id("input-coupon")).sendKeys(coupon);
		driver.findElement(By.id("button-coupon")).click();

		String couponMsg = driver.findElement(By.cssSelector(".alert.alert-danger.alert-dismissible")).getText();

		System.out.println(couponMsg);

		Thread.sleep(3000);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)", "");

		driver.findElement(By.xpath("//h4[@class='panel-title'] //a[text()='Estimate Shipping & Taxes ']")).click();

		WebElement dropdown = driver.findElement(By.id("input-country"));
		Select select = new Select(dropdown);
		select.selectByVisibleText(country);

		dropdown = driver.findElement(By.id("input-zone"));
		select = new Select(dropdown);
		select.selectByVisibleText(state);
	
		driver.findElement(By.id("input-postcode")).sendKeys(pincode);

		driver.findElement(By.id("button-quote")).click();

		driver.findElement(By.cssSelector("input[value='flat.flat']")).click();
		driver.findElement(By.id("button-shipping")).click();

		Thread.sleep(3000);

		js.executeScript("window.scrollBy(0,1000)", "");

		driver.findElement(By.xpath("//a[text()='Checkout']")).click();

	}

	void addPaymentAddress(String firstName, String lastName, String company, String address, String city,
			String pincode, String country, String zone) throws InterruptedException {

		driver.findElement(By.xpath("//div[@id = 'collapse-payment-address'] //input[@value='new']")).click();

		driver.findElement(By.id("input-payment-firstname")).sendKeys(firstName);
		driver.findElement(By.id("input-payment-lastname")).sendKeys(lastName);
		driver.findElement(By.id("input-payment-company")).sendKeys(company);
		driver.findElement(By.id("input-payment-address-1")).sendKeys(address);
		driver.findElement(By.id("input-payment-city")).sendKeys(city);
		driver.findElement(By.id("input-payment-postcode")).sendKeys(pincode);

		WebElement dropdown = driver.findElement(By.id("input-payment-country"));
		Select select = new Select(dropdown);
		select.selectByVisibleText(country);

		dropdown = driver.findElement(By.id("input-payment-zone"));
		select = new Select(dropdown);
		select.selectByValue(zone);

		Thread.sleep(2000);

		driver.findElement(By.id("button-payment-address")).click();
	}

	void selectDeliveryAddress() {
		waitTillElementAppears(By.id("button-shipping-address"));
		driver.findElement(By.id("button-shipping-address")).click();
	}

	void deliveryAndPaymentMethod() {

//		driver.findElement(By.className("comment")).sendKeys("Please deliver the items as soon as possible.");

		waitTillElementAppears(
				driver.findElement(By.xpath("//div[@id='collapse-shipping-method'] //textarea[@name='comment']")));
		driver.findElement(By.xpath("//div[@id='collapse-shipping-method'] //textarea[@name='comment']"))
				.sendKeys("Please deliver the items as soon as possible.");
		driver.findElement(By.id("button-shipping-method")).click();

//		Thread.sleep(2000);
		waitTillElementAppears(By.xpath("//div[@id='collapse-payment-method'] //textarea[@name='comment']"));

		driver.findElement(By.xpath("//div[@id='collapse-payment-method'] //textarea[@name='comment']"))
				.sendKeys(Keys.CONTROL + "A");
		driver.findElement(By.xpath("//div[@id='collapse-payment-method'] //textarea[@name='comment']"))
				.sendKeys("Please expect COD for the items.");
		driver.findElement(By.id("button-payment-method")).click();
		String getWarning = driver.findElement(By.className("alert-danger")).getText();
		System.out.println(getWarning);

		driver.findElement(By.name("agree")).click();
		driver.findElement(By.id("button-payment-method")).click();

	}

	void confirmOrder() throws InterruptedException {

		waitTillElementAppears(driver.findElement(By.id("button-confirm")));
		driver.findElement(By.id("button-confirm")).click();

		Thread.sleep(2000);
//		waitTillElementAppears(driver.findElement(By.cssSelector("div[id='content'] h1")));

		String sucMsg = driver.findElement(By.cssSelector("div[id='content'] h1")).getText();
		System.out.println(sucMsg);
	}

	void moveToHomePage() {
		driver.findElement(By.linkText("Continue")).click();
	}

	void closeBrowser() {
		driver.quit();
	}

	static void waitTillElementAppears(WebElement element) {
		wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}

	static void waitTillElementAppears(By by) {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
	}



}
