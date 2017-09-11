package common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import httpUtil.ServiceHitUtil;
import toolbox.ResultUtil;
import util.ExcelUtils;


public class SuiteExucuter {

	public static void execute(Map<String, String> map) {
		

		
		String PROJECT =map.get("PROJECT");	
		String EXECUTION =map.get("EXECUTION"); 
		String	ENVIRONMENT =map.get("ENVIRONMENT");
		String	BASEURL=map.get("BASEURL");
		String	PORT=map.get("PORT");	
		String TESTSUIT=map.get("TESTSUIT") ;
 
Map<String, Map<String, String>> scriptsMap=ExcelUtils.getSuite("Input/"+PROJECT+"_TESTSUITE.xls",PROJECT);
		
		ResultUtil.createReportFolder(PROJECT);
		
		for(Entry scriptMap:scriptsMap.entrySet()){
			Map<String,String> scriptDetailsMap=(Map<String, String>) scriptMap.getValue();
			String strDPName=scriptDetailsMap.get("TestID");
			ResultUtil.createReport(scriptDetailsMap);
			ServiceHitUtil serviceHitUtil=new ServiceHitUtil();
			serviceHitUtil.hitService("Scripts/"+PROJECT+"/"+strDPName+".xls",strDPName,map);
		}
		
		ResultUtil.endReport();
	}

}
