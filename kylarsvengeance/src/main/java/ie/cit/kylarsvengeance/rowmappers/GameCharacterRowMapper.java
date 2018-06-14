package ie.cit.kylarsvengeance.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ie.cit.kylarsvengeance.domain.GameCharacter;
import ie.cit.kylarsvengeance.domain.Inventory;

public class GameCharacterRowMapper implements RowMapper <GameCharacter> {
	
	@Override
	public GameCharacter mapRow(ResultSet rs, int index) throws SQLException {
		GameCharacter gc = new GameCharacter();
		gc.setCharacter_id(rs.getInt("character_id"));
		gc.setCharacter_class(rs.getString("character_class"));
		gc.setCharacter_name(rs.getString("character_name"));
		gc.setCharacter_gender(rs.getString("character_gender"));
		gc.setCharacter_kubits(rs.getInt("character_kubits"));
		gc.setCharacter_inventory_id(rs.getInt("character_inventory_id"));
		return gc;
	}
}
