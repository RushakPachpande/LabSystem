CREATE DATABASE practodb;

USE practodb;

CREATE TABLE `user` (
    `user_id` INT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(50) NOT NULL,
    `password` VARCHAR(100) NOT NULL,
    `role` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`user_id`)
);

CREATE TABLE `patient` (
    `patient_id` INT NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(50) NOT NULL,
    `last_name` VARCHAR(50) NOT NULL,
    `date_of_birth` DATE NOT NULL,
    `contact_number` VARCHAR(15) NOT NULL,
    `address` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`patient_id`)
);

CREATE TABLE `appointment` (
    `appointment_id` INT NOT NULL AUTO_INCREMENT,
    `patient_id` INT NOT NULL,
    `user_id` INT NOT NULL,
    `appointment_date` DATE NOT NULL,
    `appointment_time` TIME NOT NULL,
    `test_type` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`appointment_id`),
    KEY `fk_patient` (`patient_id`),
    KEY `fk_user` (`user_id`),
    CONSTRAINT `fk_patient` FOREIGN KEY (`patient_id`)
        REFERENCES `patient` (`patient_id`),
    CONSTRAINT `fk_user` FOREIGN KEY (`user_id`)
        REFERENCES `user` (`user_id`)
);