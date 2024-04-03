package TestCase;

import Utility.ExtentListenersClass;
import org.apache.logging.log4j.LogManager;
import org.json.simple.JSONArray;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import payLoad.POJO_Class_02;


//Implementing ITestListener interface to listen all the events happening inside this programme also integrated ExtentReport to log all the details with it.
@Listeners(ExtentListenersClass.class)
public class testscript006 extends BaseClass{

//Initializing JsonArray class of Json-Simple library in order to use it to create JsonArray as Payload in the below TestCases
    JSONArray jsonArray = new JSONArray();

    //Using dataProvider function tha was created earlier in the "BaseClass" of "TestCase package" in order to provide arguments for the respective parameters of the @Test function given below,
@Test(dataProvider ="ApiTestData_02",priority = 1)
public void createJsonArrayAs_Payload(String FirstName, String LastName, String Gender, String Age, String Salary){

    //Instantiating Log4j library in order to log the respective details about the events happening inside this programme to a logReport named as "myLog.log" in the "logs" directory.
    logger = LogManager.getLogger(" createJsonArrayAs_Payload");

    //Instantiating the "POJOClass_02" created earlier in the package named as "payLoads".
    pojo_class_02 = new POJO_Class_02();

    //Using setFunction of "POJOClass_02" in order to initialise the instance variables of the same.
    pojo_class_02.setFirstName(FirstName);
    pojo_class_02.setLastName(LastName);
    pojo_class_02.setGender(Gender);
    pojo_class_02.setAge(Age);
    pojo_class_02.setSalary(Salary);

    // Creating jsonArray in order to use it as Payload in the next TestCase.

    jsonArray.add(pojo_class_02);

    Reporter.log("Creating jsonArray by adding POJO_Class_02 object one by one in order to use it as Payload in the next TestCase");
    logger.info("Creating jsonArray in order to use it as Payload in the next TestCase");
    System.out.println();
}


    @Test(priority = 2,description = "Validation of the httpResponse received against httpPostRequest having JsonArray as Payload to a specific RestApi")
    public void responseForHttp_PostRequest_JsonArray_asPayload(){

        //Instantiating Log4j library in order to log the respective details about the events happening inside this programme to a logReport named as "myLog.log" in the "logs" directory.
        logger = LogManager.getLogger("responseForHttp_PostRequest_JsonArray_asPayload");

        //Using functions of "readConfigFile class" in order to retrieve different values stored in the "URLs.properties" file in the "Resources" directory.
        String baseUri = readConfigFile.getBaseUri();
        String basePath = readConfigFile.getBasePath_For_PostRequest();

        try{

            //Serialization of class object i.e. "POJOClass_02" into byte streams i.e. JsonData.
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
            logger.info("The Status code of the HttpResponse received against Http-Post request having JsonArray as Payload is same as expected : " + actualStatusCode);
            Reporter.log("The Status code of the HttpResponse received against Http-Post request having JsonArray as Payload is same as expected : " + actualStatusCode);

           //Getting ResponseBody as String
            Reporter.log("ResponseBody received : "+response.asString());
            logger.info("ResponseBody received : "+response.asString());

            System.out.println();

        } else{
            System.out.println();
            logger.info("The status code of the HttpResponse received against Http-Post request having JsonArray as Payload is not the same as expected as the actualStatusCode : "+actualStatusCode+ " & the expectedStatusCode : "+expectedStatusCode);
            Reporter.log("The status code of the HttpResponse received against Http-Post request having JsonArray as Payload is not the same as expected as the actualStatusCode : "+actualStatusCode+ " & the expectedStatusCode : "+expectedStatusCode);
            logger.error("Assertion failed");
            Reporter.log("Assertion failed");
            Assert.assertTrue(false);

        }

}}
