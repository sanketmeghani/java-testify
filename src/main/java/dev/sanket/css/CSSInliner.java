package dev.sanket.css;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

import org.fit.cssbox.css.CSSNorm;
import org.fit.cssbox.css.DOMAnalyzer;
import org.fit.cssbox.css.NormalOutput;
import org.fit.cssbox.css.Output;
import org.fit.cssbox.io.DOMSource;
import org.fit.cssbox.io.DefaultDOMSource;
import org.fit.cssbox.io.DefaultDocumentSource;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class CSSInliner
{
    private String sourceURL;

    public CSSInliner(String sourceURL)
    {
        this.sourceURL = sourceURL;
    }

    public String process(String outputFile) throws IOException, SAXException
    {
        DefaultDocumentSource docSource = new DefaultDocumentSource(sourceURL);
        DOMSource domSource = new DefaultDOMSource(docSource);
        Document doc = domSource.parse();

        DOMAnalyzer da = new DOMAnalyzer(doc, docSource.getURL());
        da.attributesToStyles(); // convert the HTML presentation attributes to inline styles
        da.addStyleSheet(null, CSSNorm.stdStyleSheet(), DOMAnalyzer.Origin.AGENT); // use the standard style sheet
        da.addStyleSheet(null, CSSNorm.userStyleSheet(), DOMAnalyzer.Origin.AGENT); // use the additional style sheet
        da.addStyleSheet(null, CSSLoader.load("/home/sanket/eclipsews/HelloWorld/src/main/resources/sample.css"), DOMAnalyzer.Origin.USER);
        da.getStyleSheets(); // load the author style sheets

        da.stylesToDomInherited();

        PrintStream os = new PrintStream(new FileOutputStream(outputFile));
        Output out = new NormalOutput(doc);
        out.dumpTo(os);
        os.close();

        docSource.close();

        return null;
    }

    public static String inline(String html)
    {
        String filePath = "/tmp/";
        String inputFile = filePath + System.currentTimeMillis();
        String outputFile = filePath + "inlined-" + System.currentTimeMillis();

        StringBuilder output = new StringBuilder();

        try
        {
            FileWriter writer = new FileWriter(inputFile);
            writer.append(html);
            writer.flush();
            writer.close();

            File file = new File(inputFile);

            DefaultDocumentSource docSource = new DefaultDocumentSource(file.toURI().toURL());
            DOMSource domSource = new DefaultDOMSource(docSource);
            Document doc = domSource.parse();

            DOMAnalyzer da = new DOMAnalyzer(doc, docSource.getURL());
            da.attributesToStyles(); // convert the HTML presentation attributes to inline styles
            da.addStyleSheet(null, CSSNorm.stdStyleSheet(), DOMAnalyzer.Origin.AGENT); // use the standard style sheet
            da.addStyleSheet(null, CSSNorm.userStyleSheet(), DOMAnalyzer.Origin.AGENT); // use the additional style
                                                                                        // sheet
            da.getStyleSheets(); // load the author style sheets

            da.stylesToDomInherited();

            PrintStream os = new PrintStream(new FileOutputStream(outputFile));
            Output out = new NormalOutput(doc);
            out.dumpTo(os);
            os.close();

            docSource.close();

            BufferedReader reader = new BufferedReader(new FileReader(outputFile));

            String line = null;

            while ((line = reader.readLine()) != null)
            {
                output.append(line);
            }

            reader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (SAXException e)
        {
            e.printStackTrace();
        }
        finally
        {
            File file = new File(inputFile);
            file.delete();

            file = new File(outputFile);
            file.delete();
        }

        return output.toString();
    }
}
