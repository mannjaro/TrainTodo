package com.example.itemtouchhelper;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import javax.xml.validation.Validator;

public class MyAlert extends DialogFragment implements TextWatcher {

    private View view;
    private EditText editText;
    private EditText detailText;
    private AlertDialog alertDialog;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Todo");
        builder.setMessage("Title");
        view = View.inflate(getActivity(), R.layout.input_dialog, null);
        builder.setView(view);
        editText = view.findViewById(R.id.input_title);
        editText.addTextChangedListener(this);
        detailText = view.findViewById(R.id.input_detail);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String title = editText.getText().toString();
                String detail = detailText.getText().toString();
                if (title.equals("")) {
                    Toast.makeText(getActivity(), "Entered: " + editText.getText().toString(), Toast.LENGTH_LONG).show();
                } else {
                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.addTitle(title, detail);
                    dialog.dismiss();
                }
            }
        });
        builder.setNegativeButton("Back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //
            }
        });
        alertDialog = builder.create();
        alertDialog.show();
        alertDialog.getButton(Dialog.BUTTON_POSITIVE).setEnabled(false);
        return alertDialog;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        alertDialog.getButton(Dialog.BUTTON_POSITIVE).setEnabled( !s.toString().isEmpty());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }



}
