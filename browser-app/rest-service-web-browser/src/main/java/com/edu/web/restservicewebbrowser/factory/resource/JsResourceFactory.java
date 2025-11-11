package com.edu.web.restservicewebbrowser.factory.resource;

import com.edu.web.restservicewebbrowser.domain.resource.JsResource;
import com.edu.web.restservicewebbrowser.domain.resource.Resource;

public class JsResourceFactory extends ResourceFactory {

    @Override
    protected Resource createResource(String url, byte[] rawData) {
        String jsContent = new String(rawData);

        return new JsResource(url, jsContent);
    }
}