package httpUtil;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import toolbox.ResultUtil;

public class HTTPPostUtil {
	
	static Logger log = Logger.getLogger(HTTPPutUtil.class.getName());
	
	/**
	 * Description : WebService put method with Request body
	 * 
	 * @author Mahesh Babu
	*/
	public static Map put(String strURL, String strRequestBody) {
		Map responseMap = new HashMap();
		CloseableHttpClient httpclient = null;
		try {
			responseMap.put("STATUS_CODE", "000");
			httpclient = HttpClients.createDefault();
			HttpPost request = new HttpPost(strURL);

			if ((strRequestBody == null)) {
				strRequestBody = "";
			} else {
				ResultUtil.print("INFO", "Request Body: " + strRequestBody);
			}

			StringEntity input = new StringEntity(strRequestBody);
			
			//Headers
			request.setHeader("Authorization","wiley_signature_method=\"HMAC-SHA1\",wiley_timestamp=\"1387808818\",wiley_nonce=\"3DEBB8C1983\",wiley_signature=\"HQ9r27QJ+lGzVHzhw/Pd4RjSjFQ=\"");
			request.setHeader("Content-Type","application/xml");
			CloseableHttpResponse response = httpclient.execute(request);

			responseMap.put("STATUS_CODE", response.getStatusLine()
					.getStatusCode());
			responseMap.put("RESPONSE_CONTENT",
					new String(EntityUtils.toByteArray(response.getEntity())));
			httpclient.close();
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
}



