package stark.reshaper.images.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import stark.dataworks.boot.ExceptionLogger;
import stark.dataworks.boot.autoconfig.minio.EasyMinio;
import stark.dataworks.boot.autoconfig.web.LogArgumentsAndResponse;
import stark.dataworks.boot.web.ServiceResponse;
import stark.reshaper.images.rpc.IAvatarService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.UUID;

@Slf4j
@Service
@LogArgumentsAndResponse
public class AvatarService implements IAvatarService
{
    @Value("${dataworks.easy-minio.bucket-name-avatar}")
    private String bucketNameAvatar;

    @Value("${images.avatar-url-prefix}")
    private String avatarUrlPrefix;


    @Autowired
    private EasyMinio easyMinio;

    @Override
    public ServiceResponse<String> uploadAvatar(MultipartFile avatarFile)
    {
        if (avatarFile == null)
            return ServiceResponse.buildErrorResponse(-3, "Argument null.");

        String avatarFileName = UUID.randomUUID().toString();
        String originalFilename = avatarFile.getOriginalFilename();
        int lastIndexOfDot = originalFilename.lastIndexOf(".");
        avatarFileName += originalFilename.substring(lastIndexOfDot);

        try
        {
            easyMinio.uploadFileByStream(bucketNameAvatar, avatarFileName, avatarFile.getInputStream());
        }
        catch (Exception e)
        {
            ExceptionLogger.logExceptionInfo(e);
            return ServiceResponse.buildErrorResponse(-1, "Upload failure, see provider log for more information.");
        }

        String avatarUrl = avatarUrlPrefix + avatarFileName;
        return ServiceResponse.buildSuccessResponse(avatarUrl);
    }

    @Override
    public void getAvatar(String avatarFileName, HttpServletResponse response)
    {
        try
        {
            InputStream avatarFileInputStream = easyMinio.getObjectInputStream(bucketNameAvatar, avatarFileName);
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            ServletOutputStream outputStream = response.getOutputStream();

            byte[] bytes = new byte[1024];
            int readLength;
            while ((readLength = avatarFileInputStream.read(bytes)) > 0)
                outputStream.write(bytes, 0, readLength);

            outputStream.close();
            avatarFileInputStream.close();
        }
        catch (Exception e)
        {
            ExceptionLogger.logExceptionInfo(e);
        }
    }
}
