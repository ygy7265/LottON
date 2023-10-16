package kr.co.lottemarket.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.lottemarket.entity.admin.Admin_ProductFIleEntity;

@Repository
public interface Admin_FileRepository extends JpaRepository<Admin_ProductFIleEntity, String> {

}
