--��Ч��־
delete from SYS_DICT WHERE DICTTYPE = 'VALIDATED';
insert into SYS_DICT (DICTID, DICTTYPE, TYPENAME, DCITCODE, DICTNAME, DESCRITPION, VALIDATED, MEMO, BAE001, BAE002, BAE003, BAE004)
values (seq_dictid.nextval, 'VALIDATED', '��Ч��־', '1', '��Ч', '��Ч��־', '1', '', SYSDATE, '1', SYSDATE, '1');
insert into SYS_DICT (DICTID, DICTTYPE, TYPENAME, DCITCODE, DICTNAME, DESCRITPION, VALIDATED, MEMO, BAE001, BAE002, BAE003, BAE004)
values (seq_dictid.nextval, 'VALIDATED', '��Ч��־', '0', '��Ч', '��Ч��־', '1', '', SYSDATE, '1', SYSDATE, '1');

--ϵͳ���
delete from SYS_DICT where DICTTYPE = 'SUBSYSTYPE';
insert into SYS_DICT (DICTID, DICTTYPE, TYPENAME, DCITCODE, DICTNAME, DESCRITPION, VALIDATED, MEMO, BAE001, BAE002, BAE003, BAE004)
values (seq_dictid.nextval, 'SUBSYSTYPE', 'ϵͳ���', 'XTGL', 'ϵͳ����', 'ϵͳ���', '1', '', SYSDATE, '1', SYSDATE, '1');
insert into SYS_DICT (DICTID, DICTTYPE, TYPENAME, DCITCODE, DICTNAME, DESCRITPION, VALIDATED, MEMO, BAE001, BAE002, BAE003, BAE004)
values (seq_dictid.nextval, 'SUBSYSTYPE', 'ϵͳ���', 'BLOG', '����ϵͳ', 'ϵͳ���', '1', '', SYSDATE, '1', SYSDATE, '1');

--�˵����
delete from SYS_DICT where DICTTYPE = 'RIGHTTYPE';
insert into SYS_DICT (DICTID, DICTTYPE, TYPENAME, DCITCODE, DICTNAME, DESCRITPION, VALIDATED, MEMO, BAE001, BAE002, BAE003, BAE004)
values (seq_dictid.nextval, 'RIGHTTYPE', 'Ȩ�����', '1', '�˵�Ȩ��', 'Ȩ�����', '1', '', SYSDATE, '1', SYSDATE, '1');
insert into SYS_DICT (DICTID, DICTTYPE, TYPENAME, DCITCODE, DICTNAME, DESCRITPION, VALIDATED, MEMO, BAE001, BAE002, BAE003, BAE004)
values (seq_dictid.nextval, 'RIGHTTYPE', 'Ȩ�����', '2', '����Ȩ��', 'Ȩ�����', '1', '', SYSDATE, '1', SYSDATE, '1');

--�˵�����
delete from SYS_DICT where DICTTYPE = 'RIGHTLEVEL';
insert into SYS_DICT (DICTID, DICTTYPE, TYPENAME, DCITCODE, DICTNAME, DESCRITPION, VALIDATED, MEMO, BAE001, BAE002, BAE003, BAE004)
values (seq_dictid.nextval, 'RIGHTLEVEL', '�˵�����', '1', 'һ���˵�', '�˵�����', '1', '', SYSDATE, '1', SYSDATE, '1');
insert into SYS_DICT (DICTID, DICTTYPE, TYPENAME, DCITCODE, DICTNAME, DESCRITPION, VALIDATED, MEMO, BAE001, BAE002, BAE003, BAE004)
values (seq_dictid.nextval, 'RIGHTLEVEL', '�˵�����', '2', '�����˵�', '�˵�����', '1', '', SYSDATE, '1', SYSDATE, '1');

--�û�״̬
delete from SYS_DICT where DICTTYPE = 'STATE';
insert into SYS_DICT (DICTID, DICTTYPE, TYPENAME, DCITCODE, DICTNAME, DESCRITPION, VALIDATED, MEMO, BAE001, BAE002, BAE003, BAE004)
values (seq_dictid.nextval, 'STATE', '�û�״̬', '1', '����', '�û�״̬', '1', '', SYSDATE, '1', SYSDATE, '1');
insert into SYS_DICT (DICTID, DICTTYPE, TYPENAME, DCITCODE, DICTNAME, DESCRITPION, VALIDATED, MEMO, BAE001, BAE002, BAE003, BAE004)
values (seq_dictid.nextval, 'STATE', '�û�״̬', '2', '��ֹ��¼', '�û�״̬', '1', '', SYSDATE, '1', SYSDATE, '1');
insert into SYS_DICT (DICTID, DICTTYPE, TYPENAME, DCITCODE, DICTNAME, DESCRITPION, VALIDATED, MEMO, BAE001, BAE002, BAE003, BAE004)
values (seq_dictid.nextval, 'STATE', '�û�״̬', '3', '��ע��', '�û�״̬', '1', '', SYSDATE, '1', SYSDATE, '1');

--�û�״̬
delete from SYS_DICT where DICTTYPE = 'USERTYPE';
insert into SYS_DICT (DICTID, DICTTYPE, TYPENAME, DCITCODE, DICTNAME, DESCRITPION, VALIDATED, MEMO, BAE001, BAE002, BAE003, BAE004)
values (seq_dictid.nextval, 'USERTYPE', '�û����', '1', '��̨����', '�û����', '1', '', SYSDATE, '1', SYSDATE, '1');
insert into SYS_DICT (DICTID, DICTTYPE, TYPENAME, DCITCODE, DICTNAME, DESCRITPION, VALIDATED, MEMO, BAE001, BAE002, BAE003, BAE004)
values (seq_dictid.nextval, 'USERTYPE', '�û����', '2', 'ǰ̨ע��', '�û����', '1', '', SYSDATE, '1', SYSDATE, '1');
insert into SYS_DICT (DICTID, DICTTYPE, TYPENAME, DCITCODE, DICTNAME, DESCRITPION, VALIDATED, MEMO, BAE001, BAE002, BAE003, BAE004)
values (seq_dictid.nextval, 'USERTYPE', '�û����', '3', 'QQ��¼', '�û����', '1', '', SYSDATE, '1', SYSDATE, '1');
insert into SYS_DICT (DICTID, DICTTYPE, TYPENAME, DCITCODE, DICTNAME, DESCRITPION, VALIDATED, MEMO, BAE001, BAE002, BAE003, BAE004)
values (seq_dictid.nextval, 'USERTYPE', '�û����', '4', '΢�ŵ�¼', '�û����', '1', '', SYSDATE, '1', SYSDATE, '1');
insert into SYS_DICT (DICTID, DICTTYPE, TYPENAME, DCITCODE, DICTNAME, DESCRITPION, VALIDATED, MEMO, BAE001, BAE002, BAE003, BAE004)
values (seq_dictid.nextval, 'USERTYPE', '�û����', '5', '΢����¼', '�û����', '1', '', SYSDATE, '1', SYSDATE, '1');

--�Ƿ��־
delete from SYS_DICT WHERE DICTTYPE = 'SFBZ';
insert into SYS_DICT (DICTID, DICTTYPE, TYPENAME, DCITCODE, DICTNAME, DESCRITPION, VALIDATED, MEMO, BAE001, BAE002, BAE003, BAE004)
values (seq_dictid.nextval, 'SFBZ', '�Ƿ��־', '1', '��', '�Ƿ��־', '1', '', SYSDATE, '1', SYSDATE, '1');
insert into SYS_DICT (DICTID, DICTTYPE, TYPENAME, DCITCODE, DICTNAME, DESCRITPION, VALIDATED, MEMO, BAE001, BAE002, BAE003, BAE004)
values (seq_dictid.nextval, 'SFBZ', '�Ƿ��־', '0', '��', '�Ƿ��־', '1', '', SYSDATE, '1', SYSDATE, '1');

commit;