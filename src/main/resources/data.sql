INSERT INTO customers (id, firstname, lastname, email, street, house_number, zipcode, city) VALUES (1001, 'harry', 'henkst', 'harry@henkst.com', 'mooieweg', 6, '5435ws', 'Oss')
INSERT INTO cars(id,brand, type, license_plate, customer_id) VALUES (1002, 'Seat', 'Leon', '3-srp-wx', '1001')
INSERT INTO employees(id,username, password, enabled, lastname, role) VALUES (1003, 'Hans', '$2y$10$iuI2WO1n2GEQxQrME8/bp.Q6gp5aK2rYz/csUJFCABLZk7LRDIQcK', 'true' , 'de Sleutelaar', 'MECHANIC')
INSERT INTO appointments(id, maintenance_details, description, date, customer_id, car_id) VALUES (1004, 'APK keuring', 'even bellen voor grote zaken' , '2022-05-07T12:01:05.767+00:00', 1001, 1002 )