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

CREATE TABLE users_roles (
    user_id VARCHAR REFERENCES "user"(id),
    role_id INT REFERENCES role(id),
    UNIQUE(user_id, role_id)
);
