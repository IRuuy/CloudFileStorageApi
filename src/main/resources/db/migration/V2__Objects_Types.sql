CREATE TABLE object_type (
    id SERIAL PRIMARY KEY,
    name VARCHAR
);

CREATE TABLE object (
    id VARCHAR PRIMARY KEY,
    parent_id VARCHAR REFERENCES object(id),
    object_type_id INTEGER REFERENCES object_type(id),
    owner_id VARCHAR REFERENCES "user"(id),
    name VARCHAR,
    created_at TIMESTAMP,
    modified_at TIMESTAMP,
    size INT
);

INSERT INTO object_type (name)
VALUES ('file'), ('folder');