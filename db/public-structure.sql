/*
 Navicat Premium Data Transfer

 Source Server         : immoSales
 Source Server Type    : PostgreSQL
 Source Server Version : 90204
 Source Host           : localhost
 Source Database       : immosales
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 90204
 File Encoding         : utf-8

 Date: 06/20/2013 15:22:33 PM
*/

-- ----------------------------
--  Sequence structure for jeasyorm_sequence
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."jeasyorm_sequence";
CREATE SEQUENCE "public"."jeasyorm_sequence" INCREMENT 1 START 64 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."jeasyorm_sequence" OWNER TO "michaelsilvestre";

-- ----------------------------
--  Table structure for tcity_copy
-- ----------------------------
DROP TABLE IF EXISTS "public"."tcity_copy";
CREATE TABLE "public"."tcity_copy" (
	"id" int4 NOT NULL,
	"locality_id" int4 NOT NULL,
	"lang_id" int4,
	"poste_code" varchar(10) COLLATE "default",
	"city" varchar(50) COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."tcity_copy" OWNER TO "michaelsilvestre";

-- ----------------------------
--  Table structure for tcountry_copy
-- ----------------------------
DROP TABLE IF EXISTS "public"."tcountry_copy";
CREATE TABLE "public"."tcountry_copy" (
	"id" int4 NOT NULL,
	"iso" varchar(50) COLLATE "default",
	"label" varchar(50) COLLATE "default",
	"label_fr" varchar(50) COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."tcountry_copy" OWNER TO "michaelsilvestre";

-- ----------------------------
--  Table structure for tlang_copy
-- ----------------------------
DROP TABLE IF EXISTS "public"."tlang_copy";
CREATE TABLE "public"."tlang_copy" (
	"id" int4 NOT NULL,
	"lang" varchar(10) COLLATE "default",
	"label_fr" varchar(60) COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."tlang_copy" OWNER TO "michaelsilvestre";

-- ----------------------------
--  Table structure for tlocality_copy
-- ----------------------------
DROP TABLE IF EXISTS "public"."tlocality_copy";
CREATE TABLE "public"."tlocality_copy" (
	"id" int4 NOT NULL,
	"region_id" int4 NOT NULL,
	"locality" varchar(50) COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."tlocality_copy" OWNER TO "michaelsilvestre";

-- ----------------------------
--  Table structure for tregion_copy
-- ----------------------------
DROP TABLE IF EXISTS "public"."tregion_copy";
CREATE TABLE "public"."tregion_copy" (
	"id" int4 NOT NULL,
	"country_id" int4 NOT NULL,
	"region" varchar(50) COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."tregion_copy" OWNER TO "michaelsilvestre";

-- ----------------------------
--  Table structure for tlang
-- ----------------------------
DROP TABLE IF EXISTS "public"."tlang";
CREATE TABLE "public"."tlang" (
	"id" int4 NOT NULL,
	"lang" varchar(10) COLLATE "default",
	"label_fr" varchar(60) COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."tlang" OWNER TO "michaelsilvestre";

-- ----------------------------
--  Table structure for tcountry
-- ----------------------------
DROP TABLE IF EXISTS "public"."tcountry";
CREATE TABLE "public"."tcountry" (
	"id" int4 NOT NULL,
	"iso" varchar(50) COLLATE "default",
	"label" varchar(50) COLLATE "default",
	"label_fr" varchar(50) COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."tcountry" OWNER TO "michaelsilvestre";

-- ----------------------------
--  Table structure for tlocality
-- ----------------------------
DROP TABLE IF EXISTS "public"."tlocality";
CREATE TABLE "public"."tlocality" (
	"id" int4 NOT NULL,
	"region_id" int4 NOT NULL,
	"locality" varchar(50) COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."tlocality" OWNER TO "michaelsilvestre";

-- ----------------------------
--  Table structure for tregion
-- ----------------------------
DROP TABLE IF EXISTS "public"."tregion";
CREATE TABLE "public"."tregion" (
	"id" int4 NOT NULL,
	"country_id" int4 NOT NULL,
	"region" varchar(50) COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."tregion" OWNER TO "michaelsilvestre";

-- ----------------------------
--  Table structure for taddress
-- ----------------------------
DROP TABLE IF EXISTS "public"."taddress";
CREATE TABLE "public"."taddress" (
	"id" int4 NOT NULL,
	"person_id" int4 NOT NULL,
	"streetname" varchar(60) COLLATE "default",
	"streetnumber" varchar(10) COLLATE "default",
	"streetbox" varchar(10) COLLATE "default",
	"city_id" int4 NOT NULL
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."taddress" OWNER TO "michaelsilvestre";

-- ----------------------------
--  Table structure for tcity
-- ----------------------------
DROP TABLE IF EXISTS "public"."tcity";
CREATE TABLE "public"."tcity" (
	"id" int4 NOT NULL,
	"locality_id" int4 NOT NULL,
	"lang_id" int4,
	"poste_code" varchar(10) COLLATE "default",
	"city" varchar(50) COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."tcity" OWNER TO "michaelsilvestre";

-- ----------------------------
--  Table structure for towner
-- ----------------------------
DROP TABLE IF EXISTS "public"."towner";
CREATE TABLE "public"."towner" (
	"id" int4 NOT NULL,
	"person_id" int4 NOT NULL,
	"phonenumber" varchar(20) COLLATE "default",
	"email" varchar(50) COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."towner" OWNER TO "michaelsilvestre";

-- ----------------------------
--  Table structure for tperson
-- ----------------------------
DROP TABLE IF EXISTS "public"."tperson";
CREATE TABLE "public"."tperson" (
	"id" int4 NOT NULL,
	"titre" varchar(10) NOT NULL COLLATE "default",
	"firstname" varchar(50) NOT NULL COLLATE "default",
	"lastname" varchar(50) NOT NULL COLLATE "default",
	"created_at" timestamp(6) NULL,
	"updated_at" timestamp(6) NULL
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."tperson" OWNER TO "michaelsilvestre";

-- ----------------------------
--  Table structure for tsaler
-- ----------------------------
DROP TABLE IF EXISTS "public"."tsaler";
CREATE TABLE "public"."tsaler" (
	"id" int4 NOT NULL,
	"person_id" int4 NOT NULL,
	"phonenumber" varchar(20) COLLATE "default",
	"email" varchar(50) COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."tsaler" OWNER TO "michaelsilvestre";

-- ----------------------------
--  Table structure for tcustomer
-- ----------------------------
DROP TABLE IF EXISTS "public"."tcustomer";
CREATE TABLE "public"."tcustomer" (
	"id" int4 NOT NULL,
	"person_id" int4 NOT NULL,
	"phonenumber" varchar(20) COLLATE "default",
	"email" varchar(50) COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."tcustomer" OWNER TO "michaelsilvestre";

-- ----------------------------
--  Table structure for troom
-- ----------------------------
DROP TABLE IF EXISTS "public"."troom";
CREATE TABLE "public"."troom" (
	"id" int4 NOT NULL,
	"bien_id" int4 NOT NULL,
	"room_type" varchar(50) COLLATE "default",
	"superficy" int4
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."troom" OWNER TO "michaelsilvestre";

-- ----------------------------
--  Table structure for toptions
-- ----------------------------
DROP TABLE IF EXISTS "public"."toptions";
CREATE TABLE "public"."toptions" (
	"id" int4 NOT NULL,
	"bien_id" int4 NOT NULL,
	"key_data" varchar(50) COLLATE "default",
	"value_data" varchar(100) COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."toptions" OWNER TO "michaelsilvestre";

-- ----------------------------
--  Table structure for toffer
-- ----------------------------
DROP TABLE IF EXISTS "public"."toffer";
CREATE TABLE "public"."toffer" (
	"id" int4 NOT NULL,
	"buyer_id" int4 NOT NULL,
	"bien_id" int4,
	"status" varchar(50) COLLATE "default",
	"offer" int4,
	"end_date" timestamp(6) NULL,
	"created_at" timestamp(6) NULL,
	"updated_at" timestamp(6) NULL
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."toffer" OWNER TO "michaelsilvestre";

-- ----------------------------
--  Table structure for tbuyer
-- ----------------------------
DROP TABLE IF EXISTS "public"."tbuyer";
CREATE TABLE "public"."tbuyer" (
	"id" int4 NOT NULL,
	"person_id" int4 NOT NULL,
	"phonenumber" varchar(20) COLLATE "default",
	"email" varchar(50) COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."tbuyer" OWNER TO "michaelsilvestre";

-- ----------------------------
--  Table structure for tinterest
-- ----------------------------
DROP TABLE IF EXISTS "public"."tinterest";
CREATE TABLE "public"."tinterest" (
	"id" int4 NOT NULL,
	"buyer_id" int4 NOT NULL,
	"bien_id" int4 NOT NULL,
	"status" varchar(50) NOT NULL COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."tinterest" OWNER TO "michaelsilvestre";

-- ----------------------------
--  Table structure for tbien
-- ----------------------------
DROP TABLE IF EXISTS "public"."tbien";
CREATE TABLE "public"."tbien" (
	"id" int4 NOT NULL,
	"owner_id" int4 NOT NULL,
	"name" varchar(100) NOT NULL COLLATE "default",
	"description" text COLLATE "default",
	"type_product" varchar(50) COLLATE "default",
	"street_name" varchar(100) COLLATE "default",
	"street_number" varchar(100) COLLATE "default",
	"street_box" varchar(100) COLLATE "default",
	"city_id" int4 NOT NULL,
	"price" int8,
	"year_construction" int4,
	"face_wide" int4,
	"n_frontage" int4,
	"n_floor" int4,
	"cpeb" char(1) COLLATE "default",
	"created_at" timestamp(6) NULL,
	"updated_at" timestamp(6) NULL,
	"status" varchar(20) NOT NULL COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."tbien" OWNER TO "michaelsilvestre";

-- ----------------------------
--  Table structure for timages
-- ----------------------------
DROP TABLE IF EXISTS "public"."timages";
CREATE TABLE "public"."timages" (
	"id" int4 NOT NULL,
	"bien_id" int4 NOT NULL,
	"image_name" varchar(50) NOT NULL COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."timages" OWNER TO "michaelsilvestre";

-- ----------------------------
--  View structure for complete_city
-- ----------------------------
DROP VIEW IF EXISTS "public"."complete_city";
CREATE VIEW "public"."complete_city" AS SELECT tcity.id, tcity.city, tcity.poste_code, tlocality.locality, tregion.region, tcountry.label_fr FROM (((tcity JOIN tlocality ON ((tcity.locality_id = tlocality.id))) JOIN tregion ON ((tlocality.region_id = tregion.id))) JOIN tcountry ON ((tregion.country_id = tcountry.id))) ORDER BY tregion.id;

-- ----------------------------
--  Primary key structure for table tcity_copy
-- ----------------------------
ALTER TABLE "public"."tcity_copy" ADD CONSTRAINT "tcity_copy_pkey" PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table tcountry_copy
-- ----------------------------
ALTER TABLE "public"."tcountry_copy" ADD CONSTRAINT "tcountry_copy_pkey" PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table tlang_copy
-- ----------------------------
ALTER TABLE "public"."tlang_copy" ADD CONSTRAINT "tlang_copy_pkey" PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table tlocality_copy
-- ----------------------------
ALTER TABLE "public"."tlocality_copy" ADD CONSTRAINT "tlocality_copy_pkey" PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table tregion_copy
-- ----------------------------
ALTER TABLE "public"."tregion_copy" ADD CONSTRAINT "tregion_copy_pkey" PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table tlang
-- ----------------------------
ALTER TABLE "public"."tlang" ADD CONSTRAINT "tlang_pkey" PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Triggers structure for table tlang
-- ----------------------------
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_75325" AFTER UPDATE ON "public"."tlang" FROM "public"."tcity" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_75325" ON "public"."tlang" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_75324" AFTER DELETE ON "public"."tlang" FROM "public"."tcity" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_75324" ON "public"."tlang" IS NULL;

-- ----------------------------
--  Primary key structure for table tcountry
-- ----------------------------
ALTER TABLE "public"."tcountry" ADD CONSTRAINT "tcountry_pkey" PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Triggers structure for table tcountry
-- ----------------------------
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_75335" AFTER UPDATE ON "public"."tcountry" FROM "public"."tregion" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_75335" ON "public"."tcountry" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_75334" AFTER DELETE ON "public"."tcountry" FROM "public"."tregion" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_75334" ON "public"."tcountry" IS NULL;

-- ----------------------------
--  Primary key structure for table tlocality
-- ----------------------------
ALTER TABLE "public"."tlocality" ADD CONSTRAINT "tlocality_pkey" PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Triggers structure for table tlocality
-- ----------------------------
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_75332" AFTER UPDATE ON "public"."tlocality" FROM "public"."tregion" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_75332" ON "public"."tlocality" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_75331" AFTER INSERT ON "public"."tlocality" FROM "public"."tregion" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_75331" ON "public"."tlocality" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_75320" AFTER UPDATE ON "public"."tlocality" FROM "public"."tcity" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_75320" ON "public"."tlocality" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_75319" AFTER DELETE ON "public"."tlocality" FROM "public"."tcity" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_75319" ON "public"."tlocality" IS NULL;

-- ----------------------------
--  Primary key structure for table tregion
-- ----------------------------
ALTER TABLE "public"."tregion" ADD CONSTRAINT "tregion_pkey" PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Triggers structure for table tregion
-- ----------------------------
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_75337" AFTER UPDATE ON "public"."tregion" FROM "public"."tcountry" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_75337" ON "public"."tregion" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_75336" AFTER INSERT ON "public"."tregion" FROM "public"."tcountry" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_75336" ON "public"."tregion" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_75330" AFTER UPDATE ON "public"."tregion" FROM "public"."tlocality" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_75330" ON "public"."tregion" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_75329" AFTER DELETE ON "public"."tregion" FROM "public"."tlocality" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_75329" ON "public"."tregion" IS NULL;

-- ----------------------------
--  Primary key structure for table taddress
-- ----------------------------
ALTER TABLE "public"."taddress" ADD CONSTRAINT "taddress_pkey" PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Triggers structure for table taddress
-- ----------------------------
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_75684" AFTER UPDATE ON "public"."taddress" FROM "public"."tcity" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_75684" ON "public"."taddress" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_75683" AFTER INSERT ON "public"."taddress" FROM "public"."tcity" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_75683" ON "public"."taddress" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_75679" AFTER UPDATE ON "public"."taddress" FROM "public"."tperson" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_75679" ON "public"."taddress" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_75678" AFTER INSERT ON "public"."taddress" FROM "public"."tperson" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_75678" ON "public"."taddress" IS NULL;

-- ----------------------------
--  Primary key structure for table tcity
-- ----------------------------
ALTER TABLE "public"."tcity" ADD CONSTRAINT "tcity_pkey" PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Triggers structure for table tcity
-- ----------------------------
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_75327" AFTER UPDATE ON "public"."tcity" FROM "public"."tlang" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_75327" ON "public"."tcity" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_75326" AFTER INSERT ON "public"."tcity" FROM "public"."tlang" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_75326" ON "public"."tcity" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_75322" AFTER UPDATE ON "public"."tcity" FROM "public"."tlocality" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_75322" ON "public"."tcity" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_75321" AFTER INSERT ON "public"."tcity" FROM "public"."tlocality" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_75321" ON "public"."tcity" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_75682" AFTER UPDATE ON "public"."tcity" FROM "public"."taddress" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_75682" ON "public"."tcity" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_75681" AFTER DELETE ON "public"."tcity" FROM "public"."taddress" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_75681" ON "public"."tcity" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_75712" AFTER UPDATE ON "public"."tcity" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_75712" ON "public"."tcity" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_75711" AFTER DELETE ON "public"."tcity" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_75711" ON "public"."tcity" IS NULL;

-- ----------------------------
--  Primary key structure for table towner
-- ----------------------------
ALTER TABLE "public"."towner" ADD CONSTRAINT "towner_pkey" PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Triggers structure for table towner
-- ----------------------------
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_75699" AFTER UPDATE ON "public"."towner" FROM "public"."tperson" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_75699" ON "public"."towner" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_75698" AFTER INSERT ON "public"."towner" FROM "public"."tperson" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_75698" ON "public"."towner" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_75707" AFTER UPDATE ON "public"."towner" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_75707" ON "public"."towner" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_75706" AFTER DELETE ON "public"."towner" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_75706" ON "public"."towner" IS NULL;

-- ----------------------------
--  Primary key structure for table tperson
-- ----------------------------
ALTER TABLE "public"."tperson" ADD CONSTRAINT "tperson_pkey" PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Triggers structure for table tperson
-- ----------------------------
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_75677" AFTER UPDATE ON "public"."tperson" FROM "public"."taddress" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_75677" ON "public"."tperson" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_75676" AFTER DELETE ON "public"."tperson" FROM "public"."taddress" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_75676" ON "public"."tperson" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_75697" AFTER UPDATE ON "public"."tperson" FROM "public"."towner" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_75697" ON "public"."tperson" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_75696" AFTER DELETE ON "public"."tperson" FROM "public"."towner" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_75696" ON "public"."tperson" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_75692" AFTER UPDATE ON "public"."tperson" FROM "public"."tsaler" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_75692" ON "public"."tperson" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_75691" AFTER DELETE ON "public"."tperson" FROM "public"."tsaler" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_75691" ON "public"."tperson" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_75687" AFTER UPDATE ON "public"."tperson" FROM "public"."tcustomer" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_75687" ON "public"."tperson" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_75686" AFTER DELETE ON "public"."tperson" FROM "public"."tcustomer" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_75686" ON "public"."tperson" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_75702" AFTER UPDATE ON "public"."tperson" FROM "public"."tbuyer" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_75702" ON "public"."tperson" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_75701" AFTER DELETE ON "public"."tperson" FROM "public"."tbuyer" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_75701" ON "public"."tperson" IS NULL;

-- ----------------------------
--  Primary key structure for table tsaler
-- ----------------------------
ALTER TABLE "public"."tsaler" ADD CONSTRAINT "tsaler_pkey" PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Triggers structure for table tsaler
-- ----------------------------
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_75694" AFTER UPDATE ON "public"."tsaler" FROM "public"."tperson" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_75694" ON "public"."tsaler" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_75693" AFTER INSERT ON "public"."tsaler" FROM "public"."tperson" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_75693" ON "public"."tsaler" IS NULL;

-- ----------------------------
--  Primary key structure for table tcustomer
-- ----------------------------
ALTER TABLE "public"."tcustomer" ADD CONSTRAINT "tcustomer_pkey" PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Triggers structure for table tcustomer
-- ----------------------------
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_75689" AFTER UPDATE ON "public"."tcustomer" FROM "public"."tperson" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_75689" ON "public"."tcustomer" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_75688" AFTER INSERT ON "public"."tcustomer" FROM "public"."tperson" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_75688" ON "public"."tcustomer" IS NULL;

-- ----------------------------
--  Primary key structure for table troom
-- ----------------------------
ALTER TABLE "public"."troom" ADD CONSTRAINT "troom_pkey" PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Triggers structure for table troom
-- ----------------------------
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_75719" AFTER UPDATE ON "public"."troom" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_75719" ON "public"."troom" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_75718" AFTER INSERT ON "public"."troom" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_75718" ON "public"."troom" IS NULL;

-- ----------------------------
--  Primary key structure for table toptions
-- ----------------------------
ALTER TABLE "public"."toptions" ADD CONSTRAINT "toptions_pkey" PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Triggers structure for table toptions
-- ----------------------------
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_75724" AFTER UPDATE ON "public"."toptions" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_75724" ON "public"."toptions" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_75723" AFTER INSERT ON "public"."toptions" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_75723" ON "public"."toptions" IS NULL;

-- ----------------------------
--  Primary key structure for table toffer
-- ----------------------------
ALTER TABLE "public"."toffer" ADD CONSTRAINT "toffer_pkey" PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Triggers structure for table toffer
-- ----------------------------
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_75734" AFTER UPDATE ON "public"."toffer" FROM "public"."tbuyer" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_75734" ON "public"."toffer" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_75733" AFTER INSERT ON "public"."toffer" FROM "public"."tbuyer" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_75733" ON "public"."toffer" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_75729" AFTER UPDATE ON "public"."toffer" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_75729" ON "public"."toffer" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_75728" AFTER INSERT ON "public"."toffer" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_75728" ON "public"."toffer" IS NULL;

-- ----------------------------
--  Primary key structure for table tbuyer
-- ----------------------------
ALTER TABLE "public"."tbuyer" ADD CONSTRAINT "tbuyer_pkey" PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Triggers structure for table tbuyer
-- ----------------------------
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_75704" AFTER UPDATE ON "public"."tbuyer" FROM "public"."tperson" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_75704" ON "public"."tbuyer" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_75703" AFTER INSERT ON "public"."tbuyer" FROM "public"."tperson" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_75703" ON "public"."tbuyer" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_75732" AFTER UPDATE ON "public"."tbuyer" FROM "public"."toffer" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_75732" ON "public"."tbuyer" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_75731" AFTER DELETE ON "public"."tbuyer" FROM "public"."toffer" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_75731" ON "public"."tbuyer" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_75753" AFTER UPDATE ON "public"."tbuyer" FROM "public"."tinterest" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_75753" ON "public"."tbuyer" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_75752" AFTER DELETE ON "public"."tbuyer" FROM "public"."tinterest" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_75752" ON "public"."tbuyer" IS NULL;

-- ----------------------------
--  Primary key structure for table tinterest
-- ----------------------------
ALTER TABLE "public"."tinterest" ADD CONSTRAINT "tinterest_pkey" PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Triggers structure for table tinterest
-- ----------------------------
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_75755" AFTER UPDATE ON "public"."tinterest" FROM "public"."tbuyer" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_75755" ON "public"."tinterest" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_75754" AFTER INSERT ON "public"."tinterest" FROM "public"."tbuyer" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_75754" ON "public"."tinterest" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_75760" AFTER UPDATE ON "public"."tinterest" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_75760" ON "public"."tinterest" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_75759" AFTER INSERT ON "public"."tinterest" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_75759" ON "public"."tinterest" IS NULL;

-- ----------------------------
--  Primary key structure for table tbien
-- ----------------------------
ALTER TABLE "public"."tbien" ADD CONSTRAINT "tbien_pkey" PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Triggers structure for table tbien
-- ----------------------------
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_75714" AFTER UPDATE ON "public"."tbien" FROM "public"."tcity" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_75714" ON "public"."tbien" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_75713" AFTER INSERT ON "public"."tbien" FROM "public"."tcity" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_75713" ON "public"."tbien" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_75709" AFTER UPDATE ON "public"."tbien" FROM "public"."towner" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_75709" ON "public"."tbien" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_75708" AFTER INSERT ON "public"."tbien" FROM "public"."towner" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_75708" ON "public"."tbien" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_75717" AFTER UPDATE ON "public"."tbien" FROM "public"."troom" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_75717" ON "public"."tbien" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_75716" AFTER DELETE ON "public"."tbien" FROM "public"."troom" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_75716" ON "public"."tbien" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_75722" AFTER UPDATE ON "public"."tbien" FROM "public"."toptions" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_75722" ON "public"."tbien" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_75721" AFTER DELETE ON "public"."tbien" FROM "public"."toptions" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_75721" ON "public"."tbien" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_75727" AFTER UPDATE ON "public"."tbien" FROM "public"."toffer" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_75727" ON "public"."tbien" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_75726" AFTER DELETE ON "public"."tbien" FROM "public"."toffer" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_75726" ON "public"."tbien" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_75758" AFTER UPDATE ON "public"."tbien" FROM "public"."tinterest" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_75758" ON "public"."tbien" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_75757" AFTER DELETE ON "public"."tbien" FROM "public"."tinterest" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_75757" ON "public"."tbien" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_75789" AFTER UPDATE ON "public"."tbien" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_75789" ON "public"."tbien" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_75788" AFTER DELETE ON "public"."tbien" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_cascade_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_75788" ON "public"."tbien" IS NULL;

-- ----------------------------
--  Primary key structure for table timages
-- ----------------------------
ALTER TABLE "public"."timages" ADD CONSTRAINT "timage_pkey" PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Triggers structure for table timages
-- ----------------------------
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_75791" AFTER UPDATE ON "public"."timages" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_75791" ON "public"."timages" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_75790" AFTER INSERT ON "public"."timages" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_75790" ON "public"."timages" IS NULL;

-- ----------------------------
--  Foreign keys structure for table tlocality
-- ----------------------------
ALTER TABLE "public"."tlocality" ADD CONSTRAINT "fk_locality_region" FOREIGN KEY ("region_id") REFERENCES "public"."tregion" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table tregion
-- ----------------------------
ALTER TABLE "public"."tregion" ADD CONSTRAINT "fk_region_country" FOREIGN KEY ("country_id") REFERENCES "public"."tcountry" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table taddress
-- ----------------------------
ALTER TABLE "public"."taddress" ADD CONSTRAINT "fk_address_person" FOREIGN KEY ("person_id") REFERENCES "public"."tperson" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE "public"."taddress" ADD CONSTRAINT "fk_address_city" FOREIGN KEY ("city_id") REFERENCES "public"."tcity" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table tcity
-- ----------------------------
ALTER TABLE "public"."tcity" ADD CONSTRAINT "fk_city_locality" FOREIGN KEY ("locality_id") REFERENCES "public"."tlocality" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE "public"."tcity" ADD CONSTRAINT "fk_city_lang" FOREIGN KEY ("lang_id") REFERENCES "public"."tlang" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table towner
-- ----------------------------
ALTER TABLE "public"."towner" ADD CONSTRAINT "fk_owner_person" FOREIGN KEY ("person_id") REFERENCES "public"."tperson" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table tsaler
-- ----------------------------
ALTER TABLE "public"."tsaler" ADD CONSTRAINT "fk_saler_person" FOREIGN KEY ("person_id") REFERENCES "public"."tperson" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table tcustomer
-- ----------------------------
ALTER TABLE "public"."tcustomer" ADD CONSTRAINT "fk_customer_person" FOREIGN KEY ("person_id") REFERENCES "public"."tperson" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table troom
-- ----------------------------
ALTER TABLE "public"."troom" ADD CONSTRAINT "fk_room_bien" FOREIGN KEY ("bien_id") REFERENCES "public"."tbien" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table toptions
-- ----------------------------
ALTER TABLE "public"."toptions" ADD CONSTRAINT "fk_options_bien" FOREIGN KEY ("bien_id") REFERENCES "public"."tbien" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table toffer
-- ----------------------------
ALTER TABLE "public"."toffer" ADD CONSTRAINT "fk_offer_bien" FOREIGN KEY ("bien_id") REFERENCES "public"."tbien" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE "public"."toffer" ADD CONSTRAINT "fk_offer_buyer" FOREIGN KEY ("buyer_id") REFERENCES "public"."tbuyer" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table tbuyer
-- ----------------------------
ALTER TABLE "public"."tbuyer" ADD CONSTRAINT "fk_buyer_person" FOREIGN KEY ("person_id") REFERENCES "public"."tperson" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table tinterest
-- ----------------------------
ALTER TABLE "public"."tinterest" ADD CONSTRAINT "fk_interest_buyer" FOREIGN KEY ("buyer_id") REFERENCES "public"."tbuyer" ("id") ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE "public"."tinterest" ADD CONSTRAINT "fk_interest_bien" FOREIGN KEY ("bien_id") REFERENCES "public"."tbien" ("id") ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table tbien
-- ----------------------------
ALTER TABLE "public"."tbien" ADD CONSTRAINT "fk_bien_owner" FOREIGN KEY ("owner_id") REFERENCES "public"."towner" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE "public"."tbien" ADD CONSTRAINT "fk_bien_city" FOREIGN KEY ("city_id") REFERENCES "public"."tcity" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table timages
-- ----------------------------
ALTER TABLE "public"."timages" ADD CONSTRAINT "fk_images_bien" FOREIGN KEY ("bien_id") REFERENCES "public"."tbien" ("id") ON UPDATE CASCADE ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE;

