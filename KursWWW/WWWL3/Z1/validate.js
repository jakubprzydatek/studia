function nrKontaValidation()
{
    var nrkontaStrPattern = new RegExp(/^\d{26}$/);
    var nrkontaStr = document.getElementById("nrkonta").value;
    console.log(nrkontaStrPattern.test(nrkontaStr));
    if(!nrkontaStrPattern.test(nrkontaStr))
    {
        alert("Numer konta musi składać się z 26 cyfr");
    }
}

function peselValidation()
{
    var peselStrPattern = new RegExp(/^\d{11}$/);
    var peselStr = document.getElementById("pesel").value;
    console.log(peselStrPattern.test(peselStr));
    if(!peselStrPattern.test(peselStr))
    {
        alert("PESEL musi składać się z 11 cyfr");
    }
}

function dateValidation()
{
    var dateStrPattern = new RegExp(/\d{4}([-]\d{2}){2}/);
    var dateStr = document.getElementById("data").value;
    console.log(dateStrPattern.test(dateStr));
    console.log(dateStr);
    if(!dateStrPattern.test(dateStr))
    {
        alert("Prawidłowy format daty to dd.mm.rrrr");
    }
}

function emailValidation()
{
    var emailStrPattern = new RegExp(/^\S+@\S+\.\S+$/);
    var emailStr = document.getElementById("email").value;
    console.log(emailStrPattern.test(emailStr));
    console.log(emailStr);
    if(!emailStrPattern.test(emailStr))
    {
        alert("Nieprawidłowy adres email");
    }
}


function validation()
{
    nrKontaValidation()
    peselValidation()
    dateValidation()
    emailValidation()
}


const nrkonta = document.getElementById('nrkonta');
nrkonta.addEventListener('focusout', (event) => {
    nrKontaValidation();
});

const pesel = document.getElementById('pesel');
pesel.addEventListener('focusout', (event) => {
    peselValidation();
});

const data = document.getElementById('data');
data.addEventListener('focusout', (event) => {
    dateValidation();
});

const email = document.getElementById('email');
email.addEventListener('focusout', (event) => {
    emailValidation();
});

const submit_btn = document.getElementById('submit_btn');
submit_btn.addEventListener('click', (event) => {
    validation();
});


