
class DocumentEditCommand implements Command {
    private Document document;
    private String text;
    private boolean isAdd;

    public DocumentEditCommand(Document doc, String txt, boolean isAdd) {
        this.document = doc;
        this.text = txt;
        this.isAdd = isAdd;
        execute();
    }

    @Override
    public void execute() {
        if (isAdd) {
            document.write(text);
        } else {
            document.erase(text);
        }
    }

    @Override
    public void undo() {
        if (isAdd) {
            document.erase(text);
        } else {
            document.write(text);
        }
    }
}