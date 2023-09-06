package kr.ac.kopo.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        // 루트 설정 클래스를 반환 (스프링 빈 설정)
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        // DispatcherServlet 설정 클래스를 반환 (스프링 MVC 설정)
        return new Class<?>[] { WebConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        // DispatcherServlet의 매핑을 지정
        return new String[] { "/" };
    }
}