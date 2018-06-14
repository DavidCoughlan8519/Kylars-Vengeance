package ie.cit.kylarsvengeance.domain;

public class Item {

	private int inventory_id;
	private int item_id;
	private String item;
	private String equip_type;
	private int protection;
	private int damage;
	private int purchase_cost;
	private int sell_value;
	private int upgrade_cost;
	
	public Item(){}
	
	public Item(int inventory_id, int item_id, String item, String equip_type, int protection, int damage,
			int purchase_cost, int sell_value, int upgrade_cost) {
		super();
		this.inventory_id = inventory_id;
		this.item_id = item_id;
		this.item = item;
		this.equip_type = equip_type;
		this.protection = protection;
		this.damage = damage;
		this.purchase_cost = purchase_cost;
		this.sell_value = sell_value;
		this.upgrade_cost = upgrade_cost;
	}

	public int getInventory_id() {
		return inventory_id;
	}

	public void setInventory_id(int inventory_id) {
		this.inventory_id = inventory_id;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getEquip_type() {
		return equip_type;
	}

	public void setEquip_type(String equip_type) {
		this.equip_type = equip_type;
	}

	public int getProtection() {
		return protection;
	}

	public void setProtection(int protection) {
		this.protection = protection;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getPurchase_cost() {
		return purchase_cost;
	}

	public void setPurchase_cost(int purchase_cost) {
		this.purchase_cost = purchase_cost;
	}

	public int getSell_value() {
		return sell_value;
	}

	public void setSell_value(int sell_value) {
		this.sell_value = sell_value;
	}

	public int getUpgrade_cost() {
		return upgrade_cost;
	}

	public void setUpgrade_cost(int upgrade_cost) {
		this.upgrade_cost = upgrade_cost;
	}

	@Override
	public String toString() {
		return "Item [item_id=" + item_id + ", item=" + item + ", equip_type=" + equip_type + ", protection="
				+ protection + ", damage=" + damage + ", purchase_cost=" + purchase_cost + ", sell_value=" + sell_value
				+ ", upgrade_cost=" + upgrade_cost + "]";
	}
	
}
