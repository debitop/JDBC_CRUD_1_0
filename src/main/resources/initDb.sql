DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq
  START 1000;

CREATE TABLE users (
  id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  firstName  VARCHAR NOT NULL,
  lastName VARCHAR NOT NULL,
  email      VARCHAR NOT NULL,
  dob        DATE
)