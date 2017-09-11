package httpUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import toolbox.DBHandler;
import toolbox.ResponseHandler;
import toolbox.ResultUtil;
import toolbox.StringHandler;
import util.ExcelUtils;
//import util.UIUtil;
import util.MapUtil;
//import util.ScriptUtil;

public class ServiceHitUtil {

	private String strEnv, strBaseUrl, strPort, strUIUrl;
	private Map<String, String> configMap;
	static Logger log = Logger.getLogger(ServiceHitUtil.class.getName());

	public ServiceHitUtil() {
	//this.configMap = getConfig("config/execution");
		//strEnv = configMap.get("ENVIRONMENT");
		//strBaseUrl = configMap.get("BASEURL");
		//strPort = configMap.get("PORT");
		//strUIUrl = configMap.get("UIURL");
	}

	/**
	 * Description : Generic WebService method to request all services
	 * 
	 * @author mahesh Babu
	 * @param map 
	 */
	public void hitService(String strData, String strWorksheetName, Map<String, String> map) {
		Map<String, Object> resultMap = new HashMap();
		
		strEnv = map.get("ENVIRONMENT");
		strBaseUrl = map.get("BASEURL");
		strPort = map.get("PORT");

		try {

			Map<String, Map<String, String>> dataMap = ExcelUtils.getData(strData, strWorksheetName);

			/*
			 * // Description: Establish DB Connection
			 * DBHandler.getConnection(configMap.get("DBTIMEOUT"),
			 * configMap.get("DB"), configMap.get("DBURL"),
			 * configMap.get("DBUSERNAME"), configMap.get("DBPASSWORD"));
			 */
			for (Entry<String, Map<String, String>> entry : dataMap.entrySet()) {
				Map responseMap = null;
				String strStatusCode;
				Map<String, String> iterationMap = MapUtil.normalizeMap(entry.getValue());
				// Map<String, String> iterationMap =
				// MapUtil.normalizeRandomData(entry.getValue());
				iterationMap = MapUtil.normalizeMap(iterationMap);
				// iterationMap = MapUtil.normalizeDBMap(configMap,
				// iterationMap);
				Map<String, String> urlPathMap = MapUtil.getUrlPathMap(iterationMap);
				// Description: Print Summary
				ResultUtil.print("INFO", iterationMap.get("SUMMARY"));
				// Description: Generating URL
				Map<String, String> urlMap = URIUtil.getURLWithoutParameters(strBaseUrl, strPort,
						iterationMap.get("METHOD"), urlPathMap);
				String strURL = urlMap.get("URL");
				String strDecodedURL = urlMap.get("DECODED_URL");

				iterationMap.put("RB_URL", strURL);

				iterationMap = MapUtil.normalizeMap(iterationMap);

				if (iterationMap.get("METHOD").equalsIgnoreCase("GET")) {
					responseMap = HTTPGetUtil.get(strDecodedURL);
				} else if (iterationMap.get("METHOD").equalsIgnoreCase("PUT")) {
					responseMap = HTTPPutUtil.put(strDecodedURL, iterationMap.get("REQUEST_BODY"));
				}

				strStatusCode = responseMap.get("STATUS_CODE").toString();
				// Description: Comparing Status code
				StringHandler.compare("Compare response code", iterationMap.get("STATUS_CODE"),
						responseMap.get("STATUS_CODE").toString());
				System.out.println(strStatusCode);

				// Description: Response comparison
				if (iterationMap.get("VERIFY_RESPONSE").equalsIgnoreCase("Y")
						&& responseMap.containsKey("RESPONSE_CONTENT")) {
					ResultUtil.reportResponse("INFO", responseMap.get("RESPONSE_CONTENT").toString());
					ResponseHandler.verifyResponse(iterationMap, responseMap);

				}
			}
			// Description: Close DB Connection
			DBHandler.closeConnection();
		} catch (Exception e) {
			log.error(e.getMessage());
		}

	}
}
