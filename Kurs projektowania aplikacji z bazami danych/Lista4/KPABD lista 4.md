# KPABD lista 4

## Zadanie 1
https://sqlchris.wordpress.com/2017/06/25/poziomy-izolacji-transakcji-sql-server/
```sql=
DROP TABLE IF EXISTS Przedstawienia
GO

CREATE TABLE Przedstawienia (ID INT PRIMARY KEY, Tytuł VARCHAR(50), LiczbaWidzow INT)

INSERT INTO Przedstawienia VALUES(1, 'Zbrodnia i kara',		 100)
INSERT INTO Przedstawienia VALUES(2, 'Pan Tadeusz',		 200)
INSERT INTO Przedstawienia VALUES(3, 'Romeo i Julia',		 300)
INSERT INTO Przedstawienia VALUES(4, 'Krzyżacy',		 400)
INSERT INTO Przedstawienia VALUES(5, 'Opowieść wigilijna', 500)
GO
```


### Dirty reads
Występuje, kiedy jedna transakcja zmienia dane, ale nie komituje ich (nie zatwierdza), a druga transakcja ma pozwolenie do czytania tych zmienionych danych. Występuje w tym przypadku duża możliwość, że zostaną odczytane niespójne dane.

Poziomy izolacji:
* READ UNCOMITTED

**Pierwsze okno**
```sql=
SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED; 

BEGIN TRAN
UPDATE Przedstawienia SET LiczbaWidzow = 100 WHERE ID = 2
UPDATE Przedstawienia SET LiczbaWidzow = 100 WHERE ID = 3
WAITFOR DELAY '00:00:05'
SELECT * FROM Przedstawienia
ROLLBACK TRANSACTION
SELECT * FROM Przedstawienia
```


**Drugie okno**
```sql=
SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED; 
SELECT * FROM Przedstawienia
```

W drugim oknie wyświetlą sie zmienione dane, które chwile potem są wycofywane i nie były zakomittowane. 

### Non-Repeatable reads
Transakcja odczytuje zmienione dane odczytując ten sam zbiór danych w dwóch różnych momentach czasowych podczas trwania tej samej transakcji.

Poziomy izolacji:
* READ UNCOMITTED
* READ COMITTED

**Pierwsze okno**
```sql=
SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED; 

BEGIN TRAN
SELECT * FROM Przedstawienia
WAITFOR DELAY '00:00:05'
SELECT * FROM Przedstawienia
COMMIT TRAN
```

**Drugie okno**
```sql=
BEGIN TRAN
DELETE FROM Przedstawienia WHERE ID = 1
COMMIT TRAN
GO
```

W pierwszym oknie 2 odczyty wypisują tabele ze zmienionymi danymi
### Phantom reads
Występuje w transakcjach z predykatem WHERE x <, gdzie odczyt danych w dwóch różnych poleceniach SELECT w tej samej transakcji zwraca różną liczbę wierszy.

Poziomy izolacji:
* READ UNCOMITTED
* READ COMITTED
* REPEATABLE READ

**Pierwsze okno**
```sql=
SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED; 

BEGIN TRAN
SELECT * FROM Przedstawienia WHERE LiczbaWidzow > 200
WAITFOR DELAY '00:00:05'
SELECT * FROM Przedstawienia WHERE LiczbaWidzow > 200
COMMIT TRAN
```

**Drugie okno**
```sql=
BEGIN TRAN
INSERT INTO Przedstawienia VALUES(6, 'Edyp',		 600)
INSERT INTO Przedstawienia VALUES(7, 'Zemsta', 700)
COMMIT TRAN
GO
```
Wyniki w pierwszym oknie różnią się ilością wierszy

Zmiana poziomu izolacji (domyślny READ COMITTED)
```sql=
SET TRANSACTION ISOLATION LEVEL *nazwa poziomu izolacji*
```

## Zadanie 2

1NF - dzielimy dane na atomowe
2NF - dzielimy dane na tabele związane z obiektem
3NF - wyrzucamy to, co można wywnioskować na podstawie innych danych + wydzielamy te dane z tabel, które mogą się powtarzać
![](https://i.imgur.com/0PW2fkC.png)

## Zadanie 3
Dorzucamy kilka pozycji do tabel
```sql=
SET IDENTITY_INSERT Czytelnik ON
INSERT INTO Czytelnik (CZYTELNIK_ID,PESEL,NAZWISKO,MIASTO,DATA_URODZENIA) VALUES
(4, '00261075151', 'Surowy', 'Paryż', '2000-06-10'),
(5, '82041434147', 'Wichura', 'Gdańsk',  '1982-04-14'),
(6, '95031147325', 'Szczęsny', 'Opole', '1995-03-11'),
(7, '88090536984', 'Fikus', 'Zielona Góra', '1988-09-05'),
(8, '02261381773', 'Koral', 'Wrocław', '2002-06-13'),
(9, '71112675277', 'Pazura', 'Gdańsk', '1971-11-26'),
(10, '60081817984', 'Tomczak', 'Poznań', '1960-08-18')
SET IDENTITY_INSERT Czytelnik OFF
GO

SET IDENTITY_INSERT Egzemplarz ON
INSERT INTO Egzemplarz (Egzemplarz_ID,Ksiazka_ID,Sygnatura) VALUES
(6,  2, 'S0006'),
(7,  3, 'S0007'),
(8,  3, 'S0008'),
(9,  3, 'S0009'),
(10, 1, 'S0010'),
(11, 2, 'S0011'),
(12, 3, 'S0012'),
(13, 1, 'S0013'),
(14, 2, 'S0014'),
(15, 5, 'S0015'),
(16, 1, 'S0016'),
(17, 1, 'S0017'),
(18, 3, 'S0018'),
(19, 4, 'S0019'),
(20, 2, 'S0020'),
(21, 5, 'S0021'),
(22, 5, 'S0022'),
(23, 6, 'S0023'),
(24, 6, 'S0024'),
(25, 6, 'S0025'),
(26, 6, 'S0026'),
(27, 4, 'S0027'),
(28, 1, 'S0028'),
(29, 5, 'S0029'),
(30, 5, 'S0030'),
(31, 2, 'S0031'),
(32, 3, 'S0032'),
(33, 3, 'S0033'),
(34, 3, 'S0034'),
(35, 1, 'S0035'),
(36, 2, 'S0036'),
(37, 3, 'S0037'),
(38, 1, 'S0038'),
(39, 2, 'S0039'),
(40, 5, 'S0040'),
(41, 1, 'S0041'),
(42, 1, 'S0042'),
(43, 3, 'S0043'),
(44, 4, 'S0044'),
(45, 2, 'S0045'),
(46, 5, 'S0046'),
(47, 5, 'S0047'),
(48, 6, 'S0048'),
(49, 6, 'S0049'),
(50, 6, 'S0050'),
(51, 6, 'S0051'),
(52, 4, 'S0052'),
(53, 1, 'S0053'),
(54, 5, 'S0054'),
(55, 5, 'S0055'),
(56, 2, 'S0056'),
(57, 3, 'S0057'),
(58, 3, 'S0058'),
(59, 3, 'S0059'),
(60, 1, 'S0060'),
(61, 2, 'S0061'),
(62, 3, 'S0062'),
(63, 1, 'S0063'),
(64, 2, 'S0064'),
(65, 5, 'S0065'),
(66, 1, 'S0066'),
(67, 1, 'S0067'),
(68, 3, 'S0068'),
(69, 4, 'S0069'),
(70, 2, 'S0070'),
(71, 5, 'S0071'),
(72, 5, 'S0072'),
(73, 6, 'S0073'),
(74, 6, 'S0074'),
(75, 6, 'S0075'),
(76, 6, 'S0076'),
(77, 4, 'S0077'),
(78, 1, 'S0078'),
(79, 5, 'S0079'),
(80, 5, 'S0080'),
(81, 2, 'S0081'),
(82, 3, 'S0082'),
(83, 1, 'S0083'),
(84, 2, 'S0084'),
(85, 5, 'S0085'),
(86, 1, 'S0086'),
(87, 1, 'S0087'),
(88, 3, 'S0088'),
(89, 4, 'S0089'),
(90, 2, 'S0090'),
(91, 5, 'S0091'),
(92, 5, 'S0092'),
(93, 6, 'S0093'),
(94, 6, 'S0094'),
(95, 6, 'S0095'),
(96, 6, 'S0096'),
(97, 4, 'S0097'),
(98, 1, 'S0098'),
(99, 5, 'S0099'),
(100, 5, 'S0100')
SET IDENTITY_INSERT Egzemplarz OFF
GO

SET IDENTITY_INSERT Wypozyczenie ON
INSERT INTO Wypozyczenie (Wypozyczenie_ID,Czytelnik_ID,Egzemplarz_ID,Data,Liczba_Dni) VALUES
(3, 2, 26, '2020-01-08', 43),
(4, 5, 26, '2020-01-28', 31),
(5, 5, 11, '2020-02-22', 5),
(6, 3, 19, '2020-02-24', 40),
(7, 3, 2, '2020-01-05', 25),
(8, 4, 28, '2020-01-28', 31),
(9, 2, 4, '2020-03-22', 49),
(10, 3, 12, '2020-03-23', 26),
(11, 1, 6, '2020-03-05', 33),
(12, 1, 26, '2020-03-01', 46),
(13, 2, 3, '2020-03-30', 6),
(14, 1, 2, '2020-03-03', 11),
(15, 3, 20, '2020-02-28', 44),
(16, 5, 15, '2020-02-27', 15),
(17, 1, 16, '2020-02-23', 28),
(18, 1, 22, '2020-02-19', 38),
(19, 4, 4, '2020-03-29', 47),
(20, 5, 3, '2020-01-16', 6),
(21, 4, 8, '2020-02-27', 10),
(22, 4, 19, '2020-01-22', 41),
(23, 1, 13, '2020-01-03', 35),
(24, 3, 12, '2020-02-05', 35),
(25, 3, 18, '2020-02-10', 30),
(26, 1, 3, '2020-02-21', 10),
(27, 5, 8, '2020-02-18', 13),
(28, 3, 3, '2020-03-13', 14),
(29, 2, 27, '2020-03-03', 39),
(30, 2, 19, '2020-02-06', 17),
(31, 5, 17, '2020-01-11', 32),
(32, 3, 28, '2020-03-06', 42),
(33, 4, 10, '2020-01-11', 2),
(34, 4, 16, '2020-01-22', 9),
(35, 4, 17, '2020-01-06', 30),
(36, 5, 10, '2020-03-03', 16),
(37, 5, 1, '2020-01-28', 8),
(38, 4, 4, '2020-03-04', 46),
(39, 1, 9, '2020-02-06', 47),
(40, 2, 21, '2020-02-12', 3),
(41, 2, 23, '2020-02-21', 1),
(42, 5, 13, '2020-03-18', 22),
(43, 5, 25, '2020-03-21', 22),
(44, 5, 3, '2020-01-10', 43),
(45, 5, 7, '2020-01-27', 38),
(46, 4, 13, '2020-01-29', 32),
(47, 2, 30, '2020-03-08', 11),
(48, 1, 15, '2020-03-14', 36),
(49, 3, 23, '2020-03-25', 26),
(50, 7, 6, '2020-02-14', 13),
(51, 7, 56, '2020-01-08', 43),
(52, 8, 76, '2020-01-28', 31),
(53, 9, 96, '2020-01-08', 43),
(54, 10, 46, '2020-01-28', 31),
(55, 5, 91, '2020-02-22', 5),
(56, 5, 99, '2020-02-24', 40),
(57, 6, 62, '2020-01-05', 25),
(58, 4, 54, '2020-01-28', 31),
(59, 7, 55, '2020-03-22', 49),
(60, 8, 76, '2020-03-23', 26),
(61, 6, 70, '2020-03-05', 33),
(62, 5, 98, '2020-03-01', 46),
(63, 5, 99, '2020-03-30', 6),
(64, 6, 100, '2020-03-03', 11),
(65, 7, 93, '2020-02-28', 44),
(66, 8, 86, '2020-02-27', 15),
(67, 8, 71, '2020-02-23', 28),
(68, 10, 37, '2020-02-19', 38),
(69, 9, 50, '2020-03-29', 47),
(70, 9, 88, '2020-01-16', 6),
(71, 8, 89, '2020-02-27', 10),
(72, 7, 75, '2020-01-22', 41),
(73, 5, 47, '2020-01-03', 35),
(74, 6, 57, '2020-02-05', 35),
(75, 7, 56, '2020-02-10', 30),
(76, 10, 87, '2020-02-21', 10),
(77, 8, 82, '2020-02-18', 13),
(78, 9, 75, '2020-03-13', 14),
(79, 9, 60, '2020-03-03', 39),
(80, 5, 69, '2020-02-06', 17),
(81, 6, 61, '2020-01-11', 32),
(82, 7, 63, '2020-03-06', 42),
(83, 7, 67, '2020-01-11', 2),
(84, 5, 66, '2020-01-22', 9),
(85, 5, 96, '2020-01-06', 30),
(86, 4, 87, '2020-03-03', 16),
(87, 8, 95, '2020-01-28', 8),
(88, 8, 72, '2020-03-04', 46),
(89, 9, 68, '2020-02-06', 47),
(90, 9, 60, '2020-02-12', 3),
(91, 10, 54, '2020-02-21', 1),
(92, 6, 86, '2020-03-18', 22),
(93, 5, 59, '2020-03-21', 22),
(94, 7, 98, '2020-01-10', 43),
(95, 8, 93, '2020-01-27', 38),
(96, 9, 77, '2020-01-29', 32),
(97, 6, 54, '2020-03-08', 11),
(98, 9, 98, '2020-03-14', 36),
(99, 10, 92, '2020-03-25', 26),
(100, 5, 90, '2020-02-14', 13)
SET IDENTITY_INSERT Wypozyczenie OFF
GO
```

```sql=
SET STATISTICS TIME ON

SELECT DISTINCT c.PESEL, c.Nazwisko
FROM Egzemplarz e
JOIN Ksiazka k ON e.Ksiazka_ID=k.Ksiazka_ID
JOIN Wypozyczenie w ON e.Egzemplarz_ID=w.Egzemplarz_ID
JOIN Czytelnik c ON c.Czytelnik_ID = w.Czytelnik_ID
--client processing time: 1.3
--total execution time: 79.4
--wait time on server replies: 78.1

SELECT c.PESEL, c.Nazwisko
FROM Czytelnik c WHERE c.Czytelnik_ID IN
(SELECT w.Czytelnik_ID FROM Wypozyczenie w
JOIN Egzemplarz e ON e.Egzemplarz_ID=w.Egzemplarz_ID
JOIN Ksiazka k ON e.Ksiazka_ID=k.Ksiazka_ID)
--client processing time: 1.8
--total execution time: 82.4
--wait time on server replies: 80.6

SELECT c.PESEL, c.Nazwisko
FROM Czytelnik c WHERE c.Czytelnik_ID IN
(SELECT w.Czytelnik_ID FROM Wypozyczenie w, Egzemplarz e, Ksiazka k 
WHERE e.Egzemplarz_ID = w.Egzemplarz_ID AND e.Ksiazka_ID = k.Ksiazka_ID)
--client processing time: 2.3
--total execution time: 83.8
--wait time on server replies: 81.5

SET STATISTICS TIME OFF
GO
```
https://stackoverflow.com/questions/2577174/join-vs-sub-query

## Zadanie 4
```sql=

```