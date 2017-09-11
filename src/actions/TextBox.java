package actions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import toolbox.ResultUtil;



public class TextBox {
	
		
	public static boolean enterValue(String strLogicalName,String strLocatorType,String strLocatorValue,String strValue,WebDriver driver){
		boolean blResult=false;
		WebElement element = Object.getElement(driver, strLocatorType, strLocatorValue);
		if(element!=null){
			if(element.isDisplayed()){
				if(element.isEnabled()){
					try{
						element.sendKeys(strValue);
						blResult=true;
						ResultUtil.report("PASS", "Entered value [[" + strValue + "]] in <<" + strLogicalName + ">> textbox","","", driver);
						
					}
					catch(Exception e){
						ResultUtil.report("FAIL", "Unable to enter value in <<" + strLogicalName + ">> textbox Exception occurred:" + e.getMessage(), "","",driver);
					}
					
				}
				else{
					ResultUtil.report("FAIL", "Unable to enter value-<<" + strLogicalName + ">> textbox is disabled","","", driver);
				}
			}
			else{
				ResultUtil.report("FAIL", "Unable to enter value-<<" + strLogicalName + ">> textbox does not exist", "", "", driver);
			}
		}
		else{
			ResultUtil.report("FAIL", "Unable to enter value-<<" + strLogicalName + ">> textbox does not exist", "","",driver);
		}
		return blResult;
	}
	
	public static boolean verifyValue(String strLogicalName,WebElement element,String strValue,WebDriver driver){
		boolean blResult=false;
		if(element!=null){
			if(element.isDisplayed()){
				if(element.isEnabled()){
					try{
						String textValue=element.getAttribute("value");
						if(textValue.equalsIgnoreCase(strValue)){
							blResult=true;
							ResultUtil.report("PASS", "Verified value [[" + strValue + "]] in <<" + strLogicalName + ">> textbox","","", driver);
						}
					}
					catch(Exception e){
						ResultUtil.report("FAIL", "Unable to verify textbox value in <<" + strLogicalName + ">> textbox Exception occurred:" + e.getMessage(),"","", driver);
					}
					
				}
				else{
					ResultUtil.report("FAIL", "Unable to verify value-<<" + strLogicalName + ">> textbox is disabled","","", driver);
				}
			}
			else{
				ResultUtil.report("FAIL", "Unable to verify value-<<" + strLogicalName + ">> textbox does not exist","","", driver);
			}
		}
		else{
			ResultUtil.report("FAIL", "Unable to verify value-<<" + strLogicalName + ">> textbox does not exist", "", "", driver);
		}
		return blResult;
	}
	
	public static boolean enterPasswordValue(String strLogicalName,String strLocatorType,String strLocatorValue,String strValue,WebDriver driver){
		boolean blResult=false;
		WebElement element = Object.getElement(driver, strLocatorType, strLocatorValue);
		if(element!=null){
			if(element.isDisplayed()){
				if(element.isEnabled()){
					try{
						element.sendKeys(strValue);
						blResult=true;
						ResultUtil.report("PASS", "Entered Password in <<" + strLogicalName + ">> textbox","","", driver);
						
					}
					catch(Exception e){
						ResultUtil.report("FAIL", "Unable to enter value in <<" + strLogicalName + ">> textbox Exception occurred:" + e.getMessage(),"","", driver);
					}
					
				}
				else{
					ResultUtil.report("FAIL", "Unable to enter value-<<" + strLogicalName + ">> textbox is disabled","","", driver);
				}
			}
			else{
				ResultUtil.report("FAIL", "Unable to enter value-<<" + strLogicalName + ">> textbox does not exist", "", "", driver);
			}
		}
		else{
			ResultUtil.report("FAIL", "Unable to enter value-<<" + strLogicalName + ">> textbox does not exist","","", driver);
		}
		return blResult;
	}
	
	public static boolean clearValue(String strLogicalName,String strLocatorType,String strLocatorValue ,WebDriver driver){
		boolean blResult=false;
		WebElement element = Object.getElement(driver, strLocatorType, strLocatorValue);
		if(element!=null){
			if(element.isDisplayed()){
				if(element.isEnabled()){
					try{
						element.clear();
						blResult=true;
						ResultUtil.report("PASS", "Cleared <<" + strLogicalName + ">> textbox value","","", driver);
						
					}
					catch(Exception e){
						ResultUtil.report("FAIL", "Unable to clear <<" + strLogicalName + ">> textbox value Exception occurred:" + e.getMessage(), "","",driver);
					}
					
				}
				else{
					ResultUtil.report("FAIL", "Unable to clear value-<<" + strLogicalName + ">> texbox is disabled","","", driver);
				}
			}
			else{
				ResultUtil.report("FAIL", "Unable to clear value-<<" + strLogicalName + ">> texbox does not exist", "","",driver);
			}
		}
		else{
			ResultUtil.report("FAIL", "Unable to clear value-<<" + strLogicalName + ">> texbox does not exist", "", "", driver);
		}
		return blResult;
	}
	
	public static boolean JSEnterValue(String strLogicalName,WebDriver driver,WebElement element,String strValue){
		boolean blResult=false;
		if(element!=null){
			if(element.isDisplayed()){
				if(element.isEnabled()){
					try{
						JavascriptExecutor javascriptExecutor=((JavascriptExecutor)driver);
						javascriptExecutor.executeScript("arguments[0].value=arguments[1]", element,strValue);
						element.sendKeys(strValue);
						blResult=true;
						ResultUtil.report("PASS", "Entered value [[" + strValue + "]] in <<" + strLogicalName + ">> textbox","","", driver);
						
					}
					catch(Exception e){
						ResultUtil.report("FAIL", "Unable to enter value in <<" + strLogicalName + ">> textbox Exception occurred:" + e.getMessage(),"","", driver);
					}
					
				}
				else{
					ResultUtil.report("FAIL", "Unable to enter value-<<" + strLogicalName + ">> textbox is disabled","","", driver);
				}
			}
			else{
				ResultUtil.report("FAIL", "Unable to enter value-<<" + strLogicalName + ">> textbox does not exist", "","",driver);
			}
		}
		else{
			ResultUtil.report("FAIL", "Unable to enter value-<<" + strLogicalName + ">> textbox does not exist", "", "", driver);
		}
		return blResult;
	}
	
}
