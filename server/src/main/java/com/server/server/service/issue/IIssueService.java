package com.server.server.service.issue;

import com.server.server.model.Issue;

public interface IIssueService {
    Issue findIssueByProductId(Long id);

    void save(Issue i);
}
