package iloan.kernel;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class ImageFilter extends FileFilter
{

    //Accept all directories and all gif, jpg or png files.
    @Override
    public boolean accept(File f)
    {
        if (f.isDirectory())
        {
            return true;
        }
        String extension = ImageFilterUtils.getExtension(f);
        if (extension != null)
        {
            if (extension.equalsIgnoreCase(ImageFilterUtils.gif)
                    || extension.equalsIgnoreCase(ImageFilterUtils.jpeg)
                    || extension.equalsIgnoreCase(ImageFilterUtils.jpg)
                    || extension.equalsIgnoreCase(ImageFilterUtils.png))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        return false;
    }

    //The description of this filter
    @Override
    public String getDescription()
    {
        return "Images";
    }
}
