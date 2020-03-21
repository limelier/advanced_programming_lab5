package util;

import catalog.Catalog;
import document.Document;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CatalogUtil {

    /**
     * Save the catalog at the location in its path.
     *
     * @param catalog the catalog to save
     * @throws IOException the catalog's path was invalid
     */
    public static void save(Catalog catalog) throws IOException {
        try (
                ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(catalog.getPath())))
        ) {
            oos.writeObject(catalog);
        }
    }

    /**
     * Create a catalog, loading it from the given path.
     *
     * @param path the path to the serialized catalog object
     * @return the loaded catalog
     * @throws IOException            the path is invalid
     * @throws ClassNotFoundException the file at the path does not contain a catalog object
     */
    public static Catalog load(String path) throws IOException, ClassNotFoundException {
        try (
                ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(path)))
        ) {
            return (Catalog) ois.readObject();
        }
    }

    /**
     * Open the file pointed to by the document's "location" field.
     * <p>
     * Opens in the browser if the file is an URI, or locally if it is not.
     *
     * @param doc the document to view
     * @throws IOException        the path was local, and was not a valid path
     * @throws URISyntaxException the path was an invalid URI
     */
    public static void view(Document doc) throws IOException, URISyntaxException {
        Desktop desktop = Desktop.getDesktop();
        boolean web = doc.getLocation().matches("[a-z]*://.*");

        if (web) {
            desktop.browse(new URI(doc.getLocation()));
        } else {
            desktop.open(new File(doc.getLocation()));
        }
    }

    /**
     * Create and save a new catalog.
     *
     * @param name the catalog's name
     * @param path the path to save the catalog at
     * @return the newly created catalog
     * @throws IOException the path was invalid
     */
    public static Catalog create(String name, String path) throws IOException {
        Catalog catalog = new Catalog(name, path);
        save(catalog);
        return catalog;
    }
}
