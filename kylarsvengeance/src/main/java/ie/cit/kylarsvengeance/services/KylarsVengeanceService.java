package ie.cit.kylarsvengeance.services;

import java.util.ArrayList;
import java.util.List;

import ie.cit.kylarsvengeance.domain.GameCharacter;
import ie.cit.kylarsvengeance.domain.Inventory;
import ie.cit.kylarsvengeance.domain.Item;
import ie.cit.kylarsvengeance.domain.Player;
import ie.cit.kylarsvengeance.rowmappers.ItemRowMapper;

public interface KylarsVengeanceService {

	
	public void addItem(Item item);
	
	public Item getItem(int item_id);
	
	public List<Item> getAllItems();
	
	public List<Player> getAllPlayers();
	
	public Player getPlayer(int player_id);
	
	public List<GameCharacter> getAllGameCharacters(Player player);
	
	public GameCharacter getGameCharacter(int character_id);
	
	public List<Inventory> getAllInventory();
	
	public Player getPlayerByNames(String firstName, String lastName);
	
	public List<Item> getAllItemsFromPayersInv(int inventory_id);
	
	public List<Item> getTypeOfItem(int inventory_id,String type);
	
	public List<Item> getAllTypesOfItem(String type);
	
	public void chargePlayer(GameCharacter gc, Item item);
	
	public void sellItem(GameCharacter gc, Item item);
	
	public List<Item> getCharactersInventory(int character_Inv_id);
	public void upgradeItem(Item item);
}
