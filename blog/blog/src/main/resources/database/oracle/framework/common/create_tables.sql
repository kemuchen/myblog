--����Ա��Ϣ��
create table SYS_USER
(
   USERID    NUMBER(12),
   USERNAME  VARCHAR2(50) not null,
   LOGINID   VARCHAR2(50) not null,
   PASSWORD  VARCHAR2(128),
   USERTYPE  VARCHAR2(6),
   EMAIL     VARCHAR2(100),
   TELEPHONE VARCHAR2(20),
   STATE     VARCHAR2(6),
   LOGINFAIL NUMBER(8),
   LOGINTIME DATE,
   PHOTO     VARCHAR2(200),
   MEMO      VARCHAR2(200),
   BAE001    DATE,
   BAE002    NUMBER(12),
   BAE003    DATE,
   BAE004    NUMBER(12),
   constraint PK_SYS_USER_USERID primary key (USERID)
) tablespace &&tbsname_data_blog;
comment on table SYS_USER is '�û���Ϣ��';
comment on column SYS_USER.USERID is '�û�ID';
comment on column SYS_USER.USERNAME is '����Ա����';
comment on column SYS_USER.LOGINID is '��¼id';
comment on column SYS_USER.PASSWORD is '��¼����';
comment on column SYS_USER.USERTYPE is '�û���𣨼��û���Դ��';
comment on column SYS_USER.EMAIL is '����';
comment on column SYS_USER.TELEPHONE is '��ϵ�绰';
comment on column SYS_USER.STATE is '״̬';
comment on column SYS_USER.LOGINFAIL is '��¼ʧ�ܴ���';
comment on column SYS_USER.LOGINTIME is '�����¼ʱ��';
comment on column SYS_USER.PHOTO is 'ͷ��';
comment on column SYS_USER.MEMO is '��ע';
comment on column SYS_USER.BAE001 is '����ʱ��';
comment on column SYS_USER.BAE002 is '������';
comment on column SYS_USER.BAE003 is '����޸�ʱ��';
comment on column SYS_USER.BAE004 is '����޸���';

--�˵���Ϣ��
create table SYS_RIGHT
(
   RIGHTID    VARCHAR2(12),
   SUBSYSTYPE VARCHAR2(6),
   RIGHTNAME  VARCHAR2(100),
   RIGHTTYPE  VARCHAR2(6),
   RIGHTLEVEL VARCHAR2(6),
   URL        VARCHAR2(100),
   ICON       VARCHAR2(100),
   SORTNO     NUMBER(3),
   VALIDATED  VARCHAR2(6) default '1',
   AUTHED     VARCHAR2(6) default '1',
   MEMO       VARCHAR2(200),
   BAE001     DATE,
   BAE002     NUMBER(12),
   BAE003     DATE,
   BAE004     NUMBER(12),
   constraint PK_SYS_RIGHT_RIGHTID primary key (RIGHTID)
) tablespace &&tbsname_data_blog;
comment on table SYS_RIGHT is '�˵���Ϣ��';
comment on column SYS_RIGHT.RIGHTID is '�˵�id';
comment on column SYS_RIGHT.SUBSYSTYPE is '��ϵͳ���';
comment on column SYS_RIGHT.RIGHTNAME is '�˵�����';
comment on column SYS_RIGHT.RIGHTTYPE is '�˵����';
comment on column SYS_RIGHT.RIGHTLEVEL is '�˵�����';
comment on column SYS_RIGHT.URL is '�˵�url';
comment on column SYS_RIGHT.ICON is '�˵�ͼ��';
comment on column SYS_RIGHT.SORTNO is '�����';
comment on column SYS_RIGHT.VALIDATED is '��Ч��־��1-��Ч��0-��Ч��';
comment on column SYS_RIGHT.AUTHED is '�Ƿ���Ҫ��֤��1-�ǣ�0-��';
comment on column SYS_RIGHT.MEMO is '��ע';
comment on column SYS_RIGHT.BAE001 is '����ʱ��';
comment on column SYS_RIGHT.BAE002 is '������';
comment on column SYS_RIGHT.BAE003 is '����޸�ʱ��';
comment on column SYS_RIGHT.BAE004 is '����޸���';

--��ɫ��Ϣ��
create table SYS_ROLE
(
   ROLEID       NUMBER(12),
   ROLENAME     VARCHAR2(100),
   DESCRIPTION  VARCHAR2(200),
   VALIDATED    VARCHAR2(6) default '1',
   MEMO         VARCHAR2(200),
   BAE001       DATE,
   BAE002       NUMBER(12),
   BAE003       DATE,
   BAE004       NUMBER(12),
   constraint PK_SYS_ROLE_ROLEID primary key (ROLEID)
) tablespace &&tbsname_data_blog;
comment on table SYS_ROLE is '��ɫ��Ϣ��';
comment on column SYS_ROLE.ROLEID is '��ɫID';
comment on column SYS_ROLE.ROLENAME is '��ɫ����';
comment on column SYS_ROLE.DESCRIPTION is '��ɫ˵��';
comment on column SYS_ROLE.VALIDATED is '��Ч��־��1-��Ч��0-��Ч��';
comment on column SYS_ROLE.MEMO is '��ע';
comment on column SYS_ROLE.BAE001 is '����ʱ��';
comment on column SYS_ROLE.BAE002 is '������';
comment on column SYS_ROLE.BAE003 is '����޸�ʱ��';
comment on column SYS_ROLE.BAE004 is '����޸���';

--��ɫȨ�ޱ�
create table SYS_ROLE2RIGHT
(
   ROLE2RIGHTID   NUMBER(12),
   ROLEID         NUMBER(12),
   RIGHTID        VARCHAR2(12),
   VALIDATED      VARCHAR2(6) default '1',
   MEMO           VARCHAR2(200),
   BAE001         DATE,
   BAE002         NUMBER(12),
   BAE003         DATE,
   BAE004         NUMBER(12),
   constraint PK_SYS_ROLE2RIGHTID primary key (ROLE2RIGHTID)
) tablespace &&tbsname_data_blog;
comment on table SYS_ROLE2RIGHT is '��ɫȨ�ޱ�';
comment on column SYS_ROLE2RIGHT.ROLE2RIGHTID is '��ɫȨ��ID';
comment on column SYS_ROLE2RIGHT.ROLEID is '��ɫID';
comment on column SYS_ROLE2RIGHT.RIGHTID is 'Ȩ��ID';
comment on column SYS_ROLE2RIGHT.VALIDATED is '��Ч��־��1-��Ч��0-��Ч��';
comment on column SYS_ROLE2RIGHT.MEMO is '��ע';
comment on column SYS_ROLE2RIGHT.BAE001 is '����ʱ��';
comment on column SYS_ROLE2RIGHT.BAE002 is '������';
comment on column SYS_ROLE2RIGHT.BAE003 is '����޸�ʱ��';
comment on column SYS_ROLE2RIGHT.BAE004 is '����޸���';

--����Ա��ɫ��
create table SYS_USER2ROLE
(
   USER2ROLEID    NUMBER(12),
   USERID         NUMBER(12),
   ROLEID         NUMBER(12),
   VALIDATED      VARCHAR2(6) default '1',
   MEMO           VARCHAR2(200),
   BAE001         DATE,
   BAE002         NUMBER(12),
   BAE003         DATE,
   BAE004         NUMBER(12),
   constraint PK_SYS_USER2ROLE primary key (USER2ROLEID)
) tablespace &&tbsname_data_blog;
comment on table SYS_USER2ROLE is '����Ա��ɫ��';
comment on column SYS_USER2ROLE.USER2ROLEID is '����Ա��ɫID';
comment on column SYS_USER2ROLE.USERID is '����ԱID';
comment on column SYS_USER2ROLE.ROLEID is '��ɫID';
comment on column SYS_USER2ROLE.VALIDATED is '��Ч��־��1-��Ч��0-��Ч��';
comment on column SYS_USER2ROLE.MEMO is '��ע';
comment on column SYS_USER2ROLE.BAE001 is '����ʱ��';
comment on column SYS_USER2ROLE.BAE002 is '������';
comment on column SYS_USER2ROLE.BAE003 is '����޸�ʱ��';
comment on column SYS_USER2ROLE.BAE004 is '����޸���';

--�ֵ�����
create table SYS_DICT
(
   DICTID      NUMBER(12),
   DICTTYPE    VARCHAR2(20),
   TYPENAME    VARCHAR2(20),
   DCITCODE    VARCHAR2(20),
   DICTNAME    VARCHAR2(20),
   DESCRITPION VARCHAR2(100),
   VALIDATED   VARCHAR2(6),
   MEMO        VARCHAR2(200),
   BAE001      DATE,
   BAE002      NUMBER(12),
   BAE003      DATE,
   BAE004      NUMBER(12),
   constraint PK_SYS_DICT primary key (DICTID)
) tablespace &&tbsname_data_blog;
comment on table SYS_DICT is '�ֵ����';
comment on column SYS_DICT.DICTID is '�ֵ���ID';
comment on column SYS_DICT.DICTTYPE is '�ֵ�������';
comment on column SYS_DICT.TYPENAME is '�ֵ�����������';
comment on column SYS_DICT.DCITCODE is '�ֵ������';
comment on column SYS_DICT.DICTNAME is '�ֵ�������';
comment on column SYS_DICT.DESCRITPION is '�ֵ�������';
comment on column SYS_DICT.VALIDATED is '��Ч��־';
comment on column SYS_DICT.MEMO is '��ע';
comment on column SYS_DICT.BAE001 is '����ʱ��';
comment on column SYS_DICT.BAE002 is '������';
comment on column SYS_DICT.BAE003 is '����޸�ʱ��';
comment on column SYS_DICT.BAE004 is '����޸���';

--��̬�ֵ�����
create table DYNAMIC_DICT_CONFIG
(
   CONFIGID    VARCHAR2(100),
   MULTSQL     VARCHAR2(4000),
   VALIDATED   VARCHAR2(6) default '1',
   DESCRIPTION VARCHAR2(4000),
   MEMO        VARCHAR2(200),
   BAE001      DATE,
   BAE002      NUMBER(12),
   BAE003      DATE,
   BAE004      NUMBER(12),
   constraint PK_DICT_CONFIGID primary key (CONFIGID)
) tablespace &&tbsname_data_blog;
comment on table DYNAMIC_DICT_CONFIG is '��̬�ֵ����ñ�';
comment on column DYNAMIC_DICT_CONFIG.CONFIGID is '����id';
comment on column DYNAMIC_DICT_CONFIG.MULTSQL is '���¼��ѯ���';
comment on column DYNAMIC_DICT_CONFIG.VALIDATED is '��Ч��־';
comment on column DYNAMIC_DICT_CONFIG.DESCRIPTION is '��̬�ֵ�����';
comment on column DYNAMIC_DICT_CONFIG.MEMO is '��ע';
comment on column DYNAMIC_DICT_CONFIG.BAE001 is '����ʱ��';
comment on column DYNAMIC_DICT_CONFIG.BAE002 is '������';
comment on column DYNAMIC_DICT_CONFIG.BAE003 is '����޸�ʱ��';
comment on column DYNAMIC_DICT_CONFIG.BAE004 is '����޸���';

--��ʱ�����ñ�
create table QUARTZ_JOB
(
   JOBID    VARCHAR2(12),
   JBOGROUP VARCHAR2(12),
   JOBTYPE  VARCHAR2(12),
   TASK     VARCHAR2(200),
   SCHEDULE VARCHAR2(20),
   VALIDATED VARCHAR2(6),
   MEMO     VARCHAR2(200)
) tablespace &&tbsname_data_blog;
comment on table QUARTZ_JOB is '��ʱ����������';
comment on column QUARTZ_JOB.JOBID is '����id';
comment on column QUARTZ_JOB.JBOGROUP is '�������';
comment on column QUARTZ_JOB.JOBTYPE is '��������';
comment on column QUARTZ_JOB.TASK is '��������';
comment on column QUARTZ_JOB.SCHEDULE is '������Ȱ���';
comment on column QUARTZ_JOB.VALIDATED is '��Ч��־';
comment on column QUARTZ_JOB.MEMO is '��ע';
