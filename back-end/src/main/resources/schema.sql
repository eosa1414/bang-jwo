CREATE DATABASE IF NOT EXISTS bangjwo;

USE bangjwo;

CREATE TABLE IF NOT EXISTS `likes` (
   `like_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   `room_id` BIGINT NOT NULL,
   `member_id` BIGINT NOT NULL,
   `flag` BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS `memo` (
  `memo_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `member_id` BIGINT NOT NULL,
  `room_id` BIGINT NOT NULL,
  `content` VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS `payment` (
    `payment_id`					BIGINT		    NOT NULL	AUTO_INCREMENT PRIMARY KEY,
    `imp_uid`                       VARCHAR(255)    NOT NULL,
    `member_id`						BIGINT		    NOT NULL,
    `room_id`						BIGINT		    NOT NULL,
    `status`                        ENUM('READY', 'PAID', 'FAILED') NOT NULL DEFAULT 'READY',
    `created_at`				    TIMESTAMP	    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`                    TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS `landlord_info` (
    `landlord_info_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(20) NOT NULL,
    `phone_number` VARCHAR(20) NOT NULL,
    `address` TEXT NOT NULL,
    `resident_registration_number` CHAR(14) NOT NULL,
    `rental_property_address` TEXT NOT NULL,
    `property_structure` VARCHAR(255) NOT NULL,
    `property_purpose` VARCHAR(255) NOT NULL,
    `property_area` DECIMAL(10,2) NOT NULL,
    `repair_responsibility` TEXT NULL,
    `priority_confirmed_date_yn` BOOLEAN NOT NULL,
    `tax_arrears` BOOLEAN NOT NULL,
    `location_of_rental_housing` VARCHAR(100) NULL,
    `rental_housing_land_type` VARCHAR(255) NULL,
    `rental_housing_land_area` DECIMAL NULL,
    `rental_housing_usage` VARCHAR(50) NULL,
    `rental_housing_area` DECIMAL NULL,
    `rental_part_address` VARCHAR(50) NULL,
    `rental_part_area` DECIMAL NULL,
    `contract_type` ENUM('NEW', 'AGREEMENT', 'RENEW') NOT NULL,
    `lease_type` ENUM('DEPOSIT', 'NODEPOSIT') NOT NULL,
    `deposit_amount` BIGINT NOT NULL,
    `monthly_rent` BIGINT NOT NULL,
    `lease_start_date` DATE NOT NULL,
    `lease_end_date` DATE NOT NULL,
    `contract_fee` INT NULL,
    `middle_fee` INT NULL,
    `down_payment_date` DATE NULL,
    `interim_payment_date` DATE NULL,
    `balance` INT NULL,
    `balance_payment_date` DATE NULL,
    `monthly_rent_payment_date` VARCHAR(2) NULL,
    `fixed_management_fee` INT NOT NULL DEFAULT FALSE,
    `unfixed_management_fee` VARCHAR(255) NULL,
    `monthly_rent_account_bank` VARCHAR(20) NULL,
    `monthly_rent_account_number` VARCHAR(20) NULL,
    `facilities_repair_status` BOOLEAN NOT NULL DEFAULT FALSE,
    `facilities_repair_content` VARCHAR(255) NULL,
    `landlord_burden` VARCHAR(255) NULL,
    `tenant_burden` VARCHAR(255) NULL,
    `landlord_signature_url_1` VARCHAR(255) NULL,
    `landlord_signature_url_2` VARCHAR(255) NULL,
    `landlord_signature_url_3` VARCHAR(255) NULL,
    `landlord_signature_url_4` VARCHAR(255) NULL
);

CREATE TABLE IF NOT EXISTS `contract` (
    `contract_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `member_id` BIGINT NOT NULL,
    `member_id2` BIGINT NOT NULL,
    `room_id` BIGINT NOT NULL,
    `tenant_info_id` BIGINT NOT NULL,
    `landload_info_id` BIGINT NOT NULL,
    `ipfs_key` VARCHAR(255) NOT NULL,
    `aes_key` TEXT NOT NULL,
    `status` ENUM('DONE', 'UNDONE') NOT NULL,
    `landlord_auth` BOOLEAN NOT NULL DEFAULT FALSE,
    `tenant_auth` BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS `tenant_info` (
    `tenant_info_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(20) NOT NULL,
    `phone` VARCHAR(20) NOT NULL,
    `address` TEXT NOT NULL,
    `resident_registration_number` CHAR(14) NOT NULL,
    `move_in_date` DATE NOT NULL,
    `tenant_signature_url` VARCHAR(255) NULL
);

CREATE TABLE IF NOT EXISTS `member` (
    `member_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `kakao_id` BIGINT NOT NULL,
    `name` VARCHAR(20) NOT NULL,
    `birthday` VARCHAR(20) NULL,
    `phone` VARCHAR(20) NOT NULL,
    `profile_url` VARCHAR(255) NULL,
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted_at` TIMESTAMP NULL
);

CREATE TABLE IF NOT EXISTS `special_clause` (
    `special_clause_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `landload_info_id` BIGINT NOT NULL,
    `move_in_registration_date` DATE NOT NULL,
    `unpaid_amount` INT NULL,
    `dispute_resolution` BOOLEAN NOT NULL,
    `is_housing_reconstruction_planned` BOOLEAN NOT NULL,
    `construction_period` DATE NULL,
    `estimated_construction_duration` INT NULL,
    `is_detailed_address_consent_given` BOOLEAN NOT NULL,
    `etc` VARCHAR(255) NULL
);

CREATE TABLE IF NOT EXISTS `options` (
    `option_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `room_id` BIGINT NOT NULL,
    `option_name` ENUM('ELEVATOR', 'ROOFTOP', 'AIR_CONDITIONER', 'WASHING_MACHINE', 'REFRIGERATOR', 'MICROWAVE', 'GAS_RANGE', 'INDUCTION', 'BED') NOT NULL
);

CREATE TABLE IF NOT EXISTS `image` (
    `image_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `room_id` BIGINT NOT NULL,
    `image_url` VARCHAR(255) NOT NULL,
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS `review` (
    `review_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `lessor_id` BIGINT NOT NULL,
    `lessee_id` BIGINT NOT NULL,
    `room_id` BIGINT NOT NULL,
    `real_estate_id` VARCHAR(255) NOT NULL,
    `address_detail` VARCHAR(255) NOT NULL,
    `content` VARCHAR(255) NULL,
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted_at` TIMESTAMP NULL
);

CREATE TABLE IF NOT EXISTS `address` (
    `address_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `room_id` BIGINT NOT NULL,
    `name` VARCHAR(255) NULL,
    `address_detail` VARCHAR(255) NULL,
    `postal_code` VARCHAR(10) NULL,
    `lat` DECIMAL(9,6) NULL,
    `lng` DECIMAL(9,6) NULL,
    `province` VARCHAR(100) NULL,
    `city` VARCHAR(100) NULL,
    `district` VARCHAR(100) NULL
);

CREATE TABLE IF NOT EXISTS `room` (
    `room_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `member_id` BIGINT NOT NULL,
    `building_type` ENUM('ONEROOM_TWOROOM', 'APARTMENT', 'VILLA_HOUSE', 'OFFICETEL') NULL,
    `status` ENUM('UNDER_VERIFICATION', 'ON_SALE', 'SOLD_OUT') NOT NULL,
    `real_estate_id` VARCHAR(20) NOT NULL,
    `deposit` INT NOT NULL,
    `monthly_rent` INT NOT NULL,
    `exclusive_area` DECIMAL NULL,
    `supply_area` DECIMAL NULL,
    `total_units` INT NULL,
    `floor` VARCHAR(10) NULL,
    `max_floor` INT NULL,
    `parking_spaces` INT NOT NULL,
    `available_from` DATE NOT NULL,
    `permission_date` DATE NOT NULL,
    `simple_description` VARCHAR(255) NULL,
    `description` TEXT NULL,
    `maintenance_cost` INT NOT NULL,
    `room_cnt` TINYINT NOT NULL,
    `bathroom_cnt` TINYINT NOT NULL,
    `direction` ENUM('EAST', 'WEST', 'SOUTH', 'NORTH', 'NORTHWEST', 'NORTHEAST', 'SOUTHWEST', 'SOUTHEAST') NOT NULL,
    `verified` BOOLEAN NOT NULL DEFAULT FALSE,
    `registry_paid` BOOLEAN NOT NULL DEFAULT FALSE,
    `discussable` BOOLEAN NOT NULL DEFAULT FALSE,
    `discuss_detail` VARCHAR(255) NULL,
    `reviewable` BOOLEAN NOT NULL DEFAULT FALSE,
    `is_phone_public` BOOLEAN NOT NULL DEFAULT FALSE,
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted_at` TIMESTAMP NULL
);

CREATE TABLE IF NOT EXISTS `real_estate_pdf` (
    `pdf_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `member_id` BIGINT NOT NULL,
    `room_id` BIGINT NOT NULL,
    `pdf_url` VARCHAR(255) NOT NULL,
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted_at` TIMESTAMP NULL
);

CREATE TABLE IF NOT EXISTS `maintenance_include` (
    `maintenance_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `room_id` BIGINT NOT NULL,
    `maintenance_include_name` ENUM('WATER','ELECTRICITY','INTERNET','GAS','CLEANING','CABLE_TV','PARKING','HEATING','ELEVATOR_MAINTENANCE') NOT NULL
);

CREATE TABLE IF NOT EXISTS `chat_room` (
    `chat_room_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `landlord_id` BIGINT NOT NULL,
    `tenant_id` BIGINT NOT NULL,
    `room_id` BIGINT NOT NULL,
    `landload_unread_count` BIGINT NOT NULL DEFAULT 0,
    `tenant_unread_count` BIGINT NOT NULL DEFAULT 0,
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
