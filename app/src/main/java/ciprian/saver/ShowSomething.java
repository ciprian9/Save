package ciprian.saver;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ciprian Anton on 06-10-2017.
 */

public class ShowSomething extends ListActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView list = getListView();
        final DBHandler db;
        db = new DBHandler(this);

        List<String> values = new ArrayList<>();

            List<Goal> goalsList = db.getAllGoalsList();	//	fetch List of BlockedNumbers form DB  method - 'getAllBlockedNumbers'

            for (Goal g : goalsList) {

                values.add(g.get_goal_name());
                System.out.println();
                }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, values);

        list.setAdapter(arrayAdapter);
    }

    //Start the next activity using an intent and passing extra info in it
    @Override
    public void onListItemClick(ListView listView,
                                View itemView,
                                int position,
                                long id) {
        Intent intent = new Intent(ShowSomething.this, GoalDetails.class);
        intent.putExtra(GoalDetails.EXTRA_GOALNO, (int) id);
        startActivity(intent);
    }

}
