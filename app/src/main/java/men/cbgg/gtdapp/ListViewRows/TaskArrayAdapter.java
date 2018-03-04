package men.cbgg.gtdapp.ListViewRows;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import men.cbgg.gtdapp.R;

public class TaskArrayAdapter extends ArrayAdapter {

    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    //to reference the Activity
    private Activity context;

    public TaskArrayAdapter(Activity context){

        super(context, R.layout.taskrow, new ArrayList<String>());

        this.context=context;

    }

    public void addTask(Task task) {
        add(task);
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.taskrow, null,true);

        //this code gets references to objects in the listview_row.xml file
        TextView nameField = (TextView) rowView.findViewById(R.id.rowName);
        TextView dateField = (TextView) rowView.findViewById(R.id.rowDate);

        //this code sets the values of the objects to values from the arrays
        nameField.setText(((Task)getItem(position)).name);
        dateField.setText( ((Task)getItem(position)).date );

        return rowView;

    };

    public void deleteTask(Task task) {
        for (int i=0; i<getCount(); i++) {
            Log.d("deleteTask", ((Task)getItem(i)).name);
            Log.d("deleteTask2", task.name);
            if (getItem(i) == task) {
                remove(task);
                break;
            }
        }
    }
}
