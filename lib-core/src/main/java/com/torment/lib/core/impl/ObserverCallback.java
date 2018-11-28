package com.torment.lib.core.impl;

/**
 * Created by torment on 2017/6/28.
 */
public interface ObserverCallback<BASE_ENTITY> {
    public void onSuccess(BASE_ENTITY data);

    public void onFailure(int code, String message, boolean isHttpError);
}
