CREATE TABLE IF NOT EXISTS client(
    id SERIAL,
    nui VARCHAR(13) NOT NULL,
    fullname VARCHAR(100),
    address VARCHAR(100),
    PRIMARY KEY (id),
    UNIQUE (nui)
    );
