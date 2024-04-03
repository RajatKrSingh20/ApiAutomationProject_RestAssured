package TestCase;

import Utility.ExtentListenersClass;
import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;



//Implementing ITestListener interface to listen all the events happening inside this programme also integrated ExtentReport to log all the details with it.
@Listeners(ExtentListenersClass.class)
public class TestScript_002 extends BaseClass{


    @Test(description = "Validation of the httpResponse received against httpGetRequestSingleUser to a specific RestApi")
    public void responseForHttpGetRequest_SingleUser(){


        //Instantiating Log4j library in order to log the respective details about the events happening inside this programme to a logReport named as "myLog.log" in the "logs" directory.
        logger = LogManager.getLogger("responseForHttpGetRequest_SingleUser");


        //Using functions of "readConfigFile class" in order to retrieve different values stored in the "URLs.properties" file in the "Resources" directory.
        String baseUri = readConfigFile.getBaseUri();

        //getting basePath of the URL by using a function of "readConfigFile class" and also providing "path parameter" as argument to the parameter of this function for creating HTTP-Get request for Single user..
        String basePath = readConfigFile.getBasePath_For_Get_Request_SingleUser("2");

        try{

            // Getting response of the HttpGet request using function of "createRequestClass" that was instantiated earlier.
            response =  createRequestClass.createHttpGetRequest_SingleUser(baseUri,basePath);

            // using a function of a pre-defined class "JsonPath" of "RestAssured" library in order to get the Path of all the keys present inside the jsonData in the Response received earlier against Http-Post request.
            jsonPath = response.jsonPath();

        }catch(Exception exception){
            System.out.println("Exception has been managed : "+exception.getMessage());
            Reporter.log("Exception has been managed : "+exception.getMessage());
            logger.info("Exception has been managed : "+exception.getMessage());
        }

        //Getting StatusCode of the Response received earlier against Http-Get Request for Single User and will validate it against the Expected Status Code.
        int expectedStatusCode = 200;
        int actualStatusCode = response.getStatusCode();

        if( actualStatusCode == expectedStatusCode) {
            Assert.assertTrue(true);
            logger.info("The Status code of the HttpResponse received against Http-Get request for Single User is the same as expected : " + actualStatusCode);
            Reporter.log("The Status code of the HttpResponse received against Http-Get request for Single User is the same as expected : " + actualStatusCode);

            //Getting ResponseBody as String
            Reporter.log("The Response Body received against Http-Get request for Single User is : "+response.asString());
            logger.info("The Response Body received against Http-Get request for Single User is : "+response.asString());
        }
        else{
            logger.info("The status code of the HttpResponse received against Http-Get request for Single User is not the same as expected as the actualStatusCode : "+actualStatusCode+ " & the expectedStatusCode : "+expectedStatusCode);
            Reporter.log("The status code of the HttpResponse received against Http-Get request for Single User is not the same as expected as the actualStatusCode : "+actualStatusCode+ " & the expectedStatusCode : "+expectedStatusCode);
            logger.error("Assertion failed");
            Reporter.log("Assertion failed");
            Assert.assertTrue(false);
        }


        //Getting the value of a key that is inside the JsonPayload, received in the ResponseBody in response of HttpPostRequest by providing the path of the key inside jsonData.
        //We are using the data type for "email_Id_of_User" as "var" because the value of key inside JsonData could be of any DataType.
        var email_Id_of_User   = jsonPath.get("data.email");
        String expected_emailId_of_User = "janet.weaver@reqres.in";

        //Validating the Actual value of the key i.e. "email" with the expected value.
        if(email_Id_of_User.equals(expected_emailId_of_User)){
            logger.info("Actual Email_Id in the jsonPayload of ResponseBody received against Http-get request is the same as expected as the Actual_Email_Id of the user is :"+email_Id_of_User+" whereas the Expected_Email_Id of the user is : "+expected_emailId_of_User);
            Reporter.log("Actual Email_Id in jsonPayload of the ResponseBody received against Http-get request is the same as expected as the Actual_Email_Id of the user is :"+email_Id_of_User+" whereas the Expected_Email_Id of the user is : "+expected_emailId_of_User);

            // Getting a value of a key contained inside the jsonPayload just received earlier in the Response against HTTP get request.
            var id = jsonPath.get("data.id");

            Reporter.log("User_id of the user in the dataBase is : "+id);
            logger.info("User_id of the user in the dataBase is : "+id);
            System.out.println();

        }
        else{
            System.out.println();
            logger.info("Actual Email_Id in the jsonPayload of ResponseBody received against Http-get request is not the same as expected as the Actual_Email_Id is : "+email_Id_of_User+" whereas the Expected_Email_Id is : "+expected_emailId_of_User );
            Reporter.log("Actual Email_Id in the jsonPayload of ResponseBody received against Http-get request is not the same as expected as the Actual_Email_Id is : "+email_Id_of_User+" whereas the Expected_Email_Id is : "+expected_emailId_of_User );
            Assert.assertTrue(false);
        }



    }
}

