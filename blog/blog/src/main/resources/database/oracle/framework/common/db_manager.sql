--创建表空间
create tablespace table_blog datafile 'E:\oracle\product\10.2.0\oradata\muchen\table_blog.dbf' size 10m;
create tablespace index_blog datafile 'E:\oracle\product\10.2.0\oradata\muchen\index_blog.dbf' size 10m;

--新建用户 blog
create user blog identified by blog default tablespace table_blog;

--给用户授权
grant connect, resource, dba to blog;