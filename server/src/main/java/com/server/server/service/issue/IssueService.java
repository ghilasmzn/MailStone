package com.server.server.service.issue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.server.model.Issue;
import com.server.server.repository.IssueRepository;

@Service
public class IssueService implements IIssueService {

    @Autowired
    IssueRepository iRepo;

    @Override
    public Issue findIssueByProductId(Long id) {
        return iRepo.findIssueByProductId(id);
    }

    @Override
    public void save(Issue i) {
        iRepo.save(i);
    }
}
