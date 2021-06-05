package com.testing.pruebatecnicaempleo;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AplicarOferta {

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
	public void testExitosoSinLogueo() {
		//CP-022
		waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Conoce nuestras vacantes")));
		jsExecutor.executeScript("arguments[0].scrollIntoViewIfNeeded();",
				driver.findElement(By.partialLinkText("Conoce nuestras vacantes")));
		driver.findElement(By.partialLinkText("Conoce nuestras vacantes")).click();
		ArrayList<String> newTb = new ArrayList<String>(driver.getWindowHandles());
		// switch to new tab
		driver.switchTo().window(newTb.get(1));
		waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.id("apply-vacant")));
		jsExecutor.executeScript("arguments[0].scrollIntoViewIfNeeded();", driver.findElement(By.id("apply-vacant")));
		driver.findElement(By.id("apply-vacant")).click();
		waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.className("signup-country-container")));
		assertTrue(driver.getTitle().contains("Registro"));
		driver.quit();
	}

}
