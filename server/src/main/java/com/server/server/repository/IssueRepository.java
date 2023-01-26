package com.server.server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.server.server.model.Issue;

@Repository
public interface IssueRepository extends CrudRepository<Issue, Long> {
    Issue findIssueByProductId(Long id);
}
