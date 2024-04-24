
import java.util.Stack;
class DocumentInvoker {
    private Document document = new Document();
    private Stack<Command> commandStack = new Stack<>();
    private Stack<Command> redoStack = new Stack<>();

    public void write(String text) {
        Command cmd = new DocumentEditCommand(document, text, true);
        commandStack.push(cmd);
        redoStack.clear();
    }

    public void erase(String text) {
        Command cmd = new DocumentEditCommand(document, text, false);
        commandStack.push(cmd);
        redoStack.clear();
    }

    public void undo() {
        if (!commandStack.isEmpty()) {
            Command cmd = commandStack.pop();
            cmd.undo();
            redoStack.push(cmd);
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            Command cmd = redoStack.pop();
            cmd.execute();
            commandStack.push(cmd);
        }
    }

    public String read() {
        return document.readDocument();
    }
}