package com.gradProj.HUrry.Repositories;

import com.gradProj.HUrry.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long>  {
}
