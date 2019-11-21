package pl.piomin.microservices.edge;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class ErrorFilter extends ZuulFilter {
	
  protected static Logger logger = LoggerFactory.getLogger(ErrorFilter.class.getName());
  
  @Override
  public String filterType() {
    return "error";
  }

  @Override
  public int filterOrder() {
    return 1;
  }

  @Override
  public boolean shouldFilter() {
    return true;
  }

  @Override
  public Object run() {
    RequestContext ctx = RequestContext.getCurrentContext();
    HttpServletRequest request = ctx.getRequest();
    logger.debug("Error Filter:Request Method : " + request.getMethod() + " Request URL : " + request.getRequestURL().toString());
    return null;
  }
}
