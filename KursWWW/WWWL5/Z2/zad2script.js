function removeImg(source) {
  let start = source.indexOf("<img");
  let end = source.indexOf("/>");
  let toRemove = source.slice(start, end+2);
  source = source.replace(toRemove, "");
  return source;
}

$(document).ready(function() {
  $.get("https://tvn24.pl/najnowsze.xml", function(data) {
    let xml = data,
              xmlDoc = $.parseXML(xml),
              $xml = $( xmlDoc );
    let i=0;
    $(xml).find( "item" ).each(function () {
        if(i==5) {
          return 0;
        }
        i++;
        let link = "<a target='_blank' href='" + $(this).find("link").text() + "'>LINK</a>";    
        addNewRecord($("#articles"), link, $(this).find("title").text(), removeImg($(this).find("description").text()));
    });
    })
});


function addNewRecord(tableId, link, tytul, opis){
  tableId.append(
    "<tr><td>"+ link + "</a></td>" +
    "<td>" + tytul + "</td>" +
    "<td>" + opis + "</td></tr>");
}


function writeSearch(){
  let searchedPhrase = $("#searchphrase").val();
  let searchedResultsTable = $('#searchData');
  searchedResultsTable.html("");

  searchedResultsTable.append("<tr>" +
    "<th>Source</th>" +
    "<th>Title</th>" +
    "<th>Description</th>" +
    "</tr>");
  $.get("https://tvn24.pl/najnowsze.xml", function(news){
    for (var element of $(news).xpath("/rss/channel/item")){
      let opis = $(element).xpath("./description/text()")[1].data;
      console.log($(element).xpath("./description/text()"));
      if(opis.includes(searchedPhrase))
      {
        let link = "<a target='_blank' href='" + $(element).xpath("./link/text()")[0].data + "'>LINK</a>";
        let tytul = ($(element).xpath("./title/text()")[0].data);
        addNewRecord(searchedResultsTable, link, tytul, removeImg(opis));
      }
  }});
}