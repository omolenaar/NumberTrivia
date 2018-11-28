package com.example.olgam.numbertrivia;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private TriviaAdapter mAdapter;
    private ArrayList<Trivia> mTriviaItems;
    private TextView mNumberText;
    private TextView mTriviaText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = findViewById(R.id.rvTriviaItem);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager((mLayoutManager));

        final TriviaAdapter mAdapter = new TriviaAdapter(mTriviaItems);
        mRecyclerView.setAdapter(mAdapter);

        mTriviaItems = new ArrayList<>();
        initializeData();
        updateUI();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestData();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void requestData() {

        NumbersApiService service = NumbersApiService.retrofit.create(NumbersApiService.class);
        int number = (int) (Math.random() * 1000);
        number = number + 1;
        final String numberAsString = Integer.toString(number);
        Call<Trivia> call = service.getTrivia(number);
        call.enqueue(new Callback<Trivia>() {
            @Override
            public void onResponse(Call<Trivia> call, Response<Trivia> response) {
                Trivia trivia = response.body();
                mTriviaItems.add(new Trivia(trivia.getText(), trivia.getNumber()));
                updateUI();
            }

            @Override
            public void onFailure(Call<Trivia> call, Throwable t) {
            }
        });
    }


    private void initializeData() {
        Trivia newTriviaItem1 = new Trivia("text", "999");
        mTriviaItems.add(newTriviaItem1);
        Trivia newTriviaItem2 = new Trivia("Now it is time to add the view where we want to see the quote of the day. Open the layout file for the main activity, called content_main.xml", "45");
        mTriviaItems.add(newTriviaItem2);
        Trivia newTriviaItem3 = new Trivia("bla bla bla bla bla bla bla bla blab labl lab lb lba lb alb a lba lb alb a", " 220");
        mTriviaItems.add(newTriviaItem3);
        Trivia newTriviaItem4 = new Trivia("This happened when my RecyclerView was inside a ScrollView and I was using Android Support Library 23.0. To fix, I updated to Android Support Library 23.2", " 56");
        mTriviaItems.add(newTriviaItem4);

    }

    private void updateUI() {
        if (mAdapter == null) {
            mAdapter = new TriviaAdapter(mTriviaItems);
            mRecyclerView.setAdapter(mAdapter);
        }
        mAdapter.notifyDataSetChanged();


    }
}
