package actions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;

import static org.apache.commons.io.FileUtils.waitFor;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import toolbox.ResultUtil;



public class Button {   
	
	public static boolean click(String strLogicalName,String strLocatorType,String strLocatorValue,WebDriver driver) {
		boolean blResult=false;
		WebElement element = Object.getElement(driver, strLocatorType, strLocatorValue);
		if(element!=null){
			if(element.isDisplayed()){
				if(element.isEnabled()){
					try{
						element.click();
						blResult=true;
						ResultUtil.report("PASS", "Clicked button <<" + strLogicalName + ">>","", "", driver);
					}
					catch(Exception e){
						ResultUtil.report("FAIL", "Unable to click <<" + strLogicalName + ">> button Exception occurred:" + e.getMessage(),"", "", driver);
					}
					
				}
				else{
					ResultUtil.report("FAIL", "Unable to click-<<" + strLogicalName + ">> button is disabled", "", "", driver);
				}
			}
			else{
				ResultUtil.report("FAIL", "Unable to click-<<" + strLogicalName + ">> button does not exist","", "", driver);
			}
			
		}
		else{
			ResultUtil.report("FAIL", "Unable to click-<<" + strLogicalName + ">> button does not exist", "", "", driver);
		}
		return blResult;
	}
   
   public static boolean mouseClick(String strLogicalName,String strLocatorType,String strLocatorValue,WebDriver driver) {
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
						ResultUtil.report("PASS", "Clicked button <<" + strLogicalName + ">>","","", driver);
					}
					catch(Exception e){
						ResultUtil.report("FAIL", "Unable to click <<" + strLogicalName + ">> button Exception occurred:" + e.getMessage(),"","", driver);
					}
					
				}
				else{
					ResultUtil.report("FAIL", "Unable to click-<<" + strLogicalName + ">> button is disabled","","", driver);
				}
			}
			else{
				ResultUtil.report("FAIL", "Unable to click-<<" + strLogicalName + ">> button does not exist","","", driver);
			}
		}
		else{
			ResultUtil.report("FAIL", "Unable to click-<<" + strLogicalName + ">> button does not exist", "", "", driver);
		}
		
		return blResult;
	}
	
   public static boolean JSClick(String strLogicalName,WebElement element,WebDriver driver) throws Exception{
		boolean blResult=false;
		
		if(element!=null){
			if(element.isDisplayed()){
				if(element.isEnabled()){
					try{
					    ((JavascriptExecutor)driver). executeScript("arguments[0].click",element);
						blResult=true;
						ResultUtil.report("PASS", "Clicked button <<" + strLogicalName + ">>","","", driver);
					}
					catch(Exception e){
						ResultUtil.report("FAIL", "Unable to click <<" + strLogicalName + ">> button Exception occurred:" + e.getMessage(),"","", driver);
					}
					
				}
				else{
					ResultUtil.report("FAIL", "Unable to click-<<" + strLogicalName + ">> button is disabled","","", driver);
				}
			}
			else{
				ResultUtil.report("FAIL", "Unable to click-<<" + strLogicalName + ">> button does not exist","","", driver);
			}
		}
		else{
			ResultUtil.report("FAIL", "Unable to click-<<" + strLogicalName + ">> button does not exist","","", driver);
		}
		
		return blResult;
	}
   
	public static boolean verify(String strLogicalName,WebElement element,String strValue,WebDriver driver) throws Exception{
		boolean blResult=false;
		
		if(element!=null){
			if(element.isDisplayed()){
				try{
					String btnName=element.getText();
					if(btnName.equalsIgnoreCase(strValue)){
						blResult=true;	
						ResultUtil.report("PASS", "Verified <<" + strLogicalName + ">> button", "", "", driver);
					}
				}
				catch(Exception e){
					ResultUtil.report("FAIL", "Unable to verify <<" + strLogicalName + ">> button Exception occurred:" + e.getMessage(),"","", driver);
				}
			}
			else{
				ResultUtil.report("FAIL", "Unable to verify-<<" + strLogicalName + ">> button does not exist","","", driver);
			}
		}
		else{
			ResultUtil.report("FAIL", "Unable to verify-<<" + strLogicalName + ">> button does not exist","","", driver);
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
					ResultUtil.report("PASS", "Verified <<" + strLogicalName + ">> button","","", driver);
				}
				catch(Exception e){
					ResultUtil.report("FAIL", "Unable to verify-<<" + strLogicalName + ">> button Exception occurred:" + e.getMessage(),"","", driver);
				}
			}
			else{
				ResultUtil.report("FAIL", "Unable to verify-<<" + strLogicalName + ">> button does not exist","","", driver);
			}
		}
		else{
			ResultUtil.report("FAIL", "Unable to verify-<<" + strLogicalName + ">> button does not exist", "", "", driver);
		}
		return blResult;
	}
	
	
	public static boolean isEnabled(WebElement element, String strLogicalName,WebDriver driver) throws Exception{
		boolean blResult=false;
		if(element!=null){
			if(element.isDisplayed()){
				if(element.isEnabled()){
					try{
						blResult=true;
						ResultUtil.report("PASS", "<<" + strLogicalName + ">> button is enabled","", "", driver);
					}
					catch(Exception e){
						ResultUtil.report("FAIL", "Unable verify <<" + strLogicalName + ">> button enable status Exception occurred:" + e.getMessage(),"", "", driver);
					}
				}
				else{
					ResultUtil.report("FAIL", "<<" + strLogicalName + ">> button is disbled","", "", driver);
				}
			}
			else{
				ResultUtil.report("FAIL", "<<" + strLogicalName + ">> button does not exist","", "", driver);
			}
		}
		else{
			ResultUtil.report("FAIL", "<<" + strLogicalName + ">> button does not exist", "", "", driver);
		}
		return blResult;
	 }
	public static boolean isDisabled(WebElement element, String strLogicalName,WebDriver driver) throws Exception{
		boolean blResult=false;
		if(element!=null){
			if(element.isDisplayed()){
				if(!element.isEnabled()){
					try{
						blResult=true;
						ResultUtil.report("PASS", "<<" + strLogicalName + ">> button is disabled","", "", driver);
					}
					catch(Exception e){
						ResultUtil.report("FAIL", "Unable verify <<" + strLogicalName + ">> button disable status Exception occurred:" + e.getMessage(),"", "", driver);
					}
				}
				else{
					ResultUtil.report("FAIL", "<<" + strLogicalName + ">> button is enabled","", "", driver);
				}
			}
			else{
				ResultUtil.report("FAIL", "<<" + strLogicalName + ">> button does not exist", "", "", driver);
			}
		}
		else{
			ResultUtil.report("FAIL", "<<" + strLogicalName + ">> button does not exist", "", "", driver);
		}
		return blResult;
	 }

    public static boolean visibleClick(String strLogicalName,String strLocatorType,String strLocatorValue,WebDriver driver) {
        boolean blResult=false;
        WebElement element = Object.getElement(driver, strLocatorType, strLocatorValue);
        if(element!=null){
            if(element.isDisplayed()){
                if(element.isEnabled()){
                    try{
                        element.click();
                        blResult=true;
                        ResultUtil.report("PASS", "Clicked button <<" + strLogicalName + ">>","","", driver);
                    }
                    catch(Exception e){
                        ResultUtil.report("FAIL", "Unable to click <<" + strLogicalName + ">> button Exception occurred:" + e.getMessage(),"","", driver);
                    }

                }
                else{
                    ResultUtil.report("FAIL", "Unable to click-<<" + strLogicalName + ">> button is disabled","","", driver);
                }
            }
            else{
                ResultUtil.report("FAIL", "Unable to click-<<" + strLogicalName + ">> button does not exist","","", driver);
            }

        }
        else{
            ResultUtil.report("FAIL", "Unable to click-<<"+strLogicalName+">> button does not exist","","", driver);
        }
        return blResult;
    }
}
