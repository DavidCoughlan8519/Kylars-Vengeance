package ie.cit.kylarsvengeance.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.cit.kylarsvengeance.daos.KylarsVengeanceDAO;
import ie.cit.kylarsvengeance.domain.GameCharacter;
import ie.cit.kylarsvengeance.domain.Inventory;
import ie.cit.kylarsvengeance.domain.Item;
import ie.cit.kylarsvengeance.domain.Player;


@Service
public class KylarsVengeanceServiceImpl implements KylarsVengeanceService{

	KylarsVengeanceDAO dao;
	@Autowired
	public KylarsVengeanceServiceImpl(KylarsVengeanceDAO dao) {
		super();
		this.dao = dao;
	}
	@Override
	public void addItem(Item item) {
		dao.addItem(item);	
	}
	@Override
	public Item getItem(int item_id) {
		 return dao.getItem(item_id);
	}
	@Override
	public List<Item> getAllItems() {
		 return dao.getAllItems();
	}
	@Override
	public List<Player> getAllPlayers() {
		return dao.getAllPlayers();
	}
	@Override
	public Player getPlayer(int player_id) {
		return dao.getPlayer(player_id);
	}
	@Override
	public List<GameCharacter> getAllGameCharacters(Player player) {
		return dao.getGameCharacters(player);
	}
	@Override
	public GameCharacter getGameCharacter(int character_id) {
		return dao.getGameCharacter(character_id);
	}
	@Override
	public List<Inventory> getAllInventory() {
		return dao.getAllInventory();
	}
	@Override
	public Player getPlayerByNames(String firstName, String lastName) {
		return dao.getPlayerByUserName(firstName, lastName);
	}
	@Override
	public List<Item> getAllItemsFromPayersInv(int inventory_id) {
		return dao.getAllItemsFromPayersInv(inventory_id);
	}
	@Override
	public List<Item> getTypeOfItem(int inventory_id, String type) {
		return dao.getTypeOfItem(inventory_id, type);
	}
	@Override
	public List<Item> getAllTypesOfItem(String type) {
		return dao.getAllTypesOfItem(type);
	}
	@Override
	public void chargePlayer(GameCharacter gc, Item item) {
		dao.chargePlayer(gc, item);
	}
	@Override
	public void sellItem(GameCharacter gc, Item item) {
		dao.sellItem(gc, item);
	}
	@Override
	public List<Item> getCharactersInventory(int character_Inv_id) {
		return dao.getCharactersInventory(character_Inv_id);
	}
	@Override
	public void upgradeItem(Item item) {
		 dao.upgradeItem(item);
	}

}
