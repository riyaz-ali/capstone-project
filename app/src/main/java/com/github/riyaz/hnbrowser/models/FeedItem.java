package com.github.riyaz.hnbrowser.models;

/**
 * Model class for a single item in Hacker news feed
 */
public class FeedItem {
    private int id;
    private String title;
    private int points;
    private String user;
    private long time;
    private String time_ago;
    private int commentsCount;
    private String type;
    private String url;
    private String domain;

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

    public int getCommentsCount() {
        return commentsCount;
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
}
