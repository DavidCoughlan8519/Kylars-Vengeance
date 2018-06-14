package ie.cit.kylarsvengeance.daos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ie.cit.kylarsvengeance.domain.GameCharacter;
import ie.cit.kylarsvengeance.domain.Inventory;
import ie.cit.kylarsvengeance.domain.Item;
import ie.cit.kylarsvengeance.domain.Player;
import ie.cit.kylarsvengeance.domain.PlayerGameState;
import ie.cit.kylarsvengeance.interfaces.GameData;
import ie.cit.kylarsvengeance.rowmappers.GameCharacterRowMapper;
import ie.cit.kylarsvengeance.rowmappers.InventoryRowMapper;
import ie.cit.kylarsvengeance.rowmappers.ItemRowMapper;
import ie.cit.kylarsvengeance.rowmappers.PlayerGameStateRowMapper;
import ie.cit.kylarsvengeance.rowmappers.PlayerRowMapper;

@Repository
public class KylarsVengeanceDAO  implements GameData{

	private JdbcTemplate jdbcTemplate;
	String sql;
	
	@Autowired
	public KylarsVengeanceDAO(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	//-----------------------------------------------------------------------------
	//Players
	//-----------------------------------------------------------------------------
	@Override
	public List<Player> getAllPlayers() {
		sql = "SELECT* FROM PLAYERS";
		return jdbcTemplate.query(sql, new PlayerRowMapper());
	}


	@Override
	public Player getPlayer(int player_id) {
		sql = "SELECT * FROM PLAYERS WHERE PLAYER_ID = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{player_id}, new PlayerRowMapper());
	}
	
	@Override
	public Player getPlayerByUserName(String firstName, String lastName) {
		sql = "SELECT * FROM PLAYERS WHERE firstName = ? AND lastName = ?" ;
		return jdbcTemplate.queryForObject(sql, new Object[]{firstName,lastName}, new PlayerRowMapper());
	}

	//-----------------------------------------------------------------------------
	//Game characters
	//-----------------------------------------------------------------------------
	@Override
	public GameCharacter getGameCharacter(int character_id) {
		sql = "SELECT * FROM GameCharacter WHERE character_id = ?";
		 return jdbcTemplate.queryForObject(sql, new Object[] { character_id }, new GameCharacterRowMapper());
	}

	@Override
	public List<GameCharacter> getGameCharacters(Player player) {
		ArrayList<GameCharacter> gameCharacters = new ArrayList<GameCharacter>();
		sql = "SELECT * FROM PLAYERS_GAME_STATE WHERE player_id = ?";
		//get all the characters
		for (PlayerGameState pgs:   jdbcTemplate.query(sql, new Object[] {player.getPlayer_id()}, new PlayerGameStateRowMapper()))
		{ //get the ones im looking for player
			sql = "SELECT * FROM GameCharacter Where CHARACTER_ID = ?";
			GameCharacter gc = jdbcTemplate.queryForObject(sql, new Object[]{pgs.getCharacter_id()},new GameCharacterRowMapper());
			gc.setCharacter_inventory_id(pgs.getInventory_id());
			gameCharacters.add(gc);
		}
		return gameCharacters;
	}
//----------------------------------------------------------------------------
//Items
//--------------------------------------------------------------
	@Override
	public Item getItem(int item_id) {
		sql = "SELECT * FROM items WHERE ITEM_ID = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{item_id}, new ItemRowMapper());
	}
	
	@Override
	public void upgradeItem(Item item) {
		sql = "UPDATE Items SET PROTECTION = ?, DAMAGE = ?,SELL_VALUE = ? ,UPGRADE_COST = ? WHERE ITEM_ID = ?";
		int newProtection = item.getProtection() + 10;
		int newDamage = item.getDamage() + 10;
		int newSellValue = item.getSell_value() + 20;
		int newUpgradeCost = item.getUpgrade_cost() + 10;
		jdbcTemplate.update(sql,newProtection, newDamage, newSellValue,newUpgradeCost,item.getItem_id());
	}
	
	@Override
	public void sellItem(GameCharacter gc, Item item) {

		int priceToAdd = gc.getCharacter_kubits() + item.getSell_value();
		int characterId = gc.getCharacter_id();
		
		sql = "UPDATE GameCharacter SET CHARACTER_KUBITS = ? WHERE CHARACTER_ID = ?";
		jdbcTemplate.update(sql,new Object[]{priceToAdd,characterId});

		sql = "UPDATE items set inventory_id = ? WHERE item_id = ?";
		jdbcTemplate.update(sql, new Object[]{0,item.getItem_id()});
	}
	@Override
	public List<Item> getAllItemsFromPayersInv(int inventory_id) {
		sql = "SELECT * FROM items WHERE inventory_id = ?";
		return jdbcTemplate.query(sql,new Object[]{inventory_id}, new ItemRowMapper());
	}
	
	@Override
	public List<Item> getAllItems() {
		sql = "SELECT * FROM items";
		return jdbcTemplate.query(sql, new ItemRowMapper());
	}

	@Override
	public void addItem(Item item) {
		sql = "INSERT INTO items (item_id,item,equip_type_id,protection,damage,purchase_cost,sell_value,upgrade_cost)";
		jdbcTemplate.update(sql, item.getItem_id(),item.getItem(), item.getEquip_type(),item.getProtection(),item.getDamage(),item.getPurchase_cost(),item.getSell_value(),item.getUpgrade_cost());
	}

//-------------------------------------------------------------------------
//Inventory
//-------------------------------------------------------------------------
	@Override
	public Inventory getInventoryItem(int inventory_id) {
		sql = "SELECT * FROM inventory WHERE inventory_id =?";
		return jdbcTemplate.queryForObject(sql, new Object[] { inventory_id }, new InventoryRowMapper());
	}

	
	@Override
	public List<Inventory> getAllInventory() {
		sql = "SELECT * FROM inventory";
		return  jdbcTemplate.query(sql, new InventoryRowMapper());
	}

	@Override
	public List<Item> getTypeOfItem(int inventory_id, String type) {
		sql = "SELECT * FROM items WHERE inventory_id = ? AND EQUIP_TYPE = ?";
		return jdbcTemplate.query(sql,new Object[]{inventory_id,type}, new ItemRowMapper());
	}

	@Override
	public Inventory checkCloseUpItem(int inventory_id) {
		sql = "SELECT UP_CLOSE FROM inventory WHERE inventory_id =?";
		return jdbcTemplate.queryForObject(sql, new Object[] { inventory_id }, new InventoryRowMapper());
	}

	@Override
	public Inventory checkDistanceItem(int inventory_id) {
		sql = "SELECT DISTANCE FROM inventory WHERE inventory_id =?";
		return jdbcTemplate.queryForObject(sql, new Object[] { inventory_id }, new InventoryRowMapper());
	}

	@Override
	public Inventory checkShieldItem(int inventory_id) {
		sql = "SELECT SHIELD FROM inventory WHERE inventory_id =?";
		return jdbcTemplate.queryForObject(sql, new Object[] { inventory_id }, new InventoryRowMapper());
	}

	@Override
	public Inventory checkArmourItem(int inventory_id) {
		sql = "SELECT ARMOUR FROM inventory WHERE inventory_id =?";
		return jdbcTemplate.queryForObject(sql, new Object[] { inventory_id }, new InventoryRowMapper());
	}

	@Override
	public List<Item> getAllTypesOfItem(String type) {
		sql = "SELECT * FROM items WHERE EQUIP_TYPE = ?";
		return jdbcTemplate.query(sql,new Object[]{type}, new ItemRowMapper());
	}

	@Override 
	public void chargePlayer(GameCharacter gc, Item item) {
		int priceToDeduct = 0;
		priceToDeduct = gc.getCharacter_kubits() - item.getPurchase_cost();
		sql = "UPDATE GameCharacter SET CHARACTER_KUBITS = ? WHERE CHARACTER_ID = ?";
		int characterId = gc.getCharacter_id();
		jdbcTemplate.update(sql,new Object[]{priceToDeduct,characterId});
		
		sql = "UPDATE inventory set "+item.getEquip_type()+" = ? WHERE inventory_id = ?";
		
		jdbcTemplate.update(sql, new Object[]{item.getItem_id(),gc.getCharacter_inventory_id()});
		sql = "UPDATE items set inventory_id = ? WHERE item_id = ?";
		jdbcTemplate.update(sql, new Object[]{gc.getCharacter_inventory_id(),item.getItem_id()});
	}

	@Override
	public List<Item> getCharactersInventory(int character_Inv_id) {
		sql = "SELECT * FROM items WHERE inventory_id = ?";
		return jdbcTemplate.query(sql,new Object[]{character_Inv_id}, new ItemRowMapper());
	}	
}
