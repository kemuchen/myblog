--������ռ�
create tablespace table_blog datafile 'E:\oracle\product\10.2.0\oradata\muchen\table_blog.dbf' size 10m;
create tablespace index_blog datafile 'E:\oracle\product\10.2.0\oradata\muchen\index_blog.dbf' size 10m;

--�½��û� blog
create user blog identified by blog default tablespace table_blog;

--���û���Ȩ
grant connect, resource, dba to blog;