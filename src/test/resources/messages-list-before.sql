DELETE FROM message;

INSERT INTO message(id, text, tag, user_id) VALUES
(1, 'first', 'my-tag', 2),
(2, 'second', 'more', 2),
(3, 'third', 'my-tag', 2),
(4, 'fourth', 'another', 2);

ALTER sequence hibernate_sequence restart WITH 10;