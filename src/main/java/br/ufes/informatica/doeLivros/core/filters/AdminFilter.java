package br.ufes.informatica.doeLivros.core.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.ufes.informatica.doeLivros.core.domain.Role;
import br.ufes.informatica.doeLivros.core.domain.User;

//https://stackoverflow.com/tags/servlet-filters/info
public class AdminFilter implements Filter {

	@Override
	public void init(FilterConfig config) throws ServletException {
		// If you have any <init-param> in web.xml, then you could get them
		// here by config.getInitParameter("name") and assign it as field.
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/core/login/index.xhtml"); // No logged-in user found, so
																							// redirect to login page.
		} else {
			User user = (User) session.getAttribute("user");
			if (user.getRolesList().iterator().next().getName().compareTo(Role.ADMIN_ROLE_NAME) == 0) {
				chain.doFilter(req, res); // Logged-in ADMIN found, so just continue request.
			} else {
				response.sendRedirect(request.getContextPath() + "/index.xhtml");
			}
		}
	}

	@Override
	public void destroy() {
		// If you have assigned any expensive resources as field of
		// this Filter class, then you could clean/close them here.
	}

}