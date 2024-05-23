INSERT INTO Carte (titlu, autor, an, esteDisponibilaPentruImprumut, idSectiune, volum)
VALUES ('Book Title', 'Author Name', 2024, true, 1, 1);
INSERT INTO Carte (titlu, autor, an, esteDisponibilaPentruImprumut, idSectiune, volum)
VALUES ('Book Title 2', 'Author Name 2', 2021, true, 4, 2);

INSERT INTO AudioBook (titlu, autor, an, esteDisponibilaPentruImprumut, idSectiune, durata)
VALUES ('AudioBook title', 'Author', 1925, true, 1, 180);
INSERT INTO AudioBook (titlu, autor, an, esteDisponibilaPentruImprumut, idSectiune, durata)
VALUES ('AudioBook title 2', 'Author', 2004, true, 1, 120);

INSERT INTO Cititor (nume, prenume) VALUES ('Popescu', 'Ion');
INSERT INTO Cititor (nume, prenume) VALUES ('Ionescu', 'Maria');
INSERT INTO Cititor (nume, prenume) VALUES ('Georgescu', 'Alexandru');
INSERT INTO Cititor (nume, prenume) VALUES ('Dumitrescu', 'Elena');

INSERT INTO Bibliotecar (nume, prenume) VALUES ('Bibliotecar1', '1');
INSERT INTO Bibliotecar (nume, prenume) VALUES ('Bibliotecar2', '2');
INSERT INTO Bibliotecar (nume, prenume) VALUES ('Bibliotecar3', '3');
INSERT INTO Bibliotecar (nume, prenume) VALUES ('Bibliotecar4', '4');

INSERT INTO BibliotecarSectiune (bibliotecar_id, sectiune_id) VALUES (1, 1);
INSERT INTO BibliotecarSectiune (bibliotecar_id, sectiune_id) VALUES (1, 2);
INSERT INTO BibliotecarSectiune (bibliotecar_id, sectiune_id) VALUES (1, 3);
INSERT INTO BibliotecarSectiune (bibliotecar_id, sectiune_id) VALUES (2, 3);
INSERT INTO BibliotecarSectiune (bibliotecar_id, sectiune_id) VALUES (2, 4);

INSERT INTO auth (username, password, idBibliotecar) VALUES ('user', 'user', 1);
INSERT INTO auth (username, password, idBibliotecar) VALUES ('user', 'user', 2);