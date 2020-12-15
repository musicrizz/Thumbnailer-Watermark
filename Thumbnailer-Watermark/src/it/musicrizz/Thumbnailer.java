/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.musicrizz;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;
import java.util.logging.Logger;

/**
 *
 * @author gio
 */
public class Thumbnailer {

    private static Logger log = Logger.getLogger(Thumbnailer.class.getName());
    
    public static byte[] createThumb(byte[] source) {
        return createThumb(source, null);
    }
    
    
    public static byte[] createThumb(byte[] source, Float scaleFactor) {

        if (source == null || source.length == 0) {
            return null;
        }

        BufferedImage bff_src;
        BufferedImage bff_dest;
        ByteArrayOutputStream thumb_file = null;

        try {
            bff_src = ImageIO.read(new ByteArrayInputStream(source));
            int w = bff_src.getWidth();
            int h = bff_src.getHeight();
            
            float scale = 0;
            if (scaleFactor != null && scaleFactor > 0) {
                 scale = scaleFactor;
            }else{
                scale = thumbScaleFactor(w, h);
            }

            w = even_number(Math.round(w * scale));
            h = even_number(Math.round(h * scale));

            AffineTransform trasformation = AffineTransform.getScaleInstance(scale, scale);
            AffineTransformOp scaling = new AffineTransformOp(trasformation, AffineTransformOp.TYPE_BILINEAR);

            bff_dest = new BufferedImage(w, h, bff_src.getType());
            Graphics2D g_out = ((Graphics2D) (bff_dest.getGraphics()));
            g_out.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g_out.drawImage(bff_src, scaling, 0, 0);

            thumb_file = new ByteArrayOutputStream();
            ImageIO.write(bff_dest, "jpg", thumb_file);
            g_out.dispose();

            return thumb_file.toByteArray();
        } catch (Exception e) {
            log.severe(()->"Exception thumb creation : "+e.getLocalizedMessage());
        } finally {
            try {
                if (thumb_file != null) {
                    thumb_file.close();
                }
                bff_src = null;
                bff_dest = null;
            } catch (Exception e) { }
        }

        return null;
    }

    public static int even_number(int n) {
        return n % 2 == 0 ? n : n + 1;
    }

    public static float thumbScaleFactor(int w, int h) {
        int max = Math.max(w, h);
        //very naive scale factor but , gives the idea !!
        if (max > 600 && max < 1024) {
            return 0.4F;
        }
        if (max > 1023 && max < 1480) {
            return 0.3F;
        }
        if (max > 1479) {
            return 0.2F;
        }
        
        return 1.F;
    }

}
