package men.cbgg.gtdapp.Storage;

import android.content.Context;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

import men.cbgg.gtdapp.ListViewRows.Task;

public class Storage {

    private static ArrayList<Task> tasks = new ArrayList<Task>();
    final static String filename = "GTDStorage";

    public static void writeFile(FileOutputStream fout) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(fout);
            out.writeObject(tasks);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setTasks(ArrayList<Task> tasks) {

    }

    public static void readFile(FileInputStream fin) {
        try {
            ObjectInputStream in = new ObjectInputStream(fin);
            tasks = (ArrayList<Task>)(in.readObject());
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Task> getTasks() {
        return (ArrayList<Task>) tasks.clone();
    }

}
