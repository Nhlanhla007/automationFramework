package evs_PageObjects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import utils.Action;
import utils.DataTable2;

public class EVS_RetriveGiftCardOrderId {
	WebDriver driver;
    Action action;
    DataTable2 dataTable2;
    
    public EVS_RetriveGiftCardOrderId(WebDriver driver, DataTable2 dataTable2) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        action = new Action(driver);
        this.dataTable2=dataTable2;
    }
    
    @FindBy(xpath = "//span[@class='number']")
    WebElement OderID;
    
    public void RetriveOrderID(ExtentTest test) throws IOException {
        String Oderid = null;
        action.explicitWait(10000);
        action.elementExistWelcome(OderID, 10, "order number", test);
        Oderid = action.getText(OderID, "Order ID",test);
        dataTable2.setValueOnOtherModule("evs_RetriveOrderID", "orderID", Oderid, 0);
        dataTable2.setValueOnOtherModule("evs_OrderStatusSearch","orderID",Oderid,0);
    }

}
    
