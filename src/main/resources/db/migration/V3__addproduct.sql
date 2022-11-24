CREATE TABLE IF NOT EXISTS product(
    id SERIAL NOT NULL,
    description VARCHAR(250) NOT NULL,
    brand VARCHAR(20) NOT NULL,
    stock INT NOT NULL,
    PRIMARY KEY(id)
)