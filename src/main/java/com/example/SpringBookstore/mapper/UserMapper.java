package com.example.SpringBookstore.mapper;

import com.example.SpringBookstore.entity.User;
import com.example.SpringBookstore.entityDTO.UserDTO;

public class UserMapper {
    public static User userDTO2User(UserDTO userDTO) {
        User user = new User();

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setGender(userDTO.getGender());
        user.setCountry(userDTO.getCountry());
        user.setBirthDate(userDTO.getBirthDate());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setVerifiedAccount(userDTO.getVerifiedAccount());

        return user;
    }

    public static UserDTO user2UserDTO(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setID(user.getID());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setGender(user.getGender());
        userDTO.setCountry(user.getCountry());
        userDTO.setBirthDate(user.getBirthDate());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setVerifiedAccount(user.getVerifiedAccount());

        return userDTO;
    }
}
