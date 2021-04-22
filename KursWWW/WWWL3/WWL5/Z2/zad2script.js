

$.get("https://tvn24.pl/najnowsze.xml", function(data){
  alert("Data: " + data);
  var xml = data, 
            xmlDoc = $.parseXML(xml), 
            $xml = $(xmlDoc); 
  $(xml).find('title').each(function()
      {
       console.log($(this).text());
      });

});


$("#addUsrBtn").click(function() {
  var imie = $('#name').val();
  var nazwisko = $('#surname').val();
  var city = $('#city').val();
  var kod = $('#kod').val();
  var data = $('#datepicker').datepicker({ dateFormat: 'mm-dd-yy' }).val();
  recordsNubmer +=1;
  $( "#users-table" ).append( "<tr data-number='"+ recordsNubmer +"'>" +
        "<td>" + imie + "</td>" +
        "<td>" + nazwisko + "</td>" +
        "<td>" + city + "</td>" +
        "<td>" + kod + "</td>" +
        "<td>" + data + "</td>" +
        "<td><button id='removeDialogBtn' onclick='test($(this))' data-number='"+ recordsNubmer +"'>Usuń</button></td>" +
      "</tr>" );
  $("#dialog").dialog("close");
  return false;
});

