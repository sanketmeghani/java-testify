package dev.sanket.xtream;

import java.util.ArrayList;
import java.util.List;

public class Employee
{
    private String name;

    private List<Employee> subOrdinates = new ArrayList<Employee>();

    public String getName()
    {
        return name;
    }

    public Employee setName(String name)
    {
        this.name = name;
        
        return this;
    }

    public List<Employee> getSubOrdinates()
    {
        return subOrdinates;
    }

    public Employee setSubOrdinates(List<Employee> subOrdinates)
    {
        this.subOrdinates = subOrdinates;
        
        return this;
    }

    public static Employee create()
    {
        return new Employee();
    }

    public Employee addSubOrdiniate(Employee subOrdinate)
    {
        this.subOrdinates.add(subOrdinate);
        
        return this;
    }
}
