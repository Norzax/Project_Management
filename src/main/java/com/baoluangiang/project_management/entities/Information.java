package com.baoluangiang.project_management.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "information")
@Entity(name = "information")
public class Information {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "Bio")
    private String bio;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "avatar_Url")
    private String avatarUrl;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
