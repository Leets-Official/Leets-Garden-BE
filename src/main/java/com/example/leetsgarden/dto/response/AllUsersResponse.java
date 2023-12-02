package com.example.leetsgarden.dto.response;

import com.example.leetsgarden.domain.User;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllUsersResponse {
    private Long id;
    private String name;

    public static AllUsersResponse fromEntity(User user) {
        return new AllUsersResponse(user.getId(), user.getName());
    }
}
