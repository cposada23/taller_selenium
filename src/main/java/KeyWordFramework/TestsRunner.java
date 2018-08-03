package KeyWordFramework;


import KeyWordFramework.configuration.Constants;

import factory.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import utilities.ExcelUtility;
import utilities.ReflectionRunner;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestsRunner {
    private static Logger logger = LogManager.getLogger(TestsRunner.class.getName());
    public static void main(String[] args) throws Exception {
        logger.info("************************************************************");
        logger.info("*                                                          *");
        logger.info("*                  TESTS STRARTING                         *");
        logger.info("*                                                          *");
        logger.info("************************************************************");


        String excelTestPath = new File(Constants.excelRelativePath).getAbsolutePath();
        logger.info("Absolute path " + excelTestPath);

        ExcelUtility.setExcelFile(excelTestPath);
        ArrayList<String> testCases = ExcelUtility.getTests(Constants.testCasesSheet);
        //logTestCases(testCases);
        /*RUN TEST CASES*/
        for (String testCase : testCases) {
            runTestCase(testCase);
        }

    }

    private static void runTestCase(String testCase) throws Exception{
        String [] testCaseInfo = testCase.split(";");
        String testCaseName = testCaseInfo[0];
        String testCaseBrowser = testCaseInfo[1];
        String testCaseSheet = testCaseInfo[2];
        logger.info("Running test case: " + testCaseName);
        logger.info("Test case browser: " + testCaseBrowser);
        logger.info("Test case sheet: " + testCaseSheet);
        int numberOfSteps = ExcelUtility.getNumberOfSteps(testCaseSheet);
        logger.info("The test case has " + numberOfSteps + " number of steps");
        for (int i = 1; i <= numberOfSteps; i++) {
            String method = ExcelUtility.getDataFromCell(testCaseSheet, i, Constants.actionKeyWordColumn).toString();
            List<Object> myParamList = new ArrayList<>();
            logger.debug("Calling Method: " + method);
            for (int col = Constants.locatorTypeColumn; col < Constants.resultCaseColumn; col++) {
                // String dataFromCol = ExcelUtility.getDataFromCell(testCaseSheet, i, col);
                Object data = ExcelUtility.getDataFromCell(testCaseSheet, i, col);
                if ( !data.toString().isEmpty() & !data.toString().equals("null")) {
                    logger.debug("added parameter: " + data.toString());
                    myParamList.add(data);
                }

            }
            Object[] paramListObject;
            paramListObject = new Object[myParamList.size()];
            paramListObject = myParamList.toArray(paramListObject);
            ReflectionRunner.runReflectionMethod("KeyWordFramework.configuration.KeyWords",
                    method, paramListObject);
        }
    }



    private static void logTestCases(ArrayList<String> testCases) {
        logger.info("****** TEST CASES *******");
        for (String testCase :
                testCases) {
            logger.info("Test Case: " + testCase);
        }
    }
}
