CREATE TABLE IF NOT EXISTS USER
(
    user_id          VARCHAR(36)  NOT NULL PRIMARY KEY,
    user_name        VARCHAR(250) NOT NULL,
    user_description VARCHAR(250),
    user_status      VARCHAR(250),
    gender           VARCHAR(250)
);

CREATE TABLE IF NOT EXISTS USER_POSITION
(
    user_id            VARCHAR(36) NOT NULL PRIMARY KEY,
    latitude           DOUBLE,
    longitude          DOUBLE,
    position_timestamp DOUBLE      NOT NULL
);
