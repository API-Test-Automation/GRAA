package common;

import actions.Browser;
import toolbox.ResultUtil;
import intialize.Prerequsite;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class ExtendedLibrary extends Browser {

    // public static final String CONFIG_FILE_PATH = "src\\config\\Config.properties";

    public static Reference _ref;

    static {
        _ref = new Reference();
//        _reference = Reference.instance();
    }

    private WebDriverWait wait;

    public static String getXPATHValue(String sKey) throws IOException {
        FileInputStream inputStream = new FileInputStream("src\\or\\OR.properties");
        Properties pro = new Properties();
        pro.load(inputStream);
        return pro.getProperty(sKey).toString().trim();

    }

    public static ExpectedCondition<Boolean> presenceOfAllElementsLocatedBy(final By locator) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(final WebDriver driver) {
                return !driver.findElements(locator).isEmpty();
            }
        };
    }

    public static ExpectedCondition<Boolean> pageToLoad() {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(final WebDriver webDriver) {
                return ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete");
            }
        };
    }

    public static ExpectedCondition<String> appearingOfWindowAndSwitchToIt(final String title) {
        return new ExpectedCondition<String>() {
            @Override
            public String apply(final WebDriver driver) {
                final String initialHandle = driver.getWindowHandle();
                for (final String handle : driver.getWindowHandles()) {
                    if (needToSwitch(initialHandle, handle)) {
                        driver.switchTo().window(handle);
                        if (driver.getTitle().equals(title)) {
                            return handle;
                        }
                    }
                }
                driver.switchTo().window(initialHandle);
                return null;
            }
        };
    }
    public static ExpectedCondition<String> ClosedAndNavigatingToLastWindow(final String title) {
        return new ExpectedCondition<String>() {
            @Override
            public String apply(final WebDriver driver) {
                for (String handle : driver.getWindowHandles()) {
                    driver.switchTo().window(handle);
                    if (driver.getTitle().contains(title)) {
                        return handle;
                    } }

                return null;
            }
        };
    }

    private static boolean needToSwitch(String initialHandle, String handle) {
        if (handle.isEmpty()) {
            return false;
        }
        return !initialHandle.equals(handle);
    }

    public void waitForWindowToBeAppearedAndSwitchToIt(final String windowTitle) {
        waitFor(appearingOfWindowAndSwitchToIt(windowTitle));
    }
    public void waitForLastWindowdAndSwitchToIt(final String windowTitle) {
        waitFor(ClosedAndNavigatingToLastWindow(windowTitle));
    }

    public Boolean waitForTextToBePresentInElement(final WebElement element, final String textToBePresent) {
        return waitFor(textToBePresentInElement(element, textToBePresent));
    }

    public WebElement waitForVisibilityOfElementLocatedBy(final By locator) {
        return waitFor(visibilityOfElementLocated(locator));
    }

    public Boolean waitForInvisibilityOfElementLocatedBy(final By locator) {
        return waitFor(invisibilityOfElementLocated(locator));
    }
    public Boolean waitForInvisibilityOfElementTill1MinLocatedBy(final By locator) {
        return waitFor1Min(invisibilityOfElementLocated(locator));
    }

    public <T> T waitFor(final ExpectedCondition<T> condition) {
        wait = new WebDriverWait(driver, 20);
        try {
            return wait.until(condition);
        } catch (UnhandledAlertException alertException) {
            try {
                Alert alert = driver.switchTo().alert();
//                String alertText = alert.getText();
                alert.dismiss();
            } catch (NoAlertPresentException ignored) {
                System.out.println("ignore alert");
            }
            return wait.until(condition);
        }
    }
    public <T> T waitFor1Min(final ExpectedCondition<T> condition) {
        wait = new WebDriverWait(driver, 60);
        try {
            return wait.until(condition);
        } catch (UnhandledAlertException alertException) {
            try {
                Alert alert = driver.switchTo().alert();
//                String alertText = alert.getText();
                alert.dismiss();
            } catch (NoAlertPresentException ignored) {
                System.out.println("ignore alert");
            }
            return wait.until(condition);
        }
    }

    public WebElement waitForPresenceOfElementLocatedBy(final By locator, String errorMessage) {
        try {
            WebElement resultElement = waitFor(presenceOfElementLocated(locator));

//            ResultUtil.report("PASS", resultElement.getText() + "element is present", "", "", driver);
            return resultElement;
        } catch (WebDriverException we) {
            ResultUtil.report("FAIL", "", "", errorMessage + "-" + we.getMessage(), driver);
            return null;
        }
    }

    public WebElement getParentElement(WebElement element) {
        return (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].parentNode", element);
    }

    public final Object executeScript(final String script, final Object... args) {
        return ((JavascriptExecutor) driver).executeScript(script, args);
    }

    public List<WebElement> waitForPresenceOfAllElementsLocatedBy(final By locator) {
        waitFor(presenceOfAllElementsLocatedBy(locator));
        return driver.findElements(locator);
    }

    public List<WebElement> waitForVisibilityOfAllElementsLocatedBy(final By locator) {
        waitFor(visibilityOfAllElementsLocatedBy(locator));
        return driver.findElements(locator);
    }

    public void waitForPageToLoad() {
        try {
            try {
                waitFor(pageToLoad());
            } catch (TimeoutException expected) {
                String readyState = ((JavascriptExecutor) driver).executeScript("return document.readyState").toString();
                ResultUtil.report("INFO", "*****ERROR***** TimeoutException occurred while waiting for page to load! return document.readyState value is '" + readyState + "' But expected to be 'complete'", "", "", driver);
//                LOGGER.error("*****ERROR***** TimeoutException occurred while waiting for page to load! return document.readyState value is '" + readyState + "' But expected to be 'complete'");
            } catch (WebDriverException e) {
                ResultUtil.report("INFO", "*****ERROR***** WebDriverException occurred while waiting for page to load!", "", "", driver);
//                LOGGER.error("*****ERROR***** WebDriverException occurred while waiting for page to load!");
            }
        } catch (Throwable unexpectedThrowable) {
            //TODO this catch should be removed by September 1 2014! Adding it to find out the reason of browsers being not closed after test
//            LOGGER.error("*****ERROR*****!!!*****ERROR*****Throwable occurred was throws during waiting for page to load!", unexpectedThrowable);
            ResultUtil.report("INFO", "HomePage > logoutOnlineLibrary", "", "", driver);
        }
    }

    /**
     * getTableHeaderColumnIndex
     *
     * @param columnName Name of the column, whose index you want to have
     * @param allHeaders Point it to the table headers
     * @return int value, which is the index of the column in the table
     */
    public int getTableHeaderColumnIndex(String columnName, List<WebElement> allHeaders) {
        int rowIndex = -1;
        for (int iterator = 0; iterator < allHeaders.size(); iterator++) {
            if (columnName.equals(allHeaders.get(iterator).getText().trim())) {
                rowIndex = iterator;
            }
        }
        return rowIndex;
    }

    public void reloadPage(By locator) {
        WebElement element = waitForPresenceOfElementLocatedBy(locator, "Element locator to clicking F5 was not found");
        getDriver().navigate().refresh();
        waitForStalenessOf(element);
//        waitForPageToLoad();
    }

    public Boolean waitForStalenessOf(final WebElement webElement) {
        return waitFor(stalenessOf(webElement));
    }

    public final WebElement findElementByNoThrow(final By locator) {
        try {
            return getDriver().findElement(locator);
        } catch (WebDriverException ignored) {
            System.out.println("[INFO] Element not found... Ignoring exception");
            return null;
        }
    }

    public final List<WebElement> findElementsByNoThrow(final By locator) {
        try {
            return getDriver().findElements(locator);
        } catch (WebDriverException ignored) {
            System.out.println("[INFO] Element not found... Ignoring exception");
            return null;
        }
    }


    // TODO: Needed refactor to use this as common
    public void navigateToJQAPage() {
        try {
            waitInMilliSeconds(1000);
            executeScript("window.location.href='" + Prerequsite.configMap.get("JQA_URL") + "';");
            waitInMilliSeconds(15000);
            if (BrowserName.equals(BrowserType.FireFox.getType())) {
                waitInMilliSeconds(5000);
                Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\AutoIT\\FF_Admin.exe");
            } else if (BrowserName.equals(BrowserType.InternetExplore.getType())) {
                driver.get("javascript:document.getElementById('overridelink').click();");
                waitInMilliSeconds(15000);
                Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\AutoIT\\IE2.exe");
            } else if (BrowserName.equals(BrowserType.Chrome.getType())) {
                Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\AutoIT\\Chrome2_Maximised.exe");
            }
        } catch (IOException e1) {
        }
    }
    public void navigateToJQAGateway(){
        try{
            waitInMilliSeconds(1000);
            getDriver().navigate().to(Prerequsite.configMap.get("JQA_GATEWAY_URL"));
           // executeScript("window.location.href='" + Prerequsite.configMap.get("JQA_GATEWAY_URL") + "';");
            waitForPageToLoad();

        }catch (Exception e){
            ResultUtil.report("FAIL", "Unable to load with JQA GateWay url", "", "",driver);
        }
    }
    public void navigateMetaDataServiceUrl(String url){
        try{
            waitInMilliSeconds(1000);
            getDriver().navigate().to(url);

        }catch (Exception e){
            ResultUtil.report("FAIL", "Unable to load with "+url+"", "", "",driver);
        }
    }

    public void switchToLastWindow() {
        Set<String> windowHandles = driver.getWindowHandles();
        Iterator<String> windowIterator = windowHandles.iterator();
        if (windowIterator.hasNext()) {
            driver.switchTo().window(windowIterator.next());
        }
    }
}
