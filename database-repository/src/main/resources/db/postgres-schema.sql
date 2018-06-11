--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.9
-- Dumped by pg_dump version 9.6.9

-- Started on 2018-06-11 10:11:28

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 5 (class 2615 OID 16394)
-- Name: travel_agency; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA travel_agency;


ALTER SCHEMA travel_agency OWNER TO postgres;

--
-- TOC entry 1 (class 3079 OID 12387)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2202 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 186 (class 1259 OID 16395)
-- Name: country; Type: TABLE; Schema: travel_agency; Owner: postgres
--

CREATE TABLE travel_agency.country (
    c_id bigint NOT NULL,
    c_name text NOT NULL
);


ALTER TABLE travel_agency.country OWNER TO postgres;

--
-- TOC entry 194 (class 1259 OID 16500)
-- Name: country_c_id_seq; Type: SEQUENCE; Schema: travel_agency; Owner: postgres
--

CREATE SEQUENCE travel_agency.country_c_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE travel_agency.country_c_id_seq OWNER TO postgres;

--
-- TOC entry 2203 (class 0 OID 0)
-- Dependencies: 194
-- Name: country_c_id_seq; Type: SEQUENCE OWNED BY; Schema: travel_agency; Owner: postgres
--

ALTER SEQUENCE travel_agency.country_c_id_seq OWNED BY travel_agency.country.c_id;


--
-- TOC entry 187 (class 1259 OID 16400)
-- Name: hotel; Type: TABLE; Schema: travel_agency; Owner: postgres
--

CREATE TABLE travel_agency.hotel (
    h_id bigint NOT NULL,
    h_name text NOT NULL,
    h_phone text NOT NULL,
    h_stars integer NOT NULL,
    country bigint NOT NULL
);


ALTER TABLE travel_agency.hotel OWNER TO postgres;

--
-- TOC entry 195 (class 1259 OID 16503)
-- Name: hotel_h_id_seq; Type: SEQUENCE; Schema: travel_agency; Owner: postgres
--

CREATE SEQUENCE travel_agency.hotel_h_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE travel_agency.hotel_h_id_seq OWNER TO postgres;

--
-- TOC entry 2204 (class 0 OID 0)
-- Dependencies: 195
-- Name: hotel_h_id_seq; Type: SEQUENCE OWNED BY; Schema: travel_agency; Owner: postgres
--

ALTER SEQUENCE travel_agency.hotel_h_id_seq OWNED BY travel_agency.hotel.h_id;


--
-- TOC entry 188 (class 1259 OID 16408)
-- Name: review; Type: TABLE; Schema: travel_agency; Owner: postgres
--

CREATE TABLE travel_agency.review (
    r_id bigint NOT NULL,
    content text NOT NULL,
    "user" bigint
);


ALTER TABLE travel_agency.review OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 16506)
-- Name: review_r_id_seq; Type: SEQUENCE; Schema: travel_agency; Owner: postgres
--

CREATE SEQUENCE travel_agency.review_r_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE travel_agency.review_r_id_seq OWNER TO postgres;

--
-- TOC entry 2205 (class 0 OID 0)
-- Dependencies: 196
-- Name: review_r_id_seq; Type: SEQUENCE OWNED BY; Schema: travel_agency; Owner: postgres
--

ALTER SEQUENCE travel_agency.review_r_id_seq OWNED BY travel_agency.review.r_id;


--
-- TOC entry 189 (class 1259 OID 16413)
-- Name: tour; Type: TABLE; Schema: travel_agency; Owner: postgres
--

CREATE TABLE travel_agency.tour (
    t_id bigint NOT NULL,
    photo text,
    date date NOT NULL,
    description text,
    cost numeric,
    tour_type bigint NOT NULL,
    duration integer NOT NULL
);


ALTER TABLE travel_agency.tour OWNER TO postgres;

--
-- TOC entry 192 (class 1259 OID 16443)
-- Name: tour_m2m_country; Type: TABLE; Schema: travel_agency; Owner: postgres
--

CREATE TABLE travel_agency.tour_m2m_country (
    t_id bigint NOT NULL,
    c_id bigint NOT NULL
);


ALTER TABLE travel_agency.tour_m2m_country OWNER TO postgres;

--
-- TOC entry 193 (class 1259 OID 16458)
-- Name: tour_m2m_user; Type: TABLE; Schema: travel_agency; Owner: postgres
--

CREATE TABLE travel_agency.tour_m2m_user (
    t_id bigint NOT NULL,
    u_id bigint NOT NULL
);


ALTER TABLE travel_agency.tour_m2m_user OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 16509)
-- Name: tour_t_id_seq; Type: SEQUENCE; Schema: travel_agency; Owner: postgres
--

CREATE SEQUENCE travel_agency.tour_t_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE travel_agency.tour_t_id_seq OWNER TO postgres;

--
-- TOC entry 2206 (class 0 OID 0)
-- Dependencies: 197
-- Name: tour_t_id_seq; Type: SEQUENCE OWNED BY; Schema: travel_agency; Owner: postgres
--

ALTER SEQUENCE travel_agency.tour_t_id_seq OWNED BY travel_agency.tour.t_id;


--
-- TOC entry 190 (class 1259 OID 16427)
-- Name: tour_type; Type: TABLE; Schema: travel_agency; Owner: postgres
--

CREATE TABLE travel_agency.tour_type (
    t_id bigint NOT NULL,
    t_type text
);


ALTER TABLE travel_agency.tour_type OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 16512)
-- Name: tour_type_t_id_seq; Type: SEQUENCE; Schema: travel_agency; Owner: postgres
--

CREATE SEQUENCE travel_agency.tour_type_t_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE travel_agency.tour_type_t_id_seq OWNER TO postgres;

--
-- TOC entry 2207 (class 0 OID 0)
-- Dependencies: 198
-- Name: tour_type_t_id_seq; Type: SEQUENCE OWNED BY; Schema: travel_agency; Owner: postgres
--

ALTER SEQUENCE travel_agency.tour_type_t_id_seq OWNED BY travel_agency.tour_type.t_id;


--
-- TOC entry 191 (class 1259 OID 16435)
-- Name: user; Type: TABLE; Schema: travel_agency; Owner: postgres
--

CREATE TABLE travel_agency."user" (
    u_id bigint NOT NULL,
    login text NOT NULL,
    password text NOT NULL
);


ALTER TABLE travel_agency."user" OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 16515)
-- Name: user_u_id_seq; Type: SEQUENCE; Schema: travel_agency; Owner: postgres
--

CREATE SEQUENCE travel_agency.user_u_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE travel_agency.user_u_id_seq OWNER TO postgres;

--
-- TOC entry 2208 (class 0 OID 0)
-- Dependencies: 199
-- Name: user_u_id_seq; Type: SEQUENCE OWNED BY; Schema: travel_agency; Owner: postgres
--

ALTER SEQUENCE travel_agency.user_u_id_seq OWNED BY travel_agency."user".u_id;


--
-- TOC entry 2046 (class 2604 OID 16502)
-- Name: country c_id; Type: DEFAULT; Schema: travel_agency; Owner: postgres
--

ALTER TABLE ONLY travel_agency.country ALTER COLUMN c_id SET DEFAULT nextval('travel_agency.country_c_id_seq'::regclass);


--
-- TOC entry 2047 (class 2604 OID 16505)
-- Name: hotel h_id; Type: DEFAULT; Schema: travel_agency; Owner: postgres
--

ALTER TABLE ONLY travel_agency.hotel ALTER COLUMN h_id SET DEFAULT nextval('travel_agency.hotel_h_id_seq'::regclass);


--
-- TOC entry 2048 (class 2604 OID 16508)
-- Name: review r_id; Type: DEFAULT; Schema: travel_agency; Owner: postgres
--

ALTER TABLE ONLY travel_agency.review ALTER COLUMN r_id SET DEFAULT nextval('travel_agency.review_r_id_seq'::regclass);


--
-- TOC entry 2049 (class 2604 OID 16511)
-- Name: tour t_id; Type: DEFAULT; Schema: travel_agency; Owner: postgres
--

ALTER TABLE ONLY travel_agency.tour ALTER COLUMN t_id SET DEFAULT nextval('travel_agency.tour_t_id_seq'::regclass);


--
-- TOC entry 2050 (class 2604 OID 16514)
-- Name: tour_type t_id; Type: DEFAULT; Schema: travel_agency; Owner: postgres
--

ALTER TABLE ONLY travel_agency.tour_type ALTER COLUMN t_id SET DEFAULT nextval('travel_agency.tour_type_t_id_seq'::regclass);


--
-- TOC entry 2051 (class 2604 OID 16517)
-- Name: user u_id; Type: DEFAULT; Schema: travel_agency; Owner: postgres
--

ALTER TABLE ONLY travel_agency."user" ALTER COLUMN u_id SET DEFAULT nextval('travel_agency.user_u_id_seq'::regclass);


--
-- TOC entry 2054 (class 2606 OID 16399)
-- Name: country country_pkey; Type: CONSTRAINT; Schema: travel_agency; Owner: postgres
--

ALTER TABLE ONLY travel_agency.country
    ADD CONSTRAINT country_pkey PRIMARY KEY (c_id);


--
-- TOC entry 2056 (class 2606 OID 16407)
-- Name: hotel hotel_pkey; Type: CONSTRAINT; Schema: travel_agency; Owner: postgres
--

ALTER TABLE ONLY travel_agency.hotel
    ADD CONSTRAINT hotel_pkey PRIMARY KEY (h_id);


--
-- TOC entry 2058 (class 2606 OID 16412)
-- Name: review review_pkey; Type: CONSTRAINT; Schema: travel_agency; Owner: postgres
--

ALTER TABLE ONLY travel_agency.review
    ADD CONSTRAINT review_pkey PRIMARY KEY (r_id);


--
-- TOC entry 2068 (class 2606 OID 16474)
-- Name: tour_m2m_country tour_m2m_country_pk; Type: CONSTRAINT; Schema: travel_agency; Owner: postgres
--

ALTER TABLE ONLY travel_agency.tour_m2m_country
    ADD CONSTRAINT tour_m2m_country_pk PRIMARY KEY (t_id, c_id);


--
-- TOC entry 2070 (class 2606 OID 16476)
-- Name: tour_m2m_user tour_m2m_user_pk; Type: CONSTRAINT; Schema: travel_agency; Owner: postgres
--

ALTER TABLE ONLY travel_agency.tour_m2m_user
    ADD CONSTRAINT tour_m2m_user_pk PRIMARY KEY (t_id, u_id);


--
-- TOC entry 2060 (class 2606 OID 16420)
-- Name: tour tour_pkey; Type: CONSTRAINT; Schema: travel_agency; Owner: postgres
--

ALTER TABLE ONLY travel_agency.tour
    ADD CONSTRAINT tour_pkey PRIMARY KEY (t_id);


--
-- TOC entry 2062 (class 2606 OID 16434)
-- Name: tour_type tour_type_pkey; Type: CONSTRAINT; Schema: travel_agency; Owner: postgres
--

ALTER TABLE ONLY travel_agency.tour_type
    ADD CONSTRAINT tour_type_pkey PRIMARY KEY (t_id);


--
-- TOC entry 2066 (class 2606 OID 16442)
-- Name: user user_pkey; Type: CONSTRAINT; Schema: travel_agency; Owner: postgres
--

ALTER TABLE ONLY travel_agency."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (u_id);


--
-- TOC entry 2052 (class 1259 OID 16492)
-- Name: country_c_name_uindex; Type: INDEX; Schema: travel_agency; Owner: postgres
--

CREATE UNIQUE INDEX country_c_name_uindex ON travel_agency.country USING btree (c_name);


--
-- TOC entry 2063 (class 1259 OID 16493)
-- Name: tour_type_t_type_uindex; Type: INDEX; Schema: travel_agency; Owner: postgres
--

CREATE UNIQUE INDEX tour_type_t_type_uindex ON travel_agency.tour_type USING btree (t_type);


--
-- TOC entry 2064 (class 1259 OID 16494)
-- Name: user_login_uindex; Type: INDEX; Schema: travel_agency; Owner: postgres
--

CREATE UNIQUE INDEX user_login_uindex ON travel_agency."user" USING btree (login);


--
-- TOC entry 2071 (class 2606 OID 16482)
-- Name: hotel hotel_country_c_id_fk; Type: FK CONSTRAINT; Schema: travel_agency; Owner: postgres
--

ALTER TABLE ONLY travel_agency.hotel
    ADD CONSTRAINT hotel_country_c_id_fk FOREIGN KEY (country) REFERENCES travel_agency.country(c_id);


--
-- TOC entry 2072 (class 2606 OID 16495)
-- Name: review review_user_u_id_fk; Type: FK CONSTRAINT; Schema: travel_agency; Owner: postgres
--

ALTER TABLE ONLY travel_agency.review
    ADD CONSTRAINT review_user_u_id_fk FOREIGN KEY ("user") REFERENCES travel_agency."user"(u_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2075 (class 2606 OID 16453)
-- Name: tour_m2m_country tour_m2m_country_country_c_id_fk; Type: FK CONSTRAINT; Schema: travel_agency; Owner: postgres
--

ALTER TABLE ONLY travel_agency.tour_m2m_country
    ADD CONSTRAINT tour_m2m_country_country_c_id_fk FOREIGN KEY (c_id) REFERENCES travel_agency.country(c_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2074 (class 2606 OID 16448)
-- Name: tour_m2m_country tour_m2m_country_tour_t_id_fk; Type: FK CONSTRAINT; Schema: travel_agency; Owner: postgres
--

ALTER TABLE ONLY travel_agency.tour_m2m_country
    ADD CONSTRAINT tour_m2m_country_tour_t_id_fk FOREIGN KEY (t_id) REFERENCES travel_agency.tour(t_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2076 (class 2606 OID 16463)
-- Name: tour_m2m_user tour_m2m_user_tour_t_id_fk; Type: FK CONSTRAINT; Schema: travel_agency; Owner: postgres
--

ALTER TABLE ONLY travel_agency.tour_m2m_user
    ADD CONSTRAINT tour_m2m_user_tour_t_id_fk FOREIGN KEY (t_id) REFERENCES travel_agency.tour(t_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2077 (class 2606 OID 16468)
-- Name: tour_m2m_user tour_m2m_user_user_u_id_fk; Type: FK CONSTRAINT; Schema: travel_agency; Owner: postgres
--

ALTER TABLE ONLY travel_agency.tour_m2m_user
    ADD CONSTRAINT tour_m2m_user_user_u_id_fk FOREIGN KEY (u_id) REFERENCES travel_agency."user"(u_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2073 (class 2606 OID 16518)
-- Name: tour tour_tour_type_t_id_fk; Type: FK CONSTRAINT; Schema: travel_agency; Owner: postgres
--

ALTER TABLE ONLY travel_agency.tour
    ADD CONSTRAINT tour_tour_type_t_id_fk FOREIGN KEY (tour_type) REFERENCES travel_agency.tour_type(t_id);


-- Completed on 2018-06-11 10:11:28

--
-- PostgreSQL database dump complete
--

