-- 创建库
create database if not exists java_inter_view_pro;

-- 切换库
use java_inter_view_pro;


-- 用户表
create table if not exists user
(
    id            bigint auto_increment comment 'id' primary key,
    userAccount   varchar(256)  not null comment '账号',
    userPassword  varchar(512)  not null comment '密码',
    unionId       varchar(256)  null comment '微信开放平台id',
    mpOpenId      varchar(256)  null comment '公众号openId',
    userName      varchar(256)  null comment '用户昵称',
    userAvatar    varchar(1024) null comment '用户头像',
    userProfile   varchar(512)  null comment '用户简介',
    vipExpireTime datetime      null comment '会员过期时间',
    vipCode       varchar(128)  null comment '会员兑换码',
    vipNumber     bigint        null comment '会员编号',
    shareCode     varchar(20)            DEFAULT NULL COMMENT '分享码',
    inviteUser    bigint                 DEFAULT NULL COMMENT '邀请用户 id',
    userRole      varchar(256)           default 'user' not null comment '用户角色：user/admin/vip',
    userStatus    tinyint                default 0 not null comment '用户状态（0正常 1停用）',
    creator       varchar(64)   NULL     DEFAULT '' COMMENT '创建者',
    createTime    datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updater       varchar(64)   NULL     DEFAULT '' COMMENT '更新者',
    updateTime    datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted       bit(1)        NOT NULL DEFAULT b'0' COMMENT '是否删除',
    index idx_unionId (unionId)
) comment '用户表' collate = utf8mb4_unicode_ci;

-- 题库表
create table if not exists question_bank
(
    id            bigint auto_increment comment 'id' primary key,
    title         varchar(256)  null comment '标题',
    description   text          null comment '描述',
    picture       varchar(2048) null comment '图片',
    reviewStatus  int                    default 0 not null comment '状态：0-待审核, 1-通过, 2-拒绝',
    reviewMessage varchar(512)  null comment '审核信息',
    reviewerId    bigint        null comment '审核人 id',
    reviewTime    datetime      null comment '审核时间',
    viewNum       int                    default 0 not null comment '浏览量',
    priority      int                    default 0 not null comment '优先级',
    creator       varchar(64)   NULL     DEFAULT '' COMMENT '创建者',
    createTime    datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updater       varchar(64)   NULL     DEFAULT '' COMMENT '更新者',
    updateTime    datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted       bit(1)        NOT NULL DEFAULT b'0' COMMENT '是否删除',
    index idx_title (title)
) comment '题库' collate = utf8mb4_unicode_ci;

-- 题目表
create table if not exists question
(
    id         bigint auto_increment comment 'id' primary key,
    title      varchar(256)  null comment '标题',
    content    text          null comment '内容',
    tags       varchar(1024) null comment '标签列表（json 数组）',
    answer     text          null comment '推荐答案',
    source     varchar(512)  null comment '题目来源',
    needVip    tinyint                default 0 not null comment '仅会员可见（1 表示仅会员可见）',
    viewNum    int                    default 0 not null comment '浏览量',
    thumbNum   int                    default 0 not null comment '点赞数',
    favourNum  int                    default 0 not null comment '收藏数',
    creator    varchar(64)   NULL     DEFAULT '' COMMENT '创建者',
    createTime datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updater    varchar(64)   NULL     DEFAULT '' COMMENT '更新者',
    updateTime datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted    bit(1)        NOT NULL DEFAULT b'0' COMMENT '是否删除',
    index idx_title (title),
    index idx_userId (creator)
) comment '题目' collate = utf8mb4_unicode_ci;

-- 题库题目表（硬删除）
create table if not exists question_bank_question
(
    id             bigint auto_increment comment 'id' primary key,
    questionBankId bigint                             not null comment '题库 id',
    questionId     bigint                             not null comment '题目 id',
    questionOrder  int      default 0                 not null comment '题目顺序（题号）',
    userId         bigint                             not null comment '创建用户 id',
    createTime     datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime     datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    UNIQUE (questionBankId, questionId)
) comment '题库题目' collate = utf8mb4_unicode_ci;


