package com.jsp.warehouse_manager.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jsp.warehouse_manager.repository.AdminRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    AdminRepository adminRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
      return adminRepo.findByEmail(username)
                    //.map( admin -> new UserDetailsImpl(admin))
                    .map(UserDetailsImpl::new)
                    .orElseThrow(() -> new UsernameNotFoundException("User Not Exist"));
    }

}
