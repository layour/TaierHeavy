CREATE TABLE
IF NOT EXISTS "public"."yyuap_check_record" (
	"pk_record" VARCHAR (32) COLLATE "default" NOT NULL,
	"ref_pk_device" VARCHAR (32) COLLATE "default" NOT NULL,
	"ref_pk_user" VARCHAR (32) COLLATE "default" NOT NULL,
	"checktime" VARCHAR (20) COLLATE "default" NOT NULL,
	"longitude" VARCHAR (64) COLLATE "default" NOT NULL,
	"latitude" VARCHAR (64) COLLATE "default" NOT NULL,
	"ts" VARCHAR (20) COLLATE "default",
	"isfinished" int2 DEFAULT 0 NOT NULL,
	CONSTRAINT "yyuap_check_record_pkey" PRIMARY KEY ("pk_record")
) WITH (OIDS = FALSE);

ALTER TABLE "public"."yyuap_check_record" OWNER TO "postgres";

CREATE TABLE
IF NOT EXISTS "public"."yyuap_device" (
	"pk_device" VARCHAR (32) COLLATE "default" NOT NULL,
	"code" VARCHAR (32) COLLATE "default" NOT NULL,
	"name" VARCHAR (32) COLLATE "default" NOT NULL,
	"creatorid" VARCHAR (32) COLLATE "default",
	"creationtime" VARCHAR (20) COLLATE "default",
	"longitude" VARCHAR (64) COLLATE "default" NOT NULL,
	"latitude" VARCHAR (64) COLLATE "default" NOT NULL,
	"ts" VARCHAR (20) COLLATE "default",
	CONSTRAINT "yyuap_device_pkey" PRIMARY KEY ("pk_device")
) WITH (OIDS = FALSE);

ALTER TABLE "public"."yyuap_device" OWNER TO "postgres";

CREATE TABLE
IF NOT EXISTS "public"."yyuap_user" (
	"name" VARCHAR (64) COLLATE "default" NOT NULL,
	"password" VARCHAR (64) COLLATE "default" NOT NULL,
	"pk_user" VARCHAR (32) COLLATE "default" NOT NULL,
	"deptname" VARCHAR (32) COLLATE "default" NOT NULL,
	"ts" VARCHAR (20) COLLATE "default",
	CONSTRAINT "yyuap_user_pkey" PRIMARY KEY ("pk_user")
) WITH (OIDS = FALSE);

ALTER TABLE "public"."yyuap_user" OWNER TO "postgres";

CREATE TABLE
IF NOT EXISTS "public"."yyuap_cad" (
	"name" VARCHAR (64) COLLATE "default" NOT NULL,
	"code" VARCHAR (32) COLLATE "default" NOT NULL,
	"pk_cad" VARCHAR (32) COLLATE "default" NOT NULL,
	"ref_pk_user" VARCHAR (32) COLLATE "default" NOT NULL,
	"imgsrc" VARCHAR (255) COLLATE "default" NOT NULL,
	"updatetime" VARCHAR (20) COLLATE "default" NOT NULL,
	"ts" varchar(20) COLLATE "default",
	CONSTRAINT "yyuap_cad_pkey" PRIMARY KEY ("ref_pk_user")
) WITH (OIDS = FALSE);

ALTER TABLE "public"."yyuap_cad" OWNER TO "postgres";

