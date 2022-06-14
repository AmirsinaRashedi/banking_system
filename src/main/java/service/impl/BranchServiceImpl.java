package service.impl;

import base.repository.BaseRepository;
import base.service.impl.BaseServiceImpl;
import domain.Branch;
import repository.BranchRepository;
import service.BranchService;

public class BranchServiceImpl extends BaseServiceImpl<Branch, Long>
        implements BranchService {

    private BranchRepository branchRepository;

    public BranchServiceImpl(BaseRepository repository, BranchRepository branchRepository) {

        super(repository);

        this.branchRepository = branchRepository;

    }

    @Override
    public Branch createBranch() {

        Branch newBranch = null;

        try {

            branchRepository.beginTransaction();

            newBranch = branchRepository.createBranch();

            branchRepository.commitTransaction();

        } catch (Exception e) {

            branchRepository.rollbackTransaction();

            e.getStackTrace();

            System.out.println(e.getMessage());

            return null;
        }

        return newBranch;
    }
}
