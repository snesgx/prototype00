USE kudori_local;

/*
drop table fileindex;
drop table devices;
delete from fileindex;
*/
select get_deviceid('ankkes');

select * from devices;

select * from fileindex where device_id=2 order by file_size desc;

select *,get_filefullpath(device_id,id) fullpath from fileindex where device_id=2 order by file_size desc;


select sum(file_size) from fileindex;
select count(*) from fileindex;


select *,get_filefullpath(device_id,id) from fileindex where file_name like '%2016%' order by file_size desc;



CALL delete_path(1,"/");

select device_id,id,modification_date_time, 
file_size,get_filefullpath(device_id,id) fullpath   
from fileindex
order by file_size DESC LIMIT 100;
