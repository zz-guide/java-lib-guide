package com.xulei.boot01.resolver;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class MyLocaleResolver implements LocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        //此处可以根据项目需要修改代码，比如使用session或cookie
        String l = request.getParameter("l");
        Locale locale = null;
        if (StringUtils.isEmpty(l)) {
            locale = Locale.getDefault();
        } else {
            String[] split = l.split("_");
            if (split.length == 1) {
                locale = new Locale(split[0]);
            } else {
                locale = new Locale(split[0], split[1]);
            }
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }

}
