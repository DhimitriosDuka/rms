CREATE TABLE ingredient(
	id SERIAL PRIMARY KEY,
	name CHARACTER VARYING(50) NOT NULL,
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
	name CHARACTER VARYING(50) NOT NULL,
	price NUMERIC NOT NULL,
	michelin_stars INT,
	course CHARACTER VARYING(20),
	description CHARACTER VARYING(50),
	type CHARACTER VARYING(20),
	category CHARACTER VARYING(20)
);

CREATE TABLE menu_item_ingredient (
	amount INT NOT NULL,
	ingredient_id BIGINT REFERENCES ingredient(id),
	dish_id BIGINT REFERENCES menu_item(id),
	PRIMARY KEY(ingredient_id, dish_id)
);

CREATE TABLE users (
	id SERIAL PRIMARY KEY,
	first_name CHARACTER VARYING(20) NOT NULL,
	last_name CHARACTER VARYING(20) NOT NULL,
	user_name CHARACTER VARYING(20) NOT NULL,
	email CHARACTER VARYING(40) NOT NULL,
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
	delivery_guy_id BIGINT REFERENCES users(id)
);

CREATE TABLE orders_menu_items (
	amount INT NOT NULL,
	ingredient_id BIGINT REFERENCES ingredient(id) ,
	dish_id BIGINT REFERENCES menu_item(id),
	PRIMARY KEY(ingredient_id, dish_id)
);



