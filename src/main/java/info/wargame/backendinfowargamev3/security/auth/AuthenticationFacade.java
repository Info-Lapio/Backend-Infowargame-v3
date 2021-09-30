package info.wargame.backendinfowargamev3.security.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade {

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public String getEmail() {
        Authentication authentication = this.getAuthentication();
        if(authentication.getPrincipal() instanceof AuthDetail) {
            return ((AuthDetail) authentication.getPrincipal()).getUser().getEmail();
        }else {
            return this.getAuthentication().getName();
        }
    }
}
