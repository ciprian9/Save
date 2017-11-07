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
 * Created by Ciprian on 07-11-2017.
 */
public class DeleteGoalDialog extends DialogFragment {

    ////interface to handle the Events
    interface DeleteGoalDialogListener{

        void onDeleteButtonClick(DialogFragment dialog);
    }

    //create an Instance to deliever the action
    DeleteGoalDialogListener deleteGoalListener;

    // Override the Fragment.onAttach() method to instantiate the SetGoalDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the deleteGoalListener  so we can send events to the host
            deleteGoalListener = (DeleteGoalDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement DeleteGoalDialogListener");
        }
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.delete_goal, null))

                // Add action buttons
                .setPositiveButton(R.string.btnLabel_delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        deleteGoalListener.onDeleteButtonClick(DeleteGoalDialog.this);

                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DeleteGoalDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();

    }
}
