package application.jobtracker.controller;

import application.jobtracker.model.Job;
import application.jobtracker.repository.JobRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

@Controller
public class JobController {

    private final JobRepository jobRepository;

    private final String uploadDir = System.getProperty("user.dir") + File.separator + "uploads" + File.separator;

    public JobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @GetMapping("/")
    String getHomePage(Model model) {
        model.addAttribute("message","Hello");
        model.addAttribute("jobs",jobRepository.findAll());
        return "index";
    }

    @PostMapping("/jobs")
    public String addJob(@RequestParam("jobName") String jobName,
                         @RequestParam("companyName") String companyName,
                         @RequestParam("cvFile") MultipartFile file) throws IOException
    {
        File uploadFolder = new File(uploadDir);

        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }

        String filePath = uploadDir + companyName + "_" + jobName + ".pdf";
        file.transferTo(new File(filePath));

        Job job = new Job();
        job.setJobName(jobName);
        job.setCompanyName(companyName);
        job.setDateSubmitted(LocalDateTime.now());
        job.setCvFileName(companyName + "_" + jobName + ".pdf");

        jobRepository.save(job);

        return "redirect:/";
    }
}
