/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function moveLeft(e){
    var el=e; 
    var parent = el.parentNode;
    var items = parent.getElementsByClassName("item");
    
    for(var i=0;i<items.length-5;i++) {
        
        if (items[i].style.display !== "none"){
            items[i].style.display = "none";
            break;
        }
    }
}

function moveRight(e){
    var el=e; 
    var parent = el.parentNode;
    var items = parent.getElementsByClassName("item");
    
    for(var i=items.length-6;i>=0;i--) {
        
        if (items[i].style.display === "none"){
            items[i].style.display = "inline-block";
            break;
        }
    }
    
}

function setCenterHeight(){
    var height = 800;
    
    try {
        var totalRows = document.getElementById('winelist').rows.length;
        if (totalRows>4) {
        height += (totalRows-4)*150;
        }
        document.getElementById("center").style.height= height.toString() +"px";
    }
    catch(err) {
       
    }
    
    
    
}