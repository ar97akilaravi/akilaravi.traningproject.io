/* Menu Item Table Details */

SELECT * FROM truyum.menu_item;

INSERT INTO `truyum`.`menu_item` (`me_id`, `me_name`, `me_price`, `me_active`, `me_date_of_launch`, `me_category`, `me_free_delivery`) VALUES ('1', 'Sandwich', '99.00', 'Yes', '2017-03-15', 'Main Course', 'Yes');
INSERT INTO `truyum`.`menu_item` (`me_id`, `me_name`, `me_price`, `me_active`, `me_date_of_launch`, `me_category`, `me_free_delivery`) VALUES ('2', 'Burger', '129.00', 'Yes', '2017-12-23', 'Main Course', 'No');
INSERT INTO `truyum`.`menu_item` (`me_id`, `me_name`, `me_price`, `me_active`, `me_date_of_launch`, `me_category`, `me_free_delivery`) VALUES ('3', 'Pizza', '149.00', 'Yes', '2017-08-21', 'Main Course', 'No');
INSERT INTO `truyum`.`menu_item` (`me_id`, `me_name`, `me_price`, `me_active`, `me_date_of_launch`, `me_category`, `me_free_delivery`) VALUES ('4', 'French Fries', '57.00', 'No', '2017-07-02', 'Starteers', 'Yes');
INSERT INTO `truyum`.`menu_item` (`me_id`, `me_name`, `me_price`, `me_active`, `me_date_of_launch`, `me_category`, `me_free_delivery`) VALUES ('5', 'Chocolate Brownie', '32.00', 'Yes', '2022-11-02', 'Dessert', 'Yes');

/* User Table Details */

SELECT * FROM truyum.user;

INSERT INTO `truyum`.`user` (`us_id`, `us_name`) VALUES ('1001', 'Akila');
INSERT INTO `truyum`.`user` (`us_id`, `us_name`) VALUES ('1002', 'Sujitha');

/* Cart Table Details */

SELECT * FROM truyum.cart;

INSERT INTO `truyum`.`cart` (`ct_id`, `ct_us_id`, `ct_me_id`) VALUES ('1', '1', '1');
INSERT INTO `truyum`.`cart` (`ct_id`, `ct_us_id`, `ct_me_id`) VALUES ('2', '1', '3');
INSERT INTO `truyum`.`cart` (`ct_id`, `ct_us_id`, `ct_me_id`) VALUES ('3', '1', '5');

/*1.	View Menu Item List Admin */


INSERT INTO `truyum`.`menu_item` (`me_id`, `me_name`, `me_price`, `me_active`, `me_date_of_launch`, `me_category`, `me_free_delivery`) VALUES ('6', 'Chocolate Icecreame', '50.00', 'Yes', '2019-12-03', 'Dessert', 'No');

SELECT * FROM truyum.menu_item;

/*2.	View Menu Item List Customer (TYUC002)*/

select * from truyum.menu_item where me_date_of_launch>(select curdate()) and me_active='yes';

/*3.	Edit Menu Item (TYUC003) */

select * from truyum.menu_item
where me_id=2;

update truyum.menu_item
set
me_name='Biriyani',
me_price='150.00',
me_active='Yes',
me_date_of_launch='2020-01-03',
me_category='Main Course',
me_free_delivery='No'
where me_id='1';

SELECT * FROM truyum.menu_item;

/*4.	Add to Cart (TYUC004)*/

use truyum;
truncate table cart;

INSERT INTO `truyum`.`cart` (`ct_id`, `ct_us_id`, `ct_me_id`) VALUES ('1', '1', '1');
INSERT INTO `truyum`.`cart` (`ct_id`, `ct_us_id`, `ct_me_id`) VALUES ('2', '1', '3');
INSERT INTO `truyum`.`cart` (`ct_id`, `ct_us_id`, `ct_me_id`) VALUES ('3', '1', '5');
INSERT INTO `truyum`.`cart` (`ct_id`, `ct_us_id`, `ct_me_id`) VALUES ('4', '2', '4');

SELECT * FROM truyum.cart;

/*5.	View Cart (TYUC005)*/

select me_name,me_price,me_active,me_date_of_launch,me_category,me_free_delivery from truyum.menu_item
inner join truyum.cart
on ct_me_id=me_id where ct_us_id=2;

select sum(me_price) as total from truyum.menu_item 
inner join truyum.cart
on ct_me_id=me_id where ct_us_id=1;

/*6.	Remove Item from Cart (TYUC006)*/

DELETE FROM truyum.cart
WHERE ct_me_id=4 and ct_us_id=2 limit 1;

select me_name,me_price,me_active,me_date_of_launch,me_category,me_free_delivery from truyum.menu_item
inner join truyum.cart
on ct_me_id=me_id where ct_us_id=2;
