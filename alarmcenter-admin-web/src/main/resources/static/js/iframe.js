function loadPage(url) {
    $.ajax({
        url: url,
        cache: false,
        success: function (html) {
            $("#bodyPage").html(html);
        },
        error: function () {
            $("#bodyPage").html("加载页面时出现异常！");
        }
    });
}