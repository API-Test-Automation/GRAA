package actions;

import org.openqa.selenium.WebDriver;
import toolbox.ResultUtil;
import org.openqa.selenium.WebElement;




public class Checkbox {	
	public static boolean check(String strLogicalName,String strLocatorType, String strLocatorValue,WebDriver driver) throws Exception{		
		boolean blResult=false;
		WebElement element = Object.getElement(driver, strLocatorType, strLocatorValue);
		if(element!=null){
			if(element.isDisplayed()){
				if(element.isEnabled()){
					try{
						 if(!element.isSelected()){
							 element.click();
							 blResult=true;
							 ResultUtil.report("PASS", "<<" + strLogicalName + ">> checkBox is checked","","", driver);
						    }
					}
					catch(Exception e){
						ResultUtil.report("FAIL", "Unable to check <<" + strLogicalName + ">> checkbox Exception occurred:" + e.getMessage(),"","", driver);
					}
				}
				else{
					ResultUtil.report("FAIL", "Unable to check-<<" + strLogicalName + ">> checkBox is disabled","","", driver);
				}
			}
			else{
				ResultUtil.report("FAIL", "Unable to check-<<" + strLogicalName + ">> checkBox does not exist","","", driver);
			}
		}
		else{
			ResultUtil.report("FAIL", "Unable to check-<<" + strLogicalName + ">> checkBox does not exist", "", "", driver);
		}
		return blResult;
	}
	
	public static boolean unCheck(String strLogicalName,String strLocatorType, String strLocatorValue,WebDriver driver) throws Exception{
		boolean blResult=false;
		WebElement element = Object.getElement(driver, strLocatorType, strLocatorValue);
		if(element!=null){
			if(element.isDisplayed()){
				if(element.isEnabled()){
					try{
						 if(element.isSelected()){
							 element.click();
							 blResult=true;
							 ResultUtil.report("PASS", "<<" + strLogicalName + ">> checkBox is unchecked","","", driver);
						    }
					}
					catch(Exception e){
						ResultUtil.report("FAIL", "Unable to uncheck <<" + strLogicalName + ">> checkbox Exception occurred:" + e.getMessage(),"","", driver);
					}
					
				}
				else{
					ResultUtil.report("FAIL", "Unable to uncheck-<<" + strLogicalName + ">> checkBox is disabled","","", driver);
				}
			}
			else{
				ResultUtil.report("FAIL", "Unable to uncheck-<<" + strLogicalName + " checkBox does not exist","","", driver);
			}
		}
		else{
			ResultUtil.report("FAIL", "Unable to uncheck-<<" + strLogicalName + " checkBox does not exist", "", "", driver);
		}
		return blResult;
	}
	
	public static boolean isChecked(String strLogicalName,String strLocatorType, String strLocatorValue,WebDriver driver) throws Exception{
		boolean blResult=false;
		WebElement element = Object.getElement(driver, strLocatorType, strLocatorValue);
		if(element!=null){
			if(element.isDisplayed()){
				try{
					 if(element.isSelected()){
						 blResult=true;
						 //ResultUtil.print("PASS", "<<"+strLogicalName+">> checkBox is in checked status",driver);
						 ResultUtil.report("PASS", "<<" + strLogicalName + ">> checkBox is in checked status", "", "", driver);
					    }
				}
				catch(Exception e){
					//ResultUtil.print("FAIL", "Unable to verify <<"+strLogicalName+">> checkbox checked status Exception occurred:"+e.getMessage(),driver);
					ResultUtil.report("FAIL", "Unable to verify <<" + strLogicalName + ">> checkbox checked status Exception occurred:" + e.getMessage(), "", "", driver);
				}
			}
			else{
				ResultUtil.report("FAIL", "Unable to verify checked status-<<" + strLogicalName + ">> checkbox does not exist","","", driver);
			}
		}
		else{
			ResultUtil.report("FAIL", "Unable to verify checked status-<<"+strLogicalName+">> checkbox does not exist","","", driver);
		}
		return blResult;
	}
	
	public static boolean isUnchecked(String strLogicalName,String strLocatorType, String strLocatorValue,WebDriver driver) throws Exception{
		boolean blResult=false;
		WebElement element = Object.getElement(driver, strLocatorType, strLocatorValue);
		if(element!=null){
			if(element.isDisplayed()){
				try{
					 if(!element.isSelected()){
						 blResult=true;
						 ResultUtil.report("PASS", "<<" + strLogicalName + ">> checkBox is in unchecked status","", "", driver);
					    }
				}
				catch(Exception e){
					ResultUtil.report("FAIL", "Unable to verify <<" + strLogicalName + ">> checkbox unchecked status Exception occurred:" + e.getMessage(),"","", driver);
				}
			}
			else{
				ResultUtil.report("FAIL", "Unable to verify unchecked status-<<" + strLogicalName + ">> checkbox does not exist", "", "", driver);
			}
		}
		else{
			ResultUtil.report("FAIL", "Unable to verify unchecked status-<<" + strLogicalName + ">> checkbox does not exist", "", "", driver);
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
					ResultUtil.report("PASS", "Verified <<" + strLogicalName + ">> checkbox","","", driver);
				}
				catch(Exception e){
					ResultUtil.report("FAIL", "Unable to verify-<<" + strLogicalName + ">> checkbox Exception occurred:" + e.getMessage(),"","", driver);
				}
			}
			else{
				ResultUtil.report("FAIL", "Unable to verify-<<" + strLogicalName + ">> checkbox does not exist","","", driver);
			}
		}
		else{
			ResultUtil.report("FAIL", "Unable to verify-<<"+strLogicalName+">> checkbox does not exist","","", driver);
		}
		return blResult;
	}
	
	public static boolean isEnabled(String strLocatorType,String strLocatorValue, String strLogicalName,WebDriver driver) throws Exception{
		boolean blResult=false;
		WebElement element = Object.getElement(driver, strLocatorType, strLocatorValue);
		if(element!=null){
			if(element.isDisplayed()){
				if(element.isEnabled()){
					try{
						blResult=true;
						ResultUtil.report("PASS", "<<" + strLogicalName + ">> checkbox is enabled","","", driver);
					}
					catch(Exception e){
						ResultUtil.report("FAIL", "Unable verify <<" + strLogicalName + ">> checkbox enable status Exception occurred:" + e.getMessage(),"","", driver);
					}
				}
				else{
					ResultUtil.report("FAIL", "<<" + strLogicalName + ">> checkbox is disbled","","", driver);
				}
			}
			else{
				ResultUtil.report("FAIL", "<<" + strLogicalName + ">> checkbox does not exist","","", driver);
			}
		}
		else{
			ResultUtil.report("FAIL", "<<"+strLogicalName+">> checkbox does not exist","","", driver);
		}
		return blResult;
	 }
	public static boolean isDisabled(String strLocatorType, String strLocatorValue, String strLogicalName,WebDriver driver) throws Exception{
		boolean blResult=false;
		WebElement element = Object.getElement(driver, strLocatorType, strLocatorValue);
		if(element!=null){
			if(element.isDisplayed()){
				if(!element.isEnabled()){
					try{
						blResult=true;
						ResultUtil.report("PASS", "<<" + strLogicalName + ">> checkbox is disabled","","", driver);
					}
					catch(Exception e){
						ResultUtil.report("FAIL", "Unable verify <<" + strLogicalName + ">> checkbox disable status Exception occurred:" + e.getMessage(),"","", driver);
					}
				}
				else{
					ResultUtil.report("FAIL", "<<" + strLogicalName + ">> checkbox is enabled", "", "", driver);
				}
			}
			else{
				ResultUtil.report("FAIL", "<<" + strLogicalName + ">> checkbox does not exist", "", "", driver);
			}
		}
		else{
			ResultUtil.report("FAIL", "<<" + strLogicalName + ">> checkbox does not exist", "", "", driver);
		}
		return blResult;
	 }
}
