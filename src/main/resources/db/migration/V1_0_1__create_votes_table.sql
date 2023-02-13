CREATE SEQUENCE vote_seq START WITH 1;

CREATE TABLE votes
(
    id       BIGINT PRIMARY KEY AUTO_INCREMENT,
    likes    INT,
    dislikes INT
);