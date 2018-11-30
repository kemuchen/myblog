--博客管理
delete from sys_right where rightid = '02';
insert into sys_right(rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, memo, bae001, bae002, bae003, bae004)
values('02', 'BLOG', '博客管理', '1', '1', '', 'layui-icon-set-fill', 2, '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '0201';
insert into sys_right(rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, memo, bae001, bae002, bae003, bae004)
values('0201', 'BLOG', '文章管理', '2', '1', '/blog/articleManager', 'layui-icon-set-fill', 1, '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '0202';
insert into sys_right(rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, memo, bae001, bae002, bae003, bae004)
values('0202', 'BLOG', '分类管理', '2', '1', '/blog/flgl', 'layui-icon-set-fill', 2, '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '0203';
insert into sys_right(rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, memo, bae001, bae002, bae003, bae004)
values('0203', 'BLOG', '标签管理', '2', '1', '/blog/bqgl', 'layui-icon-set-fill', 3, '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '0204';
insert into sys_right(rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, memo, bae001, bae002, bae003, bae004)
values('0204', 'BLOG', '时间线管理', '2', '1', '/blog/sjxgl', 'layui-icon-set-fill', 4, '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '0205';
insert into sys_right(rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, memo, bae001, bae002, bae003, bae004)
values('0205', 'BLOG', '相册管理', '2', '1', '/blog/xcgl', 'layui-icon-set-fill', 5, '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '0206';
insert into sys_right(rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, memo, bae001, bae002, bae003, bae004)
values('0206', 'BLOG', '音乐管理', '2', '1', '/blog/yygl', 'layui-icon-set-fill', 6, '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '0207';
insert into sys_right(rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, memo, bae001, bae002, bae003, bae004)
values('0207', 'BLOG', '读书管理', '2', '1', '/blog/dsgl', 'layui-icon-set-fill', 7, '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '0208';
insert into sys_right(rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, memo, bae001, bae002, bae003, bae004)
values('0208', 'BLOG', '博客系统介绍管理', '2', '1', '/blog/bkxtjsgl', 'layui-icon-set-fill', 8, '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '0209';
insert into sys_right(rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, memo, bae001, bae002, bae003, bae004)
values('0209', 'BLOG', '个人简介管理', '2', '1', '/blog/grjjgl', 'layui-icon-set-fill', 9, '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '0210';
insert into sys_right(rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, memo, bae001, bae002, bae003, bae004)
values('0210', 'BLOG', '友情链接管理', '2', '1', '/blog/yqljgl', 'layui-icon-set-fill', 10, '1', '', sysdate, 1, sysdate, 1);
delete from sys_right where rightid = '0211';
insert into sys_right(rightid, subsystype, rightname, rightlevel, righttype, url, icon, sortno, validated, memo, bae001, bae002, bae003, bae004)
values('0211', 'BLOG', '轮播管理', '2', '1', '/blog/lbgl', 'layui-icon-set-fill', 11, '1', '', sysdate, 1, sysdate, 1);
commit;