package com.example.jwttest.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement
    private Long iuser;
    private String username; //id
    private String password;
    private String roles; //권한 user와 admin사용 ( 필요하면 manager 까지?)

//    public List<String> getRoleList(){
//        if(this.roles.length()>0){
//            return Arrays.asList(this.roles.split(","));
//        }
//        return new ArrayList<>();
//    }
}
