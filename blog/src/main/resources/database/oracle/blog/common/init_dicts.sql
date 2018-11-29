--文章来源
delete from SYS_DICT WHERE DICTTYPE = 'SOURCE';
insert into SYS_DICT (DICTID, DICTTYPE, TYPENAME, DCITCODE, DICTNAME, DESCRITPION, VALIDATED, MEMO, BAE001, BAE002, BAE003, BAE004)
values (seq_dictid.nextval, 'SOURCE', '文章来源', '1', '原创', '文章来源', '1', '', SYSDATE, '1', SYSDATE, '1');
insert into SYS_DICT (DICTID, DICTTYPE, TYPENAME, DCITCODE, DICTNAME, DESCRITPION, VALIDATED, MEMO, BAE001, BAE002, BAE003, BAE004)
values (seq_dictid.nextval, 'SOURCE', '文章来源', '0', '转载', '文章来源', '1', '', SYSDATE, '1', SYSDATE, '1');
commit;