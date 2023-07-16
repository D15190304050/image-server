package stark.reshaper.images.rpc;


import org.springframework.web.multipart.MultipartFile;
import stark.dataworks.boot.web.ServiceResponse;

import javax.servlet.http.HttpServletResponse;

public interface IAvatarService
{
    ServiceResponse<String> uploadAvatar(MultipartFile avatarFile);
    void getAvatar(String avatarFileName, HttpServletResponse response);
}
