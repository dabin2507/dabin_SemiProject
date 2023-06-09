 CREATE TABLE "FREE" 
   (	"FREE_NUM" NUMBER, 
	"FREE_NAME" VARCHAR2(30 BYTE), 
	"FREE_PASS" VARCHAR2(30 BYTE), 
	"FREE_SUBJECT" VARCHAR2(300 BYTE), 
	"FREE_CONTENT" VARCHAR2(4000 BYTE), 
	"FREE_FILE" VARCHAR2(50 BYTE), 
	"FREE_RE_REF" NUMBER, 
	"FREE_RE_LEV" NUMBER, 
	"FREE_RE_SEQ" NUMBER, 
	"FREE_READCOUNT" NUMBER, 
	"FREE_REG_DATE" DATE DEFAULT SYSDATE, 
	"FREE_LIKE" NUMBER, 
	"FREE_HATE" NUMBER, 
	"FREE_WRITER_ID" VARCHAR2(20 BYTE)
   );
   
   CREATE UNIQUE INDEX "SYS_C007151" ON "FREE" ("FREE_NUM");


  ALTER TABLE "FREE" ADD PRIMARY KEY ("FREE_NUM") ENABLE;
  ALTER TABLE "FREE" MODIFY ("FREE_CONTENT" NOT NULL ENABLE);
  ALTER TABLE "FREE" MODIFY ("FREE_SUBJECT" NOT NULL ENABLE);
  ALTER TABLE "FREE" MODIFY ("FREE_PASS" NOT NULL ENABLE);


  ALTER TABLE "FREE" ADD CONSTRAINT "FREE_FK1" FOREIGN KEY ("FREE_WRITER_ID")
	  REFERENCES "USER_INFO" ("USER_ID") ENABLE;