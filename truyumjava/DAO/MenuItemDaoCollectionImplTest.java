package com.cognizant.truyum.dao;

import java.util.List;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

public class MenuItemDaoCollectionImplTest {

	public static void main(String[] args) {
	
		testGetMenuItemListCustomer();
		testModifyMenuItem();
		testGetMenuItem();
	}
	
	public static void testGetMenuItemListAdmin()
	{
		
	}
	public static void testGetMenuItemListCustomer()
	{
		MenuItemDaoCollectionImpl daoCollectionImpl=new MenuItemDaoCollectionImpl();
		List<MenuItem> list=daoCollectionImpl.getMenuItemListCustomer();
		for(MenuItem menuItem: list)
		{
			System.out.println(menuItem.getId()+" "+menuItem.getName()+" "+menuItem.getPrice()+" "+menuItem.getCategory()+" "+menuItem.getDateOfLaunch());
		}
		
	}
	public static void testModifyMenuItem()
	{
		MenuItem menuItem=new MenuItem(1,"Sandwich", (float) 199.0, true, DateUtil.convertToDate("16/07/2018"), "Main Course", true);
		MenuItemDaoCollectionImpl daoCollectionImpl=new MenuItemDaoCollectionImpl();
		daoCollectionImpl.modifyMenuItem(menuItem);
		List<MenuItem> list=daoCollectionImpl.getMenuItemListCustomer();
		System.out.println();
		System.out.println("Data after modification");
		System.out.println();
		for(MenuItem menuItems: list)
		{
			System.out.println(menuItems.getId()+" "+menuItems.getName()+" "+menuItems.getPrice()+" "+menuItems.getCategory()
			+" "+menuItems.getDateOfLaunch());
		}
		
	}
	public static void testGetMenuItem()
	{
		MenuItemDaoCollectionImpl daoCollectionImpl=new MenuItemDaoCollectionImpl();
		MenuItem menuItems=daoCollectionImpl.getMenuItem(1);
		System.out.println(menuItems.getId()+" "+menuItems.getName()+" "+menuItems.getPrice()+" "+menuItems.getCategory()
		+" "+menuItems.getDateOfLaunch());
	}


}
