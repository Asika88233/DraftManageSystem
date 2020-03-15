package com.Asika.demo.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ErrorConfig implements ErrorPageRegistrar {

	@Override
	public void registerErrorPages(ErrorPageRegistry registry) {
		   ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/errorpage/401");
           ErrorPage error405Page = new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED, "/errorpage/405");
           ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/errorpage/404");
           ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/errorpage/500");
           registry.addErrorPages(error401Page,error405Page, error404Page, error500Page);
	}

}
