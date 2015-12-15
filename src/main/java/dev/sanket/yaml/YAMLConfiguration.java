package dev.sanket.yaml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.yaml.snakeyaml.Yaml;

public class YAMLConfiguration
{
    private Map<Object, Object> properties = new HashMap<Object, Object>();

    @SuppressWarnings("unchecked")
    public void loadProperties()
    {
        try (InputStream propertiesStream = getClass().getClassLoader().getResourceAsStream("yamlconfig.yml"))
        {
            Yaml yaml = new Yaml();

            properties.putAll((Map<Object, Object>) yaml.load(propertiesStream));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void print()
    {
        for (Entry<Object, Object> entry : properties.entrySet())
        {
            Object key = entry.getKey();
            Object value = entry.getValue();

            System.out.println(key + "(" + key.getClass() + ") : " + value + "(" + value.getClass() + ")");
        }
    }
}
