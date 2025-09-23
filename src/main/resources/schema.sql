CREATE TABLE jobs (
                      id BIGINT NOT NULL AUTO_INCREMENT,
                      job_name VARCHAR(255),
                      company_name VARCHAR(255),
                      status VARCHAR(255),
                      date_submitted DATETIME,
                      cv_file_name VARCHAR(255),
                      PRIMARY KEY (id)
) ;