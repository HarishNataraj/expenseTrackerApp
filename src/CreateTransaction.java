import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;

public class CreateTransaction {
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");	
	
	public void makeTransaction(String date, double amount, String tMode, Category category)
			throws ParseException, InputMismatchException, IllegalArgumentException {
		dateFormat.parse(date);
		if (amount < 1) {
			throw new InputMismatchException();
		}
		if (TransactionMode.valueOf(tMode) == null) {
			throw new IllegalArgumentException();
		} else {
			TransactionMode mode = TransactionMode.valueOf(tMode);
			tMode = mode.toString();
		}
		category.makeTransaction(date, amount, tMode);
	}
	
	
}
