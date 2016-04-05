package com.github.zhuxiaohao.common_library.util;


import com.github.zhuxiaohao.common_library.service.HttpCache;
import com.github.zhuxiaohao.common_library.service.impl.ImageCache;
import com.github.zhuxiaohao.common_library.service.impl.ImageSDCardCache;

/**
 * ClassName: CacheManager <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2014年11月20日 下午6:36:06 <br/>
 * 缓存管理器
 *
 * @author chenhao
 * @since JDK 1.6
 */
public class CacheManager {

    private static HttpCache httpCache = null;

    private CacheManager() {
        throw new AssertionError();
    }

    /**
     * get the singleton instance of HttpCache
     *
     * @param context {@link Activity#getApplicationContext()}
     * @return
     */
    public static HttpCache getHttpCache(android.content.Context context) {
        if (httpCache == null) {
            synchronized (CacheManager.class) {
                if (httpCache == null) {
                    httpCache = new HttpCache(context);
                }
            }
        }
        return httpCache;
    }

    /**
     * get the singleton instance of ImageCache
     *
     * @return
     * @see ImageCacheManager#getImageCache()
     */
    public static ImageCache getImageCache() {
        return ImageCacheManager.getImageCache();
    }

    /**
     * get the singleton instance of ImageSDCardCache
     *
     * @return
     * @see ImageCacheManager#getImageSDCardCache()
     */
    public static ImageSDCardCache getImageSDCardCache() {
        return ImageCacheManager.getImageSDCardCache();
    }
}
