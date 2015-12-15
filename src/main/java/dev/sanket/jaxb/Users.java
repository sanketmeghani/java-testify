package dev.sanket.jaxb;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Users")
public class Users
{
    private List<User> users = new ArrayList<User>();

    @XmlElements(value = {@XmlElement(name = "User", type = User.class)})
    @XmlElementWrapper
    public List<User> getUsers()
    {
        return users;
    }

    public void addUser(User user)
    {
        users.add(user);
    }
}
