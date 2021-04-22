function markAll()
{
    $('img').each(function() {
        markImg($(this));
  });
}

function unmarkAll()
{
    $('img').each(function() {
        unmarkImg($(this));
  });
}



$('img').click(function() {
    if($(this).data("marked") == false){
        markImg($(this));
    }else{
        unmarkImg($(this));
    }
  });

  $('#mAll').click(function() {
    markAll();
  });

  $('#umAll').click(function() {
    unmarkAll();
  });

  $('#lMarked').click(function() {
    listMarked();
  });


  
function markImg(selectedImg){
    selectedImg.css("border-width", "5px");
    selectedImg.css("border-style", "solid");
    selectedImg.css("border-color", "red");
    selectedImg.data("marked", true);
} 

function unmarkImg(selectedImg){
    selectedImg.css("border-width", "0px");
    selectedImg.css("border-style", "none");
    selectedImg.css("border-color", "white");
    selectedImg.data("marked", false);
} 

function listMarked(){
    $('#log').val('');
    var text;
    var markedImgId;
    $('img').each(function(){
        if($(this).data("marked") == true){
            markedImgId = $(this).attr('id');
            text = $('#log').val();
            text+=markedImgId + " ";
            $('#log').val(text);
        }
    })
    $('#log').val(text);
}