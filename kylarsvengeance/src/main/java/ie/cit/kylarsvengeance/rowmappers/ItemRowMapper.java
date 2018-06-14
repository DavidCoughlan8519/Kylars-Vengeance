package ie.cit.kylarsvengeance.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ie.cit.kylarsvengeance.domain.Item;

public class ItemRowMapper implements RowMapper<Item> {
	
	@Override
	public Item mapRow(ResultSet rs, int index) throws SQLException {
		Item item = new Item();
		item.setInventory_id(rs.getInt("inventory_id"));
		item.setItem_id(rs.getInt("item_id"));
		item.setItem(rs.getString("item"));
		item.setEquip_type(rs.getString("equip_type"));
		item.setProtection(rs.getInt("protection"));
		item.setDamage(rs.getInt("damage"));
		item.setPurchase_cost(rs.getInt("purchase_cost"));
		item.setSell_value(rs.getInt("sell_value"));
		item.setUpgrade_cost(rs.getInt("upgrade_cost"));
		return item;
	}
}
