package info.wargame.backendinfowargamev3.security.auth;

import info.wargame.backendinfowargamev3.entity.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public AuthDetail loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        return userRepository.findByEmail(phoneNumber)
                .map(AuthDetail::new)
                .orElseThrow(RuntimeException::new);
    }
}
