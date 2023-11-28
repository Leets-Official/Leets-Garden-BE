package com.example.leetsgarden.domain;

import com.example.leetsgarden.dto.request.AddMeetingRequest;
import com.example.leetsgarden.dto.request.UpdateMeetingRequest;
import com.example.leetsgarden.repository.UserRepository;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String place;

    @Column(nullable = false)
    private String meetingDay;

    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL)
    private List<UserMeeting> userMeetings;

    public static Meeting from(AddMeetingRequest request, List<User> userList) {
        Meeting meeting = Meeting.builder()
                .name(request.getMeetingName())
                .place(request.getMeetingPlace())
                .meetingDay(request.getMeetingDay())
                .build();

        meeting.userMeetings = userList.stream()
                .map(user -> new UserMeeting(user, meeting))
                .toList();

        return meeting;
    }

    public void update(UpdateMeetingRequest request, UserRepository userRepository){
        this.name = request.getMeetingName();
        this.place = request.getMeetingPlace();
        this.meetingDay = request.getMeetingDay();

        List<String> usernameList = request.getUserList();
        List<UserMeeting> updatedUserMeetings = new ArrayList<>();

        for (String username : usernameList) {
            User user = userRepository.findByName(username).orElseThrow(() -> new IllegalArgumentException("해당 유저는 존재하지 않습니다. : " + username));
            updatedUserMeetings.add(new UserMeeting(user, this));
        }
        this.userMeetings = updatedUserMeetings;
    }
}