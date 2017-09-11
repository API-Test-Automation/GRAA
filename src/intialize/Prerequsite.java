package intialize;

import util.DateUtil;
import util.FileUtil;
import toolbox.ResultUtil;
import org.apache.commons.io.FileUtils;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import util.XMLUtil;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class Prerequsite {
	public static Map<String,String> configMap;


    @BeforeSuite(groups = {"all", "chrome", "firefox", "ie", "parallel"})
public void initialize(ITestContext context){	
	getExecutionConfig();
	createReportSummary(context);
}

    @AfterSuite(groups = {"all", "chrome", "firefox", "ie", "parallel"})
public void ending(){		
	ResultUtil.endReport();
	
}

public void createReportSummary(final ITestContext context){
	try{	
		String strUrl = Prerequsite.configMap.get("Url");
		final String strSuiteName=context.getCurrentXmlTest().getSuite().getName();
		final String strReportFolder="result/"+strSuiteName+"-"+DateUtil.getCurrentDate("yyyy_MM_dd_HH_mm_ss");
		System.setProperty("MODULE",  context.getCurrentXmlTest().getName());
		ResultUtil.strResultFolder=strReportFolder;
		FileUtil.createFolder(strReportFolder);
		FileUtil.createFolder(strReportFolder+"/Screenshot");

		/*final String strSummaryContent="<html><head><style>"+ResultUtil.strCSS+"</style></head>"+
			"<body><div class='datagrid'><table id='tblSummary'><thead>"+
			"<tr><th>Script Name</th><th>Machine</th><th>Browser</th><th>Start Time</th><th>Status</th><th>Link</th></tr>"+
			"</thead><tbody></tbody></table></div></body></html>";
		FileUtil.createFileWithContent(strReportFolder+"/Summary.html", strSummaryContent);*/

		String strSummaryFile=strReportFolder+"/Summary.html";
		FileUtils.copyDirectory(new File("template"), new File(strReportFolder));
		XMLUtil.addText(strSummaryFile, "//h1[@id='header']", strSuiteName);
		XMLUtil.addText(strSummaryFile, "//td[@id='QAEnv']", strUrl);
		XMLUtil.addText(strSummaryFile, "//td[@id='date']", DateUtil.getCurrentDate("dd-MM-YYYY"));
		XMLUtil.addText(strSummaryFile, "//td[@id='sTime']", DateUtil.getCurrentDate("HH:mm:ss"));
		ResultUtil.reportFilesMap=new HashMap<String,String>();
	}
	catch(Exception e){

	}
}

public void getExecutionConfig(){
	try{
		Properties prop = new Properties();
		prop.load(new FileInputStream("config/testdata.properties"));
		configMap = new HashMap<String, String>();
		for (String key : prop.stringPropertyNames()) {
			configMap.put(key, prop.getProperty(key));
		}
	}
	catch(Exception e){
		
	}
}
}
