package com.cognizant.truyum.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

public class MenuItemDaoCollectionImpl implements MenuItemDao {

	private static List<MenuItem>menuItemList;
	
	
	

	public static List<MenuItem> getMenuItemList() {
		return menuItemList;
	}

	public static void setMenuItemList(List<MenuItem> menuItemList) {
		MenuItemDaoCollectionImpl.menuItemList = menuItemList;
	}

	public MenuItemDaoCollectionImpl() {
		if(menuItemList == null)
		{
			List<MenuItem> list=new ArrayList<>();
			list.add(new MenuItem(1,"Sandwich", (float) 99.0, true, DateUtil.convertToDate("15/07/2017"), "Main Course", true));
			list.add(new MenuItem(2,"Burger", (float) 129.0, true, DateUtil.convertToDate("23/12/2017"), "Main Course", false));
			list.add(new MenuItem(3,"Pizza", (float) 149.0, true, DateUtil.convertToDate("21/08/2018"), "Main Course", false));
			list.add(new MenuItem(4,"French Fries", (float) 57.0, false, DateUtil.convertToDate("02/07/2017"), "Starters", true));
			list.add(new MenuItem(5,"Chocolate Brownie", (float) 32.0, true, DateUtil.convertToDate("02/11/2022"), "Desert", true));
			setMenuItemList(list);
		}
		
	}

	@Override
	public List<MenuItem> getMenuItemListAdmin() {
		return menuItemList;
	}

	@Override
	public List<MenuItem> getMenuItemListCustomer() {
		
		List<MenuItem> menuItemListCustomer=new ArrayList<>();
		Date date =new Date();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
		String d1=simpleDateFormat.format(date);
		for(int i=0;i<menuItemList.size();i++)
		{
			if((menuItemList.get(i).getDateOfLaunch().before(DateUtil.convertToDate(d1))|| 
					menuItemList.get(i).getDateOfLaunch().equals(DateUtil.convertToDate(d1))
					&& menuItemList.get(i).isActive()))
			{
				menuItemListCustomer.add(menuItemList.get(i));
			}
			{
				
			}
		}
		
		return menuItemListCustomer;
	}

	@Override
	public void modifyMenuItem(MenuItem menuItem) {
		
		for(int i=0;i<menuItemList.size();i++)
		{
			if(menuItemList.get(i).getId()==menuItem.getId())
			{
				menuItemList.get(i).setName(menuItem.getName());
				menuItemList.get(i).setPrice(menuItem.getPrice());
				menuItemList.get(i).setCategory(menuItem.getCategory());
				menuItemList.get(i).setDateOfLaunch(menuItem.getDateOfLaunch());
				menuItemList.get(i).setFreeDelivery(menuItem.isFreeDelivery());
				menuItemList.get(i).setActive(menuItem.isActive());
			}
		}

	}

	@Override
	public MenuItem getMenuItem(long menuItemId) {
		
		for(int i=0;i<menuItemList.size();i++)
		{
			if(menuItemList.get(i).getId()==menuItemId)
			{
				return menuItemList.get(i);
			}
		}

		return null;
	}

}
