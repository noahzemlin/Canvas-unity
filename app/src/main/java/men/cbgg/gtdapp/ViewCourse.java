package men.cbgg.gtdapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import java.util.ArrayList;

import edu.ksu.canvas.model.assignment.Assignment;
import men.cbgg.gtdapp.ListViewRows.AssignmentArrayAdapter;

public class ViewCourse extends AppCompatActivity {

    String courseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_course);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        courseId = getIntent().getStringExtra("id");

        ArrayList<Assignment> assignments = CanvasConnection.getAssignemnts(courseId);

        ListView assList = findViewById(R.id.assignmentList);

        assList.setAdapter(new AssignmentArrayAdapter(this, assignments));

    }
}
