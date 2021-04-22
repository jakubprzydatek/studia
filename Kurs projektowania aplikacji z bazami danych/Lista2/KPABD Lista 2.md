# KPABD Lista 2

## Zadanie 1

```sql=
DROP FUNCTION IF EXISTS borrowedBooks
GO

CREATE OR ALTER FUNCTION borrowedBooks(@days INT) RETURNS TABLE AS
RETURN
	SELECT 
		reader.PESEL, COUNT(rental.Wypozyczenie_ID) AS specimens_number
	FROM	
		dbo.Czytelnik AS reader,
		dbo.Wypozyczenie AS rental
	WHERE
		reader.Czytelnik_ID = rental.Czytelnik_ID AND
		rental.Liczba_Dni >= @days
	GROUP BY reader.PESEL
GO


SELECT * FROM borrowedBooks(1)
```
Nasze rozwiązanie zwraca ile czytelnik ma książek powyżej iluś dni. To niżej zwraca wszystkie książki przetrzymywane przez czytelnika, gdzie ma on przynajmniej jedną książkę wypożyczoną >= @days.
![](https://i.imgur.com/JXocdpQ.png)

## Zadanie 2

```sql=
DROP TABLE IF EXISTS firstnames
GO

CREATE TABLE firstnames (
	id INT PRIMARY KEY,
	firstname CHAR(20)
)
GO

INSERT INTO firstnames VALUES(1, 'Jakub')
INSERT INTO firstnames VALUES(2, 'Paweł')
INSERT INTO firstnames VALUES(3, 'Grzegorz')
INSERT INTO firstnames VALUES(4, 'Maciej')
INSERT INTO firstnames VALUES(5, 'Łukasz')
INSERT INTO firstnames VALUES(6, 'Antoni')
INSERT INTO firstnames VALUES(7, 'Kacper')
INSERT INTO firstnames VALUES(8, 'Piotr')
INSERT INTO firstnames VALUES(9, 'Zenon')
INSERT INTO firstnames VALUES(10, 'Wiktor')
GO

DROP TABLE IF EXISTS lastnames
GO

CREATE TABLE lastnames (
	id INT PRIMARY KEY,
	lastname CHAR(20)
)
GO

INSERT INTO lastnames VALUES(1, 'Kowalski')
INSERT INTO lastnames VALUES(2, 'Sowa')
INSERT INTO lastnames VALUES(3, 'Nowak')
INSERT INTO lastnames VALUES(4, 'Tracz')
INSERT INTO lastnames VALUES(5, 'Zamachowski')
INSERT INTO lastnames VALUES(6, 'Pazura')
INSERT INTO lastnames VALUES(7, 'Stoch')
INSERT INTO lastnames VALUES(8, 'Tomczuk')
INSERT INTO lastnames VALUES(9, 'Mickiewicz')
INSERT INTO lastnames VALUES(10, 'Słowacki')
GO

DROP TABLE IF EXISTS fldata
GO

CREATE TABLE fldata (
	firstname CHAR(20),
	lastname CHAR(20),
	CONSTRAINT pk_personID PRIMARY KEY (firstname, lastname)
)
GO

DROP PROCEDURE IF EXISTS insertRandomPairs
GO

CREATE OR ALTER PROCEDURE insertRandomPairs @n INT AS
BEGIN
	IF OBJECT_ID('fldata') IS NULL THROW 50000, 'fldata does not exist', 1;
	TRUNCATE TABLE fldata

	DECLARE @i INT
	SET @i = 0

	DECLARE @firstnames_count INT
	DECLARE @lastnames_count INT
	DECLARE @possibilities INT
	SET @firstnames_count = (SELECT COUNT(DISTINCT firstname) FROM dbo.firstnames)
	SET @lastnames_count = (SELECT COUNT(DISTINCT lastname) FROM dbo.lastnames)
	SET @possibilities = @firstnames_count * @lastnames_count

	IF (@n > @possibilities)
		THROW 50001, 'n is larger than the number of all possible pairs', 1;

	WHILE (@i < @n)
	BEGIN
		DECLARE @firstname CHAR(20)
		SET @firstname = (SELECT TOP 1 firstname FROM firstnames ORDER BY NEWID())
		DECLARE @lastname CHAR(20)
		SET @lastname = (SELECT TOP 1 lastname FROM lastnames ORDER BY NEWID())

		IF NOT EXISTS (SELECT * FROM fldata WHERE firstname = @firstname AND lastname = @lastname)
		BEGIN
			INSERT INTO fldata VALUES(@firstname, @lastname)
			SET @i = @i + 1
		END
	END
	SELECT * FROM fldata
END
GO

EXEC insertRandomPairs @n = 10
GO
```
Inna wersja: wygenerować wszystkie możliwe pary i wziąć @n par.
![](https://i.imgur.com/LK86PWC.png)
Duży minus: jeśli małe @n to na próżno wygenerujemy dużą ilość par, gdy dużo kombinacji.

## Zadanie 3

```sql=
DROP TYPE IF EXISTS Readers
GO

CREATE TYPE Readers AS TABLE(czytelnik_id INT)
GO

DROP PROCEDURE IF EXISTS daysSum
GO

CREATE OR ALTER PROCEDURE daysSum @readers Czytelnicy READONLY AS
BEGIN
	SELECT 
		input.czytelnik_id, SUM(rental.Liczba_Dni) AS result
	FROM	
		@readers AS input,
		dbo.Czytelnik AS readers,
		dbo.Wypozyczenie AS rental
	WHERE
		readers.Czytelnik_ID = rental.Czytelnik_ID AND
		readers.Czytelnik_ID = input.czytelnik_id
	GROUP BY 
		input.czytelnik_id
END
GO

DECLARE @id_czyt Czytelnicy
INSERT INTO @id_czyt VALUES (1)
INSERT INTO @id_czyt VALUES (2)
INSERT INTO @id_czyt VALUES (3)

EXEC daysSum @id_czyt
GO
```

## Zadanie 4

```sql=
DROP PROCEDURE IF EXISTS specimensSum
GO

CREATE OR ALTER PROCEDURE specimensSum(@tytul NVARCHAR(300) = NULL, @autor NVARCHAR(200) = NULL, @rokwydania INT = NULL) AS
BEGIN
	DECLARE @query NVARCHAR(max)
	SET @query = N'
		SELECT SUM(quantity) AS result FROM (
			SELECT book.Ksiazka_ID, COUNT(specimen.Egzemplarz_ID) as quantity
			FROM dbo.Ksiazka AS book, dbo.Egzemplarz AS specimen
			WHERE
				(book.Ksiazka_ID = specimen.Ksiazka_ID) AND
				(book.Tytul = @tytul OR @tytul IS NULL) AND
				(book.Autor = @autor OR @autor IS NULL) AND
				(book.Rok_Wydania = @rokwydania OR @rokwydania IS NULL)
			GROUP BY book.Ksiazka_ID) AS summary'
	--PRINT @query
	EXECUTE sp_executesql @query, N'@tytul NVARCHAR(300), @autor NVARCHAR(200), @rokwydania INT', @tytul = @tytul, @autor = @autor, @rokwydania = @rokwydania
END
GO

EXEC specimensSum @tytul = NULL, @autor = 'Eric L. Brown', @rokwydania = NULL
GO
```
PLG 2021 ~ "To wygląda jak zwykły sql upchany na siłę w dynamicznego sql'a"
ale przeszło xd

Lepsze:
![](https://i.imgur.com/ufoWiVR.png)