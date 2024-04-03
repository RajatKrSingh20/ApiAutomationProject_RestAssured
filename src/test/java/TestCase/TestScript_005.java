package TestCase;

import Utility.ExtentListenersClass;
import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

//Implementing ITestListener interface to listen all the events happening inside this programme also integrated ExtentReport to log all the details with it.
@Listeners(ExtentListenersClass.class)
public class TestScript_005 extends BaseClass {

    @Test(description = "Validation of the httpResponse received against http_DeleteRequest to a specific RestApi")
    public void responseFor_HttpDeleteRequest(){


        //Instantiating Log4j library in order to log the respective details about the events happening inside this programme to a logReport named as "myLog.log" in the "logs" directory.
        logger = LogManager.getLogger("responseFor_HttpDeleteRequest");


        //Using functions of "readConfigFile class" in order to retrieve different values stored in the "URLs.properties" file in the "Resources" directory.
        String baseUri = readConfigFile.getBaseUri();

        //getting basePath of the URL by using a function of "readConfigFile class" and also providing "path parameter" as argument to the parameter of this function for creating HTTP-Delete request.
        String basePath = readConfigFile.getBasePath_For_DeleteRequest("2");

        try{

            // Getting response of the HttpDelete request using function of "createRequestClass" that was instantiated earlier.
            response =  createRequestClass.createHttp_DeleteRequest(baseUri,basePath);


        }catch(Exception exception){
            System.out.println("Exception has been managed : "+exception.getMessage());
            Reporter.log("Exception has been managed : "+exception.getMessage());
            logger.info("Exception has been managed : "+exception.getMessage());
        }

        //Getting StatusCode of the Response received earlier against Http-Delete Request and will validate it against the Expected Status Code.
        int expectedStatusCode = 204;
        int actualStatusCode = response.getStatusCode();

        if( actualStatusCode == expectedStatusCode) {
            Assert.assertTrue(true);
            logger.info("The Status code of the HttpResponse received against Http-Delete request is the same as expected : " + actualStatusCode);
            Reporter.log("The Status code of the HttpResponse received against Http-Delete request is the same as expected : " + actualStatusCode);
            System.out.println();
        }

        else{
            System.out.println();
            logger.info("The status code of the HttpResponse received against the Http-Delete request is not the same as expected as the actualStatusCode : "+actualStatusCode+" & the expectedStatusCode : "+expectedStatusCode);
            Reporter.log("The status code of the HttpResponse received against the Http-Delete request is not the same as expected as the actualStatusCode : "+actualStatusCode+" & the expectedStatusCode : "+expectedStatusCode );
            logger.error("Assertion failed");
            Reporter.log("Assertion failed");
            Assert.assertTrue(false);
        }



        System.out.println();
    }
}
