package com.erhuo.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;


@Configuration
@EnableWebMvc
@ComponentScan("com.erhuo.controller")
public class WebConfig implements WebMvcConfigurer, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


//    /**
//     * mvc默认jsp视图解析器
//     * @return 内部资源视图解析器
//     */
//    @Bean
//    public InternalResourceViewResolver internalResourceViewResolver() {
//        return new InternalResourceViewResolver("/static/", ".html");
//    }

    /**
     * 本地资源映射路径
     *
     * @param registry 资源处理注册
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**") //访问路径
                .addResourceLocations("/WEB-INF/static/"); //真实路径，映射的真实路径末尾必须加/
//        registry.addResourceHandler("/img/**").addResourceLocations("/img/");
//        registry.addResourceHandler("/fonts/**").addResourceLocations("/fonts/");
//        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
//        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
    }

    /**
     * 首页跳转
     *
     * @param registry 视图控制器注册
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
    }

//    /**
//     * 自定义拦截器
//     * @param registry 拦截器注册
//     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        String[] path = {"/admin/*","/tch/*","/stu/*"};
//        String[] eclp = {"/*login"};
//        registry.addInterceptor(new LoginInterceptor())
//                .addPathPatterns(path)
//                .excludePathPatterns(eclp);
//    }

    /**
     * mvc处理返回数据
     *
     * @return json格式转换
     */
    @Bean
    public MappingJackson2HttpMessageConverter mapConverter() {
        return new MappingJackson2HttpMessageConverter();
    }

    /**
     * @return 模板组件信息
     */
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(this.applicationContext);
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);//默认
        templateResolver.setCacheable(true);
        templateResolver.setCharacterEncoding("UTF-8");
        //templateResolver.setOrder(1);//视图查找顺序
        return templateResolver;
    }

    /**
     * 依赖于模板资源
     *
     * @return 模板引擎组件
     */
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    /**
     * @param templateEngine 依赖于模板引擎组件
     * @return 视图解析组件
     */
    @Bean
    public ViewResolver viewResolver(SpringTemplateEngine templateEngine) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine);
        viewResolver.setCharacterEncoding("UTF-8");
        return viewResolver;
    }

    /**
     *
     * @return 文件上传处理器
     */
    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }



}