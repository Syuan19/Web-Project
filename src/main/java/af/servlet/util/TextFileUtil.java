package af.servlet.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by shaofa on 2017/11/15.
 */

public class TextFileUtil
{
    public static String read( File f, String charset) throws Exception
    {
        FileInputStream fstream = new FileInputStream(f);
        try{
            int fileSize = (int)f.length();
            if(fileSize > 1024*512)
                throw new Exception("File too large to read! size=" + fileSize);

            byte[] buffer = new byte[ fileSize ];
            int n = fstream.read(buffer);
            
            // detect utf-8 bom bytes
            int off = 0;
            int len = n;
            if(buffer[0] == (byte)0xef && buffer[1] == (byte)0xbb && buffer[2] == (byte)0xbf)
            {
            	off += 3;
            	len -= 3;
            }
            return new String(buffer, off, len , charset);
            
        }finally
        {
            try{ fstream.close();}catch (Exception e){}
        }
    }

    public static void write(File f, String text, String charset) throws Exception
    {
        FileOutputStream fstream = new FileOutputStream(f);
        try{
            fstream.write( text.getBytes( charset ));
        }finally
        {
            fstream.close();
        }
    }
}
