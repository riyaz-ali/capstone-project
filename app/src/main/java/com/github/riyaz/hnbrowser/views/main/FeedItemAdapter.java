package com.github.riyaz.hnbrowser.views.main;


import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.riyaz.hnbrowser.R;
import com.github.riyaz.hnbrowser.models.FeedItem;
import com.pascalwelsch.arrayadapter.ArrayAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * {@code RecyclerView.Adapter} implementation for displaying {@link com.github.riyaz.hnbrowser.models.FeedItem}
 */
public class FeedItemAdapter extends ArrayAdapter<FeedItem, FeedItemAdapter.ViewHolder> {

    // VH impl
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.feed_title)
        TextView title;
        @BindView(R.id.feed_domain)
        TextView domain;
        @BindView(R.id.feed_points)
        TextView points;
        @BindView(R.id.feed_posted_by)
        TextView postedBy;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            domain.setPaintFlags(domain.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        }

        void bind(@NonNull FeedItem model){
            title.setText(model.getTitle());
            domain.setText(model.getDomain());
            points.setText(model.getPoints() + "");
            postedBy.setText(Html.
                    fromHtml(context.getString(R.string.main_posted_by, model.getUser(), model.getTimeAgo())));
        }


        @Override
        public void onClick(View v) {
            listener.onClick(getItem(getAdapterPosition()));
        }
    }

    // listener interface for parent to implement
    public interface OnClickListener {
        // called when a feed item is clicked from the list
        void onClick(@NonNull FeedItem feed);
    }

    // Context
    private Context context;

    // click listener implementation
    private OnClickListener listener;

    public FeedItemAdapter(@NonNull Context context, @NonNull OnClickListener listener, @NonNull List<FeedItem> feeds){
        super(feeds);
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_feed_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(getItem(i));
    }

    @Nullable
    @Override
    public Object getItemId(@NonNull FeedItem item) {
        return item.getId();
    }
}
