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

CREATE TABLE IF NOT EXISTS Bibliotecar (
                                           id INT AUTO_INCREMENT PRIMARY KEY,
                                           nume VARCHAR(255) NOT NULL,
    prenume VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS BibliotecarSectiune (
                                                   bibliotecar_id INT,
                                                   sectiune_id INT,
                                                   FOREIGN KEY (bibliotecar_id) REFERENCES Bibliotecar(id),
    FOREIGN KEY (sectiune_id) REFERENCES Sectiune(id),
    PRIMARY KEY (bibliotecar_id, sectiune_id)
    );

CREATE TABLE IF NOT EXISTS ImprumutCarte (
                                             id INT AUTO_INCREMENT PRIMARY KEY,
                                             idCarte INT,
                                             idCititor INT,
                                             dataImprumut DATE,
                                             durataImprumutZile INT,
                                             FOREIGN KEY (idCarte) REFERENCES Carte(id),
    FOREIGN KEY (idCititor) REFERENCES Cititor(id)
    );

CREATE TABLE IF NOT EXISTS ImprumutAudioBook (
                                                 id INT AUTO_INCREMENT PRIMARY KEY,
                                                 idAudioBook INT,
                                                 idCititor INT,
                                                 dataImprumut DATE,
                                                 durataImprumutZile INT,
                                                 FOREIGN KEY (idAudioBook) REFERENCES AudioBook(id),
    FOREIGN KEY (idCititor) REFERENCES Cititor(id)
    );
