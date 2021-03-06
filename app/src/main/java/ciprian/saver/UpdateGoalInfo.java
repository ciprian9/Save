package ciprian.saver;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
/**
 * Created by Ciprian Anton on 06-10-2017.
 */
public class UpdateGoalInfo extends DialogFragment {

    ////interface to handle the Events
    interface UpdateGoalDialogListener{

        void onUpdateButtonClick(DialogFragment dialog);
    }

    //create an Instance to deliever the action
    UpdateGoalDialogListener updateGoalListener;
    Context context;

    // Override the Fragment.onAttach() method to instantiate the GoalDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the updateGoalListener so we can send events to the host
            updateGoalListener = (UpdateGoalDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement UpdateGoalDialogListener");
        }
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.udpate_goal_info, null))

                // Add action buttons
                .setPositiveButton(R.string.tv_Update, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        updateGoalListener.onUpdateButtonClick(UpdateGoalInfo.this);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        UpdateGoalInfo.this.getDialog().cancel();
                    }
                });
        return builder.create();

    }
}
