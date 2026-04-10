CREATE DATABASE IF NOT EXISTS newspaper_db;
USE newspaper_db;
-- Table 1: Subscribers
CREATE TABLE IF NOT EXISTS subscribers (
 sub_id INT PRIMARY KEY AUTO_INCREMENT,
 name VARCHAR(100) NOT NULL,
 address VARCHAR(200) NOT NULL,
 paper_name VARCHAR(100) NOT NULL,
 monthly_rate DECIMAL(6,2) NOT NULL
);
-- Table 2: Payments
CREATE TABLE IF NOT EXISTS payments (
 pay_id INT PRIMARY KEY AUTO_INCREMENT,
 sub_id INT NOT NULL,
 pay_month VARCHAR(7) NOT NULL,
 paid_on DATE DEFAULT (CURRENT_DATE),
 FOREIGN KEY (sub_id) REFERENCES subscribers(sub_id)
);
-- Table 3: Deliveries
CREATE TABLE IF NOT EXISTS deliveries (
 del_id INT PRIMARY KEY AUTO_INCREMENT,
 sub_id INT NOT NULL,
 del_date DATE NOT NULL,
 delivered BOOLEAN DEFAULT TRUE,
 FOREIGN KEY (sub_id) REFERENCES subscribers(sub_id)
);
