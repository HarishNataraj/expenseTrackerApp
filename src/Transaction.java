
public class Transaction {
	private String transactionDate;
	private double transactionAmount;
	private String transactionMode;
	
	public Transaction(String transactionDate, double transactionAmount, String transactionMode) {
		this.transactionDate = transactionDate;
		this.transactionAmount = transactionAmount;
		this.transactionMode = transactionMode;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public double getTransactionAmount() {
		return transactionAmount;
	}

	public String getTransactionMode() {
		return transactionMode;
	}
	
	
	
	
}
