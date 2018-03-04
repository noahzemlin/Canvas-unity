package men.cbgg.gtdapp.ListViewRows;

public class Message {

    String name;
    String message;

    public Message(String name, String message) {
        this.name = name;
        this.message = message;
    }

    @Override
    public String toString() {
        return name;
    }
}
