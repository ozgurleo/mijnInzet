
document.getElementById("IDS").innerHTML = "Hello JavaScript!";

/*
hier wordt de table mee opgeslagen in de sessionstorage met key=showTaskTable en
met value = de tabel met id="taskTable"in de showTasks.html:
-------------------------------------------------------------
sessionStorage.setItem( "showTaskTable", $("#taskTable").html() );
-------------------------------------------------------------
 */


/*
hier wordt de table opgehaald uit de sessionlStorage met key=showTaskTable en in
in de variable "applicationBasketTableData" gestopt:
-------------------------------------------------------------
var applicationBasketTableData  = sessionStorage.getItem( "showTaskTable" );
$("#taskTable").html( showTaskTable );
-------------------------------------------------------------
 */


/*
 ----------------------------------------------------------------------------------------------
 */


    /*
var hst = document.getElementById("taskTable");

sessionStorage.setItem( hst,JSON.stringify($("#taskTable").html()));

var showTaskTable= JSON.parse(localStorage.getItem("taskTable"));

function maketable() {
    for (var i = 0; i < showTaskTable.length; i++) {
        hst.innerHTML +=
            "<tr>" +
            "<td>" + showTaskTable[i].taskId + "</td>" +
            "<td>" + showTaskTable[i].taskName + "</td>" +
            "</tr>";
    }
}

window.onload.call(maketable())
*/