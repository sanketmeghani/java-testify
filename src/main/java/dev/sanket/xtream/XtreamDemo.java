package dev.sanket.xtream;

import com.thoughtworks.xstream.XStream;

public class XtreamDemo
{
    private Employee employee;
    
    public void createObject()
    {
        employee = Employee.create().setName("Sanket");
        
        employee.addSubOrdiniate(Employee.create().setName("Ravi"));
    }

    public String getXML()
    {
        XStream xStream = new XStream();
        
        return xStream.toXML(employee);
    }
}
