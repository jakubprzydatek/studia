var przymiotniki = ["Piękny", "Zielony", "Niechlubny", "Złowieszczy", "Smutny", "Kolorowy", "Bezczelny", "Miły", "Nieszczęśliwy"];
var rzeczowniki = ["piłkarz", "kot", "dyrektor", "kominiarz", "sprzedawca", "aktor", "polityk", "mieszkaniec", "rolnik"];
var czasowniki = ["mył się", "urodził się", "postarzał się", "modlił się", "biegał", "gotował", "jadł", "czesał się", "uczył się"];
var przysłówki = ["nieefektywnie", "niezdrowo", "niedobrze", "w Ameryce", "na łące", "wcześniej", "podczas pracy zdalnej", "rano", "czasem"];

function loadpList(){
    var plist = $("ul[data-role='przymiotniki']");
    $.each(przymiotniki, function(i) {
        var li = $('<li/>')
            .addClass('menu-item')
            .attr('role', 'menu-item')
            .appendTo(plist);
        var liText = $('<span/>')
            .addClass('li-text')
            .text(przymiotniki[i])
            .appendTo(li);

    });
}

function loadrList(){
    var rlist = $("ul[data-role='rzeczowniki']");
    $.each(rzeczowniki, function(i) {
        var li = $('<li/>')
            .addClass('menu-item')
            .attr('role', 'menu-item')
            .appendTo(rlist);
        var liText = $('<span/>')
            .addClass('li-text')
            .text(rzeczowniki[i])
            .appendTo(li);

    });
}

function loadcList(){
    var clist = $("ul[data-role='czasowniki']");
    $.each(rzeczowniki, function(i) {
        var li = $('<li/>')
            .addClass('menu-item')
            .attr('role', 'menu-item')
            .appendTo(clist);
        var liText = $('<span/>')
            .addClass('li-text')
            .text(czasowniki[i])
            .appendTo(li);

    });
}

function loadpsList(){
    var pslist = $("ul[data-role='przyslowki']");
    $.each(rzeczowniki, function(i) {
        var li = $('<li/>')
            .addClass('menu-item')
            .attr('role', 'menu-item')
            .appendTo(pslist);
        var liText = $('<span/>')
            .addClass('li-text')
            .text(przysłówki[i])
            .appendTo(li);

    });
}

function loadLists(){
    loadpList();
    loadrList();
    loadcList();
    loadpsList();
}

function generate(){
    var generatedText = przymiotniki[getRandomInt()] + " " + rzeczowniki[getRandomInt()] + " " + czasowniki[getRandomInt()] + " " + przysłówki[getRandomInt()];
    console.log(generatedText);
    $('#generatedBS').val(generatedText);
}

function getRandomInt() {
    var min = 0;
    var max = 8;
    return Math.floor(Math.random() * (max - min)) + min;
  }