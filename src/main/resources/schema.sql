CREATE TABLE ingredient(
	id SERIAL PRIMARY KEY,
	name CHARACTER VARYING(50) NOT NULL UNIQUE ,
	calories NUMERIC NOT NULL,
	amount NUMERIC NOT NULL,
	unit CHARACTER VARYING(10),
	fat NUMERIC NOT NULL,
	cholesterol NUMERIC NOT NULL,
	sodium NUMERIC NOT NULL,
	protein NUMERIC NOT NULL,
	sugar NUMERIC NOT NULL,
	food_group CHARACTER VARYING(20)
);

CREATE TABLE menu_item (
	id SERIAL PRIMARY KEY,
	name CHARACTER VARYING(50) NOT NULL UNIQUE ,
	price NUMERIC NOT NULL,
	michelin_stars INT,
	course CHARACTER VARYING(20),
	description CHARACTER VARYING(50),
	type CHARACTER VARYING(20),
	category CHARACTER VARYING(20),
	currency CHARACTER VARYING(20) NOT NULL ,
	calories NUMERIC NOT NULL
);

CREATE TABLE menu_item_ingredient (
	amount INT NOT NULL,
	ingredient_id BIGINT REFERENCES ingredient(id),
	menu_item_id BIGINT REFERENCES menu_item(id),
	PRIMARY KEY(ingredient_id, menu_item_id)
);

CREATE TABLE users (
	id SERIAL PRIMARY KEY,
	first_name CHARACTER VARYING(20) NOT NULL,
	last_name CHARACTER VARYING(20) NOT NULL,
	user_name CHARACTER VARYING(20) NOT NULL UNIQUE ,
	email CHARACTER VARYING(40) NOT NULL UNIQUE,
	password CHARACTER VARYING(255) NOT NULL,
	telephone_number CHARACTER VARYING(20),
	address CHARACTER VARYING(50),
	role CHARACTER VARYING(15) NOT NULL,
	created_at DATE NOT NULL,
	updated_at DATE
);

CREATE TABLE orders (
	id SERIAL PRIMARY KEY,
	status CHARACTER VARYING(20),
	total_price NUMERIC NOT NULL,
	total_calories NUMERIC NOT NULL,
	created_at DATE NOT NULL,
	delivery_time DATE NOT NULL,
	costumer_id BIGINT REFERENCES users(id),
	delivery_guy_id BIGINT REFERENCES users(id),
	phone_number CHARACTER VARYING(20) NOT NULL,
	address CHARACTER VARYING(20) NOT NULL
);

CREATE TABLE orders_menu_items (
	amount INT NOT NULL,
	menu_item_id BIGINT REFERENCES menu_item(id) ,
	order_id BIGINT REFERENCES orders(id),
	note VARCHAR(50),
	PRIMARY KEY(menu_item_id, order_id)
);

CREATE TABLE schedule(
     id SERIAL PRIMARY KEY,
     work_date DATE NOT NULL,
     start_work_hour TIMESTAMP WITH TIME ZONE NOT NULL,
     end_work_hour TIMESTAMP WITH TIME ZONE NOT NULL,
     user_id BIGINT REFERENCES users(id)
);
