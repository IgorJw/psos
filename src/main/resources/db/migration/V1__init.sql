CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(255) NOT NULL
);

CREATE TABLE users_basic_info (
                                  id SERIAL PRIMARY KEY,
                                  name VARCHAR(255),
                                  surname VARCHAR(255),
                                  start_year INTEGER,
                                  user_id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE subjects (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          teacher VARCHAR(255) NOT NULL,
                          user_id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE events (
                        id SERIAL PRIMARY KEY,
                        user_id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE,
                        subject_id INTEGER NOT NULL REFERENCES subjects(id) ON DELETE CASCADE ON UPDATE CASCADE,
                        content TEXT,
                        date DATE,
                        event_priority VARCHAR(255)
);
INSERT INTO users (username, password,role)
VALUES ('admin', '$2a$10$jpibn/RY5spt5adkqBcnxOlGmeI3xtABO0jDa7I2CONgdHK6FSBoO','ADMIN');

INSERT INTO users_basic_info (name, surname, start_year, user_id)
VALUES ('name', 'surname', 2023, 1);

INSERT INTO subjects (name, teacher, user_id)
VALUES ('Analiza matematyczna', 'Bulatek', 1);

INSERT INTO events (user_id, subject_id, content, date, event_priority)
VALUES (1, 1, 'Kolos z analizy', '2023-04-15', 'BIG');
