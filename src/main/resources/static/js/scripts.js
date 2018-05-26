function search(){
    var query = document.getElementById('searchQueryInput').value;
    if(query != null && query.length > 0){
        window.location.href = window.location.href + 'search/' + query;
    }
}
