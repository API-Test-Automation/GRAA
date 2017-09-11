package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


import apppages.Sample;

public class Reference {

    private static Reference refSingleton;
    /**
     * @param args
     */
    public WebDriver driver;


    public Sample rdPage;
  


    public Reference() {
       

    	rdPage = PageFactory.initElements(driver, Sample.class);
       

    }

    /**
     * Method DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static synchronized Reference instance() {
        if (refSingleton == null) {
            refSingleton = new Reference();
        }

        return refSingleton;
    }

}