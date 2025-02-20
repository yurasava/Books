INSERT INTO genres (name) VALUES
('Classic literature'),
('Programming');

INSERT INTO authors (name) VALUES
('L. Tolstoy'),
('F. Scott Fitzgerald'),
('Harper Lee'),
('J. D. Salinger'),
('John Steinbeck'),
('Robert C. Martin'),
('Brian Goetz'),
('Erich Gamma');

INSERT INTO books (title, author_id, description, genre_id) VALUES
('War and Peace', 1, 'A classic novel about the Russian Revolution', 1),
('The Great Gatsby', 2, 'A classic novel about the American Dream', 1),
('To Kill a Mockingbird', 3, 'A classic novel about racial injustice', 1),
('The Catcher in the Rye', 4, 'A classic novel about teenage angst', 1),
('The Grapes of Wrath', 5, 'A classic novel about the Great Depression', 1),
('Clean Code', 6, 'A classic book about software development best practices', 2),
('Java Concurrency in Practice', 7, 'A classic book about concurrency in Java', 2),
('Design Patterns', 8, 'A classic book about software design patterns', 2);
