create table users (
  id serial PRIMARY KEY,
  username varchar(100) NOT NULL UNIQUE,
  pwd varchar(50) NOT NULL
);

create table user_roles (
  user_id integer NOT NULL,
  role_id integer NOT NULL,
  UNIQUE (user_id, role_id)
);

create table roles (
  id serial PRIMARY KEY,
  role varchar(100) NOT NULL UNIQUE
);

create table dossiers (
  id serial PRIMARY KEY,
  nom varchar(255) NOT NULL,
  prenom varchar(255) NOT NULL,
  no_plaque varchar(255) NOT NULL,
  no_permis varchar(255) NOT NULL
);

create table infractions (
  id serial PRIMARY KEY,
  description varchar(255) NOT NULL,
  gravite integer NOT NULL
);

create table infraction_dossiers (
  id serial PRIMARY KEY,
  dossier_id integer NOT NULL,
  infraction_id integer NOT NULL
);

insert into users (username, pwd) VALUES
  ('henry', 'bob12'),
  ('gaston', 'toto13');

insert into roles (role) VALUES
  ('administrateur'),
  ('utilisateur');

insert into user_roles (user_id, role_id) VALUES
  (1, 1),
  (2, 2);

insert into dossiers (nom, prenom, no_plaque, no_permis) VALUES
  ('henry', 'lemieux', '123456', '987654'),
  ('gaston', 'rinfrette', '789789', '123456');

insert into infractions (description, gravite) VALUES
  ('Delit de fuite', 1),
  ('ASDASD', 30),
  ('QWEQWE', 50),
  ('YOLO', 100),
  ('RTYRTY', 25);

insert into infraction_dossiers (dossier_id, infraction_id) VALUES
  (1, 1),
  (1, 2),
  (1, 3),
  (1, 4),
  (1, 5);
