--文章ID序列
create sequence SEQ_ARTICLEID
minvalue 1
maxvalue 999999999999
start with 1
increment by 1
nocache;

--文章分类ID序列
create sequence SEQ_TYPEID
minvalue 1
maxvalue 999999999999
start with 1
increment by 1
nocache;

--文章标签ID
create sequence SEQ_TAGID
minvalue 1
maxvalue 999999999999
start with 1
increment by 1
nocache;

--评论id序列
create sequence SEQ_CRITICID
minvalue 1
maxvalue 999999999999
start with 1
increment by 1
nocache;

--点赞信息表序列
create sequence SEQ_PRAISEORTREADID
minvalue 1
maxvalue 999999999999
start with 1
increment by 1
nocache;