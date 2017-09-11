package httpUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;



public class HTTPGetUtil {

	static Logger log = Logger.getLogger(HTTPGetUtil.class.getName());

	/**
	 * Description : WebService Get method with header
	 * 
	 * @author Mahesh Babu
	*/
	public static Map get(String strURL, Map<String, String> headerMap) {
		Map responseMap = new HashMap();
		CloseableHttpClient httpclient = null;

		try {
			responseMap.put("STATUS_CODE", "000");
			httpclient = HttpClients.createDefault();
			HttpGet request = new HttpGet(strURL);

			for (Entry<String, String> entry : headerMap.entrySet()) {
				String strHeaderName = entry.getKey();
				String strHeaderValue = entry.getValue();
				request.addHeader(strHeaderName, strHeaderValue);
			}
			CloseableHttpResponse response = httpclient.execute(request);
			responseMap.put("STATUS_CODE", response.getStatusLine()
					.getStatusCode());
			responseMap.put("RESPONSE_CONTENT",
					new String(EntityUtils.toByteArray(response.getEntity()),"UTF-8"));
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			closeClient(httpclient);
		}
		return responseMap;
	}

	private static void closeClient(CloseableHttpClient httpclient) {
		try {
			httpclient.close();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	/**
	 * Description : WebService Get method without header
	 * 
	 * @author Mahesh Babu
	*/
	public static Map get(String strURL) {
		Map responseMap = new HashMap();
		CloseableHttpClient httpclient = null;

		try {
			responseMap.put("STATUS_CODE", "000");
			httpclient = HttpClients.createDefault();
			HttpGet request = new HttpGet(strURL);
			CloseableHttpResponse response = httpclient.execute(request);
			responseMap.put("STATUS_CODE", response.getStatusLine()
					.getStatusCode());
			String strResponse = new String(EntityUtils.toByteArray(response
					.getEntity()), "UTF-8");
			responseMap.put("RESPONSE_CONTENT", strResponse);
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			closeClient(httpclient);
		}
		return responseMap;
	}
}
