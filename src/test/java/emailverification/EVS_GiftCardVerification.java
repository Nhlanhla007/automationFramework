package emailverification;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Action;
import utils.DataTable2;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class EVS_GiftCardVerification {
    WebDriver driver;
    Action action;

    @FindBy(xpath="//*[@id=\"identifierId\"]")
    private WebElement userNameText;

    @FindBy(xpath="//*[@id=\"identifierNext\"]/div/button/div[2]']")
    private WebElement nextButton;

    @FindBy(xpath="//input[@class='whsOnd zHQkBf']")
    private WebElement userPassword;

    @FindBy(xpath="//*[@id=\"passwordNext\"]/div/button/div[2]")
    private WebElement userPasswordNext;

    @FindBy(xpath="//*[@id=\":7a\"]/div[1]")
    private WebElement singleEmailText;

    DataTable2 dataTable2;
    public EVS_GiftCardVerification(WebDriver driver, DataTable2 dataTable2){
        this.driver = driver;
        action = new Action(driver);
        this.dataTable2=dataTable2;

    }
    public void evsGiftCardVerificationSender (ExtentTest test) throws IOException, ParseException {
        action.navigateToURL("https://mail.google.com/");
        navigateToGmail(dataTable2.getValueOnCurrentModule("username"),dataTable2.getValueOnCurrentModule("password"),test);
        action.explicitWait(10000);
        List<WebElement> email = driver.findElements(By.xpath("/html/body/div[7]/div[3]/div/div[2]/div[1]/div[2]/div/div/div/div/div[2]/div/div[1]/div/div/div[9]/div/div[1]/div[3]/div/table//tr"));
        int waitTimeForBarcodeEmailInSec=Integer.parseInt(dataTable2.getValueOnCurrentModule("waitTimeForBarcodeEmailInSec"));
        int numberOfEmailsAfter=email.size();
        Date date1 =new Date();
        long curTime1=date1.getTime();
        long difference = 0;
        boolean foundGiftCard=false;
        boolean foundInvoice=false;
        boolean foundOrderConfirmation=false;
        while((difference<=waitTimeForBarcodeEmailInSec)||(foundGiftCard&&foundInvoice&&foundOrderConfirmation)) {
//
                for (WebElement emailsub : email) {

                    if ((emailsub.getText().contains("been sent a gift from Incredible Connection"))&&!foundGiftCard) {
                        emailsub.click();
                        action.explicitWait(5000);
                        String emailText = action.getText(singleEmailText,"emailText",test);
                        int barcodeIndex = emailText.indexOf("Barcode for");
                        int serialNumberIndex = emailText.indexOf("Serial Number");
                        int scratchCodeIndex = emailText.indexOf("Scratch Code");
                        System.out.println(emailText.substring(barcodeIndex, barcodeIndex + 37));
                        dataTable2.setValueOnCurrentModule("barcode",emailText.substring(barcodeIndex, barcodeIndex + 37));
                        System.out.println(emailText.substring(serialNumberIndex, serialNumberIndex + 30));
                        dataTable2.setValueOnCurrentModule("serialNumber",emailText.substring(serialNumberIndex, serialNumberIndex + 30));
                        System.out.println(emailText.substring(scratchCodeIndex, scratchCodeIndex + 19));
                        dataTable2.setValueOnCurrentModule("scratchCode",emailText.substring(scratchCodeIndex, scratchCodeIndex + 19));
                        foundGiftCard=true;
                        driver.findElement(By.xpath("/html/body/div[7]/div[3]/div/div[2]/div[1]/div[2]/div/div/div/div/div[1]/div[2]/div[1]/div/div[1]/div/div")).click();
                    }
                    if ((emailsub.getText().contains("Your Everyshop order confirmation"))&&!foundOrderConfirmation) {
                        foundOrderConfirmation=true;
                    }
                    if ((emailsub.getText().contains("Invoice for your Everyshop order"))&&!foundInvoice) {
                        foundInvoice=true;
                    }
//                }
            }
            action.refresh();
            action.explicitWait(30000);
            Date date2 =new Date();;
            long curTime2=date2.getTime();
            difference=(curTime2-curTime1)/1000;
            System.out.println("Time waiting for email(sec): "+difference);
            System.out.println("Number of emails: "+numberOfEmailsAfter);
            email = driver.findElements(By.xpath("/html/body/div[7]/div[3]/div/div[2]/div[1]/div[2]/div/div/div/div/div[2]/div/div[1]/div/div/div[9]/div/div[1]/div[3]/div/table//tr"));

            numberOfEmailsAfter=email.size();
        }

        action.CompareResult(" You've been sent a gift from Everyshop Email for reciever","true",String.valueOf(foundGiftCard),test);
        action.CompareResult(" Invoice for your Everyshop order Email for sender ","true",String.valueOf(foundInvoice),test);
        action.CompareResult(" Your Everyshop order confirmation Email for sender ","true",String.valueOf(foundOrderConfirmation),test);
    }
        public void navigateToGmail(String userName,String password,ExtentTest test) throws IOException {
        action.navigateToURL("https://mail.google.com/");
        action.explicitWait(5000);
        driver.findElement(By.id("identifierId")).sendKeys(userName);
        driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
        action.explicitWait(2000);
        driver.findElement(By.xpath("//input[@class='whsOnd zHQkBf']")).sendKeys(password);
        driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
        action.explicitWait(5000);
//        action.writeText(userNameText,userName,"userName",test);
//        action.click(nextButton,"nextButton",test);
//        action.explicitWait(5000);
//        action.writeText(userPassword,password,"password",test);
//        action.click(nextButton,"nextButton",test);
//        action.explicitWait(10000);
    }
    public void clearEmail(HashMap<String, ArrayList<String>> input, ExtentTest test, int rowNumber) throws IOException {
        navigateToGmail(input.get("userName").get(rowNumber),input.get("password").get(rowNumber),test);
        action.explicitWait(5000);
        List<WebElement> email = driver.findElements(By.xpath("/html/body/div[7]/div[3]/div/div[2]/div[1]/div[2]/div/div/div/div/div[2]/div/div[1]/div/div//div/div[1]/div[3]/div/table//tr"));
        System.out.println("email.size():"+email.size());
        if(email.size()>0){
            driver.findElement(By.xpath("/html/body/div[7]/div[3]/div/div[2]/div[1]/div[2]/div/div/div/div/div[1]/div/div[1]/div[1]/div/div/div[1]/div/div[1]/span")).click();
            action.explicitWait(2000);
            driver.findElement(By.xpath("/html/body/div[7]/div[3]/div/div[2]/div[1]/div[2]/div/div/div/div/div[1]/div/div[1]/div[1]/div/div/div[2]/div[3]/div")).click();
            action.explicitWait(2000);
        }
    }
}