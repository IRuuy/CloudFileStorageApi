package ru.shulgindaniil.cloudFileStorage.objectStorage.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.shulgindaniil.cloudFileStorage.objectStorage.service.facade.FileFacade;
import ru.shulgindaniil.cloudFileStorage.objectStorage.web.dto.FileObjectFullDTO;
import ru.shulgindaniil.cloudFileStorage.security.UserDetailsImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cloud/upload")
public class UploadController {
    private final FileFacade fileFacade;

    @PostMapping("/file")
    public FileObjectFullDTO uploadFile(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestPart String parentId,
            @RequestPart MultipartFile file
    ) {
        return fileFacade.create(file, parentId, userDetails);
    }
}
