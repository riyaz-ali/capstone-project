package com.github.riyaz.hnbrowser.views.detail;

import android.os.Build;
import android.support.annotation.NonNull;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Space;
import android.widget.TextView;

import com.github.riyaz.hnbrowser.R;
import com.github.riyaz.hnbrowser.models.Item;
import com.xwray.groupie.ExpandableGroup;
import com.xwray.groupie.ExpandableItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentItem extends com.xwray.groupie.Item<CommentItem.VH> implements ExpandableItem {

    private final Item comment;
    private final int depth;

    public CommentItem(@NonNull Item comment, int depth){
        this.comment = comment;
        this.depth = depth;
    }

    @Override
    public void bind(@NonNull VH viewHolder, int position) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            viewHolder.text.setText(Html.fromHtml(comment.getContent(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            viewHolder.text.setText(Html.fromHtml(comment.getContent()));
        }

        viewHolder.author.setText(comment.getUser());
        viewHolder.timeAgo.setText(comment.getTimeAgo());

        // gap width
        ViewGroup.LayoutParams lp = viewHolder.iv.getLayoutParams();
        lp.width = depth * 25;
        viewHolder.iv.setLayoutParams(lp);
    }

    @Override
    public int getLayout() {
        return R.layout.adapter_comment_item;
    }

    @Override
    public void setExpandableGroup(@NonNull ExpandableGroup onToggleListener) {
        // no op
    }

    @NonNull
    @Override
    public VH createViewHolder(@NonNull View itemView) {
        return new VH(itemView);
    }

    public static class VH extends com.xwray.groupie.ViewHolder {
        @BindView(R.id.comment_text)
        TextView text;
        @BindView(R.id.comment_gap)
        Space iv;
        @BindView(R.id.comment_author)
        TextView author;
        @BindView(R.id.comment_time_ago)
        TextView timeAgo;

        public VH(@NonNull View rootView) {
            super(rootView);
            ButterKnife.bind(this, rootView);
        }
    }
}
