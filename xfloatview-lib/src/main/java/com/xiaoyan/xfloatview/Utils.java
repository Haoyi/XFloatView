package com.xiaoyan.xfloatview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.widget.ImageView;

import java.lang.reflect.Field;

/**
 * 工具类
 *
 * @author xiaoyan
 * @since 2018/9/13 上午2:15
 */
final class Utils {

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 得到设备屏幕的高度
     */
    static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 得到设备屏幕的宽度
     */
    static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    static final String STATUS_BAR_HEIGHT_RES_NAME = "status_bar_height";

    /**
     * 计算状态栏高度高度 getStatusBarHeight
     *
     * @return
     */
    static int getStatusBarHeight() {
        return getInternalDimensionSize(Resources.getSystem(),
                STATUS_BAR_HEIGHT_RES_NAME);
    }

    static int getInternalDimensionSize(Resources res, String key) {
        int result = 0;
        int resourceId = res.getIdentifier(key, "dimen", "android");
        if (resourceId > 0) {
            result = res.getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 旋转图片
     *
     * @param angle  旋转角度
     * @param bitmap 要旋转的图片
     * @return 旋转后的图片
     */
    static Bitmap rotate(Bitmap bitmap, int angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                bitmap.getHeight(), matrix, true);
    }

    /**
     * 获取ImageView的图片资源id
     *
     * @param imageView 要获取图片资源id的ImageView
     * @return 获取到的图片资源ID
     */

    static int getResId(ImageView imageView) {
        int resId = 0;

        if (imageView==null)
            return resId;

        Field[] fields=imageView.getClass().getDeclaredFields();
        for(Field f:fields){
            if(f.getName().equals("mBackgroundTintHelper")){
                f.setAccessible(true);
                try {
                    Object obj = f.get(imageView);
                    Field[] fields2=obj.getClass().getDeclaredFields();
                    for(Field f2:fields2){
                        if(f2.getName().equals("mBackgroundResId")){
                            f2.setAccessible(true);
                            resId = f2.getInt(obj);
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return resId;
    }

}
