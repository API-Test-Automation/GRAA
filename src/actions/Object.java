package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.StringUtils;
import common.ExtendedLibrary;

public class Object extends ExtendedLibrary {
	public static WebElement getElement(WebDriver driver,String strLocatorType,String strLocatorValue){
			WebElement element=null;
			try{
				if(strLocatorType.equalsIgnoreCase("ID")){
					element=driver.findElement(By.id(strLocatorValue));
				}
				else if(strLocatorType.equalsIgnoreCase("NAME")){
					element=driver.findElement(By.name(strLocatorValue));
				}
				else if(strLocatorType.equalsIgnoreCase("XPATH")){
					element=driver.findElement(By.xpath(strLocatorValue));
				}
				else if(strLocatorType.equalsIgnoreCase("CSS")){
					element=driver.findElement(By.cssSelector(strLocatorValue));
				}
				else if(strLocatorType.equalsIgnoreCase("CLASS")){
					element=driver.findElement(By.className(strLocatorValue));
				}
			}
			catch(Exception e){}
			
			return element;
	}
	
	/*public static WebElement getElement(WebDriver driver,String strLocatorType,String strLocatorValue){
		WebElement element=null;
		int inObjectWaitTime = 0;
		 
		try{
		inObjectWaitTime=Integer.parseInt(StringUtils.getKeyValue(CONFIG_FILE_PATH, "WAIT_TIME"));
		 
		WebDriverWait webDriverWait=new WebDriverWait(driver,inObjectWaitTime);
		 
		switch(strLocatorType){
		  case "ID":
		  element=webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id(strLocatorValue)));
		  element=driver.findElement(By.id(strLocatorValue));
		  
		  case "NAME":
		  element=webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(strLocatorValue)));
		  element=driver.findElement(By.name(strLocatorValue));
		  
		  case "XPATH":
		  element=webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(strLocatorValue)));
		  element=driver.findElement(By.xpath(strLocatorValue));
		  
		  case "CSS":
		  element=webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(strLocatorValue)));
		  element=driver.findElement(By.cssSelector(strLocatorValue));
		  
		  case "CLASS":
		  element=webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(strLocatorValue)));
		  element=driver.findElement(By.className(strLocatorValue));
		}
		}
		catch(Exception e){
		ResultUtil.report("FAIL", "Unable to find element as waited for:"+inObjectWaitTime, driver);
		}
		 
		return element;
		}*/
}
