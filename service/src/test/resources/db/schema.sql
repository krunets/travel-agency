CREATE SCHEMA IF NOT EXISTS travel_agency;

CREATE TABLE travel_agency.country
(
  c_id bigint not null check (c_id>=0),
  c_name varchar(2) not null,
  primary key (c_id)
);
CREATE TABLE travel_agency.hotel
(
  h_id BIGINT PRIMARY KEY auto_increment NOT NULL,
  h_name varchar(255) NOT NULL,
  h_phone varchar(255) NOT NULL,
  h_stars INTEGER NOT NULL,
  tour BIGINT NOT NULL
);
CREATE TABLE travel_agency.review
(
  r_id BIGINT PRIMARY KEY auto_increment NOT NULL,
  content varchar(255) NOT NULL,
  user_id BIGINT,
  tour BIGINT
);

CREATE TABLE travel_agency.tour
(
  t_id BIGINT PRIMARY KEY auto_increment NOT NULL,
  photo varchar(255),
  date DATE NOT NULL,
  description varchar(255),
  cost NUMERIC,
  tour_type INTEGER NOT NULL,
  duration BIGINT NOT NULL
);
CREATE TABLE travel_agency.tour_m2m_country
(
  t_id BIGINT NOT NULL,
  c_id BIGINT NOT NULL,
  CONSTRAINT tour_m2m_country_pk PRIMARY KEY (t_id, c_id)
);
CREATE TABLE travel_agency.tour_m2m_user
(
  t_id BIGINT NOT NULL,
  u_id BIGINT NOT NULL,
  CONSTRAINT tour_m2m_user_pk PRIMARY KEY (t_id, u_id)
);
CREATE TABLE travel_agency.tour_type
(
  t_id INTEGER PRIMARY KEY auto_increment NOT NULL,
  t_type varchar(255)
);
CREATE TABLE travel_agency.user
(
  u_id BIGINT  PRIMARY KEY auto_increment NOT NULL,
  login varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  role  varchar(255) NOT NULL,
  photo varchar(255),
);
ALTER TABLE travel_agency.hotel ADD FOREIGN KEY (tour) REFERENCES travel_agency.tour (t_id);
ALTER TABLE travel_agency.review ADD FOREIGN KEY (user_id) REFERENCES travel_agency.user (u_id);
ALTER TABLE travel_agency.review ADD FOREIGN KEY (tour) REFERENCES tour (t_id);
ALTER TABLE travel_agency.tour ADD FOREIGN KEY (tour_type) REFERENCES travel_agency.tour_type (t_id);
ALTER TABLE travel_agency.tour_m2m_country ADD FOREIGN KEY (t_id) REFERENCES travel_agency.tour (t_id);
ALTER TABLE travel_agency.tour_m2m_country ADD FOREIGN KEY (c_id) REFERENCES travel_agency.country (c_id);
ALTER TABLE travel_agency.tour_m2m_user ADD FOREIGN KEY (t_id) REFERENCES travel_agency.tour (t_id);
ALTER TABLE travel_agency.tour_m2m_user ADD FOREIGN KEY (u_id) REFERENCES travel_agency.user (u_id);

CREATE SEQUENCE HIBERNATE_SEQUENCE START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE COUNTRY_SEQUENCE START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE review_sequence START WITH 1 INCREMENT BY 1;

