package com.example.todoappfirebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;

import com.example.todoappfirebase.Adapter.ToDoAdapter;
import com.example.todoappfirebase.Model.ToDoModel;
import com.example.todoappfirebase.Utils.DatabaseHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DialogCloseListener{

    private RecyclerView TaskRecyclerView;
    private ToDoAdapter TaskAdapter;
    private DatabaseHandler db;

    private List<ToDoModel> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        db = new DatabaseHandler(this);

        taskList = new ArrayList<>();

        TaskRecyclerView = findViewById(R.id.taskRecyclerView);
        TaskRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        TaskAdapter = new ToDoAdapter(this);
        TaskRecyclerView.setAdapter(TaskAdapter);

        ToDoModel task = new ToDoModel();
        task.setTask("this is a test task");
        task.setStatus(0);
        task.setId(1);

        taskList.add(task);
        taskList.add(task);
        taskList.add(task);
        taskList.add(task);
        taskList.add(task);
        taskList.add(task);

        TaskAdapter.setTasks(taskList);
    }

    @Override
    public void handleDialogClose(DialogInterface dialog) {
        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        TaskAdapter.setTasks(taskList);
        TaskAdapter.notifyDataSetChanged();
    }
}