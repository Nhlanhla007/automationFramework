package JDGroupPageObjects;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import utils.Action;
import utils.ConfigFileReader;

public class ic_GiftCardPurchase {
	WebDriver driver;
    Action action;
    
    Ic_Products ic_products;
    ic_PayUPayment ic_payU;
	
	public ic_GiftCardPurchase(WebDriver driver){
		
		this.driver = driver;
        PageFactory.initElements(driver, this);
        action = new Action(driver);
        ic_products = new Ic_Products(driver);
        ic_payU = new ic_PayUPayment(driver);
	}

	@FindBy(xpath="//input[@id='giftcard-amount']")
    private WebElement ic_Amount;
	
	@FindBy(xpath = "//input[@id='firstname']")
	WebElement User_Firstname;
	
	@FindBy(xpath="//input[@id='giftcard-amount-input']")
    private WebElement ic_AmountInput;
	
	@FindBy(xpath="//input[@id='giftcard_sender_name']")
    private WebElement ic_SenderName;
	
	@FindBy(xpath="//input[@id='giftcard_sender_email']")
    private WebElement ic_SenderEmail;
	
	@FindBy(xpath="//input[@id='giftcard_recipient_name']")
    private WebElement ic_RecipientName;
	
	@FindBy(xpath="//input[@id='giftcard_recipient_email']")
    private WebElement ic_RecipientEmail;
	
	@FindBy(xpath="//*[@id=\"giftcard-message\"]")
    private WebElement ic_CardMessage;
	
	@FindBy(xpath="//*[@id=\"product_addtocart_form\"]/div[4]/div/div/div[2]")
    private WebElement ic_AddToCart;
	
	@FindBy(xpath="/html/body/div[2]/header/div[2]/div/div[3]/div[3]/a")
    private WebElement ic_Cart;
	
	@FindBy(xpath="//*[@id=\"maincontent\"]/div/div[1]/div[2]/div[2]/ol/li[1]/div/div[2]/div[3]/div/div[1]/form/button/span")
    private WebElement ic_MoreInfo;
	
	@FindBy(xpath="//*[@id=\"top-cart-btn-checkout\"]/span")
    private WebElement ic_secure;
	
	public void purchaseGiftCard(HashMap<String, ArrayList<String>> input,ExtentTest test,int rowNumber) throws IOException{
		driver.navigate().to(ConfigFileReader.getPropertyVal("MagentoURL"));
		String selectAmountFlag = input.get("selectAmountFlag").get(rowNumber);
		String selectAmount = input.get("selectAmount").get(rowNumber);
		String inputAmount = input.get("inputAmount").get(rowNumber);
		String senderName = input.get("senderName").get(rowNumber);
		String senderEmail = input.get("senderEmail").get(rowNumber);
		String recipientName = input.get("recipientName").get(rowNumber);
		String recipientEmail = input.get("recipientEmail").get(rowNumber);
		String cardMessage = input.get("cardMessage").get(rowNumber);
		ic_products.loadProductListingPage("SearchUsingSearchBar", "Gift Card", test);
		action.click(ic_MoreInfo, "more information", test);
		if(selectAmountFlag.equalsIgnoreCase("Yes")){
		action.writeText(ic_Amount, selectAmount, "Select the Amount", test);
		}else {
			action.writeText(ic_AmountInput, inputAmount, "Write your amount of choice", test);
		}
		action.writeText(ic_SenderName, senderName, "sender name", test);
		action.writeText(ic_SenderEmail, senderEmail, "sender email", test);
		action.writeText(ic_RecipientName, recipientName, "recipient ame", test);
		action.writeText(ic_RecipientEmail, recipientEmail, "recipient email", test);
		action.writeText(ic_CardMessage, cardMessage, "card message", test);
		action.explicitWait(5000);
		action.click(ic_AddToCart, "add to cart", test);
		action.explicitWait(5000);
		action.click(ic_Cart, "cart clicked", test);
		action.click(ic_secure, "Checkout Secure clicked", test);
	}
	
}






