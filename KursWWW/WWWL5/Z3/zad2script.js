$(document).ready(function() {
    $.getJSON('najnowsze.json', {}, function (data) {
        console.log(data);
        for (let i = 0; i < 5; i++) {
            let link = "<a target='_blank' href='"+ Mustache.render(`{{rss.channel.item.${i}.link}}`,data) + "'>LINK</a>" ;
            let tytul = Mustache.render(`{{rss.channel.item.${i}.title}}`,data);
            let opis = Mustache.render(`{{{rss.channel.item.${i}.description.__cdata}}}`,data);
            addNewRecord($("#articles"), link, tytul, opis);
        }
    });

    function addNewRecord(tableId, link, tytul, opis){
        tableId.append(
            "<tr><td>"+ link + "</td>" +
            "<td>" + tytul + "</td>" +
            "<td>" + opis + "</td></tr>");
    }
});