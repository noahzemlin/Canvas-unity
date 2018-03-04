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

public class ChatArrayAdapter extends ArrayAdapter {

    //to reference the Activity
    private Activity context;

    public static ChatArrayAdapter _instance;

    public ChatArrayAdapter(Activity context, ArrayList<Message> chat){

        super(context, R.layout.chatrow, chat);
        this.context=context;
        _instance = this;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.chatrow, null,true);

        Message message = (Message) getItem(position);

        //this code gets references to objects in the listview_row.xml file
        TextView nameField = (TextView) rowView.findViewById(R.id.messageName);
        TextView messageField = (TextView) rowView.findViewById(R.id.messageMessage);

        if (message.name != null) {
            nameField.setText(message.name);
            messageField.setText(message.message);
        } else {
            nameField.setText("You");
            messageField.setText(message.message);

            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) nameField.getLayoutParams();
            params.leftMargin = params.rightMargin; params.rightMargin = 16;

            nameField.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
            messageField.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        }

        return rowView;

    };

    public static void addMessage(String name, String message) {
        _instance.add(new Message(name, message));
    }
}
