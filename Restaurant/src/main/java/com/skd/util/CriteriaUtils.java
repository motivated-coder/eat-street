package com.skd.util;

import com.skd.entity.Restaurant;
import org.apache.commons.lang.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class CriteriaUtils {
    public static void addEqualFilterPredicateEqual(List<Predicate> predicatesForFilter, CriteriaBuilder criteriaBuilder,
                                                    Root<?> root, String rootParam, Long id) {
        addEqualFilterPredicateEqual(predicatesForFilter, criteriaBuilder, root, rootParam, null, id);
    }

    public static void addEqualsFilterPredicate(List<Predicate> predicatesForFilter, CriteriaBuilder criteriaBuilder,
                                                    Root<?> root, String rootParam, String id) {
        addEqualsPredicate(predicatesForFilter, criteriaBuilder, root, rootParam, null, id);
    }

    public static void addOpenFilterPredicate(List<Predicate> predicatesForFilter, CriteriaBuilder criteriaBuilder,
                                                    Root<?> root, String rootParam, Boolean condition) {
        addConditionalPredicate(predicatesForFilter, criteriaBuilder, root, rootParam, null, condition);
    }

    private static void addEqualFilterPredicateEqual(List<Predicate> predicatesForFilter, CriteriaBuilder criteriaBuilder,
                                                     Root<?> root, String rootParam, String subRootParam, Long id) {
        if(id == null)
            return;
        if(StringUtils.isBlank(subRootParam))
            predicatesForFilter.add(criteriaBuilder.equal(root.get(rootParam), id));
        else
            predicatesForFilter.add(criteriaBuilder.equal(root.get(rootParam).get(subRootParam), id));
    }

    private static void addEqualsPredicate(List<Predicate> predicatesForFilter, CriteriaBuilder criteriaBuilder,
                                                     Root<?> root, String rootParam, String subRootParam, String id) {
        if(id == null)
            return;
        if(StringUtils.isBlank(subRootParam))
            predicatesForFilter.add(criteriaBuilder.equal(root.get(rootParam), id));
        else
            predicatesForFilter.add(criteriaBuilder.equal(root.get(rootParam).get(subRootParam), id));
    }

    private static void addConditionalPredicate(List<Predicate> predicatesForFilter, CriteriaBuilder criteriaBuilder,
                                                     Root<?> root, String rootParam, String subRootParam, Boolean condition) {
        if(condition == null)
            return;
        if(StringUtils.isBlank(subRootParam))
            predicatesForFilter.add(criteriaBuilder.equal(root.get(rootParam), condition));
        else
            predicatesForFilter.add(criteriaBuilder.equal(root.get(rootParam).get(subRootParam), condition));
    }

    public static void addLikeFilterPredicateEqual(List<Predicate> predicatesForFilter, CriteriaBuilder criteriaBuilder,
                                                   Root<?> root, String rootParam, String paramValue) {
        addLikeFilterPredicateEqual(predicatesForFilter, criteriaBuilder, root, rootParam, null, paramValue);
    }

    private static void addLikeFilterPredicateEqual(List<Predicate> predicatesForFilter, CriteriaBuilder criteriaBuilder,
                                                    Root<?> root, String rootParam, String subRootParam, String paramValue) {
        if(paramValue == null)
            return;
        String lowerCasedValue = StringUtils.lowerCase(paramValue);
        if(StringUtils.isBlank(subRootParam))
            predicatesForFilter.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(rootParam)), CriteriaUtils.formatFilterValue(lowerCasedValue)));
        else
            predicatesForFilter.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(rootParam).get(subRootParam)), CriteriaUtils.formatFilterValue(lowerCasedValue)));
    }

    private static String formatFilterValue(String lowerCasedValue) {
        String specialChar = StringUtils.isBlank(lowerCasedValue)? null : "%";
        String trimmedSpecialChar = StringUtils.trim(specialChar);
        if(specialChar == null)
            return lowerCasedValue;
        return trimmedSpecialChar + lowerCasedValue + trimmedSpecialChar;
    }

    private static void addStartDateFilterPredicate(List<Predicate> predicatesForFilter, CriteriaBuilder criteriaBuilder,
                                                    Root<Restaurant> restaurantRoot, String rootParam, Date date){
        if(date != null){
            predicatesForFilter.add(criteriaBuilder.greaterThanOrEqualTo(restaurantRoot.get("registeredDate"), date));
        }
    }

    public static void addTotalNoOfOrdersReceivedPredicate(List<Predicate> predicatesForFilter, CriteriaBuilder criteriaBuilder,
                                                           Root<Restaurant> restaurantRoot, String rootParam, Integer value){
        if(value != null){
            predicatesForFilter.add(criteriaBuilder.greaterThanOrEqualTo(restaurantRoot.get("totalOrdersReceived"), value));
        }
    }

    public static void addMenuItemPricePredicate(List<Predicate> predicatesForFilter, CriteriaBuilder criteriaBuilder,
                                                           Root<?> root, String rootParam, BigDecimal value){
        if(value != null){
            predicatesForFilter.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), value));
        }
    }
}







