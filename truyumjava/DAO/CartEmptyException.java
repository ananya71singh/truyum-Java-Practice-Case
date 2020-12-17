package com.cognizant.truyum.dao;

@SuppressWarnings("serial")
public class CartEmptyException extends Exception
{

	public CartEmptyException(String message)
	{
		System.out.println(message);
	}
}
