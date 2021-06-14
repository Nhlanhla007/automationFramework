package evs_PageObjects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import Logger.Log;
import utils.Action;
import utils.ConfigFileReader;
import utils.DataTable2;

public class EVS_WishList_Login {

	WebDriver driver;
	Action action;
	EVS_Cart cartValidation;
	DataTable2 dataTable2;
	EVS_WishList WishList;

	// ic_validateProductSKU validateProductSKU;
	// ic_CompareProducts compareProducts;
	public EVS_WishList_Login(WebDriver driver, DataTable2 dataTable2) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		action = new Action(driver);
		cartValidation = new EVS_Cart(driver, dataTable2);
		this.dataTable2 = dataTable2;
		WishList = new EVS_WishList(driver, dataTable2);
		// validateProductSKU = new ic_validateProductSKU(driver, dataTable2);
		// compareProducts = new ic_CompareProducts(driver, dataTable2);
	}

	static Logger logger = Log.getLogData(Action.class.getSimpleName());

	/*
	 * PAGE OBJECTS
	 */
	@FindBy(xpath = "//span[contains(text(),'Products')]")
	WebElement icProductLink;

	@FindBy(xpath = "//input[@id='search']")
	WebElement icSearchBar;

	@FindBy(xpath = "//button[@type='submit' and @title='Search']")
	WebElement icSearchIcon;

	@FindBy(css = "a.product-item-link")
	public List<WebElement> ic_products;

	@FindBy(xpath = "//span[contains(text(),'Next')]")
	public WebElement ic_ClickNext;

	@FindBy(xpath = "//a[@class=\"icon__movie-projector\"]")
	WebElement entertainmentProdLink;

	@FindBy(xpath = "//body[1]/div[1]/header[1]/div[2]/div[1]/div[2]/div[3]/nav[1]/ul[1]/li[1]/ul[1]/li[1]/ul[1]/li[7]/a[1]/span[1]")
	WebElement fitness;

	@FindBy(xpath = "//body[1]/div[1]/header[1]/div[2]/div[1]/div[2]/div[3]/nav[1]/ul[1]/li[1]/ul[1]/li[1]/ul[1]/li[9]/a[1]/span[1]")
	WebElement software;

	@FindBy(xpath = "//span[contains(text(),'Furniture & D�cor')]")
	WebElement furnitureAndDecor;

	@FindBy(xpath = "//*[@class=\"box-tocart\"]/div/span")
	WebElement productOutOfStock;

	@FindBy(xpath = "//span[@class = \"sr-only\"]")
	WebElement shopByDeptLink;

	@FindBy(xpath = "//span[@class='sku-code']")
	WebElement skuCode;

	@FindBy(xpath = "//*[@data-price-type = \"finalPrice\"]/span")
	WebElement productFinalPrice;

	List<WebElement> listElements;

	@FindBy(id = "product-addtocart-button")
	WebElement productDetailsPageAddToCartButton;

	@FindBy(xpath = "//span[text()='Add to Wish List']")
	WebElement addToWishListButton;
	
	@FindBy(xpath = "//a[@title='Wishlist']") 
	WebElement wishListButton;

	@FindBy(xpath = "//span[@class='logged-in']")
	WebElement loggedInIcon;

	@FindBy(xpath = "//div[contains(text(),'You must login or register to add items to your wi')]")
	WebElement loginMsg;

	@FindBy(xpath = "//span[contains(text(),'My Wish List(s)')]")
	WebElement myWishListPopUp;

	@FindBy(xpath = "//span[@data-action='add-to-existing-wishlist' and @title='Wish List']")
	WebElement addToWishListCheckBox;
	
	@FindBy(xpath = "//span[contains(text(),'Add to Wish List(s)')]")
	WebElement addToWishLists;

	
	
	/*
	 * PAGE METHODS
	 */

	public void clickNext(ExtentTest test) throws Exception {
		action.mouseover(ic_ClickNext, "scroll to element");
		action.explicitWait(10000);
		action.click(ic_ClickNext, "Clicked Next", test);
	}

	/**
	 * Returns a list of products from current viewing page
	 * 
	 * @return List<WebElement>
	 */
	public List<WebElement> returnList() {
		return ic_products;
	}

	/**
	 * Validates if the product click NEXT page button exists/is visible
	 * 
	 * @return WebElement
	 */
	public WebElement returnNext() {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		boolean isPresent = driver.findElements(By.xpath("//span[contains(text(),'Next')]")).size() > 0;
		// boolean clickN = action.attributeEnabled(ic_ClickNext);
		if (isPresent) {
			WebElement web = ic_ClickNext.findElement(By.xpath(".//parent::*"));
			boolean status = action.attributeValidation(web, "aria-disabled", "false", 5);
			if (status) {
				return ic_ClickNext.findElement(By.xpath(".//parent::*"));
			}
		}
		return null;
	}

	public void ic_ClickProductLink(ExtentTest test) {
		try {
			if (ic_ElementVisable(icProductLink)) {
				action.click(icProductLink, "Click product link", test);
				Thread.sleep(10000);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
		}
	}

	public void ic_EnterTextToSearchBar(String productToFind, ExtentTest test) {
		try {
			ic_ElementVisable(icSearchBar);
			action.clear(icSearchBar, "SearchBar");
			action.writeText(icSearchBar, productToFind, "SearchBar", test);
			action.click(icSearchIcon, "Click on search", test);
			action.explicitWait(6);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
		}
	}

	// Checks if an element is visible
	/**
	 * Checks for the visability of an element
	 * 
	 * @param element
	 * @return boolean
	 */
	public boolean ic_ElementVisable(WebElement element) {
		return action.elementExists(element, 10);
	}

	/**
	 * Filters through excel data, removes delimeters.
	 * 
	 * @param allProductsToSearch
	 * @return List<String>
	 */
	public List<String> filterProducts(String allProductsToSearch) {
		String[] productsArray = allProductsToSearch.split("#");
		List<String> productsList = new ArrayList<String>(Arrays.asList(productsArray));
		return productsList;
	}

	/**
	 * Gathers data from excel. Determines search type inserted from excel and
	 * selects appropriate construct for execution
	 * 
	 * @param input
	 * @param test
	 * @param rowNumber
	 */
	public void evs_SelectProductAndAddToCart(ExtentTest test) throws IOException {
		 String navigateURL = ConfigFileReader.getPropertyVal("EVS_URL");
		 action.navigateToURL(navigateURL);

		String typeSearch = dataTable2.getValueOnCurrentModule("typeSearch");// input.get("typeSearch").get(rowNumber);
		String productsToSearch = dataTable2.getValueOnCurrentModule("specificProduct");// input.get("specificProduct").get(rowNumber);
		String quantityOfSearchProducts = dataTable2.getValueOnCurrentModule("Quantity");// input.get("Quantity").get(rowNumber);
		String waitTimeInSeconds = dataTable2.getValueOnCurrentModule("cartButtonWaitTimeInSeconds");// input.get("cartButtonWaitTimeInSeconds").get(rowNumber);
		String TypeOfOperation = dataTable2.getValueOnCurrentModule("TypeOfOperation");
		String validationRequired = dataTable2.getValueOnCurrentModule("validationRequired");
		List<String> theProducts = filterProducts(productsToSearch);

		try {
			Map<String, List<String>> productsInCart = ic_CreateCartFromProductListing(productsToSearch,
					quantityOfSearchProducts, typeSearch, waitTimeInSeconds, test);
			switch (TypeOfOperation) {
			case "Add_To_Wishlist":
				if (validationRequired.equalsIgnoreCase("Yes_Wishlist")) {
					WishList.ValidateProductsIn_Wishlist(productsInCart, test);
				}
				break;
			case "Add_To_Cart":
				cartValidation.iCcartVerification2(productsInCart, test);
				break;
			case "Validate_Out_Of_Stock":
				break;
			/*
			 * default: cartValidation.iCcartVerification2(productsInCart,
			 * test); break;
			 */
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
		}

	}

	/**
	 * Loads the listing page
	 * 
	 * @param category
	 * @param product
	 * @param test
	 * @throws IOException
	 * @throws Exception
	 */
	public void loadProductListingPage(String category, String product, ExtentTest test) throws IOException, Exception {
		switch (category) {
		case "SearchUsingSearchBar":
			ic_EnterTextToSearchBar(product, test);
			break;
		case "All Products":
			ic_ClickProductLink(test);
			break;
		default:
			boolean isPresent = action.waitUntilElementIsDisplayed(shopByDeptLink, 15);
			if (category.equalsIgnoreCase("Entertainment") | category.equalsIgnoreCase("Fashion")
					| category.equalsIgnoreCase("Health & Beauty") | category.equalsIgnoreCase("Home")
					| category.equalsIgnoreCase("Work & Study") | category.equalsIgnoreCase("Projects & DIY")
					| category.equalsIgnoreCase("Lifestyle & Leisure") | category.equalsIgnoreCase("Fitness")
					| category.equalsIgnoreCase("Kids World")) {

				Map<String, String> productLinks = new HashMap<>();
				productLinks.put("Entertainment", "icon__movie-projector");
				productLinks.put("Fashion", "icon__t-shirt");
				productLinks.put("Health & Beauty", "icon__heart-with-pulse");
				productLinks.put("Home", "icon__home");
				productLinks.put("Work & Study", "icon__laptop");
				productLinks.put("Projects & DIY", "icon__wrench");
				productLinks.put("Lifestyle & Leisure", "icon__road");
				productLinks.put("Fitness", "icon__cycling");
				productLinks.put("Cellular", "icon__iphone-x");
				productLinks.put("Kids World", "icon__party-balloon");
				for (Map.Entry map : productLinks.entrySet()) {
					if (category.equalsIgnoreCase(map.getKey().toString())) {
						WebElement linkFromNavBar = byCategoryFromMenuBar(map.getValue().toString());
						action.click(linkFromNavBar, "Navigating to : " + map.getKey().toString(), test);
					}
				}
			} else {
				if (isPresent) {
					action.click(shopByDeptLink, "Shop By Department", test);
					WebElement typeOfSearch = byCategory(category);
					action.scrollElemetnToCenterOfView(typeOfSearch, "typeOfSearch", test);
					action.scrollElementIntoView(typeOfSearch);
					action.click(typeOfSearch, typeOfSearch.getText(), test);
				}
			}
			break;
		}
	}

	/**
	 * Finds the category that will be searched
	 * 
	 * @param nameOfCategory
	 * @return WebElement
	 */
	public WebElement byCategory(String nameOfCategory) {
		WebElement category = driver
				.findElement(By.xpath("//span[contains(text(),'" + nameOfCategory + "')]/parent::a"));
		return category;
	}

	/**
	 * Finds the category that will be searched
	 * 
	 * @param nameOfCategory
	 * @return WebElement
	 */
	public WebElement byCategoryFromMenuBar(String nameOfCategory) {
		WebElement category = driver.findElement(By.xpath("//*[@class ='" + nameOfCategory + "']/parent::li"));
		return category;
	}

	WebElement ic_FindProduct(WebElement elem) {
		return elem;
	}

	WebElement getCartButton(WebElement product) {
		WebElement cartButton = product
				.findElement(By.xpath(".//parent::*/following-sibling::div[5]/div[2]/div/form/button"));
		return cartButton;
	}

	void addToCart(WebElement addToCartButton, String waitTimeInSeconds, ExtentTest test) {
		try {
			// action.scrollElemetnToCenterOfView(addToCartButton);
			// action.mouseover(addToCartButton, "Scroll to add to cart");
			action.explicitWait(2000);
			action.click(addToCartButton, "Add To Cart", test);
			cartValidation.cartButtonValidation(addToCartButton, Integer.parseInt(waitTimeInSeconds), test);
			action.explicitWait(7000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			e.printStackTrace();
		}

	}

	void addToCartFromProdDetailsPage(WebElement productLink, String waitTimeInSeconds, int quanity, ExtentTest test)
			throws Exception {
		if (quanity == 1) {
			WebElement prodC = productLink.findElement(By.xpath(".//parent::strong/parent::*/parent::*/a[1]"));
			action.click(prodC, "Navigate to product Details page", test);
		}
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		boolean isPresent = driver.findElements(By.id("product-addtocart-button")).size() > 0;
		if (isPresent) {
			action.scrollElemetnToCenterOfView(productDetailsPageAddToCartButton, "productDetailsPageAddToCartButton",
					test);
			// action.click(productDetailsPageAddToCartButton, "Add To Cart",
			// test);
			productDetailsPageAddToCartButton.click();
			cartValidation.cartButtonValidation(productDetailsPageAddToCartButton, Integer.parseInt(waitTimeInSeconds),
					test);
			action.explicitWait(8000);
		} else {
			String outOfStockMessage = productOutOfStock.getText();
			action.CompareResult("Product is out of stock", "Currently Out of Stock", outOfStockMessage, test);
		}
	}

	void addToWishlistFromProdDetailsPage(WebElement productLink, String waitTimeInSeconds, int quanity,
			ExtentTest test) throws Exception {
		
		if (quanity == 1) {
			WebElement prodC = productLink.findElement(By.xpath(".//parent::strong/parent::*/parent::*/a[1]"));
			action.click(prodC, "Navigate to product Details page", test);

		}

		action.explicitWait(5000);


		if (!action.isElementPresent(loggedInIcon)) {
			
			action.clickEle(addToWishListButton, "Add to WishList Button", test);
			action.explicitWait(5);
			String msg = action.getText(loginMsg, "Login Message", test);
			action.CompareResult("User redirected to Login Page",
					"You must login or register to add items to your wishlist.", msg, test);

		} else {
			action.click(wishListButton, "Wish list button", test);
			action.explicitWait(2000);
			addToWishList(test);

			// action.explicitWait(Integer.parseInt(waitTimeInSeconds));
		}
	}

	

	public void addToWishList(ExtentTest test) throws IOException {

		if (action.isElementPresent(myWishListPopUp)) {
			if (action.isElementPresent(addToWishListCheckBox)) {
				action.clickEle(addToWishListCheckBox, "Adding item to Wish List", test);
				action.clickEle(addToWishLists, "Add to Wish List Pop up", test);
				action.explicitWait(5000);
			}

		}

	}

	String findPrice(WebElement theProduct) {
		String price = theProduct.findElement(By.xpath("parent::*/following-sibling::*[1]/div/span/span/span"))
				.getText();
		return price;
	}

	public WebElement ic_FindProduct(ExtentTest test, String product) throws Exception {
		boolean status = true;
		while (status) {
			List<WebElement> allProducts = ic_products;
			for (WebElement el : allProducts) {
				if (el.getText().trim().toLowerCase().equalsIgnoreCase(product)) {
					status = false;
					action.mouseover(el, "On Product");
					return el;
				}
			}
			WebElement nextButton = returnNext();
			if (nextButton != null) {
				clickNext(test);
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					logger.info(e.getMessage());
				}
			} else {
				status = false;
				action.CompareResult("Product Not Found", product, "", test);
				// System.out.println("Item has not been found anywhere");
			}
		}
		return null;
	}

	public static Map<String, List<String>> productData;

	Map<String, List<String>> ic_CreateCartFromProductListing(String productsList, String quantityOfProducts,
			String searchCategory, String waitTimeInSeconds, ExtentTest test) {
		productData = new LinkedHashMap<>();
		String cartAdditionMethod = dataTable2.getValueOnCurrentModule("CartAdditionMethod");
		String TypeOfOperation = dataTable2.getValueOnCurrentModule("TypeOfOperation");// "Add_To_Cart";//Add_To_Wishlist
		try {
			List<String> theProducts = filterProducts(productsList);
			List<String> quantity = filterProducts(quantityOfProducts);
			for (int s = 0; s < theProducts.size(); s++) {
				List<String> productPrice_Quantity_SKU = new ArrayList<>();
				loadProductListingPage(searchCategory, theProducts.get(s), test);// change
																					// signatures
				WebElement prod = ic_FindProduct(test, theProducts.get(s));
				String productName = "";
				int set = 0;
				int quantityExecu = 0;
				if (prod != null) {
					productName = prod.getText();
					for (int o = 0; o < quantity.size(); o++) {
						if (o == s) {
							for (int g = 0; g < Integer.parseInt(quantity.get(o)); g++) {

								if (cartAdditionMethod.equalsIgnoreCase("ProductListingPage")) {
									WebElement cartButton = getCartButton(prod);
									addToCart(cartButton, waitTimeInSeconds, test);
								} else if (cartAdditionMethod.equalsIgnoreCase("ProductDetailPage")) {
									quantityExecu++;
									switch (TypeOfOperation) {
									case "Add_To_Cart":
										addToCartFromProdDetailsPage(prod, waitTimeInSeconds, quantityExecu, test);
										break;
									case "Add_To_Wishlist":
										addToWishlistFromProdDetailsPage(prod, waitTimeInSeconds, quantityExecu, test);
										break;
									case "Get_SKU_Code":
										// validateProductSKU.displayProductSKU(test,
										// prod);
										break;
									case "Add_To_Compare":
										// compareProducts.compareProductsFunctionality(test,
										// prod);
										// compareProducts.validateAddedProductsCompare(test,
										// prod);
										// compareProducts.clearAllProduct(test,
										// prod);
										break;
									case "Validate_Out_Of_Stock":
										addToCartFromProdDetailsPage(prod, waitTimeInSeconds, quantityExecu, test);
										break;
									}

								}
								if (set <= 0) {
									set++;
									productPrice_Quantity_SKU.add(quantity.get(o));
								}
							}
						}
					}
					String skuCode = getSKUCode(cartAdditionMethod, prod, test);
					productPrice_Quantity_SKU.add(skuCode);
					String productPrice = productFinalPrice.getText();
					productPrice_Quantity_SKU.add(productPrice);
				}
				productData.put(productName, productPrice_Quantity_SKU);
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		}
		return productData;

	}

	String getSKUCode(String cartAdditionType, WebElement productLink, ExtentTest test) throws Exception {
		if (cartAdditionType.equalsIgnoreCase("ProductListingPage")) {
			WebElement prodC = productLink.findElement(By.xpath(".//parent::strong/parent::*/parent::*/a[1]"));
			action.click(prodC, "Navigate to product Details page to Retrieve SKU", test);
			// Navigate to the cart detail page
			if (skuCode.getText() != null | skuCode.getText() != "") {
				return skuCode.getText();
			}
		} else {
			if (skuCode.getText() != null | skuCode.getText() != "") {
				return skuCode.getText();
			}
		}
		return "NA";
	}

}