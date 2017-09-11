package actions;
import toolbox.ResultUtil;
import org.openqa.selenium.WebDriver;




public class Window {
public static boolean focusWindow(String strLogicalName,WebDriver driver,String strWindowHandle){
	boolean blResult=false;
	
	try{
		driver.switchTo().window(strWindowHandle);
		blResult=true;
		ResultUtil.report("PASS", "Focused " + strLogicalName + " window using window handle", "", "", driver);
	}
	catch(Exception e){
		ResultUtil.report("FAIL", "Unable to focus " + strLogicalName + " window using window handle Exception occurred:" + e.getMessage(),"","", driver);
	}
	return blResult;
}
}
