/*
 * FileName         : XMLUtil 
 * File Description : To get access of Result Util for reporting purpose
 * Company          : GAVS
 * Author           : UdayBhaskar Donda
 * Manager          : Anand Kumar M C
 * 
 */

package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringEscapeUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XMLUtil {
	//static Logger log = Logger.getLogger(XMLUtil.class.getName());

	public static Map elementsUpdate(String strXMLFilePath,
			Map<String, String> XpathList, Map<String, String> DataList) {
		Map resultMap = new HashMap();
		StringBuilder strActualXpaths = new StringBuilder();
		String strStatus = "Fail";
		String strResultDesc = "";
		SAXReader reader = new SAXReader();
		Document document;

		try {
			document = reader.read(strXMLFilePath);
			Iterator<String> dataKeys = DataList.keySet().iterator();
			strStatus = "Pass";
			String strNodeValue, strNodeValueKey;
			for (Entry entry : XpathList.entrySet()) {
				String strXpath = entry.getValue().toString();
				Node node = document.selectSingleNode(strXpath);
				strNodeValueKey = dataKeys.next();
				strNodeValue = DataList.get(strNodeValueKey);
				if (node != null) {
					strActualXpaths.append("[" + strXpath + "]");
					node.setText(strNodeValue);
				} else {
					strStatus = "Fail";
				}

			}
			OutputFormat format = OutputFormat.createPrettyPrint();
			XMLWriter writer = new XMLWriter(new FileWriter(strXMLFilePath),
					format);
			writer.write(document);
			writer.close();
			strResultDesc = "XML process:" + "\n" + "Expected Xpaths :"
					+ XpathList.values().toString() + "\n" + "Actual Xpaths :"
					+ strActualXpaths.toString() + "\n" + "Values :"
					+ DataList.values().toString();
		} catch (Exception e) {
			//log.error(e.getMessage());
			strResultDesc = "XML process:" + "\n" + "Expected Xpaths :"
					+ XpathList.values().toString() + "\n" + "Actual Xpaths :"
					+ strActualXpaths.toString() + "\n" + "Values :"
					+ DataList.values().toString();
		}

		resultMap.put("Status", strStatus);
		resultMap.put("Description", strResultDesc);
		return resultMap;
	}

	/**
	 * Description : To update an XML attribute.
	 * 
	 * @author Mahesh Babu
	*/
	public static Map attributesUpdate(String strXMLFilePath,
			Map<String, String> XpathList, Map<String, String> attributesMap,
			Map<String, Map<String, String>> DataList) {
		Map resultMap = new HashMap();
		StringBuilder strActualXpaths = new StringBuilder();
		String strStatus = "Fail";
		String strResultDesc = "";
		SAXReader reader = new SAXReader();
		Document document;

		try {
			document = reader.read(strXMLFilePath);
			strStatus = "Pass";
			String strNodeValue, strNodeValueKey;
			Iterator<String> dataListKeys = DataList.keySet().iterator();
			for (Entry entry : XpathList.entrySet()) {
				String strXpath = entry.getValue().toString();
				Element element = (Element) document.selectSingleNode(strXpath);
				if (element != null) {
					Map<String, String> dataMap = DataList.get(dataListKeys
							.next());
					Iterator<String> dataMapKeys = dataMap.keySet().iterator();
					for (Entry attribute : attributesMap.entrySet()) {
						String strAttributeName = attribute.getValue()
								.toString();
						String strAttributeValue = dataMap.get(dataMapKeys
								.next());
						element.addAttribute(strAttributeName,
								strAttributeValue);
					}
					strActualXpaths.append("[" + strXpath + "]");
				} else {
					strStatus = "Fail";
				}
			}
			OutputFormat format = OutputFormat.createPrettyPrint();
			XMLWriter writer = new XMLWriter(new FileWriter(strXMLFilePath),
					format);
			writer.write(document);
			writer.close();
			strResultDesc = "XML process:" + "\n" + "Expected Xpaths :"
					+ XpathList.values().toString() + "\n" + "Actual Xpaths :"
					+ strActualXpaths.toString();
		} catch (Exception e) {
			//log.error(e.getMessage());
			strResultDesc = "XML process:" + "\n" + "Expected Xpaths :"
					+ XpathList.values().toString() + "\n" + "Actual Xpaths :"
					+ strActualXpaths.toString();
		}

		resultMap.put("Status", strStatus);
		resultMap.put("Description", strResultDesc);
		return resultMap;
	}

	/**
	 * Description : To create XML document with ROOT node.
	 * 
	 * @author Mahesh Babu
	*/
	public static boolean createXmlWithRootNode(String strXMLFilePath,
			String strNodeName, String strNodeText) {
		boolean blResult = false;
		Document document;

		try {
			document = DocumentHelper.createDocument();
			Element node = document.addElement(strNodeName);
			node.addText(strNodeText);
			OutputFormat format = OutputFormat.createPrettyPrint();
			XMLWriter writer = new XMLWriter(new FileWriter(strXMLFilePath),
					format);
			writer.write(document);
			writer.close();
			blResult = true;
		} catch (IOException e) {
			//log.error(e.getMessage());
		}
		return blResult;
	}

	/*
	 * public static boolean addChildNodes(String strXMLFilePath,String
	 * strXpath,String strNodeName,Map<String,String> childNodesDetailsMap){
	 * boolean blResult=false; SAXReader reader = new SAXReader(); Document
	 * document; try{ document = reader.read(strXMLFilePath); Element
	 * parentElement = (Element) document.selectSingleNode(strXpath); Element
	 * childElement=parentElement.addElement(strNodeName); for(Entry
	 * entry:childNodesDetailsMap.entrySet()){ String
	 * strChildNodeName=entry.getKey().toString(); String
	 * strChildNodeText=MapUtil.getItemAsString(childNodesDetailsMap,
	 * strChildNodeName);
	 * childElement.addElement(strChildNodeName).addText(strChildNodeText); }
	 * OutputFormat format = OutputFormat.createPrettyPrint(); XMLWriter writer
	 * = new XMLWriter(new FileWriter(strXMLFilePath),format);
	 * writer.write(document); writer.close(); blResult=true; } catch(Exception
	 * e){ //e.printStackTrace(); } return blResult; }
	 */

	/**
	 * Description : To add text in XML node.
	 * 
	 * @author Mahesh Babu
	*/
	public static boolean addNodeWithText(String strXMLFilePath,
			String strXpath, String strNodeName, String strText) {
		boolean blResult = false;
		SAXReader reader = new SAXReader();
		Document document;
		FileInputStream in=null;
		try {
			in = new FileInputStream(new File(strXMLFilePath));
			Reader read = new InputStreamReader(in, "ISO-8859-1");
			 //document = reader.read(strXMLFilePath);
			document = reader.read(read);
			Element parentElement = (Element) document
					.selectSingleNode(strXpath);
			Element childElement = parentElement.addElement(strNodeName);
			childElement.addText(strText);
			OutputFormat format = OutputFormat.createPrettyPrint();
			// OutputFormat format =new OutputFormat();
			// format.setEncoding("UTF-8");
			XMLWriter writer = new XMLWriter(new FileWriter(strXMLFilePath),
					format);
			writer.write(document);
			writer.close();
			blResult = true;
		} catch (Exception e) {
			e.printStackTrace();
			//log.error(e.getMessage());
		}
		finally{
			try{in.close();}catch(Exception e){}
		}
		return blResult;
	}

	/**
	 * Description : To create XML document.
	 * 
	 * @author Mahesh Babu
	*/
	public static boolean createDocument(String strXMLFilePath, String strXML) {
		boolean blResult = false;
		SAXReader reader = new SAXReader();
		Document document;
		try {
			document = DocumentHelper.parseText(strXML);
			OutputFormat format = OutputFormat.createPrettyPrint();
			XMLWriter writer = new XMLWriter(new FileWriter(strXMLFilePath),
					format);
			writer.write(document);
			writer.close();
			blResult = true;
		} catch (Exception e) {
			//log.error(e.getMessage());
		}
		return blResult;
	}

	/**
	 * Description : To add text in XML node.
	 * 
	 * @author Mahesh Babu
	*/
	public static boolean addNodeText(String strXMLFilePath, String strXpath,
			String strText) {
		boolean blResult = false;
		SAXReader reader = new SAXReader();
		Document document;
		FileInputStream in=null;
		try {
			in = new FileInputStream(new File(strXMLFilePath));
			Reader read = new InputStreamReader(in, "ISO-8859-1");
			document = reader.read(read);
			//document = reader.read(strXMLFilePath);
			Element parentElement = (Element) document
					.selectSingleNode(strXpath);
			parentElement.clearContent();
			parentElement.setText(strText);
			OutputFormat format = OutputFormat.createPrettyPrint();
			XMLWriter writer = new XMLWriter(new FileWriter(strXMLFilePath),
					format);
			writer.write(document);
			writer.close();
			blResult = true;
		} 
		catch (Exception e) {
			//log.error(e.getMessage());
		}
		finally{
			try{in.close();}catch(Exception e){}
		}
		return blResult;
	}

	/**
	 * Description : To add node attributes.
	 * 
	 * @author Mahesh Babu
	*/
	public static boolean addNodeWithAttributes(String strXMLFilePath,
			String strXpath, String strNodeName,
			Map<String, String> attributesListMap) {
		boolean blResult = false;
		SAXReader reader = new SAXReader();
		Document document;
		FileInputStream in=null;
		try {
			in = new FileInputStream(new File(strXMLFilePath));
			Reader read = new InputStreamReader(in, "ISO-8859-1");
			//document = reader.read(strXMLFilePath);
			document = reader.read(read);
			Element parentElement = (Element) document
					.selectSingleNode(strXpath);
			Element childElement = parentElement.addElement(strNodeName);
			for (Entry entry : attributesListMap.entrySet()) {
				String strAttributeName = entry.getKey().toString();
				String strAttributeValue = entry.getValue().toString();
				childElement.addAttribute(strAttributeName, strAttributeValue);
			}
			OutputFormat format = OutputFormat.createPrettyPrint();
			XMLWriter writer = new XMLWriter(new FileWriter(strXMLFilePath),
					format);
			writer.write(document);
			writer.close();
			blResult = true;
		} catch (Exception e) {
			e.printStackTrace();
			//log.error(e.getMessage());
		}
		finally{
			try{in.close();}catch(Exception e){}
		}
		return blResult;
	}

	/**
	 * Description : To add node with attributes.
	 * 
	 * @author Mahesh Babu
	*/
	public static boolean addNodeWithAttributes(String strXMLFilePath,
			String strXpath, String strNodeName, String strText,
			Map<String, String> attributesListMap) {
		boolean blResult = false;
		SAXReader reader = new SAXReader();
		Document document;
		try {
			document = reader.read(strXMLFilePath);
			Element parentElement = (Element) document
					.selectSingleNode(strXpath);
			Element childElement = parentElement.addElement(strNodeName);
			childElement.addText(strText);

			for (Entry entry : attributesListMap.entrySet()) {
				String strAttributeName = entry.getKey().toString();
				String strAttributeValue = entry.getValue().toString();
				childElement.addAttribute(strAttributeName, strAttributeValue);
			}
			OutputFormat format = OutputFormat.createPrettyPrint();
			XMLWriter writer = new XMLWriter(new FileWriter(strXMLFilePath),
					format);
			writer.write(document);
			writer.close();
			blResult = true;
		} catch (Exception e) {
			//log.error(e.getMessage());
		}
		return blResult;
	}

	/**
	 * Description : To add XML node.
	 * 
	 * @author Mahesh Babu
	*/
	public static boolean addNode(String strXMLFilePath, String strXpath,
			String strNodeName) {
		boolean blResult = false;
		SAXReader reader = new SAXReader();
		Document document;
		try {
			document = reader.read(strXMLFilePath);
			Element parentElement = (Element) document
					.selectSingleNode(strXpath);
			Element childElement = parentElement.addElement(strNodeName);
			OutputFormat format = OutputFormat.createPrettyPrint();
			XMLWriter writer = new XMLWriter(new FileWriter(strXMLFilePath),
					format);
			writer.write(document);
			writer.close();
			blResult = true;
		} catch (Exception e) {
			//log.error(e.getMessage());
		}
		return blResult;
	}

	/**
	 * Description : To add text in XML node.
	 * 
	 * @author Mahesh Babu
	*/
	public static boolean addText(String strXMLFilePath, String strXpath,
			String strText) {
		boolean blResult = false;
		SAXReader reader = new SAXReader();
		Document document;
		FileInputStream in=null;
		
		try {
			in = new FileInputStream(new File(strXMLFilePath));
			Reader read = new InputStreamReader(in, "ISO-8859-1");
			
			//document = reader.read(strXMLFilePath);
			document = reader.read(read);
			Element parentElement = (Element) document
					.selectSingleNode(strXpath);
			parentElement.addText(strText);
			OutputFormat format = OutputFormat.createPrettyPrint();
			XMLWriter writer = new XMLWriter(new FileWriter(strXMLFilePath),
					format);
			writer.write(document);
			writer.close();
			blResult = true;
		} catch (Exception e) {
			//log.error(e.getMessage());
		}
		finally{
			try{in.close();}catch(Exception e){}
		}
		return blResult;
	}

	
	public static boolean addAttribute(String strXMLFilePath, String strXpath,
			String strAttributeName, String strAttributeValue) {
		boolean blResult = false;
		SAXReader reader = new SAXReader();
		Document document;
		FileInputStream in=null;
		try {
			in = new FileInputStream(new File(strXMLFilePath));
			Reader read = new InputStreamReader(in, "ISO-8859-1");
			document = reader.read(read);
			//document = reader.read(strXMLFilePath);
			Element parentElement = (Element) document
					.selectSingleNode(strXpath);
			parentElement.addAttribute(strAttributeName, strAttributeValue);
			OutputFormat format = OutputFormat.createPrettyPrint();
			XMLWriter writer = new XMLWriter(new FileWriter(strXMLFilePath),
					format);
			writer.write(document);
			writer.close();
			blResult = true;
		} catch (Exception e) {
			//log.error(e.getMessage());
		}
		finally{
			try{in.close();}catch(Exception e){}
		}
		return blResult;
	}

	
	public static boolean addAttributes(String strXMLFilePath, String strXpath,
			Map<String, String> attributesDetailsMap) {
		SAXReader reader = new SAXReader();
		Document document;
		FileInputStream in=null;
		try {
			in = new FileInputStream(new File(strXMLFilePath));
			Reader read = new InputStreamReader(in, "ISO-8859-1");
			document = reader.read(read);

			//document = reader.read(strXMLFilePath);

			String strAttributeName, strAttributeValue;
			Element element = (Element) document.selectSingleNode(strXpath);
			for (Entry entry : attributesDetailsMap.entrySet()) {
				strAttributeName = entry.getKey().toString();
				strAttributeValue = entry.getValue().toString();
				element.addAttribute(strAttributeName, strAttributeValue);
			}
			OutputFormat format = OutputFormat.createPrettyPrint();
			XMLWriter writer = new XMLWriter(new FileWriter(strXMLFilePath),
					format);
			writer.write(document);
			writer.close();
		} catch (Exception e) {
			//log.error(e.getMessage());
		}
		finally{
			try{in.close();}catch(Exception e){}
		}
		return true;
	}
	
	/**
	 * Description : To get attribute value for an XML node.
	 * 
	 * @author Mahesh Babu
	*/
	public static String getAttributeValue(String strXML, String strXpath,String strAttributeName) {
		SAXReader reader = new SAXReader();
		Document document;
		String strAttributeValue="";
		
		try {
			document = reader.read(new StringReader(strXML));  
			Element element = (Element) document.selectSingleNode(strXpath);
			strAttributeValue=element.attributeValue(strAttributeName);
		} catch (Exception e) {
			//e.printStackTrace();
			//log.error(e.getMessage());
		}
		finally{
			
		}
		
		return strAttributeValue;
	}

	/**
	 * Description : To normalize XML.
	 * 
	 * @author Mahesh Babu
	*/
	public static String normalizeXML(String strText) {
		String strNormalizedText = StringEscapeUtils.escapeXml(strText);
		return strNormalizedText;
	}
	
	/**
	 * Description : To get the inner text of a node
	 * 
	 * @author Mahesh Babu
	*/
	
	@SuppressWarnings("unchecked")
	public static String getNodeText(String strXML,String strXPath) {
		String strText = "";
		Document document;

		try {
			document = DocumentHelper.parseText(strXML.replaceAll("\\s+xmlns\\s*(:\\w)?\\s*=\\s*\\\"(?<url>[^\\\"]*)\\\"",""));
			if((strXPath!=null)&&(strXML!=null)){
			List<Element> elementList=document.selectNodes(strXPath);
			
			strText=elementList.get(0).getText();}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strText;
	}
	
	/**
	 * Description : To get node as XML
	 * 
	 * @author Mahesh Babu
	*/
	public static String getNodeXML(String strXML,String strXPath) {
		String strResultXML = "";
		Document document;

		try {
			document = DocumentHelper.parseText(strXML.replaceAll("\\s+xmlns\\s*(:\\w)?\\s*=\\s*\\\"(?<url>[^\\\"]*)\\\"",""));
			if((strXPath!=null)&&(strXML!=null)){
			Element element=(Element)document.selectSingleNode(strXPath);
			
			strResultXML=element.asXML();
			}
		} catch (Exception e) {
		}
		
		return strResultXML;
	}

    public static String getText(String strXMLFilePath, String strXpath) 
    {
          String str = null;
          SAXReader reader = new SAXReader();
          Document document;
          FileInputStream in = null;

          try {
                in = new FileInputStream(new File(strXMLFilePath));
                Reader read = new InputStreamReader(in, "UTF-8");

                // document = reader.read(strXMLFilePath);
                document = reader.read(read);
                Element parentElement = (Element) document
                            .selectSingleNode(strXpath);
                str = parentElement.getText();
                //System.out.println("String: "+str);
          } catch (Exception e) {
                System.out.println("Warning: Exception Occured:"+e.getMessage());
          } finally {
                try {
                      in.close();
                } catch (Exception e) {
                      System.out.println("Warning: Exception Occured:"+e.getMessage());
                }
          }
          return str;
    }

}
