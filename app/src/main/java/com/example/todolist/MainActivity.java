package com.example.todolist;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> todos;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextTodo = findViewById(R.id.editTextTodo);
        Button buttonAdd = findViewById(R.id.buttonAdd);
        ListView listView = findViewById(R.id.listView);

        // Initialize the list and adapter
        todos = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, todos);
        listView.setAdapter(adapter);

        // Add button click listener
        buttonAdd.setOnClickListener(v -> {
            String todoText = editTextTodo.getText().toString();
            if (!todoText.isEmpty()) {
                todos.add(todoText);
                adapter.notifyDataSetChanged();
                editTextTodo.setText("");
            } else {
                Toast.makeText(MainActivity.this, "Please enter a todo item", Toast.LENGTH_SHORT).show();
            }
        });

        // Long click listener to remove items
        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            todos.remove(position);
            adapter.notifyDataSetChanged();
            return true;
        });
    }
}
