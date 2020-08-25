import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class NoiseReader {
    static int[] getDimensions(String path) {
        BufferedImage image;
        File noiseImage = new File(path);

        int[] dimensions = new int[2];

        try {
            image = ImageIO.read(noiseImage);
            int width = image.getWidth();
            int height = image.getHeight();

            dimensions[0] = width;
            dimensions[1] = height;
        }
        catch (IOException e) {}

        return dimensions;
    }

    static int[] readNoise(String path, int[] dimensions) {
        int[] noise = new int[dimensions[0]*dimensions[1]];
        
        BufferedImage image;
        File noiseImage = new File(path);

        try {
            image = ImageIO.read(noiseImage);

            int id = 0;
            for (int x = 0; x < dimensions[0]; x++) {
                for (int y = 0; y < dimensions[1]; y++) {
                    int rgb = image.getRGB(x, y);

                    int red = (rgb>>16)&0xff;
                    int green = (rgb>>8)&0xff;
                    int blue = rgb&0xff;

                    if (red == green && red == blue) {
                        noise[id] = 255-red;
                        id++;
                    }
                    else id++;
                }
            }
        } catch (IOException e) {}

        return noise;
    }
}