create database est;

create table estudiantes
(
  `_id`            int auto_increment
    primary key,
  nombre           varchar(60) null,
  apellido         varchar(60) null,
  fecha_nacimiento date        null
);

create table materias
(
  `_id`  int auto_increment
    primary key,
  nombre varchar(60) not null,
  uv     int         not null
)
  comment 'Materias que se imparten';

create table estudiante_x_materia
(
  `_id`         int auto_increment,
  estudiante_id int not null,
  materia_id    int not null,
  primary key (`_id`, estudiante_id, materia_id),
  constraint estudiante_x_materia_estudiantes__id_fk
  foreign key (estudiante_id) references estudiantes (`_id`),
  constraint estudiante_x_materia_materias__id_fk
  foreign key (materia_id) references materias (`_id`)
);

create index estudiante_x_materia_estudiantes__id_fk
  on estudiante_x_materia (estudiante_id);

create index estudiante_x_materia_materias__id_fk
  on estudiante_x_materia (materia_id);


