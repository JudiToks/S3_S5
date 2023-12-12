CREATE DATABASE Meuble;
\c meuble;

CREATE TABLE Matiere_Premiere (
    id_matierepremiere serial primary key,
    type varchar (50) not null,
);

CREATE TABLE Meuble (
    id_meuble serial primary key,
    id_matierepremiere int not null,
    categorie varchar (50) not null,
    taille varchar (10) not null,
    foreign key (id_matierepremiere) references Matiere_Premiere (id_matierepremiere)
);

CREATE TABLE Type_Meuble (
    
);