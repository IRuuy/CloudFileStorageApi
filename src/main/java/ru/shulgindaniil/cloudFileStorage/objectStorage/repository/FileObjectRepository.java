package ru.shulgindaniil.cloudFileStorage.objectStorage.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.shulgindaniil.cloudFileStorage.objectStorage.domain.entity.FileObject;
import ru.shulgindaniil.cloudFileStorage.objectStorage.domain.entity.FileObjectType;

import java.util.Collection;
import java.util.Optional;

public interface FileObjectRepository extends JpaRepository<FileObject, String> {
    @EntityGraph(attributePaths = {"owner", "parent"})
    Optional<FileObject> findByIdAndOwnerIdAndType(String id, String ownerId, FileObjectType type);

    @Override
    @EntityGraph(attributePaths = {"owner", "parent"})
    Optional<FileObject> findById(String s);

    @EntityGraph(attributePaths = {"owner"})
    @Query("""
    SELECT fo FROM FileObject fo
    WHERE fo.owner.id = :ownerId AND fo.parent is null
    """)
    Optional<FileObject> findBaseParent(@Param("ownerId") String ownerId);

    @EntityGraph(attributePaths = {"owner", "parent"})
    @Query("""
    SELECT fo FROM FileObject fo
    WHERE fo.parent.id = :parentId AND fo.parent.owner.id = :ownerId
    """)
    Collection<FileObject> getAllChildren(String parentId, String ownerId);

    @Query(nativeQuery = true, value = """
    WITH RECURSIVE ItemHierarchy AS (
        SELECT * FROM file_object WHERE id = (SELECT parent_id FROM file_object WHERE id = :id)
        UNION ALL
        SELECT i.* FROM file_object i INNER JOIN ItemHierarchy ih ON i.id = ih.parent_id
    )
    SELECT * FROM ItemHierarchy;
    """)
    Collection<FileObject> getPath(String id);

    @Query(nativeQuery = true, value = """
    SELECT EXISTS(SELECT 1 FROM file_object WHERE parent_id = :id)
    """)
    boolean hashChild(String id);


    @Query(nativeQuery = true, value = """
    SELECT EXISTS (
        SELECT 1 FROM file_object
        WHERE parent_id = :parentId AND name = :name AND type = :type
     )
    """)
    boolean existFileObject(String parentId, String name, String type);
}
