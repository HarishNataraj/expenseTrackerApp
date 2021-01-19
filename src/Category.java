import java.util.ArrayList;

public class Category {
	private String categoryName;
	private ArrayList<Transaction> transactions;
	
	public Category(String categoryName) {
		this.categoryName = categoryName;
		transactions = new ArrayList<>();
	}

	public String getCategoryName() {
		return categoryName;
	}

	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}
	
	public void makeTransaction(String date, double amount, String mode) {
		transactions.add(new Transaction(date, amount, mode));
	}
}
