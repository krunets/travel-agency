CREATE TABLE country
(
    c_id BIGINT PRIMARY KEY NOT NULL,
    c_name VARCHAR(2) NOT NULL
);
CREATE TABLE hotel
(
    h_id BIGINT PRIMARY KEY NOT NULL,
    h_name TEXT NOT NULL,
    h_phone TEXT NOT NULL,
    h_stars INTEGER NOT NULL,
    tour BIGINT,
    latitude DOUBLE PRECISION,
    longitude DOUBLE PRECISION
);
CREATE TABLE review
(
    r_id BIGINT PRIMARY KEY NOT NULL,
    content TEXT NOT NULL,
    user_id BIGINT,
    tour BIGINT NOT NULL
);
CREATE TABLE tour
(
    t_id BIGINT PRIMARY KEY NOT NULL,
    photo TEXT,
    date DATE NOT NULL,
    description TEXT,
    cost NUMERIC,
    tour_type INTEGER NOT NULL,
    duration BIGINT NOT NULL
);
CREATE TABLE tour_m2m_country
(
    t_id BIGINT NOT NULL,
    c_id BIGINT NOT NULL,
    CONSTRAINT tour_m2m_country_pk PRIMARY KEY (t_id, c_id)
);
CREATE TABLE tour_m2m_user
(
    t_id BIGINT NOT NULL,
    u_id BIGINT NOT NULL,
    CONSTRAINT tour_m2m_user_pk PRIMARY KEY (t_id, u_id)
);
CREATE TABLE tour_type
(
    t_id INTEGER PRIMARY KEY NOT NULL,
    t_type TEXT
);
CREATE TABLE "user"
(
    u_id BIGINT PRIMARY KEY NOT NULL,
    login TEXT NOT NULL,
    password TEXT NOT NULL,
    role TEXT NOT NULL,
    photo TEXT
);
CREATE UNIQUE INDEX country_c_name_uindex ON country (c_name);
ALTER TABLE hotel ADD FOREIGN KEY (tour) REFERENCES tour (t_id);
ALTER TABLE review ADD FOREIGN KEY (user_id) REFERENCES "user" (u_id);
ALTER TABLE review ADD FOREIGN KEY (tour) REFERENCES tour (t_id);
ALTER TABLE tour_m2m_country ADD FOREIGN KEY (t_id) REFERENCES tour (t_id);
ALTER TABLE tour_m2m_country ADD FOREIGN KEY (c_id) REFERENCES country (c_id);
ALTER TABLE tour_m2m_user ADD FOREIGN KEY (t_id) REFERENCES tour (t_id);
ALTER TABLE tour_m2m_user ADD FOREIGN KEY (u_id) REFERENCES "user" (u_id);
CREATE UNIQUE INDEX tour_type_t_type_uindex ON tour_type (t_type);
CREATE UNIQUE INDEX user_login_uindex ON "user" (login);