-- ������Ϣ����������ʱ��
delete from QUARTZ_JOB where JOBID = '101';
insert into QUARTZ_JOB (JOBID, JBOGROUP, JOBTYPE, TASK, SCHEDULE, VALIDATED, MEMO)
values ('101', 'blog', 'JAVA', 'cn.muchen.blog.lucene.LeceneSearchQuartz', '0 */5 * * * ?', '1', null);
commit;
