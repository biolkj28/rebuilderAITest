package com.process.enrolment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TokenDTO {
    private String accessToken;

    public TokenDTO(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
 * TODO: 2023-04-05 추후 시간이 된다면 리프레쉬 토큰
 */
}
