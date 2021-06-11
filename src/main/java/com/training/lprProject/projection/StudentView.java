package com.training.lprProject.projection;

import com.training.lprProject.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "student", types = {User.class})
public interface StudentView {

    @Value("#{target.firstName + ' ' + target.lastName}")
    String getFullName();

    String getEmail();
}
