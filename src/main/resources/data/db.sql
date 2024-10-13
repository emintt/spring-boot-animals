DROP DATABASE IF EXISTS MediaApp;
CREATE DATABASE MediaApp;
USE MediaApp;
CREATE TABLE Media (
    media_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    filename VARCHAR(255) NOT NULL,
    filesize INT NOT NULL,
    media_type VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
#     FOREIGN KEY (user_id) REFERENCES Users(user_id)
);


