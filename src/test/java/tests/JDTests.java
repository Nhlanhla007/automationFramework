package tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import base.TestCaseBase;
import emailverification.ICGiftCardVerification;
import ic_MagentoPageObjects.*;
import ic_MagentoPageObjects.MagentoOrderStatusPage;
import ic_MagentoPageObjects.Magento_UserInfoVerification;
import ic_MagentoPageObjects.MagentoRegisterNewUser;
import ic_MagentoPageObjects.admin_UserUpdate;
import ic_MagentoPageObjects.ic_Magento_Login;
import ic_MagentoPageObjects.MagentoOrderStatusPage;
import ic_MagentoPageObjects.ic_MagentoOrderSAPnumber;
import ic_MagentoPageObjects.ic_Magento_Login;
import com.aventstack.extentreports.ExtentTest;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import JDGroupPageObjects.*;
import SAP_HanaDB.SAPorderRelated;
import utils.*;

public class JDTests extends BaseTest {
	ExtentReportJD reportJD;

	public String currentSuite;
	public String currentKeyWord;
	HashMap<String, Integer> occCount=null;
	int testcaseID;
	
	@BeforeClass(alwaysRun = true)
	public void setUp() {

	}
	//Login to Opera Cloud

	@Test
	public void suiteExecutor() throws Exception {
		dataTable2= new DataTable2();
		dataMap2=dataTable2.getExcelData();

		int numberOfSuits=dataMap2.size();
		for(int i=0;i<numberOfSuits;i++){
			Object Key = dataMap2.keySet().toArray()[i];
			if(!Key.toString().contains("++")&&!Key.toString().contains("Suites")&&!Key.toString().contains("inputData")) {
				currentSuite=Key.toString();
				HashMap<String, ArrayList<String>> singleSuiteData = dataMap2.get(Key);
				System.out.println("currentSuite:"+currentSuite);
				reportJD=new ExtentReportJD(currentSuite);
				runSuite(singleSuiteData);
				reportJD.endReport();
			}
		}
	}



	public void runSuite(HashMap<String, ArrayList<String>> singleSuiteData) throws IOException, InterruptedException {

		int numberOfTestCases =singleSuiteData.get("Execute").size();
		for(int i=0;i<numberOfTestCases;i++){
			System.out.println("TestCaseNumber:"+i);
			occCount=new HashMap<String, Integer>();
			String execute=singleSuiteData.get("Execute").get(i);
			if(execute.toLowerCase().equals("yes")){
				String testCaseDescription=singleSuiteData.get("testCaseDescription").get(i);
				testcaseID=Integer.parseInt(singleSuiteData.get("TestCaseID").get(i)) ;
				ExtentTest test=reportJD.createTest(testcaseID+" : "+testCaseDescription);
				startBrowserSession();
				configFileReader.setPropertyVal("sequence","true");
				try {
				for(int j=0;j<10;j++){
					String actionToRunLable="Action"+(j+1);
					String actionToRun=singleSuiteData.get(actionToRunLable).get(i);
					currentKeyWord=actionToRun;
					System.out.println("actionToRunLable:"+actionToRunLable);
					System.out.println("currentKeyWord:"+currentKeyWord);
						if(!currentKeyWord.equals("")){
							if(!occCount.containsKey(currentKeyWord)){
								occCount.put(currentKeyWord,0);
							}else{
								int occNum=occCount.get(currentKeyWord);
								occNum++;
								occCount.put(currentKeyWord,occNum);
							}
							runKeyWord(actionToRun,test);
//								updateSheet();
						}

				}
				} catch (Exception e) {
					logger.info(e.getMessage());
					String screenShot=GenerateScreenShot.getScreenShot(driver);
					ExtentTest node = test.createNode("Exception");
					node.fail(e.getMessage()+node.addScreenCaptureFromPath(screenShot));
				}
				endBrowserSession();
			}

		}
	}

	public void runKeyWord(String actionToRun,ExtentTest test) throws IOException, InterruptedException, SQLException, ParseException {
		String moduleToRun=actionToRun;
		IConnection ic=new IConnection(driver);
		ic_PaymentOption Payopt=new ic_PaymentOption(driver);
		ic_PayUPayment  PayU = new ic_PayUPayment(driver);
		Ic_Products products = new Ic_Products(driver);
		IC_Cart icCart=new IC_Cart(driver);
		ic_AccountConfirmation icAccountConfirmation = new ic_AccountConfirmation(driver);
		ICDelivery icDelivery=new ICDelivery(driver);
		ic_Magento_Login icMagento = new ic_Magento_Login(driver);
		MagentoOrderStatusPage orderStatus = new MagentoOrderStatusPage(driver);
		ic_MagentoOrderSAPnumber icOrderSAPnumber = new ic_MagentoOrderSAPnumber(driver);
		ic_AccountInformation verifyAcc = new ic_AccountInformation(driver,dataMap2);
		ic_NewAccountCreation newAcc = new ic_NewAccountCreation(driver);
		MagentoRetrieveCustomerDetailsPage custDetails = new MagentoRetrieveCustomerDetailsPage(driver);
		MagentoAccountInformation MagentoCustDetail = new MagentoAccountInformation(driver);
		Magento_UserInfoVerification Magentoverify = new Magento_UserInfoVerification(driver);
		MagentoRegisterNewUser MagentonewUser = new MagentoRegisterNewUser(driver);
		ICUpdateCustomer icUpdateUser = new ICUpdateCustomer(driver);
		ic_GiftCardPurchase icGiftCardPurchase = new ic_GiftCardPurchase(driver);
		admin_UserUpdate adminUserUpdate = new admin_UserUpdate(driver);
		customerValidationUpdates customerVerifyEdits = new customerValidationUpdates(driver);
		ic_Login ic_login = new ic_Login(driver);
		ic_CashDepositPayment ic_cashDepositPayment =new ic_CashDepositPayment(driver);
		SAPorderRelated SaporderRelated = new SAPorderRelated(driver,dataMap2);
		ICGiftCardVerification icGiftCardVerification = new ICGiftCardVerification(driver);
    	ic_GiftCardUsability GiftCardUsability = new ic_GiftCardUsability(driver);
		ic_existingAddress icExistingAddress = new ic_existingAddress(driver);
		ic_RedeemGiftCard icRedeemGiftCard = new ic_RedeemGiftCard(driver);
		ExtentTest test1=test.createNode(moduleToRun);
		int rowNumber=-1;
		if(dataMap2.containsKey(currentKeyWord+"++")) {
			rowNumber = findRowToRun(dataMap2.get(currentKeyWord + "++"), occCount.get(currentKeyWord), testcaseID);
		}
		int i = 0;
		switch (moduleToRun) {
			case "Login":
				ic.login(dataMap2.get(currentKeyWord+"++"),test1,rowNumber);
				break;
			case "ic_login":
				ic_login.Login_ic(dataMap2.get(currentKeyWord+"++"),test1,rowNumber);
				break;
			case "Logout":
				ic.logout(test1);
				break;
			case "CheckoutpaymentOption":
				Payopt.CheckoutpaymentOption(dataMap2.get(currentKeyWord+"++"),test1,rowNumber);
				break;
			case "PayUPagePayment":
				PayU.PayUPagePayment(dataMap2.get(currentKeyWord+"++"),test1,rowNumber);
				break;
			case "ProductSearch":
				products.ic_SelectProductAndAddToCart(dataMap2.get(currentKeyWord+"++"), test1, rowNumber);
				break;
			/*
			 * case "iCcartVerification": icCart.iCcartVerification(test1); break;
			 */
			case "deliveryPopulation":
				icDelivery.deliveryPopulation(dataMap2.get(currentKeyWord+"++"), test1, rowNumber);
				break;
			case "deliveryPopulationGiftCard":
				rowNumber = findRowToRun(dataMap2.get("deliveryPopulation++"), 0, testcaseID);
				icDelivery.deliveryPopulationGiftCard(dataMap2.get("deliveryPopulation++"), test1, rowNumber);
				break;
			case "Login_magento":
				icMagento.Login_magento(dataMap2.get(currentKeyWord+"++"), test1, rowNumber);
				break;
			case"OrderStatusSearch":
				orderStatus.navigateToOrderPage(dataMap2.get(currentKeyWord+"++"),test1,rowNumber);
				break;
			case "GenerateOrderSAPnumber":
				icOrderSAPnumber.GenerateOrderSAPnumber(dataMap2.get(currentKeyWord+"++"),test1,rowNumber);
				break;
			case "EnterNewUserDetails":
				//newAcc.EnterNewUserDetails(dataMap2.get(currentKeyWord+"++"),test1,rowNumber);
				break;
			case "Verify_Acount_Information":
				verifyAcc.Verify_Acount_Information(dataMap2.get(currentKeyWord+"++"),test1,rowNumber);
				break;
			case "accountCreation":
				newAcc.accountCreation(dataMap2.get(currentKeyWord+"++"), test1, rowNumber);
				break;	
			case "icAccountConfirmation":
				icAccountConfirmation.AccountCreationConfirmation(dataMap2.get(currentKeyWord+"++"), test1, rowNumber);
				break;
			case"RetrieveCustomerDetails":
				custDetails.retrieveCustomerDetails(dataMap2.get(currentKeyWord+"++"), test1, rowNumber);
				break;
			case "VadidateCustomerInfo_backend":
				rowNumber = findRowToRun(dataMap2.get("accountCreation++"), 0, testcaseID);
				MagentoCustDetail.VadidateCustomerInfo_backend(dataMap2.get("accountCreation++"), test1, rowNumber);
				break;
			case "Magento_UserInfoVerification":
    			rowNumber = findRowToRun(dataMap2.get("accountCreation++"), 0, testcaseID);
				//Magentoverify.Validate_UserInfobackend(dataMap2.get("accountCreation" + "++"),test1,rowNumber);
				break;
			case "CreateaccountBackend":
				MagentonewUser.CreateAccount_validateInfo_Backend(dataMap2.get(currentKeyWord+"++"),test1,rowNumber);
				break;
			case "ICUpdateUser":
				ArrayList<HashMap<String, ArrayList<String>>> mySheets=new ArrayList<HashMap<String, ArrayList<String>>>();
				mySheets.add(dataMap2.get(currentKeyWord+"++"));
				mySheets.add(dataMap2.get("ic_login++"));
				icUpdateUser.updateAccount(mySheets,test1,testcaseID);
				break;
			case "customerValidationUpdates":
				ArrayList<HashMap<String, ArrayList<String>>> adminsheets=new ArrayList<HashMap<String, ArrayList<String>>>();
				adminsheets.add(dataMap2.get(currentKeyWord+"++"));
				//rowNumber = findRowToRun(dataMap2.get("accountCreation++"), 0, testcaseID);
				customerVerifyEdits.VerifyCustomerinfoUpadtes(adminsheets,test1,testcaseID);
				break;
			case "adminUserUpdate":
				ArrayList<HashMap<String, ArrayList<String>>> adminSheets=new ArrayList<HashMap<String, ArrayList<String>>>();
				adminSheets.add(dataMap2.get(currentKeyWord+"++"));
				adminUserUpdate.editCustomerDetails(adminSheets,test1,testcaseID);
				break;
			case "ic_CashDepositPayment":
				ArrayList<HashMap<String, ArrayList<String>>> RequiredSheets = new ArrayList<HashMap<String, ArrayList<String>>>();
				RequiredSheets.add(dataMap2.get(currentKeyWord+"++"));
				RequiredSheets.add(dataMap2.get("Login_magento++"));
				ic_cashDepositPayment.InvoiceCashDeposit(RequiredSheets,test1,testcaseID);
			case "icGiftCardPurchase":
				icGiftCardPurchase.purchaseGiftCard(dataMap2.get(currentKeyWord+"++"), test1, rowNumber);	
				break;
			case "SAP_OrderRelated":
				SaporderRelated.SAP_OrderDetailVadidation(dataMap2.get(currentKeyWord+"++"),test1,rowNumber);
				break;
			case "icGiftCardVerificationSender":
				icGiftCardVerification.icGiftCardVerificationSender(dataMap2.get(currentKeyWord+"++"),test1,rowNumber);
				break;
			case "getnumberOfEmails":
				rowNumber = findRowToRun(dataMap2.get("icGiftCardVerificationSender++"), 0, testcaseID);
				icGiftCardVerification.getnumberOfEmails(dataMap2.get("icGiftCardVerificationSender++"),test1,rowNumber);
				break;
			case "VeriyGiftcardUsableity":
				GiftCardUsability.VeriyGiftcardUsableity(dataMap2.get(currentKeyWord+"++"),test1,rowNumber);
				break;
			case "icRedeemGiftCard":
				icRedeemGiftCard.redeemGiftCard(dataMap2.get(currentKeyWord+"++"), test1, rowNumber);
				break;
			case "icExistingAddress":
				icExistingAddress.AddressThere(test1);
				break;
		}
	}

	public int findRowToRun(HashMap<String, ArrayList<String>> input,int occCount,int testcaseID){
		int numberRows=input.get("TCID").size();
		int rowNumber=-1;
		occCount=occCount+1;
		for(int i=0;i<numberRows;i++){
			if(input.get("TCID").get(i).equals(Integer.toString(testcaseID))&&input.get("occurence").get(i).equals(Integer.toString(occCount))){
				rowNumber=i;
			}
		}
		return rowNumber;
	}


	public void startBrowserSession()
	{
		driver=null;
		if(driver == null){
			configFileReader = new ConfigFileReader();
			logger.info("Initializing the driver");


			browserName = System.getProperty("BrowserType");
			if(browserName==null){
				logger.info("System property returned Null browser type. So getting data from Config file");

				browserName=ConfigFileReader.getPropertyVal("BrowserType");

			}

			driver = TestCaseBase.initializeTestBaseSetup(browserName);
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

			navigateURL = System.getProperty("URL");
			if(navigateURL==null){
				logger.info("System property returned Null URL. So getting data from Config file");
				Report.info("System property returned Null URL. So getting data from Config file");
				navigateURL = ConfigFileReader.getPropertyVal("URL");
			}

				navigateURL = ConfigFileReader.getPropertyVal("URL");
			}
			logger.info("Navigate to URL");
			Report.info("Navigating to URL: "+navigateURL);

			driver.navigate().to(navigateURL);
			driver.manage().window().maximize();
			driver.navigate().refresh();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.info("Browser name is "+browserName);

			logger.info("App URL: "+ navigateURL);
			Values.app= navigateURL;
			Values.browser=browserName;
		}

	public void endBrowserSession() throws IOException {
		driver.close();
		writeToExcel();
	}
	public void writeToExcel() throws IOException {
		FileOutputStream outputStream = new FileOutputStream(createFile());
		XSSFWorkbook workbook= new XSSFWorkbook();;
		XSSFSheet sheet;
		for(int i=0;i<dataMap2.size() ;i++){
			Object[] keys = dataMap2.keySet().toArray();
			sheet = workbook.createSheet(keys[i].toString());
			int numCol=dataMap2.get(keys[i]).size();
			Object[] colArray = dataMap2.get(keys[i]).keySet().toArray();
			int rowNum = dataMap2.get(keys[i]).get(colArray[0]).size();
			for(int j=0;j<=rowNum;j++){
				Row row = sheet.createRow(j);
				if (j==0){
					for(int z=0;z<numCol;z++){
						Cell cell = row.createCell(z);
						cell.setCellValue(colArray[z].toString());
					}
				}else{

					for(int z=0;z<numCol;z++){
						Cell cell = row.createCell(z);
						cell.setCellValue((String) dataMap2.get(keys[i]).get(colArray[z]).get(j-1));
					}
				}
			}
		}

		workbook.write(outputStream);
	}

	public File createFile() throws IOException {
		File myObj = new File(System.getProperty("user.dir")+"\\reports\\Datasheet.xlsx");
		return myObj;
	}




}
