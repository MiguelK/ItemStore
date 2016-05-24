package com.flow.commons;

import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StringUtil {

    public static String trimToEmpty(String value) {
        if (Strings.isNullOrEmpty(value)) {
            return "";
        }

        return value.trim();
    }


    public static List<String> split(String list) {

        String s = StringUtils.trimToNull(list);
        if (s == null) {
            return Collections.emptyList();
        }

        String[] split = StringUtils.split(list, ",");
        return Arrays.asList(split);

    }
}
