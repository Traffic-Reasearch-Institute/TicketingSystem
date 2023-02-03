$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "/seats",
        data: {},
        success: function (response) {
            let seats = response['seats'];

        }
    })

});