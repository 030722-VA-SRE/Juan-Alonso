package models;

import java.util.Objects;

public class Items {

	
			//properties of each item
	private int id;
	private String itemName;
	private int value;
	private String description;
	
	
	public Items() {
		super();

	}
	
	public Items(String itemName, int value, String description) {
		this();
		this.itemName = itemName;
		this.value = value;
		this.description = description;
	}
	
	public Items(int id, String itemName, int value, String description) {
		this();
		this.id = id;
		this.itemName = itemName;
		this.value = value;
		this.description = description;
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Items [id=" + id + ", item name=" + itemName + ", value=" + value + "description=" + description + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(description, id, itemName, value);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Items other = (Items) obj;
		return Objects.equals(description, other.description) && id == other.id
				&& Objects.equals(itemName, other.itemName) && value == other.value;
	}
	
	
	
}