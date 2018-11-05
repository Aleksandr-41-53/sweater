DELETE FROM user_role;
DELETE FROM usr;

INSERT INTO usr(id, active, password, username) VALUES
(1, TRUE, '$2a$08$5OnmfJNJKbdn6oP3e0W8B.h1pFumCBSQy9Q9wr882gUmo.Nwq9i4m', 'admin'),
(2, TRUE, '$2a$08$5OnmfJNJKbdn6oP3e0W8B.h1pFumCBSQy9Q9wr882gUmo.Nwq9i4m', 'Pudje');

INSERT INTO user_role(user_id, roles) VALUES
(1, 'USER'), (1, 'ADMIN'),
(2, 'USER');
