package com.github.riyaz.hnbrowser.views.detail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.github.riyaz.hnbrowser.R;
import com.github.riyaz.hnbrowser.models.FeedItem;
import com.github.riyaz.hnbrowser.models.Item;
import com.google.gson.Gson;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.ViewHolder;

import java.io.InputStreamReader;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    public static Intent show(@NonNull Context context, @NonNull FeedItem feed){
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("FEED_ID", feed.getId());
        return intent;
    }

    @BindView(R.id.feed_title)
    TextView title;
    @BindView(R.id.feed_domain)
    TextView domain;
    @BindView(R.id.feed_points)
    TextView points;
    @BindView(R.id.feed_posted_by)
    TextView postedBy;

    @BindView(R.id.detail_comments)
    RecyclerView comments;

    // currently displayed item
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        domain.setPaintFlags(domain.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        // parse static Item resource (temporary only!)
        item = new Gson().fromJson(
                new InputStreamReader(getResources().openRawResource(R.raw.item)), Item.class);
        render(item);
    }

    // render the Item object
    private void render(@NonNull Item item){
        setTitle(item.getTitle());

        title.setText(item.getTitle());
        domain.setText(item.getDomain());
        points.setText(item.getPoints() + "");
        postedBy.setText(Html.fromHtml(getString(R.string.main_posted_by, item.getUser(), item.getTimeAgo())));

        // render comments
        GroupAdapter _comments = new GroupAdapter<>();
        for(Item comment : item.getComments())
            _comments.add(new CommentGroup(comment));
        comments.setAdapter(_comments);
        comments.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.detail_open_in_new_window){
            // open link in browser
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("https://news.ycombinator.com/item?id=%d", this.item.getId()))));
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
