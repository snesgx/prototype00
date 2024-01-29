USE kudori_local;

drop table fileindex;

CREATE TABLE fileindex (
    id BINARY(16) PRIMARY KEY,
    parent_id BINARY(16),
    file_path VARCHAR(255),
    file_size BIGINT,
    is_directory BOOLEAN,
    creation_date_time TIMESTAMP
);


select * from fileindex;
