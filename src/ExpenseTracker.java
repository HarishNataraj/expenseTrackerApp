import java.text.ParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ExpenseTracker {

	private static MenuItems menu = new MenuItems();
	private static CreateTransaction createTransaction = new CreateTransaction();
	private static Operations userOperations = new Operations();

	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		UserOperations operations;
		ExpenseCalculator expenseCalculator;
		Category category;

		String categoryName;
		int option = 0;
		String transactionDate;
		double transactionAmount;
		String transactionMode;

		User userA = new User("UserA");

		menu.printMenu();

		boolean exit = false;
		boolean makeTransaction = false;
		while (!exit) {
			System.out.println("Enter option : ");
			option = scanner.nextInt();
			switch (option) {
			case 1:
				try {
					System.out.println("Enter category name : ");
					categoryName = scanner.next();
					operations = new AddCategory();
					category = userOperations.userOperations(operations, userA, categoryName);
					System.out.println("Category added");
				} catch (CategoryException e) {
					System.out.println(e.getMessage());
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
				try {
					System.out.println("Enter category name : ");
					categoryName = scanner.next();
					operations = new DeleteCategory();
					category = userOperations.userOperations(operations, userA, categoryName);
				} catch (CategoryException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				makeTransaction = true;
				try {
					System.out.println("Enter category name : ");
					categoryName = scanner.next();
					operations = new GetCategory();
					category = userOperations.userOperations(operations, userA, categoryName);
					while (makeTransaction) {
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
							createTransaction.makeTransaction(transactionDate, transactionAmount, transactionMode,
									category);
							makeTransaction = false;
						} catch (ParseException | IllegalArgumentException | InputMismatchException e) {
							System.out.println("Invalid input : " + e);
							System.out.println("Do you want to continue your transaction(Y/N) : ");
							String choice;
							choice = scanner.next();
							if (choice.equalsIgnoreCase("Y")) {
								makeTransaction = true;
							} else {
								makeTransaction = false;
							}
						}
					}
				} catch (CategoryException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 5:
				try {
					expenseCalculator = new TotalExpense();
					expenseCalculator.calculateExpense(userA);
				} catch (CategoryException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 6:
				try {
					expenseCalculator = new ExpenseByCategory();
					expenseCalculator.calculateExpense(userA);
				} catch (CategoryException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 7:
				menu.printMenu();
				break;
			case 8:
				exit = true;
				break;

			default:
				System.out.println("Invalid input");
				menu.printMenu();
				break;
			}
		}

	}
}
