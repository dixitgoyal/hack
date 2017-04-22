package osdchack.com.locmon.Models;

/**
 * Created by DravitLochan on 22-04-2017.
 */

public class Guard {
    private String id, f_name, l_name, contact, email, password, user_name;

    public Guard(String id, String f_name, String l_name, String contact, String email, String password, String user_name)
    {
        this.id = id;
        this.f_name = f_name;
        this.l_name = l_name;
        this.contact = contact;
        this.email = email;
        this.password = password;
        this.user_name = user_name;
    }

    public Guard(String id, String user_name, String password) {
        this.id = id;
        this.user_name = user_name;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Guard(String user_name, String password)
    {
        this.password = password;
        this.user_name = user_name;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
