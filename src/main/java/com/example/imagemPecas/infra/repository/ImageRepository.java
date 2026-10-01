package com.example.imagemPecas.infra.repository;

import com.example.imagemPecas.domain.entity.Image;
import com.example.imagemPecas.domain.enums.ImageExtension;
import com.example.imagemPecas.infra.repository.specs.GenericSpecs;
import com.example.imagemPecas.infra.repository.specs.ImageSpecs;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.example.imagemPecas.infra.repository.specs.ImageSpecs.nameLike;
import static com.example.imagemPecas.infra.repository.specs.ImageSpecs.tagsLike;

public interface ImageRepository extends JpaRepository<Image, String>, JpaSpecificationExecutor<Image> {

    /**
     * Busca imagens filtrando, de forma opcional, por extensão e por nome/tags.
     * <p>
     * Equivale a:
     * <pre>
     * SELECT * FROM IMAGE WHERE 1 = 1
     *   AND EXTENSION = 'PNG'
     *   AND ( NAME LIKE 'QUERY' OR TAGS LIKE 'QUERY' )
     * </pre>
     *
     * @param extension extensão da imagem (ignorada se nula)
     * @param query     texto buscado no nome ou nas tags (ignorado se vazio)
     * @return imagens que atendem aos filtros informados
     */
    default List<Image> findByExtensionAndNameOrTagsLike(ImageExtension extension, String query) {
        // SELECT * FROM IMAGE WHERE 1 = 1
        Specification<Image> spec = Specification.where(GenericSpecs.conjunction());

        if (extension != null) {
            // AND EXTENSION = 'PNG'
            spec = spec.and(ImageSpecs.extensionEqual(extension));
        }

        if (StringUtils.hasText(query)) {
            // AND ( NAME LIKE 'QUERY' OR TAGS LIKE 'QUERY' )
            spec = spec.and(Specification.anyOf(nameLike(query), tagsLike(query)));
        }

        return findAll(spec);
    }

}
