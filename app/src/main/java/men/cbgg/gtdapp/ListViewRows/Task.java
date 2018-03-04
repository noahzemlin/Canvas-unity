package men.cbgg.gtdapp.ListViewRows;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {

    private static int lastId = -1;

    public int id;
    public String name;
    public String description;
    private Date date;

    public Task() {
        lastId += 1;
        this.id = lastId;
    }

    public Task(String name) {
        this();
        this.name = name;
    }

    public Task(String name, Date date) {
        this();
        this.name = name;
        this.date = date;
    }

    public Date getDate() {
        return (Date) date.clone();
    }

    public void setDate(Date date) {
        this.date = (Date) date.clone();
    }

    @Override
    public String toString() {
        return name;
    }

}

