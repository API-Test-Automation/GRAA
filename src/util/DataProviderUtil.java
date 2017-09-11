package util;

import java.util.Iterator;

import org.testng.annotations.DataProvider;

public class DataProviderUtil {
	
	public static class staticProviderClass{
			
		@DataProvider(name="GRAA",parallel=false)
		public static Iterator<Object[]> graa(){
			System.out.println("calling method again staticProviderClass");
			Iterator<Object[]> testData=null;//ExcelUtils.getTestData("Input/GRAAProjects.xls", "GRAA");
			return testData;
		}
		

	
	}
}


