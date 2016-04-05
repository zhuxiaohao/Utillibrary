package com.github.zhuxiaohao.common_library.service.impl;


import com.github.zhuxiaohao.common_library.entity.CacheObject;
import com.github.zhuxiaohao.common_library.service.CacheFullRemoveType;
import com.github.zhuxiaohao.common_library.util.ObjectUtils;

/**
 * 
 * ClassName: RemoveTypeDataBig <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2014年11月20日 下午6:23:48 <br/>
 * 拆卸式缓存时已满。当高速缓存已满，比较对象缓存中的数据，如果数据是大首先删除
 * @author chenhao
 * @version @param <T>
 * @since JDK 1.6
 */
public class RemoveTypeDataBig<T> implements CacheFullRemoveType<T> {

    private static final long serialVersionUID = 1L;

    @Override
    public int compare(CacheObject<T> obj1, CacheObject<T> obj2) {
        return ObjectUtils.compare(obj2.getData(), obj1.getData());
    }
}
