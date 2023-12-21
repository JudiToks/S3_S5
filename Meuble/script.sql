create database meuble;
\c meuble

create table meuble(
    id_meuble serial primary key,
    nom varchar
);
create table taille(
    id_taille serial primary key,
    nom varchar
);
create table style(
    id_style serial primary key,
    nom varchar
);
create table matiere_premiere(
    id_matiere_premiere serial primary key,
    nom varchar
);
create table style_mat_prem(
    id_style_mat_prem serial primary key,
    id_style int references style(id_style),
    id_matiere_premiere int references matiere_premiere(id_matiere_premiere)
);
create table formule_fabrication(
    id_meu_sty_tai_matprem serial primary key,
    id_style_matprem int references style_mat_prem(id_style_mat_prem),
    id_meuble int references meuble(id_meuble),
    id_taille int references taille(id_taille),
    qte double precision
);

select taille.nom, meuble.nom from formule_fabrication
    join taille on taille.id_taille = formule_fabrication.id_taille
    join meuble on meuble.id_meuble = formule_fabrication.id_meuble
    join style_mat_prem on style_mat_prem.id_style_mat_prem = formule_fabrication.id_style_matprem
                  where formule_fabrication.id_style_matprem = (select id_style_mat_prem from style_mat_prem where id_matiere_premiere = (select id_matiere_premiere from matiere_premiere where nom like '%Tissu%'));