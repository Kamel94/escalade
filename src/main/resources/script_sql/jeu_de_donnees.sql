TRUNCATE TABLE utilisateur CASCADE;
INSERT INTO utilisateur
VALUES
(1, 'ponpon', 'Dupont', 'Jean', 'jeandupont@gmail.com', '$2a$08$OyJ4o0fTP/MyUP8p7H59OetkzibvvsFBb.KWh2kJsGaCCFksa29j2', 'ADMIN', TRUE, '06 56 76 90 32', 2, '2020/03/11', 2, '2020/03/11'),
(2,'riri', 'Rivierra', 'Patrick', 'patrickrivierra@gmail.com', '$2a$08$GHlqaxOoOh6J9/b.pLhYSeJpCg/r1WV55jOVYLvUse09usvdAUVUC', 'USER', TRUE, '06 56 04 90 59', 3, '2020/03/13',  3, '2020/03/13'),
(3,'memer56', 'Mercier', 'Robert', 'robertmercier@gmail.com', '$2a$08$7TzsnvtVaFvE4DnnXh9tJu5YoXDfv6sTSija4zNpvlith7yqYHi82', 'MEMBRE', TRUE, '06 78 45 38 12', 2, '2020/03/12', 2, '2020/03/12');

TRUNCATE TABLE site CASCADE;
INSERT INTO site
VALUES 
(1, 'Gorges du Todgha (Todra)', 'Maroc', 'Province de Ouarzazate', 'Tinghir', 3, false, 1, '2020/03/14', 1, '2020/03/14'),
(2, 'Marocaz', 'France', 'Savoie', 'Marocaz', 2, false, 1, '2020/03/14', 1, '2020/03/14'),
(3, 'Picellu', 'France', 'Corse', 'Picellu', 1, true, 2, '2020/03/17', 2, '2020/03/17'),
(4, 'Antalya', 'Turquie', 'Région méditerranéenne', 'Antalya', 2, false, 3, '2020/03/19', 3, '2020/03/19');

TRUNCATE TABLE topo CASCADE;
INSERT INTO topo
values
(1,'Gorges du Todgha', 'Vous trouverez dans les gorges du Todgha de nombreux secteurs de couennes de tous les niveaux (4b à 8c), des grandes voies équipées (jusqu’à 350m)', 'Maroc', '2020/03/19', 'Disponible', null, null, 1, 1, '2020/03/14', 1, '2020/03/14'),
(2,'Marocaz', 'Voici un des sites phares de la région pour les voies dures. Idéal pour grimper en été. Les voies sont très belles et longues, d’un niveau relativement élevé.', 'France (Savoie)', '12/03/2019', 'Disponible', null, null, 2, 1, '2020/03/14', 1, '2020/03/14'),
(3,'Picellu', 'Cette falaise hyper technique est la quintessence de l’escalade verticale en granit !', 'France (Corse)', '11/03/2019', 'Disponible', null, null, 3, 2, '2020/03/17', 2, '2020/03/17'),
(4, 'Antalya', 'La vue d''en face mérite le détour : on travaille là dans la dentelle gracile...C''est un endroit fort remarquable, le Drac tout en bas, et des pointes si fines au-dessus, tout cela doté d''une approche simplissime.','France (Auvergne-Rhône-Alpes)', '2020/02/19', 'Disponible', null, null, 4, 3, '2020/03/22', 3, '2020/03/22');

TRUNCATE TABLE secteur CASCADE;
INSERT INTO secteur
values
(1,'DragonBall', 1, 2, 1, '2020/03/14', 1, '2020/03/14'),
(2,'Rave on', 1, 1, 1, '2020/03/14', 1, '2020/03/14'),
(3,'Menhir', 2, 4, 3, '2020/03/14', 3, '2020/03/14'),
(4,'Solitude', 3, 5, 2, '2020/03/17', 2, '2020/03/17'),
(5,'Virage sud', 4, 2, 2, '2020/03/19', 2, '2020/03/19');

TRUNCATE TABLE voie CASCADE;
INSERT INTO voie
values
(1, 1, 1, 2, '6b', 3, '2020/03/14', 3, '2020/03/14'),
(2, 2, 1, 5, '7c', 3, '2020/03/14', 3, '2020/03/14'),
(3, 1, 2, 4, '8a', 3, '2020/03/14', 3, '2020/03/14'),
(4, 1, 3, 5, '8c', 1, '2020/03/14', 1, '2020/03/14'),
(5, 2, 3, 7, '6c', 1, '2020/03/14', 1, '2020/03/14'),
(6, 3, 3, 9, '7a', 1, '2020/03/14', 1, '2020/03/14'),
(7, 4, 3, 12, '6b', 1, '2020/03/14', 1, '2020/03/14'),
(8, 1, 4, 12, '9c', 2, '2020/03/17', 2, '2020/03/17'),
(9, 2, 4, 20, '7c', 2, '2020/03/17', 2, '2020/03/17'),
(10, 3, 4, 5, '7a', 2, '2020/03/17', 2, '2020/03/17'),
(11, 4, 4, 7, '8b', 2, '2020/03/17', 2, '2020/03/17'),
(12, 5, 4, 9, '9b', 2, '2020/03/17', 2, '2020/03/17'),
(13, 1, 5, 12, '6c', 3, '2020/03/19', 3, '2020/03/19'),
(14, 2, 5, 17, '9a', 3, '2020/03/19', 3, '2020/03/19');

TRUNCATE TABLE commentaire CASCADE;
INSERT INTO commentaire
VALUES
(1, 1, 3, 'Très beau spot avec de nombreux secteurs, un grand plaisir !', '2020-04-08 13:19:22', 3, '2020-04-08 13:19:22'),
(2, 1, 1, 'Je recommande ce spot, si vous êtes un grimpeur passionné, vous allez adorer ces voies.', '2020-04-08 14:38:51', 1, '2020-04-08 14:38:51'),
(3, 2, 2, 'Ma compagne et moi avons adoré escalade ce site très technique, de plus l''endroit est magnifique, la vue mérite le détour !!', '2020-04-08 14:55:07', 2, '2020-04-08 14:55:07');
