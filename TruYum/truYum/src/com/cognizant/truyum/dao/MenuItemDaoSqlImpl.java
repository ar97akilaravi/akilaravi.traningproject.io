package com.cognizant.truyum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

public class MenuItemDaoSqlImpl implements MenuItemDao {

	public static final String ADMIN_MENU_ITEM_LIST = "Select * from menu_item";
	public static final String MODIFY_MENU_ITEM_LIST = "UPDATE menu_item set me_name=?, me_price=?, me_active=?, me_date_of_launch=?, me_category=?, me_free_delivery=? where me_id=?";
	public static final String CUSTOMER_MENU_ITEM_LIST = "select * from menu_item where me_date_of_launch<=(select curdate()) and me_active='Yes'";
	public static final String GET_MENU_ITEM = "select * from menu_item where me_id=?";

	@Override
	public ArrayList<MenuItem> getMenuItemListAdmin() {
		ArrayList<MenuItem> menuItemList = new ArrayList<>();
		Connection connection = ConnectionHandler.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(ADMIN_MENU_ITEM_LIST);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				MenuItem menuItem = new MenuItem();
				menuItem.setId(resultSet.getLong("me_id"));
				menuItem.setName(resultSet.getString("me_name"));
				menuItem.setPrice(resultSet.getDouble("me_price"));
				menuItem.setActive(resultSet.getString("me_active").equals("Yes"));
				menuItem.setDateOfLaunch(resultSet.getDate("me_date_of_launch"));
				menuItem.setCategory(resultSet.getString("me_category"));
				menuItem.setFreeDelivery(resultSet.getString("me_free_delivery").equals("Yes"));

				menuItemList.add(menuItem);
			}
		} catch (SQLException e) {
			System.out.println("SQL Exception occurs");
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("SQL Exception occurs");
			}
		}
		return menuItemList;
	}

	@Override
	public void modifyMenuItem(MenuItem menuItem) {
		Connection connection = ConnectionHandler.getConnection();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(MODIFY_MENU_ITEM_LIST);

			statement.setLong(7, menuItem.getId());
			statement.setString(1, menuItem.getName());
			statement.setDouble(2, menuItem.getPrice());
			statement.setString(3, menuItem.getActive() ? "Yes" : "No");
			statement.setDate(4, DateUtil.convertToSqlDate(menuItem.getDateOfLaunch()));
			statement.setString(5, menuItem.getCategory());
			statement.setString(6, menuItem.getFreeDelivery() ? "Yes" : "No");

			statement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Update not Done");
		} finally {
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
	public List<MenuItem> getMenuItemListCustomer() {
		ArrayList<MenuItem> menuItemList = new ArrayList<>();
		Connection connection = ConnectionHandler.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(CUSTOMER_MENU_ITEM_LIST);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				MenuItem menuItem = new MenuItem();
				menuItem.setId(resultSet.getLong("me_id"));
				menuItem.setName(resultSet.getString("me_name"));
				menuItem.setPrice(resultSet.getDouble("me_price"));
				menuItem.setActive(resultSet.getString("me_active").equals("Yes"));
				menuItem.setDateOfLaunch(resultSet.getDate("me_date_of_launch"));
				menuItem.setCategory(resultSet.getString("me_category"));
				menuItem.setFreeDelivery(resultSet.getString("me_free_delivery").equals("Yes"));

				menuItemList.add(menuItem);
			}
		} catch (SQLException e) {
			System.out.println("SQL Exception occurs");
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("SQL Exception occurs");
			}
		}
		return menuItemList;

	}

	@Override
	public MenuItem getMenuItem(Long menuItemId) {
		Connection connection = ConnectionHandler.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		MenuItem menuItem = null;
		try {
			preparedStatement = connection.prepareStatement(GET_MENU_ITEM);
			preparedStatement.setLong(1, menuItemId);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				menuItem = new MenuItem();
				menuItem.setId(resultSet.getLong("me_id"));
				menuItem.setName(resultSet.getString("me_name"));
				menuItem.setPrice(resultSet.getDouble("me_price"));
				menuItem.setActive(resultSet.getString("me_active").equals("Yes"));
				menuItem.setDateOfLaunch(resultSet.getDate("me_date_of_launch"));
				menuItem.setCategory(resultSet.getString("me_category"));
				menuItem.setFreeDelivery(resultSet.getString("me_free_delivery").equals("Yes"));

			}
		} catch (SQLException e) {
			System.out.println("error");
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("SQL Exception occurs");
			}
		}
		System.out.println(menuItem);
		return menuItem;
	}
}
