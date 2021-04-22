<!doctype HTML>
<head>
<meta chrset="utf-8">
<title>Zadanie1</title>
</head>
<body>
<h1>Wywołanie phpinfo()</h1>

<?php

	phpinfo();
}

?>
<h1>Tablica $_SERVER</h1>
<?php
    foreach ($_SERVER as $key => $value)
        echo $key.'='.$value.'<br />';
?>

</body>