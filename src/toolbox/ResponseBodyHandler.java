package toolbox;

import java.util.Map;
import java.util.Map.Entry;
import org.apache.log4j.Logger;

public class ResponseBodyHandler {
	
	static Logger log = Logger.getLogger(
			ResponseBodyHandler.class.getName());
	
	/**
	 * Description : To construct request body
	 * 
	 * @author Mahesh Babu
	*/
	public static String getRequestBody(String strRequestBody,Map<String,String> dataMap){
		try{
			for(Entry<String, String> entry:dataMap.entrySet()){
				String strColumnName="<<"+entry.getKey()+">>";
				String strColumnValue=entry.getValue();
				
				if(strRequestBody.contains(strColumnName)){
					strRequestBody=strRequestBody.replaceAll(strColumnName, strColumnValue);
				}
			}
		}
		catch(Exception e){
			log.error(e.getMessage());
		}
		return strRequestBody;
	}
}
