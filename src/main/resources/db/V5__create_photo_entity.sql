CREATE TABLE photo (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    data BLOB NOT NULL,
    name VARCHAR(255) NOT NULL,
    format VARCHAR(255),
    post_id BIGINT,
    user_id BIGINT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (post_id) REFERENCES post(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);
