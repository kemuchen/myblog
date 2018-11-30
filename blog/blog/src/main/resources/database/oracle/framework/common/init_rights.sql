--系统管理
delete from sys_right where rightid = '01';
insert into sys_right(rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, memo, bae001, bae002, bae003, bae004)
values('01', 'XTGL', '系统管理', '1', '1', '', 'layui-icon-set-fill', 1, '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '0101';
insert into sys_right(rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, memo, bae001, bae002, bae003, bae004)
values('0101', 'XTGL', '菜单管理', '2', '1', '/xtgl/right', 'layui-icon-set-fill', 1, '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '0102';
insert into sys_right(rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, memo, bae001, bae002, bae003, bae004)
values('0102', 'XTGL', '用户管理', '2', '1', '/xtgl/user', 'layui-icon-set-fill', 2, '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '0103';
insert into sys_right(rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, memo, bae001, bae002, bae003, bae004)
values('0103', 'XTGL', '角色管理', '2', '1', '/xtgl/role', 'layui-icon-set-fill', 3, '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '0104';
insert into sys_right(rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, memo, bae001, bae002, bae003, bae004)
values('0104', 'XTGL', '登录历史', '2', '1', '/xtgl/loginhistory', 'layui-icon-set-fill', 4, '1', '', sysdate, 1, sysdate, 1);

--操作权限
delete from sys_right where rightid = '90';
insert into sys_right (rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, authed, memo, bae001, bae002, bae003, bae004)
values ('90', 'XTGL', '操作权限一级菜单', '1', '2', '', 'layui-icon-rate-half', 5, '1', '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '9001';
insert into sys_right (rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, authed, memo, bae001, bae002, bae003, bae004)
values ('9001', 'XTGL', '后台主页访问权限', '2', '2', '/xtgl/index', 'layui-icon-rate-half', 10, '1', '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '9002';
insert into sys_right (rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, authed, memo, bae001, bae002, bae003, bae004)
values ('9002', 'XTGL', '后台管理首页主区域访问权限', '2', '2', '/xtgl/index/main', 'layui-icon-rate-half', 10, '1', '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '9003';
insert into sys_right (rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, authed, memo, bae001, bae002, bae003, bae004)
values ('9003', 'XTGL', '分页查询权限', '2', '2', '/xtgl/getAllRights', 'layui-icon-rate-half', 10, '1', '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '9004';
insert into sys_right (rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, authed, memo, bae001, bae002, bae003, bae004)
values ('9004', 'XTGL', '保存权限信息', '2', '2', '/xtgl/saveRight', 'layui-icon-rate-half', 10, '1', '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '9005';
insert into sys_right (rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, authed, memo, bae001, bae002, bae003, bae004)
values ('9005', 'XTGL', '删除权限信息', '2', '2', '/xtgl/deleteRight', 'layui-icon-rate-half', 10, '1', '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '9006';
insert into sys_right (rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, authed, memo, bae001, bae002, bae003, bae004)
values ('9006', 'XTGL', '分页查询角色', '2', '2', '/xtgl/getAllRoles', 'layui-icon-rate-half', 10, '1', '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '9007';
insert into sys_right (rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, authed, memo, bae001, bae002, bae003, bae004)
values ('9007', 'XTGL', '保存角色信息', '2', '2', '/xtgl/saveRole', 'layui-icon-rate-half', 10, '1', '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '9008';
insert into sys_right (rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, authed, memo, bae001, bae002, bae003, bae004)
values ('9008', 'XTGL', '删除角色信息', '2', '2', '/xtgl/deleteRole', 'layui-icon-rate-half', 10, '1', '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '9009';
insert into sys_right (rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, authed, memo, bae001, bae002, bae003, bae004)
values ('9009', 'XTGL', '角色授权', '2', '2', '/xtgl/authorizeRole', 'layui-icon-rate-half', 10, '1', '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '9010';
insert into sys_right (rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, authed, memo, bae001, bae002, bae003, bae004)
values ('9010', 'XTGL', '角色取消授权', '2', '2', '/xtgl/unAuthorizeRole', 'layui-icon-rate-half', 10, '1', '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '9011';
insert into sys_right (rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, authed, memo, bae001, bae002, bae003, bae004)
values ('9011', 'XTGL', '查询角色已有权限', '2', '2', '/xtgl/getAuthorizes', 'layui-icon-rate-half', 10, '1', '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '9012';
insert into sys_right (rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, authed, memo, bae001, bae002, bae003, bae004)
values ('9012', 'XTGL', '查询角色未拥有权限', '2', '2', '/xtgl/getUnAuthorizes', 'layui-icon-rate-half', 10, '1', '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '9013';
insert into sys_right (rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, authed, memo, bae001, bae002, bae003, bae004)
values ('9013', 'XTGL', '分页查询用户信息', '2', '2', '/xtgl/getAllUsers', 'layui-icon-rate-half', 10, '1', '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '9014';
insert into sys_right (rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, authed, memo, bae001, bae002, bae003, bae004)
values ('9014', 'XTGL', '保存用户信息', '2', '2', '/xtgl/saveUser', 'layui-icon-rate-half', 10, '1', '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '9015';
insert into sys_right (rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, authed, memo, bae001, bae002, bae003, bae004)
values ('9015', 'XTGL', '删除用户信息', '2', '2', '/xtgl/deleteUser', 'layui-icon-rate-half', 10, '1', '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '9016';
insert into sys_right (rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, authed, memo, bae001, bae002, bae003, bae004)
values ('9016', 'XTGL', '查询用户未拥有的角色', '2', '2', '/xtgl/getUserUnAuthorizes', 'layui-icon-rate-half', 10, '1', '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '9017';
insert into sys_right (rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, authed, memo, bae001, bae002, bae003, bae004)
values ('9017', 'XTGL', '查询用户已拥有的角色', '2', '2', '/xtgl/getUserAuthorizes', 'layui-icon-rate-half', 10, '1', '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '9018';
insert into sys_right (rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, authed, memo, bae001, bae002, bae003, bae004)
values ('9018', 'XTGL', '用户授权', '2', '2', '/xtgl/authorizeUserRole', 'layui-icon-rate-half', 10, '1', '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '9019';
insert into sys_right (rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, authed, memo, bae001, bae002, bae003, bae004)
values ('9019', 'XTGL', '取消用户授权', '2', '2', '/xtgl/unAuthorizeUserRole', 'layui-icon-rate-half', 10, '1', '1', '', sysdate, 1, sysdate, 1);
commit;