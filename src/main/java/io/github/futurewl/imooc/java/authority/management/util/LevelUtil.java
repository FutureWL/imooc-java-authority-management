package io.github.futurewl.imooc.java.authority.management.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 功能描述：标签工具
 *
 * @author weilai create by 2019-04-19:14:20
 * @version 1.0
 */
public class LevelUtil {

    public static final String SEPARATOR = ".";

    public static final String ROOT = "0";

    /**
     * 0
     * 0.1
     * 0.1.2
     * 0.1.3
     * 0.4
     *
     * @param parentLevel
     * @param parentId
     * @return
     */
    public static String calculateLevel(String parentLevel, int parentId) {
        if (StringUtils.isBlank(parentLevel)) {
            return ROOT;
        } else {
            return StringUtils.join(parentLevel, SEPARATOR, parentId);
        }
    }
}
