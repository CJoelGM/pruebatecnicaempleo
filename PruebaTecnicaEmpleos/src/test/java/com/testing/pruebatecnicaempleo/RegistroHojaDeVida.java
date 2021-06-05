package com.testing.pruebatecnicaempleo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistroHojaDeVida {

	private WebDriver driver;
	private String mainUrl = "https://www.choucairtesting.com/empleos-testing/";
	private WebDriverWait waitVar;
	private JavascriptExecutor jsExecutor;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver");
		driver = new ChromeDriver();
		waitVar = new WebDriverWait(this.driver, 180);
		driver.manage().window().maximize();
		driver.get(mainUrl);
		jsExecutor = (JavascriptExecutor) driver;
		waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Ir al portal de empleos")));
		System.out.println("set up");
		// Selecciona la opcion Ir al portal de empleos
		WebElement btnElement = driver.findElement(By.partialLinkText("Ir al portal de empleos"));
		jsExecutor.executeScript("arguments[0].scrollIntoViewIfNeeded();", btnElement);
		driver.findElement(By.partialLinkText("Ir al portal de empleos")).click();
		waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("CONTINUAR")));
		driver.findElement(By.partialLinkText("CONTINUAR")).click();
	}

	@Test
	public void testExitoso() {
		//CP-011
		String firstName = "Joel";
		String lastName = "Gutierrez";
		String email = "jagalskardig31@gmail.com";
		String phone="9878878878";
		waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.id("candidate_first_name")));
		WebElement inputElement = driver.findElement(By.id("candidate_first_name"));
		jsExecutor.executeScript("arguments[0].scrollIntoViewIfNeeded();", inputElement);
		inputElement.sendKeys(firstName);
		driver.findElement(By.id("candidate_last_name")).sendKeys(lastName);
		driver.findElement(By.id("candidate_email")).sendKeys(email);
		driver.findElement(By.id("candidate_phone")).sendKeys(phone);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.className("signup-country-container")));
		
		System.out.println(driver.getTitle());
		assertTrue(driver.getTitle().contains("Registro"));
		driver.quit();
	}
	
	@Test
	public void testErrorCorreoInvalido() {
		//CP-012
		String firstName = "Joel";
		String lastName = "Gutierrez";
		String email = "jjjj";
		String phone="9878878878";
		String expectedErrorMsg = "Incluye un signo \"@\" en la dirección de correo electrónico";
		waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.id("candidate_first_name")));
		WebElement inputElement = driver.findElement(By.id("candidate_first_name"));
		jsExecutor.executeScript("arguments[0].scrollIntoViewIfNeeded();", inputElement);
		inputElement.sendKeys(firstName);
		driver.findElement(By.id("candidate_last_name")).sendKeys(lastName);
		driver.findElement(By.id("candidate_email")).sendKeys(email);
		driver.findElement(By.id("candidate_phone")).sendKeys(phone);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		WebElement emailField = driver.findElement(By.id("candidate_email"));
		String message = (String)jsExecutor.executeScript("return arguments[0].validationMessage;", emailField);
		assertTrue(message.contains(expectedErrorMsg));
		driver.quit();
	}

	@Test
	public void testExitosoCorreoVacio() {
		//CP-013
		String firstName = "Joel";
		String lastName = "Gutierrez";
		String email = "";
		String phone="9878878878";
		waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.id("candidate_first_name")));
		WebElement inputElement = driver.findElement(By.id("candidate_first_name"));
		jsExecutor.executeScript("arguments[0].scrollIntoViewIfNeeded();", inputElement);
		inputElement.sendKeys(firstName);
		driver.findElement(By.id("candidate_last_name")).sendKeys(lastName);
		driver.findElement(By.id("candidate_email")).sendKeys(email);
		driver.findElement(By.id("candidate_phone")).sendKeys(phone);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.className("signup-country-container")));
		
		System.out.println(driver.getTitle());
		assertTrue(driver.getTitle().contains("Registro"));
		driver.quit();
	}
}
