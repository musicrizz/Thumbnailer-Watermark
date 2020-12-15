/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.musicrizz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author gio
 */
public class Watermark {
    
    private static Logger log = Logger.getLogger(Watermark.class.getName());
    
    public static byte[] createWatermark(byte[] source , String watermark) {
        return  createWatermark(source , watermark, Color.WHITE, "Serif") ;
    }
    
    public static byte[] createWatermark(byte[] source , String watermark, Color color, String fontFamily)  {
        if (source == null || source.length == 0) {
            return null;
        }
        BufferedImage bff_src;
        BufferedImage bff_dest;
        ByteArrayOutputStream thumb_file = null;
        
        try{
                 
            bff_src = ImageIO.read(new ByteArrayInputStream(source));
            int w = bff_src.getWidth();
            int h = bff_src.getHeight();
            
            Font font = new Font(fontFamily, Font.PLAIN, ((Double)Math.floor(Math.min(w, h)*0.035)).intValue());
            
            bff_dest = new BufferedImage(w, h, bff_src.getType());
            Graphics2D g_out = ((Graphics2D) (bff_dest.getGraphics()));
            g_out.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g_out.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g_out.drawImage(bff_src, null, 0, 0);          
            g_out.setFont(font);
            g_out.setColor(color);
            g_out.drawString(watermark, 12, h-g_out.getFontMetrics().getMaxDescent());

            thumb_file = new ByteArrayOutputStream();
            ImageIO.write(bff_dest, "jpg", thumb_file);
            g_out.dispose();

            return thumb_file.toByteArray();
            
        }catch(Exception ex)  {
             log.severe(()->"Exception watermark creation : "+ex.getLocalizedMessage());
        } finally {
            try {
                if (thumb_file != null) {
                    thumb_file.close();
                }
                bff_src = null;
                bff_dest = null;
            } catch (Exception e) {
            }
        }
        return null;       
    }
    
}
