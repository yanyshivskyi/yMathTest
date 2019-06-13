create table hibernate_sequence (next_val bigint primary key) engine=MyISAM;

insert into hibernate_sequence values ( 1 );

create table answer (
    id bigint not null,
    answer_text varchar(255),
    conformity varchar(255),
    is_correct bit,
    id_question bigint,
    primary key (id))  engine=MyISAM;

create table question (
    id bigint not null,
    count_point float, filename1 varchar(255),
    filename2 varchar(255),
    question_text varchar(255),
    type varchar(255),
    id_test bigint,
    primary key (id)) engine=MyISAM;

create table result (
    id bigint not null,
    my_date datetime,
    number_try integer,
    point float,
    id_test bigint,
    id_user bigint,
    primary key (id)) engine=MyISAM;

create table test (
    id bigint not null,
    count_try integer,
    description varchar(255),
    name varchar(255),
    time varchar(255),
    primary key (id)) engine=MyISAM;

create table user_role (
    user_id bigint not null,
    roles varchar(255)) engine=MyISAM;

create table usr (
    id bigint not null,
    active bit not null,
    password varchar(255),
    username varchar(255),
    groupst varchar(255),
    stfio varchar(255),
    primary key (id)) engine=MyISAM;

alter table answer add constraint id_quest_fk foreign key (id_question) references question (id);

alter table question add constraint id_test_fk foreign key (id_test) references test (id);

alter table result add constraint id_test_fk foreign key (id_test) references test (id);

alter table result add constraint id_user_fk foreign key (id_user) references usr (id);

alter table user_role add constraint user_id_fk foreign key (user_id) references usr (id);