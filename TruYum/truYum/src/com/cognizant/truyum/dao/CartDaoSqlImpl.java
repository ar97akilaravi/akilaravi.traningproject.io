package com.cognizant.truyum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;

public class CartDaoSqlImpl implements CartDao {
	public static final String ADD_CART_ITEM = "INSERT INTO `cart` ( `ct_us_id`, `ct_me_id`) VALUES (?,?)";
	public static final String GET_ALL_CART_ITEMS = "select me_id,me_name,me_price,me_active,me_date_of_launch,me_category,me_free_delivery from menu_item inner join cart on ct_me_id = me_id where ct_us_id=?";
	public static final String GET_TOTAL = "select sum(me_price) as total from menu_item inner join cart on ct_me_id = me_id where ct_us_id=?";
	public static final String DELETE_CART = "delete from cart where ct_us_id=? and  ct_me_id=? limit 1";

	@Override
	public void addCartItem(long userId, long menuItemId) {
		Connection connection = ConnectionHandler.getConnection();
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(ADD_CART_ITEM);
			statement.setLong(1, userId);
			statement.setLong(2, menuItemId);
			int noOfRows = statement.executeUpdate();
			if (noOfRows == 0) {
				System.out.println("Insert Not Done");
			}
		} catch (SQLException e1) {
			System.out.println("SQL Exception occurs");
		} finally

		{
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("SQL Exception occurs");
			}
		}

	}

	@Override
	public Cart getAllCartItems(long userId) throws CartEmptyException {
		Connection connection = ConnectionHandler.getConnection();
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatementTotal = null;
		ArrayList<MenuItem> menuItemList = new ArrayList<MenuItem>();
		ResultSet resultSetList = null;
		ResultSet resultSetTotal = null;
		Cart cart = new Cart();
		try {
			preparedStatement = connection.prepareStatement(GET_ALL_CART_ITEMS);
			preparedStatement.setLong(1, userId);
			resultSetList = preparedStatement.executeQuery();
			preparedStatementTotal = connection.prepareStatement(GET_TOTAL);
			preparedStatementTotal.setLong(1, userId);
			resultSetTotal = preparedStatementTotal.executeQuery();
			while (resultSetList.next()) {
				MenuItem menuItem = new MenuItem();
				menuItem = new MenuItem();
				menuItem.setId(resultSetList.getLong("me_id"));
				menuItem.setName(resultSetList.getString("me_name"));
				menuItem.setPrice(resultSetList.getFloat("me_price"));
				menuItem.setActive(resultSetList.getString("me_active").equals("1"));
				menuItem.setDateOfLaunch(resultSetList.getDate("me_date_of_launch"));
				menuItem.setCategory(resultSetList.getString("me_category"));
				menuItem.setFreeDelivery(resultSetList.getString("me_free_delivery").equals("1"));

				menuItemList.add(menuItem);

			}
			while (resultSetTotal.next()) {
				cart.setTotal(resultSetTotal.getDouble("Total"));
			}
			if (menuItemList.size() == 0) {
				throw new CartEmptyException();
			}
			cart.setMenuItemList(menuItemList);

		} catch (SQLException e) {
			System.out.println("SQL Exception occurs");
		} finally {
			try {

				if (resultSetList != null)
					resultSetList.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (resultSetTotal != null)
					resultSetTotal.close();
				if (preparedStatementTotal != null)
					preparedStatementTotal.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				System.out.println("SQL Exception occurs");
			}
		}
		return cart;
	}

	@Override
	public void removeCartItem(long userId, long menuItemId) {
		Connection connection = ConnectionHandler.getConnection();

		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(DELETE_CART);
			statement.setLong(1, userId);
			statement.setLong(2, menuItemId);
			int noOfRows = statement.executeUpdate();
			System.out.println("No of rows affected" + noOfRows);
		} catch (SQLException e) {
			System.out.println("SQL Exception occurs");
		} finally

		{
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("SQL Exception occurs");
			}
		}
	}
}
