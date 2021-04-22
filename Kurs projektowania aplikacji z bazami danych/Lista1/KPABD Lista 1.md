# KPABD Lista 1

## Zadanie 1
```sql=
SELECT DISTINCT SalesLT.Address.City
FROM SalesLT.Address, SalesLT.SalesOrderHeader
WHERE SalesLT.Address.AddressID = SalesLT.SalesOrderHeader.ShipToAddressID 
AND SalesLT.SalesOrderHeader.Status = 5 --kod 5 w dostawach oznacza "dostarczone"
ORDER BY SalesLT.Address.City ASC
```

## Zadanie 2
```sql=
SELECT SalesLT.ProductModel.Name, COUNT(SalesLT.Product.ProductID)
FROM SalesLT.ProductModel, SalesLT.Product
WHERE SalesLT.ProductModel.ProductModelID = SalesLT.Product.ProductModelID
GROUP BY SalesLT.ProductModel.ProductModelID, SalesLT.ProductModel.Name
HAVING COUNT(SalesLT.Product.ProductID) > 1
```

## Zadanie 3
```sql=
SELECT SalesLT.Address.City, COUNT(DISTINCT SalesLT.Customer.SalesPerson) AS 'SalesPerson per City', COUNT(DISTINCT SalesLT.Customer.CustomerID) AS 'Customers per City'
FROM SalesLT.CustomerAddress
JOIN SalesLT.Address ON SalesLT.CustomerAddress.AddressID = SalesLT.Address.AddressID
JOIN SalesLT.Customer ON SalesLT.CustomerAddress.CustomerID = SalesLT.Customer.CustomerID
GROUP BY SalesLT.Address.City

```

## Zadanie 4
https://sqlserverdlaopornych.wordpress.com/2016/10/29/sql-left-join/
```sql=
WITH tree AS
(
	SELECT leaf.ProductCategoryID FROM SalesLT.ProductCategory AS leaf
	LEFT JOIN SalesLT.ProductCategory AS child ON child.ParentProductCategoryID = leaf.ProductCategoryID
	WHERE child.ProductCategoryID IS NULL
)
SELECT SalesLT.ProductCategory.Name AS CategoryName, SalesLT.Product.Name
FROM SalesLT.Product 
JOIN SalesLT.ProductCategory ON SalesLT.ProductCategory.ProductCategoryID = SalesLT.Product.ProductCategoryID
WHERE SalesLT.ProductCategory.ProductCategoryID NOT IN (SELECT ProductCategoryID FROM tree)
```

```sql=
UPDATE SalesLT.Product
SET ProductCategoryID = 3
WHERE ProductID = 712
--wczesniej ProductCategoryID było 23
```

```sql=
UPDATE SalesLT.ProductCategory
SET ParentProductCategoryID = 5
WHERE ProductCategoryID = 6
--wczesniej ParentProductCategoryID = 1
```
Wersja mądra bez CTE od Danelskiego
![](https://i.imgur.com/xo4qOiQ.png)


## Zadanie 5
```sql=
SELECT SalesLT.Customer.LastName, SalesLT.Customer.FirstName, SUM(SalesLT.SalesOrderDetail.UnitPriceDiscount)
FROM SalesLT.SalesOrderHeader 
JOIN SalesLT.Customer ON SalesLT.SalesOrderHeader.CustomerID = SalesLT.Customer.CustomerID
JOIN SalesLT.SalesOrderDetail ON SalesLT.SalesOrderHeader.SalesOrderID = SalesLT.SalesOrderDetail.SalesOrderID 
GROUP BY SalesLT.Customer.LastName, SalesLT.Customer.FirstName
```
![](https://i.imgur.com/tX1YLkP.png)

## Zadanie 6
```sql=
ALTER TABLE SalesLT.Customer
ADD CreditCardNumber VARCHAR(16) NOT NULL DEFAULT '0000000000000000' CHECK(CreditCardNumber LIKE '%[0-9]%')
```
Jako walidację z CHECK można sprawdzić czy w numerze karty występują same cyfry.

## Zadanie 7
https://stackoverflow.com/questions/6720050/foreign-key-constraints-when-to-use-on-update-and-on-delete
```sql=
DROP TABLE IF EXISTS M1
GO
DROP TABLE IF EXISTS S1
GO

CREATE TABLE M1 (
	K INT NOT NULL,
	V VARCHAR(20),
	PRIMARY KEY(K)
)

CREATE TABLE S1 (
	K INT NOT NULL,
	MFK INT,
	V VARCHAR(20),
	PRIMARY KEY(K),
	FOREIGN KEY (MFK) REFERENCES M1(K)
)

--ON UPDATE
	--NO ACTION nie zadziała, bo foreign key musi się odnosić do jakiegoś pola
	--SET NULL zadziała i ustawi wartość na null
	--CASCADE zaktualizuje wartość
--ON DELETE
	--NO ACTION nie zadziała, bo foreign key musi się odnosić do jakiegoś pola
	--SET NULL zadziała i ustawi wartość na null
	--CASCADE usunie wiersz

INSERT INTO M1 VALUES(5, 'asd')
INSERT INTO S1 VALUES(1, 5, 'asd')

UPDATE M1 SET K = 2 WHERE K = 5
DELETE FROM M1 WHERE K = 5

SELECT * FROM S1
JOIN M1 ON M1.K = S1.MFK

------------------------------------

DROP TABLE IF EXISTS M2
GO
DROP TABLE IF EXISTS S2
GO

CREATE TABLE M2 (
	K1 INT NOT NULL,
	K2 INT NOT NULL,
	V VARCHAR(20),
	PRIMARY KEY(K1, K2)
)

CREATE TABLE S2 (
	K INT NOT NULL,
	MFK1 INT,
	MFK2 INT,
	V VARCHAR(20),
	PRIMARY KEY(K),
	FOREIGN KEY (MFK1, MFK2) REFERENCES M2(K1, K2)
)

INSERT INTO M2 VALUES(1, 2, 'asd')
INSERT INTO S2 VALUES(10, 1, 2, 'asd')

UPDATE M2 SET K1 = 3 WHERE K1 = 1
DELETE FROM M2 WHERE K1 = 5

SELECT * FROM S2
JOIN M2 ON M2.K1 = S2.MFK1 AND M2.K2 = S2.MFK2
```