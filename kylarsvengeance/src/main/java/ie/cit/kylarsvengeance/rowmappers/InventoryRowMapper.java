package ie.cit.kylarsvengeance.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ie.cit.kylarsvengeance.domain.Inventory;

public class InventoryRowMapper implements RowMapper <Inventory> {
	
	@Override
	public Inventory mapRow(ResultSet rs, int index) throws SQLException {
		Inventory inv = new Inventory();
		inv.setInventory_id(rs.getInt("inventory_id"));
		inv.setUp_close(rs.getInt("up_close"));
		inv.setDistance(rs.getInt("distance"));
		inv.setShield(rs.getInt("shield"));
		inv.setArmour(rs.getInt("armour"));
		return inv;
	}
}
