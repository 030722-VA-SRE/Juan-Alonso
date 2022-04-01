package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dto.UserDTO;
import com.revature.exceptions.ItemNotFoundException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Item;
import com.revature.models.User;
import com.revature.services.ItemService;

@RestController
@RequestMapping("/items")
public class ItemController {

	private ItemService is;

	@Autowired
	public ItemController(ItemService is) {
		super();
		this.is = is;
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping
	public ResponseEntity<List<Item>> getAll() {
		return new ResponseEntity<>(is.getAll(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> createItem(@RequestBody Item item/*, @RequestHeader String header*/) {
		Item i = is.createItem(item);
		return new ResponseEntity<>("Item " + i.getItem_name() + " was created.", HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Item> getItemById(@PathVariable("id") int id) {
		
			return new ResponseEntity<>(is.getItemById(id), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Item> updateItem(@RequestBody Item item, @PathVariable("id") int id) {
		return new ResponseEntity<>(is.updateItem(id, item), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") int id) throws ItemNotFoundException {
		is.deleteItem(id);
		return new ResponseEntity<>("Item deleted", HttpStatus.OK);
	}
	
	
	
	
	
	
}
