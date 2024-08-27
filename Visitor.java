import java.util.Arrays;
import java.util.List;

interface DocumentVisitor {
    void visit(Paragraph paragraph);

    void visit(Image image);
}

interface DocumentElement {
    void accept(DocumentVisitor visitor);
}

class Paragraph implements DocumentElement {
    private String text;

    public Paragraph(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public void accept(DocumentVisitor visitor) {
        visitor.visit(this);
    }
}

class Image implements DocumentElement {
    private String path;

    public Image(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    @Override
    public void accept(DocumentVisitor visitor) {
        visitor.visit(this);
    }
}

class DisplayVisitor implements DocumentVisitor {
    @Override
    public void visit(Paragraph paragraph) {
        System.out.println("Paragraph: " + paragraph.getText());
    }

    @Override
    public void visit(Image image) {
        System.out.println("Image: " + image.getPath());
    }
}

class WordCountVisitor implements DocumentVisitor {
    private int wordCount = 0;

    @Override
    public void visit(Paragraph paragraph) {
        wordCount += paragraph.getText().split("\\s+").length;
    }

    @Override
    public void visit(Image image) {
        // Imagens não têm palavras
    }

    public int getWordCount() {
        return wordCount;
    }
}

public class Visitor {
    public static void main(String[] args) {
        List<DocumentElement> document = Arrays.asList(
                new Paragraph("This is the first paragraph."),
                new Image("image1.png"),
                new Paragraph("This is the second paragraph."));

        DocumentVisitor displayVisitor = new DisplayVisitor();
        DocumentVisitor wordCountVisitor = new WordCountVisitor();

        for (DocumentElement element : document) {
            element.accept(displayVisitor);
            element.accept(wordCountVisitor);
        }

        System.out.println("Total word count: " + ((WordCountVisitor) wordCountVisitor).getWordCount());
    }
}