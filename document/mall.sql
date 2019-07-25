-- Table: public.mall_order

-- DROP TABLE public.mall_order;

CREATE TABLE public.mall_order
(
    id                      integer                                             NOT NULL DEFAULT nextval('mall_order_id_seq'::regclass),
    member_id               integer                                             NOT NULL,
    coupon_id               integer                                             NOT NULL,
    order_sn                character varying(100) COLLATE pg_catalog."default" NOT NULL,
    member_username         character varying(100) COLLATE pg_catalog."default" NOT NULL,
    total_amount            bigint                                              NOT NULL,
    pay_amount              bigint                                              NOT NULL,
    freight_amount          bigint                                              NOT NULL,
    promotion_amount        bigint                                              NOT NULL DEFAULT 0,
    integration_amount      bigint                                              NOT NULL DEFAULT 0,
    coupon_amount           bigint                                              NOT NULL DEFAULT 0,
    discount_amount         bigint                                              NOT NULL,
    pay_type                smallint                                            NOT NULL,
    source_type             smallint                                            NOT NULL,
    status                  smallint                                            NOT NULL,
    order_type              smallint                                            NOT NULL,
    auto_confirm_day        smallint                                            NOT NULL DEFAULT 7,
    integration             bigint                                              NOT NULL,
    growth                  bigint                                              NOT NULL,
    receiver_name           character varying(100) COLLATE pg_catalog."default" NOT NULL,
    receiver_phone          character varying(100) COLLATE pg_catalog."default" NOT NULL,
    receiver_province       character(100) COLLATE pg_catalog."default"         NOT NULL,
    receiver_city           character varying(255) COLLATE pg_catalog."default" NOT NULL,
    receiver_region         character varying(255) COLLATE pg_catalog."default",
    receiver_detail_address bit varying(255),
    note                    text COLLATE pg_catalog."default",
    confirm_status          smallint                                            NOT NULL DEFAULT 0,
    delete_status           smallint                                            NOT NULL DEFAULT 0,
    use_integration         bigint                                              NOT NULL,
    payment_time            timestamp without time zone,
    delivery_time           timestamp without time zone,
    receive_time            timestamp without time zone,
    comment_time            timestamp without time zone,
    modify_time             timestamp without time zone,
    version                 bigint                                              NOT NULL,
    CONSTRAINT mall_order_pkey PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE public.mall_order
    OWNER to postgres;
COMMENT ON TABLE public.mall_order
    IS '商城订单';

COMMENT ON COLUMN public.mall_order.member_id
    IS '用户id';

COMMENT ON COLUMN public.mall_order.promotion_amount
    IS '促销抵扣金额';

COMMENT ON COLUMN public.mall_order.integration_amount
    IS '积分抵扣金额';

COMMENT ON COLUMN public.mall_order.coupon_amount
    IS '优惠券抵扣金额';

COMMENT ON COLUMN public.mall_order.discount_amount
    IS '管理员后台调整订单使用的折扣金额';

COMMENT ON COLUMN public.mall_order.pay_type
    IS '支付方式：0->未支付；1->支付宝；2->微信';

COMMENT ON COLUMN public.mall_order.source_type
    IS '订单来源：0->web订单；1->app订单';

COMMENT ON COLUMN public.mall_order.status
    IS '订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单';

COMMENT ON COLUMN public.mall_order.order_type
    IS '订单类型：0->正常订单；1->秒杀订单';

COMMENT ON COLUMN public.mall_order.auto_confirm_day
    IS '自动收货日期(天）默认7天';

COMMENT ON COLUMN public.mall_order.integration
    IS '下单所获得积分';

COMMENT ON COLUMN public.mall_order.growth
    IS '下单所获得成长值';

COMMENT ON COLUMN public.mall_order.note
    IS '备注信息';

COMMENT ON COLUMN public.mall_order.confirm_status
    IS '是否确定收货 0->未确定 1->已确定';

COMMENT ON COLUMN public.mall_order.delete_status
    IS '删除状态 0—>未删除 1->已删除';

COMMENT ON COLUMN public.mall_order.use_integration
    IS '下单时所使用的积分';

COMMENT ON COLUMN public.mall_order.payment_time
    IS '支付时间';

COMMENT ON COLUMN public.mall_order.delivery_time
    IS '发货时间';

COMMENT ON COLUMN public.mall_order.receive_time
    IS '收货时间';

COMMENT ON COLUMN public.mall_order.comment_time
    IS '评价时间';

COMMENT ON COLUMN public.mall_order.modify_time
    IS '订单修改时间';

-- Table: public.mall_order_item

-- DROP TABLE public.mall_order_item;

CREATE TABLE public.mall_order_item
(
    id                  bigint                                              NOT NULL DEFAULT nextval('mall_order_item_id_seq'::regclass),
    order_id            integer                                             NOT NULL,
    order_sn            character varying(100) COLLATE pg_catalog."default" NOT NULL,
    product_id          integer                                             NOT NULL,
    product_pic         text COLLATE pg_catalog."default",
    product_name        character varying(255) COLLATE pg_catalog."default" NOT NULL,
    product_brand       character varying(255) COLLATE pg_catalog."default" NOT NULL,
    product_sn          character varying(100) COLLATE pg_catalog."default" NOT NULL,
    product_price       bigint                                              NOT NULL,
    product_quantity    integer                                             NOT NULL,
    product_sku_id      integer                                             NOT NULL,
    product_sku_code    character varying COLLATE pg_catalog."default"      NOT NULL,
    product_category_id integer                                             NOT NULL,
    spa1                character varying(255) COLLATE pg_catalog."default",
    spa2                character varying(255) COLLATE pg_catalog."default",
    sp3                 character varying(255) COLLATE pg_catalog."default",
    promotion_name      character varying(255) COLLATE pg_catalog."default",
    promotion_amount    bigint,
    coupon_amount       bigint,
    integration_amount  bigint,
    real_amount         bigint                                              NOT NULL,
    gift_integration    integer                                             NOT NULL DEFAULT 0,
    gift_growth         integer                                             NOT NULL DEFAULT 0,
    product_attr        text COLLATE pg_catalog."default",
    version             integer                                             NOT NULL DEFAULT 0,
    CONSTRAINT mall_order_item_pkey PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE public.mall_order_item
    OWNER to postgres;
COMMENT ON TABLE public.mall_order_item
    IS '订单所对应的商品';

COMMENT ON COLUMN public.mall_order_item.real_amount
    IS '该商品经过各种抵扣后的实际金额';

COMMENT ON COLUMN public.mall_order_item.gift_integration
    IS '获得积分';

COMMENT ON COLUMN public.mall_order_item.gift_growth
    IS '获得成长值';

COMMENT ON COLUMN public.mall_order_item.product_attr
    IS '商品销售属性:[{"key":"颜色","value":"颜色"},{"key":"容量","value":"4G"}]';

-- Table: public.mall_subject

-- DROP TABLE public.mall_subject;

CREATE TABLE public.mall_subject
(
    id               integer                                        NOT NULL DEFAULT nextval('mall_subject_id_seq'::regclass),
    category_id      integer                                        NOT NULL,
    title            character varying COLLATE pg_catalog."default" NOT NULL,
    pic              text COLLATE pg_catalog."default",
    product_count    integer                                        NOT NULL DEFAULT 0,
    recommend_status smallint                                       NOT NULL DEFAULT 0,
    create_time      timestamp without time zone                    NOT NULL,
    collect_count    integer                                        NOT NULL DEFAULT 0,
    read_count       integer                                        NOT NULL DEFAULT 0,
    comment_count    integer                                        NOT NULL DEFAULT 0,
    album_pics       text COLLATE pg_catalog."default",
    description      text COLLATE pg_catalog."default"              NOT NULL,
    show_status      smallint                                       NOT NULL DEFAULT 0,
    content          text COLLATE pg_catalog."default",
    forward_count    integer                                        NOT NULL DEFAULT 0,
    category_name    bit varying(255)                               NOT NULL,
    version          bigint                                         NOT NULL DEFAULT 0,
    CONSTRAINT mall_subject_pkey PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE public.mall_subject
    OWNER to postgres;
COMMENT ON TABLE public.mall_subject
    IS '商城专题';

COMMENT ON COLUMN public.mall_subject.category_id
    IS '专题分类id';

COMMENT ON COLUMN public.mall_subject.title
    IS '专题标题';

COMMENT ON COLUMN public.mall_subject.pic
    IS '专题主图';

COMMENT ON COLUMN public.mall_subject.product_count
    IS '关联商品数量';

COMMENT ON COLUMN public.mall_subject.recommend_status
    IS '推荐状态';

COMMENT ON COLUMN public.mall_subject.create_time
    IS '专题创建时间';

COMMENT ON COLUMN public.mall_subject.collect_count
    IS '专题收藏数目';

COMMENT ON COLUMN public.mall_subject.read_count
    IS '专题阅读数目';

COMMENT ON COLUMN public.mall_subject.comment_count
    IS '专题评论次数';

COMMENT ON COLUMN public.mall_subject.album_pics
    IS '专题画廊';

COMMENT ON COLUMN public.mall_subject.description
    IS '专题描述';

COMMENT ON COLUMN public.mall_subject.show_status
    IS '专题显示状态0->不显示；1->显示';

COMMENT ON COLUMN public.mall_subject.content
    IS '专题内容';

COMMENT ON COLUMN public.mall_subject.forward_count
    IS '专题转发数量';

COMMENT ON COLUMN public.mall_subject.category_name
    IS '专题分类名称';

COMMENT ON COLUMN public.mall_subject.version
    IS 'CAS ';

-- Table: public.member

-- DROP TABLE public.member;

CREATE TABLE public.member
(
    id                     integer                                        NOT NULL DEFAULT nextval('member_id_seq'::regclass),
    username               character varying COLLATE pg_catalog."default" NOT NULL,
    password               text COLLATE pg_catalog."default"              NOT NULL,
    gender                 smallint                                       NOT NULL,
    phone                  character varying(20) COLLATE pg_catalog."default",
    status                 smallint                                       NOT NULL DEFAULT 0,
    create_time            timestamp without time zone                    NOT NULL,
    icon                   text COLLATE pg_catalog."default",
    country                character varying(100) COLLATE pg_catalog."default",
    province               character varying(100) COLLATE pg_catalog."default",
    city                   character varying(100) COLLATE pg_catalog."default",
    job                    character varying(50) COLLATE pg_catalog."default",
    personalized_signature text COLLATE pg_catalog."default",
    birthday               timestamp(6) without time zone,
    member_level_id        integer,
    nickname               text COLLATE pg_catalog."default",
    CONSTRAINT member_pkey PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE public.member
    OWNER to postgres;
COMMENT ON TABLE public.member
    IS '商城用户表';

COMMENT ON COLUMN public.member.username
    IS '用户名';

COMMENT ON COLUMN public.member.password
    IS '用户密码';

COMMENT ON COLUMN public.member.gender
    IS '性别(0男1女)';

COMMENT ON COLUMN public.member.phone
    IS '会员手机号码';

COMMENT ON COLUMN public.member.status
    IS '会员账号状态(0未激活1正常2异常)';

COMMENT ON COLUMN public.member.create_time
    IS '创建时间';

COMMENT ON COLUMN public.member.icon
    IS '头像地址';

COMMENT ON COLUMN public.member.country
    IS '国家';

COMMENT ON COLUMN public.member.province
    IS '省份';

COMMENT ON COLUMN public.member.city
    IS '城市';

COMMENT ON COLUMN public.member.job
    IS '工作';

COMMENT ON COLUMN public.member.personalized_signature
    IS '个性签名';

COMMENT ON COLUMN public.member.birthday
    IS '生日信息';

COMMENT ON COLUMN public.member.member_level_id
    IS '会员等级id';

-- Table: public.prefrence_area

-- DROP TABLE public.prefrence_area;

CREATE TABLE public.prefrence_area
(
    id          integer  NOT NULL DEFAULT nextval('prefrence_area_id_seq'::regclass),
    name        integer  NOT NULL,
    sub_title   bit varying,
    pic         text COLLATE pg_catalog."default",
    sort        integer  NOT NULL DEFAULT 0,
    show_status smallint NOT NULL DEFAULT 0,
    CONSTRAINT prefrence_area_pkey PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE public.prefrence_area
    OWNER to postgres;

COMMENT ON COLUMN public.prefrence_area.name
    IS '优选名称';

COMMENT ON COLUMN public.prefrence_area.sub_title
    IS '子标题';

COMMENT ON COLUMN public.prefrence_area.pic
    IS '优选图片';

COMMENT ON COLUMN public.prefrence_area.sort
    IS '排序';

COMMENT ON COLUMN public.prefrence_area.show_status
    IS '显示状态 0->不显示 1->显示';

-- Table: public.prefrence_area_product_relation

-- DROP TABLE public.prefrence_area_product_relation;

CREATE TABLE public.prefrence_area_product_relation
(
    id                integer NOT NULL DEFAULT nextval('prefrence_area_product_relation_id_seq'::regclass),
    prefrence_area_id integer NOT NULL,
    product_id        integer NOT NULL,
    CONSTRAINT prefrence_area_product_relation_pkey PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE public.prefrence_area_product_relation
    OWNER to postgres;
COMMENT ON TABLE public.prefrence_area_product_relation
    IS '优选和商品关联表';

COMMENT ON COLUMN public.prefrence_area_product_relation.prefrence_area_id
    IS '优选id';

COMMENT ON COLUMN public.prefrence_area_product_relation.product_id
    IS '商品id';

-- Table: public.product

-- DROP TABLE public.product;

CREATE TABLE public.product
(
    id                    integer                                             NOT NULL DEFAULT nextval('product_id_seq'::regclass),
    brand_id              integer                                             NOT NULL,
    product_category_id   integer                                             NOT NULL,
    title                 character varying(255) COLLATE pg_catalog."default" NOT NULL,
    pic                   text COLLATE pg_catalog."default",
    product_sn            character varying(255) COLLATE pg_catalog."default" NOT NULL,
    delete_status         smallint                                            NOT NULL,
    publish_status        smallint                                            NOT NULL,
    new_status            smallint                                            NOT NULL,
    recommend_status      smallint                                            NOT NULL,
    verify_status         smallint                                            NOT NULL,
    price                 bigint                                              NOT NULL DEFAULT 0,
    sale                  bigint,
    brand_name            bit varying(255),
    product_category_name character varying(255) COLLATE pg_catalog."default",
    subtitle              bit varying(255),
    sort                  bigint,
    description           text COLLATE pg_catalog."default",
    album_pics            text COLLATE pg_catalog."default",
    services              bit varying(100),
    gift_growth           bigint,
    gift_point            bigint                                              NOT NULL DEFAULT 0,
    use_point_limit       bigint,
    original_price        bigint,
    CONSTRAINT product_pkey PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE public.product
    OWNER to postgres;

COMMENT ON COLUMN public.product.brand_id
    IS '商品品牌id';

COMMENT ON COLUMN public.product.product_category_id
    IS '商品分类id';

COMMENT ON COLUMN public.product.title
    IS '商品标题';

COMMENT ON COLUMN public.product.pic
    IS '商品主图';

COMMENT ON COLUMN public.product.product_sn
    IS '商品编号';

COMMENT ON COLUMN public.product.delete_status
    IS '删除状态0未删除1已删除';

COMMENT ON COLUMN public.product.publish_status
    IS '上线状态0未上线1上线';

COMMENT ON COLUMN public.product.new_status
    IS '是否新品0不是1是';

COMMENT ON COLUMN public.product.recommend_status
    IS '推荐状态0不推荐1推荐';

COMMENT ON COLUMN public.product.verify_status
    IS '审核状态0未审核1通过审核';

COMMENT ON COLUMN public.product.price
    IS '商品价格(统一以分为单位)';

COMMENT ON COLUMN public.product.sale
    IS '销量';

COMMENT ON COLUMN public.product.brand_name
    IS '品牌名称';

COMMENT ON COLUMN public.product.product_category_name
    IS '产品分类名称';

COMMENT ON COLUMN public.product.subtitle
    IS '子标题';

COMMENT ON COLUMN public.product.sort
    IS '排序';

COMMENT ON COLUMN public.product.description
    IS '商品描述';

COMMENT ON COLUMN public.product.album_pics
    IS '商品画廊';

COMMENT ON COLUMN public.product.services
    IS '产品服务(通过逗号分割)';

COMMENT ON COLUMN public.product.gift_growth
    IS '赠送成长值';

COMMENT ON COLUMN public.product.gift_point
    IS '赠送积分';

COMMENT ON COLUMN public.product.use_point_limit
    IS '限制积分使用数目';

COMMENT ON COLUMN public.product.original_price
    IS '原始价格';

-- Table: public.product_attribute

-- DROP TABLE public.product_attribute;

CREATE TABLE public.product_attribute
(
    id                            integer                                             NOT NULL DEFAULT nextval('product_attribute_id_seq'::regclass),
    product_attribute_category_id integer                                             NOT NULL,
    name                          character varying(255) COLLATE pg_catalog."default" NOT NULL,
    select_type                   smallint                                            NOT NULL,
    input_type                    smallint                                            NOT NULL,
    input_list                    character varying(255) COLLATE pg_catalog."default",
    sort                          integer,
    filter_type                   smallint,
    search_type                   smallint,
    related_status                smallint,
    hand_add_status               smallint,
    type                          smallint                                            NOT NULL,
    CONSTRAINT product_attribute_pkey PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE public.product_attribute
    OWNER to postgres;

-- Table: public.product_attribute_category

-- DROP TABLE public.product_attribute_category;

CREATE TABLE public.product_attribute_category
(
    id              integer                                             NOT NULL DEFAULT nextval('product_attribute_category_id_seq'::regclass),
    name            character varying(100) COLLATE pg_catalog."default" NOT NULL,
    attribute_count integer                                             NOT NULL,
    param_count     integer                                             NOT NULL,
    version         bigint,
    CONSTRAINT product_attribute_category_pkey PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE public.product_attribute_category
    OWNER to postgres;

-- Table: public.product_attribute_value

-- DROP TABLE public.product_attribute_value;

CREATE TABLE public.product_attribute_value
(
    id                   integer                                             NOT NULL DEFAULT nextval('product_attribute_value_id_seq'::regclass),
    product_id           integer                                             NOT NULL,
    product_attribute_id integer                                             NOT NULL,
    value                character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT product_attribute_value_pkey PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE public.product_attribute_value
    OWNER to postgres;

-- Table: public.product_category

-- DROP TABLE public.product_category;

CREATE TABLE public.product_category
(
    id            integer                                             NOT NULL DEFAULT nextval('product_category_id_seq'::regclass),
    parent_id     integer                                             NOT NULL,
    name          character varying(255) COLLATE pg_catalog."default" NOT NULL,
    product_count bigint                                              NOT NULL,
    show_status   smallint                                            NOT NULL,
    icon          text COLLATE pg_catalog."default",
    description   character varying(255) COLLATE pg_catalog."default",
    sort          integer                                                      DEFAULT 0,
    CONSTRAINT product_category_pkey PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE public.product_category
    OWNER to postgres;
COMMENT ON TABLE public.product_category
    IS '商品分类表';

-- Table: public.product_category_attribute_relation

-- DROP TABLE public.product_category_attribute_relation;

CREATE TABLE public.product_category_attribute_relation
(
    id           integer NOT NULL DEFAULT nextval('product_category_attribute_relation_id_seq'::regclass),
    category_id  integer NOT NULL,
    attribute_id integer NOT NULL,
    CONSTRAINT product_category_attribute_relation_pkey PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE public.product_category_attribute_relation
    OWNER to postgres;

-- Table: public.product_commnet

-- DROP TABLE public.product_commnet;

CREATE TABLE public.product_commnet
(
    id                integer                                             NOT NULL DEFAULT nextval('product_commnet_id_seq'::regclass),
    product_id        integer                                             NOT NULL,
    member_nick_name  character varying(100) COLLATE pg_catalog."default" NOT NULL,
    product_name      character varying(255) COLLATE pg_catalog."default" NOT NULL,
    star              integer                                             NOT NULL,
    member_ip         character varying COLLATE pg_catalog."default"      NOT NULL,
    show_status       smallint                                            NOT NULL DEFAULT 1,
    product_attribute character varying COLLATE pg_catalog."default",
    content           text COLLATE pg_catalog."default",
    pics              text COLLATE pg_catalog."default",
    member_icon       text COLLATE pg_catalog."default",
    CONSTRAINT product_commnet_pkey PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE public.product_commnet
    OWNER to postgres;
COMMENT ON TABLE public.product_commnet
    IS '商品评价表';

COMMENT ON COLUMN public.product_commnet.star
    IS '评价星数：0->5';

COMMENT ON COLUMN public.product_commnet.show_status
    IS '显示状态(0->不显示 1->显示)';

COMMENT ON COLUMN public.product_commnet.pics
    IS '评价时上传的商品图片 以逗号分隔 多张图片';

-- Table: public.product_subject_relation

-- DROP TABLE public.product_subject_relation;

CREATE TABLE public.product_subject_relation
(
    id         integer NOT NULL DEFAULT nextval('product_subject_relation_id_seq'::regclass),
    product_id integer NOT NULL,
    subject_id integer NOT NULL,
    CONSTRAINT product_subject_relation_pkey PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE public.product_subject_relation
    OWNER to postgres;
COMMENT ON TABLE public.product_subject_relation
    IS '产品专题关联表';

-- Table: public.promotion

-- DROP TABLE public.promotion;

CREATE TABLE public.promotion
(
    id                   integer                     NOT NULL DEFAULT nextval('promotion_id_seq'::regclass),
    promotion_start_time timestamp without time zone NOT NULL,
    promotion_end_time   timestamp without time zone NOT NULL,
    promotion_per_limit  integer                     NOT NULL,
    promotion_price      bigint                      NOT NULL,
    version              bigint                      NOT NULL DEFAULT 0,
    CONSTRAINT promotion_pkey PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE public.promotion
    OWNER to postgres;
COMMENT ON TABLE public.promotion
    IS '商品促销列表';

COMMENT ON COLUMN public.promotion.promotion_start_time
    IS '促销开始时间';

COMMENT ON COLUMN public.promotion.promotion_end_time
    IS '促销开始时间';

COMMENT ON COLUMN public.promotion.promotion_per_limit
    IS '促销数量';

COMMENT ON COLUMN public.promotion.promotion_price
    IS '促销价格';

-- Table: public.receive_address

-- DROP TABLE public.receive_address;

CREATE TABLE public.receive_address
(
    id             integer                                        NOT NULL DEFAULT nextval('address_id_seq'::regclass),
    name           character varying COLLATE pg_catalog."default" NOT NULL,
    phone          character varying COLLATE pg_catalog."default" NOT NULL,
    default_status smallint                                       NOT NULL,
    province       character varying COLLATE pg_catalog."default" NOT NULL,
    city           character varying COLLATE pg_catalog."default" NOT NULL,
    region         character varying COLLATE pg_catalog."default",
    detail_address character varying COLLATE pg_catalog."default",
    version        character varying COLLATE pg_catalog."default" NOT NULL,
    member_id      integer                                        NOT NULL,
    CONSTRAINT address_pkey PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE public.receive_address
    OWNER to postgres;
COMMENT ON TABLE public.receive_address
    IS '收货地址';

COMMENT ON COLUMN public.receive_address.name
    IS '收货人姓名';

COMMENT ON COLUMN public.receive_address.phone
    IS '收货人手机';

COMMENT ON COLUMN public.receive_address.default_status
    IS '是否默认0->不是 1->是';

COMMENT ON COLUMN public.receive_address.province
    IS '省';

COMMENT ON COLUMN public.receive_address.city
    IS '市';

COMMENT ON COLUMN public.receive_address.region
    IS '区';

COMMENT ON COLUMN public.receive_address.detail_address
    IS '详细地址';

COMMENT ON COLUMN public.receive_address.version
    IS 'CAS版本号';

COMMENT ON COLUMN public.receive_address.member_id
    IS '会员id';

-- Table: public.receive_address

-- DROP TABLE public.receive_address;

CREATE TABLE public.receive_address
(
    id             integer                                        NOT NULL DEFAULT nextval('address_id_seq'::regclass),
    name           character varying COLLATE pg_catalog."default" NOT NULL,
    phone          character varying COLLATE pg_catalog."default" NOT NULL,
    default_status smallint                                       NOT NULL,
    province       character varying COLLATE pg_catalog."default" NOT NULL,
    city           character varying COLLATE pg_catalog."default" NOT NULL,
    region         character varying COLLATE pg_catalog."default",
    detail_address character varying COLLATE pg_catalog."default",
    version        character varying COLLATE pg_catalog."default" NOT NULL,
    member_id      integer                                        NOT NULL,
    CONSTRAINT address_pkey PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE public.receive_address
    OWNER to postgres;
COMMENT ON TABLE public.receive_address
    IS '收货地址';

COMMENT ON COLUMN public.receive_address.name
    IS '收货人姓名';

COMMENT ON COLUMN public.receive_address.phone
    IS '收货人手机';

COMMENT ON COLUMN public.receive_address.default_status
    IS '是否默认0->不是 1->是';

COMMENT ON COLUMN public.receive_address.province
    IS '省';

COMMENT ON COLUMN public.receive_address.city
    IS '市';

COMMENT ON COLUMN public.receive_address.region
    IS '区';

COMMENT ON COLUMN public.receive_address.detail_address
    IS '详细地址';

COMMENT ON COLUMN public.receive_address.version
    IS 'CAS版本号';

COMMENT ON COLUMN public.receive_address.member_id
    IS '会员id';

-- Table: public.sku_stock

-- DROP TABLE public.sku_stock;

CREATE TABLE public.sku_stock
(
    id              integer                                        NOT NULL DEFAULT nextval('sku_stock_id_seq'::regclass),
    product_id      integer                                        NOT NULL,
    sku_code        character varying COLLATE pg_catalog."default" NOT NULL,
    price           bigint                                         NOT NULL,
    stock           bigint                                         NOT NULL,
    low_stock       bigint                                         NOT NULL,
    sp1             character varying COLLATE pg_catalog."default",
    sp2             character varying COLLATE pg_catalog."default",
    sp3             character varying COLLATE pg_catalog."default",
    pic             text COLLATE pg_catalog."default",
    sale            bigint                                         NOT NULL,
    promotion_price bigint,
    lock_stock      smallint,
    CONSTRAINT sku_stock_pkey PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE public.sku_stock
    OWNER to postgres;
COMMENT ON TABLE public.sku_stock
    IS 'sku库存';

COMMENT ON COLUMN public.sku_stock.product_id
    IS '商品id';

COMMENT ON COLUMN public.sku_stock.sku_code
    IS '库存编号';

COMMENT ON COLUMN public.sku_stock.price
    IS '商品价格';

COMMENT ON COLUMN public.sku_stock.stock
    IS '库存数量';

COMMENT ON COLUMN public.sku_stock.low_stock
    IS '库存警报';

COMMENT ON COLUMN public.sku_stock.sp1
    IS '属性1';

COMMENT ON COLUMN public.sku_stock.sp2
    IS '属性2';

COMMENT ON COLUMN public.sku_stock.sp3
    IS '属性3';

COMMENT ON COLUMN public.sku_stock.pic
    IS '图片';

COMMENT ON COLUMN public.sku_stock.sale
    IS '销量';

COMMENT ON COLUMN public.sku_stock.promotion_price
    IS '促销价格';

COMMENT ON COLUMN public.sku_stock.lock_stock
    IS '低库存警报';

-- Table: public.subject_category

-- DROP TABLE public.subject_category;

CREATE TABLE public.subject_category
(
    id            integer                                             NOT NULL DEFAULT nextval('subject_category_id_seq'::regclass),
    name          character varying(100) COLLATE pg_catalog."default" NOT NULL,
    icon          text COLLATE pg_catalog."default",
    subject_count integer                                             NOT NULL DEFAULT 0,
    show_status   smallint                                            NOT NULL DEFAULT 0,
    sort          integer                                             NOT NULL DEFAULT 0,
    version       bigint                                              NOT NULL DEFAULT 0,
    CONSTRAINT subject_category_pkey PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE public.subject_category
    OWNER to postgres;
COMMENT ON TABLE public.subject_category
    IS '专题分类';

COMMENT ON COLUMN public.subject_category.name
    IS '专题分类名称';

COMMENT ON COLUMN public.subject_category.icon
    IS '分类图标';

COMMENT ON COLUMN public.subject_category.subject_count
    IS '专题数目';

COMMENT ON COLUMN public.subject_category.show_status
    IS '显示状态 0->不显示 1->显示';

COMMENT ON COLUMN public.subject_category.sort
    IS '专题排序';

-- Table: public.subject_comment

-- DROP TABLE public.subject_comment;

CREATE TABLE public.subject_comment
(
    id               integer                           NOT NULL DEFAULT nextval('subject_comment_id_seq'::regclass),
    subject_id       integer                           NOT NULL,
    member_nick_name text COLLATE pg_catalog."default" NOT NULL,
    member_icon      text COLLATE pg_catalog."default",
    content          text COLLATE pg_catalog."default",
    create_time      timestamp without time zone       NOT NULL,
    show_status      smallint                          NOT NULL DEFAULT 1,
    CONSTRAINT subject_comment_pkey PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE public.subject_comment
    OWNER to postgres;
COMMENT ON TABLE public.subject_comment
    IS '专题评论表';

COMMENT ON COLUMN public.subject_comment.subject_id
    IS '专题id';

COMMENT ON COLUMN public.subject_comment.member_nick_name
    IS '用户昵称';

COMMENT ON COLUMN public.subject_comment.member_icon
    IS '用户头像';

COMMENT ON COLUMN public.subject_comment.create_time
    IS '评论创建时间';

COMMENT ON COLUMN public.subject_comment.show_status
    IS '显示状态 0->不显示 1->显示';

-- Table: public.coupon

-- DROP TABLE public.coupon;

CREATE TABLE public.coupon
(
    id            integer                                             NOT NULL DEFAULT nextval('coupon_id_seq'::regclass),
    type          smallint                                            NOT NULL,
    name          character varying(100) COLLATE pg_catalog."default" NOT NULL,
    amount        bigint                                              NOT NULL,
    per_limit     integer                                             NOT NULL,
    min_point     bigint                                              NOT NULL,
    start_time    timestamp(6) without time zone                      NOT NULL,
    end_time      timestamp(6) without time zone                      NOT NULL,
    use_type      smallint                                            NOT NULL,
    note          character varying(255) COLLATE pg_catalog."default" NOT NULL,
    count         bigint                                              NOT NULL,
    receive_count bigint                                              NOT NULL DEFAULT 0,
    version       bigint                                              NOT NULL DEFAULT 0,
    CONSTRAINT coupon_pkey PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE public.coupon
    OWNER to postgres;
COMMENT ON TABLE public.coupon
    IS '商城优惠券表';

COMMENT ON COLUMN public.coupon.type
    IS '优惠券类型 0->全场赠卷 1->会员赠卷 2->购物赠卷 3->注册赠卷';

COMMENT ON COLUMN public.coupon.amount
    IS '优惠券金额';

COMMENT ON COLUMN public.coupon.per_limit
    IS '每人限制领取数量';

COMMENT ON COLUMN public.coupon.min_point
    IS '使用门槛 0->代表无门槛';

COMMENT ON COLUMN public.coupon.start_time
    IS '优惠券开始领取时间';

COMMENT ON COLUMN public.coupon.end_time
    IS '优惠券截止领取时间';

COMMENT ON COLUMN public.coupon.use_type
    IS '使用类型：0->全场通用；1->指定分类；2->指定商品';

COMMENT ON COLUMN public.coupon.note
    IS '备注信息';

COMMENT ON COLUMN public.coupon.count
    IS '优惠券发现量';

COMMENT ON COLUMN public.coupon.receive_count
    IS '已领取数量';

-- Table: public.coupon_history

-- DROP TABLE public.coupon_history;

CREATE TABLE public.coupon_history
(
    id              integer                           NOT NULL DEFAULT nextval('coupon_history_id_seq'::regclass),
    coupon_id       integer                           NOT NULL,
    member_id       integer                           NOT NULL,
    member_nickname text COLLATE pg_catalog."default" NOT NULL,
    type            smallint                          NOT NULL,
    create_time     timestamp(6) without time zone    NOT NULL,
    status          smallint                          NOT NULL DEFAULT 0,
    use_time        timestamp(6) without time zone,
    order_id        integer,
    order_sn        character varying COLLATE pg_catalog."default",
    version         bigint                            NOT NULL DEFAULT 0,
    CONSTRAINT coupon_history_pkey PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE public.coupon_history
    OWNER to postgres;
COMMENT ON TABLE public.coupon_history
    IS '优惠券领取记录';

COMMENT ON COLUMN public.coupon_history.member_nickname
    IS '会员昵称';

COMMENT ON COLUMN public.coupon_history.type
    IS '获取类型：0->后台赠送；1->主动获取';

COMMENT ON COLUMN public.coupon_history.status
    IS '使用状态：0->未使用；1->已使用；2->已过期';

COMMENT ON COLUMN public.coupon_history.use_time
    IS '使用时间';