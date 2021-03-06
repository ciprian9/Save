package ciprian.saver;

/**
 * Created by Ciprian Anton on 06-10-2017.
 */

import android.app.DialogFragment;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AddGoalDialog.AddGoalDialogListener,
                                UpdateGoalInfo.UpdateGoalDialogListener, DeleteGoalDialog.DeleteGoalDialogListener {

    Button btnAddGoal, btnUpdateInfo, btnShowDetails, btnDeleteInfo;
    private String TAG = "Saver";
    int btnBackPressCounter = 0;
    DBHandler db;
    boolean check_goal_name;
    boolean check_desc;
    boolean check_sum;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHandler(this);

        btnAddGoal = (Button)findViewById(R.id.btnAddStudent);
        btnShowDetails = (Button)findViewById(R.id.btnShowDetails);
        btnUpdateInfo = (Button)findViewById(R.id.btnUpdateInfo);
        btnDeleteInfo = (Button)findViewById(R.id.btnDeleteInfo);
        //Set all click Listeners
        btnAddGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddGoalDialog dialog = new AddGoalDialog();
                dialog.show(getFragmentManager(), TAG);
            }
        });

        btnUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateGoalInfo updateDialog = new UpdateGoalInfo();
                updateDialog.show(getFragmentManager(),TAG);
            }
        });

        btnDeleteInfo.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                DeleteGoalDialog deleteDialog = new DeleteGoalDialog();
                deleteDialog.show(getFragmentManager(),TAG);

            }
        });

        btnShowDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this , ShowSomething.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Save Goal To Databse
    @Override
    public void onSaveButtonClick(DialogFragment dialog) {


            //		Get GoalName
            EditText entGoalName = dialog.getDialog().findViewById(R.id.add_GoalName);
            String goal_name = entGoalName.getText().toString();


            //		Get Description
            EditText entDesc =  dialog.getDialog().findViewById(R.id.add_Description);
            String description = entDesc.getText().toString();

            //		Get Sum
            EditText entSum = dialog.getDialog().findViewById(R.id.add_sumEdt);
            String sum = entSum.getText().toString();
            int int_Sum = 0;

        //Verify if all the fields were filled

        try {
            int_Sum = Integer.parseInt(entSum.getText().toString());


            check_goal_name = checkGoalName(goal_name);


            check_desc = checkDescription(description);

            check_sum = checkSum(sum);

        }
        catch(Exception ex){
            check_goal_name = false;
            check_sum = false;
            check_desc = false;
        }

        //Toast the results
        if(check_goal_name == false || check_desc == false || check_sum == false){

            Toast.makeText(getApplicationContext(),"Enter Data Again.. :P", Toast.LENGTH_LONG).show();
        }else{

            db.addNewGoal(new Goal(goal_name, description, int_Sum));

            Toast.makeText(getApplicationContext(),"Goal Added to the List..", Toast.LENGTH_LONG).show();
        }


        //Display the data entered
        Toast.makeText(getApplicationContext(),"\nName :" + goal_name + "\nDesc: " + description + "\nSum:" + sum , Toast.LENGTH_LONG).show();



    }

    //Check Id Number
    public boolean checkIdno(String Id_No){

        if(Id_No == ""){

            return false;
        }else{
            return true;
        }

    }

    //Check Goal Name
    public boolean checkGoalName(String goal_name){

        if(goal_name.isEmpty()){

            return false;
        }else{
            System.out.print(goal_name);
            return true;
        }

    }

    //Check Description
    public boolean checkDescription(String desc){

        if(desc.isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    //Check Sum
    public boolean checkSum(String sum){

        if((sum.isEmpty())){

            return false;
        }else{
            return true;
        }

    }

    @Override
    public void onUpdateButtonClick(DialogFragment dialog) {


//		Get ID
        EditText entId = dialog.getDialog().findViewById(R.id.edt_UpdateId);
        String idNo = entId.getText().toString();
        int int_idNo = 0;

        //		Get Sum
        EditText entSum = dialog.getDialog().findViewById(R.id.edt_UpdateSum);
        String sum = entSum.getText().toString();
        int int_Sum = 0;

        boolean check_idNo = false;

        boolean check_Sum = false;

        try {
            int_idNo = Integer.parseInt(entId.getText().toString());
            int_Sum = Integer.parseInt(entSum.getText().toString());

            check_idNo = checkIdno(idNo);

            check_Sum = checkSum(sum);
        }
        catch(Exception ex){
            check_idNo = false;

            check_Sum = false;
        }

        List<Goal> goalsList = db.getAllGoalsList();

        //Verify if the Id exists in the database
        for(Goal g : goalsList){
            if(g.get_id() == int_idNo){
                check_idNo = true;
                break;
            }
            else{
                check_idNo = false;
            }
        }

        if(check_idNo == false || check_Sum == false){

            Toast.makeText(getApplicationContext(),"Enter Data Again.. :P", Toast.LENGTH_LONG).show();
        }else{

            boolean updateCheck = db.updateGoalInfo(int_idNo, int_Sum);

            if(updateCheck == true){

            Toast.makeText(getApplicationContext(),"Goal has been updated", Toast.LENGTH_LONG).show();}
            else{

                Toast.makeText(getApplicationContext(),"Details Update Fails.. :(", Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    public void onDeleteButtonClick(DialogFragment dialog) {

        //		Get ID
        EditText entId = dialog.getDialog().findViewById(R.id.edt_deleteID);
        String idNo = entId.getText().toString();
        boolean check_idNo;
        int int_idNo = 0;
        try {
            int_idNo = Integer.parseInt(entId.getText().toString());
            check_idNo = checkIdno(idNo);
        }
        catch (Exception ex){
            check_idNo =  false;
        }

        if(check_idNo == false){

            Toast.makeText(getApplicationContext(),"Enter ID again..! :)", Toast.LENGTH_LONG).show();

        }else{

            boolean deleCheck = db.deleteGoal(int_idNo);

            if(deleCheck == true){

                Toast.makeText(getApplicationContext(),"Goal Deleted Successfully :)", Toast.LENGTH_LONG).show();}
            else{

                Toast.makeText(getApplicationContext(),"Goal Deletion Fails.. :(", Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    public void onBackPressed() {

        //CODE FOR EXIT ONLY IF DOUBLE BACK PRESSED - NOT WORKING
        ++btnBackPressCounter;
        if(btnBackPressCounter == 1){

            Toast.makeText(getBaseContext(), "Click Back once again to EXIT", Toast.LENGTH_SHORT).show();

        }
        else {
            finish();
            }

        }
}
