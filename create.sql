BEGIN;

CREATE TABLE IF NOT EXISTS eyeColors (
    color varchar(10) PRIMARY KEY
);
INSERT INTO eyeColors VALUES
    ('BLACK'),
    ('YELLOW'),
    ('ORANGE'),
    ('WHITE'),
    ('BROWN');

CREATE TABLE IF NOT EXISTS hairColors (
    color varchar(10) PRIMARY KEY
);
INSERT INTO hairColors VALUES
    ('RED'),
    ('YELLOW'),
    ('WHITE'),
    ('BROWN');

CREATE TABLE IF NOT EXISTS countries (
    county varchar(15) PRIMARY KEY
);
INSERT INTO countries VALUES
    ('CHINA'),
    ('SOUTH_KOREA'),
    ('JAPAN');

CREATE TABLE IF NOT EXISTS types (
    type varchar(10) PRIMARY KEY
);
INSERT INTO types VALUES
    ('VIP'),
    ('USUAL'),
    ('BUDGETARY'),
    ('CHEAP');

CREATE TABLE IF NOT EXISTS tickets (
    key bigint PRIMARY KEY,
    id serial,
    name varchar(50) NOT NULL,
    x float NOT NULL,
    y bigint NOT NULL,
    price float NOT NULL,
    type varchar(10) REFERENCES types,
    birthday timestamp,
    eye varchar(10) REFERENCES eyeColors,
    hair varchar(10) REFERENCES hairColors,
    country varchar(10) REFERENCES countries,
    creation timestamp
);

ALTER TABLE tickets
    ADD CONSTRAINT xy_check
        CHECK (x > -626),
    ADD CONSTRAINT price_check
        CHECK (price > 0);

COMMIT;