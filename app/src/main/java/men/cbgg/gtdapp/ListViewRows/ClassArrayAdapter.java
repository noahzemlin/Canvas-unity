package men.cbgg.gtdapp.ListViewRows;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.ksu.canvas.model.Course;
import men.cbgg.gtdapp.R;

public class ClassArrayAdapter extends ArrayAdapter {

    //to reference the Activity
    private Activity context;

    public ClassArrayAdapter(Activity context, ArrayList<Course> classes){

        super(context, R.layout.classrow, classes);



        this.context=context;

    }

    public void addTask(Task task) {
        add(task);
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.classrow, null,true);

        //this code gets references to objects in the listview_row.xml file
        TextView nameField = (TextView) rowView.findViewById(R.id.className);

        //this code sets the values of the objects to values from the arrays
        nameField.setText(((Course) getItem(position)).getName());


        return rowView;

    };

}
