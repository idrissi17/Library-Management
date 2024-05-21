package org.pj.library_management.service;

import org.pj.library_management.dao.entities.Admin;
import org.pj.library_management.dao.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service
public class AdminService implements AdminManager {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin addAdmin(Admin admin) {
        try {
        return adminRepository.save(admin);
        }
        catch (Exception e){
            System.out.println(Arrays.toString(e.getStackTrace()));
            return null;
        }

    }

    @Override
    public Admin updateAdmin(Admin admin) {
        try {
            return adminRepository.save(admin);
        }
        catch (Exception e){
            System.out.println(Arrays.toString(e.getStackTrace()));
            return null;
        }
    }

    @Override
    public Boolean deleteAdmin(Admin admin) {
        try{
           adminRepository.delete(admin);
           return true;
        }catch (Exception e){
            System.out.println(Arrays.toString(e.getStackTrace()));
            return false;
        }
    }

    @Override
    public Admin getAdminById(int id) {
        return adminRepository.findById(id).orElse(null);
    }

    @Override
    public Admin getAdminByUserNameOrPassword(String userName,String password) {
        try
        {
            return adminRepository.findByUsernameOrPassword(userName, password);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public Admin loginAdmin(String username, String password) {
        Admin admin =adminRepository.findByUsername(username).orElse(null);
        if (admin != null && admin.getPassword().equals(password)){
            return admin;
        }
        return null;
    }
}
