package pl.dc4b.cardirectory.textui;

public abstract class BaseTextUIManager {

    protected final String redColor = "\u001B[31m";
    protected final String greenColor = "\u001B[32m";
    protected final String blueColor = "\u001B[34m";
    protected final String whiteColor = "\u001B[37m";
    protected final String greyColor = "\u001B[90m";
    protected final String resetColor = "\u001B[0m";

    protected void showMenuTitle(String text){
        System.out.println(whiteColor + "===================================" + resetColor);
        System.out.println(whiteColor + "|| *** " + blueColor + text + whiteColor + " ***");
        System.out.println(whiteColor + "===================================" + resetColor);
    }

    protected void showEnterChoice(){
        System.out.println(greenColor + "->" + greyColor + " Enter your choice:" + resetColor);
    }

    protected void showInvalidChoice(){
        System.out.println(redColor + " [ Invalid choice. Please enter a valid option. ]" + resetColor);
    }

    protected void showSuccessMessage(){
        System.out.println(greenColor + " [ Operation succeeded! ]" + resetColor);
    }
    protected void showErrorMessage(String message){
        System.out.println(redColor + " [ " + message + " ]" + resetColor);
    }


    protected void showTableHeader(String[] headers){
        System.out.println(blueColor + "\t-------------------------------------------------------------" + resetColor);
        System.out.println(blueColor + "\t" + String.join("\t", headers) + blueColor  + resetColor);
        System.out.println(blueColor + "\t-------------------------------------------------------------" + resetColor);
    }
}
