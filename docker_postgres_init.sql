CREATE USER postgres WITH PASSWORD 'qwerty1' CREATEDB;

CREATE DATABASE core-banking-service WITH
OWNER = postgres
ENCODING = 'UTF8'
LC_COLLATE = 'en_US.utf8'
LC_CTYPE = 'en_US.utf8'
TABLESPACE = pg_default
CONNECTION LIMIT = -1;