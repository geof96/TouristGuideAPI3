-- Create tables:

-- City table
CREATE TABLE if not exists CITY
(
    CITY_ID INT auto_increment NOT null PRIMARY KEY,
    CITY    VARCHAR(20)        NOT NULL UNIQUE
);

-- Tags table
CREATE TABLE if not exists tags
(
    TAG_ID INT auto_increment NOT null PRIMARY KEY,
    TAG    varchar(15)        NOT NULL UNIQUE
);

-- Attractions table
CREATE TABLE if not exists Attractions
(
    ID          int primary key auto_increment not null,
    NAME        varchar(20)                    not null unique,
    DESCRIPTION VARCHAR(100),
    CITY_ID     INT,
    TAG_ID      INT,
    FOREIGN KEY (CITY_ID) REFERENCES CITY (CITY_ID),
    FOREIGN KEY (TAG_ID) REFERENCES tags (TAG_ID)
);

-- Insert data into tables:

-- Tags:
INSERT INTO tags (TAG)
VALUES ('Gratis');
INSERT INTO tags (TAG)
VALUES ('Museum');
INSERT INTO tags (TAG)
VALUES ('Park');

-- Cities:
INSERT INTO CITY (CITY)
VALUES ('Paris');
INSERT INTO CITY (CITY)
VALUES ('Rome');
INSERT INTO CITY (CITY)
VALUES ('New York');
INSERT INTO CITY (CITY)
VALUES ('Tokyo');

-- Attractions:
INSERT INTO Attractions (NAME, DESCRIPTION, CITY_ID, TAG_ID)
VALUES ('Eiffel Tower', 'Iconic iron lattice tower', 1, 1);
INSERT INTO Attractions (NAME, DESCRIPTION, CITY_ID, TAG_ID)
VALUES ('Louvre Museum', 'World''s largest art museum', 1, 2);
INSERT INTO Attractions (NAME, DESCRIPTION, CITY_ID, TAG_ID)
VALUES ('Colosseum', 'Ancient amphitheater', 2, 1);
INSERT INTO Attractions (NAME, DESCRIPTION, CITY_ID, TAG_ID)
VALUES ('Vatican City', 'Papal enclave and world''s smallest state', 2, 3);