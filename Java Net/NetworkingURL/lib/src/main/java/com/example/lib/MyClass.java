package com.example.lib;

import java.io.BufferedReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class MyClass {
    public static void main(String[] args) {
        try {

            //all URLs are actually URIs

//            URI uri  = new URI("http://username:password@myserver.com:5000/catalogue/phones?os=android#samsung");


            URI baseUri = new URI("http://username:password@myserver.com:5000");

            URI uri  = new URI("/catalogue/phones?os=android#samsung");
            URI resolvedUri = baseUri.resolve(uri);


            URL url = resolvedUri.toURL();
            System.out.println("URL = " + url);
        } catch (URISyntaxException e) {
            System.out.println("URI Bad Syntax: " + e.getMessage());
        } catch (MalformedURLException e) {
            System.out.println("URL Malformed: " + e.getMessage());
        }

    }
}
