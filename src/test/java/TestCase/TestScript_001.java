package TestCase;


import Utility.ExtentListenersClass;
import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import payLoad.POJO_Class_01;


//Implementing ITestListener interface to listen all the events happening inside this programme also integrated ExtentReport to log all the details with it.
@Listeners(ExtentListenersClass.class)
public class TestScript_001 extends BaseClass {


    //Using dataProvider function just created earlier in the "BaseClass" in order to provide arguments for the respective parameters of the @Test function given below,
    @Test(dataProvider ="ApiTestData",description = "Validation of the httpResponse received against httpPostRequest to a specific RestApi")
    public void responseForHttpPostRequest(String name, String job){

        //Instantiating Log4j library in order to log the respective details about the events happening inside this programme to a logReport named as "myLog.log" in the "logs" directory.
        logger = LogManager.getLogger("responseForHttpPostRequest");


        //Instantiating the "POJOClass_01" created earlier in the package named as "payLoads".
        pojo_class_01 = new POJO_Class_01();


        //Using setFunction of "POJOClass_01" in order to initialise the instance variables of the same.
        pojo_class_01.setName(name);
        pojo_class_01.setJob(job);


        //Using functions of "readConfigFile class" in order to retrieve different values stored in the "URLs.properties" file in the "Resources" directory.
        String baseUri = readConfigFile.getBaseUri();
        String basePath = readConfigFile.getBasePath_For_PostRequest();

        try{
     //Serialization of class object i.e. "POJOClass_01" into byte streams i.e. JsonData.
           payload = objectMapper.writeValueAsString(pojo_class_01);

      // Getting response of the HttpPost request using function of "createRequestClass" that was instantiated earlier.
           response =  createRequestClass.createHttp_PostRequest(baseUri,basePath,payload);

      // using a function of a pre-defined class "JsonPath" of "RestAssured" library in order to get the Path of all the keys present inside the jsonData in the Response received earlier against Http-Post request.
           jsonPath = response.jsonPath();

        }catch(Exception exception){
            System.out.println("Exception has been managed "+exception.getMessage());
       }

        //Getting StatusCode of the Response received earlier against Http Post Request and will validate it against the Expected status code.
       int expectedStatusCode = 201;
       int actualStatusCode = response.getStatusCode();

        if( actualStatusCode == expectedStatusCode) {
            Assert.assertTrue(true);
            logger.info("The Status code of the HttpResponse received against Http-Post request is same as expected : " + actualStatusCode);
            Reporter.log("The Status code of the HttpResponse received against Http-Post request is same as expected : " + actualStatusCode);

            //Getting ResponseBody as String
            Reporter.log("The Response Body received against Http-Post request is : " + response.asString());
            logger.info("The Response Body received against Http-Post request is : " + response.asString());
        }
       else{
           logger.info("The status code of the HttpResponse received against Http-Post request is not the same as expected as the actualStatusCode : "+actualStatusCode+ " & the expectedStatusCode : "+expectedStatusCode);
           Reporter.log("The status code of the HttpResponse received against Http-Post request is not the same as expected as the actualStatusCode : "+actualStatusCode+ " & the expectedStatusCode : "+expectedStatusCode);
           logger.error("Assertion failed");
           Reporter.log("Assertion failed");
           Assert.assertTrue(false);
       }

       //Getting the value of a key that is inside the JsonPayload, received in the ResponseBody in response of HttpPostRequest by providing the path of the key inside jsonData.
       //We are using the data type for "actualNameOfUser" as "var" because the value of key inside JsonData could be of any DataType.
        var actualNameOfUser   = jsonPath.get("name");
        String expectedUserName = name;

        //Validating the Actual value of the key i.e. "Username" with the expected value.
        if(actualNameOfUser.equals(expectedUserName)){
            logger.info("Actual UserName in the jsonPayload of ResponseBody received against Http-Post request is same as the Expected UserName as the ActualUserName is :"+actualNameOfUser+" whereas the ExpectedNameOfTheUser is : "+expectedUserName);
            Reporter.log("Actual UserName in jsonPayload of the ResponseBody received against Http-Post request is same as the Expected UserName as the ActualUserName is :"+actualNameOfUser+" whereas the ExpectedNameOfTheUser is : "+expectedUserName);

            // Getting a value of a key contained inside the jsonPayload just received earlier in the Response against HTTP post request.
            var id = jsonPath.get("id");

            Reporter.log("id generated for the user in the dataBase after HTTP-Post request is : "+id);
            logger.info("id generated for the user in the dataBase after HTTP-Post request is : "+id);
            System.out.println();
        }
        else{
            System.out.println();
            logger.info("Actual UserName in the ResponseBody received against Http-Post request is not the same as the Expected UserName as the ActualUserName is : "+actualNameOfUser+" whereas the ExpectedNameOfTheUser is : "+expectedUserName );
            Reporter.log("Actual UserName in the ResponseBody received against Http-Post request is not the same as the Expected UserName as the ActualUserName is : "+actualNameOfUser+" whereas the ExpectedNameOfTheUser is : "+expectedUserName );
            Assert.assertTrue(false);
            System.out.println();
        }


    }


    }








