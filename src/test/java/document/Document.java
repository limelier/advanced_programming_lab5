package document;

import java.util.HashMap;
import java.util.Map;

public class Document {
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
}
