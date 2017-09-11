package testscripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import actions.Browser;
import common.ExtendedLibrary;
import common.SuiteExucuter;
import toolbox.ResultUtil;
import util.DataProviderUtil;
import util.ExcelUtils;

@Test

/**
 * Created by maheshb on 06/April/2017.
 */
public class SampleScript extends ExtendedLibrary {
	ArrayList<String> listOfProjects = new ArrayList<String>();
	public void sampleTestcase() throws IOException {
		System.out.println("calling method again");
		LinkedHashMap<String,LinkedHashMap> dataMap =(LinkedHashMap<String, LinkedHashMap>) ExcelUtils.getTestData("Input/GRAAProjects.xls", "GRAA");
		
		for (Entry<String, LinkedHashMap> mapEntry : dataMap.entrySet()) {
			Map<String, String> map = (Map<String, String>) mapEntry.getValue();
			//System.out.println("project name is " + mapEntry.getKey());
			for (Entry<String, String> mapEntry1 : map.entrySet()) {

				//System.out.println(mapEntry1.getKey() + "::" + mapEntry1.getValue());

				if (mapEntry1.getKey().equals("EXECUTION") && mapEntry1.getValue().equals("YES")) {
					System.out.println(">>>>>>>>>>>>>>>>>" + map.get("PROJECT"));

					SuiteExucuter.execute(map);
				}

			}
		}

		

		System.out.println(">>>.."+listOfProjects);
	}

}
