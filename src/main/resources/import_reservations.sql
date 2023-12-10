-- Assuming 'reservations' is the name of your table
-- Assuming 'flight_id' and 'passenger_id' are the foreign key columns in the 'reservations' table

INSERT INTO reservations(id, flight_id, passenger_id)
VALUES(NEXTVAL('reservation_sequence_in_database'), '1', '1');

INSERT INTO reservations(id, flight_id, passenger_id)
VALUES(NEXTVAL('reservation_sequence_in_database'), '1', '1');