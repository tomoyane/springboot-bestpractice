package com.bestpractice.api.app;

import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.RequestInfoComponent;
import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

public class AppBean {

  @Configuration
  @EnableSwagger2
  public static  class SwaggerConfig {

    @Configuration
    public static class WebMvcConfig implements WebMvcConfigurer {
      @Autowired
      private RequestInfoComponent requestInfo;
      @Autowired
      private AuthComponent authComponent;

      @Bean
      public InterceptorController interceptorController() {
        return new InterceptorController(this.authComponent, this.requestInfo);
      }

      @Override
      public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptorController()).addPathPatterns("/api/**");
      }
    }

    @Bean
    public Docket swaggerSpringMvcPlugin() {

      return new Docket(DocumentationType.SWAGGER_2)
          .select()
          .paths(Predicates.not(PathSelectors.regex("/error")))
          .build()
          .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
      return new ApiInfo(
          "Spring boot best practice API",
          "Spring boot best practice API document",
          "0.0.1",
          "",
          "Spring boot best practice",
          "",
          ""
      );
    }
  }
}
