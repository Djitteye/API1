package service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.User;
import repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(String nom, String email, String password, String role) {
        // Vérifiez si l'utilisateur existe déjà
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Cet email est déjà pris");
        }

        // Créez un nouvel utilisateur
        User user = new User();
        user.setNom(nom);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);

        // Enregistrez l'utilisateur dans la base de données
        return userRepository.save(user);
    }
    
    
    public User seConnecter(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User utilisateur = userOptional.get();

            // Vérification du mot de passe (dans un scénario de production, utilisez BCryptPasswordEncoder)
            if (utilisateur.getPassword().equals(password)) {
                return utilisateur;
            }
        }

        return null; // Retourne null si l'utilisateur n'est pas trouvé ou le mot de passe est incorrect
    }
}