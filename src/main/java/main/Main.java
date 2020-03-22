package main;

import freemarker.Config;
import manager.CatalogManager;

public class Main {
    public static void main(String[] args) {
        Config.init();
        CatalogManager manager = new CatalogManager();
        manager.start();
    }
}
