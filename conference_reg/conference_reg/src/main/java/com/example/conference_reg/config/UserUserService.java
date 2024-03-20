package com.example.conference_reg.config;
import com.example.conference_reg.entity.Owner;
import com.example.conference_reg.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
public class UserUserService implements UserDetailsService {
    @Autowired
    private OwnerRepository ownerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Owner> owner = ownerRepository.findByName(username);
        System.out.println(owner.get().getPassword());
        return  owner.map(com.example.conference_reg.config.UserDetails::new)
                .orElseThrow(()-> new UsernameNotFoundException("user not found"+username));
    }

















//@Override
//public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//    Optional<Owner> ownerOptional = ownerRepository.findByName(username);
//    if (ownerOptional.isPresent()) {
//        Owner owner = ownerOptional.get();
//        OwnerModel ownerModel =ownerService. convertToModel(owner);
//        System.out.println(owner.getPassword());
//        return new UserDetails(ownerModel);
//    } else {
//        throw new UsernameNotFoundException("User not found: " + username);
//    }
// }
}
