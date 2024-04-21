package org.ryangosling.backend.service.impl;

import lombok.RequiredArgsConstructor;
import org.ryangosling.backend.dao.AuthorityRepository;
import org.ryangosling.backend.domain.Authority;
import org.ryangosling.backend.service.AuthorityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {
    private final AuthorityRepository authorityRepository;

    @Override
    public Authority add(Authority authority) {
        Optional<Authority> optionalAuthority = authorityRepository.findByAuthority(authority.getAuthority());

        if (optionalAuthority.isPresent())
            return optionalAuthority.get();
        return authorityRepository.save(authority);
    }

    @Override
    public List<Authority> getAll() {
        return authorityRepository.findAll();
    }
}
