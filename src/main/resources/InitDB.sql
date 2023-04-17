    DROP TABLE IF EXISTS university CASCADE;
    DROP TABLE IF EXISTS course CASCADE;
    DROP TABLE IF EXISTS student CASCADE;
    DROP TABLE IF EXISTS exam CASCADE;
    DROP TABLE IF EXISTS attendee CASCADE;

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
                                        CONSTRAINT chk_university_id FOREIGN KEY (university_id) REFERENCES university(id)
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
