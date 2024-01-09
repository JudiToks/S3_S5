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
create table prix_mat_prem(
    id_prix_mat_prem serial primary key,
    id_matiere_premiere int references matiere_premiere(id_matiere_premiere),
    prix double precision
);
create table style_mat_prem(
    id_style_mat_prem serial primary key,
    id_style int references style(id_style),
    id_matiere_premiere int references matiere_premiere(id_matiere_premiere)
);
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

select produit.nom, taille.nom, matiere_premiere.nom, details_produit.qte from details_produit
    join produit on produit.id_produit = details_produit.id_produit
    join taille on taille.id_taille = details_produit.id_taille
    join matiere_premiere on  matiere_premiere.id_matiere_premiere = details_produit.id_matiere_premiere
        where details_produit.id_matiere_premiere = (select id_matiere_premiere from matiere_premiere where LOWER(nom) like LOWER('%Tissu%'));

CREATE OR REPLACE VIEW v_produit_prix AS SELECT p.nom, SUM(pmp.prix * dp.qte) AS prix FROM prix_mat_prem pmp
    JOIN matiere_premiere mp on mp.id_matiere_premiere = pmp.id_matiere_premiere
    JOIN details_produit dp ON dp.id_matiere_premiere = mp.id_matiere_premiere
    JOIN produit p on p.id_produit = dp.id_produit
        WHERE dp.id_produit = p.id_produit
        GROUP BY p.nom;

SELECT id_produit, SUM(qte * prix) AS prix_total_produit
FROM id_details_produit
         JOIN id_prix_mat_prem ON id_details_produit.id_matiere_premiere = id_prix_mat_prem.id_matiere_premiere
WHERE id_produit = 1
GROUP BY id_produit;

select * from v_produit_prix where prix >= 10000 and prix <= 20000;

-- select taille.nom, meuble.nom from formule_fabrication
--     join taille on taille.id_taille = formule_fabrication.id_taille
--     join meuble on meuble.id_meuble = formule_fabrication.id_meuble
--     join style_mat_prem on style_mat_prem.id_style_mat_prem = formule_fabrication.id_style_matprem
--         where formule_fabrication.id_style_matprem = (select id_style_mat_prem from style_mat_prem where id_matiere_premiere = (select id_matiere_premiere from matiere_premiere where nom like '%Tissu%'));