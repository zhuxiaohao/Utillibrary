package com.github.zhuxiaohao.common_library.service.impl;


import com.github.zhuxiaohao.common_library.entity.CacheObject;
import com.github.zhuxiaohao.common_library.service.CacheFullRemoveType;

/**
 * ClassName: RemoveTypeLastUsedTimeFirst <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2014年11月20日 下午6:28:16 <br/>
 * 拆卸式缓存时已满。当高速缓存已满，比较对象缓存最近使用的时间，如果时间是较小的删除它
 *
 * @author chenhao
 * @version @param <T>
 * @since JDK 1.6
 */
public class RemoveTypeLastUsedTimeFirst<T> implements CacheFullRemoveType<T> {

    private static final long serialVersionUID = 1L;

    @Override
    public int compare(CacheObject<T> obj1, CacheObject<T> obj2) {
        return (obj1.getLastUsedTime() > obj2.getLastUsedTime()) ? 1 : ((obj1.getLastUsedTime() == obj2.getLastUsedTime()) ? 0 : -1);
    }
}
