$(document).ready(function() {
    $( ".list-group-item" ).bind( "click", function(event) {
        event.preventDefault();
        var clickedItem = $( this );
        $( ".list-group-item" ).each( function() {
            $( this ).removeClass( "active" );
        });
        clickedItem.addClass( "active" );
    });
});