INSERT INTO users (name, email, password)
VALUES ('Aarav Sharma', 'aarav.sharma@gmail.com', '$2a$10$JEfEh7RJnZtyGBzrl1NOcOU7zYZQcg82jWibDXOmbKKdB9VmaPtCq');

INSERT INTO users (name, email)
VALUES ('Priya Verma', 'priya.verma@gmail.com'),
       ('Rohan Mehta', 'rohan.mehta@gmail.com'),
       ('Sneha Kapoor', 'sneha.kapoor@gmail.com'),
       ('Vikram Singh', 'vikram.singh@gmail.com'),
       ('Ananya Joshi', 'ananya.joshi@gmail.com'),
       ('Karan Malhotra', 'karan.malhotra@gmail.com'),
       ('Neha Agarwal', 'neha.agarwal@gmail.com'),
       ('Aditya Chauhan', 'aditya.chauhan@gmail.com'),
       ('Ishita Bansal', 'ishita.bansal@gmail.com');

INSERT INTO leave_types (name, default_days)
VALUES ('Casual Leave', 12),
       ('Sick Leave', 10),
       ('Earned Leave', 18),
       ('Maternity Leave', 180),
       ('Paternity Leave', 15);

INSERT INTO leave_balances (user_id, leave_type_id, total_leaves, used_leaves, remaining_leaves, year)
VALUES

-- Aarav Sharma
(1, 1, 12, 3, 9, 2026),
(1, 2, 10, 1, 9, 2026),
(1, 3, 18, 5, 13, 2026),

-- Priya Verma
(2, 1, 12, 4, 8, 2026),
(2, 2, 10, 2, 8, 2026),
(2, 3, 18, 6, 12, 2026),

-- Rohan Mehta
(3, 1, 12, 2, 10, 2026),
(3, 2, 10, 0, 10, 2026),
(3, 3, 18, 4, 14, 2026),

-- Sneha Kapoor
(4, 1, 12, 5, 7, 2026),
(4, 2, 10, 3, 7, 2026),
(4, 4, 180, 30, 150, 2026),

-- Vikram Singh
(5, 1, 12, 1, 11, 2026),
(5, 2, 10, 2, 8, 2026),
(5, 3, 18, 7, 11, 2026),

-- Ananya Joshi
(6, 1, 12, 6, 6, 2026),
(6, 2, 10, 1, 9, 2026),
(6, 3, 18, 8, 10, 2026),

-- Karan Malhotra
(7, 1, 12, 2, 10, 2026),
(7, 2, 10, 4, 6, 2026),
(7, 5, 15, 3, 12, 2026),

-- Neha Agarwal
(8, 1, 12, 0, 12, 2026),
(8, 2, 10, 2, 8, 2026),
(8, 3, 18, 5, 13, 2026),

-- Aditya Chauhan
(9, 1, 12, 3, 9, 2026),
(9, 2, 10, 1, 9, 2026),
(9, 3, 18, 2, 16, 2026),

-- Ishita Bansal
(10, 1, 12, 4, 8, 2026),
(10, 2, 10, 3, 7, 2026),
(10, 3, 18, 6, 12, 2026);