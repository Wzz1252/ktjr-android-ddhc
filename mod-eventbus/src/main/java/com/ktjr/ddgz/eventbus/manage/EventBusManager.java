package com.ktjr.ddgz.eventbus.manage;

import org.greenrobot.eventbus.EventBus;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 应用事件统一分发管理器
 * Created by torment on 16/7/11.
 */
public final class EventBusManager {
    private static final String TAG = EventBusManager.class.getSimpleName();
    private static EventBusManager sInstance;

    /**
     * 事件订阅者列表
     */
    private ConcurrentLinkedQueue<SubscriberListener> mSubscriberListeners = new ConcurrentLinkedQueue<>();

    /**
     * 事件类型
     */
    public static final class EventType {
    }

    /**
     * 事件信息
     */
    public static final class MessageEvent {
        public int eventType;
        public Object data;
        public Object data2;

        public MessageEvent(int eventType) {
            this.eventType = eventType;
        }

        public MessageEvent(int eventType, Object data) {
            this.eventType = eventType;
            this.data = data;
        }

        public MessageEvent(int eventType, Object data, Object data2) {
            this(eventType, data);
            this.data2 = data2;
        }
    }

    private EventBusManager() {
        EventBus.getDefault().register(this);
    }

    @Override
    protected void finalize() throws Throwable {
        mSubscriberListeners.clear();
        EventBus.getDefault().unregister(this);
        super.finalize();
    }

    public static EventBusManager getInstance() {
        if (sInstance == null) {
            sInstance = new EventBusManager();
        }
        return sInstance;
    }

    /**
     * 分发一个事件
     *
     * @param event
     */
    public void post(MessageEvent event) {
        EventBus.getDefault().post(event);
    }

    /**
     * 添加一个事件订阅者
     *
     * @param subscriber 事件订阅者
     */
    public void register(SubscriberListener subscriber) {
        if (!mSubscriberListeners.contains(subscriber)) {
            mSubscriberListeners.add(subscriber);
        }
    }

    /**
     * 移除一个事件订阅者
     *
     * @param subscriber 事件订阅者
     */
    public void unRegister(SubscriberListener subscriber) {
        if (mSubscriberListeners.contains(subscriber)) {
            mSubscriberListeners.remove(subscriber);
        }
    }

    /**
     * 是否注册事件
     *
     * @param subscriber 事件监听者
     * @return
     */
    public boolean isSubscriber(SubscriberListener subscriber) {
        return mSubscriberListeners.contains(subscriber);
    }

    /**
     * 事件回调接口
     *
     * @param event
     */
//    @Subscribe
    public void onEventMainThread(MessageEvent event) {
//        LogUtils.debug(TAG, "dispacheing event: " + event.eventType);
        Iterator<SubscriberListener> iterator = mSubscriberListeners.iterator();
        while (iterator.hasNext()) {
            SubscriberListener subscriber = iterator.next();
            if (subscriber != null) {
                try {
                    subscriber.onEventHandler(event);
                } catch (Exception e) {
//                    LogUtils.error(TAG, Log.getStackTraceString(e));
                    continue;
                }
            }
        }
    }

    /**
     * 事件订阅者回调接口
     * 注：在UI主线程运行
     */
    public static interface SubscriberListener {
        public void onEventHandler(MessageEvent event);
    }
}