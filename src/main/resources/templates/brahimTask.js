
/*
hier wordt de table mee opgeslagen in de sessionstorage met key=showTaskTable en
met value = de tabel met id="taskTable"in de showTasks.html
 */
sessionStorage.setItem( "showTaskTable", $("#taskTable").html() );

/*
hier wordt de table opgehaald uit de sessionlStorage met key=showTaskTable en in
in de variable "applicationBasketTableData" gestopt
 */
var applicationBasketTableData  = sessionStorage.getItem( "showTaskTable" );
$("#taskTable").html( showTaskTable );

