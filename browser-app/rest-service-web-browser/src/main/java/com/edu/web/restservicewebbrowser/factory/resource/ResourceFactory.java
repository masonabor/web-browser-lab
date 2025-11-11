package com.edu.web.restservicewebbrowser.factory.resource;


import com.edu.web.restservicewebbrowser.domain.resource.Resource;


public abstract class ResourceFactory {

    protected abstract Resource createResource(String url, byte[] rawData);

    public Resource fetchAndPackageResource(String url) {
        byte[] rawData = downloadFromUrl(url);

        Resource resource = createResource(url, rawData);

        System.out.println("Фабрика " + this.getClass().getSimpleName() + " створила: " + resource.toString());
        return resource;
    }

    private byte[] downloadFromUrl(String url) {
        System.out.println("Завантаження даних з " + url + "...");
        // додати реальний виклик http
        return ("(сирі байти для " + url + ")").getBytes();
    }
}