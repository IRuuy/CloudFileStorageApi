package ru.shulgindaniil.cloudFileStorage.objectStorage.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.shulgindaniil.cloudFileStorage.objectStorage.domain.entity.FileObjectType;
import ru.shulgindaniil.cloudFileStorage.objectStorage.service.FileObjectService;
import ru.shulgindaniil.cloudFileStorage.objectStorage.service.facade.FileObjectFacade;
import ru.shulgindaniil.cloudFileStorage.objectStorage.service.facade.FolderFacade;
import ru.shulgindaniil.cloudFileStorage.objectStorage.web.dto.FileObjectDTO;
import ru.shulgindaniil.cloudFileStorage.objectStorage.web.dto.FileObjectMiniDTO;
import ru.shulgindaniil.cloudFileStorage.security.UserDetailsImpl;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cloud/folder")
public class FolderController {
    private final FolderFacade folderFacade;
    private final FileObjectFacade fileObjectFacade;
    private final FileObjectService fileObjectService;

    @PostMapping()
    public FileObjectDTO createFolder(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody FileObjectMiniDTO objectMiniDto
    ) {
        return folderFacade.create(userDetails, objectMiniDto);
    }

    @GetMapping("/home")
    public FileObjectDTO getHomeInfo(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return folderFacade.getHome(userDetails);
    }

    @GetMapping("/{folderId}")
    public FileObjectDTO getFolderInfo(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable String folderId
    ) {
        return fileObjectFacade.getFileObject(userDetails, folderId, FileObjectType.FOLDER);
    }

    @DeleteMapping("/{folderId}")
    public ResponseEntity<HttpStatus> deleteFolder(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable String folderId,
            @RequestParam(required = false, defaultValue = "false") boolean isRecursed
    ) {
        folderFacade.delete(userDetails, folderId, isRecursed);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{folderId}/items")
    public Collection<FileObjectDTO> getItems(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable String folderId
    ) {
        return fileObjectService.getAllChildren(folderId, userDetails.getId());
    }
}
