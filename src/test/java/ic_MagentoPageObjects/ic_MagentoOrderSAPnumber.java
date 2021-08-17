package ic_MagentoPageObjects;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import utils.Action;
import utils.ConfigFileReader;
import utils.DataTable2;

public class ic_MagentoOrderSAPnumber {
	WebDriver driver;
    Action action;
	DataTable2 dataTable2;
    
    public ic_MagentoOrderSAPnumber(WebDriver driver, DataTable2 dataTable2) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        action = new Action(driver);
		this.dataTable2 = dataTable2;

    }
    
    @FindBy(xpath="//div[contains(text(),'[RabbitMQ] Order SAP Number: ')][1]")
    private WebElement OrderDetailSAPNumber;

	@FindBy(xpath = "//span[@id='order_status']")
	private WebElement magentoOrderStatus;
    
    Timer t = new Timer();
    public static String OrderSAPnumber;
    
    public void GenerateOrderSAPnumber(HashMap<String, ArrayList<String>> input,ExtentTest test,int rowNumber) throws Exception {
    	boolean flagres = false;
    	int totalConunter=0;
    	String OrderSAPnumber = "";
    	long startTime = System.currentTimeMillis();
    	int TimeOutinSecond =Integer.parseInt(input.get("TimeOutinSecond").get(rowNumber));
    	int trycount =Integer.parseInt(input.get("totalCounter").get(rowNumber));
    	int elapsedTime = 0;
    	action.explicitWait(10000);

		String orderStatus = dataTable2.getValueOnOtherModule("OrderStatusSearch", "orderStatus", 0);
		System.out.println("Sales Order Status :"+orderStatus);
		action.CompareResult("Sales Order Status", orderStatus, magentoOrderStatus.getText(), test);

    	while(elapsedTime<=TimeOutinSecond && flagres==false)
    	{
			action.refresh();
			action.waitForPageLoaded(TimeOutinSecond);
			
			try {
				if(action.elementExists(OrderDetailSAPNumber, 10)){
						OrderSAPnumber = OrderDetailSAPNumber.getText(); // DO NOT USE CREATE MANY FAIL STEPS IF NOT FOUND--> action.getText(OrderDetailSAPNumber, "SAP Number",test);
						action.scrollToElement(OrderDetailSAPNumber,"OrderDetailSAPNumber");
						System.out.println(OrderSAPnumber);
					if(OrderSAPnumber.isEmpty()){
			    		action.explicitWait(TimeOutinSecond);
			    		action.refresh();
						System.out.println("not found on count:" + totalConunter);
			    	}else{
			    		flagres = true;
						System.out.println("OrderSAPnumber :" + OrderSAPnumber);
						input.get("OrderSAPnumber").set(rowNumber,OrderSAPnumber.replace("[RabbitMQ] Order SAP Number: ",""));
			    	}
				}else{
					System.out.println("OrderDetailSAPNumber not exist");
				}
					
			} catch (Exception e) {
				// TODO Auto-generated catch block
				if(trycount==totalConunter){
					e.printStackTrace();
				}
			}

			//Thread.sleep(TimeOutinSecond * 1000);
			long endTime = System.currentTimeMillis();
			long elapsedTimeInMils = endTime-startTime;
			elapsedTime = ((int) elapsedTimeInMils)/1000;
			System.out.println("elapsedTime: "+elapsedTime);
			totalConunter++;
		}
    	if(flagres){
    		action.CompareResult("SAP order Number generated :"+OrderSAPnumber, String.valueOf(true), String.valueOf(flagres), test);
    	}else{
    		JavascriptExecutor exe = (JavascriptExecutor)driver;
            exe.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            exe.executeScript("window.scrollBy(0,-500)");
    		action.CompareResult("SAP order Number generated :"+OrderSAPnumber, String.valueOf(true), String.valueOf(flagres), test);
    		throw new Exception("SAP Order Number Is Not Generated");
    	}
    	System.out.println();
    }
}
