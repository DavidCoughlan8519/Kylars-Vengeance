package ie.cit.kylarsvengeance;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import ie.cit.kylarsvengeance.services.KylarsVengeanceServiceImpl;
import ie.cit.kylarsvengeance.daos.KylarsVengeanceDAO;
import ie.cit.kylarsvengeance.domain.GameCharacter;
import ie.cit.kylarsvengeance.domain.Inventory;
import ie.cit.kylarsvengeance.domain.Item;
import ie.cit.kylarsvengeance.domain.Player;

@SpringBootApplication
public class KylarsvengeanceApplication<GameCharacters> implements CommandLineRunner {

	public static int roundAllowance = 0;

	public static void main(String[] args) {
		SpringApplication.run(KylarsvengeanceApplication.class, args);
	}

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	KylarsVengeanceServiceImpl kvsi;
	@Autowired
	KylarsVengeanceDAO dao;

	Scanner userInput = new Scanner(System.in);

	@Override
	public void run(String... arg0) throws Exception {
		try {
			System.out.print(loadHeader(
					"C:\\Users\\David\\Desktop\\OOSPproject\\kylarsvengeance\\src\\main\\resources\\NameHeader.txt"));
			login();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void sellItem(int character_id) {
		if (roundAllowance < 1) {
			GameCharacter gc = kvsi.getGameCharacter(character_id);
			boolean hasSelected = false;
			int selection = 0;
			List<Item> inv = kvsi.getAllItemsFromPayersInv(gc.getCharacter_inventory_id());

			if (inv.size() > 0) {
				while (hasSelected != true) {
					System.out.println("Please select the number of the Item you wish to sell:");
					int counter = 1;
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
					for (Item item : inv) {
						System.out.println(counter + ". " + item.toString());
						System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
						counter++;
					}
					System.out.println("Type the number of the item you wish to purchase:");
					System.out.println("Press 0 to exit to main menu.");
					selection = userInput.nextInt();
					if (selection == 0) {
						return;
					}
					if (selection > 0 && selection < inv.size() + 1) {
						kvsi.sellItem(gc, inv.get(selection - 1));
						roundAllowance++;
						hasSelected = true;
					} else {
						System.out.println("Please pick one of the weapons available.");
					}
				}
			} else {
				System.out.println("You dont have any weapons to sell.");
			}
		} else {
			System.out.println("You have to play another round before you can Buy,Sell or Upgrade an item.");
		}
	}

	public void buyItem(int character_id) throws InterruptedException {

		if (roundAllowance < 1) {
			GameCharacter gc = kvsi.getGameCharacter(character_id);
			boolean canBuyUpClose = false;
			boolean canBuyDistance = false;
			boolean canBuyShield = false;
			boolean canBuyArmour = false;

			List<Inventory> invs;

			invs = kvsi.getAllInventory();
			List<Item> inv = kvsi.getAllItemsFromPayersInv(gc.getCharacter_inventory_id());
			for (Item item : inv) {
				System.out.println("Item: " + item.toString());
			}
			canBuyUpClose = checkPlayerHasTypeOfItem(gc, "UP_CLOSE");
			canBuyDistance = checkPlayerHasTypeOfItem(gc, "DISTANCE");
			canBuyShield = checkPlayerHasTypeOfItem(gc, "SHIELD");
			canBuyArmour = checkPlayerHasTypeOfItem(gc, "ARMOUR");

			List<Item> availableToBuy = new ArrayList<Item>();

			if (canBuyUpClose == true) {
				availableToBuy.addAll(kvsi.getAllTypesOfItem("UP_CLOSE"));
			}

			if (canBuyDistance == true) {
				availableToBuy.addAll(kvsi.getAllTypesOfItem("DISTANCE"));
			}

			if (canBuyShield == true) {
				availableToBuy.addAll(kvsi.getAllTypesOfItem("SHIELD"));
			}

			if (canBuyArmour == true) {
				availableToBuy.addAll(kvsi.getAllTypesOfItem("ARMOUR"));
			}

			if (canBuyUpClose == false && canBuyDistance == false && canBuyShield == false && canBuyArmour == false) {
				System.out.println("\n\nYour inventory is full you must sell a weapon before you can buy any more.");
				Thread.sleep(3000);
				mainMenu(character_id);
			}
			if (availableToBuy.size() > 0) {
				roundAllowance++;
				makePurchase(availableToBuy, gc.getCharacter_id());
			}
		} else {
			System.out.println("You have to play another round before you can Buy,Sell or Upgrade an item.");
		}
	}

	public void makePurchase(List<Item> availableToBuy, int character_id) throws InterruptedException {
		GameCharacter gc = kvsi.getGameCharacter(character_id);
		boolean hasSelected = false;
		int selection = 0;

		while (hasSelected != true) {
			System.out.println("The following Items are available for sale");
			System.out.println("\nS");
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			int count = 1;
			for (Item item : availableToBuy) {
				System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.println(count + ". " + item.toString());
				System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				count++;
			}
			System.out.println("Type the number of the item you wish to purchase:");
			System.out.println("Press 0 to exit to main menu.");
			selection = userInput.nextInt();
			if (selection == 0) {
				return;
			}
			if (selection > 0 && selection < availableToBuy.size() + 1) {
				if (gc.getCharacter_kubits() > availableToBuy.get(selection - 1).getPurchase_cost()) {
					kvsi.chargePlayer(gc, availableToBuy.get(selection - 1));
					hasSelected = true;
				} else {
					System.out.println("You do not have enough Kubits to purchase that item.");
				}
			}
		}
	}

	public void login() throws InterruptedException {
		boolean isAlphaFirst = false;
		boolean isAlphaLast = false;
		boolean hasSelectedCharacter = false;
		String firstName = "";
		String lastName = "";
		int selectedCharacter = -1;
		int character_id = 0;

		while (isAlphaFirst == false && isAlphaLast == false) {
			System.out.println("Please enter your first name:");
			firstName = userInput.nextLine();
			isAlphaFirst = isAlpha(firstName);
			System.out.println("Please enter your last name:");
			lastName = userInput.nextLine();
			isAlphaLast = isAlpha(lastName);
		}

		Player player = kvsi.getPlayerByNames(firstName, lastName);
		List<GameCharacter> playersCharacters = kvsi.getAllGameCharacters(player);

		if (playersCharacters.size() > 0) {
			System.out.println("Press the number to select your character: ");
			while (selectedCharacter != 0) {
				for (int i = 0; i < playersCharacters.size(); i++) {
					GameCharacter gc = playersCharacters.get(i);
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.print((i + 1) + ". ");
					printGameCharacters(gc.getCharacter_id());
				}

				selectedCharacter = userInput.nextInt();
				int menuSelection = -1;

				do {
					character_id = playersCharacters.get(selectedCharacter - 1).getCharacter_id();
					System.out.println("You have choosen the following character:");
					System.out.println(
							"Starting Kylars Vengenance as " + kvsi.getGameCharacter(character_id).getCharacter_name());
					menuSelection = mainMenu(character_id);
				} while (menuSelection != 0);

			}
		} else {
			System.out.println(
					"This account has no characters. Please log into an account where you have active characters");
		}
	}

	public static String loadHeader(String path) throws IOException {
		String header;
		BufferedReader br = new BufferedReader(new FileReader(path));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			header = sb.toString();
		} finally {
			br.close();
		}
		return header;
	}

	public int mainMenu(int character_id) throws InterruptedException {
		int userValidChoice = -1;
		boolean hasChoosen = false;
		GameCharacter gc = kvsi.getGameCharacter(character_id);
		printMainMenuText();

		while (!hasChoosen) {
			userValidChoice = userInput.nextInt();
			switch (userValidChoice) {
			case 0:
				hasChoosen = true;
				break;
			case 1:
				viewInventory(character_id);
				hasChoosen = true;
				break;
			case 2:
				shop(gc.getCharacter_id());
				hasChoosen = true;
				break;
			case 3:
				// Start next round
				System.out.println("Played next round.... Reseting round allowances.");
				roundAllowance = 0;
				Thread.sleep(3000);
				mainMenu(character_id);
				hasChoosen = true;
				break;
			case 4:
				System.out.println("Exiting Kylar's Vengenance...");
				Thread.sleep(3000);
				System.exit(1);
				hasChoosen = true;
				break;
			default:
				System.out.println("Please enter a valid menu option.");
			}
		}
		return userValidChoice;
	}

	private void upgrade(int character_id) {
		if (roundAllowance < 1) {
			boolean hasSelected = false;
			int selection = 0;
			int counter = 1;
			GameCharacter gc = kvsi.getGameCharacter(character_id);
			List<Item> chractersItems;

			while (hasSelected != true) {
				System.out.println("Select the weapon you wish to upgrade:");
				System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				chractersItems = kvsi.getAllItemsFromPayersInv(gc.getCharacter_inventory_id());

				if (chractersItems.size() > 0) {
					for (Item item : chractersItems) {
						System.out.println(counter + ". " + item.toString());
						System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
						counter++;
					}

					System.out.println("Press 0 to exit to main menu.");
					selection = userInput.nextInt();

					if (selection == 0) {
						return;
					}

					if (selection > 0 && selection < chractersItems.size() + 1) {
						if (gc.getCharacter_kubits() > chractersItems.get(selection - 1).getUpgrade_cost()) {
							kvsi.upgradeItem(chractersItems.get(selection - 1));
							roundAllowance++;
							hasSelected = true;
						} else {
							System.out.println("You do not have enough Kubits to upgrade that item.");
						}
					}

				} else {
					System.out.println("You have no items. Please purchase some.");
				}
			}
		} else {
			System.out.println("You have to play another round before you can Buy,Sell or Upgrade an item.");
		}
	}

	public void shop(int character_id) throws InterruptedException {
		int userValidChoice = 0;
		boolean hasChoosen = false;
		GameCharacter gc = kvsi.getGameCharacter(character_id);
		while (!hasChoosen) {
			printShopText();
			userValidChoice = userInput.nextInt();

			if (userValidChoice < 5 && userValidChoice > 0) {
				switch (userValidChoice) {
				case 1:
					buyItem(gc.getCharacter_id());
					break;
				case 2:
					sellItem(gc.getCharacter_id());
					break;
				case 3:
					upgrade(gc.getCharacter_id());
					break;
				case 4:
					mainMenu(character_id);
					return;
				default:
					System.out.println("Please enter a valid menu option.");
				}
			}
		}
	}

	public void viewInventory(int character_id) {
		GameCharacter gc = kvsi.getGameCharacter(character_id);
		List<Item> invItems = new ArrayList<Item>();

		System.out.println(gc.toString());

		System.out.println("HAS ITEMS:");
		invItems.addAll(kvsi.getCharactersInventory(gc.getCharacter_id()));
		for (Item item : invItems) {
			System.out.println(item.toString());
		}
		System.out.println("Press any key to continue...");
		userInput.nextLine();
	}

	public void printGameCharacters(int character_id) {
		GameCharacter gc = kvsi.getGameCharacter(character_id);
		System.out.println(gc.toString());
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		List<Item> invItems = new ArrayList<Item>();
		invItems.addAll(kvsi.getCharactersInventory(character_id));
		invItems.stream().forEach((Item item) -> {
			System.out.println(item.toString());
		});
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}

	public boolean checkPlayerHasTypeOfItem(GameCharacter gc, String type) {
		boolean canBuyType = true;

		List<Inventory> invs = new ArrayList<Inventory>();
		invs = kvsi.getAllInventory();
		List<Item> inv = kvsi.getCharactersInventory(gc.getCharacter_id());
		for (Item item : inv) {
			if (type.equalsIgnoreCase(item.getEquip_type())) {
				return false;
			}
		}

		return canBuyType;
	}

	public void printShopText() {
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("									SHOP										  ");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("  Press number for option then hit return:");
		System.out.println("  1. Buy Item");
		System.out.println("  2. Sell Item");
		System.out.println("  3. Upgrade Item");
		System.out.println("  4. Back To Main Menu");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}

	public void printMainMenuText() {
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("  Press number for option then hit return:");
		System.out.println("  1. View Inventory");
		System.out.println("  2. Enter Equipment Shop");
		System.out.println("  3. Start next Round...");
		System.out.println("  4. Exit Kylar's Vengenance...");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}

	public boolean isAlpha(String name) {
		char[] chars = name.toCharArray();

		for (char c : chars) {
			if (!Character.isLetter(c)) {
				return false;
			}
		}
		return true;
	}
}
