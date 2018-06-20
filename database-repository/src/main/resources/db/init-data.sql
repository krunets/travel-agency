INSERT INTO travel_agency.country(c_name) VALUES('Belarus');
INSERT INTO travel_agency.country(c_name) VALUES('Usa');
INSERT INTO travel_agency.country(c_name) VALUES('France');
INSERT INTO travel_agency.country(c_name) VALUES('Italy');

INSERT INTO travel_agency.hotel(h_name, h_phone, h_stars, country) VALUES('Marriot', '123 23 23', 5, 1);
INSERT INTO travel_agency.hotel(h_name, h_phone, h_stars, country) VALUES('DoubleTree by Hilton', '232 12 12', 5, 3);
INSERT INTO travel_agency.hotel(h_name, h_phone, h_stars, country) VALUES('Prezident-Otel', '111 11 11', 4, 1);
INSERT INTO travel_agency.hotel(h_name, h_phone, h_stars, country) VALUES('Aqua-Minsk', '123 11 11', 2, 1);
INSERT INTO travel_agency.hotel(h_name, h_phone, h_stars, country) VALUES('Trump International Hotel Washington DC', '101 10 01', 5, 2);

INSERT INTO travel_agency."user"(login, password) VALUES('root', 'root');
INSERT INTO travel_agency."user"(login, password) VALUES('admin', 'admin');
INSERT INTO travel_agency."user"(login, password) VALUES('traveler1', 'traveler1');

INSERT INTO travel_agency.review(content, "user") VALUES('Content 1', 1);
INSERT INTO travel_agency.review(content, "user") VALUES('Content 2', 2);
INSERT INTO travel_agency.review(content, "user") VALUES('Content 3', 3);

INSERT INTO travel_agency.tour_type(t_type) VALUES('Adventure tourism');
INSERT INTO travel_agency.tour_type(t_type) VALUES('Atomic tourism');
INSERT INTO travel_agency.tour_type(t_type) VALUES('Bicycle tourism');
INSERT INTO travel_agency.tour_type(t_type) VALUES('Cultural tourism');
INSERT INTO travel_agency.tour_type(t_type) VALUES('Eco tourism');
INSERT INTO travel_agency.tour_type(t_type) VALUES('Geo tourism');
INSERT INTO travel_agency.tour_type(t_type) VALUES('Industrial tourism');


INSERT INTO travel_agency.tour(photo, date, duration, description, cost, tour_type) VALUES('photo/img1.png', '2018-07-17', 10, 'description1', 100, 1);
INSERT INTO travel_agency.tour(photo, date, duration, description, cost, tour_type) VALUES('photo/img2.png', '2018-07-20', 20, 'description2', 200, 2);
INSERT INTO travel_agency.tour(photo, date, duration, description, cost, tour_type) VALUES('photo/img3.png', '2018-07-25', 30, 'description3', 300, 3);
INSERT INTO travel_agency.tour(photo, date, duration, description, cost, tour_type) VALUES('photo/img4.png', '2018-07-30', 40, 'description4', 400, 4);
INSERT INTO travel_agency.tour(photo, date, duration, description, cost, tour_type) VALUES('photo/img5.png', '2018-08-05', 50, 'description5', 500, 5);

INSERT INTO travel_agency.tour_m2m_country(t_id, c_id) VALUES(1, 3);
INSERT INTO travel_agency.tour_m2m_country(t_id, c_id) VALUES(1, 4);
INSERT INTO travel_agency.tour_m2m_country(t_id, c_id) VALUES(2, 1);
INSERT INTO travel_agency.tour_m2m_country(t_id, c_id) VALUES(4, 2);
INSERT INTO travel_agency.tour_m2m_country(t_id, c_id) VALUES(3, 3);
INSERT INTO travel_agency.tour_m2m_country(t_id, c_id) VALUES(5, 1);

INSERT INTO travel_agency.tour_m2m_user(t_id, u_id) VALUES(1, 3);
INSERT INTO travel_agency.tour_m2m_user(t_id, u_id) VALUES(2, 3);
INSERT INTO travel_agency.tour_m2m_user(t_id, u_id) VALUES(2, 2)