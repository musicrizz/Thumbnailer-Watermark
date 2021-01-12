/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.musicrizz.test;

import it.musicrizz.Thumbnailer;
import it.musicrizz.Watermark;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author gio
 */
public class Test {

    private static Path spacex;
    private static Path starship;

    private static Path[] paths;

    public Test() {
    }

    @BeforeClass
    public static void setUpClass() {
        paths = new Path[2];
        paths[0] = Paths.get("test_resources", "spacex.jpg").toAbsolutePath();
        paths[1] = Paths.get("test_resources", "starship.jpg").toAbsolutePath();
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @org.junit.Test
    public void testThumbnailer() {

        Arrays.stream(paths).forEach(p -> {
            try {
                byte[] out = Thumbnailer.createThumb(Files.readAllBytes(p));
                String name_out = "thumb_" + p.getFileName().toString().split("\\.")[0] + ".jpg";
                Files.write(p.resolve(Paths.get("../output", name_out)).normalize(), out, StandardOpenOption.CREATE);
                assertTrue(true);
            } catch (Exception e) {
                System.err.println("EXCEPTION TEST THUMB CREATION");
                System.err.println(e.getLocalizedMessage());
                fail();
            }
        });

    }

    @org.junit.Test
    public void testWatermark() {

        Arrays.stream(paths).forEach(p -> {
            try {
                byte[] out = Watermark.createWatermark(Files.readAllBytes(p), "\u00A9www.spacex.com");
                String name_out = "watermk_" + p.getFileName().toString().split("\\.")[0] + ".jpg";
                Files.write(p.resolve(Paths.get("../output", name_out)).normalize(), out, StandardOpenOption.CREATE);
                assertTrue(true);
            } catch (Exception e) {
                System.err.println("EXCEPTION TEST WATERMARK CREATION");
                System.err.println(e.getLocalizedMessage());
                fail();
            }
        });

    }
}
