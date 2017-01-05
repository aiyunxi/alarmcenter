package com.ymatou.alarmcenter.admin.web.common.result;

import org.unbescape.javascript.JavaScriptEscape;

/**
 * Created by zhangxiaoming on 2016/12/27.
 */
public class AlertDialog extends JavaScriptResult {
    private String message;
    private String url;

    public AlertDialog(String message) {
        this(message, "");
    }

    public AlertDialog(String message, String url) {
        this.message = message;
        this.url = url;
        super.setScript(String.format("alertDialog('<strong>%s</strong>','%s');", JavaScriptEscape.escapeJavaScript(message), JavaScriptEscape.escapeJavaScript(url)));
    }
}
