INSERT INTO travel_agency.country(c_id, c_name) VALUES(1, 'BY');
INSERT INTO travel_agency.country(c_id, c_name) VALUES(2, 'US');
INSERT INTO travel_agency.country(c_id, c_name) VALUES(3, 'FR');
INSERT INTO travel_agency.country(c_id, c_name) VALUES(4, 'IT');

INSERT INTO travel_agency.tour_type(t_id, t_type) VALUES(1,'Adventure tourism');
INSERT INTO travel_agency.tour_type(t_id,t_type) VALUES(2,'Atomic tourism');
INSERT INTO travel_agency.tour_type(t_id,t_type) VALUES(3,'Bicycle tourism');
INSERT INTO travel_agency.tour_type(t_id,t_type) VALUES(4,'Cultural tourism');
INSERT INTO travel_agency.tour_type(t_id,t_type) VALUES(5,'Eco tourism');
INSERT INTO travel_agency.tour_type(t_id,t_type) VALUES(6,'Geo tourism');
INSERT INTO travel_agency.tour_type(t_id,t_type) VALUES(7,'Industrial tourism');

INSERT INTO travel_agency.tour(t_id,photo, date, duration, description, cost, tour_type) VALUES(1,'photo/img1.png', '2018-07-17', 10, 'description1', 100, 1);
INSERT INTO travel_agency.tour(t_id,photo, date, duration, description, cost, tour_type) VALUES(2,'photo/img2.png', '2018-07-20', 20, 'description2', 200, 2);
INSERT INTO travel_agency.tour(t_id,photo, date, duration, description, cost, tour_type) VALUES(3,'photo/img3.png', '2018-07-25', 30, 'description3', 300, 3);
INSERT INTO travel_agency.tour(t_id, photo, date, duration, description, cost, tour_type) VALUES(4,'photo/img4.png', '2018-07-30', 40, 'description4', 400, 4);
INSERT INTO travel_agency.tour(t_id,photo, date, duration, description, cost, tour_type) VALUES(5,'photo/img5.png', '2018-08-05', 50, 'description5', 500, 5);

INSERT INTO travel_agency.hotel(h_id, h_name, h_phone, h_stars, tour, latitude, longitude) VALUES(1,'Marriot', '123 23 23', 5, 1, 53.932717, 27.511248);
INSERT INTO travel_agency.hotel(h_id, h_name, h_phone, h_stars, tour) VALUES(2,'DoubleTree by Hilton', '232 12 12', 5, 2);
INSERT INTO travel_agency.hotel(h_id, h_name, h_phone, h_stars, tour) VALUES(3,'Prezident-Otel', '111 11 11', 4, 3);
INSERT INTO travel_agency.hotel(h_id, h_name, h_phone, h_stars, tour) VALUES(4,'Aqua-Minsk', '123 11 11', 2, 4);
INSERT INTO travel_agency.hotel(h_id, h_name, h_phone, h_stars, tour) VALUES(5,'Trump International Hotel Washington DC', '101 10 01', 5, 5);

INSERT INTO travel_agency.user(u_id, login, password, role) VALUES(1, 'root', 'root', 'ADMIN');
INSERT INTO travel_agency.user(u_id, login, password, role) VALUES(2, 'admin', 'admin', 'ADMIN');
INSERT INTO travel_agency.user(u_id, login, password, role) VALUES(3, 'traveler1', 'traveler1', 'MEMBER');

INSERT INTO travel_agency.review(r_id, content, user_id) VALUES(1, 'Content 1', 1);
INSERT INTO travel_agency.review(r_id, content, user_id) VALUES(2, 'Content 2', 2);
INSERT INTO travel_agency.review(r_id, content, user_id) VALUES(3, 'Content 3', 3);

INSERT INTO travel_agency.tour_m2m_country(t_id, c_id) VALUES(1, 3);
INSERT INTO travel_agency.tour_m2m_country(t_id, c_id) VALUES(1, 4);
INSERT INTO travel_agency.tour_m2m_country(t_id, c_id) VALUES(2, 1);
INSERT INTO travel_agency.tour_m2m_country(t_id, c_id) VALUES(4, 2);
INSERT INTO travel_agency.tour_m2m_country(t_id, c_id) VALUES(3, 3);
INSERT INTO travel_agency.tour_m2m_country(t_id, c_id) VALUES(5, 1);

INSERT INTO travel_agency.tour_m2m_user(t_id, u_id) VALUES(1, 3);
INSERT INTO travel_agency.tour_m2m_user(t_id, u_id) VALUES(2, 3);
INSERT INTO travel_agency.tour_m2m_user(t_id, u_id) VALUES(2, 2)