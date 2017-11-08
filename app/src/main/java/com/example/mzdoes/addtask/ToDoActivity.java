package com.example.mzdoes.addtask;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ToDoActivity extends Activity {

    private ArrayList<Task> toDoTasks;
    private ListView toDoList;
    private ArrayAdapter adapter;
    private TextView title;
    private ImageButton editButton;
    private ImageButton addToDoButton;
    private Boolean isEditing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);

        Intent i = getIntent();
        isEditing = false;

        //wire widgets
        title = (TextView) findViewById(R.id.textView_dailyTitle);
        toDoTasks = new ArrayList<Task>();
        toDoList = (ListView) findViewById(R.id.listView_toDo);
        editButton = (ImageButton) findViewById(R.id.imageButton_editToDo);
        addToDoButton = (ImageButton) findViewById(R.id.imageButton_addToDo);

        //create List
        settoDoTasks();

        //create adapter
        adapter = new ArrayAdapter<Task>(this, R.layout.list_to_do_item, toDoTasks) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                final View view = View.inflate(ToDoActivity.this, R.layout.list_to_do_item, null);
                //set TextView
                TextView textView = (TextView) view.findViewById(R.id.textView);
                textView.setText(toDoTasks.get(position).getName());
                textView.setBackgroundColor(toDoTasks.get(position).getColor());
                return view;
            }
        };

        toDoList.setAdapter(adapter);

        setListeners();
    }

    
    //METHODS
    public void settoDoTasks() {
        toDoTasks.add(new Task("Task1", "Desc1", true));
        toDoTasks.add(new Task("Task2", "Desc2", true));
    }
    
    public void setListeners() {
        //LONG CLICK ON LIST ITEM
        toDoList.setLongClickable(true);
        toDoList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int pos, long l) {
                if (!isEditing) {
                    //getTask from arrayList.get(pos) and set color
                    toDoTasks.get(pos).setToDoColor();
                    //notify data set
                    adapter.notifyDataSetChanged();
                    return true;
                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(ToDoActivity.this).create();
                    alertDialog.setMessage("Delete this task?");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    toDoTasks.remove(pos);
                                    adapter.notifyDataSetChanged();
                                }
                            });
                    alertDialog.show();
                    return true;
                }
            }
        });

        //PRESS ON LIST ITEM
        toDoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int pos, long l) {
                if (isEditing) {
                    //edit name
                    final AlertDialog changeDialog = new AlertDialog.Builder(ToDoActivity.this).create();
                    LayoutInflater inflater = (ToDoActivity.this).getLayoutInflater();
                    changeDialog.setView(inflater.inflate(R.layout.dialog_changetodo, null));
                    changeDialog.setTitle("Edit To Do:");
                    final EditText nameEdit = (EditText) changeDialog.findViewById(R.id.editText_name);
                    final EditText descEdit = (EditText) changeDialog.findViewById(R.id.editText_desc);
                    changeDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Cancel",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                    changeDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    String name = nameEdit.getText().toString();
                                    String desc = descEdit.getText().toString();
                                    toDoTasks.get(pos).setName(name);
                                    toDoTasks.get(pos).setDesc(desc);
                                    adapter.notifyDataSetChanged();
                                }
                            });
                    changeDialog.show();
                } else if (!isEditing) {
                    AlertDialog toDoDialog = new AlertDialog.Builder(ToDoActivity.this).create();
                    toDoDialog.setTitle(toDoTasks.get(pos).getName());
                    toDoDialog.setMessage(toDoTasks.get(pos).getDesc());
                    toDoDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                    toDoDialog.show();
                }
            }
        });
        
        //EDIT BUTTON
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEditing) {
                    isEditing = false;
                    title.setTextColor(getResources().getColor(R.color.colorBlack));
                } else {
                    isEditing = true;
                    title.setTextColor(getResources().getColor(R.color.colorAccent));
                }
            }
        });

        //ADD TO DO BUTTON
        addToDoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog addTaskDialog = new AlertDialog.Builder(ToDoActivity.this).create();
                LayoutInflater inflater = (ToDoActivity.this).getLayoutInflater();
                addTaskDialog.setView(inflater.inflate(R.layout.dialog_changetodo, null));
                addTaskDialog.setTitle("Add Task:");
                final EditText nameEdit = (EditText) addTaskDialog.findViewById(R.id.editText_name);
                final EditText descEdit = (EditText) addTaskDialog.findViewById(R.id.editText_desc);
                addTaskDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                addTaskDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String name = nameEdit.getText().toString();
                                String desc = descEdit.getText().toString();
                                toDoTasks.add(new Task(name, desc, true));
                                adapter.notifyDataSetChanged();
                            }
                        });
                addTaskDialog.show();
            }
        });
    }
    
}