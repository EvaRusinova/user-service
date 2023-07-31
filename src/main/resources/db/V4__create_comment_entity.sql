CREATE TABLE comment (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    author VARCHAR(255),
    body TEXT NOT NULL,
    post_id BIGINT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (post_id) REFERENCES post(id)
);
