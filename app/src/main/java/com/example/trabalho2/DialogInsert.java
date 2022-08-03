package com.example.trabalho2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class DialogInsert extends DialogFragment implements DialogInterface.OnClickListener {

    private EditText edtItem;
    private Adapter adapter;
    private String item;
    private onItemClickListener listener;



    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Item");

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_insert, null);
        builder.setView(dialogView);

        edtItem =  dialogView.findViewById(R.id.edtItem);

        builder.setPositiveButton(R.string.ok, this);
        builder.setNegativeButton(R.string.cancelar, this);

        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int i) {

        if (i == DialogInterface.BUTTON_POSITIVE) {
            item = edtItem.getText().toString();
            if(!TextUtils.isEmpty(item)){
                listener.onItem(item);
//                insertMode = true;
            }
            Toast.makeText(getActivity(), R.string.adicionado, Toast.LENGTH_LONG).show();
        } else if (i == DialogInterface.BUTTON_NEGATIVE) {

            Toast.makeText(getActivity(), R.string.Cancelado, Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof onItemClickListener){
            listener = (onItemClickListener) context;
        }
        else{
            throw new RuntimeException("A activity deve " +
                    "implementar o listener");
        }
    }


    public interface onItemClickListener{
        public void onItem(String item);


    }


}





