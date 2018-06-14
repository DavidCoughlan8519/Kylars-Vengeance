package ie.cit.kylarsvengeance.domain;

import java.util.ArrayList;

public class Player {

	private int player_id;
	private String firstName;
	private String lastName;
	private ArrayList<GameCharacter> playersGameCharacters = new ArrayList<GameCharacter>();
	
	public Player(){}
	
	public Player(int player_id, String firstName, String lastName) {
		super();
		this.player_id = player_id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public ArrayList<GameCharacter> getPlayersGameCharacters() {
		return playersGameCharacters;
	}

	public void setPlayersGameCharacters(ArrayList<GameCharacter> playersGameCharacters) {
		this.playersGameCharacters = playersGameCharacters;
	}

	public int getPlayer_id() {
		return player_id;
	}
	public void setPlayer_id(int player_id) {
		this.player_id = player_id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	public String toString() {
		return "Player [player_id=" + player_id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	
	
}