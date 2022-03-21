package control;



import org.eclipse.jetty.http.HttpStatus;

import Exceptions.ItemNotFoundException;
import Services.ItemService;
import io.javalin.Javalin;
import models.Items;

public class Driver {

	
	public static void main(String[] args) {
		
				//To interact with methods in item service class
				//those methods then interact with itempostgres for persistence and add business logic
		ItemService is = new ItemService();
		
				//Javalin endpoints and logic to handle endpoints	
		Javalin app = Javalin.create();
		app.start(8081);

		
		
		
				//GET all items
		
		app.get("items", (ctx) -> {
			
			try {
					ctx.json(is.getAllItems());
					ctx.status(200);
				}
				catch (ItemNotFoundException e) {
				ctx.result("Item not found");
				ctx.status(404);
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
			} catch (ItemNotFoundException e) {
				ctx.status(404);
				ctx.result("Item id: " + id + " not found");
			}
		});
		
		
		
		
		
		
		
		//adding items to db
		
		app.post("items", (ctx) -> {
			Items newItem = ctx.bodyAsClass(Items.class);
			
			if (is.addItem(newItem)) {
				ctx.status(200);
				ctx.result("Item " + newItem.getItemName() + " created.");
				
			} else {
				ctx.status(400);
				ctx.result("Could not add Item");
			}
		});
		
		
		
		// deleting item by id
		
		app.delete("items/{id}", (ctx) -> {
				int id = Integer.parseInt(ctx.pathParam("id"));
				
				
				if (is.deleteItem(id)) {
					ctx.status(200);
					ctx.result("Item with id " + id + " deleted successfully!");
				} else {
					ctx.status(400);
					ctx.result("No item with id " + id + "found.");
				};
		});
		
		
		//updating item by id
		
		app.put("items", (ctx) -> {
			Items udItem = ctx.bodyAsClass(Items.class);
			
			if (is.updateItem(udItem)) {
				ctx.status(200);
				ctx.result("Updated item with id " + udItem.getId());
			}
			else {
				ctx.status(400);
				ctx.result("No item found with id " + udItem.getId());
			}
		});
		
		
		}
	}