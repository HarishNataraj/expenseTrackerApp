import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class ExpenseTracker {
	static Scanner scanner = new Scanner(System.in);
	static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
	
	public enum TransactionMode {
		CASH, UPI, CARD
	}

	public static void main(String[] args) {
		UserOperations operations;
		ExpenseCalculator expenseCalculator;
		Category category;

		int option = 0;
		String transactionDate;
		double transactionAmount;
		String transactionMode;

		User userA = new User("UserA");

		printMenu();

		boolean exit = false;
		while (!exit) {
			System.out.println("Enter option : ");
			option = scanner.nextInt();
			switch (option) {
			case 1:
				operations = new AddCategory();
				category = userOperations(operations, userA);
				if (category == null) {
					System.out.println("Category already exists");
				}
				break;
			case 2:
				ArrayList<Category> categoryList = userA.getCategories();
				for (int i = 0; i < categoryList.size(); i++) {
					category = categoryList.get(i);
					System.out.println((i + 1) + "." + category.getCategoryName());
				}
				break;
			case 3:
				operations = new DeleteCategory();
				category = userOperations(operations, userA);
				if (category == null) {
					System.out.println("No such category found");
				} else {
					System.out.println("Category deleted");
				}
				break;
			case 4:
				operations = new GetCategory();
				category = userOperations(operations, userA);
				if (category == null) {
					System.out.println("No such category found");
				} else {
					while (true) {
						try {
							System.out.println("Enter transaction date (dd/mm/yyyy): ");
							transactionDate = scanner.next();
							System.out.println("Enter transaction amount : ");
							transactionAmount = scanner.nextDouble();
							System.out.println("Enter mode of payment :");
							System.out.println("Available modes:");
							for (TransactionMode mode : TransactionMode.values()) {
								System.out.println(mode);
							}
							transactionMode = scanner.next().toUpperCase();
							makeTransaction(transactionDate, transactionAmount, transactionMode, category);
							break;
						} catch (ParseException e) {
							System.out.println("Enter valid date in format(dd/mm/yyyy)");
						} catch (IllegalArgumentException e) {
							System.out.println("Enter valid transaction mode");
						} catch (InputMismatchException e) {
							System.out.println("Amount entered is invalid");
						}
					}
				}
				break;
			case 5:
				expenseCalculator = new TotalExpense();
				calculateExpense(expenseCalculator, userA);
				break;
			case 6:
				expenseCalculator = new ExpenseByCategory();
				calculateExpense(expenseCalculator, userA);
				break;
			case 7:
				printMenu();
				break;
			case 8:
				exit = true;
				break;

			default:
				System.out.println("Invalid input");
				printMenu();
				break;
			}
		}

	}

	private static void printMenu() {
		System.out.println("1.Add catgeory \n" + "2.View all categories \n" + "3.Delete category \n"
				+ "4.Add transaction for category \n" + "5.Calculate total expense \n"
				+ "6.Calculate exspense for each category \n" + "7.Print Menu \n" + "8.Exit");
	}

	private static Category userOperations(UserOperations operationObject, User user) {
		String categoryName;
		System.out.println("Enter category name : ");
		categoryName = scanner.next();
		return operationObject.operation(user, categoryName);
	}

	private static void calculateExpense(ExpenseCalculator expenseCalculator, User user) {
		expenseCalculator.calculateExpense(user);
	}

	private static void makeTransaction(String date, double amount, String tMode, Category category)
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
