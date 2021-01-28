
public class AddCategory implements UserOperations {
	
	@Override
	public Category operation(User user, String arg) throws CategoryException{
		Category newCategory = null;
		int size = user.getCategories().size();
		if(size == 0) {
			newCategory = new Category(arg);
			user.getCategories().add(newCategory);
		} else {
			for(Category category : user.getCategories()) {
				if(category.getCategoryName().equalsIgnoreCase(arg)) {
					throw new CategoryException("Category already exists");
				}
			}
			newCategory = new Category(arg);
			user.getCategories().add(newCategory);
		}
		return newCategory;
	}

}
