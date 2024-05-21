package org.pj.library_management.service;

import org.pj.library_management.dao.entities.Admin;
import org.pj.library_management.dao.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;

public interface AdminManager {
   public  Admin addAdmin(Admin admin);
   public  Admin updateAdmin(Admin admin);
   public Boolean deleteAdmin(Admin admin);
   public  Admin getAdminById(int id);
   public Admin getAdminByUserNameOrPassword(String userName,String password);
   public Admin loginAdmin(String username,String password);

}
