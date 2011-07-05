package org.appfuse.examples.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientAuthenticationToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.verification.ClientAuthenticationCache;
import org.springframework.security.oauth2.provider.verification.DefaultClientAuthenticationCache;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.TreeMap;

/**
 * Controller for retrieving the model for and displaying the confirmation page
 * for access to a protected resource.
 *
 * @author Ryan Heaton
 */
@Controller
@RequestMapping("/confirm_access")
public class AccessConfirmationController {

    private ClientAuthenticationCache authenticationCache = new DefaultClientAuthenticationCache();
    @Autowired
    private ClientDetailsService clientDetailsService;

    @RequestMapping(method = RequestMethod.GET)
    protected ModelAndView confirm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ClientAuthenticationToken clientAuth = authenticationCache.getAuthentication(request, response);
        if (clientAuth == null) {
            throw new IllegalStateException("No client authentication request to authorize.");
        }

        TreeMap<String, Object> model = new TreeMap<String, Object>();
        ClientDetails client = clientDetailsService.loadClientByClientId(clientAuth.getClientId());
        model.put("auth_request", clientAuth);
        model.put("client", client);

        return new ModelAndView("access_confirmation", model);
    }
}
