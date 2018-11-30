--用户ID序列
create sequence SEQ_USERID
minvalue 1
maxvalue 999999999999
start with 1
increment by 1
nocache;

--角色ID序列
create sequence SEQ_ROLEID
minvalue 1
maxvalue 999999999999
start with 1
increment by 1
nocache;

--用户角色ID
create sequence SEQ_USER2ROLEID
minvalue 1
maxvalue 999999999999
start with 1
increment by 1
nocache;

--角色权限ID序列
create sequence SEQ_ROLE2RIGHTID
minvalue 1
maxvalue 999999999999
start with 1
increment by 1
nocache;

--字典ID序列
create sequence SEQ_DICTID
minvalue 1
maxvalue 999999999999
start with 1
increment by 1
nocache;