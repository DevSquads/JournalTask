function request() {
    var data = $('#data').val();
    var target = $('#target').val();

    var requests = {
        target: target,
        data : data
    };
    $.ajax({
        type: "POST",
        url: "index.php",
        data: requests,
        success: function(response, textStatus, jqXHR){
            alert(response);
        }
    });
}

