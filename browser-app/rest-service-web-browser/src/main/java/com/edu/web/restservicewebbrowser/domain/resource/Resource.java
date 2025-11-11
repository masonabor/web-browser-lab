package com.edu.web.restservicewebbrowser.domain.resource;

public abstract class Resource {
    protected String url;

    public Resource(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [url=" + url + "]";
    }
}
