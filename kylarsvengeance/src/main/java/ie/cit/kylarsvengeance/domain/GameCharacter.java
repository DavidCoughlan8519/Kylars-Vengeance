package ie.cit.kylarsvengeance.domain;

public class GameCharacter {

	private int character_id;
	private String character_class;
	private String character_name;
	private String character_gender;
	private int character_kubits;
	private int character_inventory_id;

	public GameCharacter() {
	}

	public GameCharacter(int character_id, String character_class, String character_name, String character_gender,
			int chartacter_kubits, int character_inventory_id) {
		super();
		this.character_id = character_id;
		this.character_class = character_class;
		this.character_name = character_name;
		this.character_gender = character_gender;
		this.character_kubits = chartacter_kubits;
		this.character_inventory_id = character_inventory_id;
	}

	public int getCharacter_inventory_id() {
		return character_inventory_id;
	}

	public void setCharacter_inventory_id(int character_inventory_id) {
		this.character_inventory_id = character_inventory_id;
	}

	public int getCharacter_id() {
		return character_id;
	}

	public void setCharacter_id(int character_id) {
		this.character_id = character_id;
	}

	public String getCharacter_class() {
		return character_class;
	}

	public void setCharacter_class(String character_class) {
		this.character_class = character_class;
	}

	public String getCharacter_name() {
		return character_name;
	}

	public void setCharacter_name(String character_name) {
		this.character_name = character_name;
	}

	public String getCharacter_gender() {
		return character_gender;
	}

	public void setCharacter_gender(String character_gender) {
		this.character_gender = character_gender;
	}

	public int getCharacter_kubits() {
		return character_kubits;
	}

	public void setCharacter_kubits(int chartacter_kubits) {
		this.character_kubits = chartacter_kubits;
	}

	@Override
	public String toString() {
		return "GameCharacter [character_id=" + character_id + ", character_class=" + character_class
				+ ", character_name=" + character_name + ", character_gender=" + character_gender
				+ ", character_kubits=" + character_kubits + ", character_inventory_id=" + character_inventory_id
				+ "]";
	}

}
