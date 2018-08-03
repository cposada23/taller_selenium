package KeyWordFramework;


import KeyWordFramework.configuration.Constants;

import factory.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import utilities.ExcelUtility;

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
            String method = ExcelUtility.getDataFromCell(testCaseSheet, i, Constants.actionKeyWordColumn);
            List<Object> myParamList = new ArrayList<>();
            logger.debug("Calling Method: " + method);
            for (int col = Constants.locatorTypeColumn; col < Constants.resultCaseColumn; col++) {
                String dataFromCol = ExcelUtility.getDataFromCell(testCaseSheet, i, col);

                if (!dataFromCol.isEmpty() & !dataFromCol.equals("null")) {
                    logger.debug("added parameter: " + dataFromCol);
                    myParamList.add(dataFromCol);
                }

            }
            Object[] paramListObject;
            paramListObject = new String[myParamList.size()];
            paramListObject = myParamList.toArray(paramListObject);
            runReflectionMethod("KeyWordFramework.configuration.KeyWords",
                    method, paramListObject);
        }



        /*KeyWords keyWords = new KeyWords();
        keyWords.openBrowser(testCaseBrowser);
        keyWords.openUrl(Constants.loginAdminPage);
        keyWords.enter_Text("id","user_login" , "cposada");
        keyWords.enter_Text("id","user_pass" , "cposada");
        keyWords.clickOnButton("id", "wp-submit");
        keyWords.closeBrowser();*/
    }

    private static void runReflectionMethod(String className, String methodName, Object... inputArgs) {
        Class<?> params[] = new Class[inputArgs.length];

        for (int i = 0; i < inputArgs.length; i++) {
            if (inputArgs[i] instanceof String) {
                params[i] = String.class;
            }else if (inputArgs[i] instanceof Integer) {
                params[i] = String.class;
            }
        }

        try {
            Class<?> cls = Class.forName(className);
            Object _instance = cls.newInstance();
            Method myMethod = cls.getDeclaredMethod(methodName, params);
            myMethod.invoke(_instance, inputArgs);

        } catch (ClassNotFoundException e) {
            System.err.format(className + ":- Class not found%n");
        } catch (IllegalArgumentException e) {
            System.err
                    .format("Method invoked with wrong number of arguments%n");
        } catch (NoSuchMethodException e) {
            System.err.format("In Class " + className + "::" + methodName
                    + ":- method does not exists%n");
        } catch (InvocationTargetException e) {
            System.err.format("Exception thrown by an invoked method%n");
        } catch (IllegalAccessException e) {
            System.err
                    .format("Can not access a member of class with modifiers private%n");
            e.printStackTrace();
        } catch (InstantiationException e) {
            System.err
                    .format("Object cannot be instantiated for the specified class using the newInstance method%n");
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
