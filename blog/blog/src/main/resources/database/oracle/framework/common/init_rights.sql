--ϵͳ����
delete from sys_right where rightid = '01';
insert into sys_right(rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, memo, bae001, bae002, bae003, bae004)
values('01', 'XTGL', 'ϵͳ����', '1', '1', '', 'layui-icon-set-fill', 1, '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '0101';
insert into sys_right(rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, memo, bae001, bae002, bae003, bae004)
values('0101', 'XTGL', '�˵�����', '2', '1', '/xtgl/right', 'layui-icon-set-fill', 1, '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '0102';
insert into sys_right(rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, memo, bae001, bae002, bae003, bae004)
values('0102', 'XTGL', '�û�����', '2', '1', '/xtgl/user', 'layui-icon-set-fill', 2, '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '0103';
insert into sys_right(rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, memo, bae001, bae002, bae003, bae004)
values('0103', 'XTGL', '��ɫ����', '2', '1', '/xtgl/role', 'layui-icon-set-fill', 3, '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '0104';
insert into sys_right(rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, memo, bae001, bae002, bae003, bae004)
values('0104', 'XTGL', '��¼��ʷ', '2', '1', '/xtgl/loginhistory', 'layui-icon-set-fill', 4, '1', '', sysdate, 1, sysdate, 1);

--����Ȩ��
delete from sys_right where rightid = '90';
insert into sys_right (rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, authed, memo, bae001, bae002, bae003, bae004)
values ('90', 'XTGL', '����Ȩ��һ���˵�', '1', '2', '', 'layui-icon-rate-half', 5, '1', '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '9001';
insert into sys_right (rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, authed, memo, bae001, bae002, bae003, bae004)
values ('9001', 'XTGL', '��̨��ҳ����Ȩ��', '2', '2', '/xtgl/index', 'layui-icon-rate-half', 10, '1', '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '9002';
insert into sys_right (rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, authed, memo, bae001, bae002, bae003, bae004)
values ('9002', 'XTGL', '��̨������ҳ���������Ȩ��', '2', '2', '/xtgl/index/main', 'layui-icon-rate-half', 10, '1', '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '9003';
insert into sys_right (rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, authed, memo, bae001, bae002, bae003, bae004)
values ('9003', 'XTGL', '��ҳ��ѯȨ��', '2', '2', '/xtgl/getAllRights', 'layui-icon-rate-half', 10, '1', '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '9004';
insert into sys_right (rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, authed, memo, bae001, bae002, bae003, bae004)
values ('9004', 'XTGL', '����Ȩ����Ϣ', '2', '2', '/xtgl/saveRight', 'layui-icon-rate-half', 10, '1', '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '9005';
insert into sys_right (rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, authed, memo, bae001, bae002, bae003, bae004)
values ('9005', 'XTGL', 'ɾ��Ȩ����Ϣ', '2', '2', '/xtgl/deleteRight', 'layui-icon-rate-half', 10, '1', '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '9006';
insert into sys_right (rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, authed, memo, bae001, bae002, bae003, bae004)
values ('9006', 'XTGL', '��ҳ��ѯ��ɫ', '2', '2', '/xtgl/getAllRoles', 'layui-icon-rate-half', 10, '1', '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '9007';
insert into sys_right (rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, authed, memo, bae001, bae002, bae003, bae004)
values ('9007', 'XTGL', '�����ɫ��Ϣ', '2', '2', '/xtgl/saveRole', 'layui-icon-rate-half', 10, '1', '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '9008';
insert into sys_right (rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, authed, memo, bae001, bae002, bae003, bae004)
values ('9008', 'XTGL', 'ɾ����ɫ��Ϣ', '2', '2', '/xtgl/deleteRole', 'layui-icon-rate-half', 10, '1', '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '9009';
insert into sys_right (rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, authed, memo, bae001, bae002, bae003, bae004)
values ('9009', 'XTGL', '��ɫ��Ȩ', '2', '2', '/xtgl/authorizeRole', 'layui-icon-rate-half', 10, '1', '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '9010';
insert into sys_right (rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, authed, memo, bae001, bae002, bae003, bae004)
values ('9010', 'XTGL', '��ɫȡ����Ȩ', '2', '2', '/xtgl/unAuthorizeRole', 'layui-icon-rate-half', 10, '1', '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '9011';
insert into sys_right (rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, authed, memo, bae001, bae002, bae003, bae004)
values ('9011', 'XTGL', '��ѯ��ɫ����Ȩ��', '2', '2', '/xtgl/getAuthorizes', 'layui-icon-rate-half', 10, '1', '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '9012';
insert into sys_right (rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, authed, memo, bae001, bae002, bae003, bae004)
values ('9012', 'XTGL', '��ѯ��ɫδӵ��Ȩ��', '2', '2', '/xtgl/getUnAuthorizes', 'layui-icon-rate-half', 10, '1', '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '9013';
insert into sys_right (rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, authed, memo, bae001, bae002, bae003, bae004)
values ('9013', 'XTGL', '��ҳ��ѯ�û���Ϣ', '2', '2', '/xtgl/getAllUsers', 'layui-icon-rate-half', 10, '1', '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '9014';
insert into sys_right (rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, authed, memo, bae001, bae002, bae003, bae004)
values ('9014', 'XTGL', '�����û���Ϣ', '2', '2', '/xtgl/saveUser', 'layui-icon-rate-half', 10, '1', '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '9015';
insert into sys_right (rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, authed, memo, bae001, bae002, bae003, bae004)
values ('9015', 'XTGL', 'ɾ���û���Ϣ', '2', '2', '/xtgl/deleteUser', 'layui-icon-rate-half', 10, '1', '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '9016';
insert into sys_right (rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, authed, memo, bae001, bae002, bae003, bae004)
values ('9016', 'XTGL', '��ѯ�û�δӵ�еĽ�ɫ', '2', '2', '/xtgl/getUserUnAuthorizes', 'layui-icon-rate-half', 10, '1', '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '9017';
insert into sys_right (rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, authed, memo, bae001, bae002, bae003, bae004)
values ('9017', 'XTGL', '��ѯ�û���ӵ�еĽ�ɫ', '2', '2', '/xtgl/getUserAuthorizes', 'layui-icon-rate-half', 10, '1', '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '9018';
insert into sys_right (rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, authed, memo, bae001, bae002, bae003, bae004)
values ('9018', 'XTGL', '�û���Ȩ', '2', '2', '/xtgl/authorizeUserRole', 'layui-icon-rate-half', 10, '1', '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '9019';
insert into sys_right (rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, authed, memo, bae001, bae002, bae003, bae004)
values ('9019', 'XTGL', 'ȡ���û���Ȩ', '2', '2', '/xtgl/unAuthorizeUserRole', 'layui-icon-rate-half', 10, '1', '1', '', sysdate, 1, sysdate, 1);
commit;