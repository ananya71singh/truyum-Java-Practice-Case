package com.cognizant.truyum.dao;

import java.util.List;

import com.cognizant.truyum.model.MenuItem;

public class CartDaoSqlImplTest {

	public static void main(String[] args) {
		
		/*
		 * testGetAllCartItems(); testAddCartItem();
		 */ 
		testRemoveCartItem(); 
			
		testGetAllCartItems();
		 
		
	}
	
	public static void testAddCartItem()
	{
		CartDaoSqlImpl cartDaoSqlImpl=new CartDaoSqlImpl();
		cartDaoSqlImpl.addCartItem(123, 2);
		
	}
	public static void testGetAllCartItems()
	{
		CartDaoSqlImpl cartDaoSqlImpl=new CartDaoSqlImpl();
	
		try {
			List<MenuItem> list=cartDaoSqlImpl.getAllCartItems(123);
			for(MenuItem menuItem:list)
			{
				
				System.out.println(menuItem.getId()+" "+menuItem.getName()+" "+menuItem.getPrice()+" "+menuItem.getCategory()+" "+
			menuItem.isActive()+" "+menuItem.getDateOfLaunch()+" "+menuItem.isFreeDelivery());
			}
		} catch (CartEmptyException e) {
			e.printStackTrace();
		}
		
	}
	public static void testRemoveCartItem()
	{
		CartDaoSqlImpl cartDaoSqlImpl=new CartDaoSqlImpl();
		cartDaoSqlImpl.removeCartItem(123, 2);
	}

}
