package api.endpoints;

import static io.restassured.RestAssured.given;

import org.payload.Users;

import Excel.ToReadExcel;
import api.utilies.APIValidations;
import api.utilies.ToReadJson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GuideLinesEndPoints extends APIValidations{
	static String token;
	 static RequestSpecification requestSpecification;

		public static void toGenerate() throws Throwable {

			 token = given().contentType(ContentType.JSON)
					 .accept(ContentType.JSON)
					 .header("app_language","en").header("app_version","1")
					.body(ToReadJson.readJson())
					.when().post(ToReadExcel.readData(1, 0,"Sheet2")).then().extract().response()
					.jsonPath().getString("result.token");
					 System.out.println("-----------Token Value-----------------------");
					 System.out.println("Token: "+token);
					 System.out.println("-------------End---------------------");
					
			

		}
		
		public static void reqSpec() {
			
			requestSpecification=RestAssured.given();
			requestSpecification.contentType(ContentType.JSON).accept(ContentType.JSON)
			.header("app_language","en").header("app_version","1")
			.header("Authorization",token);

		}

		public static Response createGuideLine() throws Throwable {
			reqSpec();
			Response response= given().spec(requestSpecification).body(ToReadExcel.readData(1,5,"Sheet1"))
					.when().post(ToReadExcel.readData(1,4,"Sheet1"));
				return response;

		}
		 
		public static Response viewAllGuideLine(int pageNo, int limitNo,String field,String sort) throws Throwable {
			reqSpec();
			Response response= given().spec(requestSpecification).pathParam("page_no",pageNo ).pathParam("limit_no", limitNo).pathParam("sortField", field).pathParam("sortBy",sort)
					.when().get(ToReadExcel.readData(2,4,"Sheet1"));
				return response;
			

		}
		public static Response toEditGuideLine() throws Throwable {
			reqSpec();
			Response response= given().spec(requestSpecification)
					.body(ToReadExcel.readData(3,5,"Sheet1"))
					.when().post(ToReadExcel.readData(3,4,"Sheet1"));
				return response;

		}
		
		public static Response toDeleteGuideLine(int guideLineId ) throws Throwable {
			reqSpec();
			Response response= given().spec(requestSpecification).pathParam("id",guideLineId )
					.when().delete(ToReadExcel.readData(4,4,"Sheet1"));
				return response;

		}
		public static Response add_User(Users payload) throws Throwable {
			reqSpec();
		
			Response response= given().spec(requestSpecification).body(payload)
					.when().post(ToReadExcel.readData(5,4,"Sheet1"));
				return response;

		}
}
