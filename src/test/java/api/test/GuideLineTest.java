package api.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import DyanamicData.UserData;
import Excel.ToReadExcel;
import api.endpoints.GuideLinesEndPoints;
import api.utilies.APIValidations;
import api.utilies.Reports;
import api.utilies.ToReadJson;
import io.restassured.response.Response;

public class GuideLineTest extends APIValidations{
	@BeforeClass
	 public void toGenerateReport() throws Throwable {
	 Reports.Report(); 
	 Reports.ReportCreateTestcase("API RESPONSE");
	
	  }
	
	@Test
	public void keyGeneration() throws Throwable {
		Reports.ReportCreateTestcase("token");
		GuideLinesEndPoints.toGenerate();

	}
	
	@Test(dependsOnMethods = {"keyGeneration"})
	public void toCreateGuideLine() throws Throwable {
		Reports.ReportCreateTestcase(" To Create GuideLine -- End point https://oneself-api-test.optisolbusiness.com/api/guideline/add-or-edit-guideline");
	Response response =GuideLinesEndPoints.createGuideLine();
	
	response.then().log().all();
	responsetimevalidation(response);
responsecodevalidation(response, 200);
responsebodyvalidation( response,ToReadJson.createGuideLine());
responseHeader(response, ToReadExcel.readData(1,10,"Sheet1"),ToReadExcel.readData(1,11,"Sheet1") );
responsestatuslinevalidation(response,ToReadExcel.readData(1,9,"Sheet1"));


	}
	@Test(dependsOnMethods = {"toCreateGuideLine"})
	
	public  void toViewAllGuideLine() throws Throwable {
		Reports.ReportCreateTestcase(" To View All GuideLine -- End point https://oneself-api-test.optisolbusiness.com/api/guideline/guideline-summary?guideline_id");
		
		Response response=	GuideLinesEndPoints.viewAllGuideLine(1, 10,"created_at","DESC");
		
		response.then().log().all();
		responsecodevalidation(response, 400);
		responsetimevalidation(response);
		responseHeader(response, ToReadExcel.readData(2,10,"Sheet1"),ToReadExcel.readData(2,11,"Sheet1") );
		responsestatuslinevalidation(response,ToReadExcel.readData(2,9,"Sheet1"));
		responsebodyvalidation( response,ToReadJson.toView());

	}
	@Test(dependsOnMethods = {"toViewAllGuideLine"})
	public void toEditGuideLine() throws Throwable {
		Reports.ReportCreateTestcase(" To Edit GuideLine --End point https://oneself-api-test.optisolbusiness.com/api/guideline/add-or-edit-guideline");
		//ToReadExcel.toIgnore();
		
		Response response=	GuideLinesEndPoints.toEditGuideLine();
		response.then().log().all();
		responsetimevalidation(response);
		responsecodevalidation(response,200);
		responsebodyvalidation( response,ToReadJson.toUpdate());
		responsestatuslinevalidation(response,ToReadExcel.readData(3,9,"Sheet1"));
		responseHeader(response, ToReadExcel.readData(3,10,"Sheet1"),ToReadExcel.readData(3,11,"Sheet1") );
	}
	
	@Test(dependsOnMethods = {"toEditGuideLine"})
	public void toDeleteGuideLine() throws Throwable {
		Reports.ReportCreateTestcase(" To Delete GuideLine-- End point https://oneself-api-test.optisolbusiness.com/api/guideline/delete-guideline?id");
		Response response=	GuideLinesEndPoints.toDeleteGuideLine(135);
		response.then().log().all();
		responsecodevalidation(response, 400);
		responsetimevalidation(response);
		responsebodyvalidation( response,ToReadJson.toDelete());
		responsestatuslinevalidation(response,ToReadExcel.readData(4,9,"Sheet1"));
		responseHeader(response, ToReadExcel.readData(4,10,"Sheet1"),ToReadExcel.readData(4,11,"Sheet1") );
	}
	@Test(dependsOnMethods = {"toEditGuideLine"})
	
	public void toEditUser() throws Throwable {
		Reports.ReportCreateTestcase(" Dynamic PayLoad");
		UserData.setupData();
		Response response=	GuideLinesEndPoints.add_User(UserData.userpayLoad);
		response.then().log().all();
		responsecodevalidation(response,400);
		

	}
	
	
	@AfterClass
	 public static void ReportCooldown() {
		 Reports.ReportCooldown();
	  }

}
