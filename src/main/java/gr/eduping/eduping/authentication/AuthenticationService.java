package gr.eduping.eduping.authentication;

import gr.eduping.eduping.core.exceptions.EntityNotAuthorizedException;
import gr.eduping.eduping.dto.AuthenticationRequestDTO;
import gr.eduping.eduping.dto.AuthenticationResponseDTO;
import gr.eduping.eduping.model.User;
import gr.eduping.eduping.repository.UserRepository;
import gr.eduping.eduping.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

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

        String token = jwtService.generateToken(authentication.getName(), user.getRole().name());
        return new AuthenticationResponseDTO(token);
    }
}
