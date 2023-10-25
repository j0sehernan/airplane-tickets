CREATE TABLE customers
(
    id        SERIAL PRIMARY KEY,
    full_name VARCHAR(200)        NOT NULL,
    email     VARCHAR(100) UNIQUE NOT NULL,
    phone     VARCHAR(20)
);

CREATE TABLE airports
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(200) NOT NULL,
    location VARCHAR(200),
    code     VARCHAR(100)
);

CREATE TABLE flight_itineraries
(
    id                   SERIAL PRIMARY KEY,
    departure_airport_id INTEGER NOT NULL,
    arrival_airport_id   INTEGER NOT NULL,
    departure_time       TIMESTAMP,
    arrival_time         TIMESTAMP,
    price                DECIMAL(10, 2),
    FOREIGN KEY (departure_airport_id) REFERENCES airports (id),
    FOREIGN KEY (arrival_airport_id) REFERENCES airports (id)
);

CREATE TABLE reservations
(
    id           SERIAL PRIMARY KEY,
    customer_id  INTEGER NOT NULL,
    itinerary_id INTEGER NOT NULL,
    status       VARCHAR(50),
    date         TIMESTAMP,
    price        DECIMAL(10, 2),
    FOREIGN KEY (customer_id) REFERENCES customers (id),
    FOREIGN KEY (itinerary_id) REFERENCES flight_itineraries (id)
);