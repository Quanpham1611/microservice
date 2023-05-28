package quan.imageservice.service.impl;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import quan.imageservice.service.ImageService;

import java.io.IOException;
import java.util.UUID;
import org.springframework.util.StringUtils;
@Service
public class ImageServiceImpl implements ImageService {
    @Value("${firebase.bucket.name")
    private String bucketName;

    @Value("${firebase.get.image.url}")
    private String imageUrl;
    @Override
    public String uploadImage(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID() + StringUtils.getFilename(file.getOriginalFilename());
        ClassPathResource serviceAccount = new ClassPathResource("firebase.json");
        BlobId blobId = BlobId.of(bucketName, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
        Credentials credentials = GoogleCredentials.fromStream(serviceAccount.getInputStream());
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, file.getBytes());
        return String.format(imageUrl, fileName);
    }
}
