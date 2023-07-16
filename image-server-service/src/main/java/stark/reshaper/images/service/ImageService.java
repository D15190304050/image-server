package stark.reshaper.images.service;

import org.apache.dubbo.config.annotation.DubboService;
import stark.reshaper.images.rpc.IImageService;

@DubboService(version = "${dubbo.service.version}")
public class ImageService implements IImageService
{
}
