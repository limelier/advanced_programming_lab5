package document;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Document implements Serializable {
    private String id;
    private String name;
    private String location;
    Map<String, Object> tags;

    public Document(String id, String name, String location) {
        this.id = id;
        this.name = name;
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
}