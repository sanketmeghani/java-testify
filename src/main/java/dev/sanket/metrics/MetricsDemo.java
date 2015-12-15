package dev.sanket.metrics;

public class MetricsDemo
{
    @Monitor()
    public void processRequest(int sleepSeconds)
    {
        try
        {
            Thread.sleep(sleepSeconds * 1000);
        }
        catch (Exception e)
        {

        }
    }

}
