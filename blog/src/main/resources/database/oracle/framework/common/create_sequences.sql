--�û�ID����
create sequence SEQ_USERID
minvalue 1
maxvalue 999999999999
start with 1
increment by 1
nocache;

--��ɫID����
create sequence SEQ_ROLEID
minvalue 1
maxvalue 999999999999
start with 1
increment by 1
nocache;

--�û���ɫID
create sequence SEQ_USER2ROLEID
minvalue 1
maxvalue 999999999999
start with 1
increment by 1
nocache;

--��ɫȨ��ID����
create sequence SEQ_ROLE2RIGHTID
minvalue 1
maxvalue 999999999999
start with 1
increment by 1
nocache;

--�ֵ�ID����
create sequence SEQ_DICTID
minvalue 1
maxvalue 999999999999
start with 1
increment by 1
nocache;