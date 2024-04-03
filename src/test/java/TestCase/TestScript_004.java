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
public class TestScript_004 extends BaseClass{

   //Using dataProvider function just created earlier in order to provide arguments for the respective parameters of the @Test function given below,
    @Test(dataProvider ="ApiTestData",description = "Validation of the httpResponse received against httpPutRequest to a specific RestApi")
    public void responseForHttp_PutRequest(String name, String job){

        //Instantiating Log4j library in order to log the respective details about the events happening inside this programme to a logReport named as "myLog.log" in the "logs" directory.
        logger = LogManager.getLogger("responseForHttp_PutRequest");


        //Using functions of "readConfigFile class" in order to retrieve different values stored in the "URLs.properties" file in the "Resources" directory.
        String baseUri = readConfigFile.getBaseUri();

        //getting basePath of the URL by using a function of "readConfigFile class" and also providing "path parameter" as argument to the parameter of this function for creating he Url and then  HTTP-Put request.
        String basePath = readConfigFile.getBasePath_For_PutRequest("2");


        //Instantiating the "POJOClass_01" created earlier in the package named as "payLoads".
        pojo_class_01 = new POJO_Class_01();


        //Using setFunction of "POJOClass_01" in order to initialise the instance variables of the same.
        pojo_class_01.setName(name);
        pojo_class_01.setJob(job);


        try{
            //Serialization of class object i.e. "POJOClass_01" into byte streams i.e. JsonData.
            payload = objectMapper.writeValueAsString(pojo_class_01);

            // Getting response of the HttpPut request using function of "createRequestClass" that was instantiated earlier.
            // providing basePath, baseUri as arguments to the function below in order to construct the URL and the HTTPRequest.
            response =  createRequestClass.createHttp_PutRequest(baseUri,basePath,payload);

            // using a function of a pre-defined class "JsonPath" of "RestAssured" library in order to get the Path of all the keys present inside the jsonData in the Response received earlier against Http-Post request.
            jsonPath = response.jsonPath();

        }catch(Exception exception){
            System.out.println("Exception has been managed : "+exception.getMessage());
            Reporter.log("Exception has been managed : "+exception.getMessage());
            logger.info("Exception has been managed : "+exception.getMessage());
        }

        //Getting StatusCode of the Response received earlier against Http-Put Request  and will validate it against the Expected Status Code.
        int expectedStatusCode = 200;
        int actualStatusCode = response.getStatusCode();

        if( actualStatusCode == expectedStatusCode) {
            Assert.assertTrue(true);
            logger.info("The Status code of the HttpResponse received against Http-Put request is the same as expected : " + actualStatusCode);
            Reporter.log("The Status code of the HttpResponse received against Http-Put request is the same as expected : " + actualStatusCode);

            //Getting ResponseBody as String
            Reporter.log("The Response Body received against Http-Put request is : "+response.asString());
            logger.info("The Response Body received against Http-Put request is : "+response.asString());
        }
        else{
            logger.info("The status code of the HttpResponse received against Http-Put request is not the same as expected as the actualStatusCode : "+actualStatusCode+ " & the expectedStatusCode : "+expectedStatusCode);
            Reporter.log("The status code of the HttpResponse received against Http-Put request is not the same as expected as the actualStatusCode : "+actualStatusCode+ " & the expectedStatusCode : "+expectedStatusCode);
            logger.error("Assertion failed");
            Reporter.log("Assertion failed");
            Assert.assertTrue(false);
        }

        //Getting the value of a key that is inside the JsonPayload, received in the ResponseBody in response of HttpPutRequest by providing the path of the key inside jsonData.
        //We are using the data type for "actualJobOfUser_Morpheus" as "var" because the value of key inside JsonData could be of any DataType.
        var actualJobOfUser_Morpheus   = jsonPath.get("job");
        String expectedJobOfUser_Morpheus = job;

        //Validating the Actual value of the key i.e. "job" with the expected value.
        if(actualJobOfUser_Morpheus.equals(expectedJobOfUser_Morpheus)){
            logger.info("The actualJobOfUser_Morpheus in the jsonPayload of ResponseBody received against Http-Put request is the same as expected as the ActualJobOfUser_Morpheus is :"+actualJobOfUser_Morpheus+" whereas the ExpectedJobOfUser_Morpheus is : "+expectedJobOfUser_Morpheus);
            Reporter.log("The actualJobOfUser_Morpheus in the jsonPayload of ResponseBody received against Http-Put request is the same as expected as the ActualJobOfUser_Morpheus is :"+actualJobOfUser_Morpheus+" whereas the ExpectedJobOfUser_Morpheus is : "+expectedJobOfUser_Morpheus);

            // Getting a value of a key contained inside the jsonPayload just received earlier in the Response against HTTP post request.
            var updatedAt = jsonPath.get("updatedAt");

            Reporter.log("The information of the user is updated through HTTP-Put request at : "+updatedAt);
            logger.info("The information of the user is updated through HTTP-Put request at : "+updatedAt);
            System.out.println();
        }
        else{
            System.out.println();
            logger.info("The actualJobOfUser_Morpheus in the jsonPayload of ResponseBody received against Http-Put request is not the same as expected  as the ActualJobOfUser_Morpheus is :"+actualJobOfUser_Morpheus+" whereas the ExpectedJobOfUser_Morpheus is : "+expectedJobOfUser_Morpheus);
            Reporter.log("The actualJobOfUser_Morpheus in the jsonPayload of ResponseBody received against Http-Put request is not the same as expected  as the ActualJobOfUser_Morpheus is :"+actualJobOfUser_Morpheus+" whereas the ExpectedJobOfUser_Morpheus is : "+expectedJobOfUser_Morpheus);
            Assert.assertTrue(false);
        }

        System.out.println();
    }
}
