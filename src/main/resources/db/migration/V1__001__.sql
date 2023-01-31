CREATE TABLE roles (
    role VARCHAR(255) primary key
);

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL DEFAULT 'USER',
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL,
    FOREIGN KEY (role)
        REFERENCES roles (role)
);


CREATE TABLE countries (
    country VARCHAR(255) PRIMARY KEY
);

CREATE TABLE cities (
    id INT PRIMARY KEY AUTO_INCREMENT,
    city_name VARCHAR(255) NOT NULL,
    country_name VARCHAR(255) NOT NULL,
    FOREIGN KEY (country_name)
        REFERENCES countries (country)
);

CREATE TABLE hotels (
    id INT PRIMARY KEY AUTO_INCREMENT,
    hotel_name VARCHAR(255) NOT NULL,
    city_id INT NOT NULL,
    FOREIGN KEY (city_id)
        REFERENCES cities (id)
);

CREATE TABLE facilities (
    facility VARCHAR(255) PRIMARY KEY
);

CREATE TABLE hotels_facilities (
    hotel_id INT NOT NULL,
    facility VARCHAR(255) NOT NULL,
    FOREIGN KEY (hotel_id)
        REFERENCES hotels (id),
    FOREIGN KEY (facility)
        REFERENCES facilities (facility),
    CONSTRAINT no_duplicates UNIQUE(hotel_id, facility)
);

CREATE TABLE rooms (
    id INT PRIMARY KEY AUTO_INCREMENT,
    hotel_id INT NOT NULL,
    capacity INT NOT NULL,
    price DOUBLE NOT NULL,
    FOREIGN KEY (hotel_id)
        REFERENCES hotels (id)
);

CREATE TABLE reservations (
    id INT PRIMARY KEY AUTO_INCREMENT,
    room_id INT NOT NULL,
    user_id INT NOT NULL,
    full_price DOUBLE NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    FOREIGN KEY (room_id)
        REFERENCES rooms (id),
    FOREIGN KEY (user_id)
        REFERENCES users (id)
);

insert into roles (role)
values ("ADMIN"), ("USER");

insert into users (email, password, role, first_name, last_name, phone)
value ("admin@gmail.com", "1a1dc91c907325c69271ddf0c944bc72", "ADMIN", "Admin", "Admin", "0123456");



