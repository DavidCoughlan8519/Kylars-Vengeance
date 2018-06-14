package ie.cit.kylarsvengeance.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ie.cit.kylarsvengeance.domain.Player;

public class PlayerRowMapper  implements RowMapper<Player>{

	@Override
	public Player mapRow(ResultSet rs, int index) throws SQLException {
		Player player = new Player();
		player.setPlayer_id(rs.getInt("player_id"));
		player.setFirstName(rs.getString("firstName"));
		player.setLastName(rs.getString("lastName"));
		return player;
	}

}
