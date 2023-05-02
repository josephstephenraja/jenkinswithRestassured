package DyanamicData;

import java.text.SimpleDateFormat;

import org.payload.Users;

import com.github.javafaker.Faker;

public class UserData {
	static Faker faker;
	public static Users  userpayLoad;
	public static void setupData() {
		
		faker = new Faker();
		userpayLoad = new Users();
		userpayLoad.setFirst_name(faker.name().firstName());
		userpayLoad.setMiddle_name(null);
		userpayLoad.setLast_name(faker.name().lastName());
		userpayLoad.setPhone(faker.phoneNumber().subscriberNumber(10));
		userpayLoad.setPhone_code_id(101);
		userpayLoad.setCountry(faker.address().country());
		userpayLoad.setCity(faker.address().cityName());
		userpayLoad.setState(faker.address().state());
		userpayLoad.setEmail(faker.internet().safeEmailAddress());
		userpayLoad.setAddress_one(faker.address().firstName());
		userpayLoad.setAddress_two(faker.address().lastName());
		userpayLoad.setZipcode( faker.lorem().characters(6));
		userpayLoad.setGender(null);
		userpayLoad.setDOB(faker.date().birthday().toString());
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String dob = sdf.format(faker.date().birthday());
		userpayLoad.setDOB(dob);
		
		
		
		
		
		

	}

}
