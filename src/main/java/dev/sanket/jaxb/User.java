package dev.sanket.jaxb;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User implements Serializable
{
    private static final long serialVersionUID = 1L;

    private int id;

    private String name;

    public User(int id, String name)
    {
        this.id = id;
        this.name = name;
    }
    
    @XmlAttribute
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    @XmlElement
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public User()
    {

    }
}
