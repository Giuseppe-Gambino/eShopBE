package it.epicode.eShop.cloudinary;

import com.cloudinary.Cloudinary;
import it.epicode.eShop.exceptions.UploadException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinarySvc {

        private final Cloudinary cloudinary;

        public Map uploader(MultipartFile file, String folder)  {

            try {
                Map result =
                        cloudinary
                                .uploader()
                                .upload(file.getBytes(),
                                        Cloudinary.asMap("folder", folder, "public_id", file.getOriginalFilename()));
                return result;
            } catch (IOException e) {
                throw new UploadException("Errore durante l'upload del file " + file.getOriginalFilename());
            }


        }

}