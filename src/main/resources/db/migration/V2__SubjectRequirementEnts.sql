CREATE TABLE subject_requirement (
                                     id SERIAL PRIMARY KEY,
                                     subject_id INTEGER NOT NULL REFERENCES subjects(id) ON DELETE CASCADE ON UPDATE CASCADE,
                                     content VARCHAR(255),
                                     exam_type INTEGER,
                                     exam_count INTEGER,
                                     pass_criteria VARCHAR(255)
);

CREATE TABLE requirement (
                             id SERIAL PRIMARY KEY,
                             content VARCHAR(255),
                             subject_requirement_id INTEGER REFERENCES subject_requirement(id) ON DELETE CASCADE ON UPDATE CASCADE,
                             requirement_type INTEGER,
                             requirement_progress FLOAT
);
