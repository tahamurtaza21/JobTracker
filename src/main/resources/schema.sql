CREATE TABLE jobs (
            id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
            job_name VARCHAR(255),
            company_name VARCHAR(255),
            status VARCHAR(255),
            date_submitted TIMESTAMP,
            cv_file_name VARCHAR(255),
            PRIMARY KEY (id)
) ;