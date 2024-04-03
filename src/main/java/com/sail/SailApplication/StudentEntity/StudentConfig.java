package com.sail.SailApplication.StudentEntity;

import com.sail.SailApplication.AdvisorEntity.Advisor;
import com.sail.SailApplication.AdvisorEntity.AdvisorRepository;
import com.sail.SailApplication.StudentRepository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static java.time.Month.APRIL;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository , AdvisorRepository advisorRepository){
        return args -> {
            Student hasan = new Student(
                    "Hasan",
                    "Kilic",
                    "hasanmail",
                    "hasanpassword"
            );
            Student aykut = new Student(
                    "Aykut Emre",
                    "Yuksel",
                    "aykutmail",
                    "aykutpassword"
            );
            Student burak = new Student(
                    "Burak",
                    "Seker",
                    "burakmail",
                    "burakpassword"
            );
            Student eylul = new Student(
                    "Eylul",
                    "Berberoglu",
                    "eylulmail",
                    "eylulpassword"
            );
            Student selin = new Student(
                    "Selin",
                    "Eryasar",
                    "selinmail",
                    "selinpassword"
            );

            Advisor adv1 = new Advisor(
                    "adv1_name",
                    "adv1_surname",
                    "adv1_mail",
                    "adv1_password"
            );
            Advisor adv2 = new Advisor(
                    "adv2_name",
                    "adv2_surname",
                    "adv2_mail",
                    "adv2_password"
            );Advisor adv3 = new Advisor(
                    "adv3_name",
                    "adv3_surname",
                    "adv3_mail",
                    "adv3_password"
            );Advisor adv4 = new Advisor(
                    "adv4_name",
                    "adv4_surname",
                    "adv4_mail",
                    "adv4_password"
            );Advisor adv5 = new Advisor(
                    "adv5_name",
                    "adv5_surname",
                    "adv5_mail",
                    "adv5_password"
            );
            advisorRepository.saveAll(List.of( adv1 ,adv2 ,adv3 , adv4 , adv5));
            studentRepository.saveAll(List.of( hasan , aykut , burak , eylul , selin));
        };
    }
}
