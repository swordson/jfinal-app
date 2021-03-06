/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013-2014 sagyf Yang. The Four Group.
 */

package japp.mvc.render;

import com.jfinal.render.Render;
import com.jfinal.render.RenderException;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;

@SuppressWarnings("serial")
public class DwzRender extends Render {
    private static final String CONTENT_TYPE = "text/html;charset=" + getEncoding();
    private String callbackType = "";
    private String confirmMsg = "";
    private String forwardUrl = "";
    private String message = "";
    private String navTabId = "";
    private String rel = "";
    private String statusCode = "200";

    public DwzRender() {
    }

    public DwzRender(String message, String navTabId, String callbackType) {
        this.message = message;
        this.navTabId = navTabId;
        this.callbackType = callbackType;
    }

    public static DwzRender closeCurrentAndRefresh(String refreshNavTabId) {
        DwzRender dwzRender = new DwzRender();
        dwzRender.navTabId = refreshNavTabId;
        dwzRender.callbackType = "closeCurrent";
        return dwzRender;
    }

    public static DwzRender error() {
        DwzRender dwzRender = new DwzRender();
        dwzRender.statusCode = "300";
        dwzRender.message = "操作失败";
        return dwzRender;
    }

    public static DwzRender error(String errorMsg) {
        DwzRender dwzRender = new DwzRender();
        dwzRender.statusCode = "300";
        dwzRender.message = errorMsg;
        return dwzRender;
    }

    public static Render refresh(String refreshNavTabId) {
        DwzRender dwzRender = new DwzRender();
        dwzRender.navTabId = refreshNavTabId;
        return dwzRender;
    }

    public static DwzRender success() {
        DwzRender dwzRender = new DwzRender();
        dwzRender.message("操作成功");
        return dwzRender;
    }

    public static DwzRender success(String successMsg) {
        DwzRender dwzRender = new DwzRender();
        dwzRender.message(successMsg);
        return dwzRender;
    }

    public DwzRender callbackType(String callbackType) {
        this.callbackType = callbackType;
        return this;
    }

    public DwzRender confirmMsg(String confirmMsg) {
        this.confirmMsg = confirmMsg;
        return this;
    }

    public DwzRender forwardUrl(String forwardUrl) {
        this.forwardUrl = forwardUrl;
        return this;
    }

    public DwzRender message(String message) {
        this.message = message;
        return this;
    }

    public DwzRender navTabId(String navTabId) {
        this.navTabId = navTabId;
        return this;
    }

    public DwzRender rel(String rel) {
        this.rel = rel;
        return this;
    }

    @Override
    public void render() {
        PrintWriter writer = null;
        String dwz = "\"statusCode\":\"{0}\",\"message\":\"{1}\",\"navTabId\":\"{2}\",\"rel\":\"{3}\","
                + "\"callbackType\":\"{4}\",\"forwardUrl\":\"{5}\",\"confirmMsg\":\"{6}\"";
        dwz = "{\n"
                + MessageFormat.format(dwz, statusCode, message, navTabId, rel, callbackType, forwardUrl, confirmMsg)
                + "\n}";
        try {
            response.setHeader("Pragma", "no-cache"); // HTTP/1.0 caches might not implement Cache-Control and might
            // only implement Pragma:
            // no-cache
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType(CONTENT_TYPE);
            writer = response.getWriter();
            writer.write(dwz);
            writer.flush();
        } catch (IOException e) {
            throw new RenderException(e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    public DwzRender statusCode(String statusCode) {
        this.statusCode = statusCode;
        return this;
    }

}
