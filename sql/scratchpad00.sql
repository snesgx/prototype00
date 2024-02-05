USE kudori_local;

drop table fileindex;
drop table devices;

select get_deviceid('ankkes');

select * from devices;

select * from fileindex order by file_size desc;

select sum(file_size) from fileindex;
select count(*) from fileindex;

delete from fileindex;

CALL delete_path(1,"/");

select a.modification_date_time, 
a.file_size,CONCAT(IFNULL(d.file_name,'') , "/", IFNULL(c.file_name,'') , "/" , IFNULL(b.file_name,'') , "/" , IFNULL(a.file_name,'')) filen   
from fileindex a
 left join fileindex b 
on a.parent_id=b.id
left join fileindex c 
on b.parent_id=c.id 
left join fileindex d 
on c.parent_id=d.id 
order by a.file_size desc;
