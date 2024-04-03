package TestCase;

import Utility.ReadConfigFile;
import Utility.ReadExcelFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import endPoints.CreateRequestClass;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import payLoad.Address;
import payLoad.POJO_Class_01;
import payLoad.POJO_Class_02;
import payLoad.POJO_Class_03;

public class BaseClass {

    ReadExcelFile readExcelFile;
    ObjectMapper objectMapper;
    POJO_Class_01 pojo_class_01;
    POJO_Class_02 pojo_class_02 ;
    POJO_Class_03 pojo_class_03 ;
    Address address ;

    CreateRequestClass createRequestClass;
    ReadConfigFile readConfigFile ;
    Response response;
    String payload;

    JsonPath jsonPath;
    Logger logger;



    //Creating a DataProvider function which will be used to provide arguments for respective parameters of the @Test functions
    @DataProvider(name="ApiTestData")
    public Object[][] dataProviders(){

        //Instantiating "ReadExcelFile class" of the "Utilities" package to access the function stored in it, in order to read & get the data, stored in the ExcelFile in the "TestData" directory.
        readExcelFile = new ReadExcelFile("ApiTestData");
        Object[][] data = readExcelFile.getExcelData(0);

        return data ;
    }

    @DataProvider(name="ApiTestData_02")
    public Object[][] dataProviders_02(){

        //Instantiating "ReadExcelFile class" of the "Utilities" package to access the function stored in it, in order to read & get the data, stored in the ExcelFile in the "TestData" directory.
        readExcelFile = new ReadExcelFile("ApiTestData_02");
        Object[][] data = readExcelFile.getExcelData(0);

        return data ;
    }

    @DataProvider(name="ApiTestData_03")
    public Object[][] dataProviders_03(){

        //Instantiating "ReadExcelFile class" of the "Utilities" package to access the function stored in it, in order to read & get the data, stored in the ExcelFile in the "TestData" directory.
        readExcelFile = new ReadExcelFile("ApiTestData_03");
        Object[][] data = readExcelFile.getExcelData(0);

        return data ;
    }

    @BeforeClass
    public void setupConfig(){
// Here we are instantiating all the classes which will be commonly used in the upcoming TestCases.

        //Instantiating "ObjectMapper class" of the Jackson-databind library.
        objectMapper = new ObjectMapper();

        //Instantiating "createRequestClass" which was created earlier in the Package named as "endPoints".
        createRequestClass = new CreateRequestClass();

        //Instantiating "ReadConfigFile class" of Utilities package in order to use the function inside it.
        readConfigFile = new ReadConfigFile();
    }




}
