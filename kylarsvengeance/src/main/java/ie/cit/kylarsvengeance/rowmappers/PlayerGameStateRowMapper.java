package ie.cit.kylarsvengeance.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ie.cit.kylarsvengeance.domain.PlayerGameState;

public class PlayerGameStateRowMapper implements RowMapper <PlayerGameState>{

	@Override
	public PlayerGameState mapRow(ResultSet rs, int rowNum) throws SQLException {
		//PlayerGameState pgs = new PlayerGameState();
		//pgs.setPlayer_id(rs.getInt("player_id"));
		//pgs.setCharacter_id(rs.getInt("character_id"));
		//pgs.setInventory_id(rs.getInt("inventory"));
		return new PlayerGameState(rs.getInt("player_id"),rs.getInt("character_id"),rs.getInt("inventory_id"));
	}
}
