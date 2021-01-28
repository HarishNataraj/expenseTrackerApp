import java.util.ArrayList;

public class GetCategory implements UserOperations {

	@Override
	public Category operation(User user, String arg) throws CategoryException{
		ArrayList<Category> categories = user.getCategories();
		if(categories.size() != 0) {
			for(Category category : categories) {
				if(category.getCategoryName().equalsIgnoreCase(arg)) {
					return category;
				} else {
					throw new CategoryException("No such category found");
				}
			}
		}
		throw new CategoryException("Category list is empty");
	}



}
