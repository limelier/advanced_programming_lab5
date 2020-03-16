package util;

import catalog.Catalog;
import document.Document;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

public class CatalogUtil {
    public static void save(Catalog catalog) throws IOException {
        try (
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(catalog.getPath()))
        ) {
            oos.writeObject(catalog);
        }
    }

    public static Catalog load(String path) throws IOException, ClassNotFoundException {
        try (
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))
        ) {
            return (Catalog) ois.readObject();
        }
    }

    public static void view(Document doc) throws IOException, URISyntaxException {
        Desktop desktop = Desktop.getDesktop();
        boolean web = doc.getLocation().matches("[a-z]*://.*");

        if (web) {
            desktop.browse(new URI(doc.getLocation()));
        } else {
            desktop.open(new File(doc.getLocation()));
        }
    }
}
