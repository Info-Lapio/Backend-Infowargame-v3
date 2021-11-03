package info.wargame.backendinfowargamev3.security.auth;

import info.wargame.backendinfowargamev3.entity.user.repository.UserRepository;
import info.wargame.backendinfowargamev3.error.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public AuthDetail loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .map(AuthDetail::new)
                .orElseThrow(UserNotFoundException::new);
    }
}
