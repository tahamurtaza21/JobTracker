package application.jobtracker.model;


import jakarta.persistence.*;
import lombok.*;

import java.io.File;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jobName;
    private String companyName;
    private String status;
    private LocalDateTime dateSubmitted;
    private String cvFileName;

}
