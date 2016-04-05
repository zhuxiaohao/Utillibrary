package com.github.zhuxiaohao.common_library.service.impl;


import com.github.zhuxiaohao.common_library.entity.CacheObject;
import com.github.zhuxiaohao.common_library.service.CacheFullRemoveType;

/**
 * 
 * ClassName: RemoveTypeNotRemove <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2014年11月20日 下午6:29:15 <br/>
 * 拆卸式缓存时已满。没有删除任何一个，这意味着没有什么可以把后
 * @author chenhao
 * @version @param <T>
 * @since JDK 1.6
 */
public class RemoveTypeNotRemove<T> implements CacheFullRemoveType<T> {

    private static final long serialVersionUID = 1L;

    @Override
    public int compare(CacheObject<T> obj1,CacheObject<T> obj2) {
        return 0;
    }
}
