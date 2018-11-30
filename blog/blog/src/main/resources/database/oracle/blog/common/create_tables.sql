--������Ϣ��
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
comment on table BLOG_ARTICLE is '������Ϣ��';
comment on column BLOG_ARTICLE.ARTICLEID is '����id';
comment on column BLOG_ARTICLE.TITLE is '���±���';
comment on column BLOG_ARTICLE.ABSTRACT is '����ժҪ';
comment on column BLOG_ARTICLE.TYPEID is '���·���';
comment on column BLOG_ARTICLE.TAGID is '���±�ǩ';
comment on column BLOG_ARTICLE.SOURCE is '������Դ';
comment on column BLOG_ARTICLE.PHOTO is '���·���';
comment on column BLOG_ARTICLE.CONTENT is '��������';
comment on column BLOG_ARTICLE.SFFB is '�Ƿ񷢲�';
comment on column BLOG_ARTICLE.AUTHOR is '����';
comment on column BLOG_ARTICLE.PRAISECOUNT is '��������';
comment on column BLOG_ARTICLE.TREADCOUNT is '�ȵ�������';
comment on column BLOG_ARTICLE.CRITICCOUNT is '��������';
comment on column BLOG_ARTICLE.READCOUNT is '�Ķ�����';
comment on column BLOG_ARTICLE.VALIDATED is '��Ч��־';
comment on column BLOG_ARTICLE.MEMO is '��ע';
comment on column BLOG_ARTICLE.BAE001 is '����ʱ��';
comment on column BLOG_ARTICLE.BAE002 is '������';
comment on column BLOG_ARTICLE.BAE003 is '����޸�ʱ��';
comment on column BLOG_ARTICLE.BAE004 is '����޸���';

-- ���·�����Ϣ��
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
comment on table BLOG_ARTICLETYPE is '���·�����Ϣ��';
comment on column BLOG_ARTICLETYPE.TYPEID is '���·���id';
comment on column BLOG_ARTICLETYPE.TYPENAME is '���·�������';
comment on column BLOG_ARTICLETYPE.VALIDATED is '��Ч��־';
comment on column BLOG_ARTICLETYPE.MEMO is '��ע';
comment on column BLOG_ARTICLETYPE.BAE001 is '����ʱ��';
comment on column BLOG_ARTICLETYPE.BAE002 is '������';
comment on column BLOG_ARTICLETYPE.BAE003 is '����޸�ʱ��';
comment on column BLOG_ARTICLETYPE.BAE004 is '����޸���';

-- ���±�ǩ��Ϣ��
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
comment on table BLOG_ARTICLETAG is '���±�ǩ��Ϣ��';
comment on column BLOG_ARTICLETAG.TAGID is '���±�ǩid';
comment on column BLOG_ARTICLETAG.TAGNAME is '���±�ǩ����';
comment on column BLOG_ARTICLETAG.VALIDATED is '��Ч��־';
comment on column BLOG_ARTICLETAG.MEMO is '��ע';
comment on column BLOG_ARTICLETAG.BAE001 is '����ʱ��';
comment on column BLOG_ARTICLETAG.BAE002 is '������';
comment on column BLOG_ARTICLETAG.BAE003 is '����޸�ʱ��';
comment on column BLOG_ARTICLETAG.BAE004 is '����޸���';

-- �������۱�
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
comment on table BLOG_CRITIC is '�������۱�';
comment on column BLOG_CRITIC.CRITICID is '����id';
comment on column BLOG_CRITIC.USERID is '������';
comment on column BLOG_CRITIC.ARTICLEID is '����id';
comment on column BLOG_CRITIC.CONTENT is '��������';
comment on column BLOG_CRITIC.CRITICTIME is '����ʱ��';
comment on column BLOG_CRITIC.PARENTID is '������id';
comment on column BLOG_CRITIC.PRAISECOUNT is '��������';
comment on column BLOG_CRITIC.TREADCOUNT is '���۲���';
comment on column BLOG_CRITIC.CRITICORMESSAGE is '�������Ա�־';
comment on column BLOG_CRITIC.VALIDATED is '��Ч��־';
comment on column BLOG_CRITIC.MEMO is '��ע';
comment on column BLOG_CRITIC.BAE001 is '����ʱ��';
comment on column BLOG_CRITIC.BAE002 is '������';
comment on column BLOG_CRITIC.BAE003 is '����޸�ʱ��';
comment on column BLOG_CRITIC.BAE004 is '����޸���';

--������Ϣ��
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
comment on table BLOG_PRAISEORTREAD is '������Ϣ��';
comment on column BLOG_PRAISEORTREAD.PRAORTRE is '�޻��߲ȣ�1���ޣ�0���ȣ�';
comment on column BLOG_PRAISEORTREAD.ARTORCRI is '���»������ۣ�1�����£�0�����ۣ�';
comment on column BLOG_PRAISEORTREAD.ARID is '���»�������id';
comment on column BLOG_PRAISEORTREAD.USERID is '�û�id';
comment on column BLOG_PRAISEORTREAD.VALIDATED is '��Ч��־';
comment on column BLOG_PRAISEORTREAD.BAE001 is '����ʱ��';
comment on column BLOG_PRAISEORTREAD.BAE002 is '������';
comment on column BLOG_PRAISEORTREAD.BAE003 is '����޸�ʱ��';
comment on column BLOG_PRAISEORTREAD.BAE004 is '����޸���';