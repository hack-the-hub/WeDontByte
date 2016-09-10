CREATE TABLE issues (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `issue_id` VARCHAR(200) NOT NULL,
    `type` VARCHAR(200) NOT NULL,
    `rating` INT(1) NOT NULL,
    `comment` LONGTEXT,
    `issue_time` BIGINT(20) NOT NULL,
    PRIMARY KEY (id)
    );

CREATE TABLE geolocation (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `geolocation_issue_id` VARCHAR(200) NOT NULL REFERENCES issues(`issue_id`),
    `latitude` DECIMAL(10, 8) NOT NULL,
    `longitude` DECIMAL(11, 8) NOT NULL,
    PRIMARY KEY (`id`)
    );
