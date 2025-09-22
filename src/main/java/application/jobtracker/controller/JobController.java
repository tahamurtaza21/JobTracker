package application.jobtracker.controller;

import application.jobtracker.repository.JobRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JobController {

    private final JobRepository jobRepository;

    public JobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @GetMapping("/")
    String getHomePage(Model model) {
        model.addAttribute("message","Hello");
        model.addAttribute("jobs",jobRepository.findAll());
        return "index";
    }

}
