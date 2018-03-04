package men.cbgg.gtdapp.ListViewRows;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import edu.ksu.canvas.model.assignment.Assignment;
import men.cbgg.gtdapp.R;
import men.cbgg.gtdapp.Storage.TaskCache;

public class AssignmentArrayAdapter extends ArrayAdapter {

    //to reference the Activity
    private Activity context;

    public AssignmentArrayAdapter(Activity context, ArrayList<Assignment> asses){

        super(context, R.layout.assignmentrow, asses);

        this.context=context;
    }

    public void addTask(Task task) {
        add(task);
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.assignmentrow, null,true);

        Assignment ass = ((Assignment) getItem(position));

        //this code gets references to objects in the listview_row.xml file
        TextView nameField = (TextView) rowView.findViewById(R.id.assignmentName);
        Button button = (Button) rowView.findViewById(R.id.addToToDo);

        //this code sets the values of the objects to values from the arrays
        nameField.setText(ass.getName());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaskCache.addTask(new Task(ass.getName(), ass.getDueAt().toString()));
                button.setVisibility(View.GONE);
            }
        });

        return rowView;

    };

}
