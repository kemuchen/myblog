--���͹���
delete from sys_right where rightid = '02';
insert into sys_right(rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, memo, bae001, bae002, bae003, bae004)
values('02', 'BLOG', '���͹���', '1', '1', '', 'layui-icon-set-fill', 2, '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '0201';
insert into sys_right(rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, memo, bae001, bae002, bae003, bae004)
values('0201', 'BLOG', '���¹���', '2', '1', '/blog/articleManager', 'layui-icon-set-fill', 1, '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '0202';
insert into sys_right(rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, memo, bae001, bae002, bae003, bae004)
values('0202', 'BLOG', '�������', '2', '1', '/blog/flgl', 'layui-icon-set-fill', 2, '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '0203';
insert into sys_right(rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, memo, bae001, bae002, bae003, bae004)
values('0203', 'BLOG', '��ǩ����', '2', '1', '/blog/bqgl', 'layui-icon-set-fill', 3, '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '0204';
insert into sys_right(rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, memo, bae001, bae002, bae003, bae004)
values('0204', 'BLOG', 'ʱ���߹���', '2', '1', '/blog/sjxgl', 'layui-icon-set-fill', 4, '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '0205';
insert into sys_right(rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, memo, bae001, bae002, bae003, bae004)
values('0205', 'BLOG', '������', '2', '1', '/blog/xcgl', 'layui-icon-set-fill', 5, '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '0206';
insert into sys_right(rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, memo, bae001, bae002, bae003, bae004)
values('0206', 'BLOG', '���ֹ���', '2', '1', '/blog/yygl', 'layui-icon-set-fill', 6, '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '0207';
insert into sys_right(rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, memo, bae001, bae002, bae003, bae004)
values('0207', 'BLOG', '�������', '2', '1', '/blog/dsgl', 'layui-icon-set-fill', 7, '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '0208';
insert into sys_right(rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, memo, bae001, bae002, bae003, bae004)
values('0208', 'BLOG', '����ϵͳ���ܹ���', '2', '1', '/blog/bkxtjsgl', 'layui-icon-set-fill', 8, '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '0209';
insert into sys_right(rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, memo, bae001, bae002, bae003, bae004)
values('0209', 'BLOG', '���˼�����', '2', '1', '/blog/grjjgl', 'layui-icon-set-fill', 9, '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '0210';
insert into sys_right(rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, memo, bae001, bae002, bae003, bae004)
values('0210', 'BLOG', '�������ӹ���', '2', '1', '/blog/yqljgl', 'layui-icon-set-fill', 10, '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '0211';
insert into sys_right(rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, memo, bae001, bae002, bae003, bae004)
values('0211', 'BLOG', '�ֲ�����', '2', '1', '/blog/lbgl', 'layui-icon-set-fill', 11, '1', '', sysdate, 1, sysdate, 1);
commit;