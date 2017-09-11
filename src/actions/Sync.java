package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import toolbox.ResultUtil;


public class Sync {
public static void waitForSeconds(int inSecs,WebDriver driver){
	try{
		Thread.sleep(inSecs*1000);
		//ResultUtil.reportWithoutScreenshot("PASS", "Waited for "+inSecs+" seconds");
	}
	catch(Exception e){
		
	}
}

/*public static WebElement waitForObject(WebDriver driver,String strLogicalName,String strLocatorType,String strLocator){
	WebElement element=null;
	int inWaitNumber=Integer.parseInt(Prerequsite.configMap.get("WAIT_NUMBER").toString());
	int inObjectWaitTime=Integer.parseInt(Prerequsite.configMap.get("OBJECT_WAIT_TIME").toString())*inWaitNumber;
	
	try{
		WebDriverWait webDriverWait=new WebDriverWait(driver,inObjectWaitTime);
		
		if(strLocatorType.equalsIgnoreCase("XPATH")){
			element=webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(strLocator)));
		}
		else if(strLocatorType.equalsIgnoreCase("ID")){
			element=webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id(strLocator)));
		}
		else if(strLocatorType.equalsIgnoreCase("NAME")){
			element=webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name(strLocator)));
		}
		else if(strLocatorType.equalsIgnoreCase("CSS")){
			element=webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(strLocator)));
		}
		else if(strLocatorType.equalsIgnoreCase("CLASSNAME")){
			element=webDriverWait.until(ExpectedConditions.elementToBeClickable(By.className(strLocator)));
		}
		ResultUtil.report("PASS", "Waited for <<"+strLogicalName+">> object", driver);
	}
	catch(Exception e){
		ResultUtil.report("FAIL", "Unable to wait for <<"+strLogicalName+">> object WaitTime:"+inObjectWaitTime, driver);
	}
	
	return element;
}*/

public static WebElement waitForObject(WebDriver driver,String strLogicalName,String strLocatorType,String strLocator,int inTime){
	WebElement element=null;
	int inObjectWaitTime=inTime;
	
	try{
		WebDriverWait webDriverWait=new WebDriverWait(driver,inObjectWaitTime);
		
		if(strLocatorType.equalsIgnoreCase("XPATH")){
			element=webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(strLocator)));
		}
		else if(strLocatorType.equalsIgnoreCase("ID")){
			element=webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id(strLocator)));
		}
		else if(strLocatorType.equalsIgnoreCase("NAME")){
			element=webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name(strLocator)));
		}
		else if(strLocatorType.equalsIgnoreCase("CSS")){
			element=webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(strLocator)));
		}
		else if(strLocatorType.equalsIgnoreCase("CLASSNAME")){
			element=webDriverWait.until(ExpectedConditions.elementToBeClickable(By.className(strLocator)));
		}
		//ResultUtil.report("PASS", "Waited for <<"+strLogicalName+">> object", driver);
	}
	catch(Exception e){
		ResultUtil.report("FAIL", "Unable to wait for <<" + strLogicalName + ">> object WaitTime:" + inObjectWaitTime, "", "", driver);
	}
	
	return element;
}

public static boolean waitForFrame(WebDriver driver,String strLogicalName,int inFrameIndex,int inTime){
	boolean blResult=false;
	int inElapsedTime=0;

	while(inElapsedTime<=inTime && !(blResult)){
		
		try{driver.switchTo().frame(inFrameIndex);blResult=true;break;}catch(Exception e){}
		
		inElapsedTime++;
	}
	
	return blResult;
}

public static boolean waitForFrame(WebDriver driver,String strLogicalName,String strFrameIDorName,int inTime){
	boolean blResult=false;
	int inElapsedTime=0;

	while(inElapsedTime<=inTime && !(blResult)){
		
		try{driver.switchTo().frame(strFrameIDorName);blResult=true;break;}catch(Exception e){}
		
		inElapsedTime++;
	}
	
	return blResult;
}

}
