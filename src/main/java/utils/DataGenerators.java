package utils;


import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import base.TestCaseBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Calendar.*;

public class DataGenerators {

    private final FakeValuesService fakeValuesService;
    private final Faker faker;
    private String currentEmail;
    private String currentCellNumber;
    private String currentMobileNumber;
    private String firstName;
    private String lastName;
    private String currentFirstName;
    private String currentLastName;
    private String passportID;

    public DataGenerators() {
        fakeValuesService = new FakeValuesService(new Locale("en-ZA"), new RandomService());
        faker = new Faker(new Locale("en-ZA"));
    }

    public Faker getFaker(){
        return this.faker;
    }

    private String GenerateSACellNumber(Boolean valid) {
        String saCellNumber;
        if (valid) {
            while (true) {
                saCellNumber = fakeValuesService.regexify("(0)[156789][0-9]{8}");
                Pattern pattern = Pattern.compile("(0)[156789][0-9]{8}");
                Matcher matcher = pattern.matcher(saCellNumber);
                if (matcher.matches()) {
                    break;
                }
            }
            return saCellNumber;
        } else {
            while (true) {
                saCellNumber = fakeValuesService.bothify("##########");
                Pattern pattern = Pattern.compile("(0)[156789][0-9]{8}");
                Matcher matcher = pattern.matcher(saCellNumber);
                if (!matcher.matches()) {
                    break;
                }
            }
            return saCellNumber;
        }
    }
    private String GenerateMobileNumber(Boolean valid) {
        String mobileNumber;
        if (valid) {
            while (true) {
                mobileNumber = fakeValuesService.regexify("[156789][0-9]{8}");
                Pattern pattern = Pattern.compile("[156789][0-9]{8}");
                Matcher matcher = pattern.matcher(mobileNumber);
                if (matcher.matches()) {
                    break;
                }
            }
        } else {
            while (true) {
                mobileNumber = fakeValuesService.bothify("##########");
                Pattern pattern = Pattern.compile("(0)[156789][0-9]{8}");
                Matcher matcher = pattern.matcher(mobileNumber);
                if (!matcher.matches()) {
                    break;
                }
            }
        }
        return mobileNumber;
    }

    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }

    /**
     * Generates random SA ID number using regex expression
     * Checks is valid, citizen, older than 18 years old
     * @return 7712315046081
     */
    private String GenerateValidSAIdNumber() {
        String idNumber;
        WebDriver IdGenerate =TestCaseBase.initializeTestBaseSetup(ConfigFileReader.getPropertyVal("BrowserType"));
        DataTable2 dataTable2 = new DataTable2();

        String website = dataTable2.getCommonDataFromSheet("Common_Data", "Id_Generate_Website");
        IdGenerate.navigate().to(website);
        
		Action action = new Action(IdGenerate);
		action.waitForJStoLoad(30);
		
        WebElement idNumberFromSite = IdGenerate.findElement(By.xpath("//*[@class=\"extra\"]/dl[2]/dd"));
        idNumber = idNumberFromSite.getText().trim();
        System.out.println("ID NUMBER HERE - " + idNumberFromSite.getText());
        IdGenerate.quit();
        return idNumber;
    }

    
    private String GenerateValidPassPortNumber() {
    	String passportNumber;
        passportNumber= fakeValuesService.regexify("[A-Z]{2}[0-9]{2}[ABCD]{2}[0-9]{2}");
        return passportNumber;
    }

    public String GenerateRequiredData(String dataTypeRequired) {
        String requiredData = null;
        switch (dataTypeRequired.toUpperCase()) {
            case "GENERATED NAME":
                firstName = faker.name().firstName();
                requiredData = firstName;
                currentFirstName =  requiredData;
                break;
            case "GENERATED - USE NAME":
                requiredData = currentFirstName;
                break;
            case "GENERATED LAST NAME":
                lastName = faker.name().lastName();
                requiredData = lastName;
                currentLastName =  requiredData;
                break;
            case "GENERATED - USE LASTNAME":
                requiredData = currentLastName;
                break;
            case "GENERATED EMAIL":
                firstName = faker.name().firstName();
                currentFirstName = firstName;
                requiredData = "fake"+faker.number().digits(15) + "@automationjdg.co.za".toLowerCase();
                currentEmail = requiredData;
                break;
            case "GENERATED EMAIL - USE NAME":
                requiredData = "fake"+faker.number().digits(10) + "@automationjdg.co.za".toLowerCase();
                currentEmail = requiredData;
                break;
            case "GENERATED - USE EMAIL":
                requiredData = currentEmail;
                break;
            case "GENERATED CELL NUMBER":
                requiredData = GenerateSACellNumber(true);
                currentCellNumber = requiredData;
                break;
            case "GENERATED - USE CELL NUMBER":
                requiredData = currentCellNumber;
                break;
            case "GENERATED MOBILE NUMBER":
                requiredData = GenerateMobileNumber(true);
                currentMobileNumber = requiredData;
                break;
            case "GENERATED - USE MOBILE NUMBER":
                requiredData = currentMobileNumber;
                break;
            case "GENERATED VALID SA ID NUMBER":
                requiredData = GenerateValidSAIdNumber();
                break;
            case "GENERATED PASSPORT NUMBER":
                requiredData = GenerateValidPassPortNumber();
                passportID = requiredData;
                
                break;
            case "GENERATED NUMBER - 10":
                requiredData = faker.number().digits(10).replace('0', '1');
                break;
            case "GENERATED PASSWORD":
                requiredData = "S!s"+faker.number().digits(10).replace('0', '1');
                break;
            default:
                requiredData=dataTypeRequired;
                break;
        }
        return requiredData;
    }

    public String generateRandomValue(int length) {

        //int length = 10;
        boolean useLetters = false;
        boolean useNumbers = true;
        String generatedValue = RandomStringUtils.random(length, useLetters, useNumbers);
        System.out.println(generatedValue);
        return generatedValue;
    }

    public String generateRandomString(int length) {

        //int length = 10;
        boolean useLetters = true;
        boolean useNumbers = false;
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
        System.out.println(generatedString);
        return generatedString;
    }

    public String determineBrandFromName(String name) throws Exception {
        if (name.toUpperCase().contains("CHECKERS")) {
            return "Checkers";
        } else if (name.toUpperCase().contains("SHOPRITE")){
            return "Shoprite";
        }else if (name.toUpperCase().contains("CFS")) {
            return "CFS";
        }
        throw new Exception("Brand not determined");
    }

    public String determineEnvironmentFromName(String name) throws Exception {
        if (name.toUpperCase().contains("QA")) {
            return "QA";
        } else if (name.toUpperCase().contains("PREP")) {
            return "PREP";
        } else if (name.toUpperCase().contains("PROD")) {
            return "PROD";
        }
        throw new Exception("Environment not determined");
    }
}
