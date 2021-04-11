# HotelBooking
Problem Statement :

There is a hotel booking website.

Expose an API to book a room based on user bonus points.

 

Conditions:
If User has 'n' bonus points and Price to book the hotel is 'n’ ,Status of room changes to "BOOKED".

If User has 'n' bonus points and Price to book the hotel is greater than 'n’ , Status of room changes to "PENDING APPROVAL".

Any changes to user bonus is tracked in the system.

# Swagger Url
http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config

Used h2 database.
 
At the start of the application it will insert below records<br/>
insert into hotel (hotel_id, hotel_name, price, room_no, status, user_id) values (1, 'Ajanta', 100, '101', null, null);<br/>
insert into hotel (hotel_id, hotel_name, price, room_no, status, user_id) values (2, 'Lemon Tree', 200, '102', null, null);<br/>

insert into user (user_id, bonus_point, first_name, last_name) values (1, 200, 'Shilanand', 'Kharat');<br/>
insert into user (user_id, bonus_point, first_name, last_name) values (2, 200, 'Java', 'Developer');<br/>

# BookingController
Used to book a hotel for user<br/>
API<br/>
POST /hotels/book/{userId}<br/>

# HotelCotroller
Used to create hotel details<br/>
APIs<br/>
POST /hotels - creates new hotel<br/>
GET /hotels - get all hotels<br/>
GET /hotels/{hotelId} - get specific hotel<br/>
PATCH /hotels/{hotelId} - update hotel<br/>

# UserController
Used to create user details.<br/>
APIs<br/>

POST /users - create user<br/>
GET /users - get all users<br/>
GET /users/{userId} - get specific user<br/>
PATCH /users/{userId} - Update a specific user<br/>
