package dev.kapiaszczyk.bookstore.library.category;

import java.util.ArrayList;
import java.util.List;

public class CategoryMapper {

    public static List<CategoryStatisticsDTO> mapToDTOList(List<Object[]> results) {
        List<CategoryStatisticsDTO> dtoList = new ArrayList<>();

        for (Object[] result : results) {
            String categoryName = (String) result[1];
            Long bookCount = (Long) result[2];
            CategoryStatisticsDTO dto = new CategoryStatisticsDTO(categoryName, bookCount);
            dtoList.add(dto);
        }

        return dtoList;
    }
}
