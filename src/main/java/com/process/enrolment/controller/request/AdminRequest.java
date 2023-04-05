package com.process.enrolment.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class AdminRequest {

    @Getter
    @NoArgsConstructor
    public static class CreateRole{

        @NotBlank(message = "권한명은 필수 입력 값입니다.")
        @Pattern(regexp = "^[a-zA-Z]*$", message = "권한명은 영문만 가능합니다.")
        private String name;
        private String desc="";

        public CreateRole(String name, String desc) {
            this.name = name;
            this.desc = desc;
        }
    }

    @Getter
    public static class RoleApprove{

        @NotBlank(message = "사용자 선택은 필수 값 입니다.")
        private Long userRoleId;

        public RoleApprove(Long userRoleId) {
            this.userRoleId = userRoleId;
        }
    }
}
