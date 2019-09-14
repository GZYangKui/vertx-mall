/*
 Navicat Premium Data Transfer

 Source Server         : postgresql
 Source Server Type    : PostgreSQL
 Source Server Version : 110004
 Source Host           : localhost:5432
 Source Catalog        : mall
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 110004
 File Encoding         : 65001

 Date: 14/09/2019 17:42:48
*/


-- ----------------------------
-- Sequence structure for address_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."address_id_seq";
CREATE SEQUENCE "public"."address_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for admin_role_relation_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."admin_role_relation_id_seq";
CREATE SEQUENCE "public"."admin_role_relation_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for cart_item_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."cart_item_id_seq";
CREATE SEQUENCE "public"."cart_item_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for coupon_history_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."coupon_history_id_seq";
CREATE SEQUENCE "public"."coupon_history_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for coupon_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."coupon_id_seq";
CREATE SEQUENCE "public"."coupon_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for flash_promotion_product_relation_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."flash_promotion_product_relation_id_seq";
CREATE SEQUENCE "public"."flash_promotion_product_relation_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for flash_promotion_session_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."flash_promotion_session_id_seq";
CREATE SEQUENCE "public"."flash_promotion_session_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for home_advertise_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."home_advertise_id_seq";
CREATE SEQUENCE "public"."home_advertise_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for home_recommend_subject_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."home_recommend_subject_id_seq";
CREATE SEQUENCE "public"."home_recommend_subject_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for mall_admin_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."mall_admin_id_seq";
CREATE SEQUENCE "public"."mall_admin_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for mall_admin_login_log_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."mall_admin_login_log_id_seq";
CREATE SEQUENCE "public"."mall_admin_login_log_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for mall_brand_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."mall_brand_id_seq";
CREATE SEQUENCE "public"."mall_brand_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for mall_order_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."mall_order_id_seq";
CREATE SEQUENCE "public"."mall_order_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for mall_order_item_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."mall_order_item_id_seq";
CREATE SEQUENCE "public"."mall_order_item_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for mall_permission_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."mall_permission_id_seq";
CREATE SEQUENCE "public"."mall_permission_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for mall_role_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."mall_role_id_seq";
CREATE SEQUENCE "public"."mall_role_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for mall_subject_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."mall_subject_id_seq";
CREATE SEQUENCE "public"."mall_subject_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for member_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."member_id_seq";
CREATE SEQUENCE "public"."member_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for preference_area_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."preference_area_id_seq";
CREATE SEQUENCE "public"."preference_area_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for prefrence_area_product_relation_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."prefrence_area_product_relation_id_seq";
CREATE SEQUENCE "public"."prefrence_area_product_relation_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for product_attribute_category_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."product_attribute_category_id_seq";
CREATE SEQUENCE "public"."product_attribute_category_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for product_attribute_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."product_attribute_id_seq";
CREATE SEQUENCE "public"."product_attribute_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for product_attribute_value_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."product_attribute_value_id_seq";
CREATE SEQUENCE "public"."product_attribute_value_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for product_category_attribute_relation_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."product_category_attribute_relation_id_seq";
CREATE SEQUENCE "public"."product_category_attribute_relation_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for product_category_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."product_category_id_seq";
CREATE SEQUENCE "public"."product_category_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for product_commnet_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."product_commnet_id_seq";
CREATE SEQUENCE "public"."product_commnet_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for product_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."product_id_seq";
CREATE SEQUENCE "public"."product_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for product_subject_relation_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."product_subject_relation_id_seq";
CREATE SEQUENCE "public"."product_subject_relation_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for promotion_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."promotion_id_seq";
CREATE SEQUENCE "public"."promotion_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for role_permission_relation_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."role_permission_relation_id_seq";
CREATE SEQUENCE "public"."role_permission_relation_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for sku_stock_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."sku_stock_id_seq";
CREATE SEQUENCE "public"."sku_stock_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for subject_category_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."subject_category_id_seq";
CREATE SEQUENCE "public"."subject_category_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for subject_comment_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."subject_comment_id_seq";
CREATE SEQUENCE "public"."subject_comment_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for today_recommend_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."today_recommend_id_seq";
CREATE SEQUENCE "public"."today_recommend_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Table structure for admin_role_relation
-- ----------------------------
DROP TABLE IF EXISTS "public"."admin_role_relation";
CREATE TABLE "public"."admin_role_relation" (
  "id" int4 NOT NULL DEFAULT nextval('admin_role_relation_id_seq'::regclass),
  "admin_id" int4 NOT NULL,
  "role_id" int4 NOT NULL
)
;
COMMENT ON COLUMN "public"."admin_role_relation"."admin_id" IS '管理员id';
COMMENT ON COLUMN "public"."admin_role_relation"."role_id" IS '角色id';
COMMENT ON TABLE "public"."admin_role_relation" IS '用户角色关联表';

-- ----------------------------
-- Records of admin_role_relation
-- ----------------------------
INSERT INTO "public"."admin_role_relation" VALUES (3, 1, 1);

-- ----------------------------
-- Table structure for cart_item
-- ----------------------------
DROP TABLE IF EXISTS "public"."cart_item";
CREATE TABLE "public"."cart_item" (
  "id" int4 NOT NULL DEFAULT nextval('cart_item_id_seq'::regclass),
  "product_id" int4 NOT NULL,
  "product_sku_id" int4,
  "member_id" int4 NOT NULL,
  "quantity" int4 NOT NULL,
  "price" int4 NOT NULL,
  "product_pic" text COLLATE "pg_catalog"."default",
  "product_name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "product_sub_title" varchar(255) COLLATE "pg_catalog"."default",
  "product_sku_code" varchar(100) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6) NOT NULL,
  "modify_date" timestamp(6),
  "product_category_id" int4 NOT NULL,
  "product_brand" varchar COLLATE "pg_catalog"."default",
  "product_sn" varchar COLLATE "pg_catalog"."default",
  "product_attr" text COLLATE "pg_catalog"."default",
  "version" int8 NOT NULL DEFAULT 0
)
;
COMMENT ON COLUMN "public"."cart_item"."product_sku_id" IS '库存id';
COMMENT ON COLUMN "public"."cart_item"."quantity" IS '商品数量';
COMMENT ON COLUMN "public"."cart_item"."price" IS '价格';
COMMENT ON COLUMN "public"."cart_item"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."cart_item"."product_sn" IS 'chanping编号';
COMMENT ON COLUMN "public"."cart_item"."product_attr" IS '商品销售属性:[{"key":"颜色","value":"颜色"},{"key":"容量","value":"4G"}]';
COMMENT ON TABLE "public"."cart_item" IS '购物车item';

-- ----------------------------
-- Records of cart_item
-- ----------------------------
INSERT INTO "public"."cart_item" VALUES (1, 3, NULL, 3, 1, 5000000, 'https://aiwulele.oss-cn-beijing.aliyuncs.com/mall/images/1e060666-2dd0-4388-8160-8295271a1963/5bee9f0eN6576dcf6.jpg', 'AMD 锐龙R7 2700X电脑主机', NULL, NULL, '2019-07-29 18:19:24', NULL, 1, NULL, 'No.123456', NULL, 0);

-- ----------------------------
-- Table structure for coupon
-- ----------------------------
DROP TABLE IF EXISTS "public"."coupon";
CREATE TABLE "public"."coupon" (
  "id" int4 NOT NULL DEFAULT nextval('coupon_id_seq'::regclass),
  "type" int2 NOT NULL,
  "name" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "amount" int8 NOT NULL,
  "per_limit" int4 NOT NULL,
  "min_point" int8 NOT NULL,
  "start_time" timestamp(6) NOT NULL,
  "end_time" timestamp(6) NOT NULL,
  "use_type" int2 NOT NULL,
  "note" varchar(255) COLLATE "pg_catalog"."default",
  "count" int8 NOT NULL,
  "receive_count" int8 NOT NULL DEFAULT 0,
  "version" int8 NOT NULL DEFAULT 0
)
;
COMMENT ON COLUMN "public"."coupon"."type" IS '优惠券类型 0->全场赠卷 1->会员赠卷 2->购物赠卷 3->注册赠卷';
COMMENT ON COLUMN "public"."coupon"."amount" IS '优惠券金额';
COMMENT ON COLUMN "public"."coupon"."per_limit" IS '每人限制领取数量';
COMMENT ON COLUMN "public"."coupon"."min_point" IS '使用门槛 0->代表无门槛';
COMMENT ON COLUMN "public"."coupon"."start_time" IS '优惠券开始领取时间';
COMMENT ON COLUMN "public"."coupon"."end_time" IS '优惠券截止领取时间';
COMMENT ON COLUMN "public"."coupon"."use_type" IS '使用类型：0->全场通用；1->指定分类；2->指定商品';
COMMENT ON COLUMN "public"."coupon"."note" IS '备注信息';
COMMENT ON COLUMN "public"."coupon"."count" IS '优惠券发现量';
COMMENT ON COLUMN "public"."coupon"."receive_count" IS '已领取数量';
COMMENT ON TABLE "public"."coupon" IS '商城优惠券表';

-- ----------------------------
-- Records of coupon
-- ----------------------------
INSERT INTO "public"."coupon" VALUES (3, 0, ' 新人全场通用优惠券', 10, 1, 0, '2019-07-25 10:59:42', '2019-07-26 10:59:45', 0, NULL, 1000, 0, 0);
INSERT INTO "public"."coupon" VALUES (4, 0, 'test', 20, 1, 100, '2019-07-26 11:14:31', '2019-07-27 11:14:38', 0, NULL, 1000, 0, 0);

-- ----------------------------
-- Table structure for coupon_history
-- ----------------------------
DROP TABLE IF EXISTS "public"."coupon_history";
CREATE TABLE "public"."coupon_history" (
  "id" int4 NOT NULL DEFAULT nextval('coupon_history_id_seq'::regclass),
  "coupon_id" int4 NOT NULL,
  "member_id" int4 NOT NULL,
  "member_nickname" text COLLATE "pg_catalog"."default" NOT NULL,
  "type" int2 NOT NULL,
  "create_time" timestamp(6) NOT NULL,
  "status" int2 NOT NULL DEFAULT 0,
  "use_time" timestamp(6),
  "order_id" int4,
  "order_sn" varchar COLLATE "pg_catalog"."default",
  "version" int8 NOT NULL DEFAULT 0
)
;
COMMENT ON COLUMN "public"."coupon_history"."member_nickname" IS '会员昵称';
COMMENT ON COLUMN "public"."coupon_history"."type" IS '获取类型：0->后台赠送；1->主动获取';
COMMENT ON COLUMN "public"."coupon_history"."status" IS '使用状态：0->未使用；1->已使用；2->已过期';
COMMENT ON COLUMN "public"."coupon_history"."use_time" IS '使用时间';
COMMENT ON TABLE "public"."coupon_history" IS '优惠券领取记录';

-- ----------------------------
-- Records of coupon_history
-- ----------------------------
INSERT INTO "public"."coupon_history" VALUES (1, 3, 3, 'yangkui', 1, '2019-07-25 12:35:23', 0, NULL, NULL, NULL, 0);
INSERT INTO "public"."coupon_history" VALUES (2, 4, 3, 'yangkui', 1, '2019-07-25 12:39:16', 1, NULL, NULL, NULL, 0);
INSERT INTO "public"."coupon_history" VALUES (3, 4, 3, 'yangkui', 1, '2019-07-26 12:39:38', 2, NULL, NULL, NULL, 0);

-- ----------------------------
-- Table structure for flash_promotion_product_relation
-- ----------------------------
DROP TABLE IF EXISTS "public"."flash_promotion_product_relation";
CREATE TABLE "public"."flash_promotion_product_relation" (
  "id" int4 NOT NULL DEFAULT nextval('flash_promotion_product_relation_id_seq'::regclass),
  "flash_promotion_session_id" int4 NOT NULL,
  "product_id" int4 NOT NULL,
  "flash_promotion_price" int4 NOT NULL,
  "flash_promotion_count" int4 NOT NULL,
  "flash_promotion_limit" int4 NOT NULL DEFAULT 1,
  "sort" int8 NOT NULL DEFAULT 0,
  "version" int8 NOT NULL DEFAULT 0,
  "flash_promotion_receive" int8 NOT NULL DEFAULT 0
)
;
COMMENT ON COLUMN "public"."flash_promotion_product_relation"."flash_promotion_price" IS '限时抢购价格';
COMMENT ON COLUMN "public"."flash_promotion_product_relation"."flash_promotion_count" IS '限时抢购数量';
COMMENT ON COLUMN "public"."flash_promotion_product_relation"."flash_promotion_limit" IS '每个人限制抢购数量';
COMMENT ON COLUMN "public"."flash_promotion_product_relation"."flash_promotion_receive" IS '已经领取数量';
COMMENT ON TABLE "public"."flash_promotion_product_relation" IS '限时请购商品关联表';

-- ----------------------------
-- Records of flash_promotion_product_relation
-- ----------------------------
INSERT INTO "public"."flash_promotion_product_relation" VALUES (2, 1, 3, 5000, 100, 1, 0, 0, 0);
INSERT INTO "public"."flash_promotion_product_relation" VALUES (3, 1, 13, 5000, 100, 1, 0, 0, 0);
INSERT INTO "public"."flash_promotion_product_relation" VALUES (4, 1, 14, 5000, 100, 1, 0, 0, 0);
INSERT INTO "public"."flash_promotion_product_relation" VALUES (5, 1, 9, 5000, 100, 1, 0, 0, 0);

-- ----------------------------
-- Table structure for flash_promotion_session
-- ----------------------------
DROP TABLE IF EXISTS "public"."flash_promotion_session";
CREATE TABLE "public"."flash_promotion_session" (
  "id" int4 NOT NULL DEFAULT nextval('flash_promotion_session_id_seq'::regclass),
  "name" varchar COLLATE "pg_catalog"."default" NOT NULL,
  "start_time" time(6) NOT NULL,
  "end_time" time(6) NOT NULL,
  "status" int2 NOT NULL,
  "create_time" timestamp(6) NOT NULL
)
;
COMMENT ON COLUMN "public"."flash_promotion_session"."name" IS '场次名称';
COMMENT ON COLUMN "public"."flash_promotion_session"."create_time" IS '创建时间';
COMMENT ON TABLE "public"."flash_promotion_session" IS '限时抢购场次';

-- ----------------------------
-- Records of flash_promotion_session
-- ----------------------------
INSERT INTO "public"."flash_promotion_session" VALUES (1, '10:00', '00:00:00', '23:59:59', 1, '2019-07-31 13:48:38');

-- ----------------------------
-- Table structure for home_advertise
-- ----------------------------
DROP TABLE IF EXISTS "public"."home_advertise";
CREATE TABLE "public"."home_advertise" (
  "id" int4 NOT NULL DEFAULT nextval('home_advertise_id_seq'::regclass),
  "name" text COLLATE "pg_catalog"."default" NOT NULL,
  "type" int2 NOT NULL DEFAULT 0,
  "pic" text COLLATE "pg_catalog"."default" NOT NULL,
  "start_time" timestamp(6) NOT NULL,
  "end_time" timestamp(6) NOT NULL,
  "status" int2 NOT NULL DEFAULT 0,
  "click_count" int8 NOT NULL DEFAULT 0,
  "url" text COLLATE "pg_catalog"."default",
  "note" text COLLATE "pg_catalog"."default",
  "sort" int4 NOT NULL DEFAULT 0,
  "version" int8 NOT NULL DEFAULT 0
)
;
COMMENT ON COLUMN "public"."home_advertise"."type" IS '轮播图类型 0-> web首页 1-> app首页';
COMMENT ON COLUMN "public"."home_advertise"."pic" IS '图片地址链接';
COMMENT ON COLUMN "public"."home_advertise"."start_time" IS '展示开始时间';
COMMENT ON COLUMN "public"."home_advertise"."end_time" IS '展示结束时间';
COMMENT ON COLUMN "public"."home_advertise"."status" IS '状态 0->未上线 1->上线';
COMMENT ON COLUMN "public"."home_advertise"."click_count" IS '点击数';
COMMENT ON COLUMN "public"."home_advertise"."url" IS '链接地址';
COMMENT ON COLUMN "public"."home_advertise"."note" IS '备注信息';
COMMENT ON TABLE "public"."home_advertise" IS '商城首页轮播广告';

-- ----------------------------
-- Records of home_advertise
-- ----------------------------
INSERT INTO "public"."home_advertise" VALUES (3, '夏季大热促销', 0, 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/xiaomi.jpg', '2019-07-28 11:11:42', '2019-11-01 11:11:46', 1, 0, NULL, NULL, 0, 0);
INSERT INTO "public"."home_advertise" VALUES (4, '电影推荐广告', 0, 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20181113/movie_ad.jpg', '2019-07-28 11:13:27', '2019-10-01 11:13:31', 1, 0, NULL, NULL, 100, 0);

-- ----------------------------
-- Table structure for home_recommend_subject
-- ----------------------------
DROP TABLE IF EXISTS "public"."home_recommend_subject";
CREATE TABLE "public"."home_recommend_subject" (
  "id" int4 NOT NULL DEFAULT nextval('home_recommend_subject_id_seq'::regclass),
  "subject_id" int4 NOT NULL,
  "recommend_status" int2 NOT NULL DEFAULT 0,
  "sort" int4 NOT NULL DEFAULT 0
)
;
COMMENT ON COLUMN "public"."home_recommend_subject"."subject_id" IS '专题id';
COMMENT ON COLUMN "public"."home_recommend_subject"."recommend_status" IS '推荐状态 0->推荐 1->不推荐';
COMMENT ON COLUMN "public"."home_recommend_subject"."sort" IS '排序';
COMMENT ON TABLE "public"."home_recommend_subject" IS '首页推荐专题';

-- ----------------------------
-- Records of home_recommend_subject
-- ----------------------------
INSERT INTO "public"."home_recommend_subject" VALUES (1, 4, 1, 0);
INSERT INTO "public"."home_recommend_subject" VALUES (2, 6, 1, 1);

-- ----------------------------
-- Table structure for mall_admin
-- ----------------------------
DROP TABLE IF EXISTS "public"."mall_admin";
CREATE TABLE "public"."mall_admin" (
  "username" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "password" varchar(40) COLLATE "pg_catalog"."default" NOT NULL,
  "icon" varchar(255) COLLATE "pg_catalog"."default",
  "email" varchar(255) COLLATE "pg_catalog"."default",
  "nick_name" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "create_time" timestamp(6) NOT NULL,
  "status" int2 NOT NULL DEFAULT 0,
  "id" int4 NOT NULL DEFAULT nextval('mall_admin_id_seq'::regclass)
)
;
COMMENT ON COLUMN "public"."mall_admin"."username" IS '管理员用户名';
COMMENT ON COLUMN "public"."mall_admin"."password" IS '管理员密码';
COMMENT ON COLUMN "public"."mall_admin"."icon" IS '管理员头像';
COMMENT ON COLUMN "public"."mall_admin"."nick_name" IS '管理员昵称';
COMMENT ON COLUMN "public"."mall_admin"."create_time" IS '账户创建时间';
COMMENT ON COLUMN "public"."mall_admin"."status" IS '账户状态  0->禁用 1->启用';
COMMENT ON TABLE "public"."mall_admin" IS '商场管理员账号';

-- ----------------------------
-- Records of mall_admin
-- ----------------------------
INSERT INTO "public"."mall_admin" VALUES ('admin', '7961d23a73e658d51536cb275458e2e7281fb0d4', 'https://avatars1.githubusercontent.com/u/40876227?s=400&u=2fd68fc940879ad29c55980794dbc5818c089cb0&v=4', '752544765@qq.com', '飞翔的蜗牛', '2109-09-09 14:33:22', 1, 1);

-- ----------------------------
-- Table structure for mall_admin_login_log
-- ----------------------------
DROP TABLE IF EXISTS "public"."mall_admin_login_log";
CREATE TABLE "public"."mall_admin_login_log" (
  "id" int4 NOT NULL DEFAULT nextval('mall_admin_login_log_id_seq'::regclass),
  "admin_id" int4 NOT NULL,
  "create_time" timestamp(6) NOT NULL,
  "ip" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
  "user_agent" varchar(255) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."mall_admin_login_log"."admin_id" IS '管理员账号id';
COMMENT ON COLUMN "public"."mall_admin_login_log"."create_time" IS '记录创建时间';
COMMENT ON COLUMN "public"."mall_admin_login_log"."ip" IS 'IP地址';
COMMENT ON COLUMN "public"."mall_admin_login_log"."user_agent" IS '浏览器信息';
COMMENT ON TABLE "public"."mall_admin_login_log" IS '管理员登录日志';

-- ----------------------------
-- Records of mall_admin_login_log
-- ----------------------------
INSERT INTO "public"."mall_admin_login_log" VALUES (1, 1, '2019-09-09 16:12:42.581839', '', 'PostmanRuntime/7.16.3');
INSERT INTO "public"."mall_admin_login_log" VALUES (2, 1, '2019-09-09 16:15:45.822178', '0:0:0:0:0:0:0:1', 'PostmanRuntime/7.16.3');
INSERT INTO "public"."mall_admin_login_log" VALUES (3, 1, '2019-09-09 16:23:44.142815', '0:0:0:0:0:0:0:1', 'PostmanRuntime/7.16.3');
INSERT INTO "public"."mall_admin_login_log" VALUES (4, 1, '2019-09-10 21:24:57.354024', '0:0:0:0:0:0:0:1', 'PostmanRuntime/7.16.3');
INSERT INTO "public"."mall_admin_login_log" VALUES (5, 1, '2019-09-11 17:10:44.453515', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (6, 1, '2019-09-11 17:10:54.175546', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (7, 1, '2019-09-11 20:41:41.398403', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (8, 1, '2019-09-11 20:44:42.261033', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (9, 1, '2019-09-11 20:48:23.858739', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (10, 1, '2019-09-11 22:19:47.563707', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (11, 1, '2019-09-11 22:20:11.342962', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (12, 1, '2019-09-11 22:21:16.481264', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (13, 1, '2019-09-11 22:27:11.872581', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (14, 1, '2019-09-12 09:08:38.843667', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (15, 1, '2019-09-12 09:09:15.176385', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (16, 1, '2019-09-12 10:21:01.292359', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (17, 1, '2019-09-12 10:22:51.845379', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (18, 1, '2019-09-12 10:23:43.624817', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (19, 1, '2019-09-12 10:27:56.032567', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (20, 1, '2019-09-12 10:29:00.7622', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (21, 1, '2019-09-12 10:30:41.109414', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (22, 1, '2019-09-12 10:33:35.754806', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (23, 1, '2019-09-12 10:34:28.827659', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (24, 1, '2019-09-12 10:35:18.59342', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (25, 1, '2019-09-12 10:37:46.325137', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (26, 1, '2019-09-12 10:38:26.885156', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (27, 1, '2019-09-12 10:46:57.657794', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (28, 1, '2019-09-12 10:48:24.970158', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (29, 1, '2019-09-12 10:50:38.089112', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (30, 1, '2019-09-12 10:52:08.267574', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (31, 1, '2019-09-12 10:52:59.338706', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (32, 1, '2019-09-12 10:53:44.809615', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (33, 1, '2019-09-12 10:54:18.737315', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (34, 1, '2019-09-12 10:55:02.103192', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (35, 1, '2019-09-12 10:55:25.929503', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (36, 1, '2019-09-12 10:55:51.339589', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (37, 1, '2019-09-12 10:57:36.843364', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (38, 1, '2019-09-12 11:06:09.042577', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (39, 1, '2019-09-12 11:13:44.225915', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (40, 1, '2019-09-12 11:16:13.905085', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (41, 1, '2019-09-12 11:19:08.867454', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (42, 1, '2019-09-12 11:19:49.211893', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (43, 1, '2019-09-12 11:20:45.344345', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (44, 1, '2019-09-12 11:25:38.206967', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (45, 1, '2019-09-12 11:27:34.930854', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (46, 1, '2019-09-12 11:42:55.54605', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (47, 1, '2019-09-12 15:43:35.110818', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (48, 1, '2019-09-12 15:47:54.650868', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (49, 1, '2019-09-12 15:51:17.21133', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (50, 1, '2019-09-12 15:53:05.44801', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (51, 1, '2019-09-12 15:53:50.042143', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (52, 1, '2019-09-12 15:55:41.051998', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (53, 1, '2019-09-12 15:57:53.051018', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (54, 1, '2019-09-12 16:14:31.920888', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (55, 1, '2019-09-12 16:16:32.681381', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (56, 1, '2019-09-12 16:20:18.544672', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (57, 1, '2019-09-12 16:50:17.663999', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (58, 1, '2019-09-12 16:50:32.893376', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (59, 1, '2019-09-12 16:51:16.951432', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (60, 1, '2019-09-12 16:52:57.732254', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (61, 1, '2019-09-12 16:53:03.297737', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (62, 1, '2019-09-12 16:54:47.817372', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (63, 1, '2019-09-12 16:55:42.819567', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (64, 1, '2019-09-12 17:11:30.732811', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (65, 1, '2019-09-12 18:27:04.336555', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (66, 1, '2019-09-12 22:21:18.307121', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (67, 1, '2019-09-13 12:02:28.845464', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (68, 1, '2019-09-13 12:03:08.908208', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (69, 1, '2019-09-13 12:04:08.921754', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (70, 1, '2019-09-13 12:05:48.01292', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (71, 1, '2019-09-13 12:08:15.309542', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (72, 1, '2019-09-13 12:08:57.425524', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (73, 1, '2019-09-13 12:09:55.974553', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (74, 1, '2019-09-13 12:10:50.895787', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (75, 1, '2019-09-13 12:11:45.413989', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (76, 1, '2019-09-13 19:50:58.171143', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (77, 1, '2019-09-13 19:52:04.145916', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (78, 1, '2019-09-13 19:52:50.473571', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (79, 1, '2019-09-13 19:53:35.218262', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (80, 1, '2019-09-13 19:53:59.098935', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (81, 1, '2019-09-13 20:03:47.970779', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (82, 1, '2019-09-13 20:04:26.581313', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (83, 1, '2019-09-13 20:30:50.053866', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (84, 1, '2019-09-13 20:41:46.946499', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (85, 1, '2019-09-13 20:42:04.869644', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (86, 1, '2019-09-14 09:51:09.465938', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (87, 1, '2019-09-14 09:54:19.986863', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (88, 1, '2019-09-14 09:57:17.770027', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (89, 1, '2019-09-14 10:08:39.80726', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (90, 1, '2019-09-14 10:09:11.959935', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (91, 1, '2019-09-14 10:09:47.939918', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (92, 1, '2019-09-14 10:10:11.160537', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (93, 1, '2019-09-14 10:11:00.634917', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (94, 1, '2019-09-14 10:11:25.905443', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (95, 1, '2019-09-14 10:11:46.847616', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (96, 1, '2019-09-14 10:12:57.743289', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (97, 1, '2019-09-14 10:16:47.048455', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (98, 1, '2019-09-14 10:17:23.99052', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (99, 1, '2019-09-14 10:18:46.027079', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (100, 1, '2019-09-14 10:22:30.132177', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (101, 1, '2019-09-14 10:24:10.628649', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (102, 1, '2019-09-14 10:26:06.912258', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (103, 1, '2019-09-14 10:27:15.792981', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (104, 1, '2019-09-14 10:27:26.93901', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (105, 1, '2019-09-14 10:28:01.918763', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (106, 1, '2019-09-14 10:29:40.892942', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (107, 1, '2019-09-14 10:30:23.499808', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (108, 1, '2019-09-14 10:38:09.912901', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (109, 1, '2019-09-14 12:45:47.814634', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (110, 1, '2019-09-14 12:46:40.864484', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (111, 1, '2019-09-14 12:47:56.406842', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (112, 1, '2019-09-14 12:52:19.803796', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (113, 1, '2019-09-14 14:28:37.721228', '127.0.0.1', '');
INSERT INTO "public"."mall_admin_login_log" VALUES (114, 1, '2019-09-14 14:29:32.307194', '127.0.0.1', 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.75 Safari/537.36');
INSERT INTO "public"."mall_admin_login_log" VALUES (115, 1, '2019-09-14 16:34:05.033759', '127.0.0.1', 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.75 Safari/537.36');
INSERT INTO "public"."mall_admin_login_log" VALUES (116, 1, '2019-09-14 16:34:29.829372', '127.0.0.1', 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.75 Safari/537.36');

-- ----------------------------
-- Table structure for mall_brand
-- ----------------------------
DROP TABLE IF EXISTS "public"."mall_brand";
CREATE TABLE "public"."mall_brand" (
  "id" int4 NOT NULL DEFAULT nextval('mall_brand_id_seq'::regclass),
  "name" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "first_letter" varchar(10) COLLATE "pg_catalog"."default" NOT NULL,
  "sort" int8 NOT NULL,
  "show_status" int2 NOT NULL,
  "product_count" int8 NOT NULL,
  "product_comment_count" int8 NOT NULL,
  "logo" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "big_picture" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "brand_story" text COLLATE "pg_catalog"."default" NOT NULL,
  "version" int8 NOT NULL DEFAULT 0,
  "factory_status" int2 NOT NULL
)
;
COMMENT ON COLUMN "public"."mall_brand"."name" IS '品牌名称';
COMMENT ON COLUMN "public"."mall_brand"."first_letter" IS '首字母';
COMMENT ON COLUMN "public"."mall_brand"."sort" IS '品牌排序';
COMMENT ON COLUMN "public"."mall_brand"."show_status" IS '显示状态';
COMMENT ON COLUMN "public"."mall_brand"."product_count" IS '该品牌下商品数量';
COMMENT ON COLUMN "public"."mall_brand"."product_comment_count" IS '该品牌下商品评论数目';
COMMENT ON COLUMN "public"."mall_brand"."logo" IS '品牌logo';
COMMENT ON COLUMN "public"."mall_brand"."big_picture" IS '专区图片';
COMMENT ON COLUMN "public"."mall_brand"."factory_status" IS '是否品牌制造商 0->不是 1->是';
COMMENT ON TABLE "public"."mall_brand" IS '商城品牌';

-- ----------------------------
-- Table structure for mall_order
-- ----------------------------
DROP TABLE IF EXISTS "public"."mall_order";
CREATE TABLE "public"."mall_order" (
  "id" int4 NOT NULL DEFAULT nextval('mall_order_id_seq'::regclass),
  "member_id" int4 NOT NULL,
  "coupon_id" int4 NOT NULL,
  "order_sn" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "member_username" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "total_amount" int8 NOT NULL,
  "pay_amount" int8 NOT NULL,
  "freight_amount" int8 NOT NULL,
  "promotion_amount" int8 NOT NULL DEFAULT 0,
  "integration_amount" int8 NOT NULL DEFAULT 0,
  "coupon_amount" int8 NOT NULL DEFAULT 0,
  "discount_amount" int8 NOT NULL,
  "pay_type" int2 NOT NULL,
  "source_type" int2 NOT NULL,
  "status" int2 NOT NULL,
  "order_type" int2 NOT NULL,
  "auto_confirm_day" int2 NOT NULL DEFAULT 7,
  "integration" int8 NOT NULL,
  "growth" int8 NOT NULL,
  "receiver_name" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "receiver_phone" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "receiver_province" char(100) COLLATE "pg_catalog"."default" NOT NULL,
  "receiver_city" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "receiver_region" varchar(255) COLLATE "pg_catalog"."default",
  "receiver_detail_address" varbit(255),
  "note" text COLLATE "pg_catalog"."default",
  "confirm_status" int2 NOT NULL DEFAULT 0,
  "delete_status" int2 NOT NULL DEFAULT 0,
  "use_integration" int8 NOT NULL,
  "payment_time" timestamp(6),
  "delivery_time" timestamp(6),
  "receive_time" timestamp(6),
  "comment_time" timestamp(6),
  "modify_time" timestamp(6),
  "version" int8 NOT NULL
)
;
COMMENT ON COLUMN "public"."mall_order"."member_id" IS '用户id';
COMMENT ON COLUMN "public"."mall_order"."promotion_amount" IS '促销抵扣金额';
COMMENT ON COLUMN "public"."mall_order"."integration_amount" IS '积分抵扣金额';
COMMENT ON COLUMN "public"."mall_order"."coupon_amount" IS '优惠券抵扣金额';
COMMENT ON COLUMN "public"."mall_order"."discount_amount" IS '管理员后台调整订单使用的折扣金额';
COMMENT ON COLUMN "public"."mall_order"."pay_type" IS '支付方式：0->未支付；1->支付宝；2->微信';
COMMENT ON COLUMN "public"."mall_order"."source_type" IS '订单来源：0->web订单；1->app订单';
COMMENT ON COLUMN "public"."mall_order"."status" IS '订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单';
COMMENT ON COLUMN "public"."mall_order"."order_type" IS '订单类型：0->正常订单；1->秒杀订单';
COMMENT ON COLUMN "public"."mall_order"."auto_confirm_day" IS '自动收货日期(天）默认7天';
COMMENT ON COLUMN "public"."mall_order"."integration" IS '下单所获得积分';
COMMENT ON COLUMN "public"."mall_order"."growth" IS '下单所获得成长值';
COMMENT ON COLUMN "public"."mall_order"."note" IS '备注信息';
COMMENT ON COLUMN "public"."mall_order"."confirm_status" IS '是否确定收货 0->未确定 1->已确定';
COMMENT ON COLUMN "public"."mall_order"."delete_status" IS '删除状态 0—>未删除 1->已删除';
COMMENT ON COLUMN "public"."mall_order"."use_integration" IS '下单时所使用的积分';
COMMENT ON COLUMN "public"."mall_order"."payment_time" IS '支付时间';
COMMENT ON COLUMN "public"."mall_order"."delivery_time" IS '发货时间';
COMMENT ON COLUMN "public"."mall_order"."receive_time" IS '收货时间';
COMMENT ON COLUMN "public"."mall_order"."comment_time" IS '评价时间';
COMMENT ON COLUMN "public"."mall_order"."modify_time" IS '订单修改时间';
COMMENT ON TABLE "public"."mall_order" IS '商城订单';

-- ----------------------------
-- Table structure for mall_order_item
-- ----------------------------
DROP TABLE IF EXISTS "public"."mall_order_item";
CREATE TABLE "public"."mall_order_item" (
  "id" int8 NOT NULL DEFAULT nextval('mall_order_item_id_seq'::regclass),
  "order_id" int4 NOT NULL,
  "order_sn" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "product_id" int4 NOT NULL,
  "product_pic" text COLLATE "pg_catalog"."default",
  "product_name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "product_brand" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "product_sn" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "product_price" int8 NOT NULL,
  "product_quantity" int4 NOT NULL,
  "product_sku_id" int4 NOT NULL,
  "product_sku_code" varchar COLLATE "pg_catalog"."default" NOT NULL,
  "product_category_id" int4 NOT NULL,
  "spa1" varchar(255) COLLATE "pg_catalog"."default",
  "spa2" varchar(255) COLLATE "pg_catalog"."default",
  "sp3" varchar(255) COLLATE "pg_catalog"."default",
  "promotion_name" varchar(255) COLLATE "pg_catalog"."default",
  "promotion_amount" int8,
  "coupon_amount" int8,
  "integration_amount" int8,
  "real_amount" int8 NOT NULL,
  "gift_integration" int4 NOT NULL DEFAULT 0,
  "gift_growth" int4 NOT NULL DEFAULT 0,
  "product_attr" text COLLATE "pg_catalog"."default",
  "version" int4 NOT NULL DEFAULT 0
)
;
COMMENT ON COLUMN "public"."mall_order_item"."real_amount" IS '该商品经过各种抵扣后的实际金额';
COMMENT ON COLUMN "public"."mall_order_item"."gift_integration" IS '获得积分';
COMMENT ON COLUMN "public"."mall_order_item"."gift_growth" IS '获得成长值';
COMMENT ON COLUMN "public"."mall_order_item"."product_attr" IS '商品销售属性:[{"key":"颜色","value":"颜色"},{"key":"容量","value":"4G"}]';
COMMENT ON TABLE "public"."mall_order_item" IS '订单所对应的商品';

-- ----------------------------
-- Table structure for mall_permission
-- ----------------------------
DROP TABLE IF EXISTS "public"."mall_permission";
CREATE TABLE "public"."mall_permission" (
  "id" int4 NOT NULL DEFAULT nextval('mall_permission_id_seq'::regclass),
  "parent_id" int8 NOT NULL DEFAULT 0,
  "name" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "value" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "type" int2 NOT NULL,
  "status" int2 NOT NULL,
  "create_time" timestamp(6) NOT NULL,
  "sort" int8 NOT NULL DEFAULT 0
)
;
COMMENT ON COLUMN "public"."mall_permission"."parent_id" IS '父级权限id';
COMMENT ON COLUMN "public"."mall_permission"."name" IS '权限名称';
COMMENT ON COLUMN "public"."mall_permission"."value" IS '权限值';
COMMENT ON COLUMN "public"."mall_permission"."type" IS '权限类型：0->目录；1->菜单；2->按钮（接口绑定权限)';
COMMENT ON COLUMN "public"."mall_permission"."status" IS '权限状态 0->未启用 1->启用';
COMMENT ON COLUMN "public"."mall_permission"."create_time" IS '权限创建时间';
COMMENT ON COLUMN "public"."mall_permission"."sort" IS '权限排序';
COMMENT ON TABLE "public"."mall_permission" IS '后台角色权限表';

-- ----------------------------
-- Records of mall_permission
-- ----------------------------
INSERT INTO "public"."mall_permission" VALUES (1, 0, '添加管理员', '/api/system/addAdmin', 1, 1, '2019-09-10 21:31:25', 0);
INSERT INTO "public"."mall_permission" VALUES (2, 0, '最高权限', '*', 1, 1, '2019-09-15 10:16:00', 0);

-- ----------------------------
-- Table structure for mall_role
-- ----------------------------
DROP TABLE IF EXISTS "public"."mall_role";
CREATE TABLE "public"."mall_role" (
  "id" int4 NOT NULL DEFAULT nextval('mall_role_id_seq'::regclass),
  "name" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "description" varchar(255) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6) NOT NULL,
  "status" int2 NOT NULL DEFAULT 0,
  "sort" int4 NOT NULL DEFAULT 0
)
;
COMMENT ON COLUMN "public"."mall_role"."name" IS '角色名称';
COMMENT ON COLUMN "public"."mall_role"."description" IS '角色描述';
COMMENT ON COLUMN "public"."mall_role"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."mall_role"."status" IS '角色状态 0->未启用 1->启用';
COMMENT ON TABLE "public"."mall_role" IS '商城后台用户角色';

-- ----------------------------
-- Records of mall_role
-- ----------------------------
INSERT INTO "public"."mall_role" VALUES (1, '超级管理员', '超级管理员', '2019-09-10 21:29:25', 0, 0);

-- ----------------------------
-- Table structure for mall_subject
-- ----------------------------
DROP TABLE IF EXISTS "public"."mall_subject";
CREATE TABLE "public"."mall_subject" (
  "id" int4 NOT NULL DEFAULT nextval('mall_subject_id_seq'::regclass),
  "category_id" int4 NOT NULL,
  "title" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "pic" text COLLATE "pg_catalog"."default",
  "product_count" int4 NOT NULL DEFAULT 0,
  "recommend_status" int2 NOT NULL DEFAULT 0,
  "create_time" timestamp(0) NOT NULL,
  "collect_count" int4 NOT NULL DEFAULT 0,
  "read_count" int4 NOT NULL DEFAULT 0,
  "comment_count" int4 NOT NULL DEFAULT 0,
  "album_pics" text COLLATE "pg_catalog"."default",
  "description" text COLLATE "pg_catalog"."default" NOT NULL,
  "show_status" int2 NOT NULL DEFAULT 0,
  "content" text COLLATE "pg_catalog"."default" NOT NULL,
  "forward_count" int4 NOT NULL DEFAULT 0,
  "version" int8 NOT NULL DEFAULT 0
)
;
COMMENT ON COLUMN "public"."mall_subject"."category_id" IS '专题分类id';
COMMENT ON COLUMN "public"."mall_subject"."title" IS '专题标题';
COMMENT ON COLUMN "public"."mall_subject"."pic" IS '专题主图';
COMMENT ON COLUMN "public"."mall_subject"."product_count" IS '关联商品数量';
COMMENT ON COLUMN "public"."mall_subject"."recommend_status" IS '推荐状态';
COMMENT ON COLUMN "public"."mall_subject"."create_time" IS '专题创建时间';
COMMENT ON COLUMN "public"."mall_subject"."collect_count" IS '专题收藏数目';
COMMENT ON COLUMN "public"."mall_subject"."read_count" IS '专题阅读数目';
COMMENT ON COLUMN "public"."mall_subject"."comment_count" IS '专题评论次数';
COMMENT ON COLUMN "public"."mall_subject"."album_pics" IS '专题画廊';
COMMENT ON COLUMN "public"."mall_subject"."description" IS '专题描述';
COMMENT ON COLUMN "public"."mall_subject"."show_status" IS '专题显示状态0->不显示；1->显示';
COMMENT ON COLUMN "public"."mall_subject"."content" IS '专题内容';
COMMENT ON COLUMN "public"."mall_subject"."forward_count" IS '专题转发数量';
COMMENT ON COLUMN "public"."mall_subject"."version" IS 'CAS ';
COMMENT ON TABLE "public"."mall_subject" IS '商城专题';

-- ----------------------------
-- Records of mall_subject
-- ----------------------------
INSERT INTO "public"."mall_subject" VALUES (4, 1, '轮廓分明，双摄手机映现细腻美照', 'https://img10.360buyimg.com/mobilecms/s1500x600_jfs/t26434/217/1381240043/254214/290f9d5b/5bc6c11cN54567a27.jpg!q70.jpg', 0, 0, '2019-07-25 15:50:24', 0, 0, 0, NULL, '手机对于拍照党来说，已经不仅仅是通讯工具那么简单了。因为有时TA还充当着“单反”的角色，来不断地带给那些喜欢拍照的人惊喜。所以，在这里准备一波高颜值的双摄手机来给大家。让TA们灵敏捕捉影像的能力，为你展现出轮廓更加分明、且画质更加细腻的美照。', 1, '<ul>
                                                                        <li class="info-item ">
                                <div class="box-item-fl "><label class="name">CPU：</label><a class="product-link" href="/cell_phone_index/subcate57_list_s7957_1.html">海思 麒麟 980</a><em class="line"></em><span class="text-hui"></span>
                                    <span class="text-r">行业最高：<font>高通骁龙855+</font></span>
                                </div>
                            </li>
                                                                                                <li class="info-item t-xiangsu-hd">
                                <div class="box-item-fl "><label class="name">后置：</label><span class="product-link" title="4000万像素+1600万像素+800万像素">4000万像素+1600万像素+800万像素</span><em class="line"></em><span class="text-hui"><i class="bg-icon"></i>高清级像素</span></div>
                                <div class="box-item-fr">
                                    <div class="box-bar"><span class="progress-bar" style="width:91.64%"></span><i class="text">高于91.64%手机像素</i></div>
                                    <span class="text-r">行业最高：<font>6400万</font></span>
                                </div>
                            </li>
                                                                                                <li class="info-item t-xiangsu-hd">
                                <div class="box-item-fl "><label class="name">前置：</label><span class="product-link" title="3200万像素">3200万像素</span><em class="line"></em><span class="text-hui"><i class="bg-icon"></i>高清级像素</span></div>
                                <div class="box-item-fr">
                                    <div class="box-bar"><span class="progress-bar" style="width:99.86%"></span><i class="text">高于99.86%手机像素</i></div>
                                    <span class="text-r">行业最高：<font>4800万</font></span>
                                </div>
                            </li>
                                                                                                <li class="info-item t-ramrongliang-liuchang">
                                <div class="box-item-fl "><label class="name">内存：</label><a class="product-link" href="/cell_phone_index/subcate57_list_s7318_1.html">8GB</a><em class="line"></em><span class="text-hui"><i class="bg-icon"></i>游戏运行良好</span></div>
                                <div class="box-item-fr">
                                    <div class="box-bar"><span class="progress-bar" style="width:98.35%"></span><i class="text">大于98.35%手机内存</i></div>
                                    <span class="text-r">行业最高：<font>12G</font></span>
                                </div>
                            </li>
                                                                                                <li class="info-item t-dianchirongliang-yiban">
                                <div class="box-item-fl "><label class="name">电池：</label><span class="product-link" title="3650mAh">3650mAh</span><em class="line"></em><span class="text-hui"><i class="bg-icon"></i>大电池</span></div>
                                <div class="box-item-fr">
                                    <div class="box-bar"><span class="progress-bar" style="width:63.36%"></span><i class="text">大于63.36%手机续航</i></div>
                                    <span class="text-r">行业最高：<font>10000mAh</font></span>
                                </div>
                            </li>
                                                                                                <li class="info-item t-shuangshou">
                                <div class="box-item-fl "><label class="name">屏幕：</label><span class="product-link" title="6.1英寸">6.1英寸</span><em class="line"></em><span class="text-hui"><i class="bg-icon"></i>需双手打字</span></div>
                                <div class="box-item-fr">
                                    <div class="box-bar"><span class="progress-bar" style="width:68.34%"></span><i class="text">大于68.34%手机屏幕尺寸</i></div>
                                    <span class="text-r">行业最高：<font>8英寸</font></span>
                                </div>
                            </li>
                                                                                                <li class="info-item t-fenbianlv-hd">
                                <div class="box-item-fl "><label class="name">分辨率：</label><a class="product-link" href="/cell_phone_index/subcate57_list_p33474_1.html">2340x1080像素</a><em class="line"></em><span class="text-hui"><i class="bg-icon"></i>1080P高清</span></div>
                                <div class="box-item-fr">
                                    <div class="box-bar"><span class="progress-bar" style="width:88.76%"></span><i class="text">大于88.76%手机分辨率</i></div>
                                    <span class="text-r">行业最高：<font>1440*2560</font></span>
                                </div>
                            </li>
                                                            </ul>', 0, 0);
INSERT INTO "public"."mall_subject" VALUES (6, 1, '交通拥挤有妙招，电动车小巧穿行无障碍', 'https://img11.360buyimg.com/mobilecms/s1500x600_jfs/t14224/229/529700229/74868/a1cc7364/5a314f85N5bfd22c7.jpg!q70.jpg', 0, 0, '2019-07-30 11:00:03', 0, 0, 0, NULL, '''随着人们消费水平的提高，公路上的小车是越来越多了，导致每到上下班高峰期的时候，大路的车辆堵了一环又一环，而且你根本不知道它到底会塞多久，所以我们需要变通一下，不妨骑上电动车来个绿色出行，它够小巧玲珑，即使交通再怎么拥挤，也总有它可以通过的地方。', 1, 'test content', 0, 0);
INSERT INTO "public"."mall_subject" VALUES (7, 1, '无酒不成席，甘香白酒开怀助兴', 'https://img12.360buyimg.com/mobilecms/s1500x600_jfs/t1/881/4/12265/114011/5bd1446fEc71114bf/68925bfb4a2adc44.jpg!q70.jpg', 0, 0, '2019-07-30 11:00:03', 0, 0, 0, NULL, '白酒是由各种优质的高粱，小麦，大米等谷物为原料，经过传统工艺的长时间酿造，酒液在这样的环境中慢慢发酵，最终变成清香浓郁的白酒，被摆上人们的餐桌，供人畅饮，是一种受到大众喜爱的绝佳饮品。', 1, 'test content', 0, 0);

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS "public"."member";
CREATE TABLE "public"."member" (
  "id" int4 NOT NULL DEFAULT nextval('member_id_seq'::regclass),
  "username" varchar COLLATE "pg_catalog"."default" NOT NULL,
  "password" text COLLATE "pg_catalog"."default" NOT NULL,
  "gender" int2 NOT NULL,
  "phone" varchar(20) COLLATE "pg_catalog"."default",
  "status" int2 NOT NULL DEFAULT 0,
  "create_time" timestamp(6) NOT NULL,
  "icon" text COLLATE "pg_catalog"."default",
  "country" varchar(100) COLLATE "pg_catalog"."default",
  "province" varchar(100) COLLATE "pg_catalog"."default",
  "city" varchar(100) COLLATE "pg_catalog"."default",
  "job" varchar(50) COLLATE "pg_catalog"."default",
  "personalized_signature" text COLLATE "pg_catalog"."default",
  "birthday" timestamp(6),
  "member_level_id" int4,
  "nickname" text COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."member"."username" IS '用户名';
COMMENT ON COLUMN "public"."member"."password" IS '用户密码';
COMMENT ON COLUMN "public"."member"."gender" IS '性别(0男1女)';
COMMENT ON COLUMN "public"."member"."phone" IS '会员手机号码';
COMMENT ON COLUMN "public"."member"."status" IS '会员账号状态(0未激活1正常2异常)';
COMMENT ON COLUMN "public"."member"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."member"."icon" IS '头像地址';
COMMENT ON COLUMN "public"."member"."country" IS '国家';
COMMENT ON COLUMN "public"."member"."province" IS '省份';
COMMENT ON COLUMN "public"."member"."city" IS '城市';
COMMENT ON COLUMN "public"."member"."job" IS '工作';
COMMENT ON COLUMN "public"."member"."personalized_signature" IS '个性签名';
COMMENT ON COLUMN "public"."member"."birthday" IS '生日信息';
COMMENT ON COLUMN "public"."member"."member_level_id" IS '会员等级id';
COMMENT ON TABLE "public"."member" IS '商城用户表';

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO "public"."member" VALUES (3, 'yangkui', '7961d23a73e658d51536cb275458e2e7281fb0d4', 0, '13765591014', 0, '2019-07-21 17:26:17', NULL, NULL, NULL, NULL, NULL, NULL, '2019-07-21 17:25:26', NULL, NULL);
INSERT INTO "public"."member" VALUES (6, 'hahahah', '7961d23a73e658d51536cb275458e2e7281fb0d4', 0, NULL, 0, '2019-07-22 11:19:53', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."member" VALUES (7, 'hahahahjjjs', '7961d23a73e658d51536cb275458e2e7281fb0d4', 0, NULL, 0, '2019-07-23 10:31:49', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."member" VALUES (8, 'hahahahjjjsj', '7961d23a73e658d51536cb275458e2e7281fb0d4', 0, NULL, 0, '2019-07-23 10:34:09', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for preference_area
-- ----------------------------
DROP TABLE IF EXISTS "public"."preference_area";
CREATE TABLE "public"."preference_area" (
  "id" int4 NOT NULL DEFAULT nextval('preference_area_id_seq'::regclass),
  "title" text COLLATE "pg_catalog"."default" NOT NULL,
  "sub_title" text COLLATE "pg_catalog"."default",
  "pic" text COLLATE "pg_catalog"."default",
  "sort" int4 NOT NULL DEFAULT 0,
  "show_status" int2 NOT NULL DEFAULT 1,
  "version" int8 NOT NULL DEFAULT 0
)
;
COMMENT ON TABLE "public"."preference_area" IS '优选专区';

-- ----------------------------
-- Records of preference_area
-- ----------------------------
INSERT INTO "public"."preference_area" VALUES (1, '让音质更出众', '音质不打折 完美现场感', NULL, 0, 1, 0);
INSERT INTO "public"."preference_area" VALUES (2, '让音质更出众2', '音质不打折 完美现场感2', NULL, 0, 1, 0);

-- ----------------------------
-- Table structure for preference_area_product_relation
-- ----------------------------
DROP TABLE IF EXISTS "public"."preference_area_product_relation";
CREATE TABLE "public"."preference_area_product_relation" (
  "id" int4 NOT NULL DEFAULT nextval('prefrence_area_product_relation_id_seq'::regclass),
  "preference_area_id" int4 NOT NULL,
  "product_id" int4 NOT NULL
)
;
COMMENT ON COLUMN "public"."preference_area_product_relation"."preference_area_id" IS '优选id';
COMMENT ON COLUMN "public"."preference_area_product_relation"."product_id" IS '商品id';
COMMENT ON TABLE "public"."preference_area_product_relation" IS '优选和商品关联表';

-- ----------------------------
-- Records of preference_area_product_relation
-- ----------------------------
INSERT INTO "public"."preference_area_product_relation" VALUES (1, 1, 3);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS "public"."product";
CREATE TABLE "public"."product" (
  "id" int4 NOT NULL DEFAULT nextval('product_id_seq'::regclass),
  "brand_id" int4 NOT NULL,
  "product_category_id" int4 NOT NULL,
  "title" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "pic" text COLLATE "pg_catalog"."default",
  "product_sn" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "delete_status" int2 NOT NULL,
  "publish_status" int2 NOT NULL,
  "new_status" int2 NOT NULL,
  "recommend_status" int2 NOT NULL,
  "verify_status" int2 NOT NULL,
  "price" int8 NOT NULL DEFAULT 0,
  "sale" int8 DEFAULT 0,
  "brand_name" varbit(255),
  "product_category_name" varchar(255) COLLATE "pg_catalog"."default",
  "subtitle" text COLLATE "pg_catalog"."default",
  "sort" int8,
  "description" text COLLATE "pg_catalog"."default",
  "album_pics" text COLLATE "pg_catalog"."default",
  "services" varbit(100),
  "gift_growth" int8,
  "gift_point" int8 NOT NULL DEFAULT 0,
  "use_point_limit" int8,
  "original_price" int8
)
;
COMMENT ON COLUMN "public"."product"."brand_id" IS '商品品牌id';
COMMENT ON COLUMN "public"."product"."product_category_id" IS '商品分类id';
COMMENT ON COLUMN "public"."product"."title" IS '商品标题';
COMMENT ON COLUMN "public"."product"."pic" IS '商品主图';
COMMENT ON COLUMN "public"."product"."product_sn" IS '商品编号';
COMMENT ON COLUMN "public"."product"."delete_status" IS '删除状态0未删除1已删除';
COMMENT ON COLUMN "public"."product"."publish_status" IS '上线状态0未上线1上线';
COMMENT ON COLUMN "public"."product"."new_status" IS '是否新品0不是1是';
COMMENT ON COLUMN "public"."product"."recommend_status" IS '推荐状态0不推荐1推荐';
COMMENT ON COLUMN "public"."product"."verify_status" IS '审核状态0未审核1通过审核';
COMMENT ON COLUMN "public"."product"."price" IS '商品价格(统一以分为单位)';
COMMENT ON COLUMN "public"."product"."sale" IS '销量';
COMMENT ON COLUMN "public"."product"."brand_name" IS '品牌名称';
COMMENT ON COLUMN "public"."product"."product_category_name" IS '产品分类名称';
COMMENT ON COLUMN "public"."product"."subtitle" IS '子标题';
COMMENT ON COLUMN "public"."product"."sort" IS '排序';
COMMENT ON COLUMN "public"."product"."description" IS '商品描述';
COMMENT ON COLUMN "public"."product"."album_pics" IS '商品画廊';
COMMENT ON COLUMN "public"."product"."services" IS '产品服务(通过逗号分割)';
COMMENT ON COLUMN "public"."product"."gift_growth" IS '赠送成长值';
COMMENT ON COLUMN "public"."product"."gift_point" IS '赠送积分';
COMMENT ON COLUMN "public"."product"."use_point_limit" IS '限制积分使用数目';
COMMENT ON COLUMN "public"."product"."original_price" IS '原始价格';

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO "public"."product" VALUES (9, 1, 1, '银色星芒刺绣网纱底裤', 'https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1430933087,1031740943&fm=26&gp=0.jpg', 'No86577', 0, 0, 1, 1, 0, 10000, 50, NULL, NULL, '银色星芒刺绣网纱底裤', NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO "public"."product" VALUES (3, 1, 1, 'AMD 锐龙R7 2700X电脑主机', 'https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=4274057707,4192867063&fm=26&gp=0.jpg', 'No.123456', 0, 1, 0, 0, 0, 500000, 100, NULL, NULL, '银色星芒刺绣网纱底裤', NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO "public"."product" VALUES (11, 1, 1, '银色星芒刺绣网纱底裤3', 'https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1430933087,1031740943&fm=26&gp=0.jpg', 'No86578', 0, 1, 1, 1, 0, 10000, 0, NULL, NULL, '银色星芒刺绣网纱底裤', NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO "public"."product" VALUES (10, 1, 1, '银色星芒刺绣网纱底裤2', 'https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1430933087,1031740943&fm=26&gp=0.jpg', 'No86578', 0, 0, 0, 1, 0, 10000, 0, NULL, NULL, '银色星芒刺绣网纱底裤', NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO "public"."product" VALUES (12, 1, 1, '银色星芒刺绣网纱底裤3', 'https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1430933087,1031740943&fm=26&gp=0.jpg', 'No86578', 0, 1, 1, 1, 0, 10000, 0, NULL, '', '银色星芒刺绣网纱底裤', NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO "public"."product" VALUES (13, 1, 1, '银色星芒刺绣网纱底裤3', 'https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1430933087,1031740943&fm=26&gp=0.jpg', 'No86578', 0, 1, 1, 1, 0, 10000, 0, NULL, '', '银色星芒刺绣网纱底裤', NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO "public"."product" VALUES (14, 1, 1, '银色星芒刺绣网纱底裤3', 'https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1430933087,1031740943&fm=26&gp=0.jpg', 'No86578', 0, 1, 1, 1, 0, 10000, 0, NULL, '', '银色星芒刺绣网纱底裤', NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL);

-- ----------------------------
-- Table structure for product_attribute
-- ----------------------------
DROP TABLE IF EXISTS "public"."product_attribute";
CREATE TABLE "public"."product_attribute" (
  "id" int4 NOT NULL DEFAULT nextval('product_attribute_id_seq'::regclass),
  "product_attribute_category_id" int4 NOT NULL,
  "name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "select_type" int2 NOT NULL,
  "input_type" int2 NOT NULL,
  "input_list" varchar(255) COLLATE "pg_catalog"."default",
  "sort" int4,
  "filter_type" int2,
  "search_type" int2,
  "related_status" int2,
  "hand_add_status" int2,
  "type" int2 NOT NULL
)
;

-- ----------------------------
-- Table structure for product_attribute_category
-- ----------------------------
DROP TABLE IF EXISTS "public"."product_attribute_category";
CREATE TABLE "public"."product_attribute_category" (
  "id" int4 NOT NULL DEFAULT nextval('product_attribute_category_id_seq'::regclass),
  "name" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "attribute_count" int4 NOT NULL,
  "param_count" int4 NOT NULL,
  "version" int8
)
;

-- ----------------------------
-- Table structure for product_attribute_value
-- ----------------------------
DROP TABLE IF EXISTS "public"."product_attribute_value";
CREATE TABLE "public"."product_attribute_value" (
  "id" int4 NOT NULL DEFAULT nextval('product_attribute_value_id_seq'::regclass),
  "product_id" int4 NOT NULL,
  "product_attribute_id" int4 NOT NULL,
  "value" varchar(255) COLLATE "pg_catalog"."default" NOT NULL
)
;

-- ----------------------------
-- Table structure for product_category
-- ----------------------------
DROP TABLE IF EXISTS "public"."product_category";
CREATE TABLE "public"."product_category" (
  "id" int4 NOT NULL DEFAULT nextval('product_category_id_seq'::regclass),
  "parent_id" int4 NOT NULL,
  "name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "product_count" int8 NOT NULL,
  "show_status" int2 NOT NULL,
  "icon" text COLLATE "pg_catalog"."default",
  "description" varchar(255) COLLATE "pg_catalog"."default",
  "sort" int4 DEFAULT 0
)
;
COMMENT ON TABLE "public"."product_category" IS '商品分类表';

-- ----------------------------
-- Records of product_category
-- ----------------------------
INSERT INTO "public"."product_category" VALUES (1, 0, '手机', 100, 1, NULL, NULL, 100);
INSERT INTO "public"."product_category" VALUES (3, 0, '数码家电', 200, 1, NULL, NULL, 0);
INSERT INTO "public"."product_category" VALUES (5, 1, '华为', 200, 1, NULL, NULL, 0);
INSERT INTO "public"."product_category" VALUES (6, 1, '小米', 200, 1, NULL, NULL, 0);
INSERT INTO "public"."product_category" VALUES (8, 3, '康佳', 200, 1, NULL, NULL, 0);

-- ----------------------------
-- Table structure for product_category_attribute_relation
-- ----------------------------
DROP TABLE IF EXISTS "public"."product_category_attribute_relation";
CREATE TABLE "public"."product_category_attribute_relation" (
  "id" int4 NOT NULL DEFAULT nextval('product_category_attribute_relation_id_seq'::regclass),
  "category_id" int4 NOT NULL,
  "attribute_id" int4 NOT NULL
)
;

-- ----------------------------
-- Table structure for product_commnet
-- ----------------------------
DROP TABLE IF EXISTS "public"."product_commnet";
CREATE TABLE "public"."product_commnet" (
  "id" int4 NOT NULL DEFAULT nextval('product_commnet_id_seq'::regclass),
  "product_id" int4 NOT NULL,
  "member_nick_name" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "product_name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "star" int4 NOT NULL,
  "member_ip" varchar COLLATE "pg_catalog"."default" NOT NULL,
  "show_status" int2 NOT NULL DEFAULT 1,
  "product_attribute" varchar COLLATE "pg_catalog"."default",
  "content" text COLLATE "pg_catalog"."default",
  "pics" text COLLATE "pg_catalog"."default",
  "member_icon" text COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."product_commnet"."star" IS '评价星数：0->5';
COMMENT ON COLUMN "public"."product_commnet"."show_status" IS '显示状态(0->不显示 1->显示)';
COMMENT ON COLUMN "public"."product_commnet"."pics" IS '评价时上传的商品图片 以逗号分隔 多张图片';
COMMENT ON TABLE "public"."product_commnet" IS '商品评价表';

-- ----------------------------
-- Table structure for product_subject_relation
-- ----------------------------
DROP TABLE IF EXISTS "public"."product_subject_relation";
CREATE TABLE "public"."product_subject_relation" (
  "id" int4 NOT NULL DEFAULT nextval('product_subject_relation_id_seq'::regclass),
  "product_id" int4 NOT NULL,
  "subject_id" int4 NOT NULL
)
;
COMMENT ON TABLE "public"."product_subject_relation" IS '产品专题关联表';

-- ----------------------------
-- Table structure for promotion
-- ----------------------------
DROP TABLE IF EXISTS "public"."promotion";
CREATE TABLE "public"."promotion" (
  "id" int4 NOT NULL DEFAULT nextval('promotion_id_seq'::regclass),
  "promotion_start_time" timestamp(6) NOT NULL,
  "promotion_end_time" timestamp(6) NOT NULL,
  "promotion_per_limit" int4 NOT NULL,
  "promotion_price" int8 NOT NULL,
  "version" int8 NOT NULL DEFAULT 0
)
;
COMMENT ON COLUMN "public"."promotion"."promotion_start_time" IS '促销开始时间';
COMMENT ON COLUMN "public"."promotion"."promotion_end_time" IS '促销开始时间';
COMMENT ON COLUMN "public"."promotion"."promotion_per_limit" IS '促销数量';
COMMENT ON COLUMN "public"."promotion"."promotion_price" IS '促销价格';
COMMENT ON TABLE "public"."promotion" IS '商品促销列表';

-- ----------------------------
-- Table structure for receive_address
-- ----------------------------
DROP TABLE IF EXISTS "public"."receive_address";
CREATE TABLE "public"."receive_address" (
  "id" int4 NOT NULL DEFAULT nextval('address_id_seq'::regclass),
  "name" varchar COLLATE "pg_catalog"."default" NOT NULL,
  "phone" varchar COLLATE "pg_catalog"."default" NOT NULL,
  "default_status" int2 NOT NULL,
  "province" varchar COLLATE "pg_catalog"."default" NOT NULL,
  "city" varchar COLLATE "pg_catalog"."default" NOT NULL,
  "region" varchar COLLATE "pg_catalog"."default",
  "detail_address" varchar COLLATE "pg_catalog"."default",
  "version" varchar COLLATE "pg_catalog"."default" NOT NULL DEFAULT 0,
  "member_id" int4 NOT NULL
)
;
COMMENT ON COLUMN "public"."receive_address"."name" IS '收货人姓名';
COMMENT ON COLUMN "public"."receive_address"."phone" IS '收货人手机';
COMMENT ON COLUMN "public"."receive_address"."default_status" IS '是否默认0->不是 1->是';
COMMENT ON COLUMN "public"."receive_address"."province" IS '省';
COMMENT ON COLUMN "public"."receive_address"."city" IS '市';
COMMENT ON COLUMN "public"."receive_address"."region" IS '区';
COMMENT ON COLUMN "public"."receive_address"."detail_address" IS '详细地址';
COMMENT ON COLUMN "public"."receive_address"."version" IS 'CAS版本号';
COMMENT ON COLUMN "public"."receive_address"."member_id" IS '会员id';
COMMENT ON TABLE "public"."receive_address" IS '收货地址';

-- ----------------------------
-- Records of receive_address
-- ----------------------------
INSERT INTO "public"."receive_address" VALUES (7, 'yangkui', '13765591014', 1, '贵州省', '凯里市', NULL, 'fjakfak', '0', 3);
INSERT INTO "public"."receive_address" VALUES (19, 'ceshi ', '13765591014', 0, '贵州省', '凯里市', NULL, 'fdfafafa', '0', 3);
INSERT INTO "public"."receive_address" VALUES (9, 'yangkui', '13765591014', 0, '贵州省', '凯里市', NULL, 'fjakfak', '0', 3);

-- ----------------------------
-- Table structure for role_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS "public"."role_permission_relation";
CREATE TABLE "public"."role_permission_relation" (
  "id" int4 NOT NULL DEFAULT nextval('role_permission_relation_id_seq'::regclass),
  "role_id" int4 NOT NULL,
  "permission_id" int4 NOT NULL
)
;
COMMENT ON TABLE "public"."role_permission_relation" IS '角色权限关联表';

-- ----------------------------
-- Records of role_permission_relation
-- ----------------------------
INSERT INTO "public"."role_permission_relation" VALUES (4, 1, 1);
INSERT INTO "public"."role_permission_relation" VALUES (5, 1, 2);

-- ----------------------------
-- Table structure for sku_stock
-- ----------------------------
DROP TABLE IF EXISTS "public"."sku_stock";
CREATE TABLE "public"."sku_stock" (
  "id" int4 NOT NULL DEFAULT nextval('sku_stock_id_seq'::regclass),
  "product_id" int4 NOT NULL,
  "sku_code" varchar COLLATE "pg_catalog"."default" NOT NULL,
  "price" int8 NOT NULL,
  "stock" int8 NOT NULL,
  "low_stock" int8 NOT NULL,
  "sp1" varchar COLLATE "pg_catalog"."default",
  "sp2" varchar COLLATE "pg_catalog"."default",
  "sp3" varchar COLLATE "pg_catalog"."default",
  "pic" text COLLATE "pg_catalog"."default",
  "sale" int8 NOT NULL,
  "promotion_price" int8,
  "lock_stock" int2
)
;
COMMENT ON COLUMN "public"."sku_stock"."product_id" IS '商品id';
COMMENT ON COLUMN "public"."sku_stock"."sku_code" IS '库存编号';
COMMENT ON COLUMN "public"."sku_stock"."price" IS '商品价格';
COMMENT ON COLUMN "public"."sku_stock"."stock" IS '库存数量';
COMMENT ON COLUMN "public"."sku_stock"."low_stock" IS '库存警报';
COMMENT ON COLUMN "public"."sku_stock"."sp1" IS '属性1';
COMMENT ON COLUMN "public"."sku_stock"."sp2" IS '属性2';
COMMENT ON COLUMN "public"."sku_stock"."sp3" IS '属性3';
COMMENT ON COLUMN "public"."sku_stock"."pic" IS '图片';
COMMENT ON COLUMN "public"."sku_stock"."sale" IS '销量';
COMMENT ON COLUMN "public"."sku_stock"."promotion_price" IS '促销价格';
COMMENT ON COLUMN "public"."sku_stock"."lock_stock" IS '低库存警报';
COMMENT ON TABLE "public"."sku_stock" IS 'sku库存';

-- ----------------------------
-- Table structure for subject_category
-- ----------------------------
DROP TABLE IF EXISTS "public"."subject_category";
CREATE TABLE "public"."subject_category" (
  "id" int4 NOT NULL DEFAULT nextval('subject_category_id_seq'::regclass),
  "name" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "icon" text COLLATE "pg_catalog"."default",
  "subject_count" int4 NOT NULL DEFAULT 0,
  "show_status" int2 NOT NULL DEFAULT 0,
  "sort" int4 NOT NULL DEFAULT 0,
  "version" int8 NOT NULL DEFAULT 0
)
;
COMMENT ON COLUMN "public"."subject_category"."name" IS '专题分类名称';
COMMENT ON COLUMN "public"."subject_category"."icon" IS '分类图标';
COMMENT ON COLUMN "public"."subject_category"."subject_count" IS '专题数目';
COMMENT ON COLUMN "public"."subject_category"."show_status" IS '显示状态 0->不显示 1->显示';
COMMENT ON COLUMN "public"."subject_category"."sort" IS '专题排序';
COMMENT ON TABLE "public"."subject_category" IS '专题分类';

-- ----------------------------
-- Records of subject_category
-- ----------------------------
INSERT INTO "public"."subject_category" VALUES (1, '精选专题', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/subject_cate_jingxuan.png', 0, 1, 0, 0);

-- ----------------------------
-- Table structure for subject_comment
-- ----------------------------
DROP TABLE IF EXISTS "public"."subject_comment";
CREATE TABLE "public"."subject_comment" (
  "id" int4 NOT NULL DEFAULT nextval('subject_comment_id_seq'::regclass),
  "subject_id" int4 NOT NULL,
  "member_nick_name" text COLLATE "pg_catalog"."default" NOT NULL,
  "member_icon" text COLLATE "pg_catalog"."default",
  "content" text COLLATE "pg_catalog"."default",
  "create_time" timestamp(6) NOT NULL,
  "show_status" int2 NOT NULL DEFAULT 1
)
;
COMMENT ON COLUMN "public"."subject_comment"."subject_id" IS '专题id';
COMMENT ON COLUMN "public"."subject_comment"."member_nick_name" IS '用户昵称';
COMMENT ON COLUMN "public"."subject_comment"."member_icon" IS '用户头像';
COMMENT ON COLUMN "public"."subject_comment"."create_time" IS '评论创建时间';
COMMENT ON COLUMN "public"."subject_comment"."show_status" IS '显示状态 0->不显示 1->显示';
COMMENT ON TABLE "public"."subject_comment" IS '专题评论表';

-- ----------------------------
-- Table structure for today_recommend
-- ----------------------------
DROP TABLE IF EXISTS "public"."today_recommend";
CREATE TABLE "public"."today_recommend" (
  "id" int4 NOT NULL DEFAULT nextval('today_recommend_id_seq'::regclass),
  "product_id" int4 NOT NULL,
  "recommend_date" date NOT NULL,
  "create_time" timestamp(6) NOT NULL,
  "show_status" int2 NOT NULL DEFAULT 0
)
;
COMMENT ON COLUMN "public"."today_recommend"."product_id" IS '产品id';
COMMENT ON COLUMN "public"."today_recommend"."recommend_date" IS '推荐日期';
COMMENT ON COLUMN "public"."today_recommend"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."today_recommend"."show_status" IS '显示状态 0->不显示 1->显示';
COMMENT ON TABLE "public"."today_recommend" IS '今日推荐表产品';

-- ----------------------------
-- Records of today_recommend
-- ----------------------------
INSERT INTO "public"."today_recommend" VALUES (2, 3, '2019-07-30', '2019-07-30 21:05:35', 1);
INSERT INTO "public"."today_recommend" VALUES (4, 10, '2019-07-30', '2019-07-30 21:59:45', 1);
INSERT INTO "public"."today_recommend" VALUES (3, 9, '2019-07-30', '2019-07-30 21:06:20', 1);
INSERT INTO "public"."today_recommend" VALUES (6, 11, '2019-07-30', '2019-07-30 22:00:12', 1);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."address_id_seq"
OWNED BY "public"."receive_address"."id";
SELECT setval('"public"."address_id_seq"', 21, true);
ALTER SEQUENCE "public"."admin_role_relation_id_seq"
OWNED BY "public"."admin_role_relation"."id";
SELECT setval('"public"."admin_role_relation_id_seq"', 4, true);
ALTER SEQUENCE "public"."cart_item_id_seq"
OWNED BY "public"."cart_item"."id";
SELECT setval('"public"."cart_item_id_seq"', 2, true);
ALTER SEQUENCE "public"."coupon_history_id_seq"
OWNED BY "public"."coupon_history"."id";
SELECT setval('"public"."coupon_history_id_seq"', 6, true);
ALTER SEQUENCE "public"."coupon_id_seq"
OWNED BY "public"."coupon"."id";
SELECT setval('"public"."coupon_id_seq"', 5, true);
ALTER SEQUENCE "public"."flash_promotion_product_relation_id_seq"
OWNED BY "public"."flash_promotion_product_relation"."id";
SELECT setval('"public"."flash_promotion_product_relation_id_seq"', 3, true);
ALTER SEQUENCE "public"."flash_promotion_session_id_seq"
OWNED BY "public"."flash_promotion_session"."id";
SELECT setval('"public"."flash_promotion_session_id_seq"', 2, true);
ALTER SEQUENCE "public"."home_advertise_id_seq"
OWNED BY "public"."home_advertise"."id";
SELECT setval('"public"."home_advertise_id_seq"', 5, true);
ALTER SEQUENCE "public"."home_recommend_subject_id_seq"
OWNED BY "public"."home_recommend_subject"."id";
SELECT setval('"public"."home_recommend_subject_id_seq"', 3, true);
ALTER SEQUENCE "public"."mall_admin_id_seq"
OWNED BY "public"."mall_admin"."id";
SELECT setval('"public"."mall_admin_id_seq"', 2, true);
ALTER SEQUENCE "public"."mall_admin_login_log_id_seq"
OWNED BY "public"."mall_admin_login_log"."id";
SELECT setval('"public"."mall_admin_login_log_id_seq"', 117, true);
ALTER SEQUENCE "public"."mall_brand_id_seq"
OWNED BY "public"."mall_brand"."id";
SELECT setval('"public"."mall_brand_id_seq"', 2, false);
ALTER SEQUENCE "public"."mall_order_id_seq"
OWNED BY "public"."mall_order"."id";
SELECT setval('"public"."mall_order_id_seq"', 2, false);
ALTER SEQUENCE "public"."mall_order_item_id_seq"
OWNED BY "public"."mall_order_item"."id";
SELECT setval('"public"."mall_order_item_id_seq"', 2, false);
ALTER SEQUENCE "public"."mall_permission_id_seq"
OWNED BY "public"."mall_permission"."id";
SELECT setval('"public"."mall_permission_id_seq"', 3, true);
ALTER SEQUENCE "public"."mall_role_id_seq"
OWNED BY "public"."mall_role"."id";
SELECT setval('"public"."mall_role_id_seq"', 2, true);
ALTER SEQUENCE "public"."mall_subject_id_seq"
OWNED BY "public"."mall_subject"."id";
SELECT setval('"public"."mall_subject_id_seq"', 7, true);
ALTER SEQUENCE "public"."member_id_seq"
OWNED BY "public"."member"."id";
SELECT setval('"public"."member_id_seq"', 9, true);
ALTER SEQUENCE "public"."preference_area_id_seq"
OWNED BY "public"."preference_area"."id";
SELECT setval('"public"."preference_area_id_seq"', 3, true);
ALTER SEQUENCE "public"."prefrence_area_product_relation_id_seq"
OWNED BY "public"."preference_area_product_relation"."id";
SELECT setval('"public"."prefrence_area_product_relation_id_seq"', 2, true);
ALTER SEQUENCE "public"."product_attribute_category_id_seq"
OWNED BY "public"."product_attribute_category"."id";
SELECT setval('"public"."product_attribute_category_id_seq"', 2, false);
ALTER SEQUENCE "public"."product_attribute_id_seq"
OWNED BY "public"."product_attribute"."id";
SELECT setval('"public"."product_attribute_id_seq"', 2, false);
ALTER SEQUENCE "public"."product_attribute_value_id_seq"
OWNED BY "public"."product_attribute_value"."id";
SELECT setval('"public"."product_attribute_value_id_seq"', 2, false);
ALTER SEQUENCE "public"."product_category_attribute_relation_id_seq"
OWNED BY "public"."product_category_attribute_relation"."id";
SELECT setval('"public"."product_category_attribute_relation_id_seq"', 2, false);
ALTER SEQUENCE "public"."product_category_id_seq"
OWNED BY "public"."product_category"."id";
SELECT setval('"public"."product_category_id_seq"', 9, true);
ALTER SEQUENCE "public"."product_commnet_id_seq"
OWNED BY "public"."product_commnet"."id";
SELECT setval('"public"."product_commnet_id_seq"', 2, false);
ALTER SEQUENCE "public"."product_id_seq"
OWNED BY "public"."product"."id";
SELECT setval('"public"."product_id_seq"', 12, true);
ALTER SEQUENCE "public"."product_subject_relation_id_seq"
OWNED BY "public"."product_subject_relation"."id";
SELECT setval('"public"."product_subject_relation_id_seq"', 2, false);
ALTER SEQUENCE "public"."promotion_id_seq"
OWNED BY "public"."promotion"."id";
SELECT setval('"public"."promotion_id_seq"', 2, false);
ALTER SEQUENCE "public"."role_permission_relation_id_seq"
OWNED BY "public"."role_permission_relation"."id";
SELECT setval('"public"."role_permission_relation_id_seq"', 6, true);
ALTER SEQUENCE "public"."sku_stock_id_seq"
OWNED BY "public"."sku_stock"."id";
SELECT setval('"public"."sku_stock_id_seq"', 2, false);
ALTER SEQUENCE "public"."subject_category_id_seq"
OWNED BY "public"."subject_category"."id";
SELECT setval('"public"."subject_category_id_seq"', 2, true);
ALTER SEQUENCE "public"."subject_comment_id_seq"
OWNED BY "public"."subject_comment"."id";
SELECT setval('"public"."subject_comment_id_seq"', 2, false);
ALTER SEQUENCE "public"."today_recommend_id_seq"
OWNED BY "public"."today_recommend"."id";
SELECT setval('"public"."today_recommend_id_seq"', 7, true);

-- ----------------------------
-- Primary Key structure for table admin_role_relation
-- ----------------------------
ALTER TABLE "public"."admin_role_relation" ADD CONSTRAINT "admin_role_relation_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table cart_item
-- ----------------------------
ALTER TABLE "public"."cart_item" ADD CONSTRAINT "cart_item_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table coupon
-- ----------------------------
ALTER TABLE "public"."coupon" ADD CONSTRAINT "coupon_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table coupon_history
-- ----------------------------
ALTER TABLE "public"."coupon_history" ADD CONSTRAINT "coupon_history_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table flash_promotion_product_relation
-- ----------------------------
ALTER TABLE "public"."flash_promotion_product_relation" ADD CONSTRAINT "flash_promotion_product_relation_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table flash_promotion_session
-- ----------------------------
ALTER TABLE "public"."flash_promotion_session" ADD CONSTRAINT "flash_promotion_session_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table home_advertise
-- ----------------------------
ALTER TABLE "public"."home_advertise" ADD CONSTRAINT "home_advertise_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table home_recommend_subject
-- ----------------------------
ALTER TABLE "public"."home_recommend_subject" ADD CONSTRAINT "home_recommend_subject_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table mall_admin
-- ----------------------------
ALTER TABLE "public"."mall_admin" ADD CONSTRAINT "mall_admin_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table mall_admin_login_log
-- ----------------------------
ALTER TABLE "public"."mall_admin_login_log" ADD CONSTRAINT "mall_admin_login_log_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table mall_brand
-- ----------------------------
ALTER TABLE "public"."mall_brand" ADD CONSTRAINT "mall_brand_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table mall_order
-- ----------------------------
ALTER TABLE "public"."mall_order" ADD CONSTRAINT "mall_order_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table mall_order_item
-- ----------------------------
ALTER TABLE "public"."mall_order_item" ADD CONSTRAINT "mall_order_item_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table mall_permission
-- ----------------------------
ALTER TABLE "public"."mall_permission" ADD CONSTRAINT "mall_permission_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table mall_role
-- ----------------------------
ALTER TABLE "public"."mall_role" ADD CONSTRAINT "mall_role_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table mall_subject
-- ----------------------------
ALTER TABLE "public"."mall_subject" ADD CONSTRAINT "mall_subject_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table member
-- ----------------------------
ALTER TABLE "public"."member" ADD CONSTRAINT "member_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table preference_area
-- ----------------------------
ALTER TABLE "public"."preference_area" ADD CONSTRAINT "preference_area_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table preference_area_product_relation
-- ----------------------------
ALTER TABLE "public"."preference_area_product_relation" ADD CONSTRAINT "prefrence_area_product_relation_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table product
-- ----------------------------
ALTER TABLE "public"."product" ADD CONSTRAINT "product_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table product_attribute
-- ----------------------------
ALTER TABLE "public"."product_attribute" ADD CONSTRAINT "product_attribute_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table product_attribute_category
-- ----------------------------
ALTER TABLE "public"."product_attribute_category" ADD CONSTRAINT "product_attribute_category_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table product_attribute_value
-- ----------------------------
ALTER TABLE "public"."product_attribute_value" ADD CONSTRAINT "product_attribute_value_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table product_category
-- ----------------------------
ALTER TABLE "public"."product_category" ADD CONSTRAINT "product_category_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table product_category_attribute_relation
-- ----------------------------
ALTER TABLE "public"."product_category_attribute_relation" ADD CONSTRAINT "product_category_attribute_relation_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table product_commnet
-- ----------------------------
ALTER TABLE "public"."product_commnet" ADD CONSTRAINT "product_commnet_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table product_subject_relation
-- ----------------------------
ALTER TABLE "public"."product_subject_relation" ADD CONSTRAINT "product_subject_relation_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table promotion
-- ----------------------------
ALTER TABLE "public"."promotion" ADD CONSTRAINT "promotion_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table receive_address
-- ----------------------------
ALTER TABLE "public"."receive_address" ADD CONSTRAINT "address_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table role_permission_relation
-- ----------------------------
ALTER TABLE "public"."role_permission_relation" ADD CONSTRAINT "role_permission_relation_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sku_stock
-- ----------------------------
ALTER TABLE "public"."sku_stock" ADD CONSTRAINT "sku_stock_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table subject_category
-- ----------------------------
ALTER TABLE "public"."subject_category" ADD CONSTRAINT "subject_category_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table subject_comment
-- ----------------------------
ALTER TABLE "public"."subject_comment" ADD CONSTRAINT "subject_comment_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table today_recommend
-- ----------------------------
ALTER TABLE "public"."today_recommend" ADD CONSTRAINT "today_recommend_pkey" PRIMARY KEY ("id");
