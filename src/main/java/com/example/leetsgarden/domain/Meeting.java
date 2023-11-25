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
        // 현재 날짜와 시간
        LocalDateTime now = LocalDateTime.now();

        // 입력된 요일을 DayOfWeek 열거형으로 변환
        DayOfWeek dayOfWeek = DayOfWeek.valueOf(meetingDay.toUpperCase());

        // 최근 날짜 계산
        LocalDateTime thisWeek = now.with(TemporalAdjusters.previousOrSame(dayOfWeek));

        // 다음 주 날짜 계산
        LocalDateTime nextWeek = now.with(TemporalAdjusters.next(dayOfWeek));

        // 결과를 필드에 할당
        this.thisWeekMeetingDate = thisWeek;
        this.nextWeekMeetingDate = nextWeek;


    }
}
