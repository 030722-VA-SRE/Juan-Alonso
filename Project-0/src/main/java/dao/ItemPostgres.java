package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ConnectionUtil.ConnectionUtil;
import models.Items;

public class ItemPostgres implements ItemDao {
	
	
	//WUP
	
	@Override
	public List<Items> getAllItems() {
		
		String sql = "select * from items;";
		List<Items> itemArr = new ArrayList<>();
		
		try (
			Connection u = ConnectionUtil.getConnectionFromEnv()){
			Statement s = u.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while (rs.next()) {
				Items item = new Items();
				item.setId(rs.getInt("id"));
				item.setItemName(rs.getString("item_name"));
				item.setValue(rs.getInt("value"));
				item.setDescription(rs.getString("description"));
				
				itemArr.add(item);
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		 return itemArr;
	}

	//WOP
	
	@Override
	public Items getItembyId(int id) {
		// TODO Auto-generated method stub
		String sql = "select * from items where id = ?;";
		Items item = null;
		
				//retrieving Connection to db for ConnectionUtil
		try (Connection u = ConnectionUtil.getConnectionFromEnv()){
					//prepared statement using connection
			PreparedStatement ps = u.prepareStatement(sql);
			
			ps.setInt(1, id);
			
				//Execute query from the ps, assigning the db's query result to result set
			ResultSet rs = ps.executeQuery();
			
			
			if (rs.next()) {
					item = new Items();
					item.setId(rs.getInt("id"));
					item.setItemName(rs.getString("item_name"));
					item.setValue(rs.getInt("value"));
					item.setDescription(rs.getString("description"));
					
					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
				//if item of that id is found, returns that item, else returns null
		return item;
	}
	
	
	
	@Override
	public int addItem(Items newItem) {
		// TODO Auto-generated method stub
		int newID = -1;
		String sql = "insert into items (item_name, value, description) values (?, ?, ?) returning id;";
		
		try (Connection u = ConnectionUtil.getConnectionFromEnv()){
			PreparedStatement ps = u.prepareStatement(sql);
			
			
			ps.setString(1, newItem.getItemName());
			ps.setInt(2, newItem.getValue());
			ps.setString(3, newItem.getDescription());
			
			
			ResultSet rs = ps.executeQuery();
				
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newID;
	}



	@Override
	public boolean deleteItem(int id) {
		// TODO Auto-generated method stub
		
		String sql = "delete from items where id = ?;";
		
		try (Connection u = ConnectionUtil.getConnectionFromEnv()){
			PreparedStatement ps = u.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
			
	}
	
	//1-itemname
	//2-value
	//3-description
	//4-id
	
	@Override
	public boolean updateItem(Items item) {
		// TODO Auto-generated method stub
		String sql = "update items set item_name = ?, value = ?, description = ? where id = ?;";
		
	
		try (Connection u = ConnectionUtil.getConnectionFromEnv()){
			PreparedStatement ps = u.prepareStatement(sql);
			
			ps.setString(1, item.getItemName());
			ps.setInt(2, item.getValue());
			ps.setString(3, item.getDescription());
			ps.setInt(4, item.getId());
			
			int ss = ps.executeUpdate();
			
			if (ss == 0) {
				return false;
			} else 
				return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<Items> getItemsByValue(int value){
		String sql = "select * from items where value = ?;";
		List<Items> items = new ArrayList<>();
		
		try (Connection u = ConnectionUtil.getConnectionFromEnv()){
			PreparedStatement ps = u.prepareStatement(sql);
			
			ps.setInt(1, value);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Items newItem = new Items();
				newItem.setId(rs.getInt("id"));
				newItem.setItemName(rs.getString("item_name"));
				newItem.setDescription(rs.getString("description"));
				
				items.add(newItem);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return items;
	}
	
	public List<Items> getItemsByName(String item_name) {
		String sql = "select * from items where item_name = '%?%';";
		List<Items> items = new ArrayList<>();
		
		try (Connection u = ConnectionUtil.getConnectionFromEnv()){
			PreparedStatement ps = u.prepareStatement(sql);
			
			ps.setString(1, item_name);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Items newItem = new Items();
				newItem.setId(rs.getInt("id"));
				newItem.setValue(rs.getInt("value"));
				newItem.setDescription(rs.getString("description"));
				
				items.add(newItem);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return items;
	}
	
	
	public List<Items> getItemsbyNameAndValue(String item_name, int value) {
		String sql = "select * from items where item_name = '%?%' and value = ?;";
		List<Items> items = new ArrayList<>();
		
		try (Connection u = ConnectionUtil.getConnectionFromEnv()){
			PreparedStatement ps = u.prepareStatement(sql);
			
			ps.setInt(1, value);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Items newItem = new Items();
				newItem.setId(rs.getInt("id"));
				newItem.setDescription(rs.getString("description"));
				
				items.add(newItem);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return items;
	}
	
	
	
	
	
}
