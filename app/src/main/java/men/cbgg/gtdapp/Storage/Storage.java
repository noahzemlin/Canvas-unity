package men.cbgg.gtdapp.Storage;

import android.content.Context;
import android.util.Log;

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
import men.cbgg.gtdapp.MainActivity;

public class Storage {

    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private final static String filename = "GTDStorage";

    public static void writeFile() {
        try {
            FileOutputStream fout = MainActivity.mainActivity.openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(fout);
            Log.d("readFile()", "# of tasks: " + tasks.size());
            out.writeObject(tasks);
            out.close();
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setTasks(ArrayList<Task> _tasks) {
        tasks = _tasks;
    }

    public static void readFile() {
        try {
            FileInputStream fin = MainActivity.mainActivity.openFileInput(filename);
            ObjectInputStream in = new ObjectInputStream(fin);
            ArrayList<Task> newTasks = (ArrayList<Task>) (in.readObject());
            Log.d("readFile()", "# of tasks: " + newTasks.size());
            if (newTasks.size() > 0) {
                tasks = newTasks;
            }
            in.close();
            fin.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Task> getTasks() {
        return (ArrayList<Task>) tasks.clone();
    }

}
