package arqsoft.bank.bean;

import arqsoft.bank.service.UserService;
import arqsoft.bank.model.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;


import java.lang.Exception;

/**
 * Created by javergarav on 01/03/2017.
 */
@ManagedBean(name="userBean")
@RequestScoped
public class UserBean implements Serializable {

    private Long id;

    private String name;

    private String email;
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Solo se admiten letras mayúsculas, minusculas")
    @Size(min = 8)
    private String password;

    private String beanMessage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBeanMessage() {
        return beanMessage;
    }

    public void setBeanMessage(String message) {
        this.beanMessage = message;
    }

    public User[] getAllUsers() {
        UserService userService = new UserService();
        try {
            return userService.getAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void getUserById() {
        UserService userService = new UserService();
        try {
            this.setName(userService.getUserById(id).getName());
            this.setEmail(userService.getUserById(id).getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createUser() {
        User user = new User(name, email, password);
        UserService userService = new UserService();
        try {
            this.setBeanMessage(userService.createUser(user));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateUser() {
        User user = new User(id, name, email, password);
        UserService userService = new UserService();
        try {
            this.setBeanMessage(userService.updateUser(id, user));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteUser() {
        UserService userService = new UserService();
        try {
            this.setBeanMessage(userService.deleteUser(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
