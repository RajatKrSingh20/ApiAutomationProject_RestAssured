package TestCase;

import org.apache.logging.log4j.LogManager;
import org.json.simple.JSONArray;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import payLoad.Address;
import payLoad.POJO_Class_03;

public class TestScript_007 extends BaseClass{

    //Initializing JsonArray class of Json-Simple library in order to use it to create JsonArray to be used as Payload in the below TestCases
    JSONArray jsonArray = new JSONArray();

    //Using dataProvider function tha was created earlier in the "BaseClass" of "TestCase package" in order to provide arguments for the respective parameters of the @Test function given below,
    @Test(dataProvider ="ApiTestData_03",priority = 1)
    public void createJsonArrayAs_Payload_usingNestedJsonData(String FirstName, String LastName, String Gender, String Age, String Salary, String Street, String City, String State, String PinCode){

        //Instantiating Log4j library in order to log the respective details about the events happening inside this programme to a logReport named as "myLog.log" in the "logs" directory.
        logger = LogManager.getLogger("createJsonArrayAs_Payload_usingNestedJsonData");

        //Instantiating the "Address_Class" created earlier in the package named as "payLoads".
        address = new Address();

        //Instantiating the "POJOClass_03" created earlier in the package named as "payLoads".
        pojo_class_03 = new POJO_Class_03();

        //Using setFunction of "Address" in order to initialise the instance variables of the same.
        address.setStreet(Street);
        address.setCity(City);
        address.setState(State);
        address.setPinCode(PinCode);

        //Using setFunction of "POJOClass_02" in order to initialise the instance variables of the same.
        pojo_class_03.setFirstName(FirstName);
        pojo_class_03.setLastName(LastName);
        pojo_class_03.setGender(Gender);
        pojo_class_03.setAge(Age);
        pojo_class_03.setSalary(Salary);
        pojo_class_03.setAddress(address);

        // Creating jsonArray in order to use it as Payload in the next TestCase.

        jsonArray.add(pojo_class_03);

        Reporter.log("Creating jsonArray by adding POJO_Class_03 object one by one in order to use it as Payload in the next TestCase");
        logger.info("Creating jsonArray in order to use it as Payload in the next TestCase");
        System.out.println();
    }

    @Test(priority = 2,description = "Validation of the httpResponse received against httpPostRequest having JsonArray as Payload_nestedJsonData to a specific RestApi")
    public void responseForHttp_PostRequest_JsonArray_asPayload_nestedJsonPayload(){

        //Instantiating Log4j library in order to log the respective details about the events happening inside this programme to a logReport named as "myLog.log" in the "logs" directory.
        logger = LogManager.getLogger("responseForHttp_PostRequest_JsonArray_asPayload_nestedJsonPayload");

        //Using functions of "readConfigFile class" in order to retrieve different values stored in the "URLs.properties" file in the "Resources" directory.
        String baseUri = readConfigFile.getBaseUri();
        String basePath = readConfigFile.getBasePath_For_PostRequest();

        try{

            //Serialization of class object i.e. "POJOClass_03" into byte streams i.e. JsonData.
            payload = objectMapper.writeValueAsString(jsonArray);

            // Getting response of the HttpPost request using function of "createRequestClass" that was instantiated earlier.
            response =  createRequestClass.createHttp_PostRequest(baseUri,basePath,payload);


        }catch(Exception exception){
            System.out.println("Exception has been managed "+exception.getMessage());
        }

        //Getting StatusCode of the Response received earlier against Http Post Request using JsonArray as payload and will validate it against the Expected status code.
        int expectedStatusCode = 201;
        int actualStatusCode = response.getStatusCode();

        if( actualStatusCode == expectedStatusCode) {
            Assert.assertTrue(true);
            logger.info("The Status code of the HttpResponse received against Http-Post request having JsonArray as Payload_nestedJsonData is same as expected : " + actualStatusCode);
            Reporter.log("The Status code of the HttpResponse received against Http-Post request having JsonArray as Payload_nestedJsonData is same as expected : " + actualStatusCode);

            //Getting ResponseBody as String
            Reporter.log("ResponseBody received : "+response.asString());
            logger.info("ResponseBody received : "+response.asString());
            System.out.println();

        } else{
            System.out.println();
            logger.info("The status code of the HttpResponse received against Http-Post request having JsonArray as Payload_nestedJsonData is not the same as expected as the actualStatusCode : "+actualStatusCode+ " & the expectedStatusCode : "+expectedStatusCode);
            Reporter.log("The status code of the HttpResponse received against Http-Post request having JsonArray as Payload_nestedJsonData is not the same as expected as the actualStatusCode : "+actualStatusCode+ " & the expectedStatusCode : "+expectedStatusCode);
            logger.error("Assertion failed");
            Reporter.log("Assertion failed");
            Assert.assertTrue(false);
        }

    }}









