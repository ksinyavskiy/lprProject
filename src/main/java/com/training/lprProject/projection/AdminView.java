package com.training.lprProject.projection;

import com.training.lprProject.entity.User;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "admin", types = {User.class})
public interface AdminView {

    String getUsername();

    String getEmail();
}
