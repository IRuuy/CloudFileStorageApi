CREATE TABLE file_object_type (
    id SERIAL PRIMARY KEY,
    name VARCHAR
);

CREATE TABLE file_object (
    id VARCHAR PRIMARY KEY,
    parent_id VARCHAR,
    type_id INTEGER,
    owner_id VARCHAR,
    name VARCHAR,
    created_at TIMESTAMP DEFAULT NOW(),
    modified_at TIMESTAMP DEFAULT NOW(),
    size INT DEFAULT 0,

    CONSTRAINT fk_parent_file_object FOREIGN KEY (parent_id) REFERENCES file_object(id) ON DELETE CASCADE,
    CONSTRAINT fk_type_file_object FOREIGN KEY (type_id) REFERENCES file_object_type(id),
    CONSTRAINT fk_owner_file_object FOREIGN KEY (owner_id) REFERENCES "user"(id)
);

INSERT INTO file_object_type (name)
VALUES ('file'), ('folder');