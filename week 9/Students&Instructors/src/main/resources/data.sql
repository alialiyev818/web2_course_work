CREATE TABLE IF NOT EXISTS students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    phone VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS instructors (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    experience INT
);

CREATE TABLE IF NOT EXISTS student_instructors (
    student_id INT,
    instructor_id INT,
    FOREIGN KEY (student_id) REFERENCES students(id),
    FOREIGN KEY (instructor_id) REFERENCES instructors(id),
    PRIMARY KEY (student_id, instructor_id)
);

INSERT INTO students (firstname, lastname, email, age, phone) VALUES
('John', 'Doe', 'john@example.com', 25, '123456789'),
('Alice', 'Smith', 'alice@example.com', 23, '987654321'),
('Bob', 'Johnson', 'bob@example.com', 22, '5551234567'),
('Emma', 'Brown', 'emma@example.com', 24, '5559876543'),
('Michael', 'Wilson', 'michael@example.com', 26, '5555555555'),
('Sophia', 'Miller', 'sophia@example.com', 21, '5556667777'),
('William', 'Davis', 'william@example.com', 27, '5557778888'),
('Olivia', 'Garcia', 'olivia@example.com', 20, '5558889999'),
('James', 'Martinez', 'james@example.com', 29, '5559990000'),
('Charlotte', 'Hernandez', 'charlotte@example.com', 22, '5550001111'),
('Benjamin', 'Young', 'benjamin@example.com', 24, '5551112222'),
('Amelia', 'King', 'amelia@example.com', 26, '5552223333'),
('Elijah', 'Lee', 'elijah@example.com', 25, '5553334444'),
('Mia', 'Clark', 'mia@example.com', 23, '5554445555'),
('Lucas', 'Lewis', 'lucas@example.com', 28, '5555556666'),
('Ava', 'Hall', 'ava@example.com', 24, '5556667777'),
('Alexander', 'Allen', 'alexander@example.com', 30, '5557778888'),
('Isabella', 'Baker', 'isabella@example.com', 22, '5558889999'),
('Mason', 'Young', 'mason@example.com', 26, '5559990000'),
('Sophia', 'Scott', 'sophia.scott@example.com', 27, '5550001111');

INSERT INTO instructors (name, surname, email, phone, experience) VALUES
('Michael', 'Johnson', 'michael@example.com', '5551234567', 5),
('Emma', 'Brown', 'emma@example.com', '5559876543', 8),
('William', 'Taylor', 'william@example.com', '5552223333', 6),
('Olivia', 'Lee', 'olivia@example.com', '5554445555', 7),
('James', 'Clark', 'james@example.com', '5553334444', 9),
('Charlotte', 'White', 'charlotte@example.com', '5556667777', 4),
('Benjamin', 'Walker', 'benjamin@example.com', '5558889999', 7),
('Amelia', 'Martinez', 'amelia@example.com', '5551112222', 6),
('Elijah', 'Anderson', 'elijah@example.com', '5557778888', 8),
('Mia', 'Thompson', 'mia@example.com', '5556665555', 5),
('Lucas', 'Thomas', 'lucas@example.com', '5553336666', 6),
('Ava', 'Hernandez', 'ava@example.com', '5552229999', 7),
('Alexander', 'Garcia', 'alexander@example.com', '5558881111', 8),
('Isabella', 'Parker', 'isabella@example.com', '5557773333', 6),
('Mason', 'Smith', 'mason@example.com', '5559994444', 5),
('Sophia', 'Jones', 'sophia.jones@example.com', '5555558888', 7),
('Ethan', 'Brown', 'ethan@example.com', '5551117777', 9),
('Olivia', 'Davis', 'olivia.davis@example.com', '5552226666', 4),
('Liam', 'Hernandez', 'liam@example.com', '5553335555', 8),
('Ava', 'Young', 'ava.young@example.com', '5558884444', 5);

INSERT INTO student_instructors (student_id, instructor_id) VALUES
(1, 1), (2, 2), (3, 3), (4, 4), (5, 5), (6, 6), (7, 7), (8, 8), (9, 9), (10, 10),
(11, 11), (12, 12), (13, 13), (14, 14), (15, 15), (16, 16), (17, 17), (18, 18), (19, 19), (20, 20);
