--insert user
delete from sys_user where loginid = 'admin';
insert into sys_user(USERID, USERNAME, LOGINID, PASSWORD, USERTYPE, EMAIL, TELEPHONE, STATE, LOGINFAIL, LOGINTIME, PHOTO, MEMO)
values(SEQ_USERID.nextval, '超级管理员', 'admin', 'a', '1', '', '', '1', '', '', '', '脚本生成');

-- insert role
delete from sys_role where rolename = 'admin';
insert into sys_role(ROLEID, ROLENAME, DESCRIPTION, VALIDATED, MEMO)
values(SEQ_ROLEID.nextval, 'admin', '超级管理员', '1', '脚本生成');

-- insert role2right
delete from sys_role2right where roleid = (select ROLEID from sys_role where rolename = 'admin');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '01', '1', '脚本生成');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '0101', '1', '脚本生成');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '0102', '1', '脚本生成');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '0103', '1', '脚本生成');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '0104', '1', '脚本生成');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '90', '1', '脚本生成');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '9001', '1', '脚本生成');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '9002', '1', '脚本生成');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '9003', '1', '脚本生成');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '9004', '1', '脚本生成');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '9005', '1', '脚本生成');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '9006', '1', '脚本生成');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '9007', '1', '脚本生成');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '9008', '1', '脚本生成');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '9009', '1', '脚本生成');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '9010', '1', '脚本生成');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '9011', '1', '脚本生成');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '9012', '1', '脚本生成');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '9013', '1', '脚本生成');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '9014', '1', '脚本生成');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '9015', '1', '脚本生成');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '9016', '1', '脚本生成');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '9017', '1', '脚本生成');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '9018', '1', '脚本生成');
insert into sys_role2right(ROLE2RIGHTID, ROLEID, RIGHTID, VALIDATED, MEMO)
values(SEQ_ROLE2RIGHTID.nextval, (select ROLEID from sys_role where rolename = 'admin'), '9018', '1', '脚本生成');

-- insert user2role
delete from sys_user2role where userid = (select USERID from sys_user where LOGINID = 'admin') 
   and roleid = (select ROLEID from sys_role where rolename = 'admin');
insert into sys_user2role(USER2ROLEID, USERID, ROLEID, VALIDATED, MEMO)
values(SEQ_USER2ROLEID.nextval, (select USERID from sys_user where LOGINID = 'admin'), 
       (select ROLEID from sys_role where rolename = 'admin'), '1', '脚本生成');
commit;