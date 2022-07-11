package com.down.downloader.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "websites")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Website {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String websiteName;
    private LocalDateTime downloadStartDateTime;
    private LocalDateTime downloadEndDateTime;
    private float totalElapseTime;
    private float totalDownloadedKilobytes;
}
