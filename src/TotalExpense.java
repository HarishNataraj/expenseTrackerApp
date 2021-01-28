import java.util.ArrayList;

public class TotalExpense implements ExpenseCalculator {
	private double totalExpense;
	
	@Override
	public void calculateExpense(User user) throws CategoryException{
		ArrayList<Category> categoryList= user.getCategories();
		if(categoryList.size() !=0) {
			for(Category category : categoryList) {
				ArrayList<Transaction> transactionList = category.getTransactions();
				for(Transaction transaction : transactionList) {
					totalExpense = totalExpense + transaction.getTransactionAmount();
				}
			}
			System.out.println("Total expense : "+totalExpense);
		} else {
			throw new CategoryException("Category list is empty. Cannot calculate expense");
		}
	}

}
