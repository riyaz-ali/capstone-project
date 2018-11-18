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
    private String timeAgo;
    private String content;
    private boolean deleted;
    private boolean dead;
    private String type;
    private String url;
    private String domain;
    private List<Item> comments;
    private int level;
    private int commentsCount;
}
