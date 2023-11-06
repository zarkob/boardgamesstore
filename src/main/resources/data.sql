-- Create board_game table if not exists
CREATE TABLE IF NOT EXISTS board_game
(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    publisher VARCHAR(255),
    price DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL
);

-- Check if data already exists to avoid duplicates
MERGE INTO board_game (id, name, publisher, price, stock) KEY (id)
VALUES 	(1, 'Catan', 'Kosmos', 22.8, 40),
		(2, 'Ticket to Ride', 'Days of Wonder', 15.0, 36),
		(3, 'Chess', 'Unknown', 0, 0);
