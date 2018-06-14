package ie.cit.kylarsvengeance.domain;

public class Inventory {
	private int inventory_id;
	private int up_close;
	private int distance;
	private int shield;
	private int armour;
	
	public Inventory(){}
	
	public Inventory(int inventory_id, int up_close, int distance, int shield, int armour) {
		super();
		this.inventory_id = inventory_id;
		this.up_close = up_close;
		this.distance = distance;
		this.shield = shield;
		this.armour = armour;
	}
	public int getInventory_id() {
		return inventory_id;
	}
	public void setInventory_id(int inventory_id) {
		this.inventory_id = inventory_id;
	}
	public int getUp_close() {
		return up_close;
	}
	public void setUp_close(int up_close) {
		this.up_close = up_close;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public int getShield() {
		return shield;
	}
	public void setShield(int shield) {
		this.shield = shield;
	}
	public int getArmour() {
		return armour;
	}
	public void setArmour(int armour) {
		this.armour = armour;
	}
	@Override
	public String toString() {
		return "Inventory [inventory_id=" + inventory_id + ", up_close=" + up_close + ", distance=" + distance
				+ ", shield=" + shield + ", armour=" + armour + "]";
	}

}
