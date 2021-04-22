var wariant = "two";

$('input[type=radio][name=wariant]').change(function() {
    $('#sp').val('');
    stopSearching();
    cleanTextCss();
    if (this.value == "twoPoints") {
        wariant = "two";
    }
    else if (this.value == "fourPoints") {
        wariant = "four";
    } else if (this.value == "fourPointsB"){
        
        wariant = "fourB";
    }
});


//4pkt wariant z traversingiem

 
function searchVarB(){
    var searchedText = $('#sp').val();
    $('li').css("background-color", "gray");
    $('li').html(function(_, html){
        return html.replace('<span class="found">', "");
    });
    $('li').html(function(_, html){
        return html.replace('</span>', "");
    });

    $( "li" ).each(function( index ) {
        if($(this).text().includes(searchedText)){
            var text = $(this).text();
            $(this).html(text.replace(searchedText, '<span class="found">'+searchedText+'</span>')); 
        }
      });
}

$('#sp').keyup(function() {
    if(wariant == "fourB"){
        var searchedTextLength = $('#sp').val().length;
        if(searchedTextLength >= 3){
            searchVarB();
        }else{
            stopSearching();
        }
    }
    
  });



//4punkty bez traversingu

function search(){

    $('li').css("background-color", "gray");
    $('li').html(function(_, html){
        return html.replace('<span class="found">', "");
    });
    $('li').html(function(_, html){
        return html.replace('</span>', "");
    });


    var searchedText = $('#sp').val();
    $("li:contains("+searchedText+")").html(function(_, html) {
        return html.replace(searchedText, '<span class="found">'+searchedText+'</span>');
     });

}


function stopSearching(){
    $('li').html(function(_, html){
        return html.replace('<span class="found">', "");
    });
    $('li').html(function(_, html){
        return html.replace('</span>', "");
    });
    $('li').css("background-color", "white");
}

$('#sp').keyup(function() {
    if(wariant == "four"){
        var searchedTextLength = $('#sp').val().length;
        if(searchedTextLength >= 3){
            search();
        }else{
            stopSearching();
        }
    }
    
  });



//2punkty

function colorTextCss()
{
    var searchedText = $('#sp').val();
    console.log(searchedText);
    $( "li" ).each(function( index ) {
        if($(this).text().includes(searchedText)){
            //console.log( index + ": " + $( this ).text() );
            $(this).css("background-color", "yellow");
        }else{
            $(this).css("background-color", "grey");
        }
        
      });
}


function cleanTextCss(){
    $( "li" ).each(function( index ) {
            $(this).css("background-color", "white");
      });
}


$('#sp').keyup(function() {
    if(wariant == "two"){
        var searchedTextLength = $('#sp').val().length;
        if(searchedTextLength >= 2){
            colorTextCss();
        }

        if(searchedTextLength < 3){
            cleanTextCss();
        }
    }
    
  });


