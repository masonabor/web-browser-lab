package com.edu.web.restservicewebbrowser.domain.resource;

public class JsResource extends Resource {
    private final String jsContent;

    public JsResource(String url, String jsContent) {
        super(url);
        this.jsContent = jsContent;
    }

    public String getJsContent() {
        return jsContent;
    }
}
