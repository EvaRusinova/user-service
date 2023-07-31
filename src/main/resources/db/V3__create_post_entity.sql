CREATE TABLE post (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    author VARCHAR(255),
    title VARCHAR(255) NOT NULL,
    body TEXT NOT NULL,
    likes BIGINT,
    user_id BIGINT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);
