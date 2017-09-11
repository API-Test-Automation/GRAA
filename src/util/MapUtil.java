package util;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;

import toolbox.DBHandler;

public class MapUtil {
	static Logger log = Logger.getLogger(MapUtil.class.getName());
	
	/**
	 * Description : To get URL path
	 * 
	 * @author Mahesh Babu
	*/
	public static Map<String, String> getUrlPathMap(Map<String, String> dataMap) {
		Map<String, String> upMap = new LinkedHashMap();

		try {
			for (Entry<String, String> entry : dataMap.entrySet()) {
				String strKey = entry.getKey();
				String strValue = null;
				try {
					strValue = entry.getValue();
				} catch (Exception e) {
				}

				if (strKey.startsWith(Constants.strURLParameterPrefix)) {
					if (!(strValue == null)) {
						upMap.put(strKey, strValue);
					}
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return upMap;
	}

	/**
	 * Description : To get expected reponse as Map
	 * 
	 * @author Mahesh Babu
	*/
	public static Map<String, String> getExpectedResponseMap(
			Map<String, String> dataMap) {
		Map<String, String> responseMap = new LinkedHashMap();

		try {
			for (Entry<String, String> entry : dataMap.entrySet()) {
				String strKey = entry.getKey();
				String strValue = null;
				try {
					strValue = entry.getValue();
				} catch (Exception e) {
				}

				if (strKey.startsWith(Constants.strResponsePrefix)) {
					if (!(strValue == null)) {
						responseMap.put(strKey.replaceAll(Constants.strResponsePrefix, ""), strValue);
					}
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return responseMap;
	}

	/**
	 * Description : To normalize Input Map
	 * 
	 * @author Mahesh Babu
	*/
	public static Map<String, String> normalizeMap(
			Map<String, String> iterationMap) {
		Map<String, String> innerMap = iterationMap;

		int incount = iterationMap.size();

		try {
			for (Entry entry1 : iterationMap.entrySet()) {
				String strName = "<<" + entry1.getKey() + ">>";
				String strValue = "";
				try {
					strValue = entry1.getValue().toString();
				} catch (Exception e) {
				}

				for (Entry entry2 : innerMap.entrySet()) {
					String strInnerName = entry2.getKey().toString();
					String strInnerValue = "";
					try {
						strInnerValue = entry2.getValue().toString();
					} catch (Exception e) {
					}

					if (strInnerValue.contains(strName)) {
						strInnerValue = strInnerValue.replaceAll(strName,
								strValue);
						innerMap.put(strInnerName, strInnerValue);
					}
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return innerMap;
	}

	public static Map<String, String> normalizeRandomData(
			Map<String, String> iterationMap) {
		Map<String, String> innerMap = new LinkedHashMap();

		try {
			for (Entry entry : iterationMap.entrySet()) {
				String strName = entry.getKey().toString();
				String strValue = entry.getValue().toString();

				if (strValue.equalsIgnoreCase(Constants.strRandomEmail)) {
					strValue = RandomStringUtils.randomAlphanumeric(10) + "@"
							+ RandomStringUtils.randomAlphanumeric(5) + ".com";
				} else if (strValue.equalsIgnoreCase(Constants.strRandomFirstName)) {
					strValue = RandomStringUtils.randomAlphanumeric(10);
				} else if (strValue.equalsIgnoreCase(Constants.strRandomLastName)) {
					strValue = RandomStringUtils.randomAlphanumeric(10);
				} else if(strValue.equalsIgnoreCase(Constants.strRandomNumber)){
					strValue = RandomStringUtils.randomNumeric(10);
				} else if(strValue.equalsIgnoreCase(Constants.strRandomAlpha)){
					strValue = RandomStringUtils.randomAlphabetic(4);
				}

				innerMap.put(strName, strValue);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return innerMap;
	}

	private static Map<String, String> OLD_normalizeConfigData(
			Map<String, String> iterationMap, Map<String, String> configMap) {
		Map<String, String> innerMap = new LinkedHashMap();

		try {
			for (Entry entry : iterationMap.entrySet()) {
				String strName = entry.getKey().toString();
				String strValue = entry.getValue().toString();

				if (strValue.startsWith(Constants.strCFGPrefix)) {
					String strKey = strValue.replaceAll(Constants.strCFGPrefix, "")
							.replaceAll(">>", "").trim();
					strValue = configMap.get(strKey);
				}

				innerMap.put(strName, strValue);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return innerMap;
	}
	
	private static Map<String, String> normalizeConfigData(
			Map<String, String> iterationMap, Map<String, String> configMap) {
		Map<String, String> innerMap = new LinkedHashMap();
		Pattern p = Pattern.compile("(<<CFG_.*>>)");
		
		try {
			for (Entry entry : iterationMap.entrySet()) {
				String strName = entry.getKey().toString();
				String strValue = entry.getValue().toString();

				/*if (strValue.startsWith(Constants.strCFGPrefix)) {
					String strKey = strValue.replaceAll(Constants.strCFGPrefix, "")
							.replaceAll(">>", "").trim();
					strValue = configMap.get(strKey);
				}*/
				Matcher m = p.matcher(strValue);
				if(m.find()){
					String strConfigVariable=m.group();
					String strKey = strConfigVariable.replaceAll(Constants.strCFGPrefix, "")
							.replaceAll(">>", "").trim();
					String strConfigValue = configMap.get(strKey);
					
					strValue=strValue.replaceAll(strConfigVariable, strConfigValue);
				}
				innerMap.put(strName, strValue);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return innerMap;
	}

	/**
	 * Description : To normalize DB Map
	 * 
	 * @author Mahesh Babu
	*/
	public static Map<String, String> normalizeDBMap(
			Map<String, String> configMap, Map<String, String> iterationMap) {
		if (iterationMap.containsKey("DB_INPUT")) {
			String strQuery = iterationMap.get("DB_INPUT");
			iterationMap = DBHandler.addDBInput(strQuery, iterationMap);
		}
		return normalizeConfigData(normalizeRandomData(iterationMap), configMap);
	}

	/**
	 * Description : To get Map item as String
	 * 
	 * @author Mahesh Babu
	*/
	public static String getItemAsString(Map<String, String> map, String strKey) {
		String strValue;
		try {
			strValue = map.get(strKey).trim().toString();
		} catch (Exception e) {
			log.error(e.getMessage());
			strValue = "";
		}
		return strValue;
	}

	/**
	 * Description : To remove item from Map.
	 * 
	 * @author Mahesh Babu
	*/
	public static void removeItem(Map<String, String> map, String strKey) {
		if (map.containsKey(strKey)) {
			map.remove(strKey);
		}
	}
}
