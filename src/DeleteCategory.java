
public class DeleteCategory implements UserOperations {
	
	private Category category;
	private UserOperations operations;
	
	public DeleteCategory() {
		this.operations = new GetCategory();
	}

	@Override
	public Category operation(User user, String arg) throws CategoryException{
		
		try {
			category = operations.operation(user, arg);
			user.getCategories().remove(category);
			return category;
		} catch (CategoryException e) {
			throw(e);
		}
	}

}
