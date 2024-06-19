CREATE TABLE car (
    car_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    make VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    number_plate VARCHAR(255) NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT FK_USER FOREIGN KEY (user_id)
        REFERENCES user_account(user_id)
);