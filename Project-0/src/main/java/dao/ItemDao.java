package dao;
import java.util.List;

import models.Items;

public interface ItemDao {
	/*
	 * Function:
	 * 	- retrieve all records
	 *  - retrieve specified id record
	 *  - add records
	 *  - update record based on id
	 *  - delete record based on id
	 */
	public Items getItembyId(int id);
//	public List<Items> getAllItems();
//	public int addItem(Items newItem);
//	public boolean updateItem(Items item);
//	public boolean deleteItem(int id);

}

