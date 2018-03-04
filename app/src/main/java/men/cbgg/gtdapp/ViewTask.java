package men.cbgg.gtdapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.threeten.bp.DateTimeUtils;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.temporal.Temporal;

import men.cbgg.gtdapp.ListViewRows.ChatArrayAdapter;
import men.cbgg.gtdapp.ListViewRows.Task;
import men.cbgg.gtdapp.Storage.Storage;
import men.cbgg.gtdapp.Storage.TaskCache;

public class ViewTask extends AppCompatActivity {

    Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        long id = getIntent().getLongExtra("id", -1);
        task = TaskCache.getTaskById(id);

        TextView nameItem = findViewById(R.id.taskTitle);
        nameItem.setText(task.name);

        TextView dateItem = findViewById(R.id.dateDisplay);
        dateItem.setText(task.date);

        Button deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaskCache.deleteTask(task.id);
                finish();
            }
        });

        Button sendButton = findViewById(R.id.sendToChat);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChatArrayAdapter.addMessage(null, task.name + " at (" + task.date + ")");
                finish();
            }
        });

        ((EditText)findViewById(R.id.taskTitle)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                task.name = s.toString();
                TaskCache.deleteTask(task.id);
                TaskCache.addTask(task);
            }
        });
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        Storage.setTasks(TaskCache.getTasks());
        Storage.writeFile();
    }
}
