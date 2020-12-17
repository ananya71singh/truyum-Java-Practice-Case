package com.cognizant.truyum.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

public class CartDaoCollectionImpl implements CartDao {

	private static HashMap<Long, Cart>userCarts;
	
	
	
	public static HashMap<Long, Cart> getUserCarts() {
		return userCarts;
	}

	public static void setUserCarts(HashMap<Long, Cart> userCarts) {
		CartDaoCollectionImpl.userCarts = userCarts;
	}

	public CartDaoCollectionImpl() {
		if(userCarts==null)
		{
			HashMap<Long, Cart> cart=new HashMap<>();
			List<MenuItem> list=new ArrayList<>();
			list.add(new MenuItem(1,"Sandwich", (float) 99.0, true, DateUtil.convertToDate("15/07/2017"), "Main Course", true));
			list.add(new MenuItem(2,"Burger", (float) 129.0, true, DateUtil.convertToDate("23/12/2017"), "Main Course", false));
			double total=0;
			for(int i=0;i<list.size();i++)
			{
				total+=list.get(i).getPrice();
			}
			Cart ob=new Cart(list,total);
			cart.put((long) 123,ob);
			total=0;
			ob=null;
			list=new ArrayList<>();
			list.add(new MenuItem(3,"Pizza", (float) 149.0, true, DateUtil.convertToDate("21/08/2018"), "Main Course", false));
			list.add(new MenuItem(4,"French Fries", (float) 57.0, false, DateUtil.convertToDate("02/07/2017"), "Starters", true));
			list.add(new MenuItem(5,"Chocolate Brownie", (float) 32.0, true, DateUtil.convertToDate("02/11/2022"), "Desert", true));
			for(int i=0;i<list.size();i++)
			{
				total+=list.get(i).getPrice();
			}
			ob=new Cart(list,total);
			cart.put((long) 456,ob);
			
			setUserCarts(cart);
		}
	}

	@SuppressWarnings("null")
	@Override
	public void addCartItem(long userId, long menuItemId) {
		Cart ob=userCarts.get(userId);
		MenuItemDaoCollectionImpl menuItemDaoCollectionImpl=new MenuItemDaoCollectionImpl();
		
		if(ob==null)
		{	
			List<MenuItem> list=new ArrayList<>();
					list.add(menuItemDaoCollectionImpl.getMenuItem(menuItemId));
			double total=menuItemDaoCollectionImpl.getMenuItem(menuItemId).getPrice();
			ob.setMenuItemList(list);
			ob.setTotal(total);
			userCarts.put(userId,ob);
		}
		else
		{
			List<MenuItem> list=ob.getMenuItemList();
			list.add(menuItemDaoCollectionImpl.getMenuItem(menuItemId));
			double total=ob.getTotal()+menuItemDaoCollectionImpl.getMenuItem(menuItemId).getPrice();
			ob.setMenuItemList(list);
			ob.setTotal(total);
			userCarts.put(userId, ob);
		}

	}

	@Override
	public List<MenuItem> getAllCartItems(long userId) throws CartEmptyException {
		
		if(userCarts.isEmpty())
		{
			throw new CartEmptyException("Empty List found exception");
		}
		return userCarts.get(userId).getMenuItemList();
	}

	@Override
	public void removeCartItem(long userId, long menuItemId)
	{
		Cart ob=userCarts.get(userId);
		if(ob== null)
		{
			try {
				throw new CartEmptyException("No user found");
			} catch (CartEmptyException e) {
				System.out.println(e.toString());
			}
		}
		List<MenuItem> list=ob.getMenuItemList();
		for(int i=0;i<list.size();i++)
		{
			if(list.get(i).getId()== menuItemId)
			{
				double total=ob.getTotal()-list.get(i).getPrice();
				ob.setTotal(total);
				list.remove(i);
				break;
			}
		}
		ob.setMenuItemList(list);
		userCarts.put(userId, ob);	
		
	}

}
