package com.be.user.dto.request;

import com.be.user.entity.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

public class UserRequestDTO {
    @Getter
    @Setter
    public static class KakaoLoginDTO {
        @NotEmpty
        private Long kakaoId;

        @NotEmpty
        @Pattern(regexp = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", message = "이메일을 올바르게 입력해주세요.")
        private String email;

        @NotEmpty
        private String nickname;

        @NotEmpty
        private String profileImage;

        public User toEntity() {
            return User.builder()
                    .kakaoId(kakaoId)
                    .email(email)
                    .nickname(nickname)
                    .profileImage(profileImage)
                    .build();
        }
    }
}
