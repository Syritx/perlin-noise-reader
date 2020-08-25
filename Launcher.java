import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Launcher {
    public static void main(String[] args) throws IOException {

        int[] dimensions = NoiseReader.getDimensions("Noise.jpg");
        int[] noise = NoiseReader.readNoise("Noise.jpg", dimensions);

        int noiseDimensionID = 0;

        File noiseDisplayFile = new File("NoiseData.txt");
        noiseDisplayFile.createNewFile();

        PrintWriter writer = new PrintWriter(noiseDisplayFile);

        for (int x = 0; x < dimensions[1]; x++) {
            String noiseOneLine = "";
            
            for (int y = 0; y < dimensions[0]; y++) {
                noiseOneLine += noise[noiseDimensionID] + " ";
                noiseDimensionID++;
            }
            writer.write(noiseOneLine);
            writer.flush();
        }
        writer.close();

        Scanner scanner = new Scanner(noiseDisplayFile);
        int lineId = 0;
        do {    
            lineId++;
        }while (scanner.hasNextLine());

        scanner.close();
        System.out.println("lines found: " + lineId);
    }
}