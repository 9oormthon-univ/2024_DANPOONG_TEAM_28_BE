package com.be.mypage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class MypageResponseDto {
    private int userId;

    private String nickanme;
    private String email;
    private String profileImage;
}
