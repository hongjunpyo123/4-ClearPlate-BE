package com.qithon.clearplate.global.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
      //  .servers(List.of(new Server().url("https://api.clearplate.store"))) // 배포 시 에는 주석 풀고 로컬 시에는 주석하고 테스트
        .components(new Components()
            .addSecuritySchemes("bearerAuth",
                new SecurityScheme()
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")
                    .in(SecurityScheme.In.HEADER)
                    .name("Authorization"))
        )
        .info(new Info()
            .title("ClearPlate API")
            .description("API 호출에는 JWT 인증이 필요합니다.(role USER/ADMIN)")
            .version("1.0.0"))
        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
  }
}
