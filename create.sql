BEGIN;

CREATE TABLE IF NOT EXISTS users (
    name varchar(50) PRIMARY KEY,
    password bytea NOT NULL
);

CREATE TABLE IF NOT EXISTS tickets (
    key bigint PRIMARY KEY,
    id serial,
    name varchar(50) NOT NULL,
    x float NOT NULL,
    y bigint NOT NULL,
    price float NOT NULL,
    type varchar(10),
    birthday timestamp,
    eye varchar(10),
    hair varchar(10),
    country varchar(15),
    creation timestamp,
    client varchar(50) REFERENCES users
);

ALTER TABLE tickets
    ADD CONSTRAINT xy_check
        CHECK (x > -626),
    ADD CONSTRAINT price_check
        CHECK (price > 0),
    ADD CONSTRAINT eye_check
        CHECK (eye IN ('BLACK', 'YELLOW', 'ORANGE', 'WHITE', 'BROWN')),
    ADD CONSTRAINT hair_check
        CHECK (hair IN ('RED', 'YELLOW', 'WHITE', 'BROWN')),
    ADD CONSTRAINT type_check
        CHECK (type IN ('VIP', 'USUAL', 'BUDGETARY', 'CHEAP')),
    ADD CONSTRAINT country_check
        CHECK (country IN ('CHINA', 'SOUTH_KOREA', 'JAPAN'));

COMMIT;