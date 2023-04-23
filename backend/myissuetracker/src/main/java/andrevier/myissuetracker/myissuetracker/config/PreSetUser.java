package andrevier.myissuetracker.myissuetracker.config;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import andrevier.myissuetracker.myissuetracker.dao.IssueRepository;
import andrevier.myissuetracker.myissuetracker.dao.IssueTimeRepository;
import andrevier.myissuetracker.myissuetracker.dao.ManageIssueRepository;
import andrevier.myissuetracker.myissuetracker.dao.ManageProjectRepository;
import andrevier.myissuetracker.myissuetracker.dao.PriorityLabelRepository;
import andrevier.myissuetracker.myissuetracker.dao.ProjectRepository;
import andrevier.myissuetracker.myissuetracker.dao.ProjectTimeRepository;
import andrevier.myissuetracker.myissuetracker.dao.UserRepository;
import andrevier.myissuetracker.myissuetracker.model.Issue;
import andrevier.myissuetracker.myissuetracker.model.IssueTime;
import andrevier.myissuetracker.myissuetracker.model.ManageIssue;
import andrevier.myissuetracker.myissuetracker.model.ManageProject;
import andrevier.myissuetracker.myissuetracker.model.ManageProjectId;
import andrevier.myissuetracker.myissuetracker.model.PriorityLabel;
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
            ManageProjectRepository manageProjectRepository,
            IssueRepository issueRepository,
            ManageIssueRepository manageIssueRepository,
            IssueTimeRepository issueTimeRepository,
            PriorityLabelRepository priorityLabelRepository) {
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

            // Projects of the user 1.
            Project user1p1 = projectRepository.save(
                    new Project(
                            "Buy a car",
                            "Save money to buy a car in one year."));

            ProjectTime user1p1Time = projectTimeRepository.save(
                    new ProjectTime(
                            LocalDateTime.parse("2023-04-17T09:20:30"),
                            LocalDateTime.parse("2024-08-31T15:20:30")));
                       
            manageProjectRepository.save(
                    new ManageProject( user1p1, user1p1Time, user1)
            );
            // Issues from the project p1.        
            IssueTime timeForIssue1 = issueTimeRepository.save(
                new IssueTime(
                        LocalDateTime.now(),
                        LocalDateTime.parse("2024-05-17T00:00:00")));

            PriorityLabel priorityForIssue1 = priorityLabelRepository.save(
                new PriorityLabel("normal"));

            Issue issue1 = issueRepository.save(
                new Issue(
                        "Choose the car.",
                        "Choose the type of car considering the price.",
                        priorityForIssue1,
                        user1p1));
            
            manageIssueRepository.save(
                new ManageIssue(issue1, timeForIssue1, user1));
            
            IssueTime timeForIssue2 = issueTimeRepository.save(
                new IssueTime(
                        LocalDateTime.now(),
                        LocalDateTime.parse("2024-05-17T00:00:00")));

            PriorityLabel priorityForIssue2 = priorityLabelRepository.save(
                new PriorityLabel("normal"));
            
            Issue issue2 = issueRepository.save(
                new Issue(
                        "How can I save money ?.",
                        "Plan to save money according to my budget.",
                        priorityForIssue2,
                        user1p1));
            
            manageIssueRepository.save(
                new ManageIssue(issue2, timeForIssue2, user1));

            IssueTime timeForIssue3 = issueTimeRepository.save(
                new IssueTime(
                        LocalDateTime.now(),
                        LocalDateTime.parse("2024-05-17T00:00:00")));

            PriorityLabel priorityForIssue3 = priorityLabelRepository.save(
                new PriorityLabel("normal"));
        
            Issue issue3 = issueRepository.save(
                new Issue(
                        "How can I save money ?.",
                        "Plan to save money according to my budget.",
                        priorityForIssue3,
                        user1p1));
        
            manageIssueRepository.save(
                new ManageIssue(issue3, timeForIssue3, user1));
            //
            Project user1p2 = projectRepository.save(
                    new Project(
                        "Go to Italy in the summer",
                        "Save money to travel to Italy in December/23."));

            ProjectTime user1p2Time = projectTimeRepository.save(
                    new ProjectTime(
                        LocalDateTime.parse("2023-04-17T09:20:30"),
                        LocalDateTime.parse("2023-12-17T09:20:30")));

            manageProjectRepository.save(
                    new ManageProject(user1p2, user1p2Time, user1)
            );
            //
            Project user1p3 = projectRepository.save(
                new Project(
                    "Write my master thesis.",
                    "Organize information and write my master thesis."
                )
            );

            ProjectTime user1p3Time = projectTimeRepository.save(
                new ProjectTime(
                    LocalDateTime.parse("2023-04-20T09:20:30"),
                    LocalDateTime.parse("2023-12-01T09:20:30")));
            
            manageProjectRepository.save(
                new ManageProject(user1p3, user1p3Time, user1)
            );

            // Projects of user 2.
            Project user2p1 = projectRepository.save(
                    new Project(
                            "Go to Egypt",
                            "Save money to travel to Egypt soon."));

            ProjectTime user2p1Time = projectTimeRepository.save(
                    new ProjectTime(
                            LocalDateTime.parse("2023-04-17T09:20:30"),
                            LocalDateTime.parse("2024-08-31T15:20:30")));
                       
            manageProjectRepository.save(
                    new ManageProject( user2p1, user2p1Time, user2)
            );
            //
            Project user2p2 = projectRepository.save(
                    new Project(
                        "Buy a motorcycle",
                        "Save money to by a bike."));

            ProjectTime user2p2Time = projectTimeRepository.save(
                    new ProjectTime(
                        LocalDateTime.parse("2023-04-17T09:20:30"),
                        LocalDateTime.parse("2023-12-17T09:20:30")));

            manageProjectRepository.save(
                    new ManageProject(user2p2, user2p2Time, user2)
            );
            //
            Project user2p3 = projectRepository.save(
                new Project(
                    "Write a book about plants.",
                    "Organize information and write a book."
                )
            );

            ProjectTime user2p3Time = projectTimeRepository.save(
                new ProjectTime(
                    LocalDateTime.parse("2023-04-20T09:20:30"),
                    LocalDateTime.parse("2023-12-01T09:20:30")));
            
            manageProjectRepository.save(
                new ManageProject(user2p3, user2p3Time, user2)
            );

            // Projects from user 3.
            Project user3p1 = projectRepository.save(
                    new Project(
                            "Go to Mexico",
                            "Save money to travel to Egypt soon."));

            ProjectTime user3p1Time = projectTimeRepository.save(
                    new ProjectTime(
                            LocalDateTime.parse("2023-04-17T09:20:30"),
                            LocalDateTime.parse("2024-08-31T15:20:30")));
                       
            manageProjectRepository.save(
                    new ManageProject( user3p1, user3p1Time, user3)
            );
            //
            Project user3p2 = projectRepository.save(
                    new Project(
                        "Buy a house",
                        "Save money to by a bike."));

            ProjectTime user3p2Time = projectTimeRepository.save(
                    new ProjectTime(
                        LocalDateTime.parse("2023-04-17T09:20:30"),
                        LocalDateTime.parse("2023-12-17T09:20:30")));

            manageProjectRepository.save(
                    new ManageProject(user3p2, user3p2Time, user3)
            );
            //
            Project user3p3 = projectRepository.save(
                new Project(
                    "Write a book about monkeys.",
                    "Organize information and write a book."
                )
            );
            //
            ProjectTime user3p3Time = projectTimeRepository.save(
                new ProjectTime(
                    LocalDateTime.parse("2023-04-20T09:20:30"),
                    LocalDateTime.parse("2023-12-01T09:20:30")));
            
            manageProjectRepository.save(
                new ManageProject(user3p3, user3p3Time, user3)
            );
        };
    }
}
