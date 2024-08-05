package com.rsupport.notice.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Attach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @Column(nullable = false)
    private Long noticeNo;

    @Column(nullable = false)
    private String oriName;

    @Column(nullable = false)
    private String saveName;

    @Column
    private String savePath;

    @Column(nullable = false)
    private String fileExt;
}
