CREATE TABLE events
(id BIGSERIAL PRIMARY KEY,
 name VARCHAR(100),
 description VARCHAR(5000),
 game VARCHAR(100),
 dt TIMESTAMP,
 address VARCHAR(200),
 tags VARCHAR(300));
