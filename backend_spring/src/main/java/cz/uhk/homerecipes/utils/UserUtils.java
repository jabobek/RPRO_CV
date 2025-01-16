package cz.uhk.homerecipes.utils;

import cz.uhk.homerecipes.models.User;
import cz.uhk.homerecipes.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class UserUtils {
    public static User getCurrentUser(UserRepository userRepository) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(((UserDetails) authentication.getPrincipal()).getUsername()).orElse(null);
    }
}
