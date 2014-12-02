import java.util.List;

import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;

public class ScheduleMeService {
	private static final String API_URL = "https://scheduleme.herokuapp.com/";

	static class User {
		String created_at;
		String did;
		String email;
		String fname;
		String id;
		String lname;
		String password;
		String phone;
		String updated_at;
	}

	interface ScheduleMe {
		@GET("/add_user.json")
		User addUser(@Query("user[did]") String did,
				@Query("user[fname]") String firstname,
				@Query("user[lname]") String lastname,
				@Query("user[email]") String email,
				@Query("user[phone]") String phone,
				@Query("user[password]") String password);

		@GET("/login.json")
		List<User> loginUser(@Query("did") String did,
				@Query("password") String password);

		@GET("/find_user.json")
		List<User> findUserById(@Query("did") String did);

		@GET("/find_user.json")
		List<User> findUserByEmail(@Query("email") String email);
	}

	public static void main(String... args) {

		// Initialize api
		RestAdapter restAdapter = new RestAdapter.Builder()
				.setEndpoint(API_URL).build();
		ScheduleMe api = restAdapter.create(ScheduleMe.class);

		// Example of adding a user
		User user = api.addUser("raja", "Raja", "Ayyagari", "raja@umd.edu",
				"1234567890", "fakepw");

		// Example of logging in a user
		List<User> users = api.loginUser("jlotkows", "test");
		System.out.println(users);

		// Example of finding a user by email address
		List<User> foundUserByEmail = api
				.findUserByEmail("jlotkows@terpmail.umd.edu");
		System.out.println(foundUserByEmail.get(0).fname
				+ ": found using email address");

		// Example of finding a user by id
		List<User> foundUserById = api.findUserById("jlotkows");
		System.out.println(foundUserById.get(0).fname
				+ ": found user using first name");

	}
}