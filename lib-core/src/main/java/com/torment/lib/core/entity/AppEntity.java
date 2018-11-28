package com.torment.lib.core.entity;

public class AppEntity<T> {
    public int code = 0;
    public String message = "";
    public String type = "";
    public boolean success = false;
    public T data;
}
