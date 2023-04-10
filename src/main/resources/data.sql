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


-- set @user_id1 = 'a9a1c056-7b17-4f61-b27b-6cd9e9e70fd3';
-- set @user_id2 = '63912dd1-71fb-40bc-ab1f-394b96b93ad8';

INSERT INTO users (id, name, created_by, created_time, updated_by, updated_time)
VALUES (
           CAST(X'A9A1C0567B174F61B27B6CD9E9E70FD3' AS BINARY(16)),
           'user1',
           1,
           NOW(),
           1,
           NOW()
       );

INSERT INTO users (id, name, created_by, created_time, updated_by, updated_time)
VALUES (
           CAST(X'63912dd171fb40bcab1f394b96b93ad8' AS BINARY(16)),
           'user2',
           1,
           NOW(),
           1,
           NOW()
       );

-- set @place_id1 = 'a542e928-30e4-4256-86c5-e18cfb78b385';
-- set @place_id2 = '3b7d5e34-9176-45e9-b03a-7a465be2a270';
-- set @place_id3 = 'd636444e-a229-43a4-bb19-88132a1ac29c';
-- set @place_id4 = 'a0a64c75-eea9-4d51-919a-ce82a2b8dd2c';

insert into place (id, name, created_by, created_time, updated_by, updated_time)
values (
        CAST(X'a542e92830e4425686c5e18cfb78b385' AS BINARY(16)), '장소1', 1, now(), 1, now());

insert into place (id, name, created_by, created_time, updated_by, updated_time)
values (CAST(X'3b7d5e34917645e9b03a7a465be2a270' AS BINARY(16)), '장소2', 1, now(), 1, now());

insert into place (id, name, created_by, created_time, updated_by, updated_time)
values (CAST(X'd636444ea22943a4bb1988132a1ac29c' AS BINARY(16)), '장소3', 1, now(), 1, now());

insert into place (id, name, created_by, created_time, updated_by, updated_time)
values (CAST(X'a0a64c75eea94d51919ace82a2b8dd2c' AS BINARY(16)), '장소4', 1, now(), 1, now());



-- set @photo_id1 = 'b546cc36-f713-45e2-8101-2a9831831301';
-- set @photo_id2 = 'afbd7de1-b3e8-45e5-bff6-b7f34842ac1c';
-- set @photo_id3 = 'f030ed2a-447a-40df-98d9-d7a06d4d32b2';
-- set @photo_id4 = '43b4c3db-c982-40cc-aa4b-4f3fac3e86da';

insert into photo (id, url, created_by, created_time, updated_by, updated_time)
values (CAST(X'b546cc36f71345e281012a9831831301' AS BINARY(16)),'url1',1,now(),1,now());

insert into photo (id, url, created_by, created_time, updated_by, updated_time)
values (CAST(X'afbd7de1b3e845e5bff6b7f34842ac1c' AS BINARY(16)),'url2',1,now(),1,now());

insert into photo (id, url, created_by, created_time, updated_by, updated_time)
values (CAST(X'f030ed2a447a40df98d9d7a06d4d32b2' AS BINARY(16)),'url3',1,now(),1,now());

insert into photo (id, url, created_by, created_time, updated_by, updated_time)
values (CAST(X'43b4c3dbc98240ccaa4b4f3fac3e86da' AS BINARY(16)),'url4',1,now(),1,now());

