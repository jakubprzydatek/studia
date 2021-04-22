<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Dane osobowe</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
td,th,body { font-family:Verdana, Arial, Helvetica, sans-serif; font-size:10pt; }
/* Chrome, Safari, Edge, Opera */
input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
    -webkit-appearance: none;
    margin: 0;
}

/* Firefox */
input[type=number] {
    -moz-appearance: textfield;
}
.error {color: red;}
</style>
</head>

<body>

<h3 align="center">Dane osobowe</h3>

<form name="dane" action="dane2.php" method="post">
<table align="center" cellpadding="4" cellspacing="2" border="0" bgcolor="#FF9900">
    <tr><th align="left">Imię:</th><td><input name="imie" type="text" size="15" maxlength="20" value= "<?php echo $_POST["imie"]; ?>" </td><td><span class="error"><?php echo $imieError;?></td></tr>
    <tr><th align="left">Nazwisko:</th><td><input name="nazwisko" type="text" size="20" maxlength="40" value="<?php echo $_POST["nazwisko"]; ?>"></td><td><span class="error"><?php echo $nazwiskoError;?></span></td></tr>
    <tr><th align="left">Numer karty:</th><td>
            <input name="karta[]" type="number" size="6" maxlength="4" minlength="4" value="<?php echo $_POST["karta"][0]; ?>">
            <input name="karta[]" type="number" size="6" maxlength="4" minlength="4" value="<?php echo $_POST["karta"][1]; ?>">
            <input name="karta[]" type="number" size="6" maxlength="4" minlength="4" value="<?php echo $_POST["karta"][2]; ?>">
            <input name="karta[]" type="number" size="6" maxlength="4" minlength="4" value="<?php echo $_POST["karta"][3]; ?>"></td><td><span class="error"><?php echo $kartaError;?></span></td></tr>
<tr><th align="left">ważność karty</th><td>
        <input name="data[]" type="number" size="4" maxlength="2" minlength="2" value="<?php echo $_POST["data"][0]; ?>">
        <input name="data[]" type="number" size="4" maxlength="2" minlength="2" value="<?php echo $_POST["data"][1]; ?>"></td><td><span class="error"><?php echo $dataError;?></span></td></tr>
    <tr><th align="left">cvc</th><td><input name="cvc" type="number" size="4" maxlength="3" value="<?php echo $_POST["cvc"]; ?>"></td><td><span class="error"> <?php echo $cvcError;?></span></td></tr>
    <tr><th align="left">email:</th><td><input name="email" type="text" size="20" value="<?php echo $_POST["email"]; ?>"></td><td><span class="error"><?php echo $emailError;?></span></td></tr>
    <tr><th align="left">telefon:</th><td><input name="telefon" type="text" size="20" value="<?php echo $_POST["telefon"]; ?>"></td><td><span class="error"><?php echo $telefonError;?></span></td></tr>
    <tr><th align="left">kwota:</th><td><input name="kwota" type="text" size="20" value="<?php echo $_POST["kwota"]; ?>"></td><td><span class="error"><?php echo $kwotaError;?></span></td></tr>


								   
<tr><td align="right" colspan="2"><input type="submit" value="Wyślij"></td></tr>
</table>
<input name="sent" type="hidden" value="y">
</form>

<?php
if ( $IsPostFormData ):
?>
<table cellpadding="4" cellspacing="2" border="1" align="center">
    <tr><th>Imię:</th><td><?php echo $_POST["imie"]; ?></td></tr>
    <tr><th>Nazwisko:</th><td><?php echo $_POST["nazwisko"]; ?></td></tr>
    <tr><th>Numer karty:</th><td><?php echo join($_POST["karta"],"-"); ?></td></tr>
    <tr><th>ważność karty:</th><td><?php echo join($_POST["data"],"/"); ?></td></tr>
    <tr><th>cvc</th><td><?php echo $_POST["cvc"]; ?></td></tr>
    <tr><th>email</th><td><?php echo $_POST["email"]; ?></td></tr>
    <tr><th>nr telefonu</th><td><?php echo $_POST["telefon"]; ?></td></tr>
    <tr><th>kwota</th><td><?php echo $_POST["kwota"]; ?></td></tr>
</table>

<?php
endif;
?>


</body>
</html>