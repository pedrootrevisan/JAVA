import java.util.ArrayList;
import java.util.List;

class Document {
    private List<String> textArray = new ArrayList<>();

    public void write(String text) {
        textArray.add(text);
    }

    public void erase(String text) {
        textArray.remove(text);
    }

    public String readDocument() {
        return String.join("\n", textArray);
    }
}