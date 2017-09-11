
/*
 * FileName         : PropertyUtil 
 * File Description : To get access of Property to invoke the application
 * Company          : GAVS
 * Author           : UdayBhaskar Donda
 * Manager          : Anand Kumar M C
 * 
 */

package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

public class PropertyUtil {
	public static Map<String, String> getPropertyFileAsHashmap(String strPropertyFilePath) {
		Properties prop = new Properties();
		Map<String, String> map = new HashMap<String, String>();
		try {
			prop.load(new FileInputStream(strPropertyFilePath));
			Iterator<?> iter = prop.entrySet().iterator();
			do {
				if (!iter.hasNext())
					break;
				Entry<?, ?> entry = (Entry<?, ?>) iter.next();
				map.put(entry.getKey().toString(), entry.getValue().toString());
			} while (true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
}
