package com.edu.web.restservicewebbrowser.factory.resource;

import com.edu.web.restservicewebbrowser.domain.resource.ImageResource;
import com.edu.web.restservicewebbrowser.domain.resource.Resource;

public class ImageResourceFactory extends ResourceFactory {

    @Override
    protected Resource createResource(String url, byte[] rawData) {
        return new ImageResource(url, rawData);
    }
}