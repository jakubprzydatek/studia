<?php
$IsPostFormData = (isset($_POST["sent"]) && ($_POST["sent"]=="y"));
$IsGetFormData = (isset($_GET["sent"]) && ($_GET["sent"]=="y"));
$imieError = "";
$nazwiskoError = "";
$kartaError = "";
$dataError = "";
$cvcError = "";
$emailError = "";
$telefonError = "";
$kwotaError  = "" ;
?>
<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    if (empty($_POST["imie"])) {
        $imieError = "* Musisz podac imie";
    } else {
        if (!preg_match("/^[a-zA-Z]*$/", $_POST["imie"])) {
            $nameErr = "* Tylko litery są dozwolone";
        }
    }

    if (empty($_POST["nazwisko"])) {
        $nazwiskoError = "* Musisz podac nazwisko";
    } else {
        if (!preg_match("/^[a-zA-Z]*$/", $_POST["imie"])) {
            $nazwiskoError = "* Tylko litery są dozwolone";
        }
    }

    if ($_POST["karta"][0] == "" or $_POST["karta"][1] == "" or $_POST["karta"][2] == "" or $_POST["karta"][3] == "") {
        $kartaError = "* Musisz podac numer karty";
    } else {
        if (!preg_match("/[0-9]{4}/", $_POST["karta"][0]) && !preg_match("/[0-9]{4}/", $_POST["karta"][1]) && !preg_match("/[0-9]{4}/", $_POST["karta"][2]) && !preg_match("/[0-9]{4}/", $_POST["karta"][3])) {
            $kartaError = "* Tylko cyfry są dozwolone podaj dobry numer karty";
        }
    }


    if ($_POST["data"][0] =="" or $_POST["data"][1] =="") {
        $dataError = "* Musisz podac date waznosci karty";
    } else {
        if (!preg_match("/(1[0-2]|0[0-9])/", $_POST["data"][0]) && !preg_match("/d{2}/",  $_POST["data"][1] )) {
            $dataError = "* Tylko cyfry są dozwolone - podaj poprawną date";
        }
    }

    if (empty($_POST["cvc"])) {
        $cvcError = "* Musisz podac cvc karty";
    } else {
        if (!preg_match("/[0-9]{3}/", $_POST["cvc"])) {
            $cvcError = "* Tylko cyfry są dozwolone - podaj poprawną cvc";
        }
    }


     if (empty($_POST["email"])) {
         $emailError = "* Musisz podac cvc karty";
     } else {

         if (!filter_var($_POST["email"], FILTER_VALIDATE_EMAIL)) {
             $emailError = "* podaj poprawny adres email";
         }
     }

    if (empty($_POST["telefon"])) {
        $telefonError = "* Podaj numer telefonu";
    } else {
        if (!preg_match("/[0-9]{9}/", $_POST["telefon"])) {
            $telefonError = "* Tylko cyfry są dozwolone - podaj poprawną kwote ";
        }
    }

    if (empty($_POST["kwota"])) {
        $kwotaError = "* Podaj kwote";
    } else {
        if (!preg_match("/[0-9]+/", $_POST["kwota"])) {
            $kwotaError = "* Tylko cyfry są dozwolone - podaj poprawną kwote ";
        }
    }

}
?>