package toolbox;



public class StringHandler {
	/**
	 * Description : To compare two strings
	 * 
	 * @author Mahesh Babu
	*/
	public static boolean compare(String strCompareDesc,String str1,String str2){
		boolean blResult=false;
		if(str1.equals(str2)){
			ResultUtil.print("PASS", strCompareDesc,str1,str2);
			blResult=true;
		}
		else{
			ResultUtil.print("FAIL", strCompareDesc,str1,str2);
		}
		return blResult;
	}
}
