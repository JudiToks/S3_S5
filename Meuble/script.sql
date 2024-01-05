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

-- correct
create table produit(
    id_produit serial primary key,
    nom varchar,
    id_meuble int references meuble(id_meuble),
    id_style int references style(id_style)
);
create table details_produit(
    id_details_produit serial primary key,
    id_produit int references produit(id_produit),
    id_taille int references taille(id_taille),
    id_matiere_premiere int references matiere_premiere(id_matiere_premiere),
    qte double precision
);
-- end correct

create table formule_fabrication(
    id_meu_sty_tai_matprem serial primary key,
    id_style_matprem int references style_mat_prem(id_style_mat_prem),
    id_meuble int references meuble(id_meuble),
    id_taille int references taille(id_taille),
    qte double precision
);

select produit.nom, taille.nom, matiere_premiere.nom, details_produit.qte from details_produit
    join produit on produit.id_produit = details_produit.id_produit
    join taille on taille.id_taille = details_produit.id_taille
    join matiere_premiere on  matiere_premiere.id_matiere_premiere = details_produit.id_matiere_premiere
        where details_produit.id_matiere_premiere = (select id_matiere_premiere from matiere_premiere where LOWER(nom) like LOWER('%Tissu%'));

-- select taille.nom, meuble.nom from formule_fabrication
--     join taille on taille.id_taille = formule_fabrication.id_taille
--     join meuble on meuble.id_meuble = formule_fabrication.id_meuble
--     join style_mat_prem on style_mat_prem.id_style_mat_prem = formule_fabrication.id_style_matprem
--         where formule_fabrication.id_style_matprem = (select id_style_mat_prem from style_mat_prem where id_matiere_premiere = (select id_matiere_premiere from matiere_premiere where nom like '%Tissu%'));