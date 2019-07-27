/*
 Navicat PostgreSQL Data Transfer

 Source Server         : localhost
 Source Server Type    : PostgreSQL
 Source Server Version : 110004
 Source Host           : localhost:5432
 Source Catalog        : mall
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 110004
 File Encoding         : 65001

 Date: 27/07/2019 15:19:16
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
-- Sequence structure for prefrence_area_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."prefrence_area_id_seq";
CREATE SEQUENCE "public"."prefrence_area_id_seq" 
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
INSERT INTO "public"."mall_subject" VALUES (4, 1, '轮廓分明，双摄手机映现细腻美照', 'https://img10.360buyimg.com/mobilecms/s1500x600_jfs/t26434/217/1381240043/254214/290f9d5b/5bc6c11cN54567a27.jpg!q70.jpg', 0, 0, '2019-07-25 15:50:24', 0, 0, 0, NULL, '手机对于拍照党来说，已经不仅仅是通讯工具那么简单了。因为有时TA还充当着“单反”的角色，来不断地带给那些喜欢拍照的人惊喜。所以，在这里准备一波高颜值的双摄手机来给大家。让TA们灵敏捕捉影像的能力，为你展现出轮廓更加分明、且画质更加细腻的美照。', 1, 'test content', 0, 0);

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
-- Table structure for prefrence_area
-- ----------------------------
DROP TABLE IF EXISTS "public"."prefrence_area";
CREATE TABLE "public"."prefrence_area" (
  "id" int4 NOT NULL DEFAULT nextval('prefrence_area_id_seq'::regclass),
  "name" int4 NOT NULL,
  "sub_title" varbit,
  "pic" text COLLATE "pg_catalog"."default",
  "sort" int4 NOT NULL DEFAULT 0,
  "show_status" int2 NOT NULL DEFAULT 0
)
;
COMMENT ON COLUMN "public"."prefrence_area"."name" IS '优选名称';
COMMENT ON COLUMN "public"."prefrence_area"."sub_title" IS '子标题';
COMMENT ON COLUMN "public"."prefrence_area"."pic" IS '优选图片';
COMMENT ON COLUMN "public"."prefrence_area"."sort" IS '排序';
COMMENT ON COLUMN "public"."prefrence_area"."show_status" IS '显示状态 0->不显示 1->显示';

-- ----------------------------
-- Table structure for prefrence_area_product_relation
-- ----------------------------
DROP TABLE IF EXISTS "public"."prefrence_area_product_relation";
CREATE TABLE "public"."prefrence_area_product_relation" (
  "id" int4 NOT NULL DEFAULT nextval('prefrence_area_product_relation_id_seq'::regclass),
  "prefrence_area_id" int4 NOT NULL,
  "product_id" int4 NOT NULL
)
;
COMMENT ON COLUMN "public"."prefrence_area_product_relation"."prefrence_area_id" IS '优选id';
COMMENT ON COLUMN "public"."prefrence_area_product_relation"."product_id" IS '商品id';
COMMENT ON TABLE "public"."prefrence_area_product_relation" IS '优选和商品关联表';

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
  "sale" int8,
  "brand_name" varbit(255),
  "product_category_name" varchar(255) COLLATE "pg_catalog"."default",
  "subtitle" varbit(255),
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
INSERT INTO "public"."product" VALUES (3, 1, 1, 'AMD 锐龙R7 2700X电脑主机', 'https://aiwulele.oss-cn-beijing.aliyuncs.com/mall/images/1e060666-2dd0-4388-8160-8295271a1963/5bee9f0eN6576dcf6.jpg', 'No.123456', 0, 1, 0, 0, 0, 5000000, 100, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL);

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
  "version" varchar COLLATE "pg_catalog"."default" NOT NULL,
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
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."address_id_seq"
OWNED BY "public"."receive_address"."id";
SELECT setval('"public"."address_id_seq"', 2, false);
ALTER SEQUENCE "public"."coupon_history_id_seq"
OWNED BY "public"."coupon_history"."id";
SELECT setval('"public"."coupon_history_id_seq"', 6, true);
ALTER SEQUENCE "public"."coupon_id_seq"
OWNED BY "public"."coupon"."id";
SELECT setval('"public"."coupon_id_seq"', 5, true);
ALTER SEQUENCE "public"."mall_order_id_seq"
OWNED BY "public"."mall_order"."id";
SELECT setval('"public"."mall_order_id_seq"', 2, false);
ALTER SEQUENCE "public"."mall_order_item_id_seq"
OWNED BY "public"."mall_order_item"."id";
SELECT setval('"public"."mall_order_item_id_seq"', 2, false);
ALTER SEQUENCE "public"."mall_subject_id_seq"
OWNED BY "public"."mall_subject"."id";
SELECT setval('"public"."mall_subject_id_seq"', 5, true);
ALTER SEQUENCE "public"."member_id_seq"
OWNED BY "public"."member"."id";
SELECT setval('"public"."member_id_seq"', 9, true);
ALTER SEQUENCE "public"."prefrence_area_id_seq"
OWNED BY "public"."prefrence_area"."id";
SELECT setval('"public"."prefrence_area_id_seq"', 2, false);
ALTER SEQUENCE "public"."prefrence_area_product_relation_id_seq"
OWNED BY "public"."prefrence_area_product_relation"."id";
SELECT setval('"public"."prefrence_area_product_relation_id_seq"', 2, false);
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
SELECT setval('"public"."product_category_id_seq"', 4, true);
ALTER SEQUENCE "public"."product_commnet_id_seq"
OWNED BY "public"."product_commnet"."id";
SELECT setval('"public"."product_commnet_id_seq"', 2, false);
ALTER SEQUENCE "public"."product_id_seq"
OWNED BY "public"."product"."id";
SELECT setval('"public"."product_id_seq"', 4, true);
ALTER SEQUENCE "public"."product_subject_relation_id_seq"
OWNED BY "public"."product_subject_relation"."id";
SELECT setval('"public"."product_subject_relation_id_seq"', 2, false);
ALTER SEQUENCE "public"."promotion_id_seq"
OWNED BY "public"."promotion"."id";
SELECT setval('"public"."promotion_id_seq"', 2, false);
ALTER SEQUENCE "public"."sku_stock_id_seq"
OWNED BY "public"."sku_stock"."id";
SELECT setval('"public"."sku_stock_id_seq"', 2, false);
ALTER SEQUENCE "public"."subject_category_id_seq"
OWNED BY "public"."subject_category"."id";
SELECT setval('"public"."subject_category_id_seq"', 2, true);
ALTER SEQUENCE "public"."subject_comment_id_seq"
OWNED BY "public"."subject_comment"."id";
SELECT setval('"public"."subject_comment_id_seq"', 2, false);

-- ----------------------------
-- Primary Key structure for table coupon
-- ----------------------------
ALTER TABLE "public"."coupon" ADD CONSTRAINT "coupon_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table coupon_history
-- ----------------------------
ALTER TABLE "public"."coupon_history" ADD CONSTRAINT "coupon_history_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table mall_order
-- ----------------------------
ALTER TABLE "public"."mall_order" ADD CONSTRAINT "mall_order_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table mall_order_item
-- ----------------------------
ALTER TABLE "public"."mall_order_item" ADD CONSTRAINT "mall_order_item_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table mall_subject
-- ----------------------------
ALTER TABLE "public"."mall_subject" ADD CONSTRAINT "mall_subject_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table member
-- ----------------------------
ALTER TABLE "public"."member" ADD CONSTRAINT "member_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table prefrence_area
-- ----------------------------
ALTER TABLE "public"."prefrence_area" ADD CONSTRAINT "prefrence_area_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table prefrence_area_product_relation
-- ----------------------------
ALTER TABLE "public"."prefrence_area_product_relation" ADD CONSTRAINT "prefrence_area_product_relation_pkey" PRIMARY KEY ("id");

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
