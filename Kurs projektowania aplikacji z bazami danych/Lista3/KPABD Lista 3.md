# KPABD Lista 3

## Zadanie 1
```sql=
DROP TABLE IF EXISTS Products
GO

CREATE TABLE Products(
	ID INT PRIMARY KEY,
	ProductName VARCHAR(30)
)
GO


DROP TABLE IF EXISTS Rates
GO

CREATE TABLE Rates(
	Currency VARCHAR(3) PRIMARY KEY,
	PricePLN MONEY
)
GO

DROP TABLE IF EXISTS Prices
GO

CREATE TABLE Prices(
	ProductID INT REFERENCES Products(ID),
	Currency VARCHAR(3) REFERENCES Rates(Currency),
	Price MONEY
)
GO

INSERT INTO Products VALUES(1, 'winogrona')
INSERT INTO Products VALUES(2, 'kiwi')
INSERT INTO Products VALUES(3, 'marchew')

INSERT INTO Rates VALUES('PLN', 1.00)
INSERT INTO Rates VALUES('EUR', 4.63)
INSERT INTO Rates VALUES('USD', 3.93)
INSERT INTO Rates VALUES('RUB', 0.05)
INSERT INTO Rates VALUES('CZK', 0.18)

INSERT INTO Prices VALUES(1, 'PLN', 8.00)
INSERT INTO Prices VALUES(2, 'PLN', 7.00)
INSERT INTO Prices VALUES(3, 'PLN', 3.49)
INSERT INTO Prices VALUES(1, 'EUR', 1.72)
INSERT INTO Prices VALUES(1, 'USD', 2.03)
INSERT INTO Prices VALUES(2, 'RUB', 140.00)
INSERT INTO Prices VALUES(2, 'CZK', 38.88)
INSERT INTO Prices VALUES(3, 'EUR', 0.75)
INSERT INTO Prices VALUES(3, 'CZK', 19.38)

--------------------------------------------------------------------

DECLARE rates_cursor CURSOR FOR SELECT Currency, PricePLN FROM Rates
DECLARE products_cursor CURSOR FOR SELECT ProductID, Currency, Price FROM Prices
DECLARE @r_curr VARCHAR(3), @r_pln MONEY
DECLARE @p_productid INT, @p_curr VARCHAR(3), @p_price MONEY
DECLARE @current_price MONEY
DECLARE @delete BIT

OPEN products_cursor
FETCH NEXT FROM products_cursor INTO @p_productid, @p_curr, @p_price
SET @current_price = (SELECT Price FROM Prices WHERE ProductID = @p_productid AND Currency = 'PLN')
WHILE (@@FETCH_STATUS = 0)
BEGIN
	OPEN rates_cursor
	FETCH NEXT FROM rates_cursor INTO @r_curr, @r_pln
	SET @delete = 1
	WHILE(@@FETCH_STATUS = 0)
	BEGIN
		IF(@p_curr = @r_curr)
		BEGIN
			SET @p_price = @current_price / @r_pln
			UPDATE Prices
			SET Price = @p_price
			WHERE ProductID = @p_productid AND Currency = @p_curr
			SET @delete = 0
		END
		FETCH NEXT FROM rates_cursor INTO @r_curr, @r_pln
	END
	IF(@delete=1)
		DELETE FROM Prices WHERE ProductID = @p_productid AND Currency = @p_curr
	CLOSE rates_cursor
	FETCH NEXT FROM products_cursor INTO @p_productid, @p_curr, @p_price
	SET @current_price = (SELECT Price FROM Prices WHERE ProductID = @p_productid AND Currency = 'PLN')
END
CLOSE products_cursor
DEALLOCATE rates_cursor
DEALLOCATE products_cursor
GO
```

## Zadanie 2
```sql=
DROP TABLE IF EXISTS Employees
GO

CREATE TABLE Employees(
	ID INT PRIMARY KEY,
	SalaryGros MONEY
)
GO

DROP TABLE IF EXISTS SalaryHistory
GO

CREATE TABLE SalaryHistory(
	ID INT IDENTITY PRIMARY KEY,
	EmployeeID INT,
	Year INT,
	Month INT,
	SalaryNet MONEY,
	SalaryGros MONEY
)
GO

INSERT INTO Employees VALUES(1, 1002)
INSERT INTO Employees VALUES(2, 2002)
INSERT INTO Employees VALUES(3, 3002)
INSERT INTO Employees VALUES(4, 30238)
GO

INSERT INTO SalaryHistory VALUES(1, 2021, 1, NULL, 1000)
INSERT INTO SalaryHistory VALUES(1, 2021, 2, NULL, 1001)
INSERT INTO SalaryHistory VALUES(2, 2021, 2, NULL, 2001)
INSERT INTO SalaryHistory VALUES(3, 2021, 1, NULL, 3000)
INSERT INTO SalaryHistory VALUES(1, 2020, 1, NULL, 100)
INSERT INTO SalaryHistory VALUES(4, 2021, 1, NULL, 30201)
INSERT INTO SalaryHistory VALUES(4, 2021, 2, NULL, 30238)
GO

DROP TABLE IF EXISTS calcSalaryErrors
GO

CREATE TABLE calcSalaryErrors(
	ID INT IDENTITY PRIMARY KEY,
	EmployeeID INT,
	Year INT,
	Month INT
)
GO

-------------------------------------

CREATE OR ALTER PROCEDURE calcSalary @month INT AS
BEGIN
	TRUNCATE TABLE calcSalaryErrors
	DECLARE cEmp CURSOR FOR SELECT ID, SalaryGros FROM Employees ORDER BY ID
	DECLARE cHis CURSOR FOR SELECT ID, EmployeeID, Year, Month, SalaryNet, SalaryGros FROM SalaryHistory ORDER BY EmployeeID, Year, Month
	DECLARE @e_id INT, @e_gros MONEY
	DECLARE @h_id INT, @h_eid INT, @h_year INT, @h_month INT, @h_net MONEY, @h_gros MONEY
	DECLARE @prevMonth INT,  @sum_gros MONEY, @sum_net MONEY
	DECLARE @currYear INT = YEAR(GETDATE()), @currMonth INT = MONTH(GETDATE())
	OPEN cEmp
	FETCH NEXT FROM cEmp INTO @e_id, @e_gros
	WHILE(@@FETCH_STATUS = 0)
	BEGIN
		SET @sum_gros = 0
		OPEN cHis
		FETCH NEXT FROM cHis INTO @h_id, @h_eid, @h_year, @h_month, @h_net, @h_gros
		WHILE(@@FETCH_STATUS = 0)
		BEGIN
			IF(@e_id=@h_eid AND @currYear=@h_year AND @h_month <= @month)
			BEGIN
				IF(@sum_gros > 0 AND @h_month-@prevMonth > 1)
				BEGIN
					INSERT INTO calcSalaryErrors VALUES(@e_id, @currYear, @h_month)
					BREAK
				END
				SET @prevMonth = @h_month
				SET @sum_gros = @sum_gros + @h_gros
			END
            FETCH NEXT FROM cHis INTO @h_id, @h_eid, @h_year, @h_month, @h_net, @h_gros
			IF(@@FETCH_STATUS = -1 AND @sum_gros > 0)
			BEGIN
				IF(@prevMonth < @month AND @currMonth<=@month)
				BEGIN
					IF(@currMonth-@prevMonth > 1)
					BEGIN
						INSERT INTO calcSalaryErrors VALUES(@e_id, @currYear, @currMonth)
						BREAK
					END
					SET @sum_gros = @sum_gros + @e_gros
				END
				DECLARE @overflow INT = 0
				IF(@sum_gros > 85528)
				BEGIN
					SET @overflow = @sum_gros - 85528
					SET @sum_gros = 85528
				END
				SET @sum_net = (@sum_gros * 0.82) + (@overflow * 0.68)
				PRINT CAST(@e_id AS CHAR(10)) + ' ' + CAST((@sum_gros+@overflow) AS CHAR(10)) + ' ' + CAST(@sum_net AS CHAR(10))
			END
		END
		CLOSE cHis
		FETCH NEXT FROM cEmp INTO @e_id, @e_gros
	END
CLOSE cEmp
DEALLOCATE cEmp
DEALLOCATE cHis
END
GO

EXEC calcSalary @month = 5
GO

SELECT * FROM Employees
SELECT * FROM SalaryHistory
SELECT * FROM calcSalaryErrors
```

## Zadanie 3
```sql=
DROP TABLE IF EXISTS Cache
GO

CREATE TABLE Cache(
	ID INT IDENTITY PRIMARY KEY,
	UrlAddress VARCHAR(255),
	LastAccess DATETIME
)
GO

DROP TABLE IF EXISTS History
GO

CREATE TABLE History(
	ID INT IDENTITY PRIMARY KEY,
	UrlAddress VARCHAR(255),
	LastAccess DATETIME
)
GO

DROP TABLE IF EXISTS Params
GO

CREATE TABLE Params(
	Nam VARCHAR(255),
	Val INT
)
GO

INSERT INTO Params VALUES('max_cache', 3)
GO

-------------------------------------------

CREATE OR ALTER TRIGGER cacheCheck ON Cache INSTEAD OF INSERT AS
BEGIN
	DECLARE @new_address VARCHAR(255)
	DECLARE @new_last DATETIME
	SELECT @new_address = UrlAddress, @new_last = LastAccess FROM INSERTED
	IF EXISTS(SELECT 1 FROM Cache WHERE UrlAddress = @new_address)
		UPDATE Cache SET LastAccess = @new_last WHERE UrlAddress = @new_address
	ELSE
	BEGIN
		DECLARE @rows INT = (SELECT COUNT(*) FROM Cache)
		DECLARE @maxRows INT = (SELECT TOP 1 Val FROM Params)
		IF (@rows < @maxRows)
			INSERT INTO Cache SELECT UrlAddress, LastAccess FROM INSERTED
		ELSE
		BEGIN
			DECLARE @old_id INT, @old_address VARCHAR(255), @old_last DATETIME
			SELECT TOP 1 @old_id = ID, @old_address = UrlAddress, @old_last = LastAccess FROM Cache ORDER BY LastAccess
			IF EXISTS(SELECT 1 FROM History WHERE UrlAddress = @old_address)
				UPDATE History SET LastAccess = @old_last WHERE UrlAddress = @old_address
			ELSE
				INSERT INTO History VALUES(@old_address, @old_last)
			DELETE FROM Cache WHERE ID = @old_id
			INSERT INTO Cache SELECT UrlAddress, LastAccess FROM INSERTED				
		END
	END
END
GO

INSERT INTO Cache VALUES('google.com', '2021-03-27 18:12:00')
INSERT INTO Cache VALUES('google.com', '2021-03-27 18:15:00')
INSERT INTO Cache VALUES('reddit.com', '2021-03-27 19:00:00')
INSERT INTO Cache VALUES('youtube.com', '2021-03-27 19:30:00')
INSERT INTO Cache VALUES('facebook.com', '2021-03-27 20:00:00')
```