package com.group.hassocial.security;

import com.group.hassocial.data.dto.UserDto;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class PasswordEncoder {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() { // this security is not used at the moment.
        return new BCryptPasswordEncoder();
    }

    public String encodePassword(UserDto userDto) {
        return DigestUtils.sha256Hex(userDto.getPassword());
    }

}
