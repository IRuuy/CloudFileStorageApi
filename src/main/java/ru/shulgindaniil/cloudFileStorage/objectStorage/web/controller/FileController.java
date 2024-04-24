package ru.shulgindaniil.cloudFileStorage.objectStorage.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.shulgindaniil.cloudFileStorage.objectStorage.domain.entity.FileObjectType;
import ru.shulgindaniil.cloudFileStorage.objectStorage.service.facade.FileFacade;
import ru.shulgindaniil.cloudFileStorage.objectStorage.service.facade.FileObjectFacade;
import ru.shulgindaniil.cloudFileStorage.objectStorage.web.dto.FileObjectFullDTO;
import ru.shulgindaniil.cloudFileStorage.security.UserDetailsImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cloud/file")
public class FileController {
    private final FileObjectFacade fileObjectFacade;
    private final FileFacade fileFacade;

    @DeleteMapping("/{fileId}")
    public ResponseEntity<HttpStatus> deleteFile(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable String fileId
    ) {
        fileFacade.delete(fileId, userDetails);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{fileId}")
    public ResponseEntity<FileObjectFullDTO> getFileInfo(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable String fileId
    ) {
        return ResponseEntity.ok(fileObjectFacade.getFileObject(userDetails, fileId, FileObjectType.FILE));
    }
}
