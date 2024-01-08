$(document).ready(() => {
    $('.PruebaAjax').click(function (event) {
        event.preventDefault();
        let id = $(this).data('id');
        $.ajax({
            type: "get",
            url: "/informacion/cancion/" + id,

            success: function (htmlRecibido) {
                $('#paraelmodal').html(htmlRecibido);
                $('#delete-modal').modal('show');
            },
            error: function (e) {
                console.log(e);
            }
        });
    });
});