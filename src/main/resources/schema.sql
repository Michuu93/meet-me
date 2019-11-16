CREATE TABLE IF NOT EXISTS USER
(
    user_id          VARCHAR(36)  NOT NULL PRIMARY KEY,
    user_name        VARCHAR(250) NOT NULL,
    user_description VARCHAR(250),
    user_status      VARCHAR(250),
    latitude         DOUBLE,
    longitude        DOUBLE,
    last_activity    NUMBER       NOT NULL
);
