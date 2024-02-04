CREATE TABLE devices (
    id SMALLINT AUTO_INCREMENT,
    fs_separator CHAR(1) NOT NULL,
    device_name VARCHAR(255),
    PRIMARY KEY (id, fs_separator)
);
