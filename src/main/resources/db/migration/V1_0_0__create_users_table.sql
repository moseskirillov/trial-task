CREATE SEQUENCE user_seq START WITH 1;

CREATE TABLE users
(
    id               BIGINT PRIMARY KEY AUTO_INCREMENT,
    name             VARCHAR(255),
    email            VARCHAR(255) UNIQUE,
    password         VARCHAR(255),
    date_of_creation TIMESTAMP
);