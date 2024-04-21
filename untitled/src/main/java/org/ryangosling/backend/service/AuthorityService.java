package org.ryangosling.backend.service;

import org.ryangosling.backend.domain.Authority;

import java.util.List;

public interface AuthorityService {
    Authority add(Authority authority);

    List<Authority> getAll();
}