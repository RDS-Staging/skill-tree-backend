package com.RDS.skilltree.User;

import com.RDS.skilltree.Exceptions.NoEntityException;
import lombok.*;
import org.hibernate.usertype.UserType;
import org.springframework.util.ObjectUtils;

import java.net.URL;
import java.time.Instant;


@Getter
@Builder
public class UserDRO {
    private String rdsUserId;

    private String firstName;

    private String lastName;

    private URL imageUrl;

    private UserRole role;

    public static UserModel toModel(UserDRO user) {
        return UserModel.builder()
                .rdsUserId(user.getRdsUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .imageUrl(user.getImageUrl())
                .role(user.getRole())
                .build();
    }

    public static UserModel compareAndUpdateModel(UserModel user, UserDRO userDRO) {
        if (ObjectUtils.isEmpty(user)){
            throw new NoEntityException("No User found with the given Id");
        }
        if (userDRO.getRdsUserId() != null) {
            user.setRdsUserId(user.getRdsUserId());
        }
        if (userDRO.getFirstName() != null) {
            user.setFirstName(user.getFirstName());
        }
        if (userDRO.getLastName() != null) {
            user.setLastName(user.getLastName());
        }
        if (userDRO.getImageUrl() != null) {
            user.setImageUrl(user.getImageUrl());
        }
        if (userDRO.getRole() != null) {
            user.setRole(user.getRole());
        }
        user.setUpdatedAt(Instant.now());
        user.setUpdatedBy(user);
        return user;
    }
}