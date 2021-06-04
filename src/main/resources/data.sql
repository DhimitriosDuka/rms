-- Ingredient data

INSERT INTO ingredient (name, calories, amount, unit, fat, cholesterol, sodium, protein, sugar, food_group) VALUES ('Cucumber', 900, 100, 1, 2.111, 0.2, 0.2, 2.2, 1, 1);
INSERT INTO ingredient (name, calories, amount, unit, fat, cholesterol, sodium, protein, sugar, food_group) VALUES ('Onion', 900, 100, 1, 2.111, 0.2, 0.2, 2.2, 1, 1);
INSERT INTO ingredient (name, calories, amount, unit, fat, cholesterol, sodium, protein, sugar, food_group) VALUES ('Eggplant', 900, 100, 1, 2.111, 0.2, 0.2, 2.2, 1, 1);
INSERT INTO ingredient (name, calories, amount, unit, fat, cholesterol, sodium, protein, sugar, food_group) VALUES ('Cabbage', 900, 100, 1, 2.111, 0.2, 0.2, 2.2, 1, 1);
INSERT INTO ingredient (name, calories, amount, unit, fat, cholesterol, sodium, protein, sugar, food_group) VALUES ('Carrot', 900, 100, 1, 2.111, 0.2, 0.2, 2.2, 1, 1);

-- User data
INSERT INTO users(first_name, last_name, user_name, email, password, telephone_number, address, role, created_at) VALUES ('Dhimitrios', 'Duka', 'dduka', 'dhimitrios.duka@fti.edu.al', 'password123','0671111111', 'Tirane', 0, '2021-05-13');
INSERT INTO users(first_name, last_name, user_name, email, password, telephone_number, address, role, created_at) VALUES ('Delivery', 'Delivery', 'delivery', 'delivery@gmail.com', 'password123','0671111111', 'Tirane', 2, '2021-05-13');

-- Menu Item
INSERT INTO menu_item(name, price, michelin_stars, course, description, type, category, currency, calories) VALUES ('Cesar Salad', 17.99, 2, 0, ' ', 0, 1, 1, 450);
INSERT INTO menu_item(name, price, michelin_stars, course, description, type, category, currency, calories) VALUES ('Sandwich', 17.99, 2, 0, ' ', 0, 1, 1, 450);
INSERT INTO menu_item(name, price, michelin_stars, course, description, type, category, currency, calories) VALUES ('Fried Rice', 17.99, 2, 0, ' ', 0, 1, 1, 450);

-- Menu Item Ingredient
INSERT INTO menu_item_ingredient(amount, ingredient_id, menu_item_id) VALUES (100, 1, 2);
INSERT INTO menu_item_ingredient(amount, ingredient_id, menu_item_id) VALUES (50, 3, 2);
INSERT INTO menu_item_ingredient(amount, ingredient_id, menu_item_id) VALUES (75, 2, 2);
INSERT INTO menu_item_ingredient(amount, ingredient_id, menu_item_id) VALUES (10, 4, 2);

-- Order
INSERT INTO orders(status, created_at, delivery_time, costumer_id, delivery_guy_id, phone_number, address, total_price, total_calories) VALUES (0, '2021-05-13', '2021-05-14', 1, 2, '0671111111', 'Tirane', 45.99, 1200);
INSERT INTO orders(status, created_at, delivery_time, costumer_id, delivery_guy_id, phone_number, address, total_price, total_calories) VALUES (0, '2021-05-16', '2021-05-17', 1, 2, '0672222222', 'Tirane', 32.99, 1400);

-- Order Menu Item
INSERT INTO orders_menu_items(amount, menu_item_id, order_id, note) VALUES (2, 1, 1, ' ');
INSERT INTO orders_menu_items(amount, menu_item_id, order_id, note) VALUES (3, 3, 1, ' ');
INSERT INTO orders_menu_items(amount, menu_item_id, order_id, note) VALUES (4, 2, 2, ' ');