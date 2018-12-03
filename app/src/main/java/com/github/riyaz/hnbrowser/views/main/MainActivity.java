package com.github.riyaz.hnbrowser.views.main;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.github.riyaz.hnbrowser.R;
import com.github.riyaz.hnbrowser.models.FeedItem;
import com.github.riyaz.hnbrowser.views.detail.DetailActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.util.List;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;

public class MainActivity extends AppCompatActivity implements FeedItemAdapter.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // parse static feed resource (temporary only!)
        List<FeedItem> feed = new Gson().fromJson(
                new InputStreamReader(getResources().openRawResource(R.raw.feed)), new TypeToken<List<FeedItem>>(){}.getType());

        // prepare the RecyclerView
        RecyclerView recycler = findViewById(R.id.main_recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this, VERTICAL, false));

        FeedItemAdapter adapter = new FeedItemAdapter(this, this, feed);
        recycler.setAdapter(adapter);

        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(new ColorDrawable(Color.LTGRAY));
        recycler.addItemDecoration(divider);
    }

    @Override
    public void onClick(@NonNull FeedItem feed) {
        startActivity(DetailActivity.show(this, feed));
    }
}
