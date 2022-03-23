package control;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.http.HttpStatus;

import Exceptions.ItemNotFoundException;
import Services.ItemService;
import io.javalin.Javalin;
import models.Items;

public class Driver {

	//private static Logger log = LogManager.getLogger(Driver.class);
	private static Logger log = LogManager.getRootLogger();
	
	public static void main(String[] args) {
		
		
				//To interact with methods in item service class
				//Those methods then interact with itempostgres for persistence and add business logic
		ItemService is = new ItemService();
		
				//Javalin endpoints and logic to handle endpoints	
		Javalin app = Javalin.create();
		app.start(8081);
		log.info("App was launched.");
		
		//get all items or search by criteria
		
		app.get("items", (ctx) -> {
			String itemName = ctx.queryParam("item_name");
			String itemValue = ctx.queryParam("value");
			
			/*
			 * Different response per query params:
			 * 	-If item_name = null && value = null
			 * 		-return everything
			 * 	-if item_name != null && value = null
			 * 		-return search by itemname
			 * 	-if item_name = null && value != null
			 * 		-return search by value
			 * 	-if item_name != null && value != null
			 * 		return search by both
			 */
		
			try {
			if (itemName == null && itemValue == null) {
				ctx.json(is.getAllItems());
				ctx.status(200);
				log.info("Item search performed");
				
			} else if (itemName != null && itemValue == null) {
				
				List<Items> items = is.getItemsByName(itemName);
				ctx.json(items);
				
			} else if (itemName == null && itemValue != null) {
				int value = Integer.parseInt(itemValue);
				List<Items> items = is.getItemsByValue(value);
				ctx.json(items);
				
			} else {
				int value = Integer.parseInt(itemValue);
				List<Items> items = is.getItemsbyNameAndValue(itemName, value);
				ctx.json(items);
			}
			} catch (ItemNotFoundException e) {
				ctx.status(400);
				ctx.result("Item not found.");
				log.error("Failed item search");
			}
			
		});
		
		
		
				//GET item by id
		
		app.get("items/{id}", (ctx) -> {
				//return value of pathparam of "id", convert string to int
			int id = Integer.parseInt(ctx.pathParam("id"));
			
			try {
				Items item = is.getByID(id);
				
				ctx.json(item);
				ctx.status(200);
				log.info("Search for item id [" + item.getId() + "] performed.");
			} catch (ItemNotFoundException e) {
				ctx.status(404);
				ctx.result("Item id: " + id + " not found");
				log.error("Invalid id search performed for id [" + id + "].");
				e.printStackTrace();
				log.error("Exception was thrown: " + e.fillInStackTrace());
			}
		});
		
		
		
		
		
		
		
		//adding items to db
		
		app.post("items", (ctx) -> {
			Items newItem = ctx.bodyAsClass(Items.class);
			
			if (is.addItem(newItem)) {
				ctx.status(200);
				ctx.result("Item " + newItem.getItemName() + " created.");
				log.info("Item [" + newItem.getItemName() + "] created.");
				
			} else {
				ctx.status(400);
				ctx.result("Could not add Item");
				log.error("Failed to add item to database.");
			}
		});
		
		
		
		// deleting item by id
		
		app.delete("items/{id}", (ctx) -> {
				int id = Integer.parseInt(ctx.pathParam("id"));
				
				
				if (is.deleteItem(id)) {
					ctx.status(200);
					ctx.result("Item with id " + id + " deleted successfully!");
					log.info("Item id [" + id + "] deleted");
				} else {
					ctx.status(400);
					ctx.result("No item with id " + id + "found to delete.");
					log.error("Failed to delete id [" + "].");
				};
		});
		
	  	
		//updating item by id
		
		app.put("items", (ctx) -> {
			Items udItem = ctx.bodyAsClass(Items.class);
			
			if (is.updateItem(udItem)) {
				ctx.status(200);
				ctx.result("Updated item with id " + udItem.getId());
				log.info("Item ID " + udItem.getId() + " updated to Item Name " + udItem.getItemName() + ".");	
			}
			else {
				ctx.status(400);
				ctx.result("No item found with id " + udItem.getId());
				log.error("Failed to update item " + udItem.getId());
			}
		});
		
		
		}
	}