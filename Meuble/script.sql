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
create table fournisseur(
    id_fournisseur serial primary key,
    nom varchar
);
create table achat(
    id_achat serial primary key,
    id_matiere_premiere int references matiere_premiere(id_matiere_premiere),
    id_fournisseur int references fournisseur(id_fournisseur),
    qte double precision,
    date_achat timestamp
);
create table sortie(
    id_sortie serial primary key,
    id_matiere_premiere int references matiere_premiere(id_matiere_premiere),
    qte double precision,
    date_sortie timestamp
);
-- debut 16/01/2024
create table emp(
    id_emp serial primary key,
    nom varchar
);
create table dept(
    id_dept serial primary key,
    nom varchar
);
create table emp_dept_horaire(
    id_emp_dept_horaire serial primary key,
    id_emp int references emp(id_emp),
    id_dept int references dept(id_dept),
    prix_horaire double precision
);
create table produit_emp_horaire(
    id_produit_emp_horaire serial primary key,
    id_produit int references produit(id_produit),
    id_emp_dept_horaire int references emp_dept_horaire(id_emp_dept_horaire),
    heure_travail double precision
);
-- fin 16/01/2024

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

create or replace view v_etat_stock as
SELECT
    e.id_matiere_premiere,
    COALESCE(quantite_entree, 0) - COALESCE(quantite_sortie, 0) AS stock_actuel
FROM (
         SELECT
             id_matiere_premiere,
             SUM(qte) AS quantite_entree
         FROM achat
         GROUP BY id_matiere_premiere
     ) e
         LEFT JOIN (
    SELECT
        id_matiere_premiere,
        SUM(qte) AS quantite_sortie
    FROM sortie
    GROUP BY id_matiere_premiere
) s ON e.id_matiere_premiere = s.id_matiere_premiere order by id_matiere_premiere;



WITH DemandeMatiere AS (
    SELECT
        id_matiere_premiere,
        SUM(qte * 1) AS demande
    FROM details_produit
    WHERE id_produit = 1
    GROUP BY id_matiere_premiere
)
SELECT
    e.id_matiere_premiere,
    e.demande,
    s.stock_actuel,
    CASE
        WHEN e.demande <= s.stock_actuel THEN 'Ampy ny matiere premiere'
        ELSE 'Tsy ampy - Manque ' || (e.demande - s.stock_actuel) || ' unité(s)'
        END AS disponibilite
FROM DemandeMatiere e
         JOIN v_etat_stock s ON e.id_matiere_premiere = s.id_matiere_premiere;


-- select taille.nom, meuble.nom from formule_fabrication
--     join taille on taille.id_taille = formule_fabrication.id_taille
--     join meuble on meuble.id_meuble = formule_fabrication.id_meuble
--     join style_mat_prem on style_mat_prem.id_style_mat_prem = formule_fabrication.id_style_matprem
--         where formule_fabrication.id_style_matprem = (select id_style_mat_prem from style_mat_prem where id_matiere_premiere = (select id_matiere_premiere from matiere_premiere where nom like '%Tissu%'));