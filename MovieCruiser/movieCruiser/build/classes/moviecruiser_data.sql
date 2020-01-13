/* Movies List Datails */

SELECT * FROM moviecruiser.movies;

INSERT INTO `moviecruiser`.`movies` (`mo_id`, `mo_title`, `mo_boxoffice`, `mo_active`, `mo_date_of_launch`, `mo_genre`, `mo_has_teaser`) VALUES ('1', 'Avatar', '2787965087', 'Yes', '2020-01-05', 'Science Fiction', 'Yes');
INSERT INTO `moviecruiser`.`movies` (`mo_id`, `mo_title`, `mo_boxoffice`, `mo_active`, `mo_date_of_launch`, `mo_genre`, `mo_has_teaser`) VALUES ('2', 'The Avengers', '1518812988', 'Yes', '2020-01-06', 'Superhero', 'No');
INSERT INTO `moviecruiser`.`movies` (`mo_id`, `mo_title`, `mo_boxoffice`, `mo_active`, `mo_date_of_launch`, `mo_genre`, `mo_has_teaser`) VALUES ('3', 'Titanic', '2187463944', 'Yes', '2020-01-07', 'Romance', 'No');
INSERT INTO `moviecruiser`.`movies` (`mo_id`, `mo_title`, `mo_boxoffice`, `mo_active`, `mo_date_of_launch`, `mo_genre`, `mo_has_teaser`) VALUES ('4', 'Jurassic World', '1671713208', 'No', '2022-11-12', 'Science Fiction', 'Yes');
INSERT INTO `moviecruiser`.`movies` (`mo_id`, `mo_title`, `mo_boxoffice`, `mo_active`, `mo_date_of_launch`, `mo_genre`, `mo_has_teaser`) VALUES ('5', 'Avengers: End Game', '2750760348', 'Yes', '2023-01-01', 'Superhero', 'Yes');

/* User Datails */
INSERT INTO `moviecruiser`.`user` (`us_id`, `us_name`) VALUES ('1', 'A');
INSERT INTO `moviecruiser`.`user` (`us_id`, `us_name`) VALUES ('2', 'B');

SELECT * FROM moviecruiser.user;

/* Favorites */

INSERT INTO `moviecruiser`.`favorites` (`fs_id`, `fs_us_id`, `fs_mo_id`) VALUES ('1', '1', '1');
INSERT INTO `moviecruiser`.`favorites` (`fs_id`, `fs_us_id`, `fs_mo_id`) VALUES ('2', '1', '3');
INSERT INTO `moviecruiser`.`favorites` (`fs_id`, `fs_us_id`, `fs_mo_id`) VALUES ('3', '1', '5');
INSERT INTO `moviecruiser`.`favorites` (`fs_id`, `fs_us_id`, `fs_mo_id`) VALUES ('4', '2', '2');
INSERT INTO `moviecruiser`.`favorites` (`fs_id`, `fs_us_id`, `fs_mo_id`) VALUES ('5', '2', '4');

SELECT * FROM moviecruiser.favorites;

/*1.	View Movies List Admin */

INSERT INTO `moviecruiser`.`movies` (`mo_id`, `mo_title`, `mo_boxoffice`, `mo_active`, `mo_date_of_launch`, `mo_genre`, `mo_has_teaser`) VALUES ('6', 'Rakchashan', '543216789', 'Yes', '2019-05-05', 'Chyco triller', 'Yes');

SELECT * FROM moviecruiser.movies;

/*2.	View Movies List Customer */

SELECT * FROM moviecruiser.movies where mo_date_of_launch>=(select curdate()) and mo_active='Yes';

/*3.	Edit Movies */

SELECT * FROM moviecruiser.movies 
where mo_id=6;

update moviecruiser.movies
set
mo_title='Charlie',
mo_boxoffice='987654321',
mo_active="no",
mo_date_of_launch='2016-12-22',
mo_genre='Travelling',
mo_has_teaser='Yes'
where mo_id='1';

SELECT * FROM moviecruiser.movies;

/*5.	View Favorites */

use moviecruiser;
truncate table favorites;

SELECT * FROM moviecruiser.favorites;

SELECT mo_title,mo_boxoffice,mo_genre FROM moviecruiser.movies 
inner join moviecruiser.favorites
on fs_mo_id=mo_id where fs_us_id=1;

SELECT count(mo_id) as no_of_Favorites FROM moviecruiser.movies
inner join moviecruiser.favorites
on fs_mo_id=mo_id where fs_us_id=1;

/*6.	Remove from Favorites */

Delete from moviecruiser.favorites
where fs_mo_id=5 and fs_us_id=1;

SELECT mo_title,mo_boxoffice,mo_genre FROM moviecruiser.movies 
inner join moviecruiser.favorites
on fs_mo_id=mo_id where fs_us_id=1;
