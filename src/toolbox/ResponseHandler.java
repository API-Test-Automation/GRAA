package toolbox;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;


import util.MapUtil;

public class ResponseHandler {
	static Logger log = Logger.getLogger(
			ResponseHandler.class.getName());
	
	/**
	 * Description : To validate webservice response
	 * 
	 * @author Mahesh Babu
	*/
	public static void verifyResponse(Map<String,String> dataMap,Map responseMap){
		Map<String,String> expectedResponseMap=MapUtil.getExpectedResponseMap(dataMap);
		
		try{
			for(Entry reponseEntry:expectedResponseMap.entrySet()){
				String strReponseTagName=reponseEntry.getKey().toString();
				String strReponseValue=reponseEntry.getValue().toString();
				
				if(strReponseValue.split(":=")[0].equalsIgnoreCase("XPATH")){
					XMLHandler.verifyXPath(strReponseTagName, responseMap.get("RESPONSE_CONTENT").toString(), strReponseValue);
				}
				else if(strReponseValue.split(":=")[0].equalsIgnoreCase("!XPATH")){
					XMLHandler.notVerifyXPath(strReponseTagName, responseMap.get("RESPONSE_CONTENT").toString(), strReponseValue);
				}
				else if(strReponseValue.split(":=")[0].equalsIgnoreCase("QUERYCOUNT")){
					DBHandler.runQuery(strReponseValue);
				}
				else if(strReponseValue.split(":=")[0].equalsIgnoreCase("QUERYVALUE")){
					DBHandler.compareValue(strReponseValue);
				}
				else if(strReponseValue.split(":=")[0].equalsIgnoreCase("XML")){
					XMLHandler.compareSubXML(responseMap.get("RESPONSE_CONTENT").toString(), strReponseValue);
				}
				else if(strReponseValue.split(":=")[0].equalsIgnoreCase("!XML")){
					XMLHandler.notCompareSubXML(responseMap.get("RESPONSE_CONTENT").toString(), strReponseValue);
				}
				else if(strReponseValue.split(":=")[0].equalsIgnoreCase("DSORT")){
					XMLHandler.verifyDescendingOrder(responseMap.get("RESPONSE_CONTENT").toString(), strReponseValue);
				}
				else if(strReponseValue.split(":=")[0].equalsIgnoreCase("ASORT")){
					XMLHandler.verifyAscendingOrder(responseMap.get("RESPONSE_CONTENT").toString(), strReponseValue);
				}
			}
		}
		catch(Exception e){
			log.error(e.getMessage());
		}
	}
}
