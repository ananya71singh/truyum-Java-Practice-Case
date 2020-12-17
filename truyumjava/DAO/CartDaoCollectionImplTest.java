package com.cognizant.truyum.dao;

import java.util.List;

import com.cognizant.truyum.model.MenuItem;

public class CartDaoCollectionImplTest {

	public static void main(String[] args) 
	{
	testRemoveCartItem();	
	}
	public static void testRemoveCartItem()
	{
		CartDaoCollectionImpl cartDaoCollectionImpl=new CartDaoCollectionImpl();
		List<MenuItem> list;
		try {
			list = cartDaoCollectionImpl.getAllCartItems(123);
			for(MenuItem menuItem : list)
			{
				System.out.println(menuItem.getId()+" "+menuItem.getName()+" "+menuItem.getCategory()+" "+menuItem.getPrice()+" "+menuItem.getDateOfLaunch());
			}
		} catch (CartEmptyException e1) {
			System.out.println("Cart is Empty");
					}
		
		
			cartDaoCollectionImpl.removeCartItem(123, 1);
			System.out.println("After removing item");
		try {
			list = cartDaoCollectionImpl.getAllCartItems(123);
			
			for(MenuItem menuItem : list)
			{
				System.out.println(menuItem.getId()+" "+menuItem.getName()+" "+menuItem.getCategory()+" "+menuItem.getPrice()+" "+menuItem.getDateOfLaunch());
			}
			
		} catch (CartEmptyException e) {
			e.printStackTrace();
		}
		
	}

}
