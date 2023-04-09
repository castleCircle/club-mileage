use club;

create table users
(
    id         binary(16)   NOT NULL,
    name       varchar(255) NOT NULL COMMENT '이름',
    created_by bigint       NOT NULL,
    created_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_by bigint       NOT NULL,
    updated_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    primary key (id)
) engine = InnoDB;

create table place
(
    id         binary(16)   NOT NULL,
    name       varchar(255) NOT NULL COMMENT '장소명',
    created_by bigint       NOT NULL,
    created_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_by bigint       NOT NULL,
    updated_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    primary key (id)
) engine = InnoDB;


create table review
(
    id         binary(16)   NOT NULL,
    user_id    binary(16)   NOT NULL COMMENT '사용자 Id',
    place_id   binary(16)   NOT NULL COMMENT '장소 Id',
    content    varchar(255) COMMENT '내용',
    created_by bigint       NOT NULL,
    created_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_by bigint       NOT NULL,
    updated_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    primary key (id)
) engine = InnoDB;

alter table review
    add constraint review_uk_01 unique (user_id, place_id);

alter table review
    add constraint review_fk_01
        foreign key (user_id) references users (id);

alter table review
    add constraint review_fk_02
        foreign key (place_id) references place (id);


create table photo
(
    id         binary(16)   NOT NULL COMMENT 'photo Id',
    review_id  binary(16)   COMMENT '리뷰 Id',
    url        varchar(255) NOT NULL COMMENT '이미지 url',
    created_by bigint       NOT NULL,
    created_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_by bigint       NOT NULL,
    updated_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    primary key (id)
) engine = InnoDB;

alter table photo
    add constraint photo_fk_01
        foreign key (review_id) references review (id)
            on delete cascade;


create table point
(
    id         binary(16) NOT NULL COMMENT 'point Id',
    user_id    binary(16) NOT NULL COMMENT '사용자 Id',
    review_id  binary(16) COMMENT '리뷰 Id',
    type        varchar(255) NOT NULL COMMENT '리뷰/..',
    status     bit       DEFAULT 1 COMMENT '활성화여부',
    amount     bigint     NOT NULL,
    created_by bigint     NOT NULL,
    created_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_by bigint     NOT NULL,
    updated_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    primary key (id)
) engine = InnoDB;

alter table point
    add constraint point_fk_01
        foreign key (review_id) references review (id)
            on delete set null;

alter table point
    add constraint point_fk_02
        foreign key (user_id) references users (id);



set @user_id1 = uuid_to_bin('a9a1c056-7b17-4f61-b27b-6cd9e9e70fd3');
set @user_id2 = uuid_to_bin('63912dd1-71fb-40bc-ab1f-394b96b93ad8');
set @user_id3 = uuid_to_bin('94a855a4-af14-4a53-9a43-48398aa3fc31');

insert into users (id, name, created_by, created_time, updated_by, updated_time)
values (@user_id1,"user1",1,now(),1,now());

insert into users (id, name, created_by, created_time, updated_by, updated_time)
values (@user_id2,"user2",1,now(),1,now());

insert into users (id, name, created_by, created_time, updated_by, updated_time)
values (@user_id3,"user3",1,now(),1,now());


set @place_id1 = uuid_to_bin('a542e928-30e4-4256-86c5-e18cfb78b385');
set @place_id2 = uuid_to_bin('3b7d5e34-9176-45e9-b03a-7a465be2a270');
set @place_id3 = uuid_to_bin('d636444e-a229-43a4-bb19-88132a1ac29c');
set @place_id4 = uuid_to_bin('a0a64c75-eea9-4d51-919a-ce82a2b8dd2c');

insert into place (id, name, created_by, created_time, updated_by, updated_time)
values (@place_id1,"장소1",1,now(),1,now());

insert into place (id, name, created_by, created_time, updated_by, updated_time)
values (@place_id2,"장소2",1,now(),1,now());

insert into place (id, name, created_by, created_time, updated_by, updated_time)
values (@place_id3,"장소3",1,now(),1,now());

insert into place (id, name, created_by, created_time, updated_by, updated_time)
values (@place_id4,"장소4",1,now(),1,now());


set @photo_id1 = uuid_to_bin('b546cc36-f713-45e2-8101-2a9831831301');
set @photo_id2 = uuid_to_bin('afbd7de1-b3e8-45e5-bff6-b7f34842ac1c');
set @photo_id3 = uuid_to_bin('f030ed2a-447a-40df-98d9-d7a06d4d32b2');
set @photo_id4 = uuid_to_bin('43b4c3db-c982-40cc-aa4b-4f3fac3e86da');
set @photo_id5 = uuid_to_bin('474cde50-90c0-4453-8033-49c7f95b9d86');
set @photo_id6 = uuid_to_bin('b9055f5a-3681-4f3c-b497-b7133f8a748d');

insert into photo (id, url, created_by, created_time, updated_by, updated_time)
values (@photo_id1,"url1",1,now(),1,now());

insert into photo (id, url, created_by, created_time, updated_by, updated_time)
values (@photo_id2,"url2",1,now(),1,now());

insert into photo (id, url, created_by, created_time, updated_by, updated_time)
values (@photo_id3,"url3",1,now(),1,now());

insert into photo (id, url, created_by, created_time, updated_by, updated_time)
values (@photo_id4,"url4",1,now(),1,now());

insert into photo (id, url, created_by, created_time, updated_by, updated_time)
values (@photo_id5,"url5",1,now(),1,now());

insert into photo (id, url, created_by, created_time, updated_by, updated_time)
values (@photo_id6,"url6",1,now(),1,now());