drop table if exists txn;
CREATE TABLE txn (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             user_id BIGINT NOT NULL,
                             tid VARCHAR(255) NOT NULL UNIQUE,
                             type VARCHAR(255) NOT NULL,
                             amount DOUBLE NOT NULL,
                             txn_time BIGINT NOT NULL,
                             target_uid BIGINT,
                             created_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                             update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);