CREATE SCHEMA IF NOT EXISTS travel_agency;

CREATE TABLE IF NOT EXISTS country
(
  c_id BIGINT PRIMARY KEY auto_increment NOT NULL,
  c_name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS hotel
(
  h_id BIGINT PRIMARY KEY auto_increment NOT NULL,
  h_name TEXT NOT NULL,
  h_phone TEXT NOT NULL,
  h_stars INTEGER NOT NULL,
  country BIGINT NOT NULL
);
CREATE TABLE IF NOT EXISTS review
(
  r_id BIGINT PRIMARY KEY auto_increment NOT NULL,
  content TEXT NOT NULL,
  "user" BIGINT
);
CREATE TABLE IF NOT EXISTS tour
(
  t_id BIGINT PRIMARY KEY auto_increment NOT NULL,
  photo TEXT,
  date DATE NOT NULL,
  description TEXT,
  cost NUMERIC,
  tour_type BIGINT NOT NULL,
  duration INTEGER NOT NULL
);
CREATE TABLE IF NOT EXISTS tour_m2m_country
(
  t_id BIGINT NOT NULL,
  c_id BIGINT NOT NULL,
  CONSTRAINT tour_m2m_country_pk PRIMARY KEY (t_id, c_id)
);
CREATE TABLE IF NOT EXISTS tour_m2m_user
(
  t_id BIGINT NOT NULL,
  u_id BIGINT NOT NULL,
  CONSTRAINT tour_m2m_user_pk PRIMARY KEY (t_id, u_id)
);
CREATE TABLE IF NOT EXISTS tour_type
(
  t_id BIGINT PRIMARY KEY auto_increment NOT NULL,
  t_type TEXT
);
CREATE TABLE IF NOT EXISTS user
(
  u_id BIGINT  PRIMARY KEY auto_increment NOT NULL,
  login TEXT NOT NULL,
  password TEXT NOT NULL
);
ALTER TABLE hotel ADD FOREIGN KEY (country) REFERENCES country (c_id);
ALTER TABLE review ADD FOREIGN KEY ("user") REFERENCES user (u_id);
ALTER TABLE tour ADD FOREIGN KEY (tour_type) REFERENCES tour_type (t_id);
ALTER TABLE tour_m2m_country ADD FOREIGN KEY (t_id) REFERENCES tour (t_id);
ALTER TABLE tour_m2m_country ADD FOREIGN KEY (c_id) REFERENCES country (c_id);
ALTER TABLE tour_m2m_user ADD FOREIGN KEY (t_id) REFERENCES tour (t_id);
ALTER TABLE tour_m2m_user ADD FOREIGN KEY (u_id) REFERENCES user (u_id);


