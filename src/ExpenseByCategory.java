import java.util.ArrayList;

public class ExpenseByCategory implements ExpenseCalculator {
	
	@Override
	public void calculateExpense(User user) throws CategoryException{
		double totalExpense = 0;
		ArrayList<Category> categoryList= user.getCategories();
		if(categoryList.size() != 0) {
			for(Category category : categoryList) {
				ArrayList<Transaction> transactionList = category.getTransactions();
				for(Transaction transaction : transactionList) {
					totalExpense = totalExpense + transaction.getTransactionAmount();
				}
				System.out.println("Expense for "+category.getCategoryName()+" : "+totalExpense);
				totalExpense = 0;
			}
		} else {
			throw new CategoryException("Category list is empty. Cannot calculate expense");
		}
	}

}
