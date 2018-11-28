package com.torment.lib.core.manage;

import android.content.Context;
import android.net.Uri;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 图片加载工具类<br/>
 * 头像<br/>
 * 标准<br/>
 * 大图<br/>
 * Created by torment on 16-7-14.
 */
public class FrescoManager {

    /**
     * 标准加载
     */
    public static void loadImage(@NonNull SimpleDraweeView view, @Nullable String uri) {
        if ("".equals(uri) || uri == null) {
            return;
        }
        view.setImageURI(Uri.parse(uri));
    }

    /**
     * 推荐调用方法[不受设置界面的系数影响]
     *
     * @param context
     * @param view
     * @param uri
     * @param width
     * @param height
     */
    public static void loadImageNull(Context context, @NonNull SimpleDraweeView view, @Nullable String uri, int width, int height) {
        if ("".equals(uri) || uri == null) {
            return;
        }
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(uri))
                .setResizeOptions(new ResizeOptions(width, height))
                .build();
        PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                .setOldController(view.getController())
                .setImageRequest(request)
                .build();
        view.setController(controller);
    }

//    /**
//     * 推荐调用方法
//     *
//     * @param context
//     * @param view
//     * @param uri
//     * @param width
//     * @param height
//     */
//    public static void loadImage(Context context, @NonNull SimpleDraweeView view, @Nullable String uri, int width, int height) {
//        if ("".equals(uri) || uri == null) {
//            return;
//        }
//        float thumbnailNum = SettingFragment.getThumbnailNumber();
//
//        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(uri))
//                .setResizeOptions(new ResizeOptions((int) (width / thumbnailNum), (int) (height / thumbnailNum)))
//                .build();
//        PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
//                .setOldController(view.getController())
//                .setImageRequest(request)
//                .build();
//        view.setController(controller);
//    }

    /**
     * 渐进加载图平
     *
     * @param view
     * @param uri
     */
    public static void progressiveRenderingImage(SimpleDraweeView view, String uri) {
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(uri))
                .setProgressiveRenderingEnabled(true) // 开启渐显动画，并不是所的图片都支持
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(view.getController())
                .build();
        view.setController(controller);
    }
//
//    // ------------------
//    // 加载普通图片
//    public static void loadImage(Context context, ImageView imageView, String url,
//                                 com.bumptech.glide.request.RequestListener listener) {
//        // 1. Wifi网络直接访问
//        // 2. 不是WiFi与成功直接访问
//        if (NetWorkUtils.isWiFiAvailable(context) ||
//                !NetWorkUtils.isWiFiAvailable(context) && SharedPreferencesUtilsManager.readWifiUseState()) {
//            Glide.with(context)
//                    .load(url) // 加载url
//                    .placeholder(R.color.color_iamge_background) // 站位符
//                    //.error() // 错误占位符
//                    .diskCacheStrategy(DiskCacheStrategy.ALL) // 磁盘缓存
//                    .skipMemoryCache(false) // 内存缓存 true跳过
//                    .listener(listener)
//                    .into(imageView);
//        }
//    }
//
//    // 加载圆形图片
//    public static void loadImageCircularHeader(Context context, ImageView imageView, String url,
//                                               com.bumptech.glide.request.RequestListener listener) {
//        if (NetWorkUtils.isWiFiAvailable(context) ||
//                !NetWorkUtils.isWiFiAvailable(context) && SharedPreferencesUtilsManager.readWifiUseState()) {
//            Glide.with(context)
//                    .load(url) // 加载url
//                    //.placeholder() // 站位符
//                    //.error() // 错误占位符
//                    .asBitmap()
//                    .centerCrop()
//                    .thumbnail(0.2f) // 缩略图属性
//                    .diskCacheStrategy(DiskCacheStrategy.ALL) // 磁盘缓存
//                    .skipMemoryCache(false) // 内存缓存 true跳过
//                    .listener(listener)
//                    .into(new BitmapImageViewTarget(imageView) {
//                        @Override
//                        protected void setResource(Bitmap resource) {
//                            RoundedBitmapDrawable circularBitmapDrawable =
//                                    RoundedBitmapDrawableFactory.create(context.getResources(), resource);
//                            circularBitmapDrawable.setCircular(true);
//                            imageView.setImageDrawable(circularBitmapDrawable);
//                        }
//                    });
//        }
//    }
//
//    // 清理图片
//    public static void clearImageView(View view) {
//        Glide.clear(view);
//    }
}