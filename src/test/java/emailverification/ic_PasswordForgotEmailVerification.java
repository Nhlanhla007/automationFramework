package emailverification;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import utils.Action;
import utils.DataTable2;

public class ic_PasswordForgotEmailVerification {
	WebDriver driver;
	Action action;
	DataTable2 dataTable2;

	public ic_PasswordForgotEmailVerification(WebDriver driver, DataTable2 dataTable2){
		this.driver = driver;
		PageFactory.initElements(driver, this);
		action = new Action(driver);
		this.dataTable2=dataTable2;
	}
	
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
	    
	    @FindBy(xpath="//span[contains(text(),'websiteorders@incredible.com')]")
	    private WebElement EmailSender;
	    
	    public void icVerifyNewPasswordEmailSent(HashMap<String, ArrayList<String>> input, ExtentTest test, int rowNumber) throws IOException{
	    	action.navigateToURL("https://mail.google.com/");
	        navigateToGmail(dataTable2.getValueOnOtherModule("icGiftCardVerificationSender", "userName", 0),dataTable2.getValueOnOtherModule("icGiftCardVerificationSender", "password", 0),test);
	        action.explicitWait(10000);
	        
	        List<WebElement> email = driver.findElements(By.xpath("/html/body/div[7]/div[3]/div/div[2]/div[1]/div[2]/div/div/div/div/div[2]/div/div[1]/div/div/div[9]/div/div[1]/div[3]/div/table//tr"));
	        int waitTimeForBarcodeEmailInSec=Integer.parseInt(dataTable2.getValueOnOtherModule("icGiftCardVerificationSender", "waitTimeForBarcodeEmailInSec", 0));
	        int numberOfEmailsAfter=email.size();
	        Date date1 =new Date();
	        long curTime1=date1.getTime();
	        long difference = 0;
	        boolean foundSubject=false;
	        boolean foundSender=false;
	        
	        while((difference<=waitTimeForBarcodeEmailInSec) && (!foundSubject)) {
                for (WebElement emailsub : email) {

	                    if ((emailsub.getText().contains("Password Reset Confirmation for"))&&!foundSubject&&!foundSender) {
	                        emailsub.click();
	                        action.explicitWait(5000);
	                        foundSubject=true;
	                        String SenderEmail ="";
	                        SenderEmail = action.getText(EmailSender, "EmailSender");
	                    
	                    if ((SenderEmail.contains("websiteorders@incredible.com"))&&!foundSender) {
	                    	foundSender=true;
	                    }
                    }
	                    if(foundSender==true &&foundSubject ==true){
	                    	break;
	                    }
	                
//                }
                if(foundSender==true &&foundSubject ==true){
                	break;
                } 
            }
            action.refresh();
            action.explicitWait(20000);
            Date date2 =new Date();;
            long curTime2=date2.getTime();
            difference=(curTime2-curTime1)/1000;
            System.out.println("Time waiting for email(sec): "+difference);
            System.out.println("Number of emails: "+numberOfEmailsAfter);
            email = driver.findElements(By.xpath("/html/body/div[7]/div[3]/div/div[2]/div[1]/div[2]/div/div/div/div/div[2]/div/div[1]/div/div/div[9]/div/div[1]/div[3]/div/table//tr"));

            numberOfEmailsAfter=email.size();
            }
	        
	      //input.get("emailCountAfterGiftCardBuy").set(rowNumber,String.valueOf(email.size()));
	        action.CompareResult(" Password Reset Confirmation for","true",String.valueOf(foundSubject),test);
	        action.CompareResult(" websiteorders@incredible.com as the sender","true",String.valueOf(foundSender),test);
	    	
	    }
	    public void navigateToGmail(String userName,String password,ExtentTest test) throws IOException {
	        action.explicitWait(5000); 
	        driver.findElement(By.id("identifierId")).sendKeys(userName);
	        driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/div/button/div[2]")).click();
	        action.explicitWait(5000);
	        driver.findElement(By.xpath("//input[@class='whsOnd zHQkBf']")).sendKeys(password);
	        driver.findElement(By.xpath("//*[@id=\"passwordNext\"]/div/button/div[2]")).click();
	        action.explicitWait(10000);

	    }
}
