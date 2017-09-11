package httpUtil;

import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.http.client.utils.URIBuilder;
import org.apache.log4j.Logger;

import toolbox.ResultUtil;

public class URIUtil {

	static Logger log = Logger.getLogger(URIUtil.class.getName());

	/**
	 * Description : Method to construct URL without parameters
	 * 
	 * @author Mahesh Babu
	*/
	public static Map<String,String> getURLWithoutParameters(String strBaseUrl,
			String strPort,String strMethod,Map<String, String> upMap) {
		Map<String,String> urlMap=new HashMap();
		String strUrl = "";
		String strPath = "";
		String str = "";

		try {
			URIBuilder baseUri = new URIBuilder();
			baseUri.setScheme("http");
			baseUri.setHost(strBaseUrl);
			baseUri.setPort(Integer.parseInt(strPort));

			for (Entry<String, String> entry : upMap.entrySet()) {
				strPath = strPath + "/" + entry.getValue();
			}

			baseUri.setPath(strPath);
			str = baseUri.build().toASCIIString();
			strUrl = URLDecoder.decode(str, "ISO-8859-1");
			urlMap.put("DECODED_URL", strUrl);
			urlMap.put("URL", strUrl.replaceAll("\\(","%28").replaceAll("\\)", "%29"));
			ResultUtil.print("INFO", strMethod+"-"+strUrl);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return urlMap;
	}
}
