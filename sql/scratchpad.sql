drop table ;

CREATE TABLE your_table_name (
    id SERIAL PRIMARY KEY,
    filename VARCHAR(255),
    filecreation_timestamp TIMESTAMP,
    parent_id INTEGER
);


select * from your_table_name;
