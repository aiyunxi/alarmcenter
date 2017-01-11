function loadPage(url) {
    // $.ajax({
    //     url: url,
    //     cache: false,
    //     success: function (html) {
    //         $("#bodyPage").html(html);
    //     },
    //     error: function () {
    //         $("#bodyPage").html("加载页面时出现异常！");
    //     }
    // });
    window.location.href = url;
}

function trimText() {
    $("input[type='text']").each(function (i) {
        var txt = $.trim($("input[type='text']").eq(i).val());
        $("input[type='text']").eq(i).val(txt);
    });
    //$("input:submit").attr({ "disabled": "disabled" });
}


function alertDialog(message) {
    alertDialog(message, null);
}

function alertDialog(message, url) {
    bootbox.alert(message, function () {
        if (url != undefined && url != null && url != '')
            loadPage(url);
    });
}

function confirmDialog(message) {
    confirmDialog(message, null);
}

function confirmDialog(message, url) {
    bootbox.confirm({
        message: message,
        buttons: {
            confirm: {
                label: '确定',
                className: 'btn-success'
            },
            cancel: {
                label: '取消',
                className: 'btn-danger'
            }
        },
        callback: function (result) {
            if (url != undefined && url != null && url != '')
                loadPage(url);
        }
    });
}

