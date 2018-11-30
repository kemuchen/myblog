--操作员信息表
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
comment on table SYS_USER is '用户信息表';
comment on column SYS_USER.USERID is '用户ID';
comment on column SYS_USER.USERNAME is '操作员姓名';
comment on column SYS_USER.LOGINID is '登录id';
comment on column SYS_USER.PASSWORD is '登录密码';
comment on column SYS_USER.USERTYPE is '用户类别（即用户来源）';
comment on column SYS_USER.EMAIL is '邮箱';
comment on column SYS_USER.TELEPHONE is '联系电话';
comment on column SYS_USER.STATE is '状态';
comment on column SYS_USER.LOGINFAIL is '登录失败次数';
comment on column SYS_USER.LOGINTIME is '最近登录时间';
comment on column SYS_USER.PHOTO is '头像';
comment on column SYS_USER.MEMO is '备注';
comment on column SYS_USER.BAE001 is '创建时间';
comment on column SYS_USER.BAE002 is '创建人';
comment on column SYS_USER.BAE003 is '最近修改时间';
comment on column SYS_USER.BAE004 is '最近修改人';

--菜单信息表
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
comment on table SYS_RIGHT is '菜单信息表';
comment on column SYS_RIGHT.RIGHTID is '菜单id';
comment on column SYS_RIGHT.SUBSYSTYPE is '子系统类别';
comment on column SYS_RIGHT.RIGHTNAME is '菜单名称';
comment on column SYS_RIGHT.RIGHTTYPE is '菜单类别';
comment on column SYS_RIGHT.RIGHTLEVEL is '菜单级别';
comment on column SYS_RIGHT.URL is '菜单url';
comment on column SYS_RIGHT.ICON is '菜单图标';
comment on column SYS_RIGHT.SORTNO is '排序号';
comment on column SYS_RIGHT.VALIDATED is '有效标志（1-有效，0-无效）';
comment on column SYS_RIGHT.AUTHED is '是否需要认证（1-是，0-否）';
comment on column SYS_RIGHT.MEMO is '备注';
comment on column SYS_RIGHT.BAE001 is '创建时间';
comment on column SYS_RIGHT.BAE002 is '创建人';
comment on column SYS_RIGHT.BAE003 is '最近修改时间';
comment on column SYS_RIGHT.BAE004 is '最近修改人';

--角色信息表
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
comment on table SYS_ROLE is '角色信息表';
comment on column SYS_ROLE.ROLEID is '角色ID';
comment on column SYS_ROLE.ROLENAME is '角色名称';
comment on column SYS_ROLE.DESCRIPTION is '角色说明';
comment on column SYS_ROLE.VALIDATED is '有效标志（1-有效，0-无效）';
comment on column SYS_ROLE.MEMO is '备注';
comment on column SYS_ROLE.BAE001 is '创建时间';
comment on column SYS_ROLE.BAE002 is '创建人';
comment on column SYS_ROLE.BAE003 is '最近修改时间';
comment on column SYS_ROLE.BAE004 is '最近修改人';

--角色权限表
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
comment on table SYS_ROLE2RIGHT is '角色权限表';
comment on column SYS_ROLE2RIGHT.ROLE2RIGHTID is '角色权限ID';
comment on column SYS_ROLE2RIGHT.ROLEID is '角色ID';
comment on column SYS_ROLE2RIGHT.RIGHTID is '权限ID';
comment on column SYS_ROLE2RIGHT.VALIDATED is '有效标志（1-有效，0-无效）';
comment on column SYS_ROLE2RIGHT.MEMO is '备注';
comment on column SYS_ROLE2RIGHT.BAE001 is '创建时间';
comment on column SYS_ROLE2RIGHT.BAE002 is '创建人';
comment on column SYS_ROLE2RIGHT.BAE003 is '最近修改时间';
comment on column SYS_ROLE2RIGHT.BAE004 is '最近修改人';

--操作员角色表
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
comment on table SYS_USER2ROLE is '操作员角色表';
comment on column SYS_USER2ROLE.USER2ROLEID is '操作员角色ID';
comment on column SYS_USER2ROLE.USERID is '操作员ID';
comment on column SYS_USER2ROLE.ROLEID is '角色ID';
comment on column SYS_USER2ROLE.VALIDATED is '有效标志（1-有效，0-无效）';
comment on column SYS_USER2ROLE.MEMO is '备注';
comment on column SYS_USER2ROLE.BAE001 is '创建时间';
comment on column SYS_USER2ROLE.BAE002 is '创建人';
comment on column SYS_USER2ROLE.BAE003 is '最近修改时间';
comment on column SYS_USER2ROLE.BAE004 is '最近修改人';

--字典配置
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
comment on table SYS_DICT is '字典项表';
comment on column SYS_DICT.DICTID is '字典项ID';
comment on column SYS_DICT.DICTTYPE is '字典项类型';
comment on column SYS_DICT.TYPENAME is '字典项类型名称';
comment on column SYS_DICT.DCITCODE is '字典项代码';
comment on column SYS_DICT.DICTNAME is '字典项名称';
comment on column SYS_DICT.DESCRITPION is '字典项描述';
comment on column SYS_DICT.VALIDATED is '有效标志';
comment on column SYS_DICT.MEMO is '备注';
comment on column SYS_DICT.BAE001 is '创建时间';
comment on column SYS_DICT.BAE002 is '创建人';
comment on column SYS_DICT.BAE003 is '最近修改时间';
comment on column SYS_DICT.BAE004 is '最近修改人';

--动态字典配置
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
comment on table DYNAMIC_DICT_CONFIG is '动态字典配置表';
comment on column DYNAMIC_DICT_CONFIG.CONFIGID is '配置id';
comment on column DYNAMIC_DICT_CONFIG.MULTSQL is '多记录查询语句';
comment on column DYNAMIC_DICT_CONFIG.VALIDATED is '有效标志';
comment on column DYNAMIC_DICT_CONFIG.DESCRIPTION is '动态字典描述';
comment on column DYNAMIC_DICT_CONFIG.MEMO is '备注';
comment on column DYNAMIC_DICT_CONFIG.BAE001 is '创建时间';
comment on column DYNAMIC_DICT_CONFIG.BAE002 is '创建人';
comment on column DYNAMIC_DICT_CONFIG.BAE003 is '最近修改时间';
comment on column DYNAMIC_DICT_CONFIG.BAE004 is '最近修改人';

--定时器配置表
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
comment on table QUARTZ_JOB is '定时器任务配置';
comment on column QUARTZ_JOB.JOBID is '任务id';
comment on column QUARTZ_JOB.JBOGROUP is '任务分组';
comment on column QUARTZ_JOB.JOBTYPE is '任务类型';
comment on column QUARTZ_JOB.TASK is '任务处理类';
comment on column QUARTZ_JOB.SCHEDULE is '任务调度安排';
comment on column QUARTZ_JOB.VALIDATED is '有效标志';
comment on column QUARTZ_JOB.MEMO is '备注';
