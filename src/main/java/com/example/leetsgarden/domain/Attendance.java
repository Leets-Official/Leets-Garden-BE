package com.example.leetsgarden.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private boolean isAttended;

    @ManyToOne
    private User user;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Template template;

    public Attendance(User user, Template meeting, boolean isAttended) {
        this.user = user;
        this.template = meeting;
        this.isAttended = isAttended;
    }

    public Attendance(User user, Template meeting) {
        this.user = user;
        this.template = meeting;
    }

    // 구조 확인을 위해 임시로 만든 페이지, 추후 한성민 코드로 수정 예정
    public void checkAttendance() {

        if (!isAttended) {  // 이미 출석한 경우에는 다시 출석 체크하지 않도록 확인
            this.isAttended = true;
           // 다시 수정될 내용이기에 일단 보류
        } else {
            throw new IllegalStateException("이미 출석한 사용자입니다.");
        }
    }
}