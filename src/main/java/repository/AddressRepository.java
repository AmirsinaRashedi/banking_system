package repository;

import base.repository.BaseRepository;
import domain.Address;

public interface AddressRepository extends BaseRepository<Address, Long> {

    Address createAddress();

}
