package actions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import toolbox.ResultUtil;


public class Link {
	public static boolean click(String strLogicalName,String strLocatorType, String strLocatorValue,WebDriver driver) throws Exception{
		boolean blResult=false;
		WebElement element = Object.getElement(driver, strLocatorType, strLocatorValue);
		if(element!=null){
			if(element.isDisplayed()){
				if(element.isEnabled()){
					try{
						element.click();
						blResult=true;
						ResultUtil.report("PASS", "Clicked <<" + strLogicalName + ">> link", "", "", driver);
					}
					catch(Exception e){
						ResultUtil.report("FAIL", "Unable to click <<" + strLogicalName + ">> link Exception occurred:" + e.getMessage(),"","", driver);
					}	
				}
				else{
					ResultUtil.report("FAIL", "Unable to click-<<" + strLogicalName + ">> link is disabled","","", driver);
				}
			}
			else{
				ResultUtil.report("FAIL", "Unable to click-<<" + strLogicalName + ">> link does not exist","","", driver);
			}
		}
		else{
			ResultUtil.report("FAIL", "Unable to click-<<" + strLogicalName + ">> link does not exist", "", "", driver);
		}
		return blResult;
	}
	
	public static boolean JSClick(String strLogicalName,String strLocatorType,String strLocatorValue,WebDriver driver) throws Exception{
		boolean blResult=false;
		WebElement element = Object.getElement(driver, strLocatorType, strLocatorValue);
		if(element!=null){
			if(element.isDisplayed()){
				if(element.isEnabled()){
					try{
						((JavascriptExecutor)driver).executeScript("arguments[0].click", element);
						blResult=true;
						ResultUtil.report("PASS", "Clicked <<" + strLogicalName + ">> link","","", driver);
					}
					catch(Exception e){
						ResultUtil.report("FAIL", "Unable to click <<" + strLogicalName + ">> link Exception occurred:" + e.getMessage(),"","", driver);
					}	
				}
				else{
					ResultUtil.report("FAIL", "Unable to click-<<" + strLogicalName + ">> link is disabled", "","",driver);
				}
			}
			else{
				ResultUtil.report("FAIL", "Unable to click-<<" + strLogicalName + ">> link does not exist","","", driver);
			}
		}
		else{
			ResultUtil.report("FAIL", "Unable to click-<<" + strLogicalName + ">> link does not exist", "", "", driver);
		}
		return blResult;
	}
	
	public static boolean mouseClick(String strLogicalName,String strLocatorType,String strLocatorValue,WebDriver driver) throws Exception{
		boolean blResult=false;
		WebElement element = Object.getElement(driver, strLocatorType, strLocatorValue);
		if(element!=null){
			if(element.isDisplayed()){
				if(element.isEnabled()){
					try{
						Mouse mouse=((HasInputDevices)driver).getMouse();
						mouse.mouseDown(((Locatable)element).getCoordinates());
						try{Thread.sleep(500);}catch(Exception e){}
						mouse.mouseUp(((Locatable)element).getCoordinates());
						blResult=true;
						ResultUtil.report("PASS", "Clicked <<" + strLogicalName + ">> link","","", driver);
					}
					catch(Exception e){
						ResultUtil.report("FAIL", "Unable to click <<" + strLogicalName + ">> link Exception occurred:" + e.getMessage(),"","", driver);
					}	
				}
				else{
					ResultUtil.report("FAIL", "Unable to click-<<" + strLogicalName + ">> link is disabled", "", "", driver);
				}
			}
			else{
				ResultUtil.report("FAIL", "Unable to click-<<" + strLogicalName + ">> link does not exist","","", driver);
			}
		}
		else{
			ResultUtil.report("FAIL", "Unable to click-<<" + strLogicalName + ">> link does not exist", "", "", driver);
		}
		return blResult;
	}
	
	public static boolean mouseOver(String strLogicalName,String strLocatorType, String strLocatorValue,WebDriver driver) throws Exception{
		boolean blResult=false;
		WebElement element = Object.getElement(driver, strLocatorType, strLocatorValue);
		if(element!=null){
			if(element.isDisplayed()){
				if(element.isEnabled()){
					try{
						Mouse mouse=((HasInputDevices)driver).getMouse();
						mouse.mouseMove(((Locatable)element).getCoordinates());
						blResult=true;
						ResultUtil.report("PASS", "Mouse-overed on <<" + strLogicalName + ">> link", "", "", driver);
					}
					catch(Exception e){
						ResultUtil.report("FAIL", "Unable to mouseover <<" + strLogicalName + ">> link Exception occurred:" + e.getMessage(), "","",driver);
					}
				}
				else{
					ResultUtil.report("FAIL", "Unable to mouseover-<<" + strLogicalName + ">> link is disabled","","", driver);
				}
			}
			else{
				ResultUtil.report("FAIL", "Unable to mouseover-<<" + strLogicalName + ">> link does not exist","","", driver);
			}
		}
		else{
			ResultUtil.report("FAIL", "Unable to mouseover-<<" + strLogicalName + ">> link does not exist", "", "", driver);
		}
		return blResult;
	}
	
	public static boolean mouseOver(String strLogicalName,WebElement element,int inWait,WebDriver driver) throws Exception{
		boolean blResult=false;
		if(element!=null){
			if(element.isDisplayed()){
				if(element.isEnabled()){
					try{
						Mouse mouse=((HasInputDevices)driver).getMouse();
						mouse.mouseMove(((Locatable)element).getCoordinates());
						try{Thread.sleep(inWait*1000);}catch(Exception e){}
						blResult=true;
						ResultUtil.report("PASS", "Mouse-overed on <<" + strLogicalName + ">> link","","", driver);
					}
					catch(Exception e){
						ResultUtil.report("FAIL", "Unable to mouseover <<" + strLogicalName + ">> link Exception occurred:" + e.getMessage(),"","", driver);
					}
				}
				else{
					ResultUtil.report("FAIL", "Unable to mouseover-<<" + strLogicalName + ">> link is disabled", "", "", driver);
				}
			}
			else{
				ResultUtil.report("FAIL", "Unable to mouseover-<<" + strLogicalName + ">> link does not exist","","", driver);
			}
		}
		else{
			ResultUtil.report("FAIL", "Unable to mouseover-<<" + strLogicalName + ">> link does not exist", "", "",  driver);
		}
		return blResult;
	}
	
	public static boolean verify(String strLogicalName, WebElement element,String strValue,WebDriver driver) throws Exception{
		boolean blResult=false;
		if(element!=null){
			if(element.isDisplayed()){
				if(element.isEnabled()){
					try{
						String textValue=element.getText();
						if(textValue.equalsIgnoreCase(strValue)){
							blResult=true;
							ResultUtil.report("PASS", "Verified <<" + strLogicalName + ">> link","","", driver);
						}
					}
					catch(Exception e){
						ResultUtil.report("FAIL", "Unable to verify <<" + strLogicalName + ">> link Exception occurred:" + e.getMessage(),"","", driver);
					}
				}
				else{
					ResultUtil.report("FAIL", "Unable to verify-<<" + strLogicalName + ">> link is disabled","","", driver);
				}
			}
			else{
				ResultUtil.report("FAIL", "Unable to verify-<<" + strLogicalName + ">> link does not exist", "", "", driver);
			}
		}
		else{
			ResultUtil.report("FAIL", "Unable to verify-<<" + strLogicalName + ">> link does not exist", "", "", driver);
		}
		return blResult;
	}
	
public static boolean verify(String strLogicalName, WebElement element,WebDriver driver) throws Exception{
		
		boolean blResult=false;
		if(element!=null){
			if(element.isDisplayed()){
				try{
					blResult=true;
					ResultUtil.report("PASS", "Verified <<" + strLogicalName + ">> link","","", driver);
				}
				catch(Exception e){
					ResultUtil.report("FAIL", "Unable to verify <<" + strLogicalName + ">> link Exception occurred:" + e.getMessage(),"","", driver);
				}
			}
			else{
				ResultUtil.report("FAIL", "Unable to verify-<<" + strLogicalName + ">> link does not exist","","", driver);
			}
		}
		else{
			ResultUtil.report("FAIL", "Unable to verify-<<" + strLogicalName + ">> link does not exist", "", "", driver);
		}
		return blResult;
	}

public static boolean mouseDown(String strLogicalName,WebElement element,int inWait,WebDriver driver) throws Exception{
	boolean blResult=false;
	if(element!=null){
		if(element.isDisplayed()){
			if(element.isEnabled()){
				try{
					Actions actions=new Actions(driver);
					actions.click(element).build().perform();
					try{Thread.sleep(inWait*1000);}catch(Exception e){}
					blResult=true;
					ResultUtil.report("PASS", "Mouse-overed on <<" + strLogicalName + ">> link","","", driver);
				}
				catch(Exception e){
					ResultUtil.report("FAIL", "Unable to mouseover <<" + strLogicalName + ">> link Exception occurred:" + e.getMessage(),"","", driver);
				}
			}
			else{
				ResultUtil.report("FAIL", "Unable to mouseover-<<" + strLogicalName + ">> link is disabled","","", driver);
			}
		}
		else{
			ResultUtil.report("FAIL", "Unable to mouseover-<<" + strLogicalName + ">> link does not exist", "", "", driver);
		}
	}
	else{
		ResultUtil.report("FAIL", "Unable to mouseover-<<" + strLogicalName + ">> link does not exist", "", "", driver);
	}
	return blResult;
}

	

}
