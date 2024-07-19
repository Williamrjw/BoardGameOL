CREATE TABLE user (
                      id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                      github_id VARCHAR(255) NOT NULL,
                      username VARCHAR(63) NOT NULL,
                      email VARCHAR(255) NULL DEFAULT NULL,
                      create_time TIMESTAMP NULL DEFAULT NULL,
                      last_login_time TIMESTAMP NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;