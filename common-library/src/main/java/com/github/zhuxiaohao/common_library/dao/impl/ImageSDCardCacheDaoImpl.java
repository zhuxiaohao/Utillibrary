package com.github.zhuxiaohao.common_library.dao.impl;


import com.github.zhuxiaohao.common_library.controller.DbConstants;
import com.github.zhuxiaohao.common_library.dao.ImageSDCardCacheDao;
import com.github.zhuxiaohao.common_library.entity.CacheObject;
import com.github.zhuxiaohao.common_library.service.impl.ImageSDCardCache;
import com.github.zhuxiaohao.common_library.util.SqliteUtils;
import com.github.zhuxiaohao.common_library.util.StringUtils;

/**
 * 
 * ClassName: ImageSDCardCacheDaoImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2014年11月20日 下午6:16:34 <br/>
 *ImageSDCardCacheDao  接口实现
 * @author chenhao
 * @version 
 * @since JDK 1.6
 */
public class ImageSDCardCacheDaoImpl implements ImageSDCardCacheDao {

    private SqliteUtils sqliteUtils;

    public ImageSDCardCacheDaoImpl(SqliteUtils sqliteUtils) {
        this.sqliteUtils = sqliteUtils;
    }

    @Override
    public boolean putIntoImageSDCardCache(ImageSDCardCache imageSDCardCache, String tag) {
        if (imageSDCardCache == null || StringUtils.isEmpty(tag)) {
            return false;
        }

        StringBuilder selection = new StringBuilder();
        selection.append(DbConstants.IMAGE_SDCARD_CACHE_TABLE_TAG).append("=?");
        String[] selectionArgs = { tag };
        android.database.Cursor cursor = sqliteUtils.getDb().query(DbConstants.IMAGE_SDCARD_CACHE_TABLE_TABLE_NAME, null, selection.toString(), selectionArgs, null, null, null);
        if (cursor == null) {
            return true;
        }

        if (cursor != null && cursor.getCount() > 0) {
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                CacheObject<String> value = new CacheObject<String>();
                String imageUrl = cursor.getString(DbConstants.IMAGE_SDCARD_CACHE_TABLE_URL_INDEX);
                value.setData(cursor.getString(DbConstants.IMAGE_SDCARD_CACHE_TABLE_PATH_INDEX));
                value.setUsedCount(cursor.getInt(DbConstants.IMAGE_SDCARD_CACHE_TABLE_USED_COUNT_INDEX));
                value.setPriority(cursor.getInt(DbConstants.IMAGE_SDCARD_CACHE_TABLE_PRIORITY_INDEX));
                value.setExpired(cursor.getInt(DbConstants.IMAGE_SDCARD_CACHE_TABLE_IS_EXPIRED_INDEX) == 1);
                value.setForever(cursor.getInt(DbConstants.IMAGE_SDCARD_CACHE_TABLE_IS_FOREVER_INDEX) == 1);
                imageSDCardCache.put(imageUrl, value);
            }
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return true;
    }

    @Override
    public boolean deleteAndInsertImageSDCardCache(ImageSDCardCache imageSDCardCache, String tag) {
        if (imageSDCardCache == null || StringUtils.isEmpty(tag)) {
            return false;
        }

        android.database.sqlite.SQLiteDatabase db = sqliteUtils.getDb();
        db.beginTransaction();
        try {
            StringBuilder whereClause = new StringBuilder();
            whereClause.append(DbConstants.IMAGE_SDCARD_CACHE_TABLE_TAG).append("=?");
            String[] whereArgs = { tag };
            db.delete(DbConstants.IMAGE_SDCARD_CACHE_TABLE_TABLE_NAME, whereClause.toString(), whereArgs);

            String key;
            CacheObject<String> value;
            for (java.util.Map.Entry<String, CacheObject<String>> entry : imageSDCardCache.entrySet()) {
                if (entry != null && (key = entry.getKey()) != null && (value = entry.getValue()) != null) {
                    db.insert(DbConstants.IMAGE_SDCARD_CACHE_TABLE_TABLE_NAME, null, cacheObjectToCV(tag, key, value));
                }
            }

            db.setTransactionSuccessful();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            db.endTransaction();
        }
    }

    /**
     * @param tag
     * @param url
     * @param value
     * @return
     */
    private static android.content.ContentValues cacheObjectToCV(String tag, String url, CacheObject<String> value) {
        android.content.ContentValues values = new android.content.ContentValues();
        values.put(DbConstants.IMAGE_SDCARD_CACHE_TABLE_TAG, tag);
        values.put(DbConstants.IMAGE_SDCARD_CACHE_TABLE_URL, url);
        values.put(DbConstants.IMAGE_SDCARD_CACHE_TABLE_PATH, value.getData());
        values.put(DbConstants.IMAGE_SDCARD_CACHE_TABLE_ENTER_TIME, value.getEnterTime());
        values.put(DbConstants.IMAGE_SDCARD_CACHE_TABLE_LAST_USED_TIME, value.getLastUsedTime());
        values.put(DbConstants.IMAGE_SDCARD_CACHE_TABLE_USED_COUNT, value.getUsedCount());
        values.put(DbConstants.IMAGE_SDCARD_CACHE_TABLE_PRIORITY, value.getPriority());
        values.put(DbConstants.IMAGE_SDCARD_CACHE_TABLE_IS_EXPIRED, value.isExpired() ? 1 : 0);
        values.put(DbConstants.IMAGE_SDCARD_CACHE_TABLE_IS_FOREVER, value.isForever() ? 1 : 0);
        return values;
    }
}
