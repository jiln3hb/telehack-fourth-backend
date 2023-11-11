create table measure (
    measure_id bigint not null,
    federal_district varchar(255) not null,
    place_of_measure varchar(255) not null,
    start_date date not null,
    end_date date not null,
    primary key(measure_id));

create table background_information (
    background_information_id bigint not null,
    measure_id bigint not null,
    quantity_of_voice_connections int not null,
    quantity_of_voice_seq int not null,
    quantity_of_voice_connections_with_low_intelligibility int not null,
    quantity_of_sent_sms int not null,
    quantity_of_connecting_to_http_server_attempts int not null,
    quantity_of_http_sessions int not null,
    operator varchar(255),
    primary key (background_information_id),
    foreign key (measure_id) references measure(measure_id));

create table quality_of_text (
    quality_of_text_id bigint not null,
    measure_id bigint not null,
    undelivered_sms_rate real not null,
    avg_sms_delivery_time real not null,
    operator varchar(255),
    primary key (quality_of_text_id),
    foreign key (measure_id) references measure(measure_id));

create table quality_of_voice (
    quality_of_voice_id bigint not null,
    measure_id bigint not null,
    failed_attempts_to_establish_voice_connection_rate real not null,
    failure_voice_connections_rate real not null,
    avg_speech_quality real not null,
    voice_connections_with_low_speech_quality_rate real not null,
    operator varchar(255),
    primary key (quality_of_voice_id),
    foreign key (measure_id) references measure(measure_id));

create table quality_of_dt (
    quality_of_dt_id bigint not null,
    measure_id bigint not null,
    failed_http_sessions_rate real not null,
    avg_dt_speed_from_client real not null,
    avg_dt_speed_to_client real not null,
    http_session_time real not null,
    operator varchar(255),
    primary key (quality_of_dt_id),
    foreign key (measure_id) references measure(measure_id));

create sequence measure_id_seq start with 1 increment by 1;
create sequence background_id_seq start with 1 increment by 1;
create sequence quality_of_voice_id_seq start with 1 increment by 1;
create sequence quality_of_dt_id_seq start with 1 increment by 1;
create sequence quality_of_text_id_seq start with 1 increment by 1;