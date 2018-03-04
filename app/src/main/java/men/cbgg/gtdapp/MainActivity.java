package men.cbgg.gtdapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.threeten.bp.DateTimeUtils;
import org.threeten.bp.Instant;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;

import edu.ksu.canvas.model.Course;
import men.cbgg.gtdapp.ListViewRows.ChatArrayAdapter;
import men.cbgg.gtdapp.ListViewRows.ClassArrayAdapter;
import men.cbgg.gtdapp.ListViewRows.Message;
import men.cbgg.gtdapp.ListViewRows.Task;
import men.cbgg.gtdapp.ListViewRows.TaskArrayAdapter;
import men.cbgg.gtdapp.Storage.Storage;
import men.cbgg.gtdapp.Storage.TaskCache;

public class MainActivity extends AppCompatActivity {

    public static MainActivity mainActivity;

    private TextView mTextMessage;

    private ConstraintLayout mainpage;
    private ConstraintLayout chatpage;
    private ConstraintLayout todopage;
    private ListView mainList;
    private ListView classList;
    private ListView chatList;
    private TaskArrayAdapter listAdapter;

    public static Task openTask = null;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mainpage.setVisibility(View.VISIBLE);
                    chatpage.setVisibility(View.GONE);
                    todopage.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_todo:
                    mainpage.setVisibility(View.GONE);
                    chatpage.setVisibility(View.GONE);
                    todopage.setVisibility(View.VISIBLE);
                    return true;
                case R.id.navigation_chat:
                    mainpage.setVisibility(View.GONE);
                    chatpage.setVisibility(View.VISIBLE);
                    todopage.setVisibility(View.GONE);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainActivity = this;

        CanvasConnection.init();

        // Create ArrayAdapter using the planet list.
        listAdapter = new TaskArrayAdapter(this);

        TaskCache.init(listAdapter);

        Storage.readFile();

        ArrayList<Task> tasks = Storage.getTasks();
        for (Task task : tasks) {
            TaskCache.addTask(task);
        }

        setContentView(R.layout.main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Button mainTaskButton = (Button) findViewById(R.id.newTaskButton);
        mainTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar date = new GregorianCalendar(2018, 2, (int)(Math.random() * 30+1));
                SimpleDateFormat format1 = new SimpleDateFormat("EEE, MMM dd hh:mm a");


                TaskCache.addTask(new Task(
                        "Task #" + (int)(Math.random() * 50 + 1),
                        format1.format(date.getTime())
                ));
            }
        });

        mainpage = findViewById(R.id.mainpage);
        todopage = findViewById(R.id.todopage);
        chatpage = findViewById(R.id.chatpage);

        ArrayList<Course> classes = CanvasConnection.getUserCourses();

        chatList = findViewById(R.id.chatList);

        ArrayList<Message> messages = new ArrayList<Message>();

        messages.add(new Message(null, "heyo bud"));
        messages.add(new Message("Tyler", "heyo pal"));
        messages.add(new Message(null, "can i cheat off you?"));
        messages.add(new Message("Tyler", "no"));
        messages.add(new Message(null, "did u finish the homework?"));
        messages.add(new Message("Tyler", "Not yet... when is it due?"));

        chatList.setAdapter(new ChatArrayAdapter(this, messages));

        classList = findViewById(R.id.classList);

        classList.setAdapter(new ClassArrayAdapter(this, classes));

        classList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(MainActivity.this, ViewCourse.class);
                Course selected = (Course) ((ListView) findViewById(R.id.classList)).getItemAtPosition(position);
                intent.putExtra("id", "" + selected.getId());
                startActivity(intent);
            }
        });

        mainList = findViewById( R.id.mainList );

        // Set the ArrayAdapter as the ListView's adapter.
        mainList.setAdapter( listAdapter );

        mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(MainActivity.this, ViewTask.class);
                intent.putExtra("id", TaskCache.getTasks().get(position).id);
                openTask = TaskCache.getTasks().get(position);
                startActivity(intent);
            }
        });


        ((Button)(findViewById( R.id.chatbox_send))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ChatArrayAdapter.addMessage(null, ((EditText)findViewById( R.id.chatbox)).getText().toString());
                ((EditText)findViewById( R.id.chatbox)).getText().clear();

            }
        });

    }


}
