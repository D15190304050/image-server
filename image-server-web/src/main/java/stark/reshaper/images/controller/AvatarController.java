package stark.reshaper.images.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import stark.dataworks.boot.web.ServiceResponse;
import stark.reshaper.images.rpc.IAvatarService;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping("/avatar")
public class AvatarController
{
    @Autowired
    private IAvatarService avatarService;

    @PostMapping("/upload")
    public ServiceResponse<String> uploadAvatar(@RequestParam("avatarFile") MultipartFile avatarFile)
    {
        return avatarService.uploadAvatar(avatarFile);
    }

    @GetMapping("/{avatarFileName}")
    public void getAvatar(@PathVariable("avatarFileName") String avatarFileName, HttpServletResponse response)
    {
        avatarService.getAvatar(avatarFileName, response);
    }
}
