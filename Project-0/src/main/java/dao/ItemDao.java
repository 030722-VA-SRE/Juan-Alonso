package dao;
import java.util.List;

import models.Items;

public interface ItemDao {
	
	public Items getItembyId(int id);
	public List<Items> getAllItems();
	public int addItem(Items newItem);
	public boolean deleteItem(int id);
	public boolean updateItem(Items item);
	public List<Items> getItemsByValue(int value);
	public List<Items> getItemsByName(String item_name);
	public List<Items> getItemsbyNameAndValue(String item_name, int value);
}

