package org.webshop.usermanagement.model;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.webshop.to.CreateUserTO;

@Component
public class UserConverter implements Converter<CreateUserTO, AppUser> {

    @Override
    public AppUser convert(final CreateUserTO source) {
        final AppUser appUser = new AppUser();
        appUser.setFirstName(source.getFirstName());
        appUser.setLastName(source.getLastName());
        appUser.setEmail(source.getEmail());

//        final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        appUser.setPassword(bCryptPasswordEncoder.encode(source.getPassword()));

        appUser.setPassword(source.getPassword());
        return appUser;
    }
}
