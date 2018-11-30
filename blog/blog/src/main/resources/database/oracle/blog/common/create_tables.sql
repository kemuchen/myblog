--文章信息表
create table BLOG_ARTICLE
(
   ARTICLEID    NUMBER(12),
   TITLE        VARCHAR2(500) not null,
   ABSTRACT     VARCHAR2(4000) not null,
   TYPEID       VARCHAR2(200) not null,
   TAGID  	    VARCHAR2(200),
   SOURCE       VARCHAR2(6),
   PHOTO        VARCHAR2(100),
   CONTENT      BLOB,
   SFFB         VARCHAR2(6),
   AUTHOR       VARCHAR2(50),
   PRAISECOUNT  NUMBER(8) default 0,
   TREADCOUNT   NUMBER(8) default 0,
   CRITICCOUNT  NUMBER(8) default 0,
   READCOUNT    NUMBER(8) default 0,
   VALIDATED    VARCHAR2(6) default '1',
   MEMO         VARCHAR2(200),
   BAE001       DATE,
   BAE002       NUMBER(12),
   BAE003       DATE,
   BAE004       NUMBER(12),
   constraint PK_ARTICLE_ARTICLEID primary key (ARTICLEID)
) tablespace &&tbsname_data_blog;
comment on table BLOG_ARTICLE is '文章信息表';
comment on column BLOG_ARTICLE.ARTICLEID is '文章id';
comment on column BLOG_ARTICLE.TITLE is '文章标题';
comment on column BLOG_ARTICLE.ABSTRACT is '文章摘要';
comment on column BLOG_ARTICLE.TYPEID is '文章分类';
comment on column BLOG_ARTICLE.TAGID is '文章标签';
comment on column BLOG_ARTICLE.SOURCE is '文章来源';
comment on column BLOG_ARTICLE.PHOTO is '文章封面';
comment on column BLOG_ARTICLE.CONTENT is '文章内容';
comment on column BLOG_ARTICLE.SFFB is '是否发布';
comment on column BLOG_ARTICLE.AUTHOR is '作者';
comment on column BLOG_ARTICLE.PRAISECOUNT is '点赞数量';
comment on column BLOG_ARTICLE.TREADCOUNT is '喝倒彩数量';
comment on column BLOG_ARTICLE.CRITICCOUNT is '评论数量';
comment on column BLOG_ARTICLE.READCOUNT is '阅读数量';
comment on column BLOG_ARTICLE.VALIDATED is '有效标志';
comment on column BLOG_ARTICLE.MEMO is '备注';
comment on column BLOG_ARTICLE.BAE001 is '创建时间';
comment on column BLOG_ARTICLE.BAE002 is '创建人';
comment on column BLOG_ARTICLE.BAE003 is '最近修改时间';
comment on column BLOG_ARTICLE.BAE004 is '最近修改人';

-- 文章分类信息表
create table BLOG_ARTICLETYPE
(
   TYPEID    NUMBER(12),
   TYPENAME  VARCHAR2(20),
   VALIDATED VARCHAR2(6) default '1',
   MEMO      VARCHAR2(200),
   BAE001    DATE,
   BAE002    NUMBER(12),
   BAE003    DATE,
   BAE004    NUMBER(12),
   constraint PK_ARTICLETYPE_TYPEID primary key (TYPEID)
) tablespace &&tbsname_data_blog;
comment on table BLOG_ARTICLETYPE is '文章分类信息表';
comment on column BLOG_ARTICLETYPE.TYPEID is '文章分类id';
comment on column BLOG_ARTICLETYPE.TYPENAME is '文章分类名称';
comment on column BLOG_ARTICLETYPE.VALIDATED is '有效标志';
comment on column BLOG_ARTICLETYPE.MEMO is '备注';
comment on column BLOG_ARTICLETYPE.BAE001 is '创建时间';
comment on column BLOG_ARTICLETYPE.BAE002 is '创建人';
comment on column BLOG_ARTICLETYPE.BAE003 is '最近修改时间';
comment on column BLOG_ARTICLETYPE.BAE004 is '最近修改人';

-- 文章标签信息表
create table BLOG_ARTICLETAG
(
   TAGID     NUMBER(12),
   TAGNAME   VARCHAR2(20),
   VALIDATED VARCHAR2(6) default '1',
   MEMO      VARCHAR2(200),
   BAE001    DATE,
   BAE002    NUMBER(12),
   BAE003    DATE,
   BAE004    NUMBER(12),
   constraint PK_ARTICLETAG_TAGID primary key (TAGID)
) tablespace &&tbsname_data_blog;
comment on table BLOG_ARTICLETAG is '文章标签信息表';
comment on column BLOG_ARTICLETAG.TAGID is '文章标签id';
comment on column BLOG_ARTICLETAG.TAGNAME is '文章标签名称';
comment on column BLOG_ARTICLETAG.VALIDATED is '有效标志';
comment on column BLOG_ARTICLETAG.MEMO is '备注';
comment on column BLOG_ARTICLETAG.BAE001 is '创建时间';
comment on column BLOG_ARTICLETAG.BAE002 is '创建人';
comment on column BLOG_ARTICLETAG.BAE003 is '最近修改时间';
comment on column BLOG_ARTICLETAG.BAE004 is '最近修改人';

-- 文章评论表
create table BLOG_CRITIC
(
   CRITICID    NUMBER(12),
   USERID      NUMBER(12),
   ARTICLEID   NUMBER(12),
   CONTENT     BLOB,
   CRITICTIME  DATE,
   PARENTID    NUMBER(12) default 0,
   PRAISECOUNT NUMBER(8),
   TREADCOUNT  NUMBER(8),
   CRITICORMESSAGE VARCHAR2(6),
   VALIDATED   VARCHAR2(6),
   MEMO        VARCHAR2(200),
   BAE001      DATE,
   BAE002      NUMBER(12),
   BAE003      DATE,
   BAE004      NUMBER(12),
   constraint PK_CRITIC_CRITICID primary key (CRITICID)
) tablespace &&tbsname_data_blog;
comment on table BLOG_CRITIC is '文章评论表';
comment on column BLOG_CRITIC.CRITICID is '评论id';
comment on column BLOG_CRITIC.USERID is '评论人';
comment on column BLOG_CRITIC.ARTICLEID is '文章id';
comment on column BLOG_CRITIC.CONTENT is '评论内容';
comment on column BLOG_CRITIC.CRITICTIME is '评论时间';
comment on column BLOG_CRITIC.PARENTID is '父评论id';
comment on column BLOG_CRITIC.PRAISECOUNT is '评论赞数';
comment on column BLOG_CRITIC.TREADCOUNT is '评论踩数';
comment on column BLOG_CRITIC.CRITICORMESSAGE is '评论留言标志';
comment on column BLOG_CRITIC.VALIDATED is '有效标志';
comment on column BLOG_CRITIC.MEMO is '备注';
comment on column BLOG_CRITIC.BAE001 is '创建时间';
comment on column BLOG_CRITIC.BAE002 is '创建人';
comment on column BLOG_CRITIC.BAE003 is '最近修改时间';
comment on column BLOG_CRITIC.BAE004 is '最近修改人';

--点赞信息表
create table BLOG_PRAISEORTREAD
(
   ID        NUMBER(12),
   PRAORTRE  VARCHAR2(6),
   ARTORCRI  VARCHAR2(6),
   ARID      NUMBER(12),
   USERID    NUMBER(12),
   VALIDATED VARCHAR2(6),
   MEMO      VARCHAR2(200),
   BAE001    DATE,
   BAE002    NUMBER(12),
   BAE003    DATE,
   BAE004    NUMBER(12),
   constraint PK_PRAISEORTREAD_ID primary key (ID)
) tablespace &&tbsname_data_blog;
comment on table BLOG_PRAISEORTREAD is '点赞信息表';
comment on column BLOG_PRAISEORTREAD.PRAORTRE is '赞或者踩（1：赞，0：踩）';
comment on column BLOG_PRAISEORTREAD.ARTORCRI is '文章或者评论（1：文章，0：评论）';
comment on column BLOG_PRAISEORTREAD.ARID is '文章或者评论id';
comment on column BLOG_PRAISEORTREAD.USERID is '用户id';
comment on column BLOG_PRAISEORTREAD.VALIDATED is '有效标志';
comment on column BLOG_PRAISEORTREAD.BAE001 is '创建时间';
comment on column BLOG_PRAISEORTREAD.BAE002 is '创建人';
comment on column BLOG_PRAISEORTREAD.BAE003 is '最近修改时间';
comment on column BLOG_PRAISEORTREAD.BAE004 is '最近修改人';