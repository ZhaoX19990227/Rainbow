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



CREATE TABLE IF NOT EXISTS chat_message (
                                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                            from_user_id BIGINT NOT NULL,
                                            to_user_id BIGINT NOT NULL,
                                            content TEXT NOT NULL,
                                            type VARCHAR(20) NOT NULL, -- text/image/emoji
                                            is_anonymous TINYINT(1) DEFAULT 0,
                                            timestamp DATETIME DEFAULT CURRENT_TIMESTAMP
);