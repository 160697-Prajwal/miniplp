create table users (user_id varchar2(4) primary key, password varchar2(15), role varchar2(10),  user_name varchar2(20), mobile_no varchar2(10), phone varchar2(10), address varchar2(30), email varchar2(30));
create table hotel(hotel_id varchar2(4) primary key, city varchar2(20), hotel_name varchar2(20), address varchar2(40), description varchar2(50), avg_rate_per_night number(10,2), phone_no1 varchar2(10), phone_no2 varchar2(10), rating varchar2(1), email varchar2(30), fax varchar2(10));
create sequence hotel_id start with 1234;
create table room_table(hotel_id varchar2(4) references hotel(hotel_id) on delete cascade, room_id varchar2(4) primary key, room_no varchar2(3), room_type varchar2(20), per_night_rate number(6,2), availability varchar2(5)); 
create table booking_details (hotel_id varchar2(4) references hotel(hotel_id),booking_id varchar2(4) primary key, room_id varchar2(4) references room_table(room_id),user_name varchar2(20), user_id varchar2(4) references users(user_id), booked_from date, booked_to date, no_of_adults number, no_of_children number, amount number(6,2), status varchar2(20));
create sequence booking_id start with 1871; 
create sequence user_id start with 001;
create sequence room_id start with 121;