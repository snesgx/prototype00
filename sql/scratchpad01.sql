USE kudori_local;

Select * from fileindex where device_id=1 and (id=0x362E925C54E2026EF7AF7A5029C3E243 or parent_id=0x362E925C54E2026EF7AF7A5029C3E243);

Select * from fileindex a left join fileindex b on a.parent_id=b.id and a.device_id=b.device_id where a.device_id=1 and b.id is null;
