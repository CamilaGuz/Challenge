package com.challenge.disney.disney.repository.specifications;

import com.challenge.disney.disney.dto.PeliculaFiltersDTO;
import com.challenge.disney.disney.entity.GeneroEntity;
import com.challenge.disney.disney.entity.PeliculaEntity;
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
public class PeliculaSpecification {

    public Specification<PeliculaEntity> getByFilters(PeliculaFiltersDTO filtersDTO){
        return((root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filtersDTO.getTitle())); {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title")),
                               "%" + filtersDTO.getTitle() + "%"
                        )
                );
            }

            if (!CollectionUtils.isEmpty(filtersDTO.getGenres())) {
                Join<PeliculaEntity, GeneroEntity> join = root.join("genderId", JoinType.INNER);
                Expression<String> genderID = join.get("id");
                predicates.add(genderID.in(filtersDTO.getGenres()));

            }
            query.distinct(true);

            //orden
            String orderByField = "title";
            query.orderBy(
                    filtersDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );


            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });

    }
}
