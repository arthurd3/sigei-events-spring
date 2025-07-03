CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone VARCHAR(255) UNIQUE NOT NULL,
    user_type VARCHAR(50) NOT NULL,
    points INTEGER
);

INSERT INTO users (username, password, email, phone, user_type, points) VALUES
('Ana', '$2a$10$rw7c.Iae8M.macB5p/XMAeBdeu3GAGMd.Hp6xoZxkhbZTh4SX4ngC', 'ana@gmail.com', '21988887777', 'ADMIN', 100),
('Bruno', '$2a$10$rw7c.Iae8M.macB5p/XMAeBdeu3GAGMd.Hp6xoZxkhbZTh4SX4ngC', 'bruno@gmail.com', '11977776666', 'PARTICIPANT', 50),
('Carla', '$2a$10$rw7c.Iae8M.macB5p/XMAeBdeu3GAGMd.Hp6xoZxkhbZTh4SX4ngC', 'carla@gmail.com', '32966665555', 'PARTICIPANT', 10),
('Daniel', '$2a$10$rw7c.Iae8M.macB5p/XMAeBdeu3GAGMd.Hp6xoZxkhbZTh4SX4ngC', 'dani@gmail.com', '41955554444', 'ORGANIZER', 0);
