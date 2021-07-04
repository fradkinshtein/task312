$(document).ready(function () {
    $('.nBtn, .table .eBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');

        $.get(href, function (user, status) {
            $('.myForm #id').val(user.id)
            $('.myForm #firstName').val(user.firstName)
            $('.myForm #lastName').val(user.lastName)
            $('.myForm #username').val(user.username)
            $('.myForm #password').val(user.password)
        });
        $('.myForm #exampleModal').modal();

    });
    $('.table .delBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var src = $(this).attr('src');

        $('#myModal #delRef').attr('href', href);
        $.get(src, function (user, status) {
            $('.delForm #id1').val(user.id)
            $('.delForm #firstName1').val(user.firstName)
            $('.delForm #lastName1').val(user.lastName)
            $('.delForm #username1').val(user.username)
        });
        $('#myModal').modal();
    });
});