/*
 * FileName         : FileUtil 
 * File Description : To get access of files
 * Author           :Mahesh Babu
 * 
 */
package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {
	public static String getCanonicalPath(){
		String strResult="";
		try{
			strResult=new java.io.File(".").getCanonicalPath();
		}
		catch(Exception e){}
		return strResult;
	}
	public static boolean verifyFileExist(String strFilePath){
		File objFile=new File(strFilePath);
		return objFile.exists();
	}
	
	public static boolean createFolder(String strFolderPath){
		return new File(strFolderPath).mkdir();
	}
	
	public static boolean createFile(String strFilePath){
		boolean blResult = false;
		try {
			blResult= new File(strFilePath).createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return blResult;
	}
	
	public static boolean createFileWithContent(String strFilePath,String strContent){
		boolean blResult = false;
		try {
			File file = new File(strFilePath);
			BufferedWriter output = new BufferedWriter(new FileWriter(file));
	        output.write(strContent);
	        output.close();
	        blResult = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return blResult;
	}
	
	public static boolean renameFile(String strPath1,String strPath2){
		boolean blResult = false;
		try {
			File file1 = new File(strPath1);
			File file2 = new File(strPath2);
			
			file1.renameTo(file2);
			
	        blResult = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return blResult;
	}
	
	public static boolean writeContent(String strFilePath,String strContent){
		boolean blResult = false;
		try {
			File file = new File(strFilePath);
			BufferedWriter output = new BufferedWriter(new FileWriter(file,true));
	        output.write(strContent);
	        output.close();
	        blResult = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return blResult;
	}
	 public static boolean deleteDir( File dir )
	    {
	        if( dir.isDirectory (  ) )
	        {
	            String children[] = dir.list (  );

	            for( int i = 0; i < children.length; i++ )
	            {
	                boolean success = deleteDir ( new File( dir, children[ i ] ) );

	                if( !success )
	                {
	                    return false;
	                }
	            }
	        }

	        // The directory is now empty so delete it
	        return dir.delete (  );
	    }
}
