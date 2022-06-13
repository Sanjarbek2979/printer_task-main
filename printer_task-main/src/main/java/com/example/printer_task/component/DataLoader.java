package com.example.printer_task.component;

import com.example.printer_task.entity.Department;
import com.example.printer_task.entity.Permission;
import com.example.printer_task.entity.Role;
import com.example.printer_task.entity.User;
import com.example.printer_task.entity.enums.Region;
import com.example.printer_task.repository.DepartmentRepository;
import com.example.printer_task.repository.PermissionRepository;
import com.example.printer_task.repository.RoleRepository;
import com.example.printer_task.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    @Value("${spring.jpa.hibernate.ddl-auto}")
    public String auto;
    @Value("${spring.sql.init.mode}")
    public String mode;
  final DepartmentRepository departmentRepository;
  final PermissionRepository permissionRepository;
  final RoleRepository roleRepository;
  final UserRepository userRepository;
  final Generator generator;

    @Override
    public void run(String... args) throws Exception {

        if (auto.equals("create") && mode.equals("always")) {
            Department department = new Department();
            department.setIndex(23);
            department.setName("Axborot xavfsizligini ta'minlash");
            department.setRegion(Region.ТОШКЕНТ_ШАХРИ);
            department.setActive(true);
            departmentRepository.save(department);

            List<Permission> permissions = new ArrayList<>();
            Permission readAll = new Permission();
            readAll.setActive(true);
            readAll.setValue("READ_ALL");
            Permission readOne = new Permission();
            readOne.setActive(true);
            readOne.setValue("READ_ONE");
            Permission add = new Permission();
            add.setActive(true);
            add.setValue("ADD");
            Permission edit = new Permission();
            edit.setActive(true);
            edit.setValue("EDIT");
            Permission delete = new Permission();
            delete.setActive(true);
            delete.setValue("DELETE");
            permissions.add(readAll);
            permissions.add(readOne);
            permissions.add(add);
            permissions.add(edit);
            permissions.add(delete);
            permissionRepository.saveAll(permissions);

            Role admin = new Role();
            admin.setPermissions(Arrays.asList(add,edit,delete,readAll,readOne));
            admin.setActive(true);
            admin.setName("ADMIN");
            Role user = new Role();
            user.setPermissions(Arrays.asList(add,readAll,readOne));
            user.setActive(true);
            user.setName("USER");

            roleRepository.save(admin);
            roleRepository.save(user);

            User USER_ADMIN = new User();
            USER_ADMIN.setDepartment(departmentRepository.getById(1L));
            USER_ADMIN.setUsername("admin");
            USER_ADMIN.setPassword("admin");
            USER_ADMIN.setFullName("Allayev Sanjar");
            USER_ADMIN.setRole(roleRepository.getById(1L));
            userRepository.save(USER_ADMIN);
        }
    }
}
