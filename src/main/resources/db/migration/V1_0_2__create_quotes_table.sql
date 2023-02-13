CREATE SEQUENCE quote_seq START WITH 1;

CREATE TABLE quotes
(
    id               BIGINT PRIMARY KEY AUTO_INCREMENT,
    content          VARCHAR(255),
    date_of_creation TIMESTAMP,
    date_of_update   TIMESTAMP,
    user_id          BIGINT,
    votes_id         BIGINT,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (votes_id) REFERENCES votes (id)
);