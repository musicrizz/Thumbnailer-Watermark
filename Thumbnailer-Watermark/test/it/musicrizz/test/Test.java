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

    public Test() {
    }

    @BeforeClass
    public static void setUpClass() {
        spacex = Paths.get("test_resources", "spacex.jpg").toAbsolutePath();
        starship = Paths.get("test_resources", "starship.jpg").toAbsolutePath();
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

    //@org.junit.Test
    public void testThumbnailer() {
        
        byte[] out = null;
        try {
            out = Thumbnailer.createThumb(Files.readAllBytes(spacex));
            Files.write(spacex.resolve(Paths.get("../output", "spacexThumb.jpg")).normalize(), out, StandardOpenOption.CREATE);

            out = Thumbnailer.createThumb(Files.readAllBytes(starship));
            Files.write(spacex.resolve(Paths.get("../output", "starshipThumb.jpg")).normalize(), out, StandardOpenOption.CREATE);

            assertTrue(true);
        } catch (Exception e) {
            System.err.println("EXCEPTION TEST THUMB CREATION");
            System.err.println(e.getLocalizedMessage());
            fail();
        }
    }

    @org.junit.Test
    public void testWatermark() {
        
        byte[] out = null;
        try {
            out = Watermark.createWatermark(Files.readAllBytes(spacex), "\u00A9www.spacex.com");
            Files.write(spacex.resolve(Paths.get("../output", "spacexWatermark.jpg")).normalize(), out, StandardOpenOption.CREATE);

            out = Watermark.createWatermark(Files.readAllBytes(starship), "\u00A9www.spacex.com");
            Files.write(spacex.resolve(Paths.get("../output", "starshipWatermark.jpg")).normalize(), out, StandardOpenOption.CREATE);


            assertTrue(true);
        } catch (Exception e) {
            System.err.println("EXCEPTION TEST WATERMARK CREATION :");
            System.err.println(e.getLocalizedMessage());
            fail();
        }

    }
}
