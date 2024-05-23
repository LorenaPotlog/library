# Proiect PAO (Biblioteca - Carte, AudioBook, Sectiune, Bibliotecar, Cititor, Imprumut)


### - Clasele vor avea date membre private / protected și metode publice de acces.
Toate clasele au membrii privati si metode de get si set publice.
### - Cel puțin într-o clasă se vor rescrie metode din clasa `Object`.
In clasa [`Imprumut`](https://github.com/LorenaPotlog/library/blob/master/src/main/java/myLibrary/models/Imprumut.java) am rescris (override) metoda toString.
### - Se vor utiliza cel puțin două colecții diferite capabile să gestioneze obiectele definite anterior, iar cel puțin una trebuie să fie sortată.
- In clasa [`Bibliotecar`](https://github.com/LorenaPotlog/library/blob/master/src/main/java/myLibrary/models/Bibliotecar.java) am folosit o lista de sectiuni: `private List<Sectiune> sectiuniGestionate`; <br>
- In clasa [`Carte`](https://github.com/LorenaPotlog/library/blob/master/src/main/java/myLibrary/models/Carte.java) am rescris metoda compareTo, astfel colectia `List<Carte> carti = new ArrayList<>();` din metoda `afiseazaToate` clasa [`CarteDAO`](https://github.com/LorenaPotlog/library/blob/master/src/main/java/myLibrary/repositories/CarteDAO.java) este un set sortat de carti dupa an.
### - Se va utiliza moștenirea pentru crearea de clase specializate.
Clasele [`Carte`](https://github.com/LorenaPotlog/library/blob/master/src/main/java/myLibrary/models/Carte.java) si [`AudioBook`](https://github.com/LorenaPotlog/library/blob/master/src/main/java/myLibrary/models/AudioBook.java) mostenesc clasa parinte [`Articol`](https://github.com/LorenaPotlog/library/blob/master/src/main/java/myLibrary/models/Articol.java).
### - Cel puțin într-o clasă se va utiliza agregarea sau compoziția.
Agregare - folosita in clasa [`Bibliotecar`](https://github.com/LorenaPotlog/library/blob/master/src/main/java/myLibrary/models/Bibliotecar.java), avand ca membru o lista de sectiuni: `private List<Sectiune> sectiuniGestionate`;
### Se va implementa facilitatea de autentificare în aplicație.
Am creat o tabela `auth` in baza de date care salveaza username-ul si parola unui bibliotecar. Pentru logare am creat clasa [`Autentificare `](https://github.com/LorenaPotlog/library/blob/master/src/main/java/myLibrary/services/Autentificare.java) care verifica daca datele sunt corecte inainte sa intoarca (autentifice) un obiect de tip Bibliotecar.
### Se va implementa cel puțin o clasă care să expună acțiunile care pot fi efectuate de aplicație.
Clasa [`Biblioteca`](https://github.com/LorenaPotlog/library/blob/master/src/main/java/myLibrary/services/Biblioteca.java). Este o clasa tip Service, care face legatura intre Respository si Controller.
### Se va implementa o clasă care să permită rularea aplicației prin intermediul unui meniu de tip text.
Aplicatia se poate testa prin clasa [`Main`](https://github.com/LorenaPotlog/library/blob/master/src/main/java/myLibrary/Main.java).<br>
![meniu](https://github.com/LorenaPotlog/library/assets/101601637/36066057-9606-405c-a066-abd75439b6e0)
### Se va asigura persistența datelor utilizând o bază de date relațională și JDBC.
Am folosit H2 (in-memory db), si la fiecare rulare a proiectului creez tabelele necesare folosind fisierul [`create_tables.sql`](https://github.com/LorenaPotlog/library/blob/master/src/main/resources/create_tables.sql).
### Se vor realiza operații de tip CRUD (Create, Read, Update și Delete) pentru cel puțin una dintre clasele utilizate.
Realizat pentru clasele `Carte`, `AudioBook`, `Bibliotecar` si `Imprumut`. Exemplu: [`CarteDAO`](https://github.com/LorenaPotlog/library/blob/master/src/main/java/myLibrary/repositories/CarteDAO.java)
### Se va implementa facilitatea de înregistrare într-un fișier de tip CSV a fiecărei executări a uneia dintre acțiunile aplicației, sub forma denumire_acțiune, data_și_ora.
Folosim clasa [`Logare`](https://github.com/LorenaPotlog/library/blob/master/src/main/java/myLibrary/services/Logare.java) cu `BufferedWriter`.
![logs1](https://github.com/LorenaPotlog/library/assets/101601637/c00b4dd4-2987-4d3c-b4c2-097b7aeed468)
