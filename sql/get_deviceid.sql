DROP FUNCTION get_deviceid;
DELIMITER $$
CREATE FUNCTION get_deviceid(_device_name VARCHAR(255))
RETURNS SMALLINT
BEGIN
    DECLARE result SMALLINT;

    select id INTO result from devices where device_name=_device_name limit 1;
	
   	If result IS NULL THEN
   		INSERT INTO devices (device_name) VALUES(_device_name);	
   		select id INTO result from devices where device_name=_device_name limit 1;
   	END IF;
   
    RETURN result;
END 
$$
DELIMITER ;