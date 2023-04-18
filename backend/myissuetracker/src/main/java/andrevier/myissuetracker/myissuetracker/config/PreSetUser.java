package andrevier.myissuetracker.myissuetracker.config;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.OffsetDateTime;

import andrevier.myissuetracker.myissuetracker.dao.ManageProjectRepository;
import andrevier.myissuetracker.myissuetracker.dao.ProjectRepository;
import andrevier.myissuetracker.myissuetracker.dao.ProjectTimeRepository;
import andrevier.myissuetracker.myissuetracker.dao.UserRepository;
import andrevier.myissuetracker.myissuetracker.model.ManageProject;
import andrevier.myissuetracker.myissuetracker.model.ManageProjectId;
import andrevier.myissuetracker.myissuetracker.model.Project;
import andrevier.myissuetracker.myissuetracker.model.ProjectTime;
import andrevier.myissuetracker.myissuetracker.model.User;

@Configuration
public class PreSetUser {
    
    @Bean
    CommandLineRunner commandLineRunner(
            UserRepository userRepository,
            ProjectRepository projectRepository,
            ProjectTimeRepository projectTimeRepository,
            ManageProjectRepository manageProjectRepository) {
        return args -> {
            User user1 = new User(
                    "Marco de la Vega",
                    "marconet",
                    "marco@gmail.com");
            User user2 = new User(
                    "Maria Barros",
                    "marianarede",
                    "maria@gmail.com");
            User user3 = new User(
                    "Dora Campos",
                    "doranarede",
                    "dora@gmail.com");

            userRepository.saveAll(List.of(user1, user2, user3));

            // Projects for the user 1.
            Project p1 = projectRepository.save(
                    new Project(
                            "Buy a car",
                            "Save money to buy a car in one year."));

            ProjectTime p1Time = projectTimeRepository.save(
                    new ProjectTime(
                            OffsetDateTime.parse("2023-04-17T09:20:30+00:00"),
                            OffsetDateTime.parse("2024-08-31T15:20:30+08:00")));

            System.out.println("project p1 id:" + p1.getProjectId());
            System.out.println("project time 1: " + p1Time.getProjectTimeId());
                       
            ManageProject manageProject1 = manageProjectRepository.save(
                    new ManageProject(
                            new ManageProjectId(p1, p1Time),
                            user1)
            );
            
            Project p2 = projectRepository.save(
                    new Project(
                        "Go to Italy in the summer",
                        "Save money to travel to Italy in December/23."));

            ProjectTime p2Time = projectTimeRepository.save(
                    new ProjectTime(
                        OffsetDateTime.parse("2023-04-17T09:20:30+00:00"),
                        OffsetDateTime.parse("2023-12-17T09:20:30+00:00")));

            ManageProject manageProject2 = manageProjectRepository.save(
                    new ManageProject(
                        new ManageProjectId(p2, p2Time),
                        user1)
            );

            Project p3 = projectRepository.save(
                new Project(
                    "Write my master thesis.",
                    "Organize information and write my master thesis."
                )
            );

            ProjectTime p3Time = projectTimeRepository.save(
                new ProjectTime(
                    OffsetDateTime.parse("2023-04-20T09:20:30+00:00"),
                    OffsetDateTime.parse("2023-12-01T09:20:30+00:00")));
            
            ManageProject manageProject3 = manageProjectRepository.save(
                new ManageProject(
                    new ManageProjectId(p3, p3Time),
                    user1)
            );            
        };
    }

}
