package com.example.itemtouchhelper;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ItemDialog extends DialogFragment {

    private View view;
    private EditText editTitle;
    private EditText editDetail;
    private AlertDialog alertDialog;
    private String title;
    private String detail;
    private int position;

    public ItemDialog() {}

    public static ItemDialog newInstance(String title, String detail, int position) {
        ItemDialog dialog = new ItemDialog();
        Bundle arguments = new Bundle();
        arguments.putString("title", title);
        arguments.putString("detail", detail);
        arguments.putInt("position", position);
        dialog.setArguments(arguments);
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        assert args != null;
        title = args.getString("title");
        detail = args.getString("detail");
        position = args.getInt("position");
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Title");
        builder.setMessage("Description");
        view = View.inflate(getActivity(), R.layout.input_dialog, null);
        builder.setView(view);
        editTitle = view.findViewById(R.id.input_title);
        editDetail = view.findViewById(R.id.input_detail);
        editTitle.setText(title);
        editDetail.setText(detail);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                title = editTitle.getText().toString();
                detail = editDetail.getText().toString();
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.changeTitle(title, detail, position);
            }
        });
        builder.setNegativeButton("Back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Nothing
            }
        });
        return builder.create();
    }
}
