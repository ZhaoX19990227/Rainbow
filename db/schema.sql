CREATE TABLE IF NOT EXISTS wx_user (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nickname VARCHAR(50) NOT NULL,
  avatar VARCHAR(255),
  star_sign VARCHAR(20),
  birthday DATE,
  age INT,
  height INT,
  weight INT,
  attribute VARCHAR(10),
  location VARCHAR(255),
  latitude DOUBLE,
  longitude DOUBLE,
  password VARCHAR(100)
);
