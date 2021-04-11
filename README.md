# HotelBooking

Used h2 database.
 
At the start of the application it will insert below records<br/>
insert into hotel (hotel_id, hotel_name, price, room_no, status, user_id) values (1, 'Ajanta', 100, '101', null, null);<br/>
insert into hotel (hotel_id, hotel_name, price, room_no, status, user_id) values (2, 'Lemon Tree', 200, '102', null, null);<br/>

insert into user (user_id, bonus_point, first_name, last_name) values (1, 200, 'Shilanand', 'Kharat');
insert into user (user_id, bonus_point, first_name, last_name) values (2, 200, 'Java', 'Developer');

# BookingController
Used to book a hotel for user
API
POST /hotels/book/{userId}

# HotelCotroller
Used to create hotel details
APIs
POST /hotels - creates new hotel
GET /hotels - get all hotels
GET /hotels/{hotelId} - get specific hotel
PATCH /hotels/{hotelId} - update hotel

# UserController
Used to create user details.
APIs

POST /users - create user
GET /users - get all users
GET /users/{userId} - get specific user
PATCH /users/{userId} - Update a specific user
