package ie.cit.kylarsvengeance.domain;

public class PlayerGameState {
	
	private int player_id;
	private int character_id;
	private int inventory_id;
	
	public PlayerGameState(){}
	
	public PlayerGameState(int player_id, int character_id, int inventory_id) {
		super();
		this.player_id = player_id;
		this.character_id = character_id;
		this.inventory_id = inventory_id;
	}

	public int getPlayer_id() {
		return player_id;
	}
	public void setPlayer_id(int player_id) {
		this.player_id = player_id;
	}
	public int getCharacter_id() {
		return character_id;
	}
	public void setCharacter_id(int character_id) {
		this.character_id = character_id;
	}
	public int getInventory_id() {
		return inventory_id;
	}
	public void setInventory_id(int inventory_id) {
		this.inventory_id = inventory_id;
	}
	@Override
	public String toString() {
		return "PlayerGameState [player_id=" + player_id + ", character_id=" + character_id + ", inventory_id="
				+ inventory_id + "]";
	}
}
