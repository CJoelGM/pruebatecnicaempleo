package com.testing.pruebatecnicaempleo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

public class InicioSesion {
	
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
		//CP-017
		String email = "jagalskardig31@gmail.com";
		String password = "pruebaCorreo";
		waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Ingresar")));
		driver.findElement(By.partialLinkText("Ingresar")).click();
		waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.id("emailSignIn")));
		driver.findElement(By.id("emailSignIn")).sendKeys(email);
		waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.id("emailSignInConfirm")));
		driver.findElement(By.id("emailSignInConfirm")).sendKeys(password);
		driver.findElement(By.id("btnSubmitLogin")).click();
		waitVar.until(ExpectedConditions.titleContains("Magneto"));
		
		assertTrue(driver.getTitle().contains("Perfil"));
		driver.quit();

	}
	

	@Test
	public void testCorreoInvalido() {
		//CP-018
		try {
			String email = "kkkkl";
			String password = "pruebaCorreo";
			String expectedErrorMsg = "No se encontró cuenta asociada al correo.";
			waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Ingresar")));
			driver.findElement(By.partialLinkText("Ingresar")).click();
			waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.id("emailSignIn")));
			driver.findElement(By.id("emailSignIn")).sendKeys(email,Keys.TAB);

			waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.id("messageValidateexistEmail")));
			
			Thread.sleep(1000);
			System.out.println( driver.findElement(By.id("messageValidateexistEmail")).getText());
			assertEquals(expectedErrorMsg, driver.findElement(By.id("messageValidateexistEmail")).getText());
			driver.quit();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
}
