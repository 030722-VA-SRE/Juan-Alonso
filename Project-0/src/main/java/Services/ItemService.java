package Services;

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
}
