CREATE TABLE user_group (
                            user_id INT REFERENCES users(id),
                            group_id INT REFERENCES groups(id),
                            PRIMARY KEY (user_id, group_id)
);