CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(50) NOT NULL,
    user_name VARCHAR(20) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    age INT NOT NULL,
    is_active BOOLEAN,
    email_verified BOOLEAN DEFAULT FALSE,
    credit_card VARCHAR(19),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);
