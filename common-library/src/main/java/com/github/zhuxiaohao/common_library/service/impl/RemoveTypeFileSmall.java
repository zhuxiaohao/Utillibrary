package com.github.zhuxiaohao.common_library.service.impl;


import com.github.zhuxiaohao.common_library.entity.CacheObject;
import com.github.zhuxiaohao.common_library.service.CacheFullRemoveType;
import com.github.zhuxiaohao.common_library.util.FileUtils;

/**
 * ClassName: RemoveTypeFileSmall <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2014年11月20日 下午6:27:45 <br/>
 * 删除类型时，高速缓存已满，缓存的数据类型是字符串，它代表一个文件的路径。
 *
 * @author chenhao
 * @since JDK 1.6
 */
public class RemoveTypeFileSmall implements CacheFullRemoveType<String> {

    private static final long serialVersionUID = 1L;

    @Override
    public int compare(CacheObject<String> obj1, CacheObject<String> obj2) {
        long sizeOfFile1 = (obj1 == null ? -1 : FileUtils.getFileSize(obj1.getData()));
        long sizeOfFile2 = (obj2 == null ? -1 : FileUtils.getFileSize(obj2.getData()));
        if (sizeOfFile1 == sizeOfFile2) {
            if (obj1.getUsedCount() == obj2.getUsedCount()) {
                return (obj1.getEnterTime() > obj2.getEnterTime()) ? 1 : ((obj1.getEnterTime() == obj2.getEnterTime()) ? 0 : -1);
            }
            return (obj1.getUsedCount() > obj2.getUsedCount() ? 1 : -1);
        }
        return (sizeOfFile1 > sizeOfFile2 ? 1 : -1);
    }
}
