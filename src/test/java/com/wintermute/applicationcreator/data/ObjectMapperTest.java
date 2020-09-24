package com.wintermute.applicationcreator.data;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.wintermute.applicationcreator.document.DocumentCreator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Test the object mapping class.
 *
 * @author wintermute
 */
public class ObjectMapperTest
{
    private static DocumentContentFactory om;

    @BeforeAll
    public static void init() throws URISyntaxException, IOException
    {
        Reader reader = Files.newBufferedReader(
            Paths.get(DocumentContentFactory.class.getClassLoader().getResource("data.json").toURI()));
        JsonObject data = JsonParser.parseReader(reader).getAsJsonObject();
        DataCollector dc = new DataCollector();
        om = new DocumentContentFactory(dc.getDataFromJson(data));
    }

    @Test
    public void testApplicant()
    {
        Map<String, Function<String, String>> documentContent = om.getDocumentContent();
        Pattern pattern = Pattern.compile("(?><).*?([?=>]+)");
        String line = "jakis tam <header>";
        Matcher matcher = pattern.matcher(line);
        String group = "";
        if (matcher.find()) {
            group = matcher.group(0);
        }
        String target = "tvoja stara";

        line = line.replace(group, documentContent.get(group).apply(target));

        line = line.replace(group, target);
        System.out.println(line);

        //        dc.createDocument(new File(ObjectMapperTest.class.getClassLoader().getResource("cv.tex").getFile()), "cv_test");
//        dc.createDocument(new File(ObjectMapperTest.class.getClassLoader().getResource("cover.tex").getFile()),
//            "cover_test");
        //TODO: write a test after refactor
    }

    @Test
    public void getCoverLetter()
    {
        Map<String, Function<String, String>> documentContent = om.getDocumentContent();
        //TODO: write a test after refactor
    }
}
