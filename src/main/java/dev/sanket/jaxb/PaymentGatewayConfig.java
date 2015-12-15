package dev.sanket.jaxb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PaymentGatewayConfig
{
    private PaymentGateway paymentGateway;

    public final PaymentGateway getPaymentGateway()
    {
        return paymentGateway;
    }

    @XmlElement
    public final void setPaymentGateway(PaymentGateway paymentGateway)
    {
        this.paymentGateway = paymentGateway;
    }

    
}
