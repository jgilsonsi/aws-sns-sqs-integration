package com.jjdev.manager.dto;

import com.jjdev.manager.entity.JUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class JUserDto {

    @ApiModelProperty(hidden = true)
    private Long id;

    @NotEmpty(message = "Name cannot be empty.")
    @Length(max = 255, message = "Name must be contain a maximum of {max} characters.")
    private String name;

    @NotEmpty(message = "Email cannot be empty.")
    @Length(min = 5, max = 255, message = "User must be contain between {min} and {max} characters.")
    @Email(message = "Invalid email.")
    private String email;

    private int age;

    public JUser toUser() {
        JUser user = new JUser();
        user.setId(this.id);
        user.setName(this.name);
        user.setEmail(this.email);
        user.setAge(this.age);

        return user;
    }

}