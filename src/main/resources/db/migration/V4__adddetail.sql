CREATE TABLE IF NOT EXISTS detail(
    id SERIAL NOT NULL,
    invoice_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (invoice_id) REFERENCES invoice(id),
    FOREIGN KEY(product_id) REFERENCES product(id)
)