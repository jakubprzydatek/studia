# KPABD Lista 5

## Zadanie 2

```javascript=
db.Ksiazki.insertMany([{
    "_id": 1,
    "Tytul": "Czysty kod. Podręcznik dobrego programisty",
    "ISBN": "978-83-246-2188-0",
    "Autor": "Robert C. Martin",
    "RokWydania": 2010,
    "Cena": 68,
    "Egzemplarze": [
        {
            "Sygnatura": "S0001",
        },
        {
            "Sygnatura": "S0003"
        }
    ]
},
{
    "_id": 2,
    "Tytul": "PHP, MySQL i MVC. Tworzenie witryn WWW opartych na bazie danych",
    "ISBN": "978-83-246-1258-1",
    "Autor": "Włodzimierz Gajda",
    "RokWydania": 2010,
    "Cena": 79,
    "Egzemplarze": [
        {
            "Sygnatura": "S0002",
        }
    ]
}])


db.Czytelnicy.insertMany([{
    "_id": 1,
    "PESEL": "65120122222",
    "Nazwisko": "Nowak",
    "Miasto": "Warszawa",
    "DataUrodzenia": new Date("1965-12-01"),
    "OstatnieWypozyczenie": new Date("2020-02-02"),
    "Wypozyczenia": [
        {   
            SyngaturaEgzemplarza: "S0002",
            Data: new Date("2020-02-02"),
            LiczbaDni: 14
        },
        {   
            SyngaturaEgzemplarza: "S0001",
            Data: new Date("2019-12-01"),
            LiczbaDni: 28
        }
    ]
},
{
    "_id": 2,
    "PESEL": "60101033333",
    "Nazwisko": "Maliniak",
    "Miasto": "Wrocław",
    "DataUrodzenia": new Date("1960-10-10"),
    "OstatnieWypozyczenie": new Date("2020-01-02"),
    "Wypozyczenia": [
        {   
            SygnaturaEgzemplarza: "S0002",
            Data: new Date("2020-01-02"),
            LiczbaDni: 14
        },
        {   
            SygnaturaEgzemplarza: "S0003",
            Data: new Date("2019-12-01"),
            LiczbaDni: 28
        }
    ]
}])
```

## Zadanie 3
```javascript=
db.runCommand( {
   collMod: "Ksiazki",
   validator: { $jsonSchema: {
      bsonType: "object",
      required: [ "Tytul", "Autor", "RokWydania" ],
      properties: {
         Tytul: {
            bsonType: "string",
            description: "must be a string and is required"
         },
         Autor: {
            bsonType: "string",
            description: "must be a string and is required"
         },
         RokWydania: {
            bsonType: "int",
            description: "must be an int and is required"
         },
         Egzemplarze: {
             bsonType: ["array"],
             minItems: 0,
             uniqueItems: true,
             items: {
                    bsonType: ["object"],
                    required: ["Sygnatura"],
                    properties: {
                        Sygnatura: {
                            bsonType: "string",
                            description: "must be a string and is required"
                        }
                    }
         }
      }
      }
   } },
   validationLevel: "moderate"
} )

db.runCommand(
{
    collMod: "Czytelnicy",
    validator: { $jsonSchema: {
        bsonType: "object",
        required: ["PESEL", "Nazwisko", "Miasto", "DataUrodzenia"],
        properties: {
            PESEL: {
                bsonType: "string",
                pattern: "^[0-9]{11}$",
            },
            Nazwisko: {
                bsonType: "string",
                description: "must be a string and is required"
            },
            Miasto: {
                bsonType: "string",
                description: "must be a string and is required"
            },
            DataUrodzenia: {
                bsonType: "date",
                description: "must be a date and is required"
            },
            OstatnieWypozyczenie: {
                bsonType: "date",
                description: "must be a date and isn't required"
            },
            Wypozyczenia:{
                bsonType: "array",
                minItems: 0,
                uniqueItems: true,
                items: {
                    bsonType: ["object"],
                    required: ["SygnaturaEgzemplarza", "Data", "LiczbaDni"],
                    properties: {
                        SygnaturaEgzemplarza: {
                            bsonType: "string"
                        },
                        Data: {
                            bsonType: "date"
                        },
                        LiczbaDni: {
                            bsonType: "int"
                        }
                    }
                }
            }
        }
    }}
})
```

```javascript=
db.Ksiazki.insertOne({
    "_id": 3,
    "Tytul": "Sklepy cynamonowe",
    "ISBN": "978-83-246-2188-5",
    "Autor": 123,
    "RokWydania": 2015,
    "Cena": 40,
    "Egzemplarze": [
        {
            "Sygnatura": "S0005",
        }
    ]
})
```

## Zadanie 4

### a
```javascript=
db.Czytelnicy.find().sort({"Nazwisko":-1}).skip(db.Czytelnicy.countDocuments()/2).limit(2)
```
### b
```javascript=
db.Czytelnicy.find({"Wypozyczenia.LiczbaDni" : {$gt: 10}})
//Wypożyczenia dłuższe niż 10 dni
```

## Zadanie 5
Otwieramy cmd i przechodzimy do folderu mongoDB/bin
```
start "A" mongod --dbpath G:\MongoDB\data\db1 --port 10000 --replSet "demo"
start "B" mongod --dbpath G:\MongoDB\data\db2 --port 20000 --replSet "demo"
start "C" mongod --dbpath G:\MongoDB\data\db3 --port 30000 --replSet "demo"

mongo --port 10000

var rsConfig={ _id: "demo", members: [{_id: 0, host: 'localhost:10000', priority: 10}, {_id: 1, host: 'localhost:20000'}, {_id: 2, host: 'localhost:30000', arbiterOnly: true}]};

rsConfig

rs.initiate(rsConfig)

db.books.save({_id:1, title:"Mistrz i Małgorzata"})
db.books.find()
```
**W drugim oknie cmd**
```
mongo --port=20000

db.books.save({_id:2, title:"Wyznania gejszy"})
db.books.find();
db.setSlaveOk();
db.books.find();
```
ale nadal nie można zapisywać

zamykamy okno "A"

W oknie w którym było secondary widzimy po kliknięciu enter że zmieniło się na primary

Pierwszą komendą odpalamy "A"

primary na 20000 znowu stało się secondary