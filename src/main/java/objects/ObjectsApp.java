package objects;

import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;
import objects.low.AlertBox;
import objects.low.CheckBox;
import objects.low.DropDown;
import objects.medium.AjaxRequest;
import objects.medium.DownloadFile;
import objects.medium.JqueryListBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utilities.InputReader;

public class ObjectsApp {

    private static Logger logger = LogManager.getLogger(ObjectsApp.class.getName());
    private static final String EXIT = "Q";
    private static String browser = "CHROME";

    public static void main(String[] args) {
        logger.info("*********************************************************");
        logger.info("*                                                       *");
        logger.info("*                  START OBJECTS                        *");
        logger.info("*                                                       *");
        logger.info("*********************************************************");
//        CheckBox.checkBoxAutomation(browser);
        AlertBox.alertBoxAutomation(browser);
        // DownloadFile.downloadFileAutomation(browser);
        // JqueryListBox.jquerylistBoxAutomation(browser);
        /*String option;
        do {
            showMenu();
            option = reader();
            switch (option) {
                case "1":
                    lowMenu();
                    break;
                case "2":
                    mediumMenu();
                    break;
                case EXIT:
                    break;
                default:
                    logger.info("Please enter a valid option from the menu");
            }

        }while(!option.equalsIgnoreCase(EXIT));*/


    }

    private static void showMenu() {
        logger.info("*********************************************************");
        logger.info("*                       MENU                            *");
        logger.info("*********************************************************");
        logger.info("* 1) Easy                                               *");
        logger.info("* 2) Medium                                             *");
        logger.info("* 3) Hard                                               *");
        logger.info("* Q) EXIT                                               *");
        logger.info("*********************************************************");

    }

    private static String reader() {
        String input;
        try {
            input = InputReader.readString("Enter an option: ");
        } catch (Exception e) {
            logger.error("PLEASE ENTER A VALID INPUT");
            input = "";
        }
        return input;
    }

    private static void lowMenu() {

        String option;
        do{
            logger.info("*********************************************************");
            logger.info("*                     LOW MENU                          *");
            logger.info("*********************************************************");
            logger.info("* 1) Drop Down                                          *");
            logger.info("* 2) Check boxes                                        *");
            logger.info("* 3) Alert boxes                                        *");
            logger.info("* Q) EXIT                                               *");
            logger.info("*********************************************************");

            option = reader();
            switch (option) {
                case "1":
                    DropDown.dropDownAutomation(browser);
                    break;
                case "2":
                    CheckBox.checkBoxAutomation(browser);
                    break;
                case "3":
                    AlertBox.alertBoxAutomation(browser);
                    break;
                case EXIT:
                    break;
                default:
                    logger.info("Please enter a valid option from the menu");
            }

        }while(!option.equalsIgnoreCase(EXIT));

    }
    private static void mediumMenu() {

        String option;
        do{
            logger.info("*********************************************************");
            logger.info("*                     MEDIUM MENU                       *");
            logger.info("*********************************************************");
            logger.info("* 1) Download File                                      *");
            logger.info("* 2) Ajax Request                                       *");
            logger.info("* 3) JqueryListBox                                      *");
            logger.info("* Q) EXIT                                               *");
            logger.info("*********************************************************");

            option = reader();
            switch (option) {
                case "1":
                    DownloadFile.downloadFileAutomation(browser);
                    break;
                case "2":
                    AjaxRequest.ajaxAutomation(browser);
                    break;
                case "3":
                    JqueryListBox.jquerylistBoxAutomation(browser);
                    break;
                case EXIT:
                    break;
                default:
                    logger.info("Please enter a valid option from the menu");
            }

        }while(!option.equalsIgnoreCase(EXIT));

    }


}
