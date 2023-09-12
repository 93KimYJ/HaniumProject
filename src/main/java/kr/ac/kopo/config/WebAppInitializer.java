package kr.ac.kopo.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        // 猷⑦듃 �꽕�젙 �겢�옒�뒪瑜� 諛섑솚 (�뒪�봽留� 鍮� �꽕�젙)
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        // DispatcherServlet �꽕�젙 �겢�옒�뒪瑜� 諛섑솚 (�뒪�봽留� MVC �꽕�젙)
        return new Class<?>[] { WebConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        // DispatcherServlet�쓽 留ㅽ븨�쓣 吏��젙
        return new String[] { "/" };
    }
}