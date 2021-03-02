package io.daff.notes.util;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 复制工具类
 *
 * @author daffupman
 * @since 2021/3/2
 */
public class CopyUtil {

    /**
     * 单个对象的复制
     */
    public static <T> T copy(Object object, Class<T> clz) {
        if (object == null) return null;
        T obj;
        try {
            obj = clz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        BeanUtils.copyProperties(object, obj);
        return obj;
    }

    /**
     * 列表复制
     */
    public static <T> List<T> copyList(List<?> source, Class<T> clz) {
        List<T> targets = new ArrayList<>();
        if (!CollectionUtils.isEmpty(source)){
            for (Object c: source) {
                T obj = copy(c, clz);
                targets.add(obj);
            }
        }
        return targets;
    }
}
