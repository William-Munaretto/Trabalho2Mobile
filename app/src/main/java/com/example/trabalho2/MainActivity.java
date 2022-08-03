package com.example.trabalho2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DialogInsert.onItemClickListener, AbsListView.MultiChoiceModeListener {

    private ListView listView;
    private Adapter adapter = new Adapter(this);
    private ArrayList<String> selecionados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selecionados = adapter.getList();

        listView = (ListView) findViewById(R.id.lista);
        listView.setAdapter(adapter);

        listView.setMultiChoiceModeListener(this);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.btn_add){
            DialogInsert dialogInsert = new DialogInsert();
            dialogInsert.show(getSupportFragmentManager(), "DialogInsert");
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItem(String item) {
        adapter.insertItem(item);
    }


    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        getMenuInflater().inflate(R.menu.actions, menu);
        return true;

    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

        if (item.getItemId() == R.id.act_delete){
            for(String s: selecionados){
                adapter.deletarItem(s);
            }
            mode.finish();
            return true;
        }
        else if(item.getItemId() == R.id.act_edit){
            for(String s : selecionados){
                int i = selecionados.indexOf(s);
                adapter.editarItem(s, i);
            }
            mode.finish();
            return true;
        }

        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {

    }


    @Override
    public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

    }

}