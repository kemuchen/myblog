--���±�ǩ��̬�ֵ�����
delete from DYNAMIC_DICT_CONFIG where CONFIGID = 'atricle_manager_tagid';
insert into DYNAMIC_DICT_CONFIG(CONFIGID, MULTSQL, VALIDATED, DESCRIPTION, MEMO, BAE001, BAE002, BAE003, BAE004)
values('atricle_manager_tagid', 'select TAGID CODE, TAGNAME NAME from BLOG_ARTICLETAG', '1', '���±�ǩ��̬�ֵ�����', '', sysdate, '1', sysdate, '1');

--���·��ද̬�ֵ�����
delete from DYNAMIC_DICT_CONFIG where CONFIGID = 'atricle_manager_typeid';
insert into DYNAMIC_DICT_CONFIG(CONFIGID, MULTSQL, VALIDATED, DESCRIPTION, MEMO, BAE001, BAE002, BAE003, BAE004)
values('atricle_manager_typeid', 'select TYPEID CODE, TYPENAME NAME from BLOG_ARTICLETYPE', '1', '���·��ද̬�ֵ�����', '', sysdate, '1', sysdate, '1');
commit;