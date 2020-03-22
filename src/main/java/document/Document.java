package document;

import exceptions.InvalidDocumentLocationException;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class Document implements Serializable {
    private String id;
    private String name;
    private String location;
    Map<String, Object> tags;

    public Document(String id, String name, String location) throws InvalidDocumentLocationException {
        this.id = id;
        this.name = name;
        try {
            new URI(location);
        } catch (URISyntaxException e) {
            throw new InvalidDocumentLocationException(location);
        }
        this.location = location;
        tags = new HashMap<>();
    }

    public void addTag(String key, Object obj) {
        tags.put(key, obj);
    }

    public String getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }
}
