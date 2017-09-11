package toolbox;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;

import intialize.Prerequsite;
//import util.ScriptUtil;
import util.DateUtil;
import util.FileUtil;
import util.XMLUtil;

public class ResultUtil {
public static String strResultFolder;
private static String strCurrentReportID;
private static String strCurrentReportFileName;

public static String strID=null;

private static int inTestCount=0;
private static int inStepCount=0;
static Logger log = Logger.getLogger(
		ResultUtil.class.getName());

public static Map<String,String> reportFilesMap;

/**
 * Description : To create report folder
 * 
 * @author Mahesh Babu
*/
public static void createReportFolder(String strDriverName){
	strResultFolder="result/"+strDriverName+"-"+DateUtil.getCurrentDate("yyyy_MM_dd_HH_mm_ss");
	FileUtil.createFolder(strResultFolder);
	FileUtil.createFolder(strResultFolder+"/xml");
	
	try{
		String strSummaryFile=strResultFolder+"\\Summary.html";
		FileUtils.copyDirectory(new File("template"), new File(strResultFolder));
		XMLUtil.addText(strSummaryFile, "//h1[@id='header']", strDriverName);
		XMLUtil.addText(strSummaryFile, "//td[@id='date']", DateUtil.getCurrentDate("dd-MM-YYYY"));
		XMLUtil.addText(strSummaryFile, "//td[@id='sTime']", DateUtil.getCurrentDate("HH:mm:ss"));
	}catch(Exception e){
		log.error(e.getMessage());
	}
}

/**
 * Description : To update End Time IN Summary report
 * 
 * @author Mahesh Babu
*/
public static void endReport(){
	try{
		String strSummaryFile=strResultFolder+"\\Summary.html";
		XMLUtil.addText(strSummaryFile, "//td[@id='eTime']", DateUtil.getCurrentDate("HH:mm:ss"));
	}catch(Exception e){
		log.error(e.getMessage());
	}
}



	public synchronized static void report(String strStatus,String sStep,String strExpected,String strActual,WebDriver driver){
		try{
			//String strModuleName=System.getProperty("MODULE");
			String strThreadID=""+Thread.currentThread().getId();
			String strReportFile=reportFilesMap.get(strThreadID);
			Map<String,String> linkMap=new HashMap<String, String>();
			Map<String,String> attrMap=new HashMap<String, String>();
			String strScreenshotFile;	
			
			boolean blResult = false;
			
			Map<String,String> attributeMap=new HashMap<String, String>();
			strID=UUID.randomUUID().toString();
			attributeMap.put("name",strStatus);
			attributeMap.put("id",strID);
			XMLUtil.addNodeWithAttributes(strResultFolder+"/"+strReportFile, "//table[@id='tblDetail']/tbody", "tr",attributeMap);
			//XMLUtil.addNode(strResultFolder+"/"+strReportFile, "//table[@id='tblDetail']/tbody", "tr");
			XMLUtil.addNodeWithText(strResultFolder+"/"+strReportFile, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']", "td",String.valueOf(getStepCount()));
			//XMLUtil.addNodeWithText(strResultFolder+"/"+strReportFile, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']", "td",strStoryID);
			XMLUtil.addNodeWithText(strResultFolder+"/"+strReportFile, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']", "td",sStep);
//			XMLUtil.addNodeWithText(strResultFolder+"/"+strReportFile, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']", "td",strDesc);
			XMLUtil.addNodeWithText(strResultFolder+"/"+strReportFile, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']", "td",strExpected);
			XMLUtil.addNodeWithText(strResultFolder+"/"+strReportFile, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']", "td",strActual);
			XMLUtil.addNodeWithText(strResultFolder+"/"+strReportFile, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']", "td",DateUtil.getCurrentDate("yyyy/MM/dd HH:mm:sss"));
			XMLUtil.addNodeWithText(strResultFolder+"/"+strReportFile, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']", "td",strStatus);
			XMLUtil.addNodeWithText(strResultFolder+"/"+strReportFile, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']", "td","");
			
			/*Map<String,String> configMap = PropertyUtil.getPropertyFileAsHashmap("config/execution.properties");
			String strSCREENSHOT= configMap.get("SCREENSHOT");*/
			String strSCREENSHOT= Prerequsite.configMap.get("SCREENSHOT");

			System.out.println(strSCREENSHOT + DateUtil.getCurrentDate("yyyy/MM/dd HH:mm:sss"));
			System.out.println(strStatus + DateUtil.getCurrentDate("yyyy/MM/dd HH:mm:sss"));
			
			
			if(strSCREENSHOT.equalsIgnoreCase("ONFAILURE") && strStatus.equals("FAIL")){
				WebDriver augmentedDriver = new Augmenter().augment(driver);
			
		    	File screenshot = ((TakesScreenshot)augmentedDriver).
		                getScreenshotAs(OutputType.FILE);
		
		    	strScreenshotFile="Screenshot/"+"screen-"+java.util.UUID.randomUUID().toString()+".png";
		    	FileUtils.copyFile(screenshot, new File(strResultFolder+"/"+strScreenshotFile));
		    	linkMap.put("href",strScreenshotFile);
				XMLUtil.addNodeWithText(strResultFolder+"/"+strReportFile, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']/td[7]", "a","Link");
				XMLUtil.addAttributes(strResultFolder+"/"+strReportFile, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']/td[7]/a", linkMap);
			}
			else if(strSCREENSHOT.equalsIgnoreCase("ALWAYS") && !(strStatus.equals("INFO"))){
				WebDriver augmentedDriver = new Augmenter().augment(driver);	
			    File screenshot = ((TakesScreenshot)augmentedDriver).
			                     getScreenshotAs(OutputType.FILE);
			 
			    strScreenshotFile="Screenshot/"+"screen-"+java.util.UUID.randomUUID().toString()+".png";
				FileUtils.copyFile(screenshot, new File(strResultFolder+"/"+strScreenshotFile));
				linkMap.put("href",strScreenshotFile);
				XMLUtil.addNodeWithText(strResultFolder+"/"+strReportFile, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']/td[7]", "a","Link");
				XMLUtil.addAttributes(strResultFolder+"/"+strReportFile, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']/td[7]/a", linkMap);
		        
			}
			if(strStatus.equalsIgnoreCase("PASS")){
	           blResult=true;
	        }
	        else if(strStatus.equalsIgnoreCase("FAIL")){
	        	blResult=false;
	            XMLUtil.addNodeText(strResultFolder+"/Summary.html", "//table[@id='tblSummary']/tbody/tr[@id='"+strCurrentReportID+"']/td[6]","FAIL");
	            XMLUtil.addAttribute(strResultFolder+"/Summary.html", "//table[@id='tblSummary']/tbody/tr[@id='"+strCurrentReportID+"']","name","FAIL");
	            
	            /******Change FONT COLOR********/
	            
	            XMLUtil.addAttribute(strResultFolder+"/Summary.html", "//table[@id='tblSummary']/tbody/tr[@id='"+strCurrentReportID+"']/td[1]","style","color:red;font-weight:bold;");
	            XMLUtil.addAttribute(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']/td[1]", "style","color:red;font-weight:bold;");
	            
	            XMLUtil.addAttribute(strResultFolder+"/Summary.html", "//table[@id='tblSummary']/tbody/tr[@id='"+strCurrentReportID+"']/td[2]","style","color:red;font-weight:bold;");
	            XMLUtil.addAttribute(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']/td[2]", "style","color:red;font-weight:bold;");
	            
	            XMLUtil.addAttribute(strResultFolder+"/Summary.html", "//table[@id='tblSummary']/tbody/tr[@id='"+strCurrentReportID+"']/td[3]","style","color:red;font-weight:bold;");
	            XMLUtil.addAttribute(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']/td[3]", "style","color:red;font-weight:bold;");
	            
	            XMLUtil.addAttribute(strResultFolder+"/Summary.html", "//table[@id='tblSummary']/tbody/tr[@id='"+strCurrentReportID+"']/td[4]","style","color:red;font-weight:bold;");
	            XMLUtil.addAttribute(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']/td[4]", "style","color:red;font-weight:bold;");
	            
	            XMLUtil.addAttribute(strResultFolder+"/Summary.html", "//table[@id='tblSummary']/tbody/tr[@id='"+strCurrentReportID+"']/td[5]","style","color:red;font-weight:bold;");
	            XMLUtil.addAttribute(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']/td[5]", "style","color:red;font-weight:bold;");
	            
	            XMLUtil.addAttribute(strResultFolder+"/Summary.html", "//table[@id='tblSummary']/tbody/tr[@id='"+strCurrentReportID+"']/td[6]","style","color:red;font-weight:bold;");
	            XMLUtil.addAttribute(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']/td[6]", "style","color:red;font-weight:bold;");
	            
	          //  XMLUtil.addAttribute(strResultFolder+"/Summary.html", "//table[@id='tblSummary']/tbody/tr[@id='"+strCurrentReportID+"']/td[7]","style","color:red;font-weight:bold;");
//	            XMLUtil.addAttribute(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']/td[7]", "style","color:red;font-weight:bold;");
	        }    
	        else if(strStatus.equalsIgnoreCase("INFO")){
				System.out.println("");
				attrMap.put("style", "background-color:#ADD8E6;");
				XMLUtil.addAttributes(strResultFolder+"/"+strReportFile, "//table[@id='tblDetail']/tbody/tr[last()]", attrMap);
	        }
	        else if(strStatus.equalsIgnoreCase("MESSAGE")){
	        	
	        	System.out.println("");
				attrMap.put("style", "background-color:#ADBCE6;");
				XMLUtil.addAttributes(strResultFolder+"/"+strReportFile, "//table[@id='tblDetail']/tbody/tr[last()]", attrMap);
	        	
	        }
			
			/*if(strStatus.equalsIgnoreCase("FAIL")){
				XMLUtil.addText(strResultFolder+"/Summary.html", "//table[@id='tblSummary']/tbody/tr[@id='"+strThreadID+"-"+FilenameUtils.getBaseName(strReportFile)+"']/td[5]","FAIL");
				XMLUtil.addText("emailResult/"+strModuleName+"-Emailable-Result.html", "//table[@id='tblSummary']/tbody/tr[@id='"+strThreadID+"-"+FilenameUtils.getBaseName(strReportFile)+"']/td[5]","FAIL");
			}*/
	    }
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static int getStepCount(){
		inStepCount++;
		return inStepCount;
	}
/**
 * Description : To log result in Detail report 
 * 
 * @author Mahesh Babu
*/
public synchronized static void print(String strStatus,String strDesc){
	System.out.println(strStatus+"-"+strDesc);
	System.out.println();
	boolean blResult=false;
	
	try{
		Map<String,String> attributeMap=new HashMap();
		String strID=UUID.randomUUID().toString();
		attributeMap.put("name",strStatus);
		attributeMap.put("id",strID);
		XMLUtil.addNodeWithAttributes(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody", "tr",attributeMap);
		XMLUtil.addNodeWithText(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']", "td",strDesc);
		XMLUtil.addNodeWithText(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']", "td","");
		XMLUtil.addNodeWithText(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']", "td","");
		XMLUtil.addNodeWithText(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']", "td",strStatus);
		XMLUtil.addNodeWithText(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']", "td",DateUtil.getCurrentDate("yyyy_MM_dd_HH_mm_sss"));
		XMLUtil.addNodeWithText(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']", "td","");
		
		if(strStatus.equalsIgnoreCase("PASS")){
			blResult=true;
		}
		else if(strStatus.equalsIgnoreCase("FAIL")){
			XMLUtil.addNodeText(strResultFolder+"/Summary.html", "//table[@id='tblSummary']/tbody/tr[@id='"+strCurrentReportID+"']/td[8]","FAIL");
			XMLUtil.addAttribute(strResultFolder+"/Summary.html", "//table[@id='tblSummary']/tbody/tr[@id='"+strCurrentReportID+"']","name","FAIL");
			
			/*****Mahesh Babu-Background Color changed****/
			XMLUtil.addAttribute(strResultFolder+"/Summary.html", "//table[@id='tblSummary']/tbody/tr[@id='"+strCurrentReportID+"']/td[1]","style","color:red;font-weight:bold;");
			XMLUtil.addAttribute(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']/td[1]", "style","color:red;font-weight:bold;");
			
			XMLUtil.addAttribute(strResultFolder+"/Summary.html", "//table[@id='tblSummary']/tbody/tr[@id='"+strCurrentReportID+"']/td[2]","style","color:red;font-weight:bold;");
			XMLUtil.addAttribute(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']/td[2]", "style","color:red;font-weight:bold;");
			
			XMLUtil.addAttribute(strResultFolder+"/Summary.html", "//table[@id='tblSummary']/tbody/tr[@id='"+strCurrentReportID+"']/td[3]","style","color:red;font-weight:bold;");
			XMLUtil.addAttribute(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']/td[3]", "style","color:red;font-weight:bold;");
			
			XMLUtil.addAttribute(strResultFolder+"/Summary.html", "//table[@id='tblSummary']/tbody/tr[@id='"+strCurrentReportID+"']/td[4]","style","color:red;font-weight:bold;");
			XMLUtil.addAttribute(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']/td[4]", "style","color:red;font-weight:bold;");
			
			XMLUtil.addAttribute(strResultFolder+"/Summary.html", "//table[@id='tblSummary']/tbody/tr[@id='"+strCurrentReportID+"']/td[5]","style","color:red;font-weight:bold;");
			XMLUtil.addAttribute(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']/td[5]", "style","color:red;font-weight:bold;");
			
			XMLUtil.addAttribute(strResultFolder+"/Summary.html", "//table[@id='tblSummary']/tbody/tr[@id='"+strCurrentReportID+"']/td[6]","style","color:red;font-weight:bold;");
			XMLUtil.addAttribute(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']/td[6]", "style","color:red;font-weight:bold;");
			
			XMLUtil.addAttribute(strResultFolder+"/Summary.html", "//table[@id='tblSummary']/tbody/tr[@id='"+strCurrentReportID+"']/td[7]","style","color:red;font-weight:bold;");
			XMLUtil.addAttribute(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']/td[7]", "style","color:red;font-weight:bold;");
			
			XMLUtil.addAttribute(strResultFolder+"/Summary.html", "//table[@id='tblSummary']/tbody/tr[@id='"+strCurrentReportID+"']/td[8]","style","color:red;font-weight:bold;");
			XMLUtil.addAttribute(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']/td[8]", "style","color:red;font-weight:bold;");
			
			XMLUtil.addAttribute(strResultFolder+"/Summary.html", "//table[@id='tblSummary']/tbody/tr[@id='"+strCurrentReportID+"']/td[9]","style","color:red;font-weight:bold;");
			XMLUtil.addAttribute(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']/td[9]", "style","color:red;font-weight:bold;");
			
			
			
			
			/*for(int i=1;i<=6;i++){
				XMLUtil.addAttribute(strResultFolder+"/Summary.html", "//table[@id='tblSummary']/tbody/tr[@id='"+strCurrentReportID+"']/td[i]","style","color:red;font-weight:bold;");
				XMLUtil.addAttribute(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']/td[i]", "style","color:red;font-weight:bold;");
			}*/
									
			/*XMLUtil.addAttribute(strResultFolder+"/Summary.html", "//table[@id='tblSummary']/tbody/tr[@id='"+strCurrentReportID+"']/td[5]","style","color:red;font-weight:bold;");
			XMLUtil.addAttribute(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']/td[4]", "style","color:red;font-weight:bold;");*/
			}
		else if(strStatus.equalsIgnoreCase("INFO")){
			blResult=true;
		}
	}
	catch(Exception e){
		log.error(e.getMessage());
	}

}

/**
 * Description : To log result in Detail report 
 * 
 * @author Mahesh Babu
*/
public synchronized static void print(String strStatus,String strDesc,String strExpected,String strActual){
	System.out.println(strStatus+"-"+strDesc);
	System.out.println();
	boolean blResult=false;
	
	try{
		Map<String,String> attributeMap=new HashMap();
		String strID=UUID.randomUUID().toString();
		attributeMap.put("name",strStatus);
		attributeMap.put("id",strID);
		XMLUtil.addNodeWithAttributes(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody", "tr",attributeMap);
		XMLUtil.addNodeWithText(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']", "td",strDesc);
		XMLUtil.addNodeWithText(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']", "td",strExpected);
		XMLUtil.addNodeWithText(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']", "td",strActual);
		XMLUtil.addNodeWithText(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']", "td",strStatus);
		XMLUtil.addNodeWithText(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']", "td",DateUtil.getCurrentDate("yyyy_MM_dd_HH_mm_sss"));
		XMLUtil.addNodeWithText(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']", "td","");
		
		if(strStatus.equalsIgnoreCase("PASS")){
			blResult=true;
			
			
		}
		else if(strStatus.equalsIgnoreCase("FAIL")){
			XMLUtil.addNodeText(strResultFolder+"/Summary.html", "//table[@id='tblSummary']/tbody/tr[@id='"+strCurrentReportID+"']/td[8]","FAIL");
			XMLUtil.addAttribute(strResultFolder+"/Summary.html", "//table[@id='tblSummary']/tbody/tr[@id='"+strCurrentReportID+"']","name","FAIL");
			
			/****Mahesh Babu-Changing background color****/
			
			XMLUtil.addAttribute(strResultFolder+"/Summary.html", "//table[@id='tblSummary']/tbody/tr[@id='"+strCurrentReportID+"']/td[1]","style","color:red;font-weight:bold;");
			XMLUtil.addAttribute(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']/td[1]", "style","color:red;font-weight:bold;");
			
			XMLUtil.addAttribute(strResultFolder+"/Summary.html", "//table[@id='tblSummary']/tbody/tr[@id='"+strCurrentReportID+"']/td[2]","style","color:red;font-weight:bold;");
			XMLUtil.addAttribute(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']/td[2]", "style","color:red;font-weight:bold;");
			
			XMLUtil.addAttribute(strResultFolder+"/Summary.html", "//table[@id='tblSummary']/tbody/tr[@id='"+strCurrentReportID+"']/td[3]","style","color:red;font-weight:bold;");
			XMLUtil.addAttribute(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']/td[3]", "style","color:red;font-weight:bold;");
			
			XMLUtil.addAttribute(strResultFolder+"/Summary.html", "//table[@id='tblSummary']/tbody/tr[@id='"+strCurrentReportID+"']/td[4]","style","color:red;font-weight:bold;");
			XMLUtil.addAttribute(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']/td[4]", "style","color:red;font-weight:bold;");
			
			XMLUtil.addAttribute(strResultFolder+"/Summary.html", "//table[@id='tblSummary']/tbody/tr[@id='"+strCurrentReportID+"']/td[5]","style","color:red;font-weight:bold;");
			XMLUtil.addAttribute(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']/td[5]", "style","color:red;font-weight:bold;");
			
			XMLUtil.addAttribute(strResultFolder+"/Summary.html", "//table[@id='tblSummary']/tbody/tr[@id='"+strCurrentReportID+"']/td[6]","style","color:red;font-weight:bold;");
			XMLUtil.addAttribute(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']/td[6]", "style","color:red;font-weight:bold;");
			
			XMLUtil.addAttribute(strResultFolder+"/Summary.html", "//table[@id='tblSummary']/tbody/tr[@id='"+strCurrentReportID+"']/td[7]","style","color:red;font-weight:bold;");
			XMLUtil.addAttribute(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']/td[7]", "style","color:red;font-weight:bold;");
			
			XMLUtil.addAttribute(strResultFolder+"/Summary.html", "//table[@id='tblSummary']/tbody/tr[@id='"+strCurrentReportID+"']/td[8]","style","color:red;font-weight:bold;");
			XMLUtil.addAttribute(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']/td[8]", "style","color:red;font-weight:bold;");
			
			XMLUtil.addAttribute(strResultFolder+"/Summary.html", "//table[@id='tblSummary']/tbody/tr[@id='"+strCurrentReportID+"']/td[9]","style","color:red;font-weight:bold;");
			XMLUtil.addAttribute(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']/td[9]", "style","color:red;font-weight:bold;");
			
			
						
			/*XMLUtil.addAttribute(strResultFolder+"/Summary.html", "//table[@id='tblSummary']/tbody/tr[@id='"+strCurrentReportID+"']/td[5]","style","color:red;font-weight:bold;");
			XMLUtil.addAttribute(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']/td[4]", "style","color:red;font-weight:bold;");*/
		}
		else if(strStatus.equalsIgnoreCase("INFO")){
			blResult=true;
		}
	}
	catch(Exception e){
		log.error(e.getMessage());
	}
	
}

/**
 * Description : To log XML response in Detail report
 * 
 * @author Mahesh Babu
*/
public synchronized static void reportResponse(String strStatus,String strResponse){
	try{
		Map<String,String> attributeMap=new HashMap();
		String strID=UUID.randomUUID().toString();
		attributeMap.put("name",strStatus);
		attributeMap.put("id",strID);
		XMLUtil.addNodeWithAttributes(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody", "tr",attributeMap);
		XMLUtil.addNodeWithText(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']", "td","XML Response");
		XMLUtil.addNodeWithText(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']", "td","");
		XMLUtil.addNodeWithText(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']", "td","");
		XMLUtil.addNodeWithText(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']", "td",strStatus);
		XMLUtil.addNodeWithText(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']", "td",DateUtil.getCurrentDate("yyyy_MM_dd_HH_mm_sss"));
		XMLUtil.addNodeWithText(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']", "td","");
		
		String strRelativePath="/xml/"+UUID.randomUUID().toString()+".xml";
		String strFilePath=strResultFolder+strRelativePath;
		//FileUtil.createFileWithContent(strFilePath, strResponse);
		XMLUtil.createDocument(strFilePath, strResponse);
		Map<String,String> linkMap=new HashMap();
		//linkMap.put("href","."+strRelativePath);
		linkMap.put("href","XML_Tree.html?XMLPath=."+strRelativePath);
		XMLUtil.addNodeWithText(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']/td[6]", "a","XML");
		XMLUtil.addAttributes(strResultFolder+"/"+strCurrentReportFileName, "//table[@id='tblDetail']/tbody/tr[@id='"+strID+"']/td[6]/a", linkMap);
	}
	catch(Exception e){
		log.error(e.getMessage());
	}
}

/**
 * Description : To create Detail report file
 * 
 * @author Mahesh Babu
*/
public synchronized static void createReport(Map<String,String> scriptDetailsMap){
	String strScriptName=scriptDetailsMap.get("TestID");
	String strReportFileName=strScriptName+"-"+DateUtil.getCurrentDate("yyyy_MM_dd_HH_mm_sss");
	String strReportFile=strReportFileName+".html";
	
	strCurrentReportFileName=strReportFile;
	
	try{
		FileUtils.copyFile(new File("Template/Detail.html"), new File(strResultFolder+"/"+strReportFile));
		XMLUtil.addText(strResultFolder+"/"+strReportFile, "//h1[@id='header']", strScriptName);
		XMLUtil.addText(strResultFolder+"/"+strReportFile, "//td[@id='date']", DateUtil.getCurrentDate("dd-MM-YYYY"));
		
		Map<String,String> attributeMap=new HashMap();
		//strCurrentReportID=DateUtil.getCurrentDate("yyyy_MM_dd_HH_mm_sss");
		strCurrentReportID=""+Thread.currentThread().getId()+"-"+strScriptName;
		attributeMap.put("id",strCurrentReportID);
		attributeMap.put("name","PASS");
		
		XMLUtil.addNodeWithAttributes(strResultFolder+"/Summary.html", "//table[@id='tblSummary']/tbody", "tr",attributeMap);
		//S.No.
		XMLUtil.addNodeWithText(strResultFolder+"/Summary.html", "//table[@id='tblSummary']/tbody/tr[@id='"+strCurrentReportID+"']", "td",""+getCount());
		//JIRA ID
		//XMLUtil.addNodeWithText(strResultFolder+"/Summary.html", "//table[@id='tblSummary']/tbody/tr[@id='"+strCurrentReportID+"']", "td",scriptDetailsMap.get("JiraID"));
		//JIRA Component
		//XMLUtil.addNodeWithText(strResultFolder+"/Summary.html", "//table[@id='tblSummary']/tbody/tr[@id='"+strCurrentReportID+"']", "td",scriptDetailsMap.get("JiraComponent"));
		//Test ID
		XMLUtil.addNodeWithText(strResultFolder+"/Summary.html", "//table[@id='tblSummary']/tbody/tr[@id='"+strCurrentReportID+"']", "td",strScriptName);
		//Summary
		XMLUtil.addNodeWithText(strResultFolder+"/Summary.html", "//table[@id='tblSummary']/tbody/tr[@id='"+strCurrentReportID+"']", "td",scriptDetailsMap.get("SUMMARY"));
		//Description
		//XMLUtil.addNodeWithText(strResultFolder+"/Summary.html", "//table[@id='tblSummary']/tbody/tr[@id='"+strCurrentReportID+"']", "td",scriptDetailsMap.get("Description"));
		//TimeStamp
		XMLUtil.addNodeWithText(strResultFolder+"/Summary.html", "//table[@id='tblSummary']/tbody/tr[@id='"+strCurrentReportID+"']", "td",DateUtil.getCurrentDate("yyyy/MM/dd HH:mm:sss"));
		//Status
		XMLUtil.addNodeWithText(strResultFolder+"/Summary.html", "//table[@id='tblSummary']/tbody/tr[@id='"+strCurrentReportID+"']", "td","PASS");
		//Link
		XMLUtil.addNodeWithText(strResultFolder+"/Summary.html", "//table[@id='tblSummary']/tbody/tr[@id='"+strCurrentReportID+"']", "td","");
		
		Map<String,String> linkMap=new HashMap<String, String>();
		linkMap.put("href",strReportFile);
		XMLUtil.addNodeWithText(strResultFolder+"/Summary.html", "//table[@id='tblSummary']/tbody/tr[@id='"+strCurrentReportID+"']/td[6]", "a","Link");
		XMLUtil.addAttributes(strResultFolder+"/Summary.html", "//table[@id='tblSummary']/tbody/tr[@id='"+strCurrentReportID+"']/td[6]/a", linkMap);
	}catch(Exception e){
		log.error(e.getMessage());
	}
}


private static int getCount(){
	inTestCount++;
	return inTestCount;
}
}
