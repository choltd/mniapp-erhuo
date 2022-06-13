package com.erhuo;

import com.erhuo.config.RedisConfig;
import com.erhuo.config.RootConfig;
import com.erhuo.config.WebConfig;
import com.erhuo.config.WebSocketConfig;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

public class Init extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class, WebSocketConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{new HiddenHttpMethodFilter(),new CharacterEncodingFilter("UTF-8")};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        super.customizeRegistration(registration);
        registration.setMultipartConfig(new MultipartConfigElement(null,2000000,400000,0));
    }
//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        super.onStartup(servletContext);
        //自定义字符过滤器
//        EncodingFilter encodingFilter=new EncodingFilter();
//        FilterRegistration.Dynamic register= servletContext.addFilter("encoding", encodingFilter);
//        register.addMappingForUrlPatterns(
//                EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE), false, "/*");

//    mvc字符过滤器
//        CharacterEncodingFilter charEncodingFilter = new CharacterEncodingFilter();
//        charEncodingFilter.setEncoding("utf-8");
//        FilterRegistration.Dynamic register = servletContext.addFilter("encoding", charEncodingFilter);
//        register.addMappingForUrlPatterns(
//                EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE), false, "/*");
//
//    }

}
