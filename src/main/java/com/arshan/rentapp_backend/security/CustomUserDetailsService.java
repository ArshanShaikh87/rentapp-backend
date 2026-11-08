package com.arshan.rentapp_backend.security;

import com.arshan.rentapp_backend.user.RentAppUser;
import com.arshan.rentapp_backend.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        RentAppUser availableUser =
                userRepository.findByLoginId(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found with loginId:"+username ));
                return new RentAppUserDetails(availableUser);
    }
}
