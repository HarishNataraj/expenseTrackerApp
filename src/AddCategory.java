
public class AddCategory implements UserOperations {
	
	@Override
	public Category operation(User user, String arg) {
		Category newCategory = null;
		int size = user.getCategories().size();
		if(size == 0) {
			newCategory = new Category(arg);
			user.getCategories().add(newCategory);
		} else {
			for(Category category : user.getCategories()) {
				if(category.getCategoryName().equalsIgnoreCase(arg)) {
					return null;
				}
			}
			newCategory = new Category(arg);
			user.getCategories().add(newCategory);
		}
		return newCategory;
	}

}
