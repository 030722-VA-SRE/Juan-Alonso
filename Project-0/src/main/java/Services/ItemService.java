package Services;

import java.util.List;

import Exceptions.ItemNotFoundException;
import dao.ItemDao;
import dao.ItemPostgres;
import models.Items;

public class ItemService {
	
	private ItemDao itDao;
	
	
	public ItemService() {
		itDao = new ItemPostgres();
	}
	
	
	
	public Items getByID(int id) throws ItemNotFoundException {
		Items item = itDao.getItembyId(id);
		
		//if retrieved item is null, throw exception
		if(item == null) {
			throw new ItemNotFoundException();
		}	
		return item;
	}
	

	
	
	
	public List<Items> getAllItems() throws ItemNotFoundException {
		List<Items> items = itDao.getAllItems();
		
		if (items == null) {
			throw new ItemNotFoundException();
		}
		return itDao.getAllItems();
	}
	
	
	
	
	public boolean addItem(Items newItem) {
		
		int genID = newItem.getId();
		
		if (genID == newItem.getId()) {
			itDao.addItem(newItem);
		};
		return true;
				
	}
	
	public boolean deleteItem(int id) {
		return itDao.deleteItem(id);
	}
	
	public boolean updateItem(Items item) {
		return itDao.updateItem(item);
	}
	
	public List<Items> getItemsByValue(int value) {
		return itDao.getItemsByValue(value);
	}
	
	public List<Items> getItemsByName(String item_name) {
		return itDao.getItemsByName(item_name);
	}
	public List<Items> getItemsbyNameAndValue(String item_name, int value) {
		return itDao.getItemsbyNameAndValue(item_name, value);
	}
	
	
	
	
	
	
}
