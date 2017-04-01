package com.tengmei.trade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.tengmei.trade.interceptor.WechatOAuth2Interceptor;
import com.tengmei.wechat.rest.converter.WxMappingJackson2HttpMessageConverter;

@SpringBootApplication
@ComponentScan(basePackages = { "com.tengmei" })
public class TradeServiceApplication {
	@Autowired
	private WxMappingJackson2HttpMessageConverter wxMappingJackson2HttpMessageConverter;

	public static void main(String[] args) {
		SpringApplication.run(TradeServiceApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {

			@Autowired
			WechatOAuth2Interceptor wechatOAuth2Interceptor;

			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE");
			}

//			@Override
//			public void addInterceptors(InterceptorRegistry registry) {
//				registry.addInterceptor(wechatOAuth2Interceptor).addPathPatterns("/productindex")
//						.addPathPatterns("/main").addPathPatterns("/rest/**")
//						.excludePathPatterns("/wechat/**", "/static/**");
//				super.addInterceptors(registry);
//			}

			@Override
			public void configurePathMatch(PathMatchConfigurer configurer) {
				super.configurePathMatch(configurer);
				configurer.setUseSuffixPatternMatch(false);
			}
		};
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		RestTemplate restTemplate = builder.build();
		restTemplate.getMessageConverters().add(wxMappingJackson2HttpMessageConverter);
		return restTemplate;
	}
}
