package com.cognizant.truyum.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

public class CartDaoSqlImpl implements CartDao
{

	public List<MenuItem> getAllCartItems(long userId)throws CartEmptyException
	{
		
		
		Connection con=ConnectionHandler.getConnection();
		List<MenuItem> list=new ArrayList<>();
		String sql="select m.item_id,m.price,m.active,m.date_of_launch,m.category,m.free_delivery,m.name from menu_items m join cart c on m.item_id=c.item_id where userid=?";
		try {
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
			
			java.sql.PreparedStatement preparedStatement=con.prepareStatement(sql);
			preparedStatement.setString(1, String.valueOf(userId).trim());
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
			sql="select sum(price) as total from cart where userid=? group by userid";
			 preparedStatement=con.prepareStatement(sql);
			preparedStatement.setString(1, String.valueOf(userId).trim());
			 rs=preparedStatement.executeQuery();
			 if(rs.next())
			 {
				Cart ob=new Cart(list,Double.parseDouble(rs.getString("total")));
				System.out.println("Total Bill for user  "+userId+" is "+rs.getString("total"));
			 }
			 else
			 {
				 System.out.println("Cart is Empty");
			 }
			 con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	public void addCartItem(long userId,long menuItemId)
	{
		MenuItemDaoCollectionImpl menuItemDaoCollectionImpl=new MenuItemDaoCollectionImpl();
		MenuItem menuItem=menuItemDaoCollectionImpl.getMenuItem(menuItemId);
		Connection con=ConnectionHandler.getConnection();
		String sql="insert into cart (userid,free_delivery,price,name,item_id) values(?,?,?,?,?)";
		try {
			java.sql.PreparedStatement preparedStatement=con.prepareStatement(sql);
			preparedStatement.setString(1, String.valueOf(userId));
			preparedStatement.setBoolean(2, menuItem.isFreeDelivery());
			preparedStatement.setInt(3, (int)menuItem.getPrice());
			preparedStatement.setString(4, menuItem.getName());
			preparedStatement.setString(5, String.valueOf(menuItem.getId()));
			int rs=preparedStatement.executeUpdate();
			if(rs >0)
			{
				System.out.println("Insertion Successfully");
			}
			else
			{
				System.out.println("Insertion failed!!");
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}
	public void removeCartItem(long userId,long menuItemId)
	{
		Connection con=ConnectionHandler.getConnection();
		String sql="delete from cart where userid=? and item_id=? limit 1";
		try {
			java.sql.PreparedStatement preparedStatement=con.prepareStatement(sql);
			preparedStatement.setString(1, String.valueOf(userId));
			preparedStatement.setString(2, String.valueOf(menuItemId));
			
			int rs=preparedStatement.executeUpdate();
			if(rs >0)
			{
				System.out.println("Deleted Successfully");
			}
			else
			{
				System.out.println("Deletion failed!!");
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
