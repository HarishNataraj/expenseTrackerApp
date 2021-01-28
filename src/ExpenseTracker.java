import java.text.ParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class ExpenseTracker {
	
	private static MenuItems menu = new MenuItems();
	private static CreateTransaction createTransaction = new CreateTransaction();
	private static CalculateExpense calculateExpense = new CalculateExpense();	
	
	static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		UserOperations operations;
		ExpenseCalculator expenseCalculator;
		Category category;

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
				makeTransaction = true;
				operations = new GetCategory();
				category = userOperations(operations, userA);
				if (category == null) {
					System.out.println("No such category found");
				} else {
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
							createTransaction.makeTransaction(transactionDate, transactionAmount, transactionMode, category);
							makeTransaction = false;
						} catch (ParseException | IllegalArgumentException | InputMismatchException e) {
							System.out.println("Invalid input : "+e);
							System.out.println("Do you want to continue your transaction(Y/N) : ");
							String choice;
							choice = scanner.next();
							if(choice.equalsIgnoreCase("Y")) {
								makeTransaction = true;
							} else {
								makeTransaction = false;
							}
						}
					}
				}
				break;
			case 5:
				expenseCalculator = new TotalExpense();
				calculateExpense.calculate(expenseCalculator, userA);
				break;
			case 6:
				expenseCalculator = new ExpenseByCategory();
				calculateExpense.calculate(expenseCalculator, userA);
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

	private static Category userOperations(UserOperations operationObject, User user) {
		String categoryName;
		System.out.println("Enter category name : ");
		categoryName = scanner.next();
		return operationObject.operation(user, categoryName);
	}


}
