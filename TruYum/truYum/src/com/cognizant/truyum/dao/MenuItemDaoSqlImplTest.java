package com.cognizant.truyum.dao;

import java.io.IOException;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

public class MenuItemDaoSqlImplTest {
	public static void display(List<MenuItem> menuItemList) {
		DecimalFormat df = new DecimalFormat("##,###.00");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String active, freedelivery;
		System.out.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", "ID", "NAME", "PRICE", "ACTIVE", "DATE_OF_LAUNCH",
				"CATEGORY", " FREE DELIVERY");
		for (MenuItem menuItem : menuItemList) {
			if (menuItem.getActive() == true) {
				active = "Yes";
			} else {
				active = "No";
			}
			if (menuItem.getFreeDelivery() == true) {
				freedelivery = "Yes";
			} else {
				freedelivery = "No";
			}
			System.out.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", menuItem.getId(), menuItem.getName(),
					df.format(menuItem.getPrice()), active, sdf.format(menuItem.getDateOfLaunch()),
					menuItem.getCategory(), freedelivery);
		}
	}

	public static void testGetMenuItemListAdmin() {
		ArrayList<MenuItem> menuItemListAdmin = new MenuItemDaoSqlImpl().getMenuItemListAdmin();
		System.out.println("ADMIN");
		display(menuItemListAdmin);
	}

	public static void testModifyMenuItem() throws NumberFormatException, IOException {
		MenuItem menuItem = new MenuItem(1L, "ICECREAME", 100.00d, true, new DateUtil().convertToDate("12/12/2019"),
				"dessert", false);

		MenuItemDaoSqlImpl menuItemDaoSqlImpl = new MenuItemDaoSqlImpl();

		menuItemDaoSqlImpl.modifyMenuItem(menuItem);
	}

	public static void testGetMenuItemListCustomer() {
		List<MenuItem> menuItemListCustomer = new MenuItemDaoSqlImpl().getMenuItemListCustomer();
		System.out.println("Customer");
		display(menuItemListCustomer);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		Connection x = ConnectionHandler.getConnection();
		System.out.println(x);

		testGetMenuItemListAdmin();

		testGetMenuItemListCustomer();

		testModifyMenuItem();

		testGetMenuItemListAdmin();
	}
}
