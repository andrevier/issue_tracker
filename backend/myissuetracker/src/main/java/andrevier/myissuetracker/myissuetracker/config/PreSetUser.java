package andrevier.myissuetracker.myissuetracker.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import andrevier.myissuetracker.myissuetracker.dao.UserRepository;
import andrevier.myissuetracker.myissuetracker.model.User;

@Configuration
public class PreSetUser {
    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository){
        return args ->  {
            User user1 = new User(
                "Marco de la Vega",
                "marconet", 
                "marco@gmail.com"
            );
            User user2 = new User(
                "Maria Barros", 
                "marianarede",
                "maria@gmail.com"
            );
            User user3 = new User(
                "Dora Campos", 
                "doranarede",
                "dora@gmail.com"
            );

            userRepository.saveAll(List.of(user1, user2, user3));


        };
    }
    
}
