package com.example.leetsgarden.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long meetingId;

    @Column
    private String meetingDay;

    @Column
    private LocalDateTime thisWeekMeetingDate;

    @Column
    private LocalDateTime nextWeekMeetingDate;

    @ManyToOne
    @JoinColumn(name = "template")
    private Template template;

    public void setMeetingDay(String meetingDay) {

        LocalDateTime now = LocalDateTime.now();
        DayOfWeek dayOfWeek = DayOfWeek.valueOf(meetingDay.toUpperCase());
        LocalDateTime thisWeek = now.with(TemporalAdjusters.previousOrSame(dayOfWeek));
        LocalDateTime nextWeek = now.with(TemporalAdjusters.next(dayOfWeek));

        this.thisWeekMeetingDate = thisWeek;
        this.nextWeekMeetingDate = nextWeek;

    }
}
