package com.be.user.dto.response;

import com.be.user.entity.User;
import lombok.Getter;
import lombok.Setter;

public class UserResponseDTO {
    @Getter
    @Setter
    public static class TokenDTO {
        private String accessToken;
        private String refreshToken;

        public TokenDTO(String accessToken, String refreshToken) {
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
        }
    }

    @Getter
    @Setter
    public static class KakaoLoginDTO {
        private TokenDTO tokenInfo;
        private UserDTO userInfo;

        public KakaoLoginDTO(TokenDTO tokenInfo, User user) {
            this.tokenInfo = tokenInfo;
            this.userInfo = new UserDTO(user);
        }

        @Getter
        @Setter
        public static class UserDTO {
            private int userId;
            private String email;
            private String nickname;
            private String profileImage;

            public UserDTO(User user) {
                this.userId = user.getId();
                this.email = user.getEmail();
                this.nickname = user.getNickanme();
                this.profileImage = user.getProfileImage();
            }
        }
    }
}
