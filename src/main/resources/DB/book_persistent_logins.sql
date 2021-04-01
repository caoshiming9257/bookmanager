DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE persistent_logins (
    `username` VARCHAR (64) NOT NULL,
    `series` VARCHAR (64) PRIMARY KEY,
    `token` VARCHAR (64) NOT NULL,
    `last_used` TIMESTAMP NOT NULL
)ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;