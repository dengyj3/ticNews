use jianbao;
drop table `tbl_article_list`;
create table `tbl_article_list`(
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`title` varchar(255) NOT NULL DEFAULT '' COMMENT '标题',
	`summary` varchar(500) NOT NULL DEFAULT '' COMMENT '摘要',
	`content` varchar(2000) NOT NULL DEFAULT '' COMMENT '内容',
	`source` varchar(100) NOT NULL DEFAULT '' COMMENT '来源',
	`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	`url` varchar(255) NOT NULL DEFAULT '' COMMENT '页面url',
	`vote_positive_name` varchar(20) NOT NULL DEFAULT '' COMMENT '正方名称',
	`vote_positive_count` int(11) NOT NULL DEFAULT 0 COMMENT '正方数量',
	`vote_negtive_name` varchar(20) NOT NULL DEFAULT '' COMMENT '反方名称',
	`vote_negtive_count` int(11) NOT NULL DEFAULT 0 COMMENT '反方数量',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章列表';

drop table `tbl_tag`;
create table `tbl_tag`(
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`tag_name` varchar(20) NOT NULL DEFAULT '' COMMENT '标签名称',
	PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='标签表';

drop table `tbl_article_tag`;
create table `tbl_article_tag`(
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`tag_id` int(11) COMMENT '标签ID',
	`article_id` int(11) COMMENT '文章ID',
	PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章标签关联表';

drop table `tbl_user`;
create table `tbl_user`(
 `id` int(11) NOT NULL AUTO_INCREMENT,
 `subscribe` int(1) COMMENT '是否订阅公众号, 1:订阅, 2:未订阅',
 `openid` varchar(28) COMMENT '用户公众号的openid(公众号和网页是一样的)',
 `unionid` varchar(28) unique COMMENT '统一的用户unionid',
 `subscribe_newspaper` int(1) COMMENT '是否订阅早报, 1:订阅, 2:未订阅',
 `user_source` int(1) COMMENT '用户来源, 1: 公众号, 2:小程序, 3: 其它',
 `mp_openid` varchar(28) COMMENT '小程序的openid',
 `remark` varchar(255) COMMENT '备注',
 PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

drop table `tbl_access_token`;
create table `tbl_access_token`(
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`access_token` varchar(255) COMMENT 'Token',
	`expiresIn` varchar(255) COMMENT '有效期',
	`create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='access_token表';

drop table `tbl_vote`;
create table `tbl_vote`(
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`unionid` varchar(28) COMMENT '用户unionid',
	`article_id` int(11) COMMENT '文章ID',
	`vote` varchar(1) COMMENT '投票',
	PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='投票表';

drop table `tbl_url_config`;
create table `tbl_url_config`(
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`url` varchar(255) COMMENT '订阅早报URL',
	PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='配置表';

drop table `tbl_ftban`;
CREATE TABLE `tbl_ftban` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `apply_sn` varchar(255) DEFAULT NULL,
  `apply_sntt` varchar(255) DEFAULT NULL,
  `apply_enter_address` varchar(255) DEFAULT NULL,
  `enterprise_name` varchar(255) DEFAULT NULL,
  `enterprise_namett` varchar(255) DEFAULT NULL,
  `is_off` varchar(255) DEFAULT NULL,
  `new_processid` varchar(255) DEFAULT NULL,
  `off_date` varchar(255) DEFAULT NULL,
  `org_name` varchar(255) DEFAULT NULL,
  `processid` varchar(255) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `product_namett` varchar(255) DEFAULT NULL,
  `province_confirm` varchar(255) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table `tbl_pf_list`;
CREATE TABLE `tbl_pf_list` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cas` varchar(255) DEFAULT NULL,
  `cname` varchar(255) DEFAULT NULL,
  `pfname` varchar(255) DEFAULT NULL,
  `processid` varchar(255) DEFAULT NULL,
  `ylid` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

select t1.apply_sn,t1.enterprise_name,t1.product_name,t2.pf_name from tbl_ftban t1 left join (select processid,group_concat(ylid,'_',cname order by ylid) pf_name from tbl_pf_list group by processid)t2 on t1.processid=t2.processid

