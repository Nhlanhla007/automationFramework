package KeywordManager;


import ic_PageObjects.IC_ReturnToConfirmOrderStatus;
import spm_PageObjects.SPM_ArticleRangeValidation;
import spm_PageObjects.SPM_Delivery;
import spm_PageObjects.SPM_LaunchPortal;
import spm_MagentoPageObjects.SPM_Magento_Enquries;
import spm_PageObjects.SPM_PayUPayment;
import spm_PageObjects.SPM_PaymentOption;
import spm_MagentoPageObjects.SPM_MagentoOrderSAPnumber;
import spm_MagentoPageObjects.SPM_MagentoOrderStatusPage;
import spm_PageObjects.SPM_NewAccountCreation;
import spm_MagentoPageObjects.SPM_MagentoRetrieveCustomerDetailsPage;
import spm_MagentoPageObjects.SPM_Magento_CreditApplicationVerification;
import spm_MagentoPageObjects.SPM_Magento_LayByValidation;
import spm_MagentoPageObjects.SPM_Magento_RangeValidation;
import spm_MagentoPageObjects.SPM_Magento_UserInfoVerification;
import spm_PageObjects.SPM_LaunchPortal;
import spm_PageObjects.SPM_ProductSearch;
import spm_PageObjects.SPM_RetriveOrderID;
import spm_PageObjects.SPM_creditApplication;
import spm_PageObjects.SPM_layBy;
import evs_MagentoPageObjects.*;
import evs_MagentoPageObjects.EVS_MagentoOrderStatusPage;
import ic_PageObjects.*;
import SAP_HanaDB.*;
import SRS.srs_Login;
import SRS.srs_LogonStoreByOrderPayload;
import SRS.srs_salesOrder_DeliverStatus;
import com.aventstack.extentreports.ExtentTest;

import emailverification.ICGiftCardVerification;
import emailverification.ICWishlistverification;
import emailverification.ic_PasswordForgotEmailVerification;
import emailverification.ic_ResetPasswordEmailLink;
import evs_MagentoPageObjects.EVS_Admin_Reorder;
import evs_MagentoPageObjects.EVS_GiftCardReport;
import evs_MagentoPageObjects.EVS_MagentoCancelUpaidEFT;
import emailverification.*;
import evs_MagentoPageObjects.EVS_MagentoOrderSAPnumber;
import evs_MagentoPageObjects.EVS_MagentoRegisterNewUser;
import evs_MagentoPageObjects.EVS_MagentoRetrieveCustomerDetailsPage;
import evs_MagentoPageObjects.EVS_Magento_Login;
import evs_MagentoPageObjects.EVS_Magento_UserInfoVerification;
import evs_MagentoPageObjects.EVS_admin_UserUpdate;
import evs_PageObjects.*;
import ic_MagentoPageObjects.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Action;
import utils.DataTable2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class JDGKeyManager {
    WebDriver driver;
    DataTable2 dataTable2;
    Action action;
    LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> dataMap2 = new LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>>();

    public JDGKeyManager(WebDriver driver, DataTable2 dataTable2, LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> dataMap2){
        this.driver=driver;
        this.dataTable2=dataTable2;
        this.dataMap2=dataMap2;
        action = new Action(driver);
    }
    public void setDriver(WebDriver driver){
        this.driver=driver;
    }

    public void runKeyWord (String actionToRun,int testcaseID,HashMap<String, Integer> occCount, ExtentTest test1) throws Exception {
        String moduleToRun = actionToRun;
        //IC classes below
        IConnection ic = new IConnection(driver, dataTable2);
        Magento_UserInfoVerification Magentoverify = new Magento_UserInfoVerification(driver, dataTable2);
        ic_PaymentOption Payopt = new ic_PaymentOption(driver, dataTable2);
        ic_PayUPayment PayU = new ic_PayUPayment(driver, dataTable2);
        Ic_Products products = new Ic_Products(driver, dataTable2);
        IC_Cart icCart = new IC_Cart(driver, dataTable2);
        ic_AccountConfirmation icAccountConfirmation = new ic_AccountConfirmation(driver, dataTable2);
        ICDelivery icDelivery = new ICDelivery(driver, dataTable2);
        ic_Magento_Login icMagento = new ic_Magento_Login(driver, dataTable2);
        MagentoOrderStatusPage orderStatus = new MagentoOrderStatusPage(driver, dataTable2);
        ic_MagentoOrderSAPnumber icOrderSAPnumber = new ic_MagentoOrderSAPnumber(driver, dataTable2);
        ic_AccountInformation verifyAcc = new ic_AccountInformation(driver, dataMap2);
        ic_NewAccountCreation newAcc = new ic_NewAccountCreation(driver, dataTable2);
        MagentoRetrieveCustomerDetailsPage custDetails = new MagentoRetrieveCustomerDetailsPage(driver, dataTable2);
        MagentoAccountInformation MagentoCustDetail = new MagentoAccountInformation(driver, dataTable2);
        MagentoRegisterNewUser MagentonewUser = new MagentoRegisterNewUser(driver, dataTable2);
        ICUpdateCustomer icUpdateUser = new ICUpdateCustomer(driver, dataTable2);
        ic_GiftCardPurchase icGiftCardPurchase = new ic_GiftCardPurchase(driver, dataTable2);
        admin_UserUpdate adminUserUpdate = new admin_UserUpdate(driver, dataTable2);
        customerValidationUpdates customerVerifyEdits = new customerValidationUpdates(driver, dataTable2);
        ic_Login ic_login = new ic_Login(driver, dataTable2);
        ic_verifyDeliveryOptions icDeliveryOptionDisplay = new ic_verifyDeliveryOptions(driver, dataTable2);
        ic_invalidLoginCreds ic_invalidCredslogin = new ic_invalidLoginCreds(driver, dataTable2);
        ic_LoginPasswordIsSecured icPasswordSecured = new ic_LoginPasswordIsSecured(driver, dataTable2);
        ic_forgotPasswordLink icforgottenPassLink = new ic_forgotPasswordLink(driver, dataTable2);
        verifyForgotPassword icVerifyForgotPass = new verifyForgotPassword(driver, dataTable2);
        ic_PasswordForgotEmailVerification icForgotEmailSent = new ic_PasswordForgotEmailVerification(driver, dataTable2);
        ic_ResetPasswordEmailLink ResetPasswordLink = new ic_ResetPasswordEmailLink(driver, dataTable2);
        ic_CashDepositPayment ic_cashDepositPayment = new ic_CashDepositPayment(driver, dataTable2);
        SAPorderRelated SaporderRelated = new SAPorderRelated(driver, dataMap2, dataTable2);
        ICGiftCardVerification icGiftCardVerification = new ICGiftCardVerification(driver, dataTable2);
        ic_GiftCardUsability GiftCardUsability = new ic_GiftCardUsability(driver, dataTable2);
        ic_existingAddress icExistingAddress = new ic_existingAddress(driver, dataTable2);
        ic_RedeemGiftCard icRedeemGiftCard = new ic_RedeemGiftCard(driver, dataTable2);
        ic_SearchMinimumCharacter icMinimumCharacter = new ic_SearchMinimumCharacter(driver, dataTable2);
        SAPCustomerRelated customerDB = new SAPCustomerRelated(driver, dataMap2, dataTable2);
        IC_RetriveOrderID ic_RetriveOrderID = new IC_RetriveOrderID(driver, dataTable2);
        IC_RetriveGiftCardOrderId ic_RetriveGiftCardOrderID = new IC_RetriveGiftCardOrderId(driver, dataTable2);
        admin_GiftCardReport giftCardReport = new admin_GiftCardReport(driver, dataTable2);
        Magento_CancelSalerOrderCreditMemo CancelSalerOrderCreditMemo = new Magento_CancelSalerOrderCreditMemo(driver, dataTable2);
        Magento_CancelSalesorderVerification CancelSalesorderVerification = new Magento_CancelSalesorderVerification(driver, dataTable2);
        Magento_CreditApp_NavigateFilter CreditApp_NavigateFilter = new Magento_CreditApp_NavigateFilter(driver, dataTable2);
        Magento_CreditStatusVerification CreditStatusVerification = new Magento_CreditStatusVerification(driver, dataTable2);
        ic_RefreshLogoHomepage icLogo = new ic_RefreshLogoHomepage(driver, dataTable2);
        ic_EnterBasicDetails icEnterBasicDetails = new ic_EnterBasicDetails(driver, dataTable2);
        ic_SpouseDetails icEnterSpouseInfo = new ic_SpouseDetails(driver, dataTable2);
        ic_ContactDetailsLoan icContactInfo = new ic_ContactDetailsLoan(driver, dataTable2);
        ic_PopularSearch PopularSearch = new ic_PopularSearch(driver, dataTable2);
        ic_SearchTextReturningNoResult icReturnNoResults = new ic_SearchTextReturningNoResult(driver, dataTable2);
        IC_CreditAppEmploymentDetails creditAppEmployDetails = new IC_CreditAppEmploymentDetails(driver, dataTable2);
        IC_CreditAppAddressDetails creditAppAddressDetails = new IC_CreditAppAddressDetails(driver, dataTable2);
        ic_SubscriberNewsletter_DuplicateEmailaddress ic_SubscribeNews_DupliEmailID = new ic_SubscriberNewsletter_DuplicateEmailaddress(driver, dataTable2);
        ic_newLetterInvalidEmail icNewsletterEmail = new ic_newLetterInvalidEmail(driver, dataTable2);
        ic_Subscriber_Newsletter_ValidEmailaddress ic_SubscribeNewsletter = new ic_Subscriber_Newsletter_ValidEmailaddress(driver, dataTable2);
        IC_ProductsSortBy productsSortBy = new IC_ProductsSortBy(driver, dataTable2);
        ic_WishlistToCart IC_WishlistToCart = new ic_WishlistToCart(driver, dataTable2);
        ic_verifyWishlistItem verifyWishlistItem = new ic_verifyWishlistItem(driver, dataTable2);
        ic_RemoveFromcart RemoveFromcart = new ic_RemoveFromcart(driver, dataTable2);
        ic_WishList WishList = new ic_WishList(driver, dataTable2);
        ic_CompareProducts productsCompared = new ic_CompareProducts(driver, dataTable2);
        ic_NavigetoWishlist NavigetoWishlist = new ic_NavigetoWishlist(driver, dataTable2);
        IC_verifyLogin ic_verifyLogin = new IC_verifyLogin(driver, dataTable2);
        IC_IncreaseQuanityInCart increQuantity = new IC_IncreaseQuanityInCart(driver, dataTable2);
        IC_RemoveItemsFromCart removeItemsFromCart = new IC_RemoveItemsFromCart(driver, dataTable2);
        ic_SendWishlistToEmail SendWishlistToEmail = new ic_SendWishlistToEmail(driver, dataTable2);
        ICWishlistverification icEmailWishlistverification = new ICWishlistverification(driver, dataTable2);
        RedirectToProdDetailPageFromCart redirectAndVerify = new RedirectToProdDetailPageFromCart(driver, dataTable2);
        IC_Pagination pagination = new IC_Pagination(driver, dataTable2);
        IC_Parallel_login parallel_ic_Login = new IC_Parallel_login(driver, dataTable2);
        SapRSI sapRSI = new SapRSI(driver, dataTable2);
        ic_validateProductSKU SKUproduct = new ic_validateProductSKU(driver, dataTable2);
        ic_validateDifferentPaymentOptions icPaymentOptions = new ic_validateDifferentPaymentOptions(driver, dataTable2);
        IC_MyOrders ic_MyOrder = new IC_MyOrders(driver, dataTable2);
        IC_magento_LaunchPortal icMagentoLaunchPortal=new IC_magento_LaunchPortal(driver,dataTable2);
        
        //New functionality for backlog in IC
        ic_ClickAndCollect ClickAndCollect = new ic_ClickAndCollect(driver, dataTable2);
		IC_RetivedLoggedUserBillingAddress LoggedUserBillingAddress =new IC_RetivedLoggedUserBillingAddress(driver, dataTable2);
		srs_Login SRSLogin =new srs_Login(driver, dataTable2);
		srs_LogonStoreByOrderPayload SRSLogonStore = new srs_LogonStoreByOrderPayload(driver,dataTable2);
		srs_salesOrder_DeliverStatus SRSDeliverStatus = new srs_salesOrder_DeliverStatus(driver, dataTable2);
		Magento_FetchOrderpayload MagentoFetchOrderpayload = new Magento_FetchOrderpayload(driver, dataTable2);
		admin_ReOrder adminReorder = new admin_ReOrder(driver, dataTable2);
		ic_MagentoCancelUpaidEFT ic_MagentoCancelUpaidEFT = new ic_MagentoCancelUpaidEFT(driver, dataTable2);
        IC_ReturnToConfirmOrderStatus ic_toConfirmOrderStatus = new IC_ReturnToConfirmOrderStatus(driver, dataTable2);
        IC_ESDpurchase ic_esdLearnMore = new IC_ESDpurchase(driver, dataTable2);
        ic_TVLicenceValidation ic_TVLicenseValidation = new ic_TVLicenceValidation(driver, dataTable2);
        IC_validateRegistrationForm ValidateRegForm = new IC_validateRegistrationForm(driver,dataTable2);
        ic_TVLicenceApproval ic_tvLicenceApproval = new ic_TVLicenceApproval(driver, dataTable2);
        OpenGateSales openGateSales=new OpenGateSales(driver,dataTable2);
        IC_myTVLicense ic_MyTVLicense = new IC_myTVLicense(driver, dataTable2);
        
        //evs classes below
        EVS_Login evs_Login = new EVS_Login(driver, dataTable2);
        EVS_ProductSearch evs_productSearch = new EVS_ProductSearch(driver, dataTable2);
        EVS_MagentoOrderSAPnumber evs_magentoSAPNumber = new EVS_MagentoOrderSAPnumber(driver, dataTable2);
        EVS_Delivery evs_delivery = new EVS_Delivery(driver, dataTable2);
        EVS_NewAccountCreation evs_NewAccountCreation = new EVS_NewAccountCreation(driver, dataTable2);
        EVS_PaymentOption evs_PaymentOption = new EVS_PaymentOption(driver, dataTable2);
        EVS_PayUPayment evs_PayUPayment = new EVS_PayUPayment(driver, dataTable2);
        EVS_RetriveOrderID evs_RetriveOrderID = new EVS_RetriveOrderID(driver, dataTable2);
        EVS_MagentoOrderStatusPage evs_orderStatus = new EVS_MagentoOrderStatusPage(driver, dataTable2);
        EVS_Magento_Login evs_Login_magento=new EVS_Magento_Login(driver, dataTable2);
        EVS_SAPorderRelated evs_SAPorderRelated = new EVS_SAPorderRelated(driver,dataMap2, dataTable2);
        EVS_Cart evs_cart = new EVS_Cart(driver, dataTable2) ;
        EVS_IncreaseQuanityInCart evs_increaseQuantityInCart = new EVS_IncreaseQuanityInCart(driver, dataTable2);
        EVS_RemoveItemsFromCart evs_RemoveItemsFromCart = new EVS_RemoveItemsFromCart(driver, dataTable2);
        EVS_Parallel_login parallel_evs_Login = new EVS_Parallel_login(driver, dataTable2);
        EVS_invalidLoginCreds evs_invalidCredslogin = new EVS_invalidLoginCreds(driver, dataTable2);	
        EVS_LoginPasswordIsSecured evs_PasswordSecured = new EVS_LoginPasswordIsSecured(driver, dataTable2);
        EVS_MagentoRetrieveCustomerDetailsPage evs_custDetails = new EVS_MagentoRetrieveCustomerDetailsPage(driver, dataTable2);
        EVS_Magento_UserInfoVerification evs_Magentoverify = new EVS_Magento_UserInfoVerification(driver, dataTable2);
        EVS_SAPCustomerRelated evs_customerDB = new EVS_SAPCustomerRelated(driver, dataMap2, dataTable2);
        EVS_RedeemGiftCard evs_RedeemGiftCard = new EVS_RedeemGiftCard(driver, dataTable2);
        EVS_verifyForgotPassword evs_VerifyForgotPass = new EVS_verifyForgotPassword(driver, dataTable2);
        EVS_forgotPasswordLink evs_forgottenPassLink = new EVS_forgotPasswordLink(driver, dataTable2);
        EVS_RedirectToProdDetailPageFromCart evs_RedirectToProdDetailPageFromCart = new EVS_RedirectToProdDetailPageFromCart(driver, dataTable2);
        EVS_MagentoRegisterNewUser evs_MagentonewUser = new EVS_MagentoRegisterNewUser(driver, dataTable2);
        EVS_admin_UserUpdate evs_adminUserUpdate = new EVS_admin_UserUpdate(driver, dataTable2);
        EVS_UpdateCustomer evs_UpdateUser = new EVS_UpdateCustomer(driver, dataTable2);
        EVS_clearWishList evs_verifyWishlistItem = new EVS_clearWishList(driver, dataTable2);
        EVS_WishlistToCart evs_WishlistToCart = new EVS_WishlistToCart(driver, dataTable2);
        IC_LaunchPortal lp= new IC_LaunchPortal(driver, dataTable2);
        EVS_NavigetoWishlist evs_NavigetoWishlist = new EVS_NavigetoWishlist(driver, dataTable2);
        EVS_RemoveFromcart evs_RemoveFromcart = new EVS_RemoveFromcart(driver, dataTable2);
        EVS_SendWishlistToEmail evs_SendWishlistToEmail = new EVS_SendWishlistToEmail(driver, dataTable2);
        EVS_newLetterInvalidEmail evs_NewsletterEmail = new EVS_newLetterInvalidEmail(driver, dataTable2);
        EVS_SubscriberNewsletter_DuplicateEmailaddress evs_SubscribeNews_DupliEmailID = new EVS_SubscriberNewsletter_DuplicateEmailaddress(driver, dataTable2);
        EVS_LaunchPortal evs_lp= new EVS_LaunchPortal(driver, dataTable2);
        EVS_Subscriber_Newsletter_ValidEmailaddress evs_SubscribeNewsletter = new EVS_Subscriber_Newsletter_ValidEmailaddress(driver, dataTable2);
        EVS_SapRSI evs_sapRSI = new EVS_SapRSI(driver, dataTable2);
        EVS_CashDepositPayment evs_cashDeposit = new EVS_CashDepositPayment(driver, dataTable2);
        EVS_PopularSearch evs_PopularSearch = new EVS_PopularSearch(driver, dataTable2);
        EVS_SearchTextReturningNoResult evs_ReturnNoResults = new EVS_SearchTextReturningNoResult(driver, dataTable2);
        EVS_SearchTextResult evs_SearchText = new EVS_SearchTextResult(driver, dataTable2);		
        EVS_RefreshLogoHomepage evs_Logo = new EVS_RefreshLogoHomepage(driver, dataTable2);
        EVS_validateDifferentPaymentOptions evs_PaymentOptions = new EVS_validateDifferentPaymentOptions(driver, dataTable2);
        EVS_existingAddress evs_ExistingAddress = new EVS_existingAddress(driver, dataTable2);
        EVS_GiftCardPurchase evs_GiftCardPurchase = new EVS_GiftCardPurchase(driver, dataTable2);
        EVS_RetriveGiftCardOrderId evs_RetriveGiftCardOrderID = new EVS_RetriveGiftCardOrderId(driver, dataTable2);
        EVS_PasswordForgotEmailVerification evsForgotEmailSent = new EVS_PasswordForgotEmailVerification(driver, dataTable2);
        EVS_verifyForgotPassword evsVerifyForgotPass = new EVS_verifyForgotPassword(driver, dataTable2);
        EVS_ResetPasswordEmailLink evsResetPasswordLink = new EVS_ResetPasswordEmailLink(driver, dataTable2);
        EVS_GiftCardVerification evsGiftCardVerification = new EVS_GiftCardVerification(driver, dataTable2);
        EVS_GiftCardReport evs_giftCardReport=new EVS_GiftCardReport(driver, dataTable2);
        EVS_CompareProducts evs_productsCompared = new EVS_CompareProducts(driver, dataTable2);
        EVS_MyOrders evs_myOrders = new EVS_MyOrders(driver, dataTable2);
        EVS_Magento_LaunchPortal evsMagentoLaunchPortal=new EVS_Magento_LaunchPortal(driver,dataTable2);
        EVS_MagentoCancelUpaidEFT evs_MagentoCancelUpaidEFT = new EVS_MagentoCancelUpaidEFT(driver, dataTable2);
        EVS_ReturnToConfirmOrderStatus evs_toConfirmOrderStatus = new EVS_ReturnToConfirmOrderStatus(driver, dataTable2);
        EVS_CancelOrder_CreditMemo cancelCreditMemo=new EVS_CancelOrder_CreditMemo(driver,dataTable2);
        EVS_Admin_Reorder evs_admin_reOrder = new EVS_Admin_Reorder(driver, dataTable2);
        evs_TVLicenceApproval evs_TvLicenseApproval = new evs_TVLicenceApproval(driver, dataTable2);
        evs_TVLicenceValidation evs_TvLicense = new evs_TVLicenceValidation(driver, dataTable2);
        EVS_OpenGateSales evs_openGateSales=new EVS_OpenGateSales(driver,dataTable2);
        EVS_myTVLicense evs_MyTVLicense = new EVS_myTVLicense(driver, dataTable2);
        EVS_validateRegistrationForm evs_ValidateRegForm = new EVS_validateRegistrationForm(driver,dataTable2);
        EVS_BundleArticleCreation evs_bundleCreation = new EVS_BundleArticleCreation(driver,dataTable2);
        EVS_BundleArticleFrontEnd evs_bundleFrontEnd = new EVS_BundleArticleFrontEnd(driver, dataTable2);
        
        //Sleepmasters
        SPM_ArticleRangeValidation articleValidation = new SPM_ArticleRangeValidation(driver, dataTable2);
        SPM_Delivery sleepMastersDelivery = new SPM_Delivery(driver, dataTable2);
        SPM_PaymentOption sleepMastersCheckOut = new SPM_PaymentOption(driver, dataTable2);
        SPM_LaunchPortal spm_launchPortal = new SPM_LaunchPortal(driver, dataTable2);
        SPM_ProductSearch spm_productSearch = new SPM_ProductSearch(driver, dataTable2);
        SPM_Magento_RangeValidation spm_magentoRange = new SPM_Magento_RangeValidation(driver, dataTable2);
        SPM_Magento_LayByValidation spm_laybyMagento = new SPM_Magento_LayByValidation(driver, dataTable2);
        SPM_Magento_Enquries spm_Enquries =new SPM_Magento_Enquries(driver, dataTable2);
        SPM_PayUPayment spm_Payment = new SPM_PayUPayment(driver, dataTable2);
        SPM_RetriveOrderID spm_RetireveOrderID = new SPM_RetriveOrderID(driver, dataTable2);
        SPM_MagentoOrderStatusPage spm_OrderStatusSearch = new SPM_MagentoOrderStatusPage(driver, dataTable2);
        SPM_MagentoOrderSAPnumber spm_GenerateSAPNumber = new SPM_MagentoOrderSAPnumber(driver, dataTable2);
        SPM_SAPorderRelated spm_OrderRelated = new SPM_SAPorderRelated(driver, dataMap2, dataTable2);
        SPM_layBy spm_layby = new SPM_layBy(driver, dataTable2);
        SPM_NewAccountCreation spm_accountCreat = new SPM_NewAccountCreation(driver, dataTable2);
        SPM_MagentoRetrieveCustomerDetailsPage spm_RetrieveCustomer = new SPM_MagentoRetrieveCustomerDetailsPage(driver, dataTable2);
        SPM_Magento_UserInfoVerification spm_UserInfoVerification = new SPM_Magento_UserInfoVerification(driver, dataTable2);
        SPM_creditApplication spm_creditApp = new SPM_creditApplication(driver, dataTable2);
        SPM_Magento_CreditApplicationVerification spm_creditAppMagento = new SPM_Magento_CreditApplicationVerification(driver, dataTable2);
        
        int rowNumber = -1;
        if (dataMap2.containsKey(moduleToRun + "++")) {
            rowNumber = findRowToRun(dataMap2.get(moduleToRun + "++"), occCount.get(moduleToRun), testcaseID);
        }
        int i = 0;
        WebElement el = null;
        switch (moduleToRun) {
            case "Login":
                ic.login(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "ic_login":
                 ic_login.Login_ic(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "ic_invalidCredslogin":
                ic_invalidCredslogin.invalidLogin_ic(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "icPasswordSecured":
                icPasswordSecured.loginPasswordSafe(dataMap2.get("ic_login++"), test1, rowNumber);
                break;
            case "icForgotPasswordLink":
                icforgottenPassLink.forgotPasswordLink(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "icVerifyForgotPass":
                icVerifyForgotPass.forgotPasswordPage(dataMap2.get("accountCreation++"), test1, rowNumber);
                break;
            case "icEmailSentVerification":
                icForgotEmailSent.icVerifyNewPasswordEmailSent(dataMap2.get("accountCreation++"), test1, rowNumber);
                break;
            case "icResetForgottenPassword":
                ResetPasswordLink.clickLinkOnGmail(dataMap2.get(moduleToRun+ "++"), test1, rowNumber);
                ResetPasswordLink.resetNewPassword(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                ResetPasswordLink.clickUsedResetLink(dataMap2.get(moduleToRun+ "++"), test1, rowNumber);
                break;
            case "Logout":
                ic.logout(test1);
                break;
            case "CheckoutpaymentOption":
                Payopt.CheckoutpaymentOption(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "CheckoutpaymentOptionGiftCard":
                Payopt.CheckoutpaymentOptionGiftCard(dataMap2.get("deliveryPopulation++"),test1,rowNumber);
                break;
            case "PayUPagePayment":
                PayU.PayUPagePayment(dataMap2.get(moduleToRun+ "++"), test1, rowNumber);
                break;
            case "ProductSearch":
                products.ic_SelectProductAndAddToCart(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "ClearCart":
                icCart.removeAllItemsInCart(test1);
                break;
            case "CompareProducts":
                productsCompared.validateAddedProductsCompare(test1, el);
                productsCompared.clearAllProduct(test1, el);
                break;
            /*
             * case "iCcartVerification": icCart.iCcartVerification(test1); break;
             */
            case "deliveryPopulation":
                icDelivery.deliveryPopulation(dataMap2.get("deliveryPopulation" + "++"), test1, rowNumber);
                break;
            case "deliveryPopulationGiftCard":
                rowNumber = findRowToRun(dataMap2.get("deliveryPopulation++"), 0, testcaseID);
                icDelivery.deliveryPopulationGiftCard(dataMap2.get("deliveryPopulation++"), test1, rowNumber);
                break;
            case "Login_magento":
                icMagento.Login_magento(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "OrderStatusSearch":
                orderStatus.navigateToOrderPage(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "GenerateOrderSAPnumber":
                icOrderSAPnumber.GenerateOrderSAPnumber(dataMap2.get(moduleToRun+ "++"), test1, rowNumber);
                break;
            case "EnterNewUserDetails":
                //newAcc.EnterNewUserDetails(dataMap2.get(currentKeyWord+"++"),test1,rowNumber);
                break;
//			case "Verify_Acount_Information":
//				verifyAcc.Verify_Acount_Information(dataMap2.get(currentKeyWord+"++"),test1,rowNumber);
//				break;
            case "accountCreation":
                newAcc.accountCreation(dataMap2.get(moduleToRun + "++"), test1, rowNumber);

                break;
            case "icAccountConfirmation":
                icAccountConfirmation.AccountCreationConfirmation(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "RetrieveCustomerDetails":
                custDetails.retrieveCustomerDetails(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "VadidateCustomerInfo_backend":
                rowNumber = findRowToRun(dataMap2.get("accountCreation++"), 0, testcaseID);
                MagentoCustDetail.VadidateCustomerInfo_backend(dataMap2.get("accountCreation++"), test1, rowNumber);
                break;
            case "Magento_UserInfoVerification":
                rowNumber = findRowToRun(dataMap2.get("accountCreation++"), 0, testcaseID);
                Magentoverify.Validate_UserInfobackend(dataMap2.get("accountCreation" + "++"), test1, rowNumber);
                break;
            case "CreateaccountBackend":
                MagentonewUser.CreateAccount_validateInfo_Backend(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "ICUpdateUser":
                ArrayList<HashMap<String, ArrayList<String>>> mySheets = new ArrayList<HashMap<String, ArrayList<String>>>();
                mySheets.add(dataMap2.get("ICUpdateUser"+ "++"));
                mySheets.add(dataMap2.get("ic_login++"));
                icUpdateUser.updateAccount(mySheets, test1, testcaseID);
                break;
            case "customerValidationUpdates":
                ArrayList<HashMap<String, ArrayList<String>>> adminsheets = new ArrayList<HashMap<String, ArrayList<String>>>();
                adminsheets.add(dataMap2.get("customerValidationUpdates" + "++"));
                //rowNumber = findRowToRun(dataMap2.get("accountCreation++"), 0, testcaseID);
                customerVerifyEdits.VerifyCustomerinfoUpadtes(adminsheets, test1, testcaseID);
                break;
            case "adminUserUpdate":
                ArrayList<HashMap<String, ArrayList<String>>> adminSheets = new ArrayList<HashMap<String, ArrayList<String>>>();
                adminSheets.add(dataMap2.get(moduleToRun + "++"));
                adminUserUpdate.editCustomerDetails(adminSheets, test1, testcaseID);
                break;
            case "ic_CashDepositPayment":
                ArrayList<HashMap<String, ArrayList<String>>> RequiredSheets = new ArrayList<HashMap<String, ArrayList<String>>>();
                RequiredSheets.add(dataMap2.get(moduleToRun + "++"));
                RequiredSheets.add(dataMap2.get("Login_magento++"));
                ic_cashDepositPayment.InvoiceCashDeposit(RequiredSheets, test1, testcaseID);
                break;
            case "icGiftCardPurchase":
                icGiftCardPurchase.purchaseGiftCard(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "SAP_OrderRelated":
                SaporderRelated.SAP_OrderDetailVadidation(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "icGiftCardVerificationSender":
                icGiftCardVerification.icGiftCardVerificationSender(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "getnumberOfEmails":
                rowNumber = findRowToRun(dataMap2.get("icGiftCardVerificationSender++"), 0, testcaseID);
                icGiftCardVerification.clearEmail(dataMap2.get("icGiftCardVerificationSender++"), test1, rowNumber);
                break;
            case "giftCardReport":
                ArrayList<HashMap<String, ArrayList<String>>> mySheet = new ArrayList<HashMap<String, ArrayList<String>>>();
                mySheet.add(dataMap2.get(moduleToRun + "++"));
                giftCardReport.giftCardReports(mySheet, test1, testcaseID);
                break;
            case "VeriyGiftcardUsableity":
                GiftCardUsability.VeriyGiftcardUsableity(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "icRedeemGiftCard":
                icRedeemGiftCard.redeemGiftCard(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "EnterBasicDetails":
                icEnterBasicDetails.enterBasicInfor(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "EnterSpouseInfor":
                icEnterSpouseInfo.enterSpouseDetails(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "EnterContact":
                icContactInfo.enterContactDetailsForLoan(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "icInvalidEmailNewsLetter":
                icNewsletterEmail.ic_NewsLetterInvalidEmail(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "icExistingAddress":
                icExistingAddress.AddressThere(test1);
                break;
            case "icSearchMinimumCharacter":
                icMinimumCharacter.icValidMinimumSearch(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "ic_RetriveOrderID":
                ic_RetriveOrderID.RetriveOrderID(test1);
                break;
            case "ic_RetriveGiftCardOrderID":
                ic_RetriveGiftCardOrderID.RetriveOrderID(test1);
                break;
            case "SapCustomer":
                ArrayList<HashMap<String, ArrayList<String>>> sheets = new ArrayList<HashMap<String, ArrayList<String>>>();
                sheets.add(dataMap2.get("accountCreation++"));
                sheets.add(dataMap2.get("deliveryPopulation++"));
                //sheets.add(dataMap2.get("SapCustomer++"));//Falls away
                sheets.add(dataMap2.get("ICUpdateUser++"));
                sheets.add(dataMap2.get("CreateaccountBackend++"));
                sheets.add(dataMap2.get("adminUserUpdate++"));
                customerDB.sapDbTests(dataMap2.get(moduleToRun + "++"), sheets, test1, testcaseID, rowNumber);
                break;
            case "CancelSalerOrderCreditMemo":
                CancelSalerOrderCreditMemo.magento_CancelSalesOrder(test1, rowNumber);
                break;
            case "CancelSalesorderVerification":
                CancelSalesorderVerification.verifyCancelOrderdetails_commentHistory(test1, rowNumber);
                break;
            case "CreditApp_NavigateFilter":
                CreditApp_NavigateFilter.VerifyCreditAppSelection(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "CreditStatusVerification":
                CreditStatusVerification.VerifyCreditAppStatus(dataMap2.get("CreditStatusVerification++"), test1, rowNumber);
                CreditStatusVerification.VerifyCreditAppStatus(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "icLogoHomepage":
                icLogo.homepageLogo(test1);
                break;
            case "icPopularSearch":
                PopularSearch.VerifyPopularSearch(test1, rowNumber);
                break;
            case "icSearchNoResultsReturned":
                icReturnNoResults.ic_DoesNotExtistSearch(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "CreditEnterEmploymentDetails":
                creditAppEmployDetails.dataInput(dataTable2, test1);
                break;
            case "CreditEnterAddressDetails":
                creditAppAddressDetails.dataInput(dataTable2, test1);
                break;
            case "ic_SubscribeNews_DupliEmailID":
                ic_SubscribeNews_DupliEmailID.SubscribeNewsletter_DuplicateEmail(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "IC_ProductsSortBy":
                productsSortBy.sortBy(test1);
                break;
            case "ic_NavigetoWishlist":
                NavigetoWishlist.NavigateToWishlist_verifymsg(test1);
                break;
            case "IC_ClearWishList":
                verifyWishlistItem.handleWishlistItem(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "ic_RemoveFromcart":
                RemoveFromcart.Clear_miniCart(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "ic_verifyLogin":
                ic_verifyLogin.IC_verifyLogin_addingProductTowishlist(test1, rowNumber);
                break;
            case "IC_WishlistToCart":
                IC_WishlistToCart.verifyProducts_wishlistTocart(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "IncreaseQuanityInCart":
                increQuantity.increaseQuantity(test1);
                break;
            case "RemoveArticleFromCart":
                removeItemsFromCart.removeItemFromCart(test1);
                break;
            case "SendWishlistToEmail":
                SendWishlistToEmail.ShareYourwishlist(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "icEmailWishlistverification":
                icEmailWishlistverification.icWishlistVerificationSender(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "RedirectToProdDetailPageFromCart":
                redirectAndVerify.verifyNavigationToProductDetailPageFromCart(test1);
                break;
            case "giftCardWithInvalidCouponCode":
                icRedeemGiftCard.giftCardWithInvalidCouponCode(test1);
                break;
            case "ic_logout":
                ic_login.logout(test1, dataMap2.get("ic_login++"), rowNumber);
                break;
            case "verifyCart":
                icCart.verifyCart(test1);
                break;
            case "Pagination":
                pagination.paginate(test1);
                break;
            case "verifyDeliveryOption":
                icDeliveryOptionDisplay.validateDeliveryOptionsDisplays(test1, rowNumber);
                break;
            case "parallel_ic_Login":
                parallel_ic_Login.checkParallelExecution(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "SapRSIGetDataFromSAPDB":
                sapRSI.getDataFromSAPDB(test1);
                break;
            case "getRSIItemInMagento":
                sapRSI.getRSIItemInMagento(test1);
                break;
            case "DataValidationAfterCheckout":
                sapRSI.getDataFromSAPDBAfterCheckout(test1);
                break;
            case "skuProduct":
                products.skuProduct(test1);
                break;
            case "skuProductValidateOrderQuantity":
                products.skuProductValidateQuantity(test1);
                break;
            case "ic_SKUproduct":
                SKUproduct.displayProductSKU(test1, el);
                break;
            case "getRSISellableArticle":
				sapRSI.getSellableArticle(test1);
				break;
            case "ic_SubscribeNewsletter":
                ic_SubscribeNewsletter.SubscribeNewsletter(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "NavigateToWishlist_VerifyLoginPageAppear":
                NavigetoWishlist.NavigateToWishlist_verifyLoginPageAppears(test1);
                break;
            case"validatePaymentOption":
				icPaymentOptions.validatePaymentOption(dataMap2.get(moduleToRun + "++"),test1, rowNumber);
				break;
				//New functionality from backlog IC
            case "icClickandCollect":
				ClickAndCollect.ClickandCollectDeliveryoption(test1);
				break;
			case "IC_RetivedLoggedUserBillingAddress":
				LoggedUserBillingAddress.retriveLoggedUserBillingAddress(test1);
				break;
			case "srs_Login":
				SRSLogin.SRS_Login(dataMap2.get(moduleToRun+"++"), test1, rowNumber);
				break;
			case "SRSLogonStore":	
				SRSLogonStore.srsLogonByAptStore(dataMap2.get(moduleToRun+"++"), test1, rowNumber);
				break;
			case "srs_salesOrder_DeliverStatus":
				SRSDeliverStatus.verifySalesDeliverStatus(dataMap2.get(moduleToRun+"++"), test1, rowNumber);
				break;
			case "MagentoFetchOrderpayload":
				MagentoFetchOrderpayload.FetchOrderPayload(test1);
				break;
			 case "LaunchPortal":
	            lp.launchPortal (test1);
	           break;
			case "adminReorder":
				adminReorder.editOrder(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
				break;
            case "ic_myOrder":
                ic_MyOrder.searchOrder(test1);
                break;
            case "ic_Magento_LaunchPortal":
                icMagentoLaunchPortal.launchPortal(test1);
                break;
			case "ic_CancelOrder":
				ic_MagentoCancelUpaidEFT.IC_cancelUpaidEFT(test1);
	            break;
	        case "ic_BackTo_IC":
	            ic_toConfirmOrderStatus.backToIC(test1);
	             break;
	        case "ic_ESDLearnMore":
	        	ic_esdLearnMore.learnMoreESD(test1, rowNumber);
	             break;
	        case "ValidateRegistrationForm":
	        	ValidateRegForm.validateRegForm(test1);
            	break;
	        case "ic_tvLicenseValidation":
            	ic_TVLicenseValidation.TvLicenceValidation(test1, rowNumber);
            	break;
	        case"ic_Tvapproval":
	        	ic_tvLicenceApproval.approveTVlicence(test1, rowNumber);
            	break;
            case"ic_uploadDocument":
                Payopt.uploadValidID(test1);
                break;
            case "ic_downloadSABC_Document":
                icOrderSAPnumber.downloadSABC_ID(test1);
                break;
            case "OpenGateSales":
                openGateSales.searchOrderOpenGateSales(test1);
                break;
            case "ic_RemoveTVLicenseOrProduct":
            	removeItemsFromCart.removeTVLicense(test1);
            	break;
            case "ic_validateTVLicensePopUp":
            	ic_TVLicenseValidation.validateNoTVLicensePopUpShows(test1);
            	break;
            case "ic_MyTVLicense":
            	ic_MyTVLicense.MyTVlicenseValidation(test1);
            	break;
            case "ic_user_license_verification":
            	ic_tvLicenceApproval.licenseValidation(test1);
            	break;
            case"ic_removeAddress":
            	verifyAcc.removeAddress(test1);
            	break;
            case "ic_DeleteCustomer":
            	newAcc.deleteCustomer(test1);
            	break;

            //EVS CODE BELOW
            case "evs_Login":
                evs_Login.Login(test1);
                break;
            case "evs_ProductSearch":
                evs_productSearch.evs_SelectProductAndAddToCart(test1);
                break;
            case "evs_GenerateOrderSAPnumber":
                evs_magentoSAPNumber.GenerateOrderSAPnumber(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "evs_DeliveryPopulation":
                evs_delivery.deliveryPopulation(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "evs_deliveryPopulationGiftCard":
                rowNumber = findRowToRun(dataMap2.get("deliveryPopulation++"), 0, testcaseID);
                evs_delivery.deliveryPopulationGiftCard(dataMap2.get("deliveryPopulation++"), test1, rowNumber);
                break;
            case "evs_AccountCreation":
                evs_NewAccountCreation.accountCreation(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "evs_CheckoutpaymentOption":
                evs_PaymentOption.CheckoutpaymentOption(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "evs_CheckoutGiftCard":
            	evs_PaymentOption.CheckoutpaymentOptionGiftCard(dataMap2.get("deliveryPopulation++"),test1,rowNumber);
                break; 
            case "evs_PayUPagePayment":
                evs_PayUPayment.PayUPagePayment(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "evs_RetriveOrderID":
                evs_RetriveOrderID.RetriveOrderID(test1);
                break;
            case "evs_OrderStatusSearch":
                evs_orderStatus.navigateToOrderPage(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "EVS_Login_magento":
                evs_Login_magento.Login_magento(test1);
                break;
            case "evs_SAP_OrderRelated":
                evs_SAPorderRelated.SAP_OrderDetailVadidation(test1);
                break;
            case "evs_ClearCart":
            	evs_cart.removeAllItemsInCart(test1);
                break;
            case "evs_IncreaseQuanityInCart":
            	evs_increaseQuantityInCart.increaseQuantity(test1);
                break;
            case "evs_PasswordSecured":
                evs_PasswordSecured.loginPasswordSafe(dataMap2.get("evs_login++"), test1, rowNumber);
                break;
            case "evs_RetrieveCustomerDetails":
            	evs_custDetails.retrieveCustomerDetails(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "evs_SapCustomer":
                ArrayList<HashMap<String, ArrayList<String>>> sheetss = new ArrayList<HashMap<String, ArrayList<String>>>();
                sheetss.add(dataMap2.get("evs_AccountCreation++"));
                sheetss.add(dataMap2.get("evs_DeliveryPopulation++"));
                //sheets.add(dataMap2.get("SapCustomer++"));//Falls away
                sheetss.add(dataMap2.get("evs_UpdateUser++"));
                sheetss.add(dataMap2.get("evs_CreateaccountBackend++"));
                sheetss.add(dataMap2.get("evs_adminUserUpdate++"));
                evs_customerDB.sapDbTests(dataMap2.get(moduleToRun + "++"), sheetss, test1, testcaseID, rowNumber);
                break; 
            case "evs_Magento_UserInfoVerify":
                rowNumber = findRowToRun(dataMap2.get("evs_AccountCreation++"), 0, testcaseID);
                evs_Magentoverify.Validate_UserInfobackend(dataMap2.get("evs_AccountCreation" + "++"), test1, rowNumber);
                break;
            case "evs_RedeemGiftCard":
            	evs_RedeemGiftCard.redeemGiftCard(test1);
                break;
            case "evs_ForgotPassword":
                evs_VerifyForgotPass.forgotPasswordPage(test1);
                 break;
            case "evs_ForgotPasswordLink":
                evs_forgottenPassLink.forgotPasswordLink(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
			case "evs_RemoveItemsFromCart":
				evs_RemoveItemsFromCart.removeItemFromCart(test1);
				break;
			case "evs_RedirectToProdDetailPageFromCart":
				evs_RedirectToProdDetailPageFromCart.verifyNavigationToProductDetailPageFromCart(test1);
				break;
            case "parallel_evs_Login":
    			parallel_evs_Login.checkParallelExecution(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
    			break;
            case "evs_invalidCredslogin":
    			evs_invalidCredslogin.invalidLogin_evs(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
    			break;           
            case "evs_VerifyCart":
                evs_cart.verifyCart(test1);
                break;
            case "evs_logout":
                evs_Login.logout(test1);
                break;
            case "evs_CreateaccountBackend":
            	evs_MagentonewUser.CreateAccount_validateInfo_Backend(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "evs_adminUserUpdate":
                ArrayList<HashMap<String, ArrayList<String>>> adminSheets2 = new ArrayList<HashMap<String, ArrayList<String>>>();
                adminSheets2.add(dataMap2.get(moduleToRun + "++"));
                evs_adminUserUpdate.editCustomerDetails(adminSheets2, test1, testcaseID);
                break;
            case "evs_UpdateUser":
                ArrayList<HashMap<String, ArrayList<String>>> mySheet1 = new ArrayList<HashMap<String, ArrayList<String>>>();
                mySheet1.add(dataMap2.get("evs_UpdateUser"+ "++"));
                mySheet1.add(dataMap2.get("evs_Login++"));
                evs_UpdateUser.updateAccount(mySheet1, test1, testcaseID);
                break;
            case "evs_NavigetoWishlist":
                evs_NavigetoWishlist.NavigateToWishlist_verifymsg(test1);
                break;
            case "evs_ClearWishList":
                evs_verifyWishlistItem.handleWishlistItem(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "evs_RemoveFromcart":
                evs_RemoveFromcart.Clear_miniCart(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "evs_WishlistToCart":
                evs_WishlistToCart.verifyProducts_wishlistTocart(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "EVS_LaunchPortal":
                evs_lp.launchPortal (test1);
                break;
            case "EVS_SendWishlistToEmail":
                evs_SendWishlistToEmail.ShareYourwishlist(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "evs_InvalidEmailNewsLetter":
                evs_NewsletterEmail.evs_NewsLetterInvalidEmail(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "evs_SubscribeNews_DupEmailID":
                evs_SubscribeNews_DupliEmailID.SubscribeNewsletter_DuplicateEmail(dataMap2.get(moduleToRun + "++"), test1,rowNumber);
                break;
            case "evs_SubscribeNewsletter":
                evs_SubscribeNewsletter.SubscribeNewsletter(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "EVS_SapRSIGetDataFromSAPDB":
                evs_sapRSI.getDataFromSAPDB(test1);
                break;
            case "EVS_getRSIItemInMagento":
                evs_sapRSI.getRSIItemInMagento(test1);
                break;
            case "EVS_DataValidationAfterCheckout":
                evs_sapRSI.getDataFromSAPDBAfterCheckout(test1);
                break;
            case "EVS_getSellableArticle":
            	evs_sapRSI.getSellableArticle(test1);
            break;
            case "EVS_skuProduct":
                evs_productSearch.skuProduct(test1);
                break;
            case "EVS_skuProductValidateOrderQuantity":
                evs_productSearch.skuProductValidateQuantity(test1);
                break;
            case "evs_cashDeposit":
            	evs_cashDeposit.InvoiceCashDeposit(test1);
            	break;
            case "evs_PopularSearch":
                evs_PopularSearch.VerifyPopularSearch(test1, rowNumber);
                break;
            case "evs_SearchNoResultsReturned":
                evs_ReturnNoResults.evs_DoesNotExtistSearch(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "evs_SearchTextResults":
    			evs_SearchText.searchResultValidation(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
    			break;
            case "evs_LogoHomepage":
            	evs_Logo.homepageLogo(test1);
            	break;
            case"evs_validatePaymentOption":
            	evs_PaymentOptions.validatePaymentOption(dataMap2.get(moduleToRun + "++"),test1, rowNumber);
				break;
            case "evs_ExistingAddress":
            	evs_ExistingAddress.AddressThere(test1);
                break;
            case "evs_GiftCardPurchase":
            	evs_GiftCardPurchase.purchaseGiftCard(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "evs_RetriveGiftCardOrderID":
                evs_RetriveGiftCardOrderID.RetriveOrderID(test1);
                break;
            case "evs_giftCardReport":
                ArrayList<HashMap<String, ArrayList<String>>> mySheet01 = new ArrayList<HashMap<String, ArrayList<String>>>();
                mySheet01.add(dataMap2.get(moduleToRun + "++"));
                evs_giftCardReport.giftCardReports(mySheet01, test1, testcaseID);
                break;
            case "evs_EmailSentVerification":
                evsForgotEmailSent.evsVerifyNewPasswordEmailSent(test1);
                break;
            case "evs_VerifyForgotPass":
                evsVerifyForgotPass.forgotPasswordPage(test1);
                break;
            case "evs_ResetForgottenPassword":
                evsResetPasswordLink.clickLinkOnGmail(dataMap2.get(moduleToRun+ "++"), test1, rowNumber);
                evsResetPasswordLink.resetNewPassword(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                evsResetPasswordLink.clickUsedResetLink(dataMap2.get(moduleToRun+ "++"), test1, rowNumber);
                break;
            case "evs_GiftCardVerificationSender":
                evsGiftCardVerification.evsGiftCardVerificationSender(test1);
                break;
            case "evs_CompareProducts":
                evs_productsCompared.validateCompare(test1);
                break;
            case "evs_myOrder":
                evs_myOrders.searchOrder(test1);
                break;
            case "evs_Magento_LaunchPortal":
                evsMagentoLaunchPortal.launchPortal(test1);
                break;
            case "evs_CancelOrder":
            	evs_MagentoCancelUpaidEFT.EVS_cancelUpaidEFT(test1);
                break;
            case "evs_BackToEVS":
            	evs_toConfirmOrderStatus.backToEvs(test1);
                break;
            case "evs_cancelCreditMemo":
                cancelCreditMemo.cancelOrderCreditMemo(test1);
                break;
            case "evs_admin_reOrder":
            	evs_admin_reOrder.editOrder(test1);
            	break;
            case "evs_verifyDeliveryOption":
                icDeliveryOptionDisplay.validateDeliveryOptionsDisplays(test1, rowNumber);
                break;
            case"tvLicenseValidation":
            	evs_TvLicense.TvLicenceValidation(test1, rowNumber);
            	break;
            case"evs_uploadDocument":
            	evs_PaymentOption.uploadValidID(test1);
            	break;
            case"evs_Tvapproval":
            	evs_TvLicenseApproval.approveTVlicence(test1, rowNumber);
            	break;
            case "evs_downloadSABC_Document":
            	evs_magentoSAPNumber.downloadSABC_ID(test1);
            	break;
            case "user_license_verification":
            	evs_TvLicenseApproval.licenseValidation(test1);
            	break;
            case "evs_OpenGateSales":
                evs_openGateSales.searchOrderOpenGateSales(test1);
                break;
            case "evs_RemoveTVLicenseOrProduct":
            	evs_RemoveItemsFromCart.removeTVLicense(test1);
            	break;
            case "evs_validateTVLicensePopUp":
            	evs_TvLicense.validateNoTVLicensePopUpShows(test1);
            	break;
            case "evs_MyTVLicense":
            	evs_MyTVLicense.MyTVlicenseValidation(test1);
            	break;
            case "evs_ValidateRegistrationForm":
            	evs_ValidateRegForm.validateRegForm(test1);
            	break;
            case "evs_NavigateToBundleProduct":
            	evs_bundleCreation.NavigateToBundle(test1);
            	break;
            case "evs_SetBasicBundleProductDetails":
            	evs_bundleCreation.SetBasicBundleProductDetails_1(test1);
            	break;
            case "evs_SetTaxBundleProductDetails":
            	evs_bundleCreation.SetTaxBundleProductDetails_1(test1);
            	break;
            case "evs_AddProductsToBundle":
            	evs_bundleCreation.addBundleProducts(test1);
            	break;
            case "evs_sapData":
            	evs_bundleCreation.sapData(test1);
            	break;
            case "evs_BundlePDL":
            	evs_bundleFrontEnd.navToProdDetailPage(test1);
            	break;
            case "evs_BundleOutOfStock":
            	evs_bundleFrontEnd.OutOfStockBundleProduct(test1);
            	break;
            case "evs_BundleProductStatus":
            	evs_bundleCreation.validateProductStatus(test1);
            	break;	
            case "evs_ValidateBundleItemsPresent":
            	evs_bundleFrontEnd.validateBundleItems(test1);
            	break;
            case "evs_BundleDeletion":
            	evs_bundleCreation.bundleDeletion(test1);
            	break;
            case "evs_searchBundleArticle":
            	evs_bundleFrontEnd.searchBundleProduct(test1);
            	break;
            case "evs_DeleteCustomer":
            	evs_NewAccountCreation.deleteCustomer(test1);
            	break;
            case"evs_removeAddress":
            	evs_NewAccountCreation.removeAddressEVS(test1);
            	break;
            case "evs_searchBundleProduct":
            	evs_bundleFrontEnd.searchForBundleArticleUsingSKU(test1);
            	break;
            case "evs_bundleProduct_magento_search":
            	evs_bundleCreation.searchForArticleUsingSKU(test1);
            	break;
            case "evs_IncreaseProductQuantity":
            	evs_bundleCreation.increaseQuantity(test1);
            	break;
            case "evs_findPriceForIncreaseQuantityItem":
            	evs_bundleCreation.findPriceIncreasedProd(test1);
            	break;
            case "validateUpdatedPrices":
            	evs_bundleFrontEnd.searchForBundleArticleSKUValidateUpdates(test1);
            	break;
            case "decreaseQuantityOfBundleItem":
            	evs_bundleCreation.decreaseQtyOfBundleItemInMagento(test1);
            	break;

            // Sleep masters keywords below
            case "SPM_LaunchPortal":
            	spm_launchPortal.launchPortal(test1);
            	break;
            //case "SPM_ProductSearch":
            //	spm_productSearch.rangeSearch(test1);
            //	break;
            case "SPM_ProductSearch":
            	spm_productSearch.spm_SelectProductAndAddToCart(test1);
            	break;
            case "SPM_RangeValidation":
            	//spm_productSearch.rangeValidation(test1);
            	break;
            case "SPM_MagentoRangeValidation" :
            	//spm_magentoRange.validateRange(test1);
            	break;
            case "SPM_LoginMagento":
            	  icMagento.Login_magento(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
            	break;
            case "validateProductNotFound":
            	spm_productSearch.validateProductNotFound(test1);
            	break;
            case "SPM_ArticleRanges":
            	articleValidation.rangeValidation(test1);
            	break;
            case "SPM_LayBy_PersonalDetails":
            	spm_layby.personalDetails(test1);
            	break;
            case "SPM_LayBy_ResidentialAddressDetails":
            	spm_layby.residentialAddressDetails(test1);
            	break;
            case "SPM_LayBy_ContactDetails":
            	spm_layby.contactLayByDetails(test1);
            	break;
            case "SPM_LayBy_DeliveryAndEGT":
            	spm_layby.deliveryAndEGT(test1);
            	break;
            case "SPM_LayBy_MarketQuestions":
            	spm_layby.marketQuestions(test1);
            	break;
            case "SPM_LayBy_MagentoValidation":
            	spm_laybyMagento.validateLayBy(test1);
            	break;
            case "SPM_LayBy_DeleteRecored":
            	spm_laybyMagento.deleteLayByRecord(test1);
            	break;
            case "SPM_Equiries":
            	spm_layby.enterEquiryDetails(test1);
            	break;
            case "SPM_ValidateEquiries":
            	spm_Enquries.userEquiryDetails(test1);
            	break;
            case "SPM_NavigateToEnquiries":
            	spm_Enquries.navigateToEnquiries(test1);
            	break;
            case "SPM_DeliveryPopulation":
            	sleepMastersDelivery.deliveryPopulation(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
            	break;
            case "SPM_CheckoutpaymentOption":
            	sleepMastersCheckOut.CheckoutpaymentOption(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
            	break;
            case "SPM_PayUPagePayment":
            	spm_Payment.PayUPagePayment(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
            	break;
            case "SPM_RetriveOrderID":
            	spm_RetireveOrderID.RetriveOrderID(test1);
            	break;
            case "SPM_OrderStatusSearch":
            	spm_OrderStatusSearch.navigateToOrderPage(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
            	break;
            case "SPM_GenerateOrderSAPnumber":
            	spm_GenerateSAPNumber.GenerateOrderSAPnumber(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
            	break;
            case "SPM_SAP_OrderRelated":
            	spm_OrderRelated.SAP_OrderDetailVadidation(test1);
            	break;
            case "SPM_AccountCreation":
            	spm_accountCreat.accountCreation(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "SPM_RetrieveCustomerDetails":
            	spm_RetrieveCustomer.retrieveCustomerDetails(dataMap2.get(moduleToRun + "++"), test1, rowNumber);
                break;
            case "SPM_Magento_UserInfoVerify":
                rowNumber = findRowToRun(dataMap2.get("SPM_AccountCreation++"), 0, testcaseID);
                spm_UserInfoVerification.Validate_UserInfobackend(dataMap2.get("SPM_AccountCreation" + "++"), test1, rowNumber);
                break;
			 case "SPM_SapCustomer": 
				ArrayList<HashMap<String, ArrayList<String>>>
			 	sheetsspm = new ArrayList<HashMap<String, ArrayList<String>>>();
			 	sheetsspm.add(dataMap2.get("SPM_AccountCreation++"));
			 	sheetsspm.add(dataMap2.get("SPM_SapCustomer++"));
			 	sheetsspm.add(dataMap2.get("ICUpdateUser++"));
                sheetsspm.add(dataMap2.get("CreateaccountBackend++"));
                sheetsspm.add(dataMap2.get("adminUserUpdate++"));
			 	customerDB.sapDbTests(dataMap2.get(moduleToRun + "++"), sheetsspm, test1, testcaseID, rowNumber);
			 	break;
            case "SPM_DeleteCustomer":
            	spm_accountCreat.deleteCustomer(test1);
            	break;
            case "SPM_CreditApp_PersonalDetails":
            	spm_creditApp.personalDetails(test1);
            	break;
            case "SPM_CreditApp_SpouseDetails":
            	spm_creditApp.spouseDetails(test1);
            	break;
            case "SPM_CreditApp_Credit":
            	spm_creditApp.creditReview(test1);
            	break;
            case "SPM_CreditApp_EmploymentDetails":
            	spm_creditApp.EmploymentDetails(test1);
            	break;
            case "SPM_CreditApp_AddressDetails":
            	spm_creditApp.CreditAddDetails(test1);
            	break;
            case "SPM_CreditApp_ContactDetails":
            	spm_creditApp.CreditContactDetails(test1);
            	break;
            case "SPM_Navigate_ApplyCred":
            	spm_creditApp.navToApplyCred(test1);
            	break;
            case "SPM_CreditApp_Magento":
            	spm_creditAppMagento.VerifyCreditAppSelection(test1);
            	break;
            case "SPM_CreditApp_Delete":
            	spm_creditAppMagento.deteleCredApp(test1);
            	break;
            
        }
    }
    public int findRowToRun (HashMap < String, ArrayList < String >> input,int occCount, int testcaseID){
        int numberRows = input.get("TCID").size();
        int rowNumber = -1;
        occCount = occCount + 1;
        for (int i = 0; i < numberRows; i++) {
            if (input.get("TCID").get(i).equals(Integer.toString(testcaseID))
                    && input.get("occurence").get(i).equals(Integer.toString(occCount))) {
                rowNumber = i;
            }
        }
        return rowNumber;
    }
}
