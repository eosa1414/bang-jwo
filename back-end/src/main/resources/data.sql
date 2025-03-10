CREATE DATABASE IF NOT EXISTS bangjwo;

USE bangjwo;

CREATE TABLE IF NOT EXISTS members (
                         id            BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         kakao_id      VARCHAR(255) NOT NULL UNIQUE,
                         name          VARCHAR(50)  NOT NULL,
                         nickname      VARCHAR(50)  NOT NULL UNIQUE,
                         gender        ENUM('MALE', 'FEMALE') DEFAULT NULL,
                         email         VARCHAR(100) NOT NULL UNIQUE,
                         profile_image VARCHAR(255) DEFAULT NULL,
                         birthdate     DATE         NOT NULL,
                         created_at    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         updated_at    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         deleted_at    DATETIME     DEFAULT NULL
);
