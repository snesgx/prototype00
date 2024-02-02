USE kudori_local;

drop table fileindex;
drop table devices;

select get_deviceid('testeomax');

select * from devices;

select * from fileindex order by file_size desc;

select sum(file_size) from fileindex;
select count(*) from fileindex;

delete from fileindex;

select a.modification_date_time, a.file_size,CONCAT(d.file_name , "/", c.file_name , "/" , b.file_name , "/" , a.file_name) filen   from fileindex a
 left join fileindex b 
on a.parent_id=b.id
left join fileindex c 
on b.parent_id=c.id 
left join fileindex d 
on c.parent_id=d.id 
order by a.file_size desc;
