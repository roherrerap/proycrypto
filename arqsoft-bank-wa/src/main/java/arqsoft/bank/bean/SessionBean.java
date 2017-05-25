package arqsoft.bank.bean;

import arqsoft.bank.model.User;
import arqsoft.bank.model.Session;
import arqsoft.bank.service.UserService;
import arqsoft.bank.service.SessionService;
import arqsoft.bank.service.LdapService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by javergarav on 29/03/2017.
 */
@ManagedBean(name="sessionBean")
@RequestScoped
public class SessionBean implements Serializable {

    private long userId;
    @NotNull
    @Size(min=8, max=20, message = "Debe tener entre 8 y 20 caracteres")
    private String email;
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Solo se admiten letras mayúsculas, minusculas y sin espacios")
    @Size(min = 8)
    private String password;
    private String name;
    private String token;

    private String beanMessage;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBeanMessage() {
        return beanMessage;
    }

    public void setBeanMessage(String beanMessage) {
        this.beanMessage = beanMessage;
    }

    public void createSession() {
        LdapService login = new LdapService();
        if (login.login(this.getEmail(), this.getPassword())){
          UserService userService = new UserService();
          try {

              User user = userService.getUserByEmail(this.getEmail());
              this.setUserId(user.getId());

              if (userService.getUserByEmail(email).getPassword().equals(this.getPassword())) {
                  Session session = new Session(userId, email);
                  SessionService sessionService = new SessionService();
                  try {

                      this.setBeanMessage(sessionService.createSession(session));

                      try {
                          this.setToken(sessionService.getSessionByUserId(userId).getToken());
                          this.setName(userService.getUserById(userId).getName());
                      } catch (Exception e) {
                          e.printStackTrace();
                      }

                  } catch (Exception e) {
                      e.printStackTrace();
                  }
              }

          } catch (Exception e) {
              e.printStackTrace();
          }
        } else {
          this.setBeanMessage("FAILED to connect LDAP Server.");
        }
    }
}
