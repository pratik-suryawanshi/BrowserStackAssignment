package Utils;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUtils {
    /**
     * Downloads an image from the given URL and saves it to the specified file path.
     *
     * @param imageUrl The URL of the image to download.
     * @param filePath The name of the file to save the image as.
     */
    public static void saveImage(String imageUrl, String filePath) {
        try {
            URL url = new URL(imageUrl);
            Files.copy(url.openStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Image saved successfully.");
        } catch (IOException e) {
            System.err.println("Failed to save image: " + e.getMessage());
        }
    }
}

