
public class Operations {

	public Category userOperations(UserOperations operationObject, User user,String categoryName) throws CategoryException{
		try {
			return operationObject.operation(user, categoryName);
		} catch (CategoryException e) {
			throw(e);
		}
	}
	
}
