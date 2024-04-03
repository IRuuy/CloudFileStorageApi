CREATE TABLE "user"  (
    id VARCHAR PRIMARY KEY,
    name VARCHAR NOT NULL,
    email VARCHAR NOT NULL,
    password VARCHAR NOT NULL
);

CREATE TABLE role (
    id  SERIAL PRIMARY KEY,
    name VARCHAR
);

CREATE TABLE user_role (
    id_user VARCHAR REFERENCES "user"(id),
    id_role INT REFERENCES role(id),
    UNIQUE(id_user, id_role)
);
