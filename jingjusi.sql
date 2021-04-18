drop database if exists JingJuSi;
create database JingJuSi default character set utf8mb4;
use JingJuSi;
drop table if exists user;
drop table if exists jing_auth_user;
drop table if exists jing_video_info;
drop table if exists jing_image_info;
drop table if exists jing_course_info;
drop table if exists jing_course_video_info;
drop table if exists jing_course_student_info;
drop table if exists jing_course_manager_info;
# create table user(
#                      id bigint not null auto_increment comment '主键',
#                      active boolean comment '是否激活',
#                      password varchar(20) comment '密码',
#                      roles varchar(20) comment '权限',
#                      user_name varchar(20) comment '用户名',
#                      primary key idx_id(id),
#                      unique key idx_username(user_name)
# )engine = InnoDB comment '用户信息';
# insert into user(active, password, roles, user_name) values (true,'pass','ROLE_USER','user');
create table jing_auth_user(
                               id bigint not null auto_increment comment '主键',
                               password varchar(20) comment '密码',
                               last_login timestamp comment '上次登录时间' default now(),
                               roles varchar(20) comment '权限',
                               username varchar(20) unique comment '用户名',
                               first_name varchar(10) comment '名',
                               last_name varchar(10) comment '姓',
                               email varchar(20) unique comment '邮箱地址',
                               number varchar(20) unique comment '手机号',
                               is_staff boolean comment '是否职员',
                               is_active boolean comment '是否在线',
                               date_joined timestamp comment '加入时间' default now(),
                               level int comment '级别',
                               remarks varchar(256) comment '备注说明',
                               primary key idx_id(id),
                               unique key idx_username(username),
                               unique key idx_email(email),
                               unique key idx_number(number)
)engine = InnoDB comment '用户信息';
insert into jing_auth_user (password, roles, username, first_name, last_name, email, number, is_staff, is_active, level, remarks)
values ('pass','ROLE_ADMIN','admin','hao','w','1070390210@qq.com','13541130632',true,true,0,'测试');
insert into jing_auth_user (password, roles, username, first_name, last_name, email, number, is_staff, is_active, level, remarks)
values ('pass','ROLE_USER','user','hao','w','whz@gmail.com','110',true,true,0,'测试');
create table jing_video_info(
                                id bigint not null auto_increment comment '主键',
                                video_url varchar(128) not null unique comment '视频url',
                                video_name varchar(52) not null comment '视频名',
                                video_size varchar(32) not null comment '视频大小',
                                video_width int comment '视频宽度',
                                video_height int comment '视频高度',
                                video_time time not null comment '视频时长',
                                speaker varchar(64) comment '主讲人',
                                ma_lv decimal(3,2) comment '码率',
                                upload_time timestamp comment '上传时间' default now(),
                                upload_user varchar(32) comment '上传用户',
                                last_request timestamp comment '上次访问时间' default now(),
                                cnt_visit bigint comment '访问次数',
                                remarks varchar(256) comment '备注说明',
                                primary key (id),
                                unique key idx_url(video_url),
                                key idx_during_time(video_time),
                                key idx_name(video_name),
                                key idx_upload(upload_time)
)engine = InnoDB comment '视频信息表';

create table jing_image_info(
                                id bigint not null auto_increment comment '主键',
                                image_url varchar(128) not null unique comment '图片url',
                                image_name varchar(52) not null comment '图片名',
                                image_size varchar(32) not null comment '图片大小',
                                image_width int comment '图片宽度',
                                image_height int comment '图片高度',
                                upload_time timestamp comment '上传时间' default now(),
                                upload_user varchar(32) comment '上传用户',
                                last_request timestamp comment '上次访问时间' default now(),
                                cnt_visit bigint comment '访问次数',
                                remarks varchar(256) comment '备注说明',
                                video_id bigint comment '对应视频',
                                primary key (id),
                                unique key idx_url(image_url),
                                key idx_name(image_name),
                                key idx_video_id(video_id),
                                key idx_upload(upload_time)
)engine = InnoDB comment '图片信息表';

create table jing_course_info(
                                 id bigint not null auto_increment comment '主键',
                                 class_name varchar(64) comment '课程名',
                                 speaker varchar(64) comment '主讲人',
                                 open_time timestamp comment '开设时间',
                                 summary varchar(1024) comment '课程简介',
                                 remarks varchar(256) comment '备注',
                                 index_image varchar(128) comment '图片url',
                                 primary key (id),
                                 key idx_name(class_name),
                                 key idx_open(open_time)
)engine = InnoDB comment '课程信息表';

create table jing_course_video_info(
                                       id bigint not null auto_increment comment '主键',
                                       course_id bigint not null comment '课程id',
                                       video_id bigint not null comment '视频id',
                                       primary key (id),
                                       key idx_course(course_id),
                                       key idx_video(video_id)
)engine = InnoDB comment '课程-视频信息表';

create table jing_course_user_info(
                                         id bigint not null auto_increment comment '主键',
                                         course_id bigint not null comment '课程id',
                                         user_id bigint not null comment '学生id',
                                         roles varchar(20) comment '权限',
                                         primary key (id),
                                         unique key idx_course_video(course_id, user_id),
                                         key idx_course(course_id),
                                         key idx_video(user_id)
)engine = InnoDB comment '课程-用户信息表';

