create table sys_user(
  	user_id varchar(32) primary key comment 'primary key',
  	user_account varchar(50) not null comment '用户账户',
  	user_name varchar(50) null comment '用户姓名',
  	acc_pwd varchar(128) not null comment '账户密码',
  	is_enable boolean not null comment '账户是否可用',
  	telephone varchar(20) null comment '手机号码',
  	create_time datetime null comment '创建时间',
  	update_time datetime null comment '修改时间',
  	remark varchar(200) null comment '备注'
)ENGINE=InnoDB  DEFAULT CHARSET=utf8  COMMENT='系统用户账户表';

create table sys_role(
	role_id varchar(32) primary key comment 'primary key',
	role_name varchar(50) not null comment '角色名称',
	role_desc varchar(100) null comment '角色描述',
	is_enable boolean not null comment '角色是否可用',
  	create_time datetime null comment '创建时间',
  	update_time datetime null comment '修改时间',
  	remark varchar(200) null comment '备注'
)ENGINE=InnoDB  DEFAULT CHARSET=utf8  COMMENT='系统用户角色表';

create table sys_menu(
	menu_id varchar(32) primary key comment 'primary key',
	menu_name varchar(50) not null comment '菜单名称',
	menu_code varchar(50) not null comment '菜单编码',
	menu_type varchar(3) not null comment '菜单类型,01:菜单目录 02:菜单 03:菜单子权限',
	menu_url varchar(100) null comment '菜单链接地址 http开头为绝对，否则为根路径+menu_url',
	menu_order int(11) null default 0 comment '菜单显示排序',
	menu_style varchar(100) null comment '菜单样式类型',
	menu_parent_id varchar(32) null comment '菜单父节点',
	create_time datetime null comment '创建时间',
  	update_time datetime null comment '修改时间',
  	remark varchar(200) null comment '备注'
)ENGINE=InnoDB  DEFAULT CHARSET=utf8  COMMENT='系统菜单表';

create table sys_user_role_rel(
	user_id varchar(32) not null comment 'foreign key,sys_user.user_id',
	role_id varchar(32) not null comment 'primary key,sys_role.role_id',
	create_time datetime null comment '创建时间',
  	update_time datetime null comment '修改时间',
  	remark varchar(200) null comment '备注'
)ENGINE=InnoDB  DEFAULT CHARSET=utf8  COMMENT='系统用户角色关系表';

create table sys_role_menu_rel(
	role_id varchar(32) not null comment 'primary key,sys_role.role_id',
	menu_id varchar(32) not null comment 'foreign key,sys_menu.menu_id',
	create_time datetime null comment '创建时间',
  	update_time datetime null comment '修改时间',
  	remark varchar(200) null comment '备注'
)ENGINE=InnoDB  DEFAULT CHARSET=utf8  COMMENT='系统角色菜单关系表';

create table sys_user_menu_rel(
	user_id varchar(32) not null comment 'foreign key,sys_user.user_id',
	menu_id varchar(32) not null comment 'foreign key,sys_menu.menu_id',
	create_time datetime null comment '创建时间',
  	update_time datetime null comment '修改时间',
  	remark varchar(200) null comment '备注'
)ENGINE=InnoDB  DEFAULT CHARSET=utf8  COMMENT='系统用户菜单关系表';

create table SYS_USER_LOGIN_LOG(
	user_login_log_id varchar(32) primary key comment 'primary key',
	user_id varchar(32) not null comment 'foreign key,sys_user.user_id',
	login_ip varchar(40) null comment '登陆ip',
	login_time varchar(40) null comment '登陆时间',
	login_client varchar(100) null comment '登陆客户端信息'
)ENGINE=InnoDB  DEFAULT CHARSET=utf8  COMMENT='系统用户登陆日志表';

CREATE TABLE `t_operate_log`(
	`operate_log_id` varchar(32) primary key comment 'primary key',
	`user_id` INT NOT NULL COMMENT '操作员id',
	`operate` varchar(200) NOT NULL COMMENT '执行操作funcName',
	`model_name` varchar(200) NOT NULL COMMENT '模块名',
	`operate_desc` varchar(200) NULL COMMENT '操作描述相关关键业务信息',
	`ip` varchar(50) NULL COMMENT 'IP ADDRESS',
	`client` varchar(50) null comment '操作客户端',
	`create_time` datetime DEFAULT NULL COMMENT '创建时间',
	`remark` varchar(200) DEFAULT NULL COMMENT '备注'
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='业务员操作日志表';