package com.example.racepulse.data;

import com.example.racepulse.models.UserEntity;
import com.example.racepulse.repository.UserRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Component
public class ArticlesInitializer {

    private final NewsArticleRepository newsArticleRepository;
    private final UserRepository userRepository;

    public ArticlesInitializer(NewsArticleRepository newsArticleRepository, UserRepository userRepository) {
        this.newsArticleRepository = newsArticleRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void initData() {
        if (newsArticleRepository.findAll().isEmpty() && !userRepository.findAll().isEmpty()){
            UserEntity user = this.userRepository.findByUsername("bubsi");
            LocalDateTime date = LocalDateTime.now();
            NewsArticle article1 = new NewsArticle((long) 999,
                    "McLaren CEO Zak Brown believes the team won’t feel the full effects of their new hires and investment into their wind tunnel until 2025.",
                    "Brown insists it will take McLaren until 2025 to 'maximise everything' after new hires and wind tunnel investment\n",
                    "https://static.independent.co.uk/2022/12/13/14/7c351b7004f89547ac0219e5fdeb3c2fY29udGVudHNlYXJjaGFwaSwxNjcxMDI1Mjc0-2.66513759.jpg",
                    date,
                    date,
                    user);
            NewsArticle article2 = new NewsArticle((long) 999,
                    "END OF YEAR REPORT: Another year goes by without a championship for Ferrari but are things looking up?",
                    "Expectations were high at Ferrari coming into the season, with the target of winning both championships the stated aim. However, those hopes ended quickly as, for the first half of the year they struggled to even get on the podium.",
                    "https://f1i.com/wp-content/uploads/2021/12/Fhx3IzmXoAQwDWD.jpg",
                    date,
                    date,
                    user);

            this.newsArticleRepository.save(article1);
            this.newsArticleRepository.save(article2);
        }
    }
}
