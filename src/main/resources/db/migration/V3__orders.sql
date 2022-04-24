CREATE TABLE users (
                        id BIGINT AUTO_INCREMENT NOT NULL,
                        name VARCHAR(255) NOT NULL,
                        login VARCHAR(60) NOT NULL,
                        password VARCHAR(255) NOT NULL,
                        active BOOLEAN NOT NULL,
                        CONSTRAINT pk_users PRIMARY KEY (id)
);

