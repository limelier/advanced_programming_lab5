package catalog;

import document.Document;
import exceptions.DuplicateDocumentIdException;

import java.io.Serializable;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Catalog implements Serializable {
    private String name;
    private String path;
    private List<Document> documents;

    public Catalog(String name, String path) {
        this.name = name;
        this.path = path;
        documents = new ArrayList<>();
    }

    public void add(Document doc) throws DuplicateDocumentIdException {
        if (findById(doc.getId()) == null) {
            documents.add(doc);
        } else {
            throw new DuplicateDocumentIdException(doc.getId());
        }
    }

    public Document findById(String id) {
        return documents.stream().filter(doc -> doc.getId().equals(id)).findFirst().orElse(null);
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public List<Document> getDocuments() {
        return Collections.unmodifiableList(documents);
    }
}
