package com.testing.pruebatecnicaempleo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistroUsuarioManual {

	private WebDriver driver;
	private String mainUrl = "https://www.choucairtesting.com/empleos-testing/";
	private WebDriverWait waitVar;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver");
		driver = new ChromeDriver();
		waitVar = new WebDriverWait(this.driver, 180);
		driver.manage().window().maximize();
		driver.get(mainUrl);
		waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Ir al portal de empleos")));
		System.out.println("set up");
		// Selecciona la opcion Ir al portal de empleos
		WebElement btnElement = driver.findElement(By.partialLinkText("Ir al portal de empleos"));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoViewIfNeeded();", btnElement);
		driver.findElement(By.partialLinkText("Ir al portal de empleos")).click();
		waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("CONTINUAR")));
		driver.findElement(By.partialLinkText("CONTINUAR")).click();
	}

	@Test
	public void testExitoso() {
		// CP-006
		String email = "jagalskardig31@gmail.com";
		String firstName = "Joel";
		String lastName = "Gutierrez";
		String countryName = "Perú";
		String birthDate = "18/02/1993";

		waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Registrarse")));
		driver.findElement(By.partialLinkText("Registrarse")).click();

		waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.className("registration-type__card")));
		driver.findElements(By.className("registration-type__card")).get(1).click();

		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.className("registration-type__next-step")));
		driver.findElement(By.className("registration-type__next-step")).click();

		waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']")));
		driver.findElements(By.xpath("//input[@type='email']")).get(0).sendKeys(email);
		driver.findElements(By.xpath("//input[@type='email']")).get(1).sendKeys(email);
		WebElement btnElement = driver.findElement(By.xpath("//button[@type='submit']"));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoViewIfNeeded();", btnElement);
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.id("personal-info_firstName")));
		System.out.println("encontro campo nombre");
		driver.findElements(By.xpath("//input[@type='text']")).get(0).sendKeys(firstName);
		driver.findElement(By.id("personal-info_lastName")).sendKeys(lastName);
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.id("birth-info_countryBirth")));
		driver.findElement(By.id("birth-info_countryBirth")).sendKeys(countryName, Keys.RETURN);
		driver.findElement(By.id("birth-info_birthdate")).sendKeys(birthDate, Keys.RETURN);
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='male']")));
		System.out.println("holi");
		driver.findElement(By.xpath("//input[@type='male']")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		assertEquals("s", "s");
	}

	@Test
	public void testErrorCorreoInvalido() {
		// CP-007
		String email = "absbhajd";
		String errorExpectedMsg = "El valor ingresado no es un correo electrónico válido";
		waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Registrarse")));
		driver.findElement(By.partialLinkText("Registrarse")).click();

		waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.className("registration-type__card")));
		driver.findElements(By.className("registration-type__card")).get(1).click();

		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.className("registration-type__next-step")));
		driver.findElement(By.className("registration-type__next-step")).click();

		waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']")));
		driver.findElements(By.xpath("//input[@type='email']")).get(0).sendKeys(email);
		waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert']")));

		System.out.println("-->" + driver.findElement(By.xpath("//div[@role='alert']")).getText());
		assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), errorExpectedMsg);
		driver.quit();
	}

}
