package com.jjdev.consumer.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class JUserDto {

    private Long id;
    private String name;
    private String email;
    private int age;

}