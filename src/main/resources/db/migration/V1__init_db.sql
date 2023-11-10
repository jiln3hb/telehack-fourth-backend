create table measure_result (
    measure_id bigint not null,
    data varchar(255) not null,
    date_time timestamp(6) not null);

create sequence measure_id_seq start with 1 increment by 1;