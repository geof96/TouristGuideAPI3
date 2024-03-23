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
VALUES ('Historic');

INSERT INTO tags (TAG)
VALUES ('Nature');

INSERT INTO tags (TAG)
VALUES ('Art');


-- Cities:
INSERT INTO CITY (CITY)
VALUES ('London');

INSERT INTO CITY (CITY)
VALUES ('Barcelona');

INSERT INTO CITY (CITY)
VALUES ('Sydney');

INSERT INTO CITY (CITY)
VALUES ('Rio de Janeiro');


-- Attractions:
INSERT INTO Attractions (NAME, DESCRIPTION, CITY_ID, TAG_ID)
VALUES ('Big Ben', 'Iconic clock tower', 1, 1);

INSERT INTO Attractions (NAME, DESCRIPTION, CITY_ID, TAG_ID)
VALUES ('Sagrada Familia', 'Breathtaking basilica', 2, 2);

INSERT INTO Attractions (NAME, DESCRIPTION, CITY_ID, TAG_ID)
VALUES ('Sydney Opera House', 'World-renowned performing arts center', 3, 3);

INSERT INTO Attractions (NAME, DESCRIPTION, CITY_ID, TAG_ID)
VALUES ('Christ the Redeemer', 'Famous statue atop Corcovado mountain', 4, 1);
