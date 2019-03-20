create table clients (
	id integer PRIMARY KEY AUTOINCREMENT,
	name varchar(255),
	phone_number varchar(255),
	address varchar(255)
);

create table pets (
	id integer PRIMARY KEY AUTOINCREMENT,
	name varchar(255),
	gender varchar(255),
	altered boolean,
	client_id integer,
	FOREIGN KEY (client_id) REFERENCES clients(id)

);


create table users (
	id integer PRIMARY KEY AUTOINCREMENT,
	username varchar(255),
	encoded_password varchar(255),
	role varchar(255)
);

CREATE TABLE appointments (
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	pet_id INTEGER NOT NULL,
	client_id INTEGER NOT NULL,
	reason TEXT(255) NOT NULL,
	appt_time DATETIME NOT NULL,
	duration INTEGER DEFAULT 0 NOT NULL,
	comments TEXT(255)
);

insert into users values (null, 'admin', 'password', 'SUPER_ADMIN');
