package com.training.lprProject.projections;

import org.springframework.beans.factory.annotation.Value;

public interface StudentView extends UserView {

    @Value("#{target.firstName + ' ' + target.lastName}")
    String getFullName();

    String getEmail();
}
