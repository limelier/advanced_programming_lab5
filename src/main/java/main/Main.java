package main;

import manager.CatalogManager;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException, ClassNotFoundException {
        CatalogManager manager = new CatalogManager();
        manager.start();
    }
}
