package actions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import toolbox.ResultUtil;



public class Element {
	public static boolean verify(String strLogicalName,String strLocatorType,String strLocatorValue,String strValue,WebDriver driver) throws Exception{
		boolean blResult=false;
		WebElement element = Object.getElement(driver, strLocatorType, strLocatorValue);
		if(element!=null){
			if(element.isDisplayed()){
				if(element.isEnabled()){
					try{
						String textValue=element.getText();
						if(textValue.equalsIgnoreCase(strValue)){
							blResult=true;
							ResultUtil.report("PASS", " <<" + strLogicalName + ">> is Present", "", "", driver);
						}
					}
					catch(Exception e){
						ResultUtil.report("FAIL", "Unable to verify <<" + strLogicalName + ">> element Exception occurred:" + e.getMessage(),"","", driver);
					}
				}
				else{
					ResultUtil.report("FAIL", "Unable to verify-<<" + strLogicalName + ">> element is disabled", "", "", driver);
				}
			}
			else{
				ResultUtil.report("FAIL", "Unable to verify-<<" + strLogicalName + ">> element does not exist","","", driver);
			}
		}
		else{
			ResultUtil.report("FAIL", "Unable to verify-<<" + strLogicalName + ">> element does not exist", "", "", driver);
		}
		return blResult;
	}
	
	public static boolean mouseOver(String strLogicalName,String strLocatorType,String strLocatorValue,WebDriver driver) throws Exception{
		boolean blResult=false;
		WebElement element = Object.getElement(driver, strLocatorType, strLocatorValue);
		if(element!=null){
			if(element.isDisplayed()){
				if(element.isEnabled()){
					try{
						Mouse mouse=((HasInputDevices)driver).getMouse();
						mouse.mouseMove(((Locatable)element).getCoordinates());
						blResult=true;
						ResultUtil.report("PASS", "Mouse-overed on <<" + strLogicalName + ">> element", "", "", driver);
					}
					catch(Exception e){
						ResultUtil.report("FAIL", "Unable to mouseover <<" + strLogicalName + ">> element Exception occurred:" + e.getMessage(),"","", driver);
					}
				}
				else{
					ResultUtil.report("FAIL", "Unable to mouseover-<<" + strLogicalName + ">> element is disabled","","", driver);
				}
			}
			else{
				ResultUtil.report("FAIL", "Unable to mouseover-<<" + strLogicalName + ">> element does not exist","","", driver);
			}
		}
		else{
			ResultUtil.report("FAIL", "Unable to mouseover-<<" + strLogicalName + ">> element does not exist", "","",driver);
		}
		return blResult;
	}
	
	public static boolean mouseOver(String strLogicalName,String strLocatorType, String strLocatorValue,int inWait,WebDriver driver) throws Exception{
		boolean blResult=false;
		WebElement element = Object.getElement(driver, strLocatorType, strLocatorValue);
		if(element!=null){
			if(element.isDisplayed()){
				if(element.isEnabled()){
					try{
						Mouse mouse=((HasInputDevices)driver).getMouse();
						mouse.mouseMove(((Locatable)element).getCoordinates());
						try{Thread.sleep(inWait*1000);}catch(Exception e){}
						blResult=true;
						ResultUtil.report("PASS", "Mouse-overed on <<" + strLogicalName + ">> element","","", driver);
					}
					catch(Exception e){
						ResultUtil.report("FAIL", "Unable to mouseover <<" + strLogicalName + ">> element Exception occurred:" + e.getMessage(),"","", driver);
					}
				}
				else{
					ResultUtil.report("FAIL", "Unable to mouseover-<<" + strLogicalName + ">> element is disabled","","", driver);
				}
			}
			else{
				ResultUtil.report("FAIL", "Unable to mouseover-<<" + strLogicalName + ">> element does not exist","","", driver);
			}
		}
		else{
			ResultUtil.report("FAIL", "Unable to mouseover-<<" + strLogicalName + ">> element does not exist", "", "", driver);
		}
		return blResult;
	}
	
	public static boolean verify(String strLogicalName,String strLocatorType, String strLocatorValue,WebDriver driver) throws Exception{
		boolean blResult=false;
		WebElement element = Object.getElement(driver, strLocatorType, strLocatorValue);
		if(element!=null){
			if(element.isDisplayed()){
					try{
						blResult=true;
						ResultUtil.report("PASS", "Verified <<" + strLogicalName + ">> element", "","",driver);
						
					}
					catch(Exception e){
						ResultUtil.report("FAIL", "Unable to verify <<" + strLogicalName + ">> element Exception occurred:" + e.getMessage(),"","", driver);
					}
			}
			else{
				ResultUtil.report("FAIL", "Unable to verify-<<" + strLogicalName + ">> element does not exist","","", driver);
			}
		}
		else{
			ResultUtil.report("FAIL", "Unable to verify-<<" + strLogicalName + ">> element does not exist", "", "", driver);
		}
		return blResult;
	}
	
	public static boolean verify(String strLogicalName,WebDriver driver,String strLocatorType,String strLocatorValue) throws Exception{
		boolean blResult=false;
		
		WebElement element=Object.getElement(driver, strLocatorType, strLocatorValue);
		
		if(!(element==null)){
			if(element.isDisplayed()){
				try{
					blResult=true;
					highlightElement(driver, strLocatorType, strLocatorValue);
					ResultUtil.report("PASS", "Verified <<" + strLogicalName + ">> element","","", driver);
					
				}
				catch(Exception e){
					ResultUtil.report("FAIL", "Unable to verify <<" + strLogicalName + ">> element Exception occurred:" + e.getMessage(),"","", driver);
				}
			}
			else{
				ResultUtil.report("FAIL", "Unable to verify-<<" + strLogicalName + ">> element does not exist", "", "", driver);
			}
		}
		else{
			ResultUtil.report("FAIL", "Unable to verify-<<" + strLogicalName + ">> element does not exist","","", driver);
		}
			
		return blResult;
	}
	
	public static boolean notVerify(String strLogicalName,WebDriver driver,String strLocatorType,String strLocatorValue) throws Exception{
		boolean blResult=false;
		
		WebElement element=Object.getElement(driver, strLocatorType, strLocatorValue);
		
		if(element==null){
			ResultUtil.report("PASS", "<<" + strLogicalName + ">> element is not displayed as expected", "", "", driver);
			blResult=true;
		}
		else{
			ResultUtil.report("FAIL", "<<" + strLogicalName + ">> element is displayed","","", driver);
		}
			
		return blResult;
	}
	
	
	public static boolean highlightElement(WebDriver driver, String strLocatorType, String strLocatorValue) throws Exception {
		boolean blResult=true;
		WebElement element=Object.getElement(driver, strLocatorType, strLocatorValue);
		
	    for (int i = 0; i <=5; i++) {   	

	        //String originalStyle = element.getAttribute("style");
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        /*js.executeScript("arguments[0].setAttribute('style', 'color: black; background: solid grey;');", element);
		
	        js.executeScript("arguments[0].setAttribute('style', '" + originalStyle + "');", element);*/
	        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "background: grey; border: 2px solid grey;"); 
	        
	        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, ""); 
	        
	        
	    }
	    return blResult;
	}
	
	public static boolean dragDropElement(String strFromLogicalName, String strToLogicalName, WebDriver driver, String strFromLocatorType,String strFromLocatorValue, String strToLocatorType, String strToLocatorValue){
		boolean blResult = false;
		try{
			WebElement fromElement=Object.getElement(driver, strFromLocatorType, strFromLocatorValue);
			WebElement toElement=Object.getElement(driver, strToLocatorType, strToLocatorValue);
			
			Actions action = new Actions(driver);
			Action dragDrop = action.clickAndHold(fromElement)
					.moveToElement(toElement).release(toElement).build();
			dragDrop.perform();
			
			ResultUtil.report("PASS", "<<" + strFromLocatorType + ">> Element dragged and d", "", "", driver);
			
		}catch(Exception e){
			ResultUtil.report("FAIL", "Unable to Drag and Drop the element <<" + strFromLocatorType + ">>","","", driver);
		}
		return blResult;
		
	}
}
