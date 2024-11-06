package king_min_ji_server.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Value("${cors.allowed-origin}")
    private String allowedOrigin;

    @Value("${cors.allowed-methods}")
    private String[] allowedMethods;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(allowedOrigin) // 허용할 출처
                        .allowedMethods(allowedMethods) // 허용할 메서드
                        .allowedHeaders("*")
                        .allowCredentials(true); // 인증 정보 허용
            }
        };
    }
}
