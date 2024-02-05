USE kudori_local;

Select * from fileindex where device_id=1 and (id=0xCEFEE3C42B6C397F5863D79D6AA2E656 or parent_id=0xCEFEE3C42B6C397F5863D79D6AA2E656);
Select * from fileindex where device_id=1 and (id=0x712D8011522087D2534F5E83F944FD08);
Select * from fileindex where device_id=1 and (id=0xEFA64CD04645F97114C268CF9EB7BF88);
Select * from fileindex where device_id=1 and (id=0x8B8F8EB8F67880D6CC4D4B2B4683A7D5);
Select * from fileindex where device_id=1 and (id=0xA5AE1AF2B1507E061A2EACBD26FDCA15);
Select * from fileindex where device_id=1 and (id=0x4CAA791091D21D23E63637080226F370);
Select * from fileindex where device_id=1 and (id=0x6666CD76F96956469E7BE39D750CC7D9);





Select * from fileindex a left join fileindex b on a.parent_id=b.id and a.device_id=b.device_id where a.device_id=1 and b.id is null;

Select get_filefullpath(1,0x6666CD76F96956469E7BE39D750CC7D9);

Select file_size,get_filefullpath(device_id,id) from fileindex where file_size in (
Select file_size from fileindex where file_size > 1600000 group by file_size having count(*) > 1
) order by file_size desc;

select device_id,file_name,parent_id,id from fileindex where id=0x6666CD76F96956469E7BE39D750CC7D9;