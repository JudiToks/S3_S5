CREATE DATABASE Meuble;
\c meuble;

CREATE TABLE Matiere_Premiere (
    id_matierepremiere serial primary key,
    type varchar (50) not null,
);

CREATE TABLE Type_Meuble (
    
);