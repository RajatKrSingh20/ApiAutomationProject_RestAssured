package Utility;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfigFile {

    FileInputStream fileInputStream ;
    Properties properties ;

    public ReadConfigFile(){
        try{
            fileInputStream = new FileInputStream("C:\\Users\\rajat\\IdeaProjects\\ApiAutomationProject_RestAssured\\src\\test\\resources\\URLs.properties");
            properties = new Properties();
            properties.load(fileInputStream);
        }catch(Exception exception){
        }
    }

    public String getBaseUri(){

        return properties.getProperty("baseUri");
    }

    public String getBasePath_For_Get_Request_SingleUser(String pathParameter){

        String basePath = properties.getProperty("basePathForGetRequestSingleUser");
        return basePath+pathParameter;
    }
    public String getBasePath_For_PostRequest(){

        return properties.getProperty("basePathForPostRequest");
    }

    public String getBasePath_For_PutRequest(String pathParameter){

        String basePath =  properties.getProperty("basePathForPutRequest");
        return basePath+pathParameter;
    }

    public String getBasePath_For_DeleteRequest(String pathParameter){

        String basePath =  properties.getProperty("basePathForDeleteRequest");
        return basePath+pathParameter;
    }

    public String getBasePath_For_GetRequest_ListOfUsers(){
        return properties.getProperty("basePathForGetRequestListOfUsers");
    }

}
