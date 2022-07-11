package com.down.downloader.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "links")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String linkName;
    private float totalElapseTime;
    private float totalDownloadedKilobytes;

    @ManyToOne
    @JoinColumn(name = "website_id")
    private Website website;
}
