import java.util.ArrayList;

public class GetCategory implements UserOperations {

	@Override
	public Category operation(User user, String arg) {
		ArrayList<Category> categories = user.getCategories();
		for(Category category : categories) {
			if(category.getCategoryName().equalsIgnoreCase(arg)) {
				return category;
			}
		}
		return null;
	}

}
