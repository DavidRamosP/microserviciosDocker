package com.abc.prestamos.context;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ContextFilter implements Filter {

  private static final Logger logger = LoggerFactory.getLogger(ContextFilter.class);

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    Filter.super.init(filterConfig);
  }
  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {

    HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

    ContextHolder.getContext().setIdCorrelativo(httpServletRequest.getHeader(Context.ID_CORRELATIVO) );

    logger.debug("id-correlativo: {}", ContextHolder.getContext().getIdCorrelativo());

    filterChain.doFilter(httpServletRequest, servletResponse);
  }

  @Override
  public void destroy() {
    Filter.super.destroy();
  }
}
