//package com.be.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.List;
//import java.util.UUID;
//
//import software.amazon.awssdk.core.sync.RequestBody;
//import software.amazon.awssdk.services.s3.S3Client;
//import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
//import software.amazon.awssdk.services.s3.model.GetUrlRequest;
//import software.amazon.awssdk.services.s3.model.PutObjectRequest;
//
//@Component
//@RequiredArgsConstructor
//public class S3Service {
//    @Value("${cloud.aws.s3.bucket}")
//    private String bucketName;
//
//    @Value("${cloud.aws.s3.region}")
//    private String region;
//
//    private final S3Client s3Client;
//
//    public String upload(MultipartFile file, String directoryName) throws IOException {
//        String randomFilename =  generateRandomFilename(file);
//        String filename = directoryName + "/" + randomFilename;
//
//        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
//                .bucket(bucketName)
//                .key(filename)
//                .contentType(file.getContentType())
//                .contentLength(file.getSize())
//                .build();
//
//        s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
//
//        GetUrlRequest getUrlRequest = GetUrlRequest.builder()
//                .bucket(bucketName)
//                .key(filename)
//                .build();
//        return s3Client.utilities().getUrl(getUrlRequest).toString();
//    }
//
//    public void delete(String url) throws IOException {
//        String filename = url.substring(61);
//
//        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
//                .bucket(bucketName)
//                .key(filename)
//                .build();
//
//        s3Client.deleteObject(deleteObjectRequest);
//    }
//
//    // 랜덤 파일명 생성(파일명 중복 방지)
//    private String generateRandomFilename(MultipartFile multipartFile) {
//        String originalFilename = multipartFile.getOriginalFilename();
//        assert originalFilename != null;
//        String fileExtension = validateFileExtension(originalFilename);
//        return UUID.randomUUID() + "." + fileExtension;
//    }
//
//    // 파일 확장자 체크 및 예외 처리
//    private String validateFileExtension(String originalFilename) {
//        int lastDotIndex = originalFilename.lastIndexOf(".");
//        //파일 확장자 에러 처리 해야함
//        /*if(lastDotIndex == -1) {
//            throw new BusinessException(ErrorCode.NO_FILE_EXTENSION);
//        }*/
//
//        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
//        List<String> allowedExtensions = Arrays.asList("jpg", "png", "jpeg");
//
//        //파일 확장자 에러처리 해야함2
//        /*if (!allowedExtensions.contains(fileExtension)) {
//            throw new BusinessException(ErrorCode.INVALID_IMAGE_EXTENSION);
//        }*/
//        return fileExtension;
//    }
//}
