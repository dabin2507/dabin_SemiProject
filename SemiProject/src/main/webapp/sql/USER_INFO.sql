CREATE TABLE "USER_INFO" 
   (	"USER_ID" VARCHAR2(30 BYTE), 
	"USER_PASSWORD" VARCHAR2(50 BYTE), 
	"USER_EMAIL" VARCHAR2(50 BYTE), 
	"USER_CODE" NUMBER(10,0), 
	"USER_NAME" VARCHAR2(20 BYTE), 
	"USER_IMG" VARCHAR2(50 BYTE), 
	"USER_IS_ADMIN" NUMBER(2,0), 
	"USER_DEPT" VARCHAR2(20 BYTE), 
	"USER_JOB" VARCHAR2(20 BYTE), 
	"USER_FAX" VARCHAR2(20 BYTE), 
	"USER_INTRO" VARCHAR2(600 BYTE), 
	"USER_CARD" VARCHAR2(50 BYTE), 
	"USER_STATE" NUMBER, 
	"USER_JOIN_DATE" DATE DEFAULT sysdate, 
	"USER_PHONE" VARCHAR2(50 BYTE), 
	"USER_LASTTIME" VARCHAR2(50 BYTE), 
	"USER_PASSWORD_CH" VARCHAR2(20 BYTE)
   ) ;
   
Insert into USER_INFO (USER_ID,USER_PASSWORD,USER_EMAIL,USER_CODE,USER_NAME,USER_IMG,USER_IS_ADMIN,USER_DEPT,USER_JOB,USER_FAX,USER_INTRO,USER_CARD,USER_STATE,USER_JOIN_DATE,USER_PHONE,USER_LASTTIME,USER_PASSWORD_CH) values ('HTA6','1234','hta6@naver.com',1234,'멍멍이','profile.png',0,null,null,null,null,null,null,to_date('23/02/14','RR/MM/DD'),null,null,null);
Insert into USER_INFO (USER_ID,USER_PASSWORD,USER_EMAIL,USER_CODE,USER_NAME,USER_IMG,USER_IS_ADMIN,USER_DEPT,USER_JOB,USER_FAX,USER_INTRO,USER_CARD,USER_STATE,USER_JOIN_DATE,USER_PHONE,USER_LASTTIME,USER_PASSWORD_CH) values ('HTA7','1234','hta5@naver.com',1234,'고양이','profile.png',0,null,null,null,null,null,null,to_date('23/02/14','RR/MM/DD'),null,null,null);
Insert into USER_INFO (USER_ID,USER_PASSWORD,USER_EMAIL,USER_CODE,USER_NAME,USER_IMG,USER_IS_ADMIN,USER_DEPT,USER_JOB,USER_FAX,USER_INTRO,USER_CARD,USER_STATE,USER_JOIN_DATE,USER_PHONE,USER_LASTTIME,USER_PASSWORD_CH) values ('HTA8','1234','hta5@naver.com',1234,'개냥이','profile.png',0,null,null,null,null,null,null,to_date('23/02/14','RR/MM/DD'),null,null,null);
Insert into USER_INFO (USER_ID,USER_PASSWORD,USER_EMAIL,USER_CODE,USER_NAME,USER_IMG,USER_IS_ADMIN,USER_DEPT,USER_JOB,USER_FAX,USER_INTRO,USER_CARD,USER_STATE,USER_JOIN_DATE,USER_PHONE,USER_LASTTIME,USER_PASSWORD_CH) values ('HTA9','1234','hta5@naver.com',1234,'길냥이','profile.png',0,null,null,null,null,null,null,to_date('23/02/14','RR/MM/DD'),null,null,null);
Insert into USER_INFO (USER_ID,USER_PASSWORD,USER_EMAIL,USER_CODE,USER_NAME,USER_IMG,USER_IS_ADMIN,USER_DEPT,USER_JOB,USER_FAX,USER_INTRO,USER_CARD,USER_STATE,USER_JOIN_DATE,USER_PHONE,USER_LASTTIME,USER_PASSWORD_CH) values ('HTA0214','q1w2e3r4',null,null,'문성빈','profile.png',1,null,null,null,null,null,null,to_date('23/02/14','RR/MM/DD'),null,null,'q1w2e3r4');
Insert into USER_INFO (USER_ID,USER_PASSWORD,USER_EMAIL,USER_CODE,USER_NAME,USER_IMG,USER_IS_ADMIN,USER_DEPT,USER_JOB,USER_FAX,USER_INTRO,USER_CARD,USER_STATE,USER_JOIN_DATE,USER_PHONE,USER_LASTTIME,USER_PASSWORD_CH) values ('HTA1','1234','hta1@hta.com',1234,'문성빈','avatar-1.jpg',1,'인사','대리','02-333-4444','안녕',null,null,to_date('23/02/14','RR/MM/DD'),'010-2809-4259',null,null);
Insert into USER_INFO (USER_ID,USER_PASSWORD,USER_EMAIL,USER_CODE,USER_NAME,USER_IMG,USER_IS_ADMIN,USER_DEPT,USER_JOB,USER_FAX,USER_INTRO,USER_CARD,USER_STATE,USER_JOIN_DATE,USER_PHONE,USER_LASTTIME,USER_PASSWORD_CH) values ('HTA2','1234','hta2@naver.com',1234,'최자영','avatar-2.jpg',1,null,null,null,null,null,null,to_date('23/02/14','RR/MM/DD'),null,null,null);
Insert into USER_INFO (USER_ID,USER_PASSWORD,USER_EMAIL,USER_CODE,USER_NAME,USER_IMG,USER_IS_ADMIN,USER_DEPT,USER_JOB,USER_FAX,USER_INTRO,USER_CARD,USER_STATE,USER_JOIN_DATE,USER_PHONE,USER_LASTTIME,USER_PASSWORD_CH) values ('HTA3','1234','hta3@naver.com',1234,'안덕균','avatar-3.jpg',1,null,null,null,null,null,null,to_date('23/02/14','RR/MM/DD'),null,null,null);
Insert into USER_INFO (USER_ID,USER_PASSWORD,USER_EMAIL,USER_CODE,USER_NAME,USER_IMG,USER_IS_ADMIN,USER_DEPT,USER_JOB,USER_FAX,USER_INTRO,USER_CARD,USER_STATE,USER_JOIN_DATE,USER_PHONE,USER_LASTTIME,USER_PASSWORD_CH) values ('HTA4','1234','hta4@naver.com',1234,'이다빈','avatar-4.jpg',0,null,null,null,null,null,null,to_date('23/02/14','RR/MM/DD'),null,null,null);
Insert into USER_INFO (USER_ID,USER_PASSWORD,USER_EMAIL,USER_CODE,USER_NAME,USER_IMG,USER_IS_ADMIN,USER_DEPT,USER_JOB,USER_FAX,USER_INTRO,USER_CARD,USER_STATE,USER_JOIN_DATE,USER_PHONE,USER_LASTTIME,USER_PASSWORD_CH) values ('HTA5','1234','hta5@naver.com',1234,'유용천','avatar-5.jpg',0,null,null,null,null,null,null,to_date('23/02/14','RR/MM/DD'),null,null,null);

 CREATE UNIQUE INDEX "SYS_C007061" ON "USER_INFO" ("USER_ID") ;
 
 ALTER TABLE "USER_INFO" ADD PRIMARY KEY ("USER_ID");