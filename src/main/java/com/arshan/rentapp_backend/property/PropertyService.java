package com.arshan.rentapp_backend.property;

import com.arshan.rentapp_backend.security.RentAppUserDetails;
import com.arshan.rentapp_backend.user.RentAppUser;
import com.arshan.rentapp_backend.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {
    private final PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    // Authentication method for Owner

    }

}
