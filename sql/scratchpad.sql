USE kudori_local;

drop table fileindex;

CREATE TABLE fileindex (
    id BINARY(16) PRIMARY KEY,
    parent_id BINARY(16),
    file_path VARCHAR(255),
    file_size BIGINT,
    is_directory BOOLEAN,
    creation_date_time DATETIME
);


select * from fileindex order by file_size desc;

delete from fileindex;

select a.file_size,a.file_path,b.file_path  from fileindex a
 left join fileindex b 
on a.parent_id=b.id order by a.file_size desc;
