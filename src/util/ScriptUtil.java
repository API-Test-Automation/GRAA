package util;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;






/**
 * Description : Functional Test Script
 * 
 * @author Mahesh Babu
 */
//public class ScriptUtil  {
//	static Logger log = Logger.getLogger(ScriptUtil.class.getName());

	/**
	
	 * 
	 * @author Mahesh Babu
	 */
	/*public Map<String, Map<String, String>> getData(String strDatapool) {
		int inCounter = 1;

		Map<String, Map<String, String>> dataMap = new LinkedHashMap();

		try {
			
			
			IDatapool dp = (IDatapool) dpFactory().load(dpFile, true);
			IDatapoolIterator dpIter = dpFactory().open(dp, null);
			dpIter.dpInitialize(dp);
			dpIter.dpReset();

			while (!dpIter.dpDone()) {
				dpIter.dpCurrent();
				int inVariableCount = dpIter.getDatapool().getVariableCount();
				Map<String, String> rowMap = new LinkedHashMap();

				for (int inVariable = 0; inVariable <= inVariableCount - 1; inVariable++) {
					String strVariableName = dpIter.getDatapool()
							.getVariable(inVariable).getName();
					String strValue = null;

					try {
						strValue = dpIter.dpString(strVariableName);

					} catch (Exception e) {
					}

					if (!(strValue == null)) {
						if (strValue.trim().length() > 0) {
							rowMap.put(strVariableName, strValue);
						}
					}
				}

				if (rowMap.get("EXECUTE").equalsIgnoreCase("Y")) {
					dataMap.put("" + inCounter, rowMap);
				}
				dpIter.dpNext();
				inCounter++;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			
		}
		return dataMap;
	}*/
	
	/**
	 * Description : Reterive Suite details from Excel
	 * 
	 * @author Mahesh Babu
	 */
	/*public Map<String, Map<String, String>> getSuite(String strDatapool) {
		int inCounter = 1;

		Map<String, Map<String, String>> dataMap = new LinkedHashMap();

		try {
			System.setProperty("file.encoding", "ISO-8859-1");
			java.io.File dpFile = new java.io.File(
					(String) ScriptUtil.getOption(IOptionName.DATASTORE),
					strDatapool + ".rftdp");
			IDatapool dp = (IDatapool) dpFactory().load(dpFile, true);
			IDatapoolIterator dpIter = dpFactory().open(dp, null);
			dpIter.dpInitialize(dp);
			dpIter.dpReset();

			while (!dpIter.dpDone()) {
				dpIter.dpCurrent();
				int inVariableCount = dpIter.getDatapool().getVariableCount();
				Map<String, String> rowMap = new LinkedHashMap();

				for (int inVariable = 0; inVariable <= inVariableCount - 1; inVariable++) {
					String strVariableName = dpIter.getDatapool()
							.getVariable(inVariable).getName();
					String strValue = "";

					try {strValue = dpIter.dpString(strVariableName);} catch (Exception e) {}
					
					if(strValue==null){rowMap.put(strVariableName, "");}else{rowMap.put(strVariableName, strValue);}
				}

				if (rowMap.get("EXECUTE").equalsIgnoreCase("Y")) {
					dataMap.put("" + inCounter, rowMap);
				}
				dpIter.dpNext();
				inCounter++;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			
		}
		return dataMap;
	}
*/
	/**
	 * Description : Reterive config data from Excel
	 * 
	 * @author Mahesh Babu
	*/
/*	public Map<String, String> getConfig(String strDatapool) {

		Map<String, String> configMap = new LinkedHashMap();

		try {
			java.io.File dpFile = new java.io.File(
					(String) ScriptUtil.getOption(IOptionName.DATASTORE),
					strDatapool + ".rftdp");
			IDatapool dp = (IDatapool) dpFactory().load(dpFile, true);
			IDatapoolIterator dpIter = dpFactory().open(dp, null);
			dpIter.dpInitialize(dp);
			dpIter.dpReset();

			while (!dpIter.dpDone()) {
				dpIter.dpCurrent();
				int inVariableCount = dpIter.getDatapool().getVariableCount();
				Map<String, String> rowMap = new LinkedHashMap();

				for (int inVariable = 0; inVariable <= inVariableCount - 1; inVariable++) {
					String strVariableName = dpIter.getDatapool()
							.getVariable(inVariable).getName();
					String strValue = null;
					try {
						strValue = dpIter.dpString(strVariableName);
					} catch (Exception e) {
					}
					if (!(strValue == null)) {
						if (strValue.trim().length() > 0) {
							rowMap.put(strVariableName, strValue);
						}
					}
				}

				if (rowMap.get("ACTIVE").equalsIgnoreCase("Y")) {
					configMap = rowMap;
					break;
				}
				dpIter.dpNext();
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			
		}
		return configMap;
	}}
	*/

