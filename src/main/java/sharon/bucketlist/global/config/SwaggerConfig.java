package sharon.bucketlist.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Value("${server.base-url:http://localhost:8080}")
    private String baseUrl;

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("BucketList 프로젝트 Swagger")
                .version("v0.0.1")
                .description("BucketList 프로젝트의 API 명세서입니다.");
        return new OpenAPI()
                .components(new Components())
                .info(info)
                .addSecurityItem(new SecurityRequirement().addList("JWT"))

                // OAuth2 Scheme (Scopes 없이) - 카카오 요청에 따라 이메일과 같은 사용자 정보 Scope 추가 가능
                .addSecurityItem(new SecurityRequirement().addList("OAuth2"))

                .components(new Components()
                        .addSecuritySchemes("JWT", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT"))

                        .addSecuritySchemes("OAuth2", new SecurityScheme()
                                .type(SecurityScheme.Type.OAUTH2)
                                .flows(new OAuthFlows()
                                        .authorizationCode(new OAuthFlow()
                                                .authorizationUrl(baseUrl + "/authorize")
                                                .tokenUrl("https://kauth.kakao.com/oauth/token")))));
    }
}
