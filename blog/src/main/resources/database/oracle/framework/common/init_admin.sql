--insert user
delete from sys_user where loginid = 'admin';
insert into sys_user(USERID, USERNAME, LOGINID, PASSWORD, USERTYPE, EMAIL, TELEPHONE, STATE, LOGINFAIL, LOGINTIME, PHOTO, MEMO)
values(SEQ_USERID.nextval, '��������Ա', 'admin', 'a', '1', '', '', '1', '', '', '', '�ű�����');

-- insert role
delete from sys_role where rolename = 'admin';
insert into sys_role(ROLEID, ROLENAME, DESCRIPTION, VALIDATED, MEMO)
values(SEQ_ROLEID.nextval, 'admin', '��������Ա', '1', '�ű�����');

-- insert role2right
delete from sys_role2right where roleid = (select ROLEID from sys_role where rolename = 'admin');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '01', '1', '�ű�����');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '0101', '1', '�ű�����');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '0102', '1', '�ű�����');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '0103', '1', '�ű�����');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '0104', '1', '�ű�����');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '90', '1', '�ű�����');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '9001', '1', '�ű�����');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '9002', '1', '�ű�����');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '9003', '1', '�ű�����');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '9004', '1', '�ű�����');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '9005', '1', '�ű�����');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '9006', '1', '�ű�����');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '9007', '1', '�ű�����');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '9008', '1', '�ű�����');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '9009', '1', '�ű�����');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '9010', '1', '�ű�����');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '9011', '1', '�ű�����');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '9012', '1', '�ű�����');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '9013', '1', '�ű�����');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '9014', '1', '�ű�����');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '9015', '1', '�ű�����');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '9016', '1', '�ű�����');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '9017', '1', '�ű�����');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '9018', '1', '�ű�����');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '9018', '1', '�ű�����');

-- insert user2role
delete from sys_user2role where userid = (select USERID from sys_user where LOGINID = 'admin') 
   and roleid = (select ROLEID from sys_role where rolename = 'admin');
insert into sys_user2role(USER2ROLEID, USERID, ROLEID, VALIDATED, MEMO)
values(SEQ_USER2ROLEID.nextval, (select USERID from sys_user where LOGINID = 'admin'), 
       (select ROLEID from sys_role where rolename = 'admin'), '1', '�ű�����');
commit;