package com.edu.web.restservicewebbrowser.domain.resource;

public class ImageResource extends Resource {
    private final byte[] imageData;

    public ImageResource(String url, byte[] imageData) {
        super(url);
        this.imageData = imageData;
    }

    public byte[] getImageData() {
        return imageData;
    }
}
