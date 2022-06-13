package repository;

import base.repository.BaseRepository;
import domain.Branch;

public interface BranchRepository extends BaseRepository<Branch, Long> {

    Branch createBranch();

}
