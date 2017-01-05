package com.ymatou.alarmcenter.admin.web.common.result;

import org.unbescape.javascript.JavaScriptEscape;

/**
 * Created by zhangxiaoming on 2016/12/27.
 */
public class ConfirmDialog extends JavaScriptResult {
    private String message;
    private String url;

    public ConfirmDialog(String message) {
        this(message, "");
    }

    public ConfirmDialog(String message, String url) {
        this.message = message;
        this.url = url;
        super.setScript(String.format("confirmDialog('%s','%s');", JavaScriptEscape.escapeJavaScript(message), JavaScriptEscape.escapeJavaScript(url)));
    }
}
