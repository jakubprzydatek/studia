var i = 0;
var boxesName = ['box1', 'box2', 'box3', 'box4', 'box5'];
function moveFunc()
{
        intervalId = setInterval(function(){ moveElemUp(boxesName[i]); }, 500);
    
}

function moveElemUp(boxName)
{
    if(i == 5)
    {
        clearInterval(intervalId);
        i=0;
        intervalId1 = setInterval(function(){ moveElemDown(boxesName[i]); }, 500);
    }
    else{
        setTimeout(function(){ document.getElementById(boxName).style.bottom="-100px"; }, 100);
        setTimeout(function(){ document.getElementById(boxName).style.bottom="-40px"; }, 150);
        setTimeout(function(){ document.getElementById(boxName).style.bottom="0px"; }, 200);
        i+=1;
    }
    
    
}

function moveElemDown(boxName)
{
    if(i == 5)
    {
        clearInterval(intervalId1);
        i=0;
        intervalId = setInterval(function(){ moveElemUp(boxesName[i]); }, 500);
    }
    else{
        setTimeout(function(){ document.getElementById(boxName).style.bottom="-40px"; }, 100);
        setTimeout(function(){ document.getElementById(boxName).style.bottom="-100px"; }, 150);
        setTimeout(function(){ document.getElementById(boxName).style.bottom="-140px"; }, 200);
        i+=1;
    }
    
}