package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import Exceptions.ItemNotFoundException;
import Services.ItemService;
import dao.ItemPostgres;
import models.Items;


public class ItemServiceTest {
	private static ItemService svc = new ItemService();
	static List<Items> items;

	//List<Items> ph = new ArrayList<>();
	@BeforeAll
	public static void setUp() {
		items = new ArrayList<>();
		
		items.add(new Items(1, "Deku Stick", 10, "A long branch gathered from the Great Deku Tree."));
		items.add(new Items(2,"Deku Seeds", 30, "Can be used as slingshot ammo. Set of 30."));
		items.add(new Items(3,"Deku Shield", 40, "Shield made from the Great Deku Tree material."));
		items.add(new Items(4,"Bombs", 30, "Explosives hatched from Goron Plants. Set of 10"));
		items.add(new Items(5,"Fairy", 50, "Can bring back to life or cure illness."));
		items.add(new Items(6,"Arrows", 30, "You can shoot these if you have a bow. Set of 30"));
		items.add(new Items(7,"Magic Bean", 100, "These beans are magical! Only for sale to bean experts."));
		items.add(new Items(8,"Bombchu", 50, "These are small bombs that run along the ground."));
		items.add(new Items(9,"Milk", 20, "Heals you for 8 hearts. Contains 2 servings. Need an empty bottle to carry."));
		items.add(new Items(10,"Chateau Romani", 200, "Vintage milk produced by specially-bred cows. Need an empty bottle."));
		
	}
//	@AfterAll
//	@Test
//	public static void testDeleteItem() throws ItemNotFoundException {
//		boolean itemDeleted = false;
//		svc.deleteItem(5);
//		List<Items> dItems = svc.getAllItems();
//		items.remove(4);
//		List<Items> remItems = items;
//		
//		System.out.println(dItems);
//		System.out.println(remItems);
//		if (dItems != remItems) {
//			itemDeleted = false;
//		} else {itemDeleted = true;}
//		assertEquals(true, itemDeleted);
//	}
	
	
//	@AfterAll
//	@Test
//	public static void testAddItem() throws ItemNotFoundException {
//		boolean itemAdded = svc.addItem(items.get(0));
//		assertEquals(true, itemAdded);
//	}
	
	@Test
	public void testGetAllItems() throws ItemNotFoundException {
		List<Items> rItems = svc.getAllItems();
		assertEquals(rItems, items);
	}

	@Test
	public void testGetItembyID() throws ItemNotFoundException {
		Items item = svc.getByID(1);
		assertEquals(items.get(0), item);
	}
 

//	@AfterAll
//	@Test
//	public static void testUpdateItem() throws ItemNotFoundException {
//		boolean updatedItem = false;
//		Items updatingItem = items.get(0);
//		updatingItem.setItemName("Null Item");
//		
//		if (svc.updateItem(updatingItem)) {
//			updatedItem = true;
//			System.out.println(svc.getByID(1));
//		} else {
//			updatedItem = false;
//		}
//		
//		assertEquals(true, updatedItem);		
//	}
	
	//When getting from console repo, index - 1
	//////
	@Test
	public void testGetItemsByVal() throws ItemNotFoundException {
		List<Items> uItems;
		List<Items> uValid = new ArrayList<>();
		uValid.add(items.get(6));
		uItems = svc.getItemsByValue(100);
		assertEquals(uItems, uValid);
	}

	@Test
	public void testGetItemsByName () throws ItemNotFoundException {
		List<Items> nItems;
		List<Items> valid = new ArrayList<>();
		valid.add(items.get(0));
		nItems = svc.getItemsByName("Deku Stick");
		assertEquals(nItems, valid);
	}
	
	@Test
	public void testgetItemsByNandV () throws ItemNotFoundException {
		List<Items> nvValid = new ArrayList<>();
		List<Items> nvItems;
		nvItems = svc.getItemsbyNameAndValue("Arrows", 30);
		nvValid.add(items.get(5));
		assertEquals(nvItems, nvValid);
	}
	
	
	
	
}
