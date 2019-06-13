insert into usr(id, username, password,groupst, stfio, active) values (1, 'admin', '$2a$08$pKCfk3/wB2mhPTCRk4OsI.x6U.FCkKhQRudTlTBEo3YkXbG4r4/Be','-','-',true);

insert into user_role(user_id, roles) values (1, 'USER'), (1, 'ADMIN');