package toolbox;

import httpUtil.HTTPGetUtil;

import java.sql.*;
import java.util.Map;
import org.apache.log4j.Logger;
import util.MapUtil;
import toolbox.ResultUtil;
import com.sybase.jdbc3.jdbc.SybDriver;

public class DBHandler {
	
	private static Connection con = null;
	private static Statement stmt = null;
	private static SybDriver sybDriver = null;
	static Logger log = Logger.getLogger(
			DBHandler.class.getName());
	
	/**
	 * Description : To connect DB
	 * 
	 * @author Mahesh Babu
	*/
	public static boolean getConnection(String strTimeout,String strDBName,String strDBURL,String strUserName,String strPassword){
		boolean blResult=false;
		try {
			sybDriver=(SybDriver)Class.forName(strDBName).newInstance();
			DriverManager.setLoginTimeout(Integer.parseInt(strTimeout));
			con = DriverManager.getConnection(strDBURL, strUserName, strPassword);
			stmt = con.createStatement();
			blResult=true;
		} 
		catch (Exception e) {
			log.error(e.getMessage());
			ResultUtil.print("FAIL", "Unable to connect DB");
		}
		return blResult;
	}
	
	/**
	 * Description : To close DB connection
	 * 
	 * @author Mahesh Babu
	*/
	public static void closeConnection(){
		try{
			con.close();
		}
		catch(Exception e){
			log.error(e.getMessage());
		}
	}
	
	/**
	 * Description : To execute DB query
	 * 
	 * @author Mahesh Babu
	*/
	public static void runQuery(String strQueryInfo){
		String strQuery=strQueryInfo.split(":=")[1];
		ResultSet rs = null;
		
		try{
			int count = 0;
			rs = stmt.executeQuery(strQuery);
			while (rs.next()) {
			    ++count;
			}
			ResultUtil.print("PASS", "Executed Query : "+strQuery+" No. of Rows:"+count);
		}
		catch(Exception e){
			log.error(e.getMessage());
			ResultUtil.print("FAIL", "Unable to execute query : "+strQuery);
		}
		finally{
			closeRecordset(rs);
		}
	}
	
	private static void closeRecordset(ResultSet rs){
		try{
			rs.close();
		}
		catch(Exception e){
			log.error(e.getMessage());
		}
	}
	
	/**
	 * Description : To compare DB values with response
	 * 
	 * @author Mahesh Babu
	*/
	public static void compareValue(String strQueryDetails){
		String strQueryInfo=strQueryDetails.split(":=")[1];
		String strQuery=strQueryInfo.split("\\|")[0];
		String strColumnName=strQueryInfo.split("\\|")[1];
		String strExpectedValue=strQueryInfo.split("\\|")[2];
		String strActualValue=null;
		ResultSet rs = null;
		
		try{
			rs = stmt.executeQuery(strQuery);
			int inColType=getColumnType(rs,strColumnName);
			rs.next();
			
			if(inColType==Types.VARCHAR || inColType == Types.CHAR){
				strActualValue=rs.getString(strColumnName);
			}
			else if(inColType==Types.TINYINT){
				strActualValue=""+rs.getByte(strColumnName);
			}
			else if(inColType==Types.INTEGER){
				strActualValue=""+rs.getInt(strColumnName);
			}
			else if(inColType==Types.DATE){
				strActualValue=""+rs.getInt(strColumnName);
			}	
			else if(inColType==Types.SMALLINT){
				strActualValue=""+rs.getInt(strColumnName);
			}
			
			if(strActualValue==null){
				ResultUtil.print("FAIL", "Unable to compare value Query: "+strQuery+" value is not returned from "+strColumnName+" column");
			}
			else{
				if(strExpectedValue.equals(strActualValue)){
					ResultUtil.print("PASS", "Query:" +strQuery, strExpectedValue, strExpectedValue);
					//ResultUtil.print("PASS", "Compared value Expected: "+strExpectedValue+" Actual: "+strActualValue+" Query:"+strQuery);
				}
				else{
					ResultUtil.print("FAIL", "Query:" +strQuery, strExpectedValue, strExpectedValue);
					//ResultUtil.print("FAIL", "Compared value Expected: "+strExpectedValue+" Actual: "+strActualValue+" Query:"+strQuery);
				}
			}
		}
		catch(Exception e){
			log.error(e.getMessage());
			ResultUtil.print("FAIL", "Unable to execute query : "+strQuery);
		}
		finally{
			closeRecordset(rs);
		}
	}
	
	private static int getColumnType(ResultSet rs,String strColumnName){
		int inColNo = 0;
		try{
			ResultSetMetaData rsmd = rs.getMetaData();
			int NumOfCol = rsmd.getColumnCount();
			
			for(int i=1;i<=NumOfCol;i++){
				if(rsmd.getColumnName(i).equalsIgnoreCase(strColumnName)){
					inColNo=rsmd.getColumnType(i);
				}
			}
		}
		catch(Exception e){
			log.error(e.getMessage());
		}
		return inColNo;
	}
	
	/**
	 * Description : To get data from DB
	 * 
	 * @author Mahesh Babu
	*/
	public static Map<String,String> addDBInput(String strQuery,Map<String,String> iterationMap){
		ResultSet rs = null;
		try{
			rs = stmt.executeQuery(strQuery);
			ResultSetMetaData metaData=rs.getMetaData();
			int inColsCount=metaData.getColumnCount();
			rs.next();
			
			for(int inCol=1;inCol<=inColsCount;inCol++){
				if(!(iterationMap.containsKey("DB_"+metaData.getColumnName(inCol)))){
					String strValue="";
					try{strValue=rs.getObject(inCol).toString();}catch(Exception e){}
					iterationMap.put("DB_"+metaData.getColumnName(inCol), strValue);
				}
			}
			rs.close();
		}
		catch(Exception e){
			log.error(e.getMessage());
		}
		finally{
			closeRecordset(rs);
		}
		return MapUtil.normalizeMap(iterationMap);
	}
}
