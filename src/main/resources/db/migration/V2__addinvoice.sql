CREATE TABLE IF NOT EXISTS invoice(
    id SERIAL NOT NULL,
    client_id INT,
    code VARCHAR(20) NOT NULL,
    create_at DATE NOT NULL,
    total DECIMAL(8, 2),
    PRIMARY KEY(id),
    UNIQUE(code),
    FOREIGN KEY(client_id) REFERENCES client(id)
);