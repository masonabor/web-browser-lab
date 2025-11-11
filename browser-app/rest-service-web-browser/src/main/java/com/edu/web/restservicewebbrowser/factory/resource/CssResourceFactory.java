package com.edu.web.restservicewebbrowser.factory.resource;

import com.edu.web.restservicewebbrowser.domain.resource.CssResource;
import com.edu.web.restservicewebbrowser.domain.resource.Resource;

public class CssResourceFactory extends ResourceFactory {

    @Override
    protected Resource createResource(String url, byte[] rawData) {
        String cssContent = new String(rawData);

        return new CssResource(url, cssContent);
    }
}