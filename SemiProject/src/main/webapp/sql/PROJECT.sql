  CREATE TABLE "PROJECT" 
   (	"PROJECT_NUM" NUMBER(10,0), 
	"PROJECT_NAME" VARCHAR2(100 BYTE), 
	"PROJECT_STATE" VARCHAR2(20 BYTE), 
	"PROJECT_PROG" NUMBER DEFAULT 0, 
	"PROJECT_START" DATE, 
	"PROJECT_END" DATE, 
	"PROJECT_PRIORITY" VARCHAR2(20 BYTE), 
	"PROJECT_ADMIN" VARCHAR2(30 BYTE), 
	"PROJECT_BOOKMARK" NUMBER
   );
   
   Insert into PROJECT (PROJECT_NUM,PROJECT_NAME,PROJECT_STATE,PROJECT_PROG,PROJECT_START,PROJECT_END,PROJECT_PRIORITY,PROJECT_ADMIN,PROJECT_BOOKMARK) values (1,'중앙HTA프로젝트1','마감',0,to_date('23/01/01','RR/MM/DD'),to_date('23/02/10','RR/MM/DD'),'마감','HTA1',null);
Insert into PROJECT (PROJECT_NUM,PROJECT_NAME,PROJECT_STATE,PROJECT_PROG,PROJECT_START,PROJECT_END,PROJECT_PRIORITY,PROJECT_ADMIN,PROJECT_BOOKMARK) values (2,'중앙HTA프로젝트2','진행중',0,to_date('23/01/01','RR/MM/DD'),to_date('23/08/24','RR/MM/DD'),'낮음','HTA2',null);
Insert into PROJECT (PROJECT_NUM,PROJECT_NAME,PROJECT_STATE,PROJECT_PROG,PROJECT_START,PROJECT_END,PROJECT_PRIORITY,PROJECT_ADMIN,PROJECT_BOOKMARK) values (3,'중앙HTA프로젝트3','진행중',0,to_date('23/01/01','RR/MM/DD'),to_date('23/06/04','RR/MM/DD'),'없음','HTA3',null);
Insert into PROJECT (PROJECT_NUM,PROJECT_NAME,PROJECT_STATE,PROJECT_PROG,PROJECT_START,PROJECT_END,PROJECT_PRIORITY,PROJECT_ADMIN,PROJECT_BOOKMARK) values (4,'중앙HTA프로젝트4','진행중',0,to_date('23/01/01','RR/MM/DD'),to_date('23/05/21','RR/MM/DD'),'없음','HTA4',null);
Insert into PROJECT (PROJECT_NUM,PROJECT_NAME,PROJECT_STATE,PROJECT_PROG,PROJECT_START,PROJECT_END,PROJECT_PRIORITY,PROJECT_ADMIN,PROJECT_BOOKMARK) values (5,'중앙HTA프로젝트5','진행중',0,to_date('23/01/01','RR/MM/DD'),to_date('23/05/01','RR/MM/DD'),'없음','HTA1',null);
Insert into PROJECT (PROJECT_NUM,PROJECT_NAME,PROJECT_STATE,PROJECT_PROG,PROJECT_START,PROJECT_END,PROJECT_PRIORITY,PROJECT_ADMIN,PROJECT_BOOKMARK) values (6,'중앙HTA프로젝트6','진행중',0,to_date('23/01/01','RR/MM/DD'),to_date('23/04/22','RR/MM/DD'),'없음','HTA2',null);
Insert into PROJECT (PROJECT_NUM,PROJECT_NAME,PROJECT_STATE,PROJECT_PROG,PROJECT_START,PROJECT_END,PROJECT_PRIORITY,PROJECT_ADMIN,PROJECT_BOOKMARK) values (7,'중앙HTA프로젝트7','요청 확인',0,to_date('23/01/01','RR/MM/DD'),to_date('23/03/12','RR/MM/DD'),'높음','HTA3',null);
Insert into PROJECT (PROJECT_NUM,PROJECT_NAME,PROJECT_STATE,PROJECT_PROG,PROJECT_START,PROJECT_END,PROJECT_PRIORITY,PROJECT_ADMIN,PROJECT_BOOKMARK) values (8,'중앙HTA프로젝트8','요청 확인',0,to_date('23/01/01','RR/MM/DD'),to_date('23/02/25','RR/MM/DD'),'높음','HTA2',null);
Insert into PROJECT (PROJECT_NUM,PROJECT_NAME,PROJECT_STATE,PROJECT_PROG,PROJECT_START,PROJECT_END,PROJECT_PRIORITY,PROJECT_ADMIN,PROJECT_BOOKMARK) values (9,'중앙HTA프로젝트9','마감 임박',0,to_date('23/01/01','RR/MM/DD'),to_date('23/02/18','RR/MM/DD'),'높음','HTA1',null);
Insert into PROJECT (PROJECT_NUM,PROJECT_NAME,PROJECT_STATE,PROJECT_PROG,PROJECT_START,PROJECT_END,PROJECT_PRIORITY,PROJECT_ADMIN,PROJECT_BOOKMARK) values (10,'중앙HTA프로젝트10','마감 임박',0,to_date('23/01/01','RR/MM/DD'),to_date('23/02/16','RR/MM/DD'),'높음','HTA2',null);

CREATE UNIQUE INDEX "SYS_C007065" ON "PROJECT" ("PROJECT_NUM");

ALTER TABLE "PROJECT" ADD PRIMARY KEY ("PROJECT_NUM") ENABLE;
  ALTER TABLE "PROJECT" MODIFY ("PROJECT_ADMIN" NOT NULL ENABLE);
  ALTER TABLE "PROJECT" MODIFY ("PROJECT_NAME" NOT NULL ENABLE);
  ALTER TABLE "PROJECT" MODIFY ("PROJECT_NUM" NOT NULL ENABLE);

  ALTER TABLE "PROJECT" ADD FOREIGN KEY ("PROJECT_ADMIN")
	  REFERENCES "USER_INFO" ("USER_ID") ON DELETE CASCADE ENABLE;