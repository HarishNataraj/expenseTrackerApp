
public class DeleteCategory implements UserOperations {
	
	private Category category;
	private UserOperations operations;
	
	public DeleteCategory() {
		this.operations = new GetCategory();
	}

	@Override
	public Category operation(User user, String arg) {
		category = operations.operation(user, arg);
		if(category == null) {
			return null;
		} else {
			user.getCategories().remove(category);
			return category;
		}
	}

}
