import java.util.ArrayList;

public class ExpenseByCategory implements ExpenseCalculator {
	
	@Override
	public void calculateExpense(User user) {
		double totalExpense = 0;
		ArrayList<Category> categoryList= user.getCategories();
		for(Category category : categoryList) {
			ArrayList<Transaction> transactionList = category.getTransactions();
			for(Transaction transaction : transactionList) {
				totalExpense = totalExpense + transaction.getTransactionAmount();
			}
			System.out.println("Expense for "+category.getCategoryName()+" : "+totalExpense);
			totalExpense = 0;
		}
	}

}
