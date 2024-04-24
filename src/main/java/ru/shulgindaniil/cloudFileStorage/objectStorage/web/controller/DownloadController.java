package ru.shulgindaniil.cloudFileStorage.objectStorage.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shulgindaniil.cloudFileStorage.objectStorage.service.facade.FileFacade;
import ru.shulgindaniil.cloudFileStorage.objectStorage.service.facade.FolderFacade;
import ru.shulgindaniil.cloudFileStorage.security.UserDetailsImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/download/")
public class DownloadController {
    private final FileFacade fileFacade;
    private final FolderFacade folderFacade;

    @GetMapping("/file/{fileId}")
    public ResponseEntity<ByteArrayResource> downloadFile (
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable String fileId
    ) {
        return ResponseEntity.ok(fileFacade.download(fileId, userDetails));
    }

    @GetMapping("/folder/{folderId}")
    public ResponseEntity<ByteArrayResource> downloadFolder (
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable String folderId
    ) {
        return ResponseEntity.ok(folderFacade.download(folderId, userDetails));
    }
}
