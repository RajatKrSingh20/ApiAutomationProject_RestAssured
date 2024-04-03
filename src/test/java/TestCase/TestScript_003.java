package TestCase;

import Utility.ExtentListenersClass;
import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

//Implementing ITestListener interface to listen all the events happening inside this programme also integrated ExtentReport to log all the details with it.
@Listeners(ExtentListenersClass.class)
public class TestScript_003 extends BaseClass{

    @Test(description = "Validation of the httpResponse received against httpGetRequestListOfUsers to a specific RestApi")
    public void responseForHttpGetRequest_ListOfUsers(){

        //Instantiating Log4j library in order to log the respective details about the events happening inside this programme to a logReport named as "myLog.log" in the "logs" directory.
        logger = LogManager.getLogger("responseForHttpGetRequest_ListOfUsers");


        //Using functions of "readConfigFile class" in order to retrieve different values stored in the "URLs.properties" file in the "Resources" directory.
        String baseUri = readConfigFile.getBaseUri();

        //getting basePath of the URL by using a function of "readConfigFile class" for creating HTTP-Get request for Multi-users.
        String basePath = readConfigFile.getBasePath_For_GetRequest_ListOfUsers();

        try{

            // Getting response of the HttpGet request for List of Users using function of "createRequestClass" that was instantiated earlier.
            // providing basePath, baseUri and QueryParameter as arguments to the function below in order to construct the URL and the HTTPRequest
            response =  createRequestClass.createHttpGetRequest_ListOfUsers(baseUri,basePath,"page=2");

            // using a function of a pre-defined class "JsonPath" of "RestAssured" library in order to get the Path of all the keys present inside the jsonData in the Response received earlier against Http-Post request.
            jsonPath = response.jsonPath();

        }catch(Exception exception){
            System.out.println("Exception has been managed : "+exception.getMessage());
            Reporter.log("Exception has been managed : "+exception.getMessage());
            logger.info("Exception has been managed : "+exception.getMessage());
        }

        //Getting StatusCode of the Response received earlier against Http-Get Request for List of Users and will validate it against the Expected Status Code.
        int expectedStatusCode = 200;
        int actualStatusCode = response.getStatusCode();

        if( actualStatusCode == expectedStatusCode) {
            Assert.assertTrue(true);
            logger.info("The Status code of the HttpResponse received against Http-Get request for List of Users is the same as expected : " + actualStatusCode);
            Reporter.log("The Status code of the HttpResponse received against Http-Get request for List of Users is the same as expected : " + actualStatusCode);

            //Getting ResponseBody as String
            Reporter.log("The Response Body received against Http-Get request for List of Users is : "+response.asString());
            logger.info("The Response Body received against Http-Get request for List of Users is : "+response.asString());

        }
        else{
            logger.info("The status code of the HttpResponse received against Http-Get request for List of Users is not the same as expected as the actualStatusCode : "+actualStatusCode+ " & the expectedStatusCode : "+expectedStatusCode);
            Reporter.log("The status code of the HttpResponse received against Http-Get request for List of Users is not the same as expected as the actualStatusCode : "+actualStatusCode+  " & the expectedStatusCode : "+expectedStatusCode);
            logger.error("Assertion failed");
            Reporter.log("Assertion failed");
            Assert.assertTrue(false);
        }

        //Getting the value of a key that is inside the JsonPayload, received in the ResponseBody in response of HttpPostRequest by providing the path of the key inside jsonData.
        //We are here using the data type for "LastName_OfUser" as "var" because the value of key inside JsonData could be of any DataType.
        var LastNameOfUser_Tracey   = jsonPath.get("data[5].last_name");
        String expected_LastNameOfUser_Tracey = "Ramos";

        //Validating the Actual value of the key i.e. "email" with the expected value.
        if(LastNameOfUser_Tracey.equals(expected_LastNameOfUser_Tracey)){
            logger.info("Actual LastNameofTheUser_Tracey in the jsonPayload of ResponseBody received against Http-get request_ListOfUsers is the same as expected as the Actual_LastName of the user Tracey is :"+LastNameOfUser_Tracey+" whereas the Expected_LastName of the user Tracey is : "+expected_LastNameOfUser_Tracey);
            Reporter.log("Actual LastNameofTheUser_Tracey in the jsonPayload of ResponseBody received against Http-get request_ListOfUsers is the same as expected as the Actual_LastName of the user Tracey is :"+LastNameOfUser_Tracey+" whereas the Expected_LastName of the user Tracey is : "+expected_LastNameOfUser_Tracey);

            // Getting a value of a key contained inside the jsonPayload just received earlier in the Response against HTTP get request_ListOfUsers.
            var id = jsonPath.get("data[5].id");

            Reporter.log("User_id of the user whose name is Tracey in the dataBase is : "+id);
            logger.info("User_id of the user whose name is Tracey in the dataBase is : "+id);
            System.out.println();
        }
        else{
            System.out.println();
            logger.info( "Actual LastNameofTheUser_Tracey in the jsonPayload of ResponseBody received against Http-get request_ListOfUsers is not the same as expected as the Actual_LastName of the user Tracey is :"+LastNameOfUser_Tracey+" whereas the Expected_LastName of the user Tracey is : "+expected_LastNameOfUser_Tracey);
            Reporter.log(" Actual LastNameofTheUser_Tracey in the jsonPayload of ResponseBody received against Http-get request_ListOfUsers is not the same as expected as the Actual_LastName of the user Tracey is :"+LastNameOfUser_Tracey+" whereas the Expected_LastName of the user Tracey is : "+expected_LastNameOfUser_Tracey);
            Assert.assertTrue(false);
        }



    }
}
