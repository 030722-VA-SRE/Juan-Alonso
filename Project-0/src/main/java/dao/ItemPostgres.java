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
	
	
//	@Override
//	public List<Items> getAllItems() {
//		String sql = "select * from items;";
//		List<Items> items = new ArrayList<>();
//		
//		try (Connection u = ConnectionUtil.getConnectionFromEnv()){
//			Statement s = u.createStatement();
//			ResultSet rs = s.executeQuery(sql);
//			
//			while (rs.next()) {
//				Items newItem = new Items();
//				newItem.setId(rs.getInt("id"));
//				newItem.setItemName(rs.getString("itemName"));
//				newItem.setValue(rs.getInt("value"));
//				newItem.setDescription(rs.getString("description"));
//				
//				items.add(newItem);
//			}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		 return items;
//	}

	
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

//	@Override
//	public int addItem(Items newItem) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public boolean updateItem(Items item) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean deleteItem(int id) {
//		// TODO Auto-generated method stub
//		return false;
//	}
	
	
	
	
	
	
	
}
