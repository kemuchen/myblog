--有效标志
delete from SYS_DICT WHERE DICTTYPE = 'VALIDATED';
insert into SYS_DICT (DICTID, DICTTYPE, TYPENAME, DCITCODE, DICTNAME, DESCRITPION, VALIDATED, MEMO, BAE001, BAE002, BAE003, BAE004)
values (seq_dictid.nextval, 'VALIDATED', '有效标志', '1', '有效', '有效标志', '1', '', SYSDATE, '1', SYSDATE, '1');
insert into SYS_DICT (DICTID, DICTTYPE, TYPENAME, DCITCODE, DICTNAME, DESCRITPION, VALIDATED, MEMO, BAE001, BAE002, BAE003, BAE004)
values (seq_dictid.nextval, 'VALIDATED', '有效标志', '0', '无效', '有效标志', '1', '', SYSDATE, '1', SYSDATE, '1');

--系统类别
delete from SYS_DICT where DICTTYPE = 'SUBSYSTYPE';
insert into SYS_DICT (DICTID, DICTTYPE, TYPENAME, DCITCODE, DICTNAME, DESCRITPION, VALIDATED, MEMO, BAE001, BAE002, BAE003, BAE004)
values (seq_dictid.nextval, 'SUBSYSTYPE', '系统类别', 'XTGL', '系统管理', '系统类别', '1', '', SYSDATE, '1', SYSDATE, '1');
insert into SYS_DICT (DICTID, DICTTYPE, TYPENAME, DCITCODE, DICTNAME, DESCRITPION, VALIDATED, MEMO, BAE001, BAE002, BAE003, BAE004)
values (seq_dictid.nextval, 'SUBSYSTYPE', '系统类别', 'BLOG', '博客系统', '系统类别', '1', '', SYSDATE, '1', SYSDATE, '1');

--菜单类别
delete from SYS_DICT where DICTTYPE = 'RIGHTTYPE';
insert into SYS_DICT (DICTID, DICTTYPE, TYPENAME, DCITCODE, DICTNAME, DESCRITPION, VALIDATED, MEMO, BAE001, BAE002, BAE003, BAE004)
values (seq_dictid.nextval, 'RIGHTTYPE', '权限类别', '1', '菜单权限', '权限类别', '1', '', SYSDATE, '1', SYSDATE, '1');
insert into SYS_DICT (DICTID, DICTTYPE, TYPENAME, DCITCODE, DICTNAME, DESCRITPION, VALIDATED, MEMO, BAE001, BAE002, BAE003, BAE004)
values (seq_dictid.nextval, 'RIGHTTYPE', '权限类别', '2', '操作权限', '权限类别', '1', '', SYSDATE, '1', SYSDATE, '1');

--菜单级别
delete from SYS_DICT where DICTTYPE = 'RIGHTLEVEL';
insert into SYS_DICT (DICTID, DICTTYPE, TYPENAME, DCITCODE, DICTNAME, DESCRITPION, VALIDATED, MEMO, BAE001, BAE002, BAE003, BAE004)
values (seq_dictid.nextval, 'RIGHTLEVEL', '菜单级别', '1', '一级菜单', '菜单级别', '1', '', SYSDATE, '1', SYSDATE, '1');
insert into SYS_DICT (DICTID, DICTTYPE, TYPENAME, DCITCODE, DICTNAME, DESCRITPION, VALIDATED, MEMO, BAE001, BAE002, BAE003, BAE004)
values (seq_dictid.nextval, 'RIGHTLEVEL', '菜单级别', '2', '二级菜单', '菜单级别', '1', '', SYSDATE, '1', SYSDATE, '1');

--用户状态
delete from SYS_DICT where DICTTYPE = 'STATE';
insert into SYS_DICT (DICTID, DICTTYPE, TYPENAME, DCITCODE, DICTNAME, DESCRITPION, VALIDATED, MEMO, BAE001, BAE002, BAE003, BAE004)
values (seq_dictid.nextval, 'STATE', '用户状态', '1', '正常', '用户状态', '1', '', SYSDATE, '1', SYSDATE, '1');
insert into SYS_DICT (DICTID, DICTTYPE, TYPENAME, DCITCODE, DICTNAME, DESCRITPION, VALIDATED, MEMO, BAE001, BAE002, BAE003, BAE004)
values (seq_dictid.nextval, 'STATE', '用户状态', '2', '禁止登录', '用户状态', '1', '', SYSDATE, '1', SYSDATE, '1');
insert into SYS_DICT (DICTID, DICTTYPE, TYPENAME, DCITCODE, DICTNAME, DESCRITPION, VALIDATED, MEMO, BAE001, BAE002, BAE003, BAE004)
values (seq_dictid.nextval, 'STATE', '用户状态', '3', '已注销', '用户状态', '1', '', SYSDATE, '1', SYSDATE, '1');

--用户状态
delete from SYS_DICT where DICTTYPE = 'USERTYPE';
insert into SYS_DICT (DICTID, DICTTYPE, TYPENAME, DCITCODE, DICTNAME, DESCRITPION, VALIDATED, MEMO, BAE001, BAE002, BAE003, BAE004)
values (seq_dictid.nextval, 'USERTYPE', '用户类别', '1', '后台新增', '用户类别', '1', '', SYSDATE, '1', SYSDATE, '1');
insert into SYS_DICT (DICTID, DICTTYPE, TYPENAME, DCITCODE, DICTNAME, DESCRITPION, VALIDATED, MEMO, BAE001, BAE002, BAE003, BAE004)
values (seq_dictid.nextval, 'USERTYPE', '用户类别', '2', '前台注册', '用户类别', '1', '', SYSDATE, '1', SYSDATE, '1');
insert into SYS_DICT (DICTID, DICTTYPE, TYPENAME, DCITCODE, DICTNAME, DESCRITPION, VALIDATED, MEMO, BAE001, BAE002, BAE003, BAE004)
values (seq_dictid.nextval, 'USERTYPE', '用户类别', '3', 'QQ登录', '用户类别', '1', '', SYSDATE, '1', SYSDATE, '1');
insert into SYS_DICT (DICTID, DICTTYPE, TYPENAME, DCITCODE, DICTNAME, DESCRITPION, VALIDATED, MEMO, BAE001, BAE002, BAE003, BAE004)
values (seq_dictid.nextval, 'USERTYPE', '用户类别', '4', '微信登录', '用户类别', '1', '', SYSDATE, '1', SYSDATE, '1');
insert into SYS_DICT (DICTID, DICTTYPE, TYPENAME, DCITCODE, DICTNAME, DESCRITPION, VALIDATED, MEMO, BAE001, BAE002, BAE003, BAE004)
values (seq_dictid.nextval, 'USERTYPE', '用户类别', '5', '微博登录', '用户类别', '1', '', SYSDATE, '1', SYSDATE, '1');

--是否标志
delete from SYS_DICT WHERE DICTTYPE = 'SFBZ';
insert into SYS_DICT (DICTID, DICTTYPE, TYPENAME, DCITCODE, DICTNAME, DESCRITPION, VALIDATED, MEMO, BAE001, BAE002, BAE003, BAE004)
values (seq_dictid.nextval, 'SFBZ', '是否标志', '1', '是', '是否标志', '1', '', SYSDATE, '1', SYSDATE, '1');
insert into SYS_DICT (DICTID, DICTTYPE, TYPENAME, DCITCODE, DICTNAME, DESCRITPION, VALIDATED, MEMO, BAE001, BAE002, BAE003, BAE004)
values (seq_dictid.nextval, 'SFBZ', '是否标志', '0', '否', '是否标志', '1', '', SYSDATE, '1', SYSDATE, '1');

commit;