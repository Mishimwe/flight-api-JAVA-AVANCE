-- Assuming 'flight' is the name of your table and 'flight_sequence' is the sequence for ID generation
INSERT INTO flights(id, number, origin, destination, departure_date, departure_time, arrival_date, arrival_time, plane_id)
VALUES(
    NEXTVAL('flight_sequence_in_database'),
    'FL123',
    'Washington',
    'Paris',
    '2023-12-01',
    '12:00:00',
    '2023-12-01',
    '15:30:00',
    1
);

INSERT INTO flights(id, number, origin, destination, departure_date, departure_time, arrival_date, arrival_time, plane_id)
VALUES(
    NEXTVAL('flight_sequence_in_database'),
    'BXL12',
    'Dubai',
    'Bruxelles',
    '2023-12-01',
    '12:00:00',
    '2023-12-01',
    '15:38:00',
    1
);