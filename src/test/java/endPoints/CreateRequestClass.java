package endPoints;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateRequestClass {

    RequestSpecification requestSpecification;
    Response response ;


    public Response createHttp_PostRequest(String baseUri,String basePath, String jsonPayload){


        requestSpecification = RestAssured.given();
        requestSpecification.baseUri(baseUri);
        requestSpecification.basePath(basePath);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(jsonPayload);
        response = requestSpecification.post();

        return response ;

    }

    public Response createHttpGetRequest_SingleUser(String baseUri,String basePath){


        requestSpecification = RestAssured.given();
        requestSpecification.baseUri(baseUri);
        requestSpecification.basePath(basePath);
        response = requestSpecification.get();

        return response ;

    }

    public Response createHttpGetRequest_ListOfUsers(String baseUri,String basePath, String queryParameter){


        requestSpecification = RestAssured.given();
        requestSpecification.baseUri(baseUri);
        requestSpecification.basePath(basePath);
        requestSpecification.queryParam(queryParameter);

        response = requestSpecification.get();

        return response ;

    }

    public Response createHttp_PutRequest(String baseUri,String basePath, String jsonPayload){


        requestSpecification = RestAssured.given();
        requestSpecification.baseUri(baseUri);
        requestSpecification.basePath(basePath);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(jsonPayload);
        response = requestSpecification.put();

        return response ;

    }

    public Response createHttp_DeleteRequest(String baseUri,String basePath){


        requestSpecification = RestAssured.given();
        requestSpecification.baseUri(baseUri);
        requestSpecification.basePath(basePath);
        response = requestSpecification.delete();

        return response ;

    }

    public Response createHttp_PostRequest_using_JsonArray_as_PayLoad(String baseUri,String basePath, String jsonPayload){


        requestSpecification = RestAssured.given();
        requestSpecification.baseUri(baseUri);
        requestSpecification.basePath(basePath);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(jsonPayload);
        response = requestSpecification.post();

        return response ;

    }



}
