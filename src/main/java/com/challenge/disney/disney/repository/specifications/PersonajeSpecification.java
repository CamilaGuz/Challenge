package com.challenge.disney.disney.repository.specifications;

import com.challenge.disney.disney.dto.PersonajeFiltersDTO;
import com.challenge.disney.disney.entity.PeliculaEntity;
import com.challenge.disney.disney.entity.PersonajeEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonajeSpecification {

    public Specification<PersonajeEntity> getByFilters(PersonajeFiltersDTO filtersDTO) {
        return ((root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filtersDTO.getName())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + filtersDTO.getName().toLowerCase() + "%"
                        )

                );
            }


            if (filtersDTO.getAge() != null) {
                predicates.add(
                        criteriaBuilder.equal(root.get("age"), filtersDTO.getAge()));

            }

            if (!CollectionUtils.isEmpty(filtersDTO.getIdmovie())) {
                Join<PeliculaEntity, PersonajeEntity> join = root.join("movieCharacters", JoinType.INNER);
                Expression<String> moviesIDS = join.get("id");
                predicates.add(moviesIDS.in(filtersDTO.getIdmovie()));

            }
            //remover dupiclados
            query.distinct(true);

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
