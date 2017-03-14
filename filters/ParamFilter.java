package filters;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public final class ParamFilter implements Filter {

    static class FilteredRequest extends HttpServletRequestWrapper {

        static Pattern pattern = Pattern.compile("([A-Za-z0-9.@_-~#!¡¿°?&%$])");

        public FilteredRequest(ServletRequest request) {
            super((HttpServletRequest)request);
        }

        public String sanitize(String input) {
            String result = "";
	    Matcher parametros = pattern.matcher(input);
            for (int i = 0; i < input.length(); i++) {
		if(parametros.find()) {
	 	    result += input.charAt(i);   
		}
            }
            return result;
        }

        public String getParameter(String paramName) {
            String value = super.getParameter(paramName);
            if ("dangerousParamName".equals(paramName)) {
                value = sanitize(value);
            }
            return value;
        }

        public String[] getParameterValues(String paramName) {
            String values[] = super.getParameterValues(paramName);
            if ("dangerousParamName".equals(paramName)) {
                for (int index = 0; index < values.length; index++) {
                    values[index] = sanitize(values[index]);
                }
            }
            return values;
        }
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        chain.doFilter(new FilteredRequest(request), response);
    }

    public void destroy() {
    }

    public void init(FilterConfig filterConfig) {
    }
}