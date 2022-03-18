package control;



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

		
		app.get("items", (ctx -> {
			
		}));
		
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
		
		
		
		
		
		/*
		 * Testing out port number and postman:
		 * 		Http Request
		 * -GET
		 * -url:test
		 * -Header:
		 * -Body:
		 * 		Http Response
		 * -Body: "Hello there!"
		 * 
		 *
		 
		app.get("test", (ctx) -> {
			ctx.result("Testing 1, 2, Testing 1, 2");
		});
		*/
		
	}
}