DROP TABLE IF EXISTS university CASCADE;
DROP TABLE IF EXISTS course CASCADE;
DROP TABLE IF EXISTS student CASCADE;
DROP TABLE IF EXISTS exam CASCADE;
DROP TABLE IF EXISTS attendee CASCADE;

DROP SEQUENCE IF EXISTS student_id_seq CASCADE;
DROP SEQUENCE IF EXISTS university_id_seq CASCADE;
DROP SEQUENCE IF EXISTS course_id_seq CASCADE;
DROP SEQUENCE IF EXISTS exam_id_seq CASCADE;
DROP SEQUENCE IF EXISTS attendee_id_seq CASCADE;

CREATE TABLE IF NOT EXISTS university (

                                          id BIGINT NOT NULL,
                                          name VARCHAR(100),
                                          rector VARCHAR(50),
                                          PRIMARY KEY(id)

);

CREATE TABLE IF NOT EXISTS course (

                                      id BIGINT NOT NULL UNIQUE,
                                      teacher VARCHAR(50),
                                      title VARCHAR(100),
                                      university_id BIGINT,
                                      PRIMARY KEY(id, university_id),
                                      CONSTRAINT chk_university_id FOREIGN KEY (university_id) REFERENCES university(id)

);

CREATE TABLE IF NOT EXISTS student (

                                       id BIGINT NOT NULL UNIQUE,
                                       first VARCHAR(255),
                                       last VARCHAR(255),
                                       university_id BIGINT,
                                       PRIMARY KEY(id, university_id),
                                       CONSTRAINT chk_university_id FOREIGN KEY (university_id) REFERENCES university(id)

);

CREATE TABLE IF NOT EXISTS exam (

                                    id BIGINT NOT NULL UNIQUE,
                                    course_id  BIGINT,
                                    grade      SMALLINT,
                                    student_id BIGINT,
                                    university_id BIGINT,
                                    PRIMARY KEY (course_id, student_id, university_id),
                                    CONSTRAINT chk_course_id FOREIGN KEY (course_id) REFERENCES course(id),
                                    CONSTRAINT chk_student_id FOREIGN KEY (student_id) REFERENCES student(id),
                                    CONSTRAINT chk_university_id FOREIGN KEY (university_id) REFERENCES university(id),
                                    CHECK (grade >= 0 AND grade <= 30)
);

CREATE TABLE IF NOT EXISTS attendee (

                                        id BIGINT NOT NULL UNIQUE,
                                        course_id BIGINT,
                                        student_id BIGINT,
                                        title VARCHAR(100),
                                        university_id BIGINT,
                                        PRIMARY KEY(course_id, student_id, university_id),
                                        CONSTRAINT chk_course_id FOREIGN KEY (course_id) REFERENCES course(id),
                                        CONSTRAINT chk_student_id FOREIGN KEY (student_id) REFERENCES student(id),
                                        CONSTRAINT chk_university_id FOREIGN KEY (university_id) REFERENCES university(id)
);

INSERT INTO university (id, name, rector)
VALUES (1, 'Stanford University', 'Marc Tessier-Lavigne'),
       (2, 'Harvard University', 'Lawrence S. Bacow'),
       (3, 'Massachusetts Institute of Technology', 'L. Rafael Reif'),
       (4, 'University of Cambridge', 'Stephen Toope');

INSERT INTO course (id, teacher, title, university_id)
VALUES (1, 'Andrew Ng', 'Machine Learning', 1),
       (2, 'John Nash', 'Game Theory', 2),
       (3, 'Richard Feynman', 'Quantum Mechanics', 3),
       (4, 'Stephen Hawking', 'Theoretical Physics', 4);

INSERT INTO student (id, first, last, university_id)
VALUES (1, 'Mark', 'Zuckerberg', 1),
       (2, 'Bill', 'Gates', 2),
       (3, 'Elon', 'Musk', 3),
       (4, 'Alan', 'Turing', 4);

INSERT INTO exam (id, course_id, grade, student_id, university_id)
VALUES (1, 1, 25, 1, 1),
       (2, 2, 26, 2, 2),
       (3, 3, 28, 3, 3),
       (4, 4, 23, 4, 4);

INSERT INTO attendee (id, course_id, student_id, title, university_id)
VALUES (1, 1, 1, 'Machine Learning', 1),
       (2, 2, 2, 'Game Theory', 2),
       (3, 3, 3, 'Quantum Mechanics', 3),
       (4, 4, 4, 'Theoretical Physics', 4);
