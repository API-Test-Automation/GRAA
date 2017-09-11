package actions;

import java.util.Random;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import toolbox.ResultUtil;



public class Listbox {
	public static boolean selectItemByValue(String strLogicalName,String strLocatorType,String strLocatorValue,String strValue,WebDriver driver){
		boolean blResult=false;
		WebElement element = Object.getElement(driver, strLocatorType, strLocatorValue);
		if(element!=null){
			if(element.isDisplayed()){
				if(element.isEnabled()){
					try{
						Select sel = new Select(element);
						sel.selectByVisibleText(strValue);
						blResult=true;
						ResultUtil.report("PASS", "Selected [[" + strValue + "]] value in <<" + strLogicalName + ">> listbox", "", "", driver);
					}
					catch(Exception e){
						ResultUtil.report("FAIL", "Unable to select [[" + strValue + "]] value in <<" + strLogicalName + ">> listbox Exception occurred:" + e.getMessage(),"","", driver);
					}
					
				}
				else{
					ResultUtil.report("FAIL", "Unable to select [[" + strValue + "]] value-<<" + strLogicalName + ">> listbox is disabled","","", driver);
				}
			}
			else{
				ResultUtil.report("FAIL", "Unable to select [[" + strValue + "]] value-<<" + strLogicalName + ">> listbox does not exist","","", driver);
			}
		}
		else{
			ResultUtil.report("FAIL", "Unable to select [[" + strValue + "]] value-<<" + strLogicalName + ">> list box does not exist", "", "", driver);
		}
		return blResult;
	}
	
	public static boolean selectItems(String strLogicalName,String strLocatorType,String strLocatorValue,String strItems,WebDriver driver){
		boolean blResult=false;
		WebElement element = Object.getElement(driver, strLocatorType, strLocatorValue);
		if(element!=null){
			if(element.isDisplayed()){
				if(element.isEnabled()){
					try{
						Select sel = new Select(element);
						element.sendKeys(Keys.CONTROL);
						String[] arrItems=strItems.split("\\;");
						for(int inItem=0;inItem<=arrItems.length-1;inItem++){
							sel.selectByVisibleText(arrItems[inItem]);
						}
						
						blResult=true;
						ResultUtil.report("PASS", "Selected [[" + strItems + "]] items in <<" + strLogicalName + ">> listbox", "", "", driver);
					}
					catch(Exception e){
						ResultUtil.report("FAIL", "Unable to select [[" + strItems + "]] items in <<" + strLogicalName + ">> listbox Exception occurred:" + e.getMessage(),"","", driver);
					}
					
				}
				else{
					ResultUtil.report("FAIL", "Unable to select [[" + strItems + "]] items-<<" + strLogicalName + ">> listbox is disabled","","", driver);
				}
			}
			else{
				ResultUtil.report("FAIL", "Unable to select [[" + strItems + "]] items-<<" + strLogicalName + ">> listbox does not exist","","", driver);
			}
		}
		else{
			ResultUtil.report("FAIL", "Unable to select [[" + strItems + "]] items-<<" + strLogicalName + ">> listbox does not exist", "", "", driver);
		}
		return blResult;
	}
	
	public static boolean selectItemByIndex(String strLogicalName,String strLocatorType, String strLocatorValue,int itemIndex,WebDriver driver){
		boolean blResult=false;
		WebElement element = Object.getElement(driver, strLocatorType, strLocatorValue);
		if(element!=null){
			if(element.isDisplayed()){
				if(element.isEnabled()){
					try{
						Select sel = new Select(element);
						sel.selectByIndex(itemIndex);
						blResult=true;
						ResultUtil.report("PASS", "Selected [[" + itemIndex + "]] item in <<" + strLogicalName + ">> listbox", "", "", driver);
					}
					catch(Exception e){
						ResultUtil.report("FAIL", "Unable to select [[" + itemIndex + "]] item in <<" + strLogicalName + ">> listbox Exception occurred:" + e.getMessage(),"","", driver);
					}
					
				}
				else{
					ResultUtil.report("FAIL", "Unable to select [[" + itemIndex + "]] item-<<" + strLogicalName + ">> listbox is disabled", "","",driver);
				}
			}
			else{
				ResultUtil.report("FAIL", "Unable to select [[" + itemIndex + "]] item-<<" + strLogicalName + ">> listbox does not exist","","", driver);
			}
		}
		else{
			ResultUtil.report("FAIL", "Unable to select [[" + itemIndex + "]] item-<<" + strLogicalName + ">> listbox does not exist","","", driver);
		}
		return blResult;
	}
	
	public static boolean selectFirstItem(String strLogicalName,String strLocatorType,String strLocatorValue,WebDriver driver){
		boolean blResult=false;
		WebElement element = Object.getElement(driver, strLocatorType, strLocatorValue);
		if(element!=null){
			if(element.isDisplayed()){
				if(element.isEnabled()){
					try{
						Select sel = new Select(element);
						sel.selectByIndex(1);
						blResult=true;
						ResultUtil.report("PASS", "Selected first item in <<" + strLogicalName + ">> listbox" ,"", "" , driver);
					}
					catch(Exception e){
						ResultUtil.report("FAIL", "Unable to select first item in <<" + strLogicalName + ">> listbox Exception occurred:" + e.getMessage(),"","", driver);
					}
					
				}
				else{
					ResultUtil.report("FAIL", "Unable to select first item-<<" + strLogicalName + ">> listbox is disabled","","",driver);
				}
			}
			else{
				ResultUtil.report("FAIL", "Unable to select first item-<<" + strLogicalName + ">> listbox does not exist","","", driver);
			}
		}
		else{
			ResultUtil.report("FAIL", "Unable to select first item-<<" + strLogicalName + ">> listbox does not exist", "", "", driver);
		}
		return blResult;
	}
	
	public static boolean selectLastItem(String strLogicalName,String strLocatorType,String strLocatorValue,WebDriver driver){
		boolean blResult=false;
		WebElement element = Object.getElement(driver, strLocatorType, strLocatorValue);
		int size =0;
		if(element!=null){
			if(element.isDisplayed()){
				if(element.isEnabled()){
					try{
						Select sel = new Select(element);
						sel.selectByIndex(sel.getOptions().size());
						blResult=true;
						ResultUtil.report("PASS", "Selected last item in <<" + strLogicalName + ">> listbox","","", driver);
					}
					catch(Exception e){
						ResultUtil.report("FAIL", "Unable to select last item in <<" + strLogicalName + ">> listbox Exception occurred:" + e.getMessage(),"","", driver);
					}
					
				}
				else{
					ResultUtil.report("FAIL", "Unable to select last item-<<" + strLogicalName + ">> is disabled","","", driver);
				}
			}
			else{
				ResultUtil.report("FAIL", "Unable to select last item-<<" + strLogicalName + ">> does not exist", "", "", driver);
			}
		}
		else{
			ResultUtil.report("FAIL", "Unable to select last item-<<" + strLogicalName + ">> does not exist","","", driver);
		}
		return blResult;
	}
	
	public static boolean selectRandomItem(String strLogicalName,String strLocatorType,String strLocatorValue,WebDriver driver){
		boolean blResult=false;
		WebElement element = Object.getElement(driver, strLocatorType, strLocatorValue);
		int size =0;
		int index = 0;
		if(element!=null){
			if(element.isDisplayed()){
				if(element.isEnabled()){
					try{
						Select sel = new Select(element);
						index=getRandomIndex(sel.getOptions().size());
						sel.selectByIndex(index);
						blResult=true;
						ResultUtil.report("PASS", "Selected [[" + index + "]] random item in <<" + strLogicalName + ">> listbox","","", driver);
					}
					catch(Exception e){
						ResultUtil.report("FAIL", "Unable to select [[" + index + "]] random item in <<" + strLogicalName + ">> listbox Exception occurred:" + e.getMessage(),"","", driver);
					}
					
				}
				else{
					ResultUtil.report("FAIL", "Unable to select [[" + index + "]] random item-<<" + strLogicalName + ">> listbox is disabled","","", driver);
				}
			}
			else{
				ResultUtil.report("FAIL", "Unable to select [[" + index + "]] random item-<<" + strLogicalName + ">> listbox does not exist","","", driver);
			}
		}
		else{
			ResultUtil.report("FAIL", "Unable to select [[" + index + "]] random item-<<" + strLogicalName + ">> listbox does not exist", "", "", driver);
		}
		return blResult;
	}

	public static boolean isEnabled(String strLocatorType,String strLocatorValue, String strLogicalName,WebDriver driver){
        boolean blResult=false;
        WebElement element = Object.getElement(driver, strLocatorType, strLocatorValue);
        if(element!=null){
			if(element.isDisplayed()){
				if(element.isEnabled()){
					try{
						blResult = true;
						ResultUtil.report("PASS", "<<" + strLogicalName + ">> listbox is enabled", "", "", driver);
					}
					catch(Exception e){
						ResultUtil.report("FAIL", "Unable to verify <<" + strLogicalName + ">> listbox enable status Exception occurred: " + e.getMessage(),"","", driver);
					}
					
				}
				else{
					ResultUtil.report("FAIL", "Unable to verify enable status-<<" + strLogicalName + ">> comobox is disabled","","", driver);
				}
			}
			else{
				ResultUtil.report("FAIL", "Unable to verify enable status-<<" + strLogicalName + ">> comobox does not exist","","", driver);
			}
        }
        else{
        	ResultUtil.report("FAIL", "Unable to verify enable status-<<" + strLogicalName + ">> comobox does not exist", "", "", driver);
        }
		return blResult;
	}
	
	public static boolean isDisabled(String strLocatorType,String strLocatorValue , String strLogicalName,WebDriver driver){
        boolean blResult=false;
        WebElement element = Object.getElement(driver, strLocatorType, strLocatorValue);
        if(element!=null){
			if(element.isDisplayed()){
				if(!element.isEnabled()){
					try{
						blResult = true;
						ResultUtil.report("PASS", "<<" + strLogicalName + ">> listbox is disabled","","", driver);
					}
					catch(Exception e){
						ResultUtil.report("FAIL", "Unable to verify <<" + strLogicalName + ">> listbox disable status Exception occurred: " + e.getMessage(), "","",driver);
					}
					
				}
				else{
					ResultUtil.report("FAIL", "Unable to verify disable status-<<" + strLogicalName + ">> listbox is disabled", "","",driver);
				}
			}
			else{
				ResultUtil.report("FAIL", "Unable to verify disable status-<<" + strLogicalName + ">> listbox does not exist", "", "", driver);
			}
        }
        else{
        	ResultUtil.report("FAIL", "Unable to verify disable status-<<" + strLogicalName + ">> listbox does not exist", "", "", driver);
        }
		return blResult;
	}
	
	public static int getRandomIndex(int size){
		int random =new Random().nextInt(size+1);
		if(random==0){random++;}
		return random;
	}
	
	public static boolean deselectAll(String strLocatorType,String strLocatorValue, String strLogicalName,WebDriver driver){
        boolean blResult=false;
        WebElement element = Object.getElement(driver, strLocatorType, strLocatorValue);
        if(element!=null){
			if(element.isDisplayed()){
				if(element.isEnabled()){
					try{
						Select sel = new Select(element);
						sel.deselectAll();
						blResult = true;
						ResultUtil.report("PASS", "Deselected all items in <<" + strLogicalName + ">> listbox","","", driver);
					}
					catch(Exception e){
						ResultUtil.report("FAIL", "Unable to deselect all items in <<" + strLogicalName + ">> listbox Exception occurred: " + e.getMessage(),"","", driver);
					}
				}
				else{
					ResultUtil.report("FAIL", "Unable to deselect all items-<<" + strLogicalName + ">> listbox is disabled", "", "", driver);
				}
			}
			else{
				ResultUtil.report("FAIL", "Unable to deselect all items-<<" + strLogicalName + ">> listbox does not exist","","", driver);
			}
        }
        else{
        	ResultUtil.report("FAIL", "Unable to deselect all items-<<" + strLogicalName + ">> listbox does not exist", "", "", driver);
        }
		return blResult;
	}

}
