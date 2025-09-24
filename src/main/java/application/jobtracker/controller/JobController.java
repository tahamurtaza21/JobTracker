package application.jobtracker.controller;

import application.jobtracker.model.Job;
import application.jobtracker.repository.JobRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class JobController {

    private final JobRepository jobRepository;

    private final String uploadDir = System.getProperty("user.dir") + File.separator + "uploads" + File.separator;

    public JobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @GetMapping("/")
    String getHomePage(Model model) {
        List<Job> jobs = jobRepository.findAll();

        int jobsByWeek = getJobsByWeek(jobs);

        int jobsByMonth = getJobsByMonth(jobs);

        model.addAttribute("jobs", jobs);
        model.addAttribute("jobsByWeek", jobsByWeek);
        model.addAttribute("jobsByMonth", jobsByMonth);

        return "index";
    }

    private static int getJobsByMonth(List<Job> jobs) {
        int currentMonth = LocalDate.now().getMonthValue();
        long jobsThisMonth = jobs.stream()
                .filter(job -> job.getDateSubmitted()
                        .toLocalDate()
                        .getMonthValue() == currentMonth)
                .count();
        return (int) jobsThisMonth;
    }

    private static int getJobsByWeek(List<Job> jobs) {
        int currentWeek = LocalDate.now().get(WeekFields.ISO.weekOfYear());
        long jobsThisWeek = jobs.stream()
                .filter(job -> job.getDateSubmitted()
                        .toLocalDate()
                        .get(WeekFields.ISO.weekOfYear()) == currentWeek)
                .count();
        return (int) jobsThisWeek;
    }


    @PostMapping("/jobs")
    public String addJob(@RequestParam("jobName") String jobName,
                         @RequestParam("companyName") String companyName,
                         @RequestParam("companyName") String countryName,
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
        job.setStatus("Pending");
        job.setCountryName(countryName);
        job.setDateSubmitted(LocalDateTime.now());
        job.setCvFileName(companyName + "_" + jobName + ".pdf");

        jobRepository.save(job);

        return "redirect:/";
    }

    @PostMapping("/jobs/{id}/status")
    public String updateJobStatus(@PathVariable Long id, @RequestParam String status) {
        Job job = jobRepository.findById(id).orElse(null);
        if (job != null) {
            job.setStatus(status);
            jobRepository.save(job);
        }
        return "redirect:/";
    }

    @DeleteMapping("/jobs/{id}")
    public String deleteJob(@PathVariable Long id) {
        jobRepository.findById(id).ifPresent(jobRepository::delete);
        return "redirect:/";
    }

}

