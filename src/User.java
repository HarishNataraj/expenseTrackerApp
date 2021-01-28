import java.util.ArrayList;

public class User {
	private String userName;
	private ArrayList<Category> categories;

	public User(String userName) {
		this.userName = userName;
		categories = new ArrayList<>();
	}

	public String getUserName() {
		return userName;
	}

	public ArrayList<Category> getCategories() {
		return categories;
	}

}
