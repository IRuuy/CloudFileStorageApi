CREATE TABLE file_object (
    id VARCHAR PRIMARY KEY,
    parent_id VARCHAR,
    owner_id VARCHAR,
    type VARCHAR,
    name VARCHAR,
    created_at TIMESTAMP DEFAULT NOW(),
    modified_at TIMESTAMP DEFAULT NOW(),
    size BIGINT DEFAULT 0,

    CONSTRAINT fk_parent_file_object FOREIGN KEY (parent_id) REFERENCES file_object(id) ON DELETE CASCADE,
    CONSTRAINT fk_owner_file_object FOREIGN KEY (owner_id) REFERENCES "user"(id),
    CONSTRAINT unique_object UNIQUE(parent_id, type, name)
);
