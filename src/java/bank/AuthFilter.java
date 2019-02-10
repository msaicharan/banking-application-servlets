package bank;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

@WebFilter("/AuthFilter")
public class AuthFilter implements Filter {

	private final Logger logger = Logger.getLogger(AuthFilter.class);
	
        @Override
	public void init(FilterConfig fConfig) throws ServletException {
		logger.info("AuthenticationFilter initialized");
	}
	
        @Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
                PrintWriter out = response.getWriter();
		
		String uri = req.getRequestURI();
		logger.info("Requested Resource::"+uri);
		
		HttpSession session = req.getSession(false);
		
		if(session == null && !(uri.endsWith("jsp") || uri.endsWith("html") || uri.endsWith("create") || uri.endsWith("login"))){
			logger.error("Unauthorized access request");
                        out.print("Unauthorized access request");
//			res.sendRedirect("login.html");
		}else{
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}
		
		
	}

        @Override
	public void destroy() {
		//close any resources here
	}

}
