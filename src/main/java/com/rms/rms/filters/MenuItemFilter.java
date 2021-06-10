package com.rms.rms.filters;

import com.rms.rms.enums.Category;
import com.rms.rms.enums.Course;
import com.rms.rms.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MenuItemFilter {

    private String name;
    private Integer michelinStarts;
    private Course course;
    private Type type;
    private Category category;


}
