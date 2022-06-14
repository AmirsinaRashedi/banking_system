package service;

import base.service.BaseService;
import domain.Branch;

public interface BranchService extends BaseService<Branch, Long> {

    Branch createBranch();

}
