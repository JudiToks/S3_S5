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
create table emp_dept(
    id_emp_dept_horaire serial primary key,
    id_emp int references emp(id_emp),
    id_dept int references dept(id_dept)
);
create table dept_salaire(
    id_dept_salaire serial primary key,
    id_dept int references dept(id_dept),
    salaire double precision
);
create table heure_travail_style(
    id_heure_travail_style serial primary key,
    id_style int references style(id_style),
    heure double precision
);
create table taille_nbr_mpiasa(
    id_taille_nbr_mpiasa serial primary key,
    id_taille int references taille(id_taille),
    nombre int
);
create table taille_style_dept(
    id_taille_style_dept serial primary key,
    id_taille int references taille(id_taille),
    id_style int references style(id_style),
    id_dept int references dept(id_dept),
    nombre double precision
);
create table prix_vente_produit(
    id_prix_produit serial primary key,
    id_produit int references produit(id_produit),
    id_taille int references taille(id_taille),
    prix double precision
);
-- fin 16/01/2024
-- debut 23/01/2024
create table profil(
    id_profil serial primary key,
    nom varchar,
    annee_travail double precision,
    coeff double precision
);
create table emp_dept_embauche(
    id_emp_dept_embauche serial primary key,
    id_emp_dept int references emp_dept(id_emp_dept_horaire),
    id_profil int references profil(id_profil),
    date_embauche date
);
-- fin 23/01/2024
-- debut 25/01/2024
create table genre(
    id_genre serial primary key,
    nom varchar
);
create table client(
    id_client serial primary key,
    nom varchar,
    prenom varchar,
    dtn date,
    id_genre int references genre(id_genre)
);
create table panier(
    id_panier serial primary key,
    id_client int references client(id_client),
    etat int
);
create table details_panier(
    id_details_panier serial primary key,
    id_panier int references panier(id_panier),
    id_produit int references produit(id_produit),
    qte int
);
-- fin 25/01/2024

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


create or replace view v_benefice_produit as (
with prix_fabrication_produit as (
    with prix_prestation as (
        with list_all_prestation as (
            select
                id_taille,
                hts.id_style,
                ds.id_dept,
                (((salaire * nombre) * heure) / 3600) as prestation
            from taille_style_dept tsd
                     join dept_salaire ds on tsd.id_dept = ds.id_dept
                     join heure_travail_style hts on tsd.id_style = hts.id_style
        )
        select
            id_taille,
            id_style,
            sum(prestation) as prestation
        from list_all_prestation
        group by id_taille, id_style
    )
    select
        p.id_produit,
        p.nom as produit,
        t.id_taille,
        t.nom as taille,
        sum(pp.prestation + v_produit_prix.prix) as prix
    from produit p
        join prix_prestation pp on pp.id_style = p.id_style
        join details_produit dp on dp.id_produit = p.id_produit
        join v_produit_prix on v_produit_prix.nom = p.nom
        join taille t on t.id_taille = pp.id_taille
    group by p.id_produit, p.nom, t.id_taille, t.nom
)
select
    pfp.produit,
    pfp.taille,
    (pvp.prix - pfp.prix) as benefice
from
    prix_vente_produit pvp
        join prix_fabrication_produit pfp on pfp.id_produit = pvp.id_produit and pfp.id_taille = pvp.id_taille
);


SELECT
    ede.id_emp_dept,
    e.nom AS nom_employe,
    d.nom AS nom_departement,
    ds.salaire AS salaire_base,
    p.nom AS profil,
    p.coeff AS coefficient,
    EXTRACT(YEAR FROM AGE('2027-01-23', ede.date_embauche)) AS annee_travail,
    ds.salaire * p.coeff AS salaire_calcule
FROM
    emp_dept_embauche ede
        JOIN emp_dept ed ON ed.id_emp_dept_horaire = ede.id_emp_dept
        JOIN emp e ON ed.id_emp = e.id_emp
        JOIN dept d ON ed.id_dept = d.id_dept
        JOIN dept_salaire ds ON ed.id_dept = ds.id_dept
        JOIN profil p ON ede.id_profil = p.id_profil
where LOWER(CONCAT(e.nom, ' ', d.nom, ' ', p.nom, ' ')) LIKE LOWER('%%');

-- getAllStati
select
    p.nom as produit,
    g.nom as genre,
    sum(nbr) as nombre
from vente v
    join client c on c.id_client = v.id_client
    join genre g on g.id_genre = c.id_genre
    join produit p on p.id_produit = v.id_produit
group by p.nom, g.nom;

select
    p.nom as produit,
    g.nom as genre,
    sum(nbr) as nombre
from vente v
         join client c on c.id_client = v.id_client
         join genre g on g.id_genre = c.id_genre
         join produit p on p.id_produit = v.id_produit
group by p.nom, g.nom;


SELECT
    p.id_produit,
    p.nom AS nom_produit,
    g.nom AS nom_genre,
    sum(qte) AS nombre_ventes
FROM
    details_panier dp
        JOIN panier on dp.id_panier = panier.id_panier
        JOIN produit p ON dp.id_produit = p.id_produit
        JOIN client c ON panier.id_client = c.id_client
        JOIN genre g ON c.id_genre = g.id_genre
GROUP BY
    p.id_produit, g.id_genre, qte
ORDER BY
    p.id_produit, g.id_genre;


with list_genre_nbr as (
    SELECT
        g.id_genre,
        g.nom,
        sum(qte) AS nombre_ventes
    FROM
        details_panier dp
            JOIN panier on panier.id_panier = dp.id_panier
            JOIN produit p ON dp.id_produit = p.id_produit
            JOIN client c ON panier.id_client = c.id_client
            JOIN genre g ON c.id_genre = g.id_genre
    GROUP BY
        g.id_genre, qte
    ORDER BY
        g.id_genre
)
select
    id_genre,
    nom,
    sum(nombre_ventes)
from
    list_genre_nbr
group by id_genre, nom
order by id_genre;


select
    nom,
    stock_actuel
from v_etat_stock ves
    join matiere_premiere mp on ves.id_matiere_premiere = mp.id_matiere_premiere;


select
    nom,
    qte
from details_panier
    join produit p on details_panier.id_produit = p.id_produit
where id_panier = 1;