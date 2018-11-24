package ar.tis.tisar.todos.calendar;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import ar.tis.tisar.R;
import ar.tis.tisar.todos.ExpandableTodoTaskAdapter;
import ar.tis.tisar.todos.model.TodoTask;
import ar.tis.tisar.todos.model.database.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by sebbi on 07.03.2018.
 *
 * This class helps to show the tasks that are on a specific deadline
 */

public class CalendarPopup extends AppCompatActivity {

    private DatabaseHelper dbhelper;
    private ExpandableListView lv;
    RelativeLayout rl;
    private ExpandableTodoTaskAdapter expandableTodoTaskAdapter;
    private ArrayList<TodoTask> tasks = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.calendar_popup);

        rl = (RelativeLayout) findViewById(R.id.relative_deadline);
        lv = (ExpandableListView) findViewById(R.id.deadline_tasks);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_deadlineTasks);

        if (toolbar != null) {
            toolbar.setTitle(R.string.deadline);
            toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.White));
            setSupportActionBar(toolbar);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrow);
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }
        Bundle b = getIntent().getExtras();
        if(b != null)
            tasks = b.getParcelableArrayList("Deadlines");
        updateAdapter();


    }


    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void updateAdapter() {

        expandableTodoTaskAdapter = new ExpandableTodoTaskAdapter(this, tasks);
        lv.setAdapter(expandableTodoTaskAdapter);

    }


}



