package com.revature.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dto.UserDTO;
import com.revature.exceptions.ItemNotFoundException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Item;
import com.revature.models.User;
import com.revature.repositories.ItemRepository;
import com.revature.repositories.UserRepository;

@Service
public class ItemService {

	@Autowired
	private UserRepository ur;
	private ItemRepository ir;
	
	
	public ItemService(UserRepository ur, ItemRepository ir) {
		super();
		this.ur = ur;
		this.ir = ir;
	}
	
	public Item getItemById(int id) throws ItemNotFoundException {
		Item item = ir.findById(id).orElseThrow(ItemNotFoundException::new);
		return item;
	}

	@Transactional
	public Item createItem(Item newItem) {
		return ir.save(newItem);
	}
	
	public List<Item> getAll(){
		return ir.findAll();
	}
	
	@Transactional
	public Item updateItem(int id, Item item) {
		//log for update item
		//check if exists
		return ir.save(item);
	}
	
	@Transactional
	public void deleteItem(int id) throws ItemNotFoundException {
	
		getItemById(id);	
		ir.deleteById(id);
	}
	
	
	
	
}
