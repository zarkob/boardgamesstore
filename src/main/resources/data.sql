-- Create board_game table if not exists
CREATE TABLE IF NOT EXISTS board_game
(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    publisher VARCHAR(255)
);

-- Check if data already exists to avoid duplicates
MERGE INTO board_game (id, name, publisher) KEY (id)
VALUES 	(1, 'Catan', 'Kosmos'),
		(2, 'Ticket to Ride', 'Days of Wonder'),
		(3, 'Chess', 'Unknown');
