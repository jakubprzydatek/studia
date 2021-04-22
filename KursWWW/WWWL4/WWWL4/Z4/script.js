$.datepicker.setDefaults({
    dateFormat: 'dd-mm-yy'
});
$( function() {
    $( "#datepicker" ).datepicker();
});

var recordsNubmer = 0;
$(function() {
    $("#dialog").dialog({
      autoOpen : false, modal : true, show : "blind", hide : "blind"
    });
  
    $("#showBtn").click(function() {
      $("#dialog").dialog("open");
      return false;
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


$(function() {
    $("#removeDialog").dialog({
      autoOpen : false, modal : true, show : "blind", hide : "blind"
    });
  
    $("#removeDialogBtn").click(function() {
      $("#removeDialog").dialog("open");
      return false;
    });
});

  $("#yesBtn").click(function() {
    
  });


var numer;

  function test(wiersz){
    numer = wiersz.data("number");
    $("#removeDialog").dialog("open");
    //$("tr[data-number='"+numer+"']").remove();
  }



  $("#yesBtn").click(function() {
    $("tr[data-number='"+numer+"']").remove();
    $("#removeDialog").dialog("close");
  });

  $("#noBtn").click(function() {
    $("#removeDialog").dialog("close");
  });


