package pl.piomin.microservices.edge;

import javax.servlet.http.HttpServletRequest;
//import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

//@Slf4j
public class PreFilter extends ZuulFilter {
  
  protected static Logger logger = LoggerFactory.getLogger(PreFilter.class.getName());
  
   @Autowired
   private StringRedisTemplate stringRedisTemplate;
   @Autowired
   private org.springframework.cloud.sleuth.Tracer tracer;
  @Override
  public String filterType() {
    return "pre";
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
	  org.springframework.cloud.sleuth.Span newSpan = tracer.createSpan("redis");
	  try {
	  	newSpan.tag("redis.op", "get");
	  	newSpan.tag("lc", "redis");
	  	newSpan.logEvent(org.springframework.cloud.sleuth.Span.CLIENT_SEND);
	  	// call redis service e.g
		stringRedisTemplate.opsForValue().set("aaa", "111");  
	  } finally {
	  	newSpan.tag("peer.service", "redisService");
	  	newSpan.tag("peer.ipv4", "127.0.0.1");
	  	newSpan.tag("peer.port", "1234");
	  	newSpan.logEvent(org.springframework.cloud.sleuth.Span.CLIENT_RECV);
	  	tracer.close(newSpan);
	  }
	//stringRedisTemplate.opsForValue().set("aaa", "111");  
    RequestContext ctx = RequestContext.getCurrentContext();
    HttpServletRequest request = ctx.getRequest();
    logger.info("PreFilter Request Method : " + request.getMethod() + " Request URL : " + request.getRequestURL().toString());
    return null;
  }

}
