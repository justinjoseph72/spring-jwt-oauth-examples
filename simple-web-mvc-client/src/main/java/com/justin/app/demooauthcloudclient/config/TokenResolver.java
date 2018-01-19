package com.justin.app.demooauthcloudclient.config;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        final Class<?> type = parameter.getParameterType();
        return (TokenObject.class == type);
    }

    @Override
    public Object resolveArgument(final MethodParameter parameter, final ModelAndViewContainer mavContainer, final NativeWebRequest webRequest, final WebDataBinderFactory binderFactory) throws Exception {

        final HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        final HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        String token = response.getHeader("api-token");
        TokenObject obj = new TokenObject();
        obj.setToken(token !=null ? token:"defaultToken");
        return obj;
    }
}
