package ciprian.saver;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ArrowKeyMovementMethod;
import android.widget.TextView;

import java.util.List;

public class GoalDetails extends AppCompatActivity {
    public static final String EXTRA_GOALNO = "GoalId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_details);
        final DBHandler db;
        db = new DBHandler(this);
        String goalDetail;
        int goalNo = (Integer) getIntent().getExtras().get(EXTRA_GOALNO);
        //View Block Number List in the Text View Widget
        TextView screen = (TextView) findViewById(R.id.screen);

        screen.setMovementMethod(ArrowKeyMovementMethod.getInstance());

        screen.setText("");    //	clear text area at each button press
        screen.setTextColor(Color.MAGENTA);
        screen.setTextSize(22);


        List<Goal> goalsList = db.getAllGoalsList();    //	fetch List of BlockedNumbers form DB  method - 'getAllBlockedNumbers'
        //System.out.println(goalsList.get(goalNo)._sum);
        int new_sum = goalsList.get(goalNo)._sum;
        //System.out.println("This is " + new_sum);
        if (new_sum > 0) {
            goalDetail = "\n\n\tID:" + goalsList.get(goalNo).get_id() + "\n\tGoal:" + goalsList.get(goalNo).get_goal_name() + "\n\tDesc:" + goalsList.get(goalNo).get_description() + "\n\tTo Save:" + goalsList.get(goalNo).get_sum();
        } else {
            goalDetail = "\n\n\tTask With ID:" + goalsList.get(goalNo).get_id() + "\nName: " + goalsList.get(goalNo).get_goal_name() + "\n\t\tis completead Please remove it by clicking here";
        }
        screen.append("\n" + goalDetail);
    }
}

