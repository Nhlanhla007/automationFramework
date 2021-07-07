package evs_PageObjects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import utils.Action;
import utils.DataTable2;

public class EVS_ReturnToConfirmOrderStatus {
	WebDriver driver;
    Action action;
    DataTable2 dataTable2;
    
    public EVS_ReturnToConfirmOrderStatus(WebDriver driver, DataTable2 dataTable2){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        action = new Action(driver);
        this.dataTable2 = dataTable2;

    }
    
    @FindBy(xpath="//span[contains(text(),'My Account')]")
	private WebElement myAcco;
    
    @FindBy(xpath="//a[contains(text(),'My Orders')]")
   	private WebElement myOrder;
    
    @FindBy(xpath="//*[@id=\"my-orders-table\"]/tbody/tr[1]/td[1]")
   	private WebElement myOrderNumber;
    
    @FindBy(xpath="//*[@id=\"my-orders-table\"]/tbody/tr[1]/td[5]")
   	private WebElement myOrderStatus;
    
    @FindBy(className = "my-account")
	WebElement ic_myAccountButton;
    
    public void backToEvs(ExtentTest test) throws IOException{
        String url =dataTable2.getRowUsingReferenceAndKey("URL","SUTURLS",dataTable2.getValueOnOtherModule("evs_Login","loginDetails", 0),"url");
        action.navigateToURL(url);
        action.explicitWait(5000);
        action.click(myAcco, " click my account", test);
        action.click(myOrder, "Orders", test);
        
        String orderNum = dataTable2.getValueOnOtherModule("evs_OrderStatusSearch", "orderID", 0);
        
        action.CompareResult("The order number", orderNum, action.getText(myOrderNumber, "number", test), test);
        
        action.getText(myOrderStatus, "Confirm the Cancel status", test); 
    }

}
