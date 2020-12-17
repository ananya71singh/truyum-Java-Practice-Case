package com.cognizant.truyum.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

public class MenuItemDaoSqlImpl implements MenuItemDao {

	public List<MenuItem> getMenuItemListAdmin()
	{
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
		Connection con=ConnectionHandler.getConnection();
		List<MenuItem> list=new ArrayList<>();
		String sql="select * from menu_items";
		try {
			java.sql.PreparedStatement preparedStatement=con.prepareStatement(sql);
			
			java.sql.ResultSet rs=preparedStatement.executeQuery();
			while(rs.next())
			{
				String item_id=rs.getString("item_id");
				int price=rs.getInt("price");
				boolean active=rs.getBoolean("active");
				String date_of_launch=simpleDateFormat.format(rs.getDate("date_of_launch"));
				String category=rs.getString("category");
				boolean freeDelivery=rs.getBoolean("free_delivery");
				String name=rs.getString("name");
				list.add(new MenuItem(Long.parseLong(item_id),name,price,active,DateUtil.convertToDate(date_of_launch),category,freeDelivery));
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return list;
		
	}
	public List<MenuItem> getMenuItemListCustomer()
	{
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
		Connection con=ConnectionHandler.getConnection();
		List<MenuItem> list=new ArrayList<>();
		String sql="select * from menu_items where active=true and date_of_launch < curdate()";
		try {
			java.sql.PreparedStatement preparedStatement=con.prepareStatement(sql);
			
			java.sql.ResultSet rs=preparedStatement.executeQuery();
			while(rs.next())
			{
				String item_id=rs.getString("item_id");
				int price=rs.getInt("price");
				boolean active=rs.getBoolean("active");
				String date_of_launch=simpleDateFormat.format(rs.getDate("date_of_launch"));
				String category=rs.getString("category");
				boolean freeDelivery=rs.getBoolean("free_delivery");
				String name=rs.getString("name");
				list.add(new MenuItem(Long.parseLong(item_id),name,price,active,DateUtil.convertToDate(date_of_launch),category,freeDelivery));
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}
	public MenuItem getMenuItem(long menuItemId )
	{
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
		Connection con=ConnectionHandler.getConnection();
		List<MenuItem> list=new ArrayList<>();
		String sql="select * from menu_items where item_id=?";
		MenuItem menuItem=null;
		try {
			java.sql.PreparedStatement preparedStatement=con.prepareStatement(sql);
			preparedStatement.setLong(1, menuItemId);
			java.sql.ResultSet rs=preparedStatement.executeQuery();
			while(rs.next())
			{
				String item_id=rs.getString("item_id");
				int price=rs.getInt("price");
				boolean active=rs.getBoolean("active");
				String date_of_launch=simpleDateFormat.format(rs.getDate("date_of_launch"));
				String category=rs.getString("category");
				boolean freeDelivery=rs.getBoolean("free_delivery");
				String name=rs.getString("name");
				menuItem=new MenuItem(Long.parseLong(item_id),name,price,active,DateUtil.convertToDate(date_of_launch),category,freeDelivery);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return menuItem;
		
	}
	public void editMenuItem(MenuItem menuItem)
	{
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		Connection con=ConnectionHandler.getConnection();
		String sql="update menu_items set price=?,active=?,date_of_launch=?,category=?,free_delivery=?,name=? where item_id=?";
		
		try {
			java.sql.PreparedStatement preparedStatement=con.prepareStatement(sql);
			preparedStatement.setInt(1, (int)menuItem.getPrice());
			preparedStatement.setBoolean(2, menuItem.isActive());
			java.sql.Date sqlDate = java.sql.Date.valueOf( simpleDateFormat.format(menuItem.getDateOfLaunch()) );
			preparedStatement.setDate(3,sqlDate);
			preparedStatement.setString(4, menuItem.getCategory());
			preparedStatement.setBoolean(5, menuItem.isFreeDelivery());
			preparedStatement.setString(6, menuItem.getName());
			preparedStatement.setLong(7, menuItem.getId());
			
			int rs=preparedStatement.executeUpdate();
			if(rs >0)
			{
				System.out.println("Data Updated Successfully");
			}
			else
			{
				System.out.println("Error Occured");
			}
		con.close();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void modifyMenuItem(MenuItem menuItem) {
		
	}

}
