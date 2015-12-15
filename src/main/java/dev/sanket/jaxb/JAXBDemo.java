package dev.sanket.jaxb;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class JAXBDemo
{
    public PaymentGatewayConfig unmarsh() throws JAXBException
    {
        File xml = new File("/home/sanket/eclipsews/HelloWorld/src/main/resources/jaxbdemo.xml");
        
        JAXBContext jaxbContext = JAXBContext.newInstance(PaymentGatewayConfig.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        
        PaymentGatewayConfig pgConfig = (PaymentGatewayConfig) jaxbUnmarshaller.unmarshal(xml);
        
        return pgConfig;
    }
}
