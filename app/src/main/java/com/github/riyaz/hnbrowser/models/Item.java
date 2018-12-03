package com.github.riyaz.hnbrowser.models;


import java.util.List;

/**
 * Model class representing a single 'item' in Hacker News API
 */
public class Item {
    private int id;
    private String title;
    private int points;
    private String user;
    private long time;
    private String time_ago;
    private String content;
    private boolean deleted;
    private boolean dead;
    private String type;
    private String url;
    private String domain;
    private List<Item> comments;
    private int level;
    private int commentsCount;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getPoints() {
        return points;
    }

    public String getUser() {
        return user;
    }

    public long getTime() {
        return time;
    }

    public String getTimeAgo() {
        return time_ago;
    }

    public String getContent() {
        return content;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public boolean isDead() {
        return dead;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public String getDomain() {
        return domain;
    }

    public List<Item> getComments() {
        return comments;
    }

    public int getLevel() {
        return level;
    }

    public int getCommentsCount() {
        return commentsCount;
    }
}
