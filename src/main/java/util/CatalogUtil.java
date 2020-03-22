package util;

import catalog.Catalog;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import document.Document;
import exceptions.InvalidCatalogException;

import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.StringJoiner;

public class CatalogUtil {

    /**
     * Save the catalog at the location in its path.
     *
     * @param catalog the catalog to save
     * @throws IOException the catalog's path was invalid
     */
    public static void save(Catalog catalog) throws IOException {
        try (FileWriter writer = new FileWriter(catalog.getPath())) {
            Gson gson = new Gson();
            String json = gson.toJson(catalog);
            writer.write(json);
        }
    }

    /**
     * Create a catalog, loading it from the given path.
     *
     * @param path the path to the serialized catalog object
     * @return the loaded catalog
     * @throws IOException            the path is invalid
     */
    public static Catalog load(String path) throws IOException, InvalidCatalogException {
        try (JsonReader reader = new JsonReader(new FileReader(path))) {
            Gson gson = new Gson();
            return gson.fromJson(reader, Catalog.class);
        } catch (JsonSyntaxException e) {
            throw new InvalidCatalogException();
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

    /**
     * Returns a newline-separated list of the documents in a catalog: id, name and path.
     *
     * @param catalog the catalog to go through
     * @return the list of documents
     */
    public static String list(Catalog catalog) {
        if (catalog == null) throw new IllegalArgumentException();
        StringJoiner stringJoiner = new StringJoiner("\n");
        for (Document doc : catalog.getDocuments()) {
            stringJoiner.add(String.format("%s - '%s' (%s)", doc.getId(), doc.getName(), doc.getLocation()));
        }
        return stringJoiner.toString();
    }
}
