package ie.cit.kylarsvengeance.interfaces;

import java.util.List;

import ie.cit.kylarsvengeance.domain.GameCharacter;
import ie.cit.kylarsvengeance.domain.Inventory;
import ie.cit.kylarsvengeance.domain.Item;
import ie.cit.kylarsvengeance.domain.Player;

public interface GameData {

	//------------------------------------------------------------------
	//Get a list of players
	public List<Player> getAllPlayers();
	//Get one player
	public Player getPlayer(int player_id);
	
	public void chargePlayer(GameCharacter gc,Item item);
	
	//------------------------------------------------------------------
	//Get one Game Character
	public GameCharacter getGameCharacter(int character_id);
	//Get all the characters
	public List<GameCharacter> getGameCharacters(Player player);
	
	//-------------------------------------------------------------------
	//Get one item
	public Item getItem(int item_id);
	//Upgrade an item
	public void upgradeItem(Item item);
	//Sell an item
	public void sellItem(GameCharacter gc, Item item);
	//Get all items
	public List<Item> getAllItems();
	//Add an item
	public void addItem(Item item);
	
	public List<Item> getAllItemsFromPayersInv(int inventory_id);
	
	public List<Item> getTypeOfItem(int inventory_id,String type);
	
	public List<Item> getAllTypesOfItem(String type);
	//----------------------------------------------------------------
	//get one item from inventory
	public Inventory getInventoryItem(int inventory_id);
	//Get everything from the inventory
	public List<Inventory> getAllInventory();
	
	public List<Item> getCharactersInventory(int character_Inv_id);
	
	public Player getPlayerByUserName(String firstName, String lastName);
	
	public Inventory checkCloseUpItem(int inventory_id);
	public Inventory checkDistanceItem(int inventory_id);
	public Inventory checkShieldItem(int inventory_id);
	public Inventory checkArmourItem(int inventory_id);
}
