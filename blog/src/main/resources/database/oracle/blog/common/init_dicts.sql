--������Դ
delete from SYS_DICT WHERE DICTTYPE = 'SOURCE';
insert into SYS_DICT (DICTID, DICTTYPE, TYPENAME, DCITCODE, DICTNAME, DESCRITPION, VALIDATED, MEMO, BAE001, BAE002, BAE003, BAE004)
values (seq_dictid.nextval, 'SOURCE', '������Դ', '1', 'ԭ��', '������Դ', '1', '', SYSDATE, '1', SYSDATE, '1');
insert into SYS_DICT (DICTID, DICTTYPE, TYPENAME, DCITCODE, DICTNAME, DESCRITPION, VALIDATED, MEMO, BAE001, BAE002, BAE003, BAE004)
values (seq_dictid.nextval, 'SOURCE', '������Դ', '0', 'ת��', '������Դ', '1', '', SYSDATE, '1', SYSDATE, '1');
commit;