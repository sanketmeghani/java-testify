package dev.sanket.metrics;

public enum MetricsType
{
    LATENCY;

    public boolean needsToCollect(MetricsType[] toBeCollected)
    {
        for (MetricsType metric : toBeCollected)
        {
            if (metric.equals(this))
            {
                return true;
            }
        }

        return false;
    }
}
