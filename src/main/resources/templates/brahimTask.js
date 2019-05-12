
/*
hier wordt de table mee opgeslagen in de local storage met key=showTaskTable en
met value = de tabel met id="taskTable"in de showTasks.html
 */
localStorage.setItem( "showTaskTable", $("#taskTable").html() );

/*
hier wordt de table opgehaald uit de localStorage met key=showTaskTable
 */
var applicationBasketTableData  = localStorage.getItem( "showTaskTable" );
$("#taskTable").html( showTaskTable );