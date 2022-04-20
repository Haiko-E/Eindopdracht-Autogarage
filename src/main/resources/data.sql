INSERT INTO customers (id, firstname, lastname, email, street, house_number, zipcode, city) VALUES (1001, 'harry', 'henkst', 'harry@henkst.com', 'mooieweg', 6, '5435ws', 'Oss');
INSERT INTO customers (id, firstname, lastname, email, street, house_number, zipcode, city) VALUES (1002, 'frits', 'jansen', 'frits@jansen.com', 'kiviet', 25, '5254dr', 'Oss');

INSERT INTO cars(id,brand, type, license_plate, customer_id) VALUES (1003, 'Seat', 'Leon', '3-srp-wx', '1001');
INSERT INTO cars(id,brand, type, license_plate, customer_id) VALUES (1004, 'Ford', 'Focus', '22-ll-sp', '1002');

INSERT INTO employees(id,username, password, enabled, lastname, role) VALUES (1005, 'Hans', '$2y$10$iuI2WO1n2GEQxQrME8/bp.Q6gp5aK2rYz/csUJFCABLZk7LRDIQcK', 'true' , 'de Sleutelaar', 'MECHANIC');
INSERT INTO employees(id,username, password, enabled, lastname, role) VALUES (1006, 'Jan', '$2y$10$XO2b/1YUiMcFdbRHhxl54OZV.BWPHp/FqVWlPipceAncU84JKahOy', 'true' , 'de Admin', 'ADMIN');
INSERT INTO employees(id,username, password, enabled, lastname, role) VALUES (1007, 'ADMIN', '$2y$10$anxu85ksALH4vrX3wEBY/ed3BoAc/nlRatMe.ptGzkFBFLU2jMxhe', 'true' , 'de Admin', 'SUPERUSER');

INSERT INTO appointments(id, maintenance_details, description, date, customer_id, car_id) VALUES (1008, 'APK keuring', 'even bellen voor grote zaken' , '2022-05-07T12:01:05.767+00:00', 1001, 1003 );

INSERT INTO parts(id,in_stock, name, price ) VALUES (2001, 4, 'Bougie', 12.50);
INSERT INTO parts(id, in_stock, name, price) VALUES (2002, 12, 'Ruitenwisser Bosch', 4.50);

INSERT INTO repair_operations(id, name, price) VALUES (3001, 'APK', 40);
INSERT INTO repair_operations(id, name, price) VALUES (3002, 'Roetmeting', 40);

INSERT INTO maintenances(id, car_id) VALUES (4001, 1003);
INSERT INTO maintenances(id, car_id) VALUES (4002, 1004);

INSERT INTO maintenance_items(id, quantity, maintenance_id, part_id, repair_operation_id)VALUES (5001, 2, 4001, 2001, null);
INSERT INTO maintenance_items(id, quantity, maintenance_id, part_id, repair_operation_id)VALUES (5002, 1, 4001, null, 3001);
INSERT INTO maintenance_items(id, quantity, maintenance_id, part_id, repair_operation_id)VALUES (5003, 1, 4001, null, 3002);

INSERT INTO invoices(id, is_payed, total_price, maintenance_id, customer_id) VALUES (6001, 'false', 106, 4001, 1001);

