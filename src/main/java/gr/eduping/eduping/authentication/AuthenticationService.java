package gr.eduping.eduping.authentication;

import gr.eduping.eduping.core.enums.Role;
import gr.eduping.eduping.core.exceptions.EntityNotAuthorizedException;
import gr.eduping.eduping.dto.AuthenticationRequestDTO;
import gr.eduping.eduping.dto.AuthenticationResponseDTO;
import gr.eduping.eduping.model.User;
import gr.eduping.eduping.repository.UserRepository;
import gr.eduping.eduping.security.JwtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO dto) throws EntityNotAuthorizedException {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));

        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new EntityNotAuthorizedException("User", "User not authorized"));

        String token = jwtService.generateToken(authentication.getName(), user.getRole().name(), user.getId());
        return new AuthenticationResponseDTO(token);
    }

    /**
     * Checks if the principal has admin role.
     * @return true if the principal has admin role, false otherwise.
     */
    public boolean isPrincipalAdmin() {
        Authentication principal = SecurityContextHolder.getContext().getAuthentication();
        return principal.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(Role.ROLE_ADMIN.name()));
    }

    /**
     * Checks if the principal is the same with the user that is going to be affected during a CRUD user request.
     * @param username the username of the user to be affected.
     * @return true if the principal is the to-be affected user, false otherwise.
     */
    @Transactional(rollbackOn = Exception.class)
    public boolean isPrincipalSelf(String username) {
        Authentication principal = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            return false;
        } else {
            return Objects.equals(principal.getName(), user.getUsername());
        }
    }

    /**
     * Checks if the principal is the same with the user that is going to be affected during a CRUD user request.
     * @param id the id of the user to be affected.
     * @return true if the principal is the to-be affected user, false otherwise.
     */
    @Transactional(rollbackOn = Exception.class)
    public boolean isPrincipalSelf(Long id) {
        Authentication principal = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return false;
        } else {
            return Objects.equals(principal.getName(), user.getUsername());
        }
    }
}
