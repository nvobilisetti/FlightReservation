create database flight_schema;

use flight_schema;

CREATE TABLE flights(
flight_id int primary key auto_increment,
flight_number varchar(50),
flight_name varchar(50),
from_city varchar(50),
to_city varchar(50),
tickets_available int,
price int);

CREATE TABLE booking_info(
booking_id int primary key auto_increment,
first_name varchar(50) ,
last_name varchar(50) ,
mail_id varchar(50) ,
contact long,
booked_tickets int,
flightNumber varchar(50) ,
confirmation varchar(50));

Create table userinfo(
user_id int primary key auto_increment,
user_name varchar(50),
user_password varchar(50));



















