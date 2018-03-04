package men.cbgg.gtdapp;

import java.util.ArrayList;
import java.util.Iterator;

import men.cbgg.gtdapp.ListViewRows.Task;
import men.cbgg.gtdapp.ListViewRows.TaskArrayAdapter;

public class TaskCache {

    public static TaskCache _instance;
    private ArrayList<Task> list = new ArrayList<Task>();
    private TaskArrayAdapter listAdapter;

    public static void init(TaskArrayAdapter tasks) {
        _instance = new TaskCache();
        _instance.listAdapter = tasks;
    }

    public static void addTask(Task task) {
        _instance.list.add(task);
        _instance.listAdapter.addTask(task);

    }

    public static ArrayList<Task> getTasks() {
        return _instance.list;
    }

    public static Iterator<Task> Iterator() {
        return _instance.list.iterator();
    }

    public static void deleteTask(int id) {
        for (int i=0; i<_instance.list.size(); i++) {
            if (_instance.list.get(i).id == id) {
                _instance.listAdapter.deleteTask(_instance.list.get(i));
                _instance.list.remove(i);
            }
        }
    }

    public static Task getTaskById(int id) {
        for (Task task : _instance.list) {
            if(task.id == id){
                return task;
            }
        }
        return null;
    }
}