CREATE TABLE IF NOT EXISTS Sectiune (
                                        id INT AUTO_INCREMENT PRIMARY KEY,
                                        gen ENUM('ROMANTIC', 'HORROR', 'COMEDIE', 'DOCUMENTAR')
    );

INSERT INTO Sectiune (gen) VALUES ('ROMANTIC');
INSERT INTO Sectiune (gen) VALUES ('HORROR');
INSERT INTO Sectiune (gen) VALUES ('COMEDIE');
INSERT INTO Sectiune (gen) VALUES ('DOCUMENTAR');

CREATE TABLE IF NOT EXISTS Carte (
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     titlu VARCHAR(255) NOT NULL,
    autor VARCHAR(255),
    an INT,
    esteDisponibilaPentruImprumut BOOLEAN,
    idSectiune INT,
    volum INT,
    FOREIGN KEY (idSectiune) REFERENCES Sectiune(id)
    );

INSERT INTO Carte (titlu, autor, an, esteDisponibilaPentruImprumut, idSectiune, volum)
VALUES ('Book Title', 'Author Name', 2024, true, 1, 1);

CREATE TABLE IF NOT EXISTS AudioBook (
                                         id INT AUTO_INCREMENT PRIMARY KEY,
                                         titlu VARCHAR(255) NOT NULL,
    autor VARCHAR(255),
    an INT,
    esteDisponibilaPentruImprumut BOOLEAN,
    idSectiune INT,
    durata INT,
    FOREIGN KEY (idSectiune) REFERENCES Sectiune(id)
    );

CREATE TABLE IF NOT EXISTS Cititor (
                                       id INT AUTO_INCREMENT PRIMARY KEY,
                                       nume VARCHAR(255) NOT NULL,
    prenume VARCHAR(255) NOT NULL
    );

INSERT INTO Cititor (nume, prenume) VALUES ('Popescu', 'Ion');
INSERT INTO Cititor (nume, prenume) VALUES ('Ionescu', 'Maria');
INSERT INTO Cititor (nume, prenume) VALUES ('Georgescu', 'Alexandru');
INSERT INTO Cititor (nume, prenume) VALUES ('Dumitrescu', 'Elena');

CREATE TABLE IF NOT EXISTS Bibliotecar (
                                           id INT AUTO_INCREMENT PRIMARY KEY,
                                           nume VARCHAR(255) NOT NULL,
    prenume VARCHAR(255) NOT NULL
    );

INSERT INTO Bibliotecar (nume, prenume) VALUES ('Bibliotecar1', '1');
INSERT INTO Bibliotecar (nume, prenume) VALUES ('Bibliotecar2', '2');
INSERT INTO Bibliotecar (nume, prenume) VALUES ('Bibliotecar3', '3');
INSERT INTO Bibliotecar (nume, prenume) VALUES ('Bibliotecar4', '4');

CREATE TABLE IF NOT EXISTS BibliotecarSectiune (
                                                   bibliotecar_id INT,
                                                   sectiune_id INT,
                                                   FOREIGN KEY (bibliotecar_id) REFERENCES Bibliotecar(id),
    FOREIGN KEY (sectiune_id) REFERENCES Sectiune(id),
    PRIMARY KEY (bibliotecar_id, sectiune_id)
    );

INSERT INTO BibliotecarSectiune (bibliotecar_id, sectiune_id) VALUES (1, 1);
INSERT INTO BibliotecarSectiune (bibliotecar_id, sectiune_id) VALUES (1, 2);
INSERT INTO BibliotecarSectiune (bibliotecar_id, sectiune_id) VALUES (1, 3);
INSERT INTO BibliotecarSectiune (bibliotecar_id, sectiune_id) VALUES (2, 3);
INSERT INTO BibliotecarSectiune (bibliotecar_id, sectiune_id) VALUES (2, 4);

CREATE TABLE  if not EXISTS  auth(
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    idBibliotecar INT,
    FOREIGN KEY (idBibliotecar) REFERENCES Bibliotecar(id)
    );

INSERT INTO auth (username, password, idBibliotecar) VALUES ('user1', 'password1', 1);
INSERT INTO auth (username, password, idBibliotecar) VALUES ('user2', 'password2', 2);

CREATE TABLE IF NOT EXISTS ImprumutCarte (
                                             id INT AUTO_INCREMENT PRIMARY KEY,
                                             idArticol INT,
                                             idCititor INT,
                                             dataImprumut DATE,
                                             durataImprumutZile INT,
                                             FOREIGN KEY (idArticol) REFERENCES Carte(id),
    FOREIGN KEY (idCititor) REFERENCES Cititor(id)
    );

CREATE TABLE IF NOT EXISTS ImprumutAudioBook (
                                                 id INT AUTO_INCREMENT PRIMARY KEY,
                                                 idArticol INT,
                                                 idCititor INT,
                                                 dataImprumut DATE,
                                                 durataImprumutZile INT,
                                                 FOREIGN KEY (idArticol) REFERENCES AudioBook(id),
    FOREIGN KEY (idCititor) REFERENCES Cititor(id)
    );
