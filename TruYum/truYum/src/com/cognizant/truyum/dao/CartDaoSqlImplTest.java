package com.cognizant.truyum.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.cognizant.truyum.model.Cart;

public class CartDaoSqlImplTest {

	public static void testAddCartItem() throws CartEmptyException {

		CartDao cartDao = new CartDaoSqlImpl();
		cartDao.addCartItem(1L, 2);
		System.out.println("Item Added to Cart Successfully");

	}

	public static void testGetAllCartItems() throws CartEmptyException {
		CartDaoSqlImpl cartDaoSqlImpl = new CartDaoSqlImpl();
		Cart cart = cartDaoSqlImpl.getAllCartItems(1L);
		System.out.println(cart.getMenuItemList());
		System.out.println("Total price= " + cart.getTotal());
	}

	public static void testRemoveCartItem() throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter user id");
		long userId = Long.parseLong(bf.readLine());
		System.out.println("Enter user menu item id");
		long menuItemId = Long.parseLong(bf.readLine());

		new CartDaoSqlImpl().removeCartItem(userId, menuItemId);
	}

	public static void main(String[] args) throws CartEmptyException, NumberFormatException, IOException {

		testAddCartItem();
		testGetAllCartItems();
		testRemoveCartItem();
	}

}
