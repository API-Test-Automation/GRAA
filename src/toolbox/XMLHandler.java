package toolbox;

import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.dom4j.Element;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.mycila.xmltool.XMLDoc;
import com.mycila.xmltool.XMLTag;



public class XMLHandler {
	static Logger log = Logger.getLogger(
			XMLHandler.class.getName());
	private static String strXMLNameSpace="http://www.w3.org/2005/Atom";
	
	/*//Description: Verifies xml tag is present 
	public static boolean verifyXPath(String strXMLTagName,String strXML,String strXPath){
		boolean blResult=false;
		try{
			strXPath=strXPath.split(":=")[1].toString();
			String strNormalizedXpath=normalizeXPath(strXPath);
			XMLTag doc=XMLDoc.from(strXML,false);
			String ns = doc.getPefix(strXMLNameSpace);
			XMLTag node=doc.gotoTag(strNormalizedXpath, ns);
			
			if(!(node==null)){
				ResultUtil.print("PASS", "<<"+strXPath+">> tag is found");
			}
			else{
				ResultUtil.print("FAIL", "<<"+strXPath+">> tag is not found");
			}
		}
		catch(Exception e){
			ResultUtil.print("FAIL", "<<"+strXPath+">> tag is not found");
		}
		return blResult;
	}*/
	
	/**
	 * Description : To verify presence of an XML tag using XPath 
	 * 
	 * @author Mahesh Babu
	*/
	public static boolean verifyXPath(String strXMLTagName,String strXML,String strXPath){
		boolean blResult=false;
		try{
			Document document;
			
			strXML=removeXmlStringNamespaceAndPreamble(strXML);
			
			String strNormalizedXPath=strXPath.split(":=")[1].toString();
		   
		    document = new SAXReader().read(new StringReader(strXML));
		    
			if(document.selectNodes(strNormalizedXPath).size()>0){
				ResultUtil.print("PASS", "Verify XML Tag",strXPath+" tag(s) should be present",strXPath+" tag(s) is present as expected.");
			}
			else{
				ResultUtil.print("FAIL", "Verify XML Tag",strXPath+" tag(s) should be present",strXPath+" tag(s) is not present.");
			}
		}
		catch(Exception e){
			log.error(e.getMessage());
			ResultUtil.print("FAIL", "Verify XML Tag",strXPath+" tag(s) should be present",strXPath+" tag(s) is not present.");
		}
		return blResult;
	}
	
	/**
	 * Description : To verify non-presence of an XML tag using XPath 
	 * 
	 * @author Mahesh Babu
	*/
	public static boolean notVerifyXPath(String strXMLTagName,String strXML,String strXPath){
		boolean blResult=false;
		strXPath=strXPath.split(":=")[1].toString();
		String strNormalizedXpath=normalizeXPath(strXPath);
		XMLTag doc=XMLDoc.from(strXML,false);
		String ns = doc.getPefix(strXMLNameSpace);
		XMLTag node=null;	
		try{
			node=doc.gotoTag(strNormalizedXpath, ns);	
		}
		catch(Exception e){
			log.error(e.getMessage());
		}
		
		if(node==null){
			ResultUtil.print("PASS", "Not Verify XML Tag",strXPath+" should not be present","It is not present as expected.");
		}
		else{
			ResultUtil.print("FAIL", "Not Verify XML Tag",strXPath+" should not be present","It is present.");
		}
		
		return blResult;
	}
	
	private static String normalizeXPath(String strXPath){
		if(strXPath.contains("atom:")){
			strXPath=strXPath.replaceAll("atom:", "\\%1\\$s:");
		}
		return strXPath;
	}
	
	/**
	 * Description : To compare sub XML in main XML.
	 * 
	 * @author Mahesh Babu
	*/
	public static void compareSubXML(String strXML,String strSubXMLInfo){
		String strSubXML=strSubXMLInfo.split(":=")[1];
		String strReplaceXml=strSubXML.replaceAll("[\\t\\n\\r]","").replaceAll(">\\s+<", "><");
		System.out.println(strReplaceXml);		
		if(strXML.contains(strReplaceXml)){
			ResultUtil.print("PASS", "Verify XML Tag",strReplaceXml+" tag(s) should be present",strReplaceXml+" tag(s) is present as expected.");
		}
		else{
			ResultUtil.print("FAIL", "Verify XML Tag",strReplaceXml+" tag(s) should be present",strReplaceXml+" tag(s) is not present.");
		}
	}
	
	/**
	 * Description : To verify sub XML non presence in main XML.
	 * 
	 * @author Mahesh Babu
	*/
	public static void notCompareSubXML(String strXML,String strSubXMLInfo){
		String strSubXML=strSubXMLInfo.split(":=")[1];
		
		if(!(strXML.contains(strSubXML))){
			ResultUtil.print("PASS", "Verify XML Tag",strSubXML+" tag(s) should not be present",strSubXML+" tag(s) is not present as expected.");
		}
		else{
			ResultUtil.print("FAIL", "Verify XML Tag",strSubXML+" tag(s) should not be present",strSubXML+" tag(s) is present.");
		}
	}
	
	private static String removeXmlStringNamespaceAndPreamble(String xmlString) {
		  return xmlString.replaceAll("(<\\?[^<]*\\?>)?", ""). /* remove preamble */
		  replaceAll(" xmlns.*?(\"|\').*?(\"|\')", "") /* remove xmlns declaration */
		  .replaceAll("(<)(\\w+:)(.*?>)", "$1$3") /* remove opening tag prefix */
		  .replaceAll("(</)(\\w+:)(.*?>)", "$1$3"); /* remove closing tags prefix */
		}
	
	/**
	 * Description : To verify XML data in Descending Order
	 * 
	 * @author Mahesh Babu
	*/
	public static void verifyDescendingOrder(String strXML,String strXPath){
		
		ArrayList<String> valueList=new ArrayList();
		String strInnerXPath="";
		
		try{
			Document document;
			
			strXML=removeXmlStringNamespaceAndPreamble(strXML);
			strXPath=strXPath.split(":=")[1].toString();
			
			strInnerXPath=strXPath.split("\\|")[0];
			String strValueType=strXPath.split("\\|")[1];
		   
		    document = new SAXReader().read(new StringReader(strXML));
		    
			Iterator elements = document.selectNodes(strInnerXPath).iterator();
			
			while(elements.hasNext()){
				Element element=(Element) elements.next();
				String strValue="";
				
				if(strValueType.equalsIgnoreCase("TEXT")){
					strValue=element.getText();
				}
				else{
					strValue=element.attributeValue(strValueType);
				}
				valueList.add(removeMonth(strValue));
			}
			
			if(validSort(valueList,false)){
				ResultUtil.print("PASS", "Descending Order validation",strInnerXPath+" tag(s) values should be in descending order","Values are in descending order. Values:"+valueList);
			}
			else{
				ResultUtil.print("FAIL", "Descending Order validation",strInnerXPath+" tag(s) values should be in descending order","Values are not in descending order. Values:"+valueList);
			}
		}
		catch(Exception e){
			log.error(e.getMessage());
			ResultUtil.print("FAIL", "Descending Order validation",strInnerXPath+" tag(s) values should be in descending order","Unable to do sorting");
		}
	}
	
	/**
	 * Description : To verify XML data in Ascending Order
	 * 
	 * @author Mahesh Babu
	*/
	public static void verifyAscendingOrder(String strXML,String strXPath){
			
			ArrayList<String> valueList=new ArrayList();
			String strInnerXPath="";
			
			try{
				Document document;
				
				strXML=removeXmlStringNamespaceAndPreamble(strXML);
				strXPath=strXPath.split(":=")[1].toString();
				
				strInnerXPath=strXPath.split("\\|")[0];
				String strValueType=strXPath.split("\\|")[1];
			   
			    document = new SAXReader().read(new StringReader(strXML));
			    
				Iterator elements = document.selectNodes(strInnerXPath).iterator();
				
				while(elements.hasNext()){
					Element element=(Element) elements.next();
					String strValue="";
					
					if(strValueType.equalsIgnoreCase("TEXT")){
						strValue=element.getText();
					}
					else{
						strValue=element.attributeValue(strValueType);
					}
					valueList.add(removeMonth(strValue));
				}
				
				if(validSort(valueList,true)){
					ResultUtil.print("PASS", "Ascending Order validation",strInnerXPath+" tag(s) values should be in ascending order","Values are in ascending order. Values:"+valueList);
				}
				else{
					ResultUtil.print("FAIL", "Ascending Order validation",strInnerXPath+" tag(s) values should be in ascending order","Values are not in ascending order. Values:"+valueList);
				}
			}
			catch(Exception e){
				log.error(e.getMessage());
				ResultUtil.print("FAIL", "Ascending Order validation",strInnerXPath+" tag(s) values should be in ascending order","Unable to do sorting");
			}
		}
	
	private static boolean validSort(ArrayList<String> unsortedList, boolean ascendingOrder) {
		boolean isValid = false;
		ArrayList copyOfList = new ArrayList();
		copyOfList.addAll(unsortedList);
		 
		if (ascendingOrder) {
		    //Sorting ArrayList in ascending Order in Java 
		Collections.sort(copyOfList); 
			//System.out.println("Sorted ArrayList in Java - Ascending order : " + copyOfList); 
		} else {
		       //Sorting ArrayList in descending order in Java 
		       Collections.sort(copyOfList, Collections.reverseOrder()); 
		      // System.out.println("Sorted ArrayList in Java - Descending order : " + copyOfList);
		}
		   
		   if (unsortedList.hashCode() == copyOfList.hashCode()) {
		    isValid = true;
		   } else {
		    isValid =  false;
		   }

		return isValid;
		} 
	
	private static String removeMonth(String strValue){
		Map<String,String> monthMap=new HashMap();
		
		monthMap.put("January", "1");
		monthMap.put("February", "2");
		monthMap.put("March", "3");
		monthMap.put("April", "4");
		monthMap.put("May", "5");
		monthMap.put("June", "6");
		monthMap.put("July", "7");
		monthMap.put("August", "8");
		monthMap.put("September", "9");
		monthMap.put("October", "10");
		monthMap.put("November", "11");
		monthMap.put("December", "12");
		monthMap.put("Article", "");
		monthMap.put("image", "");
		
		for(Entry entry:monthMap.entrySet()){
			String strMonth=entry.getKey().toString();
			if(strValue.startsWith(strMonth)){
				strValue=strValue.replaceAll(strMonth, "").trim();
			}
		}
		return strValue;
		
	}
}
