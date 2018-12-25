create table if not exists travel_agency.country
(
	c_id bigint not null
		constraint country_pkey
			primary key,
	c_name varchar(2) not null
)
;

create unique index if not exists country_c_name_uindex
	on travel_agency.country (c_name)
;

create table if not exists travel_agency.tour
(
	t_id bigint default nextval('travel_agency.tour_sequence'::regclass) not null
		constraint tour_pkey
			primary key,
	photo text,
	date date not null,
	description text,
	cost numeric,
	tour_type integer not null,
	duration bigint not null
)
;

create table if not exists travel_agency.hotel
(
	h_id bigint default nextval('travel_agency.hotel_sequence'::regclass) not null
		constraint hotel_pkey
			primary key,
	h_name text not null,
	h_phone text not null,
	h_stars integer not null,
	tour bigint
		constraint hotel_tour_fkey
			references tour,
	latitude double precision,
	longitude double precision
)
;

create table if not exists travel_agency.tour_m2m_country
(
	t_id bigint not null
		constraint tour_m2m_country_t_id_fkey
			references tour,
	c_id bigint not null
		constraint tour_m2m_country_c_id_fkey
			references country,
	constraint tour_m2m_country_pk
		primary key (t_id, c_id)
)
;

create table if not exists travel_agency.tour_type
(
	t_id integer not null
		constraint tour_type_pkey
			primary key,
	t_type text
)
;

create unique index if not exists tour_type_t_type_uindex
	on travel_agency.tour_type (t_type)
;

create table if not exists travel_agency."user"
(
	u_id bigint default nextval('travel_agency.user_sequence'::regclass) not null
		constraint user_pkey
			primary key,
	login text not null,
	password text not null,
	role text not null,
	photo text
)
;

create table if not exists travel_agency.review
(
	r_id bigint default nextval('travel_agency.review_sequence'::regclass) not null
		constraint review_pkey
			primary key,
	content text not null,
	user_id bigint
		constraint review_user_id_fkey
			references "user",
	tour bigint not null
		constraint review_tour_fkey
			references tour,
	rating integer not null
)
;

create table if not exists travel_agency.hotel_m2m_user
(
	h_id bigint not null
		constraint hotel_m2m_user_hotel_fk_c
			references hotel,
	u_id bigint not null
		constraint hotel_m2m_user_user_fk_c
			references "user",
	constraint hotel_m2m_user_h_id_u_id_pk
		primary key (h_id, u_id)
)
;

create index if not exists fki_hotel_m2m_user_user
	on travel_agency.hotel_m2m_user (u_id)
;

create unique index if not exists user_login_uindex
	on travel_agency."user" (login)
;

create table if not exists travel_agency.room
(
	room_id bigserial not null
		constraint room_pkey
			primary key,
	beds integer not null,
	hotel bigint not null
		constraint room_hotel_fk
			references hotel,
	user_id bigint
		constraint room_user_fk_c
			references "user"
)
;

create unique index if not exists room_r_id_uindex
	on travel_agency.room (room_id)
;

create index if not exists fki_room_hotel_fk
	on travel_agency.room (hotel)
;

create index if not exists fki_room_user_fk_c
	on travel_agency.room (user_id)
;

create table if not exists travel_agency.transfer_type
(
	tt_id bigserial not null
		constraint transfer_type_tt_id_pk
			primary key
		constraint transfer_type_tour_fk
			references tour,
	description text not null,
	price_coefficient double precision not null,
	code text not null
)
;

create unique index if not exists transfer_type_tt_id_uindex
	on travel_agency.transfer_type (tt_id)
;

create table if not exists travel_agency.transfertype_m2m_tour
(
	tt_id bigint not null
		constraint transfertype_m2m_tt_id_fk
			references transfer_type,
	t_id bigint not null
		constraint tourtype_m2m_tour_fk
			references tour,
	constraint trasfertype_m2m_tour_t_id_tt_id_pk
		primary key (t_id, tt_id)
)
;

create index if not exists fki_transfertype_m2m_tt_id_fk
	on travel_agency.transfertype_m2m_tour (tt_id)
;

-- we don't know how to generate schema travel_agency (class Schema) :(
create sequence room_r_id_seq
;

create sequence transfer_type_tt_id_seq
;

create sequence tour_t_id_seq
;

create sequence hotel_h_id_seq
;

create sequence review_sequence
;

create sequence tour_sequence
;

create sequence hotel_sequence
;

create sequence room_sequence
;

create sequence transfer_type_sequence
;

create sequence user_sequence
;


