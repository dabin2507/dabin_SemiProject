CREATE TABLE "COMPANY_JOB" 
   (	"JOB_NAME" VARCHAR2(50 BYTE), 
	"COMPANY_NAME" VARCHAR2(50 BYTE)
   );
   
   Insert into COMPANY_JOB (JOB_NAME,COMPANY_NAME) values ('팀장','CO-WORK');
Insert into COMPANY_JOB (JOB_NAME,COMPANY_NAME) values ('사원','CO-WORK');
Insert into COMPANY_JOB (JOB_NAME,COMPANY_NAME) values ('부장','CO-WORK');
Insert into COMPANY_JOB (JOB_NAME,COMPANY_NAME) values ('과장','CO-WORK');
Insert into COMPANY_JOB (JOB_NAME,COMPANY_NAME) values ('대리','CO-WORK');

 ALTER TABLE "COMPANY_JOB" ADD CONSTRAINT "COMPANY_JOB_FK1" FOREIGN KEY ("COMPANY_NAME")
	  REFERENCES "COMPANY_INFO" ("COMPANY_NAME") ENABLE;