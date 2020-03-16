package main;

import catalog.Catalog;
import document.Document;
import util.CatalogUtil;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException, ClassNotFoundException {
        Main app = new Main();
        app.testCreateSave();
        app.testLoadView();
    }

    private void testCreateSave() throws IOException {
        Catalog catalog = new Catalog(
                "Java Resources",
                "/home/limelier/college/pa/lab5/test-env/catalog.ser"
        );
        Document doc = new Document(
                "java1",
                "Java Course 1",
                "https://profs.info.uaic.ro/~acf/java/slides/en/intro_slide_en.pdf"
        );
        doc.addTag("type", "Slides");
        catalog.add(doc);

        CatalogUtil.save(catalog);
    }

    private void testLoadView() throws IOException, ClassNotFoundException, URISyntaxException {
        Catalog catalog = CatalogUtil.load("/home/limelier/college/pa/lab5/test-env/catalog.ser");
        Document doc = catalog.findById("java1");
        CatalogUtil.view(doc);
    }
}
