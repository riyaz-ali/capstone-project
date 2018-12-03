package com.github.riyaz.hnbrowser.views.detail;

import android.support.annotation.NonNull;

import com.github.riyaz.hnbrowser.models.Item;
import com.xwray.groupie.ExpandableGroup;

public class CommentGroup extends ExpandableGroup {

    public CommentGroup(@NonNull Item comment){
        this(comment, 0);
    }

    public CommentGroup(@NonNull Item comment, int depth){
        super(new CommentItem(comment, depth), true);

        // add groups for child
        for(Item child : comment.getComments())
            add(new CommentGroup(child, depth + 1));
    }
}
