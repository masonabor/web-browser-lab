package com.edu.web.restservicewebbrowser.domain.resource;

public class CssResource extends Resource {

    private final String cssContent;

    public CssResource(String url, String cssContent) {
        super(url);
        this.cssContent = cssContent;
    }

    public String getCssContent() {
        return cssContent;
    }
}
