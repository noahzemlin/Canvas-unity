package men.cbgg.gtdapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import men.cbgg.gtdapp.ListViewRows.Task;
import men.cbgg.gtdapp.Storage.TaskCache;

public class ViewTask extends AppCompatActivity {

    Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int id = getIntent().getIntExtra("id", -1);
        task = TaskCache.getTaskById(id);
        TextView nameItem = findViewById(R.id.textView);
        nameItem.setText(task.name);

        Button deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaskCache.deleteTask(task.id);

                finish();
            }
        });
    }

}
