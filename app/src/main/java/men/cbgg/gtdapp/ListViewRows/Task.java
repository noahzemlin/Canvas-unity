package men.cbgg.gtdapp.ListViewRows;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class Task implements Serializable {

    private static int lastId = -1;

    public long id;
    public String name;
    public String description;
    public String date;

    public Task() {
        this.id = UUID.randomUUID().getLeastSignificantBits();
    }

    public Task(String name) {
        this();
        this.name = name;
    }

    public Task(String name, String date) {
        this();
        this.name = name;
        this.date = date;
    }

    @Override
    public String toString() {
        return name;
    }

}

