package com.lx.project.config;

import com.lx.project.consts.Consts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SwaggerConfig {
	
	//@Value("${swagger.switch}")
	private boolean swaggerSwitch = true;

	@Bean
	public Docket api() {
		//添加head参数start
		ParameterBuilder tokenPar = new ParameterBuilder();
		List<Parameter> pars = new ArrayList<Parameter>();
		tokenPar.name(Consts.TOKEN).description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(true).build();
		pars.add(tokenPar.build());
		//添加head参数end

		return new Docket(DocumentationType.SWAGGER_2)
				.enable(swaggerSwitch)
				.apiInfo(apiInfo()).select()
				// 自行修改为自己的包路径
				.apis(RequestHandlerSelectors
				.basePackage("com.lx.project.controller"))
				.paths(PathSelectors.any())
				.build()
				.globalOperationParameters(pars);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("自定义").description("powered by lx")
				// 服务条款网址
				.version("1.0")
				.contact(new Contact("lx", "www.liangxublog.com", "www.liangxublog.com"))
				.build();
	}
}