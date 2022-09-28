package com.ssoft.android.retofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclearView);

        apiInterface = Instance.getRetrofit().create(ApiInterface.class);

         apiInterface.getTodos().enqueue(new Callback<List<Todo>>() {
             @Override
             public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                 if (response.body().size() > 0) {
                     MyAdapter adapter = new MyAdapter(MainActivity.this, response.body());
                     LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                     recyclerView.setLayoutManager(layoutManager);
                     recyclerView.setAdapter(adapter);
                 } else {
                     Toast.makeText(MainActivity.this, "List is empty", Toast.LENGTH_SHORT).show();
                 }
             }

             @Override
             public void onFailure(Call<List<Todo>> call, Throwable t) {
                 Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
             }
         });
    }
}