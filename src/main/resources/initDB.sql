DROP TABLE user_role IF EXISTS;
DROP TABLE dish IF EXISTS;
DROP TABLE users IF EXISTS;
DROP SEQUENCE global_seq IF EXISTS;

CREATE SEQUENCE GLOBAL_SEQ AS INTEGER START WITH 100000;

CREATE TABLE users
(
    id         INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    name       VARCHAR(255)            NOT NULL,
    email      VARCHAR(255)            NOT NULL,
    password   VARCHAR(255)            NOT NULL,
    registered TIMESTAMP DEFAULT now() NOT NULL,
    enabled    BOOLEAN   DEFAULT TRUE  NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx
    ON USERS (email);

CREATE TABLE user_role
(
    user_id INTEGER      NOT NULL,
    role    VARCHAR(255) NOT NULL,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE
);

CREATE TABLE restaurants
(
    id   INTEGER      NOT NULL,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE dish
(
    id          INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    price       INT          NOT NULL,
    registered  TIMESTAMP    NOT NULL,
    restaurant_id     INTEGER      NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES USERS (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX dish_unique_user_datetime_idx
    ON dish (restaurant_id, registered)