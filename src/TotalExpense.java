import java.util.ArrayList;

public class TotalExpense implements ExpenseCalculator {
	private double totalExpense;

	@Override
	public void calculateExpense(User user) {
		ArrayList<Category> categoryList= user.getCategories();
		for(Category category : categoryList) {
			ArrayList<Transaction> transactionList = category.getTransactions();
			for(Transaction transaction : transactionList) {
				totalExpense = totalExpense + transaction.getTransactionAmount();
			}
		}
		System.out.println("Total expense : "+totalExpense);
	}

}
